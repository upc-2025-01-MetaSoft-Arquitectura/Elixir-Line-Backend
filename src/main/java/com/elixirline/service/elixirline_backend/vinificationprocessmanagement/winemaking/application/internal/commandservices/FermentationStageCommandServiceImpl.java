package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.application.internal.commandservices;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.FermentationStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.fermentationstage.CreateEmptyFermentationStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.fermentationstage.CreateFermentationStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.fermentationstage.DeleteFermentationStageByBatchCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.fermentationstage.UpdateFermentationStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.CompletionStatus;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.EndDate;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.StartDate;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.fermentationstage.FermentationStageCommandService;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.infrastructure.persistance.jpa.repositories.FermentationStageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FermentationStageCommandServiceImpl implements FermentationStageCommandService {
    private final FermentationStageRepository fermentationStageRepository;

    @Override
    public Optional<FermentationStage> handle(CreateFermentationStageCommand command) {
        if (!validateDates(command.startDate(), command.endDate())) {
            throw new IllegalArgumentException("Start date must be before end date");
        }

        FermentationStage fermentationStage = new FermentationStage(
                command.batchId(),
                command.employee(),
                command.startDate(),
                command.endDate()
        );

        fermentationStage.setYeastUsed(command.yeastUsed());
        fermentationStage.setFermentationType(command.fermentationType());
        fermentationStage.setInitialSugarLevel(command.initialSugarLevel());
        fermentationStage.setFinalSugarLevel(command.finalSugarLevel());
        fermentationStage.setInitialPH(command.initialPH());
        fermentationStage.setFinalPH(command.finalPH());
        fermentationStage.setMaxTemperature(command.maxTemperature());
        fermentationStage.setMinTemperature(command.minTemperature());
        fermentationStage.setTankCode(command.tankCode());
        fermentationStage.setComment(command.comment());

        return Optional.of(fermentationStageRepository.save(fermentationStage));
    }

    @Override
    public Optional<FermentationStage> handle(CreateEmptyFermentationStageCommand command) {
        FermentationStage fermentationStage = new FermentationStage();
        fermentationStage.setBatchId(command.batchId());
        return Optional.of(fermentationStageRepository.save(fermentationStage));
    }

    @Override
    public Optional<FermentationStage> update(UpdateFermentationStageCommand command) {
        return fermentationStageRepository.findByBatchId(command.batchId())
                .map(fermentationStage -> {
                    command.employee().ifPresent(fermentationStage::setEmployee);
                    command.startDate().ifPresent(fermentationStage::setStartDate);
                    command.endDate().ifPresent(fermentationStage::setEndDate);
                    command.yeastUsed().ifPresent(fermentationStage::setYeastUsed);
                    command.fermentationType().ifPresent(fermentationStage::setFermentationType);
                    command.initialSugarLevel().ifPresent(fermentationStage::setInitialSugarLevel);
                    command.finalSugarLevel().ifPresent(fermentationStage::setFinalSugarLevel);
                    command.initialPH().ifPresent(fermentationStage::setInitialPH);
                    command.finalPH().ifPresent(fermentationStage::setFinalPH);
                    command.maxTemperature().ifPresent(fermentationStage::setMaxTemperature);
                    command.minTemperature().ifPresent(fermentationStage::setMinTemperature);
                    command.tankCode().ifPresent(fermentationStage::setTankCode);
                    command.comment().ifPresent(fermentationStage::setComment);
                    command.completionStatus().ifPresent(fermentationStage::setCompletionStatus);

                    if (fermentationStage.getCompletionStatus() == CompletionStatus.COMPLETED) {
                        fermentationStage.completePhase();
                    }

                    return fermentationStageRepository.save(fermentationStage);
                });
    }

    @Override
    public void delete(DeleteFermentationStageByBatchCommand command) {
        fermentationStageRepository.deleteByBatchId(command.batchId());
    }

    public boolean validateDates(StartDate startDate, EndDate endDate) {
        if (startDate != null && endDate != null) {
            return !startDate.getStartDate().isAfter(endDate.getEndDate());
        }
        return false;
    }
}
