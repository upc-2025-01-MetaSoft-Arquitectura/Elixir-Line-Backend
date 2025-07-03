package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.application.internal.commandservices;

import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.commands.CreateEvidenceCommand;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.commands.DeleteEvidenceCommand;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.commands.PatchEvidenceCommand;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.entities.Evidence;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.services.EvidenceCommandService;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.infrastructure.persistance.jpa.repositories.EvidenceRepository;
import com.elixirline.service.elixirline_backend.shared.infrastructure.storage.AzureBlobService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EvidenceCommandServiceImpl implements EvidenceCommandService {
    private final EvidenceRepository evidenceRepository;
    private final AzureBlobService azureBlobService;

    public EvidenceCommandServiceImpl(EvidenceRepository evidenceRepository, AzureBlobService azureBlobService) {
        this.evidenceRepository = evidenceRepository;
        this.azureBlobService = azureBlobService;
    }

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
        return Optional.of(evidence);
    }

    @Override
    public Optional<Evidence> handle(PatchEvidenceCommand command) {
        var optionalEvidence = evidenceRepository.findById(command.evidenceId());
        if (optionalEvidence.isEmpty()) return Optional.empty();
        var evidence = optionalEvidence.get();
        String imageUrl = null;
        if (command.imageFile() != null && !command.imageFile().isEmpty()) {
            imageUrl = azureBlobService.upload(command.imageFile());
        }
        evidence.updateInformation(command.taskId(), command.description(), command.progressPercentage(), imageUrl);

        evidenceRepository.save(evidence);
        return Optional.of(evidence);
    }

    @Override
    public void handle(DeleteEvidenceCommand command) {
        if (!evidenceRepository.existsById(command.evidenceId())) {
            throw new IllegalArgumentException("Evidencia no encontrada.");
        }
        evidenceRepository.deleteById(command.evidenceId());
    }

}