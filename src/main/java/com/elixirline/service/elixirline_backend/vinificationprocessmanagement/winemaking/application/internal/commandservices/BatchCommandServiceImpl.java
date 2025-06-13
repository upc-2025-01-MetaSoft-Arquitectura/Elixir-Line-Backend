package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.application.internal.commandservices;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.exceptions.BatchNotBeCreated;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.exceptions.BatchNotFoundException;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.BatchVineyard;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.CreateBatchCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.DeleteBatchCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.PatchBatchCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.UpdateBatchCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.services.BatchCommandService;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.infrastructure.persistance.jpa.repositories.BatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BatchCommandServiceImpl implements BatchCommandService {
    private final BatchRepository batchRepository;

    @Transactional
    @Override
    public Optional<BatchVineyard> handle(CreateBatchCommand command) {
        BatchVineyard batch = new BatchVineyard(
                command.vineyardCode(),
                command.vineyardOrigin(),
                command.grapeVariety(),
                command.harvestCampaign(),
                command.receptionDate(),
                command.initialGrapeQuantityKg(),
                command.createdBy()
        );

        try {
            return Optional.of(batchRepository.save(batch));
        } catch (Exception e) {
            throw new BatchNotBeCreated(e.getMessage());
        }
    }

    @Transactional
    @Override
    public Optional<BatchVineyard> update(UpdateBatchCommand command) {
        return Optional.ofNullable(batchRepository.findById(command.batchId()).map(batch -> {
            batch.setVineyardCode(command.vineyardCode());
            batch.setVineyardOrigin(command.vineyardOrigin());
            batch.setGrapeVariety(command.grapeVariety());
            batch.setHarvestCampaign(command.harvestCampaign());
            batch.setReceptionDate(command.receptionDate());
            batch.setInitialGrapeQuantityKg(command.initialGrapeQuantityKg());
            return batchRepository.save(batch);
        }).orElseThrow(() -> new BatchNotFoundException(command.batchId())));
    }

    @Override
    public void patch(PatchBatchCommand command) {
        batchRepository.findById(command.batchId()).ifPresent(batch -> {
            if (command.progress() != null) {
                batch.setProgress(command.progress());
            }
            if (command.status() != null) {
                batch.setStatus(command.status());
            }
            if (command.currentStage() != null) {
                batch.setCurrentStage(command.currentStage());
            }
            batchRepository.save(batch);
        });
    }

    @Transactional
    @Override
    public void delete(DeleteBatchCommand command) {
        batchRepository.deleteById(command.batchId());
    }
}
