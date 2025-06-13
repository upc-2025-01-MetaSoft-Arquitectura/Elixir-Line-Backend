package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates;

import com.elixirline.service.elixirline_backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "batch_vineyard")
@NoArgsConstructor
public class BatchVineyard extends AuditableAbstractAggregateRoot<BatchVineyard> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "batch_id", unique = true)
    private Long batchId;

    @Column(name = "vineyard_id", unique = true)
    private String vineyardCode;

    @Embedded
    @NotNull(message = "Reception date is required")
    private ReceptionDate receptionDate;

    @Embedded
    @NotNull(message = "Harvest campaign is required")
    private HarvestCampaign harvestCampaign;

    @Embedded
    @NotNull(message = "Vineyard origin is required")
    private VineyardOrigin vineyardOrigin;

    @Embedded
    @NotNull(message = "Grape variety is required")
    private GrapeVariety grapeVariety;

    @Embedded
    @NotNull(message = "Initial grape quantity is required")
    private InitialGrapeQuantityKg initialGrapeQuantityKg;

    @Embedded
    @NotNull(message = "Created by is required")
    private CreatedBy createdBy;

    @Embedded
    @NotNull(message = "Progress is required")
    private Progress progress = Progress.ZERO;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private BatchStatus status = BatchStatus.NOT_STARTED;

    @Enumerated(EnumType.STRING)
    @Column(name = "current_stage", nullable = false)
    private CurrentStage currentStage = CurrentStage.RECEPTION;

    public BatchVineyard(String vineyardCode, VineyardOrigin vineyardOrigin, GrapeVariety grapeVariety,
                         HarvestCampaign harvestCampaign, ReceptionDate receptionDate,
                         InitialGrapeQuantityKg initialGrapeQuantityKg, CreatedBy createdBy) {
        this.vineyardCode = vineyardCode;
        this.vineyardOrigin = vineyardOrigin;
        this.grapeVariety = grapeVariety;
        this.harvestCampaign = harvestCampaign;
        this.receptionDate = receptionDate;
        this.initialGrapeQuantityKg = initialGrapeQuantityKg;
        this.createdBy = createdBy;
    }

    public void advanceToNextPhase() {
        this.progress = this.progress.nextPhase();
    }
}

