package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.entities.ProcessStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.batch.CurrentStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.Employee;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.EndDate;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.StartDate;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.receptionstage.QuantityKg;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.receptionstage.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "reception_stage")
public class ReceptionStage extends ProcessStage {
    @Embedded
    @Column(name = "sugar_level")
    private ReceptionSugarLevel sugarLevel; // Nivel de azucar Brix

    @Embedded
    @Column(name = "ph_level")
    private ReceptionPHLevel pHLevel;

    @Embedded
    @Column(name = "temperature")
    private ReceptionTemperature temperature;

    @Embedded
    @Column(name = "quantity_kg")
    private QuantityKg quantityKg; // Cantidad de kg de uva

    @Enumerated(EnumType.STRING)
    @Column(name = "current_stage", nullable = false)
    private CurrentStage currentStage = CurrentStage.RECEPTION;

    public ReceptionStage(Long batchId, Employee employee, StartDate startDate, EndDate endDate) {
        this.setBatchId(batchId);
        this.setEmployee(employee);
        this.setStartDate(startDate);
        this.setEndDate(endDate);
    }
}
