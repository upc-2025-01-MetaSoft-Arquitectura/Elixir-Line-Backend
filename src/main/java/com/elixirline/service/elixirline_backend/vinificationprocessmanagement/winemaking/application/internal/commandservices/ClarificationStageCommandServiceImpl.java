package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.application.internal.commandservices;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.exceptions.batch.BatchNotFoundException;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.Batch;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.ClarificationStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.CorrectionStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.clarificationstage.CreateClarificationStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.clarificationstage.CreateEmptyClarificationStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.clarificationstage.DeleteClarificationStageByBatchCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.clarificationstage.UpdateClarificationStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.batch.CurrentStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.CompletionStatus;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.EndDate;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.StartDate;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.clarificationstage.ClarificationStageCommandService;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.infrastructure.persistance.jpa.repositories.BatchRepository;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.infrastructure.persistance.jpa.repositories.ClarificationStageRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClarificationStageCommandServiceImpl implements ClarificationStageCommandService {
    private final ClarificationStageRepository clarificationStageRepository;
    private final BatchRepository batchRepository;

    @Override
    public Optional<ClarificationStage> handle(CreateClarificationStageCommand command) {
        if (!validateDates(command.startDate(), command.endDate())) {
            throw new IllegalArgumentException("Start date must be before end date");
        }

        ClarificationStage clarificationStage = new ClarificationStage(
                command.batchId(),
                command.employee(),
                command.startDate(),
                command.endDate()
        );

        clarificationStage.setMethodUsed(command.methodUsed());
        clarificationStage.setInitialTurbidity(command.initialTurbidity());
        clarificationStage.setFinalTurbidity(command.finalTurbidity());
        clarificationStage.setVolume(command.volume());
        clarificationStage.setTemperature(command.temperature());
        clarificationStage.setDuration(command.duration());
        clarificationStage.setClarifyingAgents(command.clarifyingAgents());
        clarificationStage.setComment(command.comment());

        return Optional.of(clarificationStageRepository.save(clarificationStage));
    }

    @Override
    public Optional<ClarificationStage> handle(CreateEmptyClarificationStageCommand command) {
        ClarificationStage clarificationStage = new ClarificationStage();
        clarificationStage.setBatchId(command.batchId());
        return Optional.of(clarificationStageRepository.save(clarificationStage));
    }

    @Override
    public Optional<ClarificationStage> update(UpdateClarificationStageCommand command) {
        return clarificationStageRepository.findByBatchId(command.batchId())
                .map(clarificationStage -> {
                    command.employee().ifPresent(clarificationStage::setEmployee);
                    command.startDate().ifPresent(clarificationStage::setStartDate);
                    command.endDate().ifPresent(clarificationStage::setEndDate);
                    command.methodUsed().ifPresent(clarificationStage::setMethodUsed);
                    command.initialTurbidity().ifPresent(clarificationStage::setInitialTurbidity);
                    command.finalTurbidity().ifPresent(clarificationStage::setFinalTurbidity);
                    command.volume().ifPresent(clarificationStage::setVolume);
                    command.temperature().ifPresent(clarificationStage::setTemperature);
                    command.duration().ifPresent(clarificationStage::setDuration);
                    command.clarifyingAgents().ifPresent(clarificationStage::setClarifyingAgents);
                    command.comment().ifPresent(clarificationStage::setComment);
                    command.completionStatus().ifPresent(clarificationStage::setCompletionStatus);

                    if (clarificationStage.getCompletionStatus() == CompletionStatus.COMPLETED) {
                        clarificationStage.completePhase();

                        Batch batch = batchRepository.findById(clarificationStage.getBatchId())
                                .orElseThrow(() -> new BatchNotFoundException(clarificationStage.getBatchId()));
                        batch.advanceToNextPhase();
                        batch.setCurrentStage(CurrentStage.AGING);

                        //Calcular el Hash de la etapa
                        String dataHash = calculateHash(clarificationStage);
                        clarificationStage.setDataHash(dataHash);
                        batchRepository.save(batch);
                    }

                    return clarificationStageRepository.save(clarificationStage);
                });
    }

    @Override
    public void delete(DeleteClarificationStageByBatchCommand command) {
        clarificationStageRepository.deleteByBatchId(command.batchId());
    }

    public boolean validateDates(StartDate startDate, EndDate endDate) {
        if (startDate != null && endDate != null) {
            return !startDate.getStartDate().isAfter(endDate.getEndDate());
        }
        return false;
    }


    private String calculateHash(ClarificationStage clarificationStage) {
        try {
            StringBuilder dataToHash = getStringBuilder(clarificationStage);

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
    private static StringBuilder getStringBuilder(ClarificationStage clarificationStage) {
        StringBuilder dataToHash = new StringBuilder();
        if (clarificationStage.getEmployee() != null) { dataToHash.append(clarificationStage.getEmployee()); }
        if (clarificationStage.getMethodUsed() != null) { dataToHash.append(clarificationStage.getMethodUsed()); }
        if (clarificationStage.getInitialTurbidity() != null) { dataToHash.append(clarificationStage.getInitialTurbidity()); }
        if (clarificationStage.getFinalTurbidity() != null) { dataToHash.append(clarificationStage.getFinalTurbidity()); }
        if (clarificationStage.getVolume() != null) { dataToHash.append(clarificationStage.getVolume()); }
        if (clarificationStage.getTemperature() != null) { dataToHash.append(clarificationStage.getTemperature()); }
        if (clarificationStage.getDuration() != null) { dataToHash.append(clarificationStage.getDuration()); }
        if (clarificationStage.getClarifyingAgents() != null) { dataToHash.append(clarificationStage.getClarifyingAgents()); }
        if (clarificationStage.getComment() != null) { dataToHash.append(clarificationStage.getComment()); }
        return dataToHash;
    }
}
