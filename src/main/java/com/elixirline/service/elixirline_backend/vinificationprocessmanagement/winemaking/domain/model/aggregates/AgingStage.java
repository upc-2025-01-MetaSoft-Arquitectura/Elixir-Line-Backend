package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.entities.ProcessStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.agingstate.*;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.batch.CurrentStage;
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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "aging_stage")
public class AgingStage extends ProcessStage {
    @Embedded
    @Column(name = "container_type")
    private ContainerType containerType; // Tipo de contenedor

    @Embedded
    @Column(name = "material")
    private Material material; // Material del contenedor

    @Embedded
    @Column(name = "container_code")
    private ContainerCode containerCode; // Código de contenedor

    @Embedded
    @Column(name = "average_temperature")
    private AverageTemperature averageTemperature; // Temperatura promedio

    @Embedded
    @Column(name = "aging_volume")
    private AgingVolume volume; // Volumen (litros)

    @Embedded
    @Column(name = "aging_duration")
    private AgingDuration duration; // Duración (Meses)

    @Embedded
    @Column(name = "frequency")
    private Frequency frequency; // Frecuencia (días)

    @Embedded
    @Column(name = "batonnage")
    private Batonnage batonnage; // Batonnage (número de veces)

    @Embedded
    @Column(name = "refills")
    private Refills refills; // Recargas

    @Embedded
    @Column(name = "rackings")
    private Racking rackings; // Trasiegos

    @Embedded
    @Column(name = "purpose")
    private Purpose purpose; // Propósito

    @Enumerated(EnumType.STRING)
    @Column(name = "current_stage", nullable = false)
    private CurrentStage currentStage = CurrentStage.AGING;

    public AgingStage(Long batchId, Employee employee, StartDate startDate, EndDate endDate) {
        this.setBatchId(batchId);
        this.setEmployee(employee);
        this.setStartDate(startDate);
        this.setEndDate(endDate);
    }
}
