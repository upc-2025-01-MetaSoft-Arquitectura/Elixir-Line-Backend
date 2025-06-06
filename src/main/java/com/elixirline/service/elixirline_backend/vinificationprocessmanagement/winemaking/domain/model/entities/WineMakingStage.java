package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.entities;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.StageType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "winemaking_stage")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class WineMakingStage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "stage_type", nullable = false)
    private StageType stageType;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "started_at", nullable = false)
    private Date startedAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "completed_at", nullable = true)
    private Date completedAt;

    @Column(name = "completed_by", nullable = true)
    private String completedBy;

    @Column(name = "observations", columnDefinition = "TEXT", nullable = true)
    private String observations;

    public WineMakingStage(StageType stageType, String observations) {
        this.stageType = stageType;
        this.startedAt = new Date();
        this.observations = observations;
        this.completedAt = null;
        this.completedBy = null;
    }

    public boolean isCompleted() {
        return completedAt != null;
    }

    /*Mark the stage as completed*/
    public void complete(Date completedAt) {
        this.completedAt = completedAt;
    }
}


