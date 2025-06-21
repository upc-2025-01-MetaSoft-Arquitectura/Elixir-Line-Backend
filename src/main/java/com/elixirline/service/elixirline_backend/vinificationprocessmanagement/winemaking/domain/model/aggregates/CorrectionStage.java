package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.entities.ProcessStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.batch.CurrentStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.*;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.correctionstage.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "correction_stage")
public class CorrectionStage extends ProcessStage {
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "sugarLevel", column = @Column(name = "initial_sugar_level"))
    })
    private CorrectionSugarLevel initialSugarLevel; // Nivel de Azúcar inicial (°brix)

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "sugarLevel", column = @Column(name = "final_sugar_level"))
    })
    private CorrectionSugarLevel finalSugarLevel; // Nivel de Azúcar final (°brix)

    @Embedded
    @Column(name = "added_sugar")
    private QuantitySugarKg addedSugar; // Azúcar añadida (kg)

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "correctionPHLevel", column = @Column(name = "initial_ph"))
    })
    private CorrectionPHLevel initialPH; // pH inicial

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "correctionPHLevel", column = @Column(name = "final_ph"))
    })
    private CorrectionPHLevel finalPH; // pH final

    @Embedded
    @Column(name = "acid_type")
    private AcidType acidType; // Tipo de ácido

    @Embedded
    @Column(name = "added_acid")
    private AddedAcid addedAcid; // Ácido añadido (g/L)

    @Embedded
    @Column(name = "added_sulphites")
    private AddedSulphites addedSulphites; // Sulfitos añadidos (mg/L)

    @ElementCollection
    @Column(name = "added_nutrients")
    private List<String> nutrients; // Nutrientes añadidos

    @Embedded
    @Column(name = "justification")
    private Justification justification;

    @Enumerated(EnumType.STRING)
    @Column(name = "current_stage", nullable = false)
    private CurrentStage currentStage = CurrentStage.CORRECTION;

    public CorrectionStage () { }

    public CorrectionStage(Long batchId, Employee employee, StartDate startDate, EndDate endDate) {
        this.setBatchId(batchId);
        this.setEmployee(employee);
        this.setStartDate(startDate);
        this.setEndDate(endDate);
    }
}
