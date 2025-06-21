package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.entities.ProcessStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.batch.CurrentStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.Employee;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.EndDate;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.StartDate;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.pressingstage.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pressing_stage")
public class PressingStage extends ProcessStage {
    @Embedded
    @Column(name = "press_type")
    private PressType pressType; // Tipo de prensa

    @Embedded
    @Column(name = "press_temperature")
    private PressPressure pressure; // Presión de la prensa (Bares)

    @Embedded
    @Column(name = "press_duration")
    private PressingDuration duration; // Duración (minutos)

    @Embedded
    @Column(name = "pomade_weight")
    private PomadeWeight pomadeWeight; // Peso de orujo

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "grape_yield"))
    })
    private Yield yield; // Rendimiento (litros)

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "must_usage_value"))
    })
    private MustUsage mustUsage; // Uso del mosto

    @Enumerated(EnumType.STRING)
    @Column(name = "current_stage", nullable = false)
    private CurrentStage currentStage = CurrentStage.PRESSING;

    public PressingStage() { }

    public PressingStage(Long batchId, Employee employee, StartDate startDate, EndDate endDate) {
        this.setBatchId(batchId);
        this.setEmployee(employee);
        this.setStartDate(startDate);
        this.setEndDate(endDate);
    }
}
