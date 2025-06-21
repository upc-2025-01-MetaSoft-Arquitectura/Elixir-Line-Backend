package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.application.internal.commandservices;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.CorrectionStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.correctionstage.CreateCorrectionStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.correctionstage.CreateEmptyCorrectionStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.correctionstage.DeleteCorrectionStageByBatchCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.correctionstage.UpdateCorrectionStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.CompletionStatus;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.EndDate;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.StartDate;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.correctionstage.CorrectionStageCommandService;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.infrastructure.persistance.jpa.repositories.CorrectionStageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CorrectionStageCommandServiceImpl implements CorrectionStageCommandService {
    private final CorrectionStageRepository correctionStageRepository;

    @Override
    public Optional<CorrectionStage> handle(CreateCorrectionStageCommand command) {

        if (!validateDates(command.startDate(), command.endDate())) {
            throw new IllegalArgumentException("Start date must be before end date");
        }

        CorrectionStage correctionState = new CorrectionStage(
                command.batchId(),
                command.employee(),
                command.startDate(),
                command.endDate()
        );

        correctionState.setInitialSugarLevel(command.initialSugarLevel());
        correctionState.setFinalSugarLevel(command.finalSugarLevel());
        correctionState.setAddedSugar(command.addedSugar());
        correctionState.setInitialPH(command.initialPH());
        correctionState.setFinalPH(command.finalPH());
        correctionState.setAcidType(command.acidType());
        correctionState.setAddedAcid(command.addedAcid());
        correctionState.setAddedSulphites(command.addedSulphites());
        correctionState.setNutrients(command.nutrients());
        correctionState.setJustification(command.justification());
        correctionState.setComment(command.comment());

        return Optional.of(correctionStageRepository.save(correctionState));

    }

    @Override
    public Optional<CorrectionStage> handle(CreateEmptyCorrectionStageCommand command) {
        CorrectionStage correctionStage = new CorrectionStage();
        correctionStage.setBatchId(command.batchId());
        return Optional.of(correctionStageRepository.save(correctionStage));
    }

    @Override
    public Optional<CorrectionStage> update(UpdateCorrectionStageCommand command) {
        return correctionStageRepository.findByBatchId(command.batchId())
                .map(correctionState -> {
                    command.employee().ifPresent(correctionState::setEmployee);
                    command.startDate().ifPresent(correctionState::setStartDate);
                    command.endDate().ifPresent(correctionState::setEndDate);
                    command.initialSugarLevel().ifPresent(correctionState::setInitialSugarLevel);
                    command.finalSugarLevel().ifPresent(correctionState::setFinalSugarLevel);
                    command.addedSugar().ifPresent(correctionState::setAddedSugar);
                    command.initialPH().ifPresent(correctionState::setInitialPH);
                    command.finalPH().ifPresent(correctionState::setFinalPH);
                    command.acidType().ifPresent(correctionState::setAcidType);
                    command.addedAcid().ifPresent(correctionState::setAddedAcid);
                    command.addedSulphites().ifPresent(correctionState::setAddedSulphites);
                    command.nutrients().ifPresent(correctionState::setNutrients);
                    command.justification().ifPresent(correctionState::setJustification);
                    command.comment().ifPresent(correctionState::setComment);
                    command.completionStatus().ifPresent(correctionState::setCompletionStatus);

                    if (correctionState.getCompletionStatus() == CompletionStatus.COMPLETED) {
                        correctionState.completePhase();
                    }

                    return correctionStageRepository.save(correctionState);
                });
    }

    @Override
    public void delete(DeleteCorrectionStageByBatchCommand command) {
        correctionStageRepository.deleteByBatchId(command.batchId());
    }

    public boolean validateDates(StartDate startDate, EndDate endDate) {
        if (startDate != null && endDate != null) {
            return !startDate.getStartDate().isAfter(endDate.getEndDate());
        }
        return false;
    }
}
