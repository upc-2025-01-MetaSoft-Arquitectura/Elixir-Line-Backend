package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.aggregates;

import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.valueobjetcs.TaskStatus;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.valueobjetcs.TaskType;
import com.elixirline.service.elixirline_backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Tasks extends AuditableAbstractAggregateRoot<Tasks> {
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long winegrowerId;
    private String fieldWorkerName;
    private Long fieldWorkerId;
    private Long batchId;

    @ElementCollection
    private List<Long> suppliesIds;

    private Integer progressPercentage;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Enumerated(EnumType.STRING)
    private TaskType type;

    public Tasks(
            String title,
            String description,
            LocalDate startDate,
            LocalDate endDate,
            Long winegrowerId,
            String fieldWorkerName,
            Long fieldWorkerId,
            Long batchId,
            List<Long> suppliesIds,
            Integer progressPercentage,
            TaskStatus status,
            TaskType type
    ) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.winegrowerId = winegrowerId;
        this.fieldWorkerName = fieldWorkerName;
        this.fieldWorkerId = fieldWorkerId;
        this.batchId = batchId;
        this.suppliesIds = suppliesIds;
        this.progressPercentage = progressPercentage;
        this.status = status;
        this.type = type;
    }

    public Tasks updateInformation(
            String title,
            String description,
            LocalDate startDate,
            LocalDate endDate,
            Long winegrowerId,
            String fieldWorkerName,
            Long fieldWorkerId,
            Long batchId,
            List<Long> suppliesIds,
            TaskType type
    ) {
        if (startDate != null && endDate != null && startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date cannot be after end date");
        }

        this.title = title != null ? title : this.title;
        this.description = description != null ? description : this.description;
        this.startDate = startDate != null ? startDate : this.startDate;
        this.endDate = endDate != null ? endDate : this.endDate;
        this.winegrowerId = winegrowerId != null ? winegrowerId : this.winegrowerId;
        this.fieldWorkerName = fieldWorkerName != null ? fieldWorkerName : this.fieldWorkerName;
        this.fieldWorkerId = fieldWorkerId != null ? fieldWorkerId : this.fieldWorkerId;
        this.batchId = batchId != null ? batchId : this.batchId;
        this.suppliesIds = suppliesIds != null ? suppliesIds : this.suppliesIds;
        this.type = type != null ? type : this.type;

        return this;
    }
}
