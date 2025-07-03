package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.application.internal.commandservices;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.exceptions.batch.BatchNotFoundException;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.Batch;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.PressingStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.pressingstage.CreateEmptyPressingStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.pressingstage.CreatePressingStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.pressingstage.DeletePressingStageByBatchCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.pressingstage.UpdatePressingStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.batch.CurrentStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.CompletionStatus;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.EndDate;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.StartDate;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.pressingstage.PressingStageCommandService;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.infrastructure.persistance.jpa.repositories.BatchRepository;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.infrastructure.persistance.jpa.repositories.PressingStageRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PressingStageCommandServiceImpl implements PressingStageCommandService {
    private final PressingStageRepository pressingStageRepository;
    private final BatchRepository batchRepository;

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
    public Optional<PressingStage> handle(CreateEmptyPressingStageCommand command) {
        PressingStage pressingStage = new PressingStage();
        pressingStage.setBatchId(command.batchId());
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

                        Batch batch = batchRepository.findById(pressingStage.getBatchId())
                                .orElseThrow(() -> new BatchNotFoundException(pressingStage.getBatchId()));
                        batch.advanceToNextPhase();
                        batch.setCurrentStage(CurrentStage.CLARIFICATION);

                        //Calcular el Hash de la etapa
                        String dataHash = calculateHash(pressingStage);
                        pressingStage.setDataHash(dataHash);
                        batchRepository.save(batch);
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

    private String calculateHash(PressingStage pressingStage) {
        try {
            StringBuilder dataToHash = getStringBuilder(pressingStage);

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
    private static StringBuilder getStringBuilder(PressingStage pressingStage) {
        StringBuilder dataToHash = new StringBuilder();
        if (pressingStage.getEmployee() != null) { dataToHash.append(pressingStage.getEmployee()); }
        if (pressingStage.getPressType() != null) { dataToHash.append(pressingStage.getPressType().getPressType()); }
        if (pressingStage.getPressure() != null) { dataToHash.append(pressingStage.getPressure().getPressPressure()); }
        if (pressingStage.getDuration() != null) { dataToHash.append(pressingStage.getDuration().getDuration()); }
        if (pressingStage.getPomadeWeight() != null) { dataToHash.append(pressingStage.getPomadeWeight().getPomadeWeight()); }
        if (pressingStage.getYield() != null) { dataToHash.append(pressingStage.getYield().getYield()); }
        if (pressingStage.getMustUsage() != null) { dataToHash.append(pressingStage.getMustUsage().getMustUsage()); }
        if (pressingStage.getComment() != null) { dataToHash.append(pressingStage.getComment()); }
        return dataToHash;
    }
}
