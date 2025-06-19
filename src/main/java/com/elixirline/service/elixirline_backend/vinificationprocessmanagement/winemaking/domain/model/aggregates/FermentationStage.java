package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.entities.ProcessStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.batch.CurrentStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.Employee;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.EndDate;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.StartDate;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.fermentationstage.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "fermentation_stage")
public class FermentationStage extends ProcessStage {
    @Embedded
    @Column(name = "yeast_used")
    private Yeast yeastUsed; // Levadura utilizada (mg/L)

    @Embedded
    @Column(name = "fermentation_type")
    private TypeFermentation fermentationType; // Tipo de fermentación

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "fermentationSugarLevel", column = @Column(name = "initial_sugar_level"))
    })
    private FermentationSugarLevel initialSugarLevel; // Nivel de Azúcar inicial (°brix)

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "fermentationSugarLevel", column = @Column(name = "final_sugar_level"))
    })
    private FermentationSugarLevel finalSugarLevel; // Nivel de Azúcar final (°brix)

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "fermentationPHLevel", column = @Column(name = "initial_ph"))
    })
    private FermentationPHLevel initialPH; // pH inicial

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "fermentationPHLevel", column = @Column(name = "final_ph"))
    })
    private FermentationPHLevel finalPH; // pH final

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "fermentationTemperature", column = @Column(name = "max_temperature"))
    })
    private FermentationTemperature maxTemperature; // Temperatura máxima

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "fermentationTemperature", column = @Column(name = "min_temperature"))
    })
    private FermentationTemperature minTemperature; // Temperatura mínima

    @Embedded
    @Column(name = "tank_code")
    private TankCode tankCode; // Código del tanque

    @Enumerated(EnumType.STRING)
    @Column(name = "current_stage", nullable = false)
    private CurrentStage currentStage = CurrentStage.FERMENTATION;

    public FermentationStage(Long batchId, Employee employee, StartDate startDate, EndDate endDate) {
        this.setBatchId(batchId);
        this.setEmployee(employee);
        this.setStartDate(startDate);
        this.setEndDate(endDate);
    }
}
