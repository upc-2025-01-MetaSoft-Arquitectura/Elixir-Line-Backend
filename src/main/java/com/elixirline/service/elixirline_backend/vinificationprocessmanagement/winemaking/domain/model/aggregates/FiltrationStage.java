package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.entities.ProcessStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.batch.CurrentStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.Employee;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.EndDate;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.StartDate;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.filtrationstage.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "filtration_stage")
public class FiltrationStage extends ProcessStage {
    @Embedded
    @Column(name = "filter_type")
    private FilterType filterType; // Tipo de filtración

    @Embedded
    @Column(name = "filter_medium")
    private FilterMedium filterMedium; // Medio filtrante

    @Embedded
    @Column(name = "porosity")
    private Porosity porosity; // Porosidad (micrones)

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "filtrationTurbidity", column = @Column(name = "initial_turbidity"))
    })
    private FiltrationTurbidity initialTurbidity; // Nivel de turbidez antes

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "filtrationTurbidity", column = @Column(name = "final_turbidity"))
    })
    private FiltrationTurbidity finalTurbidity; // Nivel de turbidez después

    @Embedded
    @Column(name = "temperature")
    private FiltrationTemperature temperature; // Temperatura

    @Embedded
    @Column(name = "pressure")
    private Pressure pressure; // Presión (Bares)

    @Embedded
    @Column(name = "filtered_volume")
    private FiltrationVolume filteredVolume; // Volumen Filtrado (litros)

    @Column(name = "sterile_filtration")
    private Boolean sterileFiltration; // Filtración estéril

    @Column(name = "changed_filtration")
    private Boolean changedFiltration; // Filtración cambiado

    @Embedded
    @Column(name = "change_reason")
    private ChangeReason changeReason; // Motivo del cambio

    @Enumerated(EnumType.STRING)
    @Column(name = "current_stage", nullable = false)
    private CurrentStage currentStage = CurrentStage.FILTRATION;

    public FiltrationStage() { }

    public FiltrationStage(Long batchId, Employee employee, StartDate startDate, EndDate endDate) {
        this.setBatchId(batchId);
        this.setEmployee(employee);
        this.setStartDate(startDate);
        this.setEndDate(endDate);
    }
}

