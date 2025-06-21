package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.entities.ProcessStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.batch.CurrentStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.clarificationstage.*;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.Employee;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.EndDate;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.StartDate;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "clarification_stage")
public class ClarificationStage extends ProcessStage {
    @Embedded
    @Column(name = "method_used")
    private ClarificationMethod methodUsed; // Método usado

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "turbidity", column = @Column(name = "initial_turbidity")),
    })
    private ClarificationTurbidity initialTurbidity; // Turbidez antes (NTU)

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "turbidity", column = @Column(name = "final_turbidity")),
    })
    private ClarificationTurbidity finalTurbidity; // Turbidez final (NTU)

    @Embedded
    @Column(name = "volume")
    private ClarificationVolume volume; // Volumen (litros)

    @Embedded
    @Column(name = "temperature")
    private ClarificationTemperature temperature; // Temperatura

    @Embedded
    @Column(name = "duration")
    private DurationClarification duration; // Duración (horas)

    @ElementCollection
    @CollectionTable(name = "clarification_agents", joinColumns = @JoinColumn(name = "clarification_id"))
    @MapKeyColumn(name = "agent_name") // Nombre del agente clarificante
    @Column(name = "dose") // Dosis en g/hL
    private Map<String, Double> clarifyingAgents; // Agentes clarificantes y sus dosis

    @Enumerated(EnumType.STRING)
    @Column(name = "current_stage", nullable = false)
    private CurrentStage currentStage = CurrentStage.CLARIFICATION;

    public ClarificationStage () { }

    public ClarificationStage(Long batchId, Employee employee, StartDate startDate, EndDate endDate) {
        this.setBatchId(batchId);
        this.setEmployee(employee);
        this.setStartDate(startDate);
        this.setEndDate(endDate);
    }
}
