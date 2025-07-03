package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.application.internal.commandservices;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.exceptions.batch.BatchNotFoundException;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.Batch;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.BottlingStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.ClarificationStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.bottlingstage.CreateBottlingStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.bottlingstage.CreateEmptyBottlingStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.bottlingstage.DeleteBottlingStageByBatchCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.bottlingstage.UpdateBottlingStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.batch.BatchStatus;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.CompletionStatus;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.EndDate;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.StartDate;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.bottlingstage.BottlingStageCommandService;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.infrastructure.persistance.jpa.repositories.BatchRepository;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.infrastructure.persistance.jpa.repositories.BottlingStageRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BottlingStageCommandServiceImpl implements BottlingStageCommandService {
    private final BottlingStageRepository bottlingStageRepository;
    private final BatchRepository batchRepository;

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
    public Optional<BottlingStage> handle(CreateEmptyBottlingStageCommand command) {
        BottlingStage bottlingStage = new BottlingStage();
        bottlingStage.setBatchId(command.batchId());
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

                        Batch batch = batchRepository.findById(bottlingStage.getBatchId())
                                .orElseThrow(() -> new BatchNotFoundException(bottlingStage.getBatchId()));
                        batch.advanceToNextPhase();
                        batch.setStatus(BatchStatus.FINISHED);

                        //Calcular el Hash de la etapa
                        String dataHash = calculateHash(bottlingStage);
                        bottlingStage.setDataHash(dataHash);
                        batchRepository.save(batch);
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

    private String calculateHash(BottlingStage bottlingStage) {
        try {
            StringBuilder dataToHash = getStringBuilder(bottlingStage);

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
    private static StringBuilder getStringBuilder(BottlingStage bottlingStage) {
        StringBuilder dataToHash = new StringBuilder();
        if (bottlingStage.getEmployee() != null) { dataToHash.append(bottlingStage.getEmployee()); }
        if(bottlingStage.getBottlingLine() != null) { dataToHash.append(bottlingStage.getBottlingLine()); }
        if (bottlingStage.getFilledBottles() != null) { dataToHash.append(bottlingStage.getFilledBottles()); }
        if (bottlingStage.getBottleVolume() != null) { dataToHash.append(bottlingStage.getBottleVolume()); }
        if (bottlingStage.getTotalVolume() != null) { dataToHash.append(bottlingStage.getTotalVolume()); }
        if (bottlingStage.getSealingType() != null) { dataToHash.append(bottlingStage.getSealingType()); }
        if (bottlingStage.getVineyardCode() != null) { dataToHash.append(bottlingStage.getVineyardCode()); }
        if (bottlingStage.getTemperature() != null) { dataToHash.append(bottlingStage.getTemperature()); }
        if (bottlingStage.getFilteredBeforeBottling() != null) { dataToHash.append(bottlingStage.getFilteredBeforeBottling()); }
        if (bottlingStage.getLabelsAtThisStage() != null) { dataToHash.append(bottlingStage.getLabelsAtThisStage()); }
        if (bottlingStage.getCapsuleOrSealApplication() != null) { dataToHash.append(bottlingStage.getCapsuleOrSealApplication()); }
        if (bottlingStage.getComment() != null) { dataToHash.append(bottlingStage.getComment()); }
        return dataToHash;
    }
}
