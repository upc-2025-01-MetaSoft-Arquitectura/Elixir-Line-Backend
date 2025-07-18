package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.entities;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class ProcessStage {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE) //TABLE_PER_CLASS
    private Long id;

    @Column(name = "batch_id", unique = true)
    private Long batchId;

    @Embedded
    @Column(name = "employee_name")
    private Employee employee; // Encargado de registro

    @Embedded
    @Column(name = "start_date")
    private StartDate startDate;

    @Embedded
    @Column(name = "end_date")
    private EndDate endDate;

    @Embedded
    @AttributeOverride(name = "comment", column = @Column(name = "stage_comment"))
    private Comment comment;

    @Enumerated(EnumType.STRING)
    @Column(name = "completion_status", nullable = false)
    private CompletionStatus completionStatus = CompletionStatus.NOT_COMPLETED; // Estado por defecto no completado

    @Column(name = "completed_at")
    private java.time.LocalDateTime completedAt = null; // Fecha y hora de finalización de la etapa

    @Column(name = "data_hash")
    private String dataHash = null; // Hash de los datos de la etapa para pasarlos al Smart Contract

    public void completePhase() {
        this.completedAt = java.time.LocalDateTime.now();
    }
}

