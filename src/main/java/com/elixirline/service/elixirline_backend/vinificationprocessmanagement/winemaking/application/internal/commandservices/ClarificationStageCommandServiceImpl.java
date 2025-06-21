package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.application.internal.commandservices;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.ClarificationStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.clarificationstage.CreateClarificationStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.clarificationstage.DeleteClarificationStageByBatchCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.clarificationstage.UpdateClarificationStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.CompletionStatus;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.EndDate;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.StartDate;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.clarificationstage.ClarificationStageCommandService;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.infrastructure.persistance.jpa.repositories.ClarificationStageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClarificationStageCommandServiceImpl implements ClarificationStageCommandService {
    private final ClarificationStageRepository clarificationStageRepository;

    @Override
    public Optional<ClarificationStage> handle(CreateClarificationStageCommand command) {
        if (!validateDates(command.startDate(), command.endDate())) {
            throw new IllegalArgumentException("Start date must be before end date");
        }

        ClarificationStage clarificationStage = new ClarificationStage(
                command.batchId(),
                command.employee(),
                command.startDate(),
                command.endDate()
        );

        clarificationStage.setMethodUsed(command.methodUsed());
        clarificationStage.setInitialTurbidity(command.initialTurbidity());
        clarificationStage.setFinalTurbidity(command.finalTurbidity());
        clarificationStage.setVolume(command.volume());
        clarificationStage.setTemperature(command.temperature());
        clarificationStage.setDuration(command.duration());
        clarificationStage.setClarifyingAgents(command.clarifyingAgents());
        clarificationStage.setComment(command.comment());

        return Optional.of(clarificationStageRepository.save(clarificationStage));
    }

    @Override
    public Optional<ClarificationStage> update(UpdateClarificationStageCommand command) {
        return clarificationStageRepository.findByBatchId(command.batchId())
                .map(clarificationStage -> {
                    command.employee().ifPresent(clarificationStage::setEmployee);
                    command.startDate().ifPresent(clarificationStage::setStartDate);
                    command.endDate().ifPresent(clarificationStage::setEndDate);
                    command.methodUsed().ifPresent(clarificationStage::setMethodUsed);
                    command.initialTurbidity().ifPresent(clarificationStage::setInitialTurbidity);
                    command.finalTurbidity().ifPresent(clarificationStage::setFinalTurbidity);
                    command.volume().ifPresent(clarificationStage::setVolume);
                    command.temperature().ifPresent(clarificationStage::setTemperature);
                    command.duration().ifPresent(clarificationStage::setDuration);
                    command.clarifyingAgents().ifPresent(clarificationStage::setClarifyingAgents);
                    command.comment().ifPresent(clarificationStage::setComment);
                    command.completionStatus().ifPresent(clarificationStage::setCompletionStatus);

                    if (clarificationStage.getCompletionStatus() == CompletionStatus.COMPLETED) {
                        clarificationStage.completePhase();
                    }

                    return clarificationStageRepository.save(clarificationStage);
                });
    }

    @Override
    public void delete(DeleteClarificationStageByBatchCommand command) {
        clarificationStageRepository.deleteByBatchId(command.batchId());
    }

    public boolean validateDates(StartDate startDate, EndDate endDate) {
        if (startDate != null && endDate != null) {
            return !startDate.getStartDate().isAfter(endDate.getEndDate());
        }
        return false;
    }
}
