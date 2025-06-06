package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates;

import com.elixirline.service.elixirline_backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.commands.CreateWineBatchCommand;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.entities.WineMakingStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name = "wine_batch")
@NoArgsConstructor
public class WineBatch extends AuditableAbstractAggregateRoot<WineBatch> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wine_batch_id", unique = true)
    private Long wineBatchId;

    @Embedded
    @Column(name = "internal_code", unique = true)
    @NotNull(message = "Internal code is required")
    private InternalCode internalCode;

    @Embedded
    @NotNull(message = "Reception date is required")
    private LocalDateTime receptionDate;

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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BatchStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "current_stage", nullable = false)
    private StageType currentStage;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "wineBatch"
    )
    private List<WineMakingStage> winemakingStages = new ArrayList<>();

    public WineBatch(
            InternalCode internalCode,
            LocalDateTime receptionDate,
            HarvestCampaign harvestCampaign,
            VineyardOrigin vineyardOrigin,
            GrapeVariety grapeVariety,
            InitialGrapeQuantityKg initialGrapeQuantityKg,
            CreatedBy createdBy
    ) {
        this.internalCode = internalCode;
        this.receptionDate = receptionDate;
        this.harvestCampaign = harvestCampaign;
        this.vineyardOrigin = vineyardOrigin;
        this.grapeVariety = grapeVariety;
        this.initialGrapeQuantityKg = initialGrapeQuantityKg;
        this.createdBy = createdBy;
        this.status = BatchStatus.RECEIVED;
        this.currentStage = StageType.RECEPTION;
    }

    public static WineBatch fromCommand(CreateWineBatchCommand command) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate receptionLocalDate = LocalDate.parse(command.receptionDate(), formatter);

        return new WineBatch(
                new InternalCode(command.internalCode()),
                receptionLocalDate.atStartOfDay(),
                new HarvestCampaign(command.campaign()),
                new VineyardOrigin(command.vineyard()),
                new GrapeVariety(command.grapeVariety()),
                new InitialGrapeQuantityKg(command.initialGrapeQuantityKg()),
                new CreatedBy(command.createdBy())
        );
    }


    public void complete() {
        this.status = BatchStatus.COMPLETED;
        this.currentStage = StageType.BOTTLING;
    }
}
