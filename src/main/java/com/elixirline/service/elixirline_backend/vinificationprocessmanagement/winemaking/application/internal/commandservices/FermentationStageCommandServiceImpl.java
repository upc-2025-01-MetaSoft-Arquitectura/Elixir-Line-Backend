package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.application.internal.commandservices;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.exceptions.batch.BatchNotFoundException;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.Batch;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.FermentationStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.FiltrationStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.fermentationstage.CreateEmptyFermentationStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.fermentationstage.CreateFermentationStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.fermentationstage.DeleteFermentationStageByBatchCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.fermentationstage.UpdateFermentationStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.batch.CurrentStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.CompletionStatus;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.EndDate;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.StartDate;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.fermentationstage.FermentationStageCommandService;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.infrastructure.persistance.jpa.repositories.BatchRepository;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.infrastructure.persistance.jpa.repositories.FermentationStageRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FermentationStageCommandServiceImpl implements FermentationStageCommandService {
    private final FermentationStageRepository fermentationStageRepository;
    private final BatchRepository batchRepository;

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

                        Batch batch = batchRepository.findById(fermentationStage.getBatchId())
                                .orElseThrow(() -> new BatchNotFoundException(fermentationStage.getBatchId()));
                        batch.advanceToNextPhase();
                        batch.setCurrentStage(CurrentStage.PRESSING);

                        //Calcular el Hash de la etapa
                        String dataHash = calculateHash(fermentationStage);
                        fermentationStage.setDataHash(dataHash);
                        batchRepository.save(batch);
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

    private String calculateHash(FermentationStage fermentationStage) {
        try {
            StringBuilder dataToHash = getStringBuilder(fermentationStage);

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
    private static StringBuilder getStringBuilder(FermentationStage fermentationStage) {
        StringBuilder dataToHash = new StringBuilder();
        if (fermentationStage.getEmployee() != null) { dataToHash.append(fermentationStage.getEmployee()); }
        if (fermentationStage.getYeastUsed() != null) { dataToHash.append(fermentationStage.getYeastUsed()); }
        if (fermentationStage.getFermentationType() != null) { dataToHash.append(fermentationStage.getFermentationType()); }
        if (fermentationStage.getInitialSugarLevel() != null) { dataToHash.append(fermentationStage.getInitialSugarLevel()); }
        if (fermentationStage.getFinalSugarLevel() != null) { dataToHash.append(fermentationStage.getFinalSugarLevel()); }
        if (fermentationStage.getInitialPH() != null) { dataToHash.append(fermentationStage.getInitialPH()); }
        if (fermentationStage.getFinalPH() != null) { dataToHash.append(fermentationStage.getFinalPH()); }
        if (fermentationStage.getMaxTemperature() != null) { dataToHash.append(fermentationStage.getMaxTemperature()); }
        if (fermentationStage.getMinTemperature() != null) { dataToHash.append(fermentationStage.getMinTemperature()); }
        if (fermentationStage.getTankCode() != null) { dataToHash.append(fermentationStage.getTankCode()); }
        if (fermentationStage.getComment() != null) { dataToHash.append(fermentationStage.getComment()); }
        return dataToHash;
    }
}
