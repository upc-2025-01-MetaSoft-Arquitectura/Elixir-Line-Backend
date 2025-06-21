package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.application.internal.commandservices;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.ReceptionStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.receptionstage.CreateReceptionStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.receptionstage.DeleteReceptionStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.receptionstage.UpdateReceptionStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.CompletionStatus;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.EndDate;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.StartDate;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.receptionstage.ReceptionStageCommandService;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.infrastructure.persistance.jpa.repositories.ReceptionStageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReceptionStageCommandServiceImpl implements ReceptionStageCommandService {
    private final ReceptionStageRepository receptionStageRepository;

    @Override
    public Optional<ReceptionStage> handle(CreateReceptionStageCommand command) {

        if (!validateDates(command.startDate(), command.endDate())) {
            throw new IllegalArgumentException("Start date must be before end date");
        }

        ReceptionStage receptionStage = new ReceptionStage(
                command.batchId(),
                command.employee(),
                command.startDate(),
                command.endDate()
        );
        receptionStage.setSugarLevel(command.sugarLevel());
        receptionStage.setPHLevel(command.pHLevel());
        receptionStage.setTemperature(command.temperature());
        receptionStage.setQuantityKg(command.quantityKg());
        receptionStage.setComment(command.comment());

        return Optional.of(receptionStageRepository.save(receptionStage));
    }

    @Override
    public Optional<ReceptionStage> update(UpdateReceptionStageCommand command) {
        return receptionStageRepository.findByBatchId(command.batchId())
                .map(receptionStage -> {
                    command.employee().ifPresent(receptionStage::setEmployee);
                    command.startDate().ifPresent(receptionStage::setStartDate);
                    command.endDate().ifPresent(receptionStage::setEndDate);
                    command.sugarLevel().ifPresent(receptionStage::setSugarLevel);
                    command.pHLevel().ifPresent(receptionStage::setPHLevel);
                    command.temperature().ifPresent(receptionStage::setTemperature);
                    command.quantityKg().ifPresent(receptionStage::setQuantityKg);
                    command.comment().ifPresent(receptionStage::setComment);
                    command.completionStatus().ifPresent(receptionStage::setCompletionStatus);

                    if (receptionStage.getCompletionStatus() == CompletionStatus.COMPLETED) {
                        receptionStage.completePhase();
                    }

                    return receptionStageRepository.save(receptionStage);
                });
    }

    @Override
    @Transactional
    public void delete(DeleteReceptionStageCommand command) {
        receptionStageRepository.deleteByBatchId(command.batchId());
    }

    public boolean validateDates(StartDate startDate, EndDate endDate) {
        if (startDate != null && endDate != null) {
            return !startDate.getStartDate().isAfter(endDate.getEndDate());
        }
        return false;
    }
}
