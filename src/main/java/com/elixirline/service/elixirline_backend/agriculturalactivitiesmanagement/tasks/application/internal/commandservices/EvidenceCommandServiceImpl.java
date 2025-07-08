package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.application.internal.commandservices;

import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.commands.CreateEvidenceCommand;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.commands.DeleteEvidenceCommand;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.commands.PatchEvidenceCommand;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.commands.UpdateTaskCommand;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.entities.Evidence;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.queries.GetTaskByIdQuery;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.valueobjetcs.TaskStatus;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.services.EvidenceCommandService;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.services.TasksCommandService;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.services.TasksQueryService;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.infrastructure.persistance.jpa.repositories.EvidenceRepository;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.infrastructure.persistance.jpa.repositories.TasksRepository;
import com.elixirline.service.elixirline_backend.shared.infrastructure.storage.AzureBlobService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EvidenceCommandServiceImpl implements EvidenceCommandService {
    private final EvidenceRepository evidenceRepository;
    private final AzureBlobService azureBlobService;
    private final TasksQueryService tasksQueryService;
    private final TasksCommandService tasksCommandService;

    @Override
    public Optional<Evidence> handle(CreateEvidenceCommand command) {
        String imageUrl = azureBlobService.upload(command.imageFile());
        var evidence = new Evidence(
                command.taskId(),
                command.description(),
                command.progressPercentage(),
                imageUrl
        );
        evidenceRepository.save(evidence);

        updateTaskProgress(command.taskId(), command.progressPercentage(), true);
        return Optional.of(evidence);
    }

    @Override
    @Transactional
    public Optional<Evidence> handle(PatchEvidenceCommand command) {
        var optionalEvidence = evidenceRepository.findById(command.evidenceId());
        if (optionalEvidence.isEmpty()) return Optional.empty();

        var evidence = optionalEvidence.get();
        String imageUrl = evidence.getImageUrl();

        if (command.imageFile() != null && !command.imageFile().isEmpty()) {
            imageUrl = azureBlobService.upload(command.imageFile());
        }

        evidence.updateInformation(
                command.taskId() != null ? command.taskId() : evidence.getTaskId(),
                command.description() != null ? command.description() : evidence.getDescription(),
                command.progressPercentage() != null ? command.progressPercentage() : evidence.getProgressPercentage(),
                imageUrl
        );

        evidenceRepository.save(evidence);

        if (command.progressPercentage() != null) {
            updateTaskProgress(evidence.getTaskId(), command.progressPercentage(), false);
        }

        return Optional.of(evidence);
    }

    private void updateTaskProgress(Long taskId, Integer progressPercentage, boolean isNewEvidence) {
        var taskOptional = tasksQueryService.handle(new GetTaskByIdQuery(taskId));
        if (taskOptional.isEmpty()) return;

        var task = taskOptional.get();

        if (isNewEvidence && task.getStatus() == TaskStatus.NOT_STARTED) {
            task.setStatus(TaskStatus.IN_PROCESS);
        }

        task.setProgressPercentage(progressPercentage);

        if (progressPercentage == 100) {
            task.setStatus(TaskStatus.FINISHED);
        }

        tasksCommandService.handle(new UpdateTaskCommand(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStartDate(),
                task.getEndDate(),
                task.getWinegrowerId(),
                task.getFieldWorkerName(),
                task.getFieldWorkerId(),
                task.getBatchId(),
                task.getSuppliesIds(),
                task.getType()
        ));
    }

    @Override
    public void handle(DeleteEvidenceCommand command) {
        if (!evidenceRepository.existsById(command.evidenceId())) {
            throw new IllegalArgumentException("Evidencia no encontrada.");
        }
        evidenceRepository.deleteById(command.evidenceId());
    }
}