package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.entities.ProcessStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.batch.CurrentStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.bottlingstage.*;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.Employee;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.EndDate;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.StartDate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "bottling_stage")
public class BottlingStage extends ProcessStage {
    @Embedded
    @Column(name = "bottling_line")
    private BottlingLine bottlingLine; // Línea de embotellado

    @Embedded
    @Column(name = "filled_bottles")
    private FilledBottles filledBottles; // Botellas llenadas

    @Embedded
    @Column(name = "bottle_volume")
    private BottleVolume bottleVolume; // Volumen de botella (ml)

    @Embedded
    @Column(name = "total_volume")
    private TotalVolume totalVolume; // Volumen total

    @Embedded
    @Column(name = "sealing_type")
    private SealingType sealingType; // Tipo de sellado

    @Embedded
    @Column(name = "vinification_code")
    private VineyardCodeBottling vineyardCode; // Código de viñeado (Código interno del lote)

    @Embedded
    @Column(name = "bottling_temperature")
    private BottlingTemperature temperature; // Temperatura

    @Column(name = "filtered_before_bottling")
    private Boolean filteredBeforeBottling; // Filtrado antes del embotellado

    @Column(name = "labels_at_this_stage")
    private Boolean labelsAtThisStage; // Etiquetas en esta etapa

    @Column(name = "capsule_or_seal_application")
    private Boolean capsuleOrSealApplication; // Aplicación de cápsulas o precintos

    @Enumerated(EnumType.STRING)
    @Column(name = "current_stage", nullable = false)
    private CurrentStage currentStage = CurrentStage.BOTTLING;

    public BottlingStage () { }

    public BottlingStage(Long batchId, Employee employee, StartDate startDate, EndDate endDate) {
        this.setBatchId(batchId);
        this.setEmployee(employee);
        this.setStartDate(startDate);
        this.setEndDate(endDate);
    }
}
