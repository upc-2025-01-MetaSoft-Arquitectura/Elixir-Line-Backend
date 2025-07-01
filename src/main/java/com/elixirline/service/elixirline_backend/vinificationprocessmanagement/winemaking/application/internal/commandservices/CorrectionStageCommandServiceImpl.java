package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.application.internal.commandservices;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.exceptions.batch.BatchNotFoundException;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.Batch;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.CorrectionStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.FermentationStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.correctionstage.CreateCorrectionStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.correctionstage.CreateEmptyCorrectionStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.correctionstage.DeleteCorrectionStageByBatchCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.correctionstage.UpdateCorrectionStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.batch.CurrentStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.CompletionStatus;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.EndDate;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.StartDate;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.correctionstage.CorrectionStageCommandService;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.infrastructure.persistance.jpa.repositories.BatchRepository;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.infrastructure.persistance.jpa.repositories.CorrectionStageRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CorrectionStageCommandServiceImpl implements CorrectionStageCommandService {
    private final CorrectionStageRepository correctionStageRepository;
    private final BatchRepository batchRepository;

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

                        Batch batch = batchRepository.findById(correctionState.getBatchId())
                                .orElseThrow(() -> new BatchNotFoundException(correctionState.getBatchId()));
                        batch.advanceToNextPhase();
                        batch.setCurrentStage(CurrentStage.FERMENTATION);

                        //Calcular el Hash de la etapa
                        String dataHash = calculateHash(correctionState);
                        correctionState.setDataHash(dataHash);
                        batchRepository.save(batch);
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

    private String calculateHash(CorrectionStage correctionStage) {
        try {
            StringBuilder dataToHash = getStringBuilder(correctionStage);

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(dataToHash.toString().getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error calculating hash", e);
        }
    }

    @NotNull
    private static StringBuilder getStringBuilder(CorrectionStage correctionStage) {
        StringBuilder dataToHash = new StringBuilder();
        if (correctionStage.getEmployee() != null) { dataToHash.append(correctionStage.getEmployee()); }
        if (correctionStage.getInitialSugarLevel() != null) { dataToHash.append(correctionStage.getInitialSugarLevel().getSugarLevel()); }
        if (correctionStage.getFinalSugarLevel() != null) { dataToHash.append(correctionStage.getFinalSugarLevel().getSugarLevel()); }
        if (correctionStage.getAddedSugar() != null) { dataToHash.append(correctionStage.getAddedSugar()); }
        if (correctionStage.getInitialPH() != null) { dataToHash.append(correctionStage.getInitialPH()); }
        if (correctionStage.getFinalPH() != null) { dataToHash.append(correctionStage.getFinalPH()); }
        if (correctionStage.getAcidType() != null) { dataToHash.append(correctionStage.getAcidType()); }
        if (correctionStage.getAddedAcid() != null) { dataToHash.append(correctionStage.getAddedAcid()); }
        if (correctionStage.getAddedSulphites() != null) { dataToHash.append(correctionStage.getAddedSulphites()); }
        if (correctionStage.getNutrients() != null) { dataToHash.append(correctionStage.getNutrients()); }
        if (correctionStage.getJustification() != null) { dataToHash.append(correctionStage.getJustification()); }
        if (correctionStage.getComment() != null) { dataToHash.append(correctionStage.getComment()); }
        return dataToHash;
    }
}
