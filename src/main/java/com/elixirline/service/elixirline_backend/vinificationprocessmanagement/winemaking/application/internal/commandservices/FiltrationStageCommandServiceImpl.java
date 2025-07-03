package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.application.internal.commandservices;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.exceptions.batch.BatchNotFoundException;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.Batch;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.FiltrationStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.PressingStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.filtrationstage.CreateEmptyFiltrationStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.filtrationstage.CreateFiltrationStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.filtrationstage.DeleteFiltrationStageByBatchCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.filtrationstage.UpdateFiltrationStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.batch.CurrentStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.CompletionStatus;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.EndDate;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.StartDate;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.filtrationstage.FiltrationStageCommandService;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.infrastructure.persistance.jpa.repositories.BatchRepository;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.infrastructure.persistance.jpa.repositories.FiltrationStageRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FiltrationStageCommandServiceImpl implements FiltrationStageCommandService {
    private final FiltrationStageRepository filtrationStageRepository;
    private final BatchRepository batchRepository;

    @Override
    public Optional<FiltrationStage> handle(CreateFiltrationStageCommand command) {
        if (!validateDates(command.startDate(), command.endDate())) {
            throw new IllegalArgumentException("Start date must be before end date");
        }

        FiltrationStage filtrationStage = new FiltrationStage(
                command.batchId(),
                command.employee(),
                command.startDate(),
                command.endDate()
        );

        filtrationStage.setFilterType(command.filterType());
        filtrationStage.setFilterMedium(command.filterMedium());
        filtrationStage.setPorosity(command.porosity());
        filtrationStage.setInitialTurbidity(command.initialTurbidity());
        filtrationStage.setFinalTurbidity(command.finalTurbidity());
        filtrationStage.setTemperature(command.temperature());
        filtrationStage.setPressure(command.pressure());
        filtrationStage.setFilteredVolume(command.filteredVolume());
        filtrationStage.setSterileFiltration(command.sterileFiltration());
        filtrationStage.setChangedFiltration(command.changedFiltration());
        filtrationStage.setChangeReason(command.changeReason());
        filtrationStage.setComment(command.comment());

        return Optional.of(filtrationStageRepository.save(filtrationStage));
    }

    @Override
    public Optional<FiltrationStage> handle(CreateEmptyFiltrationStageCommand command) {
        FiltrationStage filtrationStage = new FiltrationStage();
        filtrationStage.setBatchId(command.batchId());
        return Optional.of(filtrationStageRepository.save(filtrationStage));
    }

    @Override
    public Optional<FiltrationStage> update(UpdateFiltrationStageCommand command) {
        return filtrationStageRepository.findByBatchId(command.batchId())
                .map(filtrationStage -> {
                    command.employee().ifPresent(filtrationStage::setEmployee);
                    command.startDate().ifPresent(filtrationStage::setStartDate);
                    command.endDate().ifPresent(filtrationStage::setEndDate);
                    command.filterType().ifPresent(filtrationStage::setFilterType);
                    command.filterMedium().ifPresent(filtrationStage::setFilterMedium);
                    command.porosity().ifPresent(filtrationStage::setPorosity);
                    command.initialTurbidity().ifPresent(filtrationStage::setInitialTurbidity);
                    command.finalTurbidity().ifPresent(filtrationStage::setFinalTurbidity);
                    command.temperature().ifPresent(filtrationStage::setTemperature);
                    command.pressure().ifPresent(filtrationStage::setPressure);
                    command.filteredVolume().ifPresent(filtrationStage::setFilteredVolume);
                    command.sterileFiltration().ifPresent(filtrationStage::setSterileFiltration);
                    command.changedFiltration().ifPresent(filtrationStage::setChangedFiltration);
                    command.changeReason().ifPresent(filtrationStage::setChangeReason);
                    command.comment().ifPresent(filtrationStage::setComment);
                    command.completionStatus().ifPresent(filtrationStage::setCompletionStatus);

                    if (filtrationStage.getCompletionStatus() == CompletionStatus.COMPLETED) {
                        filtrationStage.completePhase();

                        Batch batch = batchRepository.findById(filtrationStage.getBatchId())
                                .orElseThrow(() -> new BatchNotFoundException(filtrationStage.getBatchId()));
                        batch.advanceToNextPhase();
                        batch.setCurrentStage(CurrentStage.BOTTLING);

                        //Calcular el Hash de la etapa
                        String dataHash = calculateHash(filtrationStage);
                        filtrationStage.setDataHash(dataHash);
                        batchRepository.save(batch);
                    }

                    return filtrationStageRepository.save(filtrationStage);
                });
    }

    @Override
    public void delete(DeleteFiltrationStageByBatchCommand command) {
        filtrationStageRepository.deleteByBatchId(command.batchId());
    }

    public boolean validateDates(StartDate startDate, EndDate endDate) {
        if (startDate != null && endDate != null) {
            return !startDate.getStartDate().isAfter(endDate.getEndDate());
        }
        return false;
    }

    private String calculateHash(FiltrationStage filtrationStage) {
        try {
            StringBuilder dataToHash = getStringBuilder(filtrationStage);

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
    private static StringBuilder getStringBuilder(FiltrationStage filtrationStage) {
        StringBuilder dataToHash = new StringBuilder();
        if (filtrationStage.getEmployee() != null) { dataToHash.append(filtrationStage.getEmployee()); }
        if (filtrationStage.getFilterType() != null) { dataToHash.append(filtrationStage.getFilterType()); }
        if (filtrationStage.getFilterMedium() != null) { dataToHash.append(filtrationStage.getFilterMedium()); }
        if (filtrationStage.getPorosity() != null) { dataToHash.append(filtrationStage.getPorosity()); }
        if (filtrationStage.getInitialTurbidity() != null) { dataToHash.append(filtrationStage.getInitialTurbidity()); }
        if (filtrationStage.getFinalTurbidity() != null) { dataToHash.append(filtrationStage.getFinalTurbidity()); }
        if (filtrationStage.getTemperature() != null) { dataToHash.append(filtrationStage.getTemperature()); }
        if (filtrationStage.getPressure() != null) { dataToHash.append(filtrationStage.getPressure()); }
        if (filtrationStage.getFilteredVolume() != null) { dataToHash.append(filtrationStage.getFilteredVolume()); }
        if (filtrationStage.getSterileFiltration() != null) { dataToHash.append(filtrationStage.getSterileFiltration()); }
        if (filtrationStage.getChangedFiltration() != null) { dataToHash.append(filtrationStage.getChangedFiltration()); }
        if (filtrationStage.getChangeReason() != null) { dataToHash.append(filtrationStage.getChangeReason()); }
        if (filtrationStage.getComment() != null) { dataToHash.append(filtrationStage.getComment()); }
        return dataToHash;
    }
}
