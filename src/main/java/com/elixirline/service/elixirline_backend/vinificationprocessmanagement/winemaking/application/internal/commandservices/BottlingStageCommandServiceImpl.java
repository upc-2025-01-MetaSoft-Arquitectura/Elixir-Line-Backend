package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.application.internal.commandservices;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.BottlingStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.bottlingstage.CreateBottlingStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.bottlingstage.DeleteBottlingStageByBatchCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.bottlingstage.UpdateBottlingStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.CompletionStatus;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.EndDate;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.StartDate;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.bottlingstage.BottlingStageCommandService;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.infrastructure.persistance.jpa.repositories.BottlingStageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BottlingStageCommandServiceImpl implements BottlingStageCommandService {
    private final BottlingStageRepository bottlingStageRepository;

    @Override
    public Optional<BottlingStage> handle(CreateBottlingStageCommand command) {
        if (!validateDates(command.startDate(), command.endDate())) {
            throw new IllegalArgumentException("Start date must be before end date");
        }

        BottlingStage bottlingStage = new BottlingStage(
                command.batchId(),
                command.employee(),
                command.startDate(),
                command.endDate()
        );

        bottlingStage.setBottlingLine(command.bottlingLine());
        bottlingStage.setFilledBottles(command.filledBottles());
        bottlingStage.setBottleVolume(command.bottleVolume());
        bottlingStage.setTotalVolume(command.totalVolume());
        bottlingStage.setSealingType(command.sealingType());
        bottlingStage.setVineyardCode(command.vineyardCode());
        bottlingStage.setTemperature(command.temperature());
        bottlingStage.setFilteredBeforeBottling(command.filteredBeforeBottling());
        bottlingStage.setLabelsAtThisStage(command.labelsAtThisStage());
        bottlingStage.setCapsuleOrSealApplication(command.capsuleOrSealApplication());
        bottlingStage.setComment(command.comment());

        return Optional.of(bottlingStageRepository.save(bottlingStage));
    }

    @Override
    public Optional<BottlingStage> update(UpdateBottlingStageCommand command) {
        return bottlingStageRepository.findByBatchId(command.batchId())
                .map(bottlingStage -> {
                    command.employee().ifPresent(bottlingStage::setEmployee);
                    command.startDate().ifPresent(bottlingStage::setStartDate);
                    command.endDate().ifPresent(bottlingStage::setEndDate);
                    command.bottlingLine().ifPresent(bottlingStage::setBottlingLine);
                    command.filledBottles().ifPresent(bottlingStage::setFilledBottles);
                    command.bottleVolume().ifPresent(bottlingStage::setBottleVolume);
                    command.totalVolume().ifPresent(bottlingStage::setTotalVolume);
                    command.sealingType().ifPresent(bottlingStage::setSealingType);
                    command.vineyardCode().ifPresent(bottlingStage::setVineyardCode);
                    command.temperature().ifPresent(bottlingStage::setTemperature);
                    command.filteredBeforeBottling().ifPresent(bottlingStage::setFilteredBeforeBottling);
                    command.labelsAtThisStage().ifPresent(bottlingStage::setLabelsAtThisStage);
                    command.capsuleOrSealApplication().ifPresent(bottlingStage::setCapsuleOrSealApplication);
                    command.comment().ifPresent(bottlingStage::setComment);
                    command.completionStatus().ifPresent(bottlingStage::setCompletionStatus);

                    if (bottlingStage.getCompletionStatus() == CompletionStatus.COMPLETED) {
                        bottlingStage.completePhase();
                    }

                    return bottlingStageRepository.save(bottlingStage);
                });
    }

    @Override
    public void delete(DeleteBottlingStageByBatchCommand command) {
        bottlingStageRepository.deleteByBatchId(command.batchId());
    }

    public boolean validateDates(StartDate startDate, EndDate endDate) {
        if (startDate != null && endDate != null) {
            return !startDate.getStartDate().isAfter(endDate.getEndDate());
        }
        return false;
    }
}
