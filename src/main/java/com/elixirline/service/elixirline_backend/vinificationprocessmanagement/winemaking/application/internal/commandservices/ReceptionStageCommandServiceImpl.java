package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.application.internal.commandservices;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.exceptions.batch.BatchNotFoundException;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.Batch;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.ReceptionStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.receptionstage.CreateEmptyReceptionStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.receptionstage.CreateReceptionStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.receptionstage.DeleteReceptionStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.receptionstage.UpdateReceptionStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.batch.BatchStatus;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.batch.CurrentStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.CompletionStatus;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.EndDate;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.StartDate;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.receptionstage.ReceptionStageCommandService;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.infrastructure.persistance.jpa.repositories.BatchRepository;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.infrastructure.persistance.jpa.repositories.ReceptionStageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReceptionStageCommandServiceImpl implements ReceptionStageCommandService {
    private final ReceptionStageRepository receptionStageRepository;
    private final BatchRepository batchRepository;

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
    public Optional<ReceptionStage> handle(CreateEmptyReceptionStageCommand command) {
        ReceptionStage receptionStage = new ReceptionStage();
        receptionStage.setBatchId(command.batchId());
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

                        Batch batch = batchRepository.findById(receptionStage.getBatchId())
                                .orElseThrow(() -> new BatchNotFoundException(receptionStage.getBatchId()));
                        batch.advanceToNextPhase();
                        batch.setCurrentStage(CurrentStage.CORRECTION);
                        batch.setStatus(BatchStatus.IN_PROCESS);

                        //Calcular el Hash de la etapa
                        String dataHash = calculateHash(receptionStage);
                        receptionStage.setDataHash(dataHash);
                        batchRepository.save(batch);
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


    private String calculateHash(ReceptionStage receptionStage) {
        try {
            StringBuilder dataToHash = getStringBuilder(receptionStage);

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
    private static StringBuilder getStringBuilder(ReceptionStage receptionStage) {
        StringBuilder dataToHash = new StringBuilder();
        if (receptionStage.getEmployee() != null) { dataToHash.append(receptionStage.getEmployee()); }
        if (receptionStage.getSugarLevel() != null) { dataToHash.append(receptionStage.getSugarLevel().getReceptionSugarLevel()); }
        if (receptionStage.getPHLevel() != null) { dataToHash.append(receptionStage.getPHLevel().getPHLevel()); }
        if (receptionStage.getTemperature() != null) { dataToHash.append(receptionStage.getTemperature().getTemperature());}
        if (receptionStage.getQuantityKg() != null) { dataToHash.append(receptionStage.getQuantityKg().getQuantityKg()); }
        if (receptionStage.getComment() != null) { dataToHash.append(receptionStage.getComment()); }
        return dataToHash;
    }
}
