package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.application.internal.commandservices;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.PressingStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.pressingstage.CreatePressingStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.pressingstage.DeletePressingStageByBatchCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.pressingstage.UpdatePressingStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.CompletionStatus;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.EndDate;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.StartDate;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.pressingstage.PressingStageCommandService;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.infrastructure.persistance.jpa.repositories.PressingStageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PressingStageCommandServiceImpl implements PressingStageCommandService {
    private final PressingStageRepository pressingStageRepository;

    @Override
    public Optional<PressingStage> handle(CreatePressingStageCommand command) {
        if (!validateDates(command.startDate(), command.endDate())) {
            throw new IllegalArgumentException("Start date must be before end date");
        }

        PressingStage pressingStage = new PressingStage(
                command.batchId(),
                command.employee(),
                command.startDate(),
                command.endDate()
        );

        pressingStage.setPressType(command.pressType());
        pressingStage.setPressure(command.pressure());
        pressingStage.setDuration(command.duration());
        pressingStage.setPomadeWeight(command.pomadeWeight());
        pressingStage.setYield(command.yield());
        pressingStage.setMustUsage(command.mustUsage());
        pressingStage.setComment(command.comment());

        return Optional.of(pressingStageRepository.save(pressingStage));
    }

    @Override
    public Optional<PressingStage> update(UpdatePressingStageCommand command) {
        return pressingStageRepository.findByBatchId(command.batchId())
                .map(pressingStage -> {
                    command.employee().ifPresent(pressingStage::setEmployee);
                    command.startDate().ifPresent(pressingStage::setStartDate);
                    command.endDate().ifPresent(pressingStage::setEndDate);
                    command.pressType().ifPresent(pressingStage::setPressType);
                    command.pressure().ifPresent(pressingStage::setPressure);
                    command.duration().ifPresent(pressingStage::setDuration);
                    command.pomadeWeight().ifPresent(pressingStage::setPomadeWeight);
                    command.yield().ifPresent(pressingStage::setYield);
                    command.mustUsage().ifPresent(pressingStage::setMustUsage);
                    command.comment().ifPresent(pressingStage::setComment);
                    command.completionStatus().ifPresent(pressingStage::setCompletionStatus);

                    if (pressingStage.getCompletionStatus() == CompletionStatus.COMPLETED) {
                        pressingStage.completePhase();
                    }

                    return pressingStageRepository.save(pressingStage);
                });
    }

    @Override
    public void delete(DeletePressingStageByBatchCommand command) {
        pressingStageRepository.deleteByBatchId(command.batchId());
    }

    public boolean validateDates(StartDate startDate, EndDate endDate) {
        if (startDate != null && endDate != null) {
            return !startDate.getStartDate().isAfter(endDate.getEndDate());
        }
        return false;
    }
}
