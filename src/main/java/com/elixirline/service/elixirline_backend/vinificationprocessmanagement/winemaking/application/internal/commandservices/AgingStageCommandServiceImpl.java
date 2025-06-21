package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.application.internal.commandservices;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.AgingStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.agingstage.CreateAgingStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.agingstage.CreateEmptyAgingStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.agingstage.DeleteAgingStageByBatchCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.agingstage.UpdateAgingStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.CompletionStatus;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.EndDate;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.StartDate;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.agingstage.AgingStageCommandService;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.infrastructure.persistance.jpa.repositories.AgingStageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AgingStageCommandServiceImpl implements AgingStageCommandService {
    private final AgingStageRepository agingStageRepository;

    @Override
    public Optional<AgingStage> handle(CreateAgingStageCommand command) {
        if (!validateDates(command.startDate(), command.endDate())) {
            throw new IllegalArgumentException("Start date must be before end date");
        }

        AgingStage agingStage = new AgingStage(
                command.batchId(),
                command.employee(),
                command.startDate(),
                command.endDate()
        );

        agingStage.setContainerType(command.containerType());
        agingStage.setMaterial(command.material());
        agingStage.setContainerCode(command.containerCode());
        agingStage.setAverageTemperature(command.averageTemperature());
        agingStage.setVolume(command.volume());
        agingStage.setDuration(command.duration());
        agingStage.setFrequency(command.frequency());
        agingStage.setBatonnage(command.batonnage());
        agingStage.setRefills(command.refills());
        agingStage.setRackings(command.rackings());
        agingStage.setPurpose(command.purpose());
        agingStage.setComment(command.comment());

        return Optional.of(agingStageRepository.save(agingStage));
    }

    @Override
    public Optional<AgingStage> handle(CreateEmptyAgingStageCommand command) {
        AgingStage agingStage = new AgingStage();
        agingStage.setBatchId(command.batchId());
        return Optional.of(agingStageRepository.save(agingStage));
    }

    @Override
    public Optional<AgingStage> update(UpdateAgingStageCommand command) {
        return agingStageRepository.findByBatchId(command.batchId())
                .map(agingStage -> {
                    command.employee().ifPresent(agingStage::setEmployee);
                    command.startDate().ifPresent(agingStage::setStartDate);
                    command.endDate().ifPresent(agingStage::setEndDate);
                    command.containerType().ifPresent(agingStage::setContainerType);
                    command.material().ifPresent(agingStage::setMaterial);
                    command.containerCode().ifPresent(agingStage::setContainerCode);
                    command.averageTemperature().ifPresent(agingStage::setAverageTemperature);
                    command.volume().ifPresent(agingStage::setVolume);
                    command.duration().ifPresent(agingStage::setDuration);
                    command.frequency().ifPresent(agingStage::setFrequency);
                    command.batonnage().ifPresent(agingStage::setBatonnage);
                    command.refills().ifPresent(agingStage::setRefills);
                    command.rackings().ifPresent(agingStage::setRackings);
                    command.purpose().ifPresent(agingStage::setPurpose);
                    command.comment().ifPresent(agingStage::setComment);
                    command.completionStatus().ifPresent(agingStage::setCompletionStatus);

                    if (agingStage.getCompletionStatus() == CompletionStatus.COMPLETED) {
                        agingStage.completePhase();
                    }

                    return agingStageRepository.save(agingStage);
                });
    }

    @Override
    public void delete(DeleteAgingStageByBatchCommand command) {
        agingStageRepository.deleteByBatchId(command.batchId());
    }

    public boolean validateDates(StartDate startDate, EndDate endDate) {
        if (startDate != null && endDate != null) {
            return !startDate.getStartDate().isAfter(endDate.getEndDate());
        }
        return false;
    }
}
