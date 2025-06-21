package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.application.internal.commandservices;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.FiltrationStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.filtrationstage.CreateFiltrationStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.filtrationstage.DeleteFiltrationStageByBatchCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.filtrationstage.UpdateFiltrationStageCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.CompletionStatus;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.EndDate;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.StartDate;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.filtrationstage.FiltrationStageCommandService;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.infrastructure.persistance.jpa.repositories.FiltrationStageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FiltrationStageCommandServiceImpl implements FiltrationStageCommandService {
    private final FiltrationStageRepository filtrationStageRepository;

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
}
