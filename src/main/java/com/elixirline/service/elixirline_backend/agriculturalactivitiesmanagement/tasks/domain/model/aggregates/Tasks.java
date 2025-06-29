package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.aggregates;

import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.valueobjetcs.TaskType;
import com.elixirline.service.elixirline_backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Tasks extends AuditableAbstractAggregateRoot<Tasks> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;

    private String assignedTo;
    private Long batchId;

    @ElementCollection
    private List<Long> suppliesIds;

    @Enumerated(EnumType.STRING)
    private TaskType type;

    public Tasks(
            String title,
            String description,
            LocalDate startDate,
            LocalDate endDate,
            String assignedTo,
            Long batchId,
            List<Long> suppliesIds,
            TaskType type
    ) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.assignedTo = assignedTo;
        this.batchId = batchId;
        this.suppliesIds = suppliesIds;
        this.type = type;
    }

    public Tasks updateInformation(
            String title,
            String description,
            LocalDate startDate,
            LocalDate endDate,
            String assignedTo,
            Long batchId,
            List<Long> suppliesIds
    ) {
        if (startDate != null && endDate != null && startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date cannot be after end date");
        }

        this.title = title != null ? title : this.title;
        this.description = description != null ? description : this.description;
        this.startDate = startDate != null ? startDate : this.startDate;
        this.endDate = endDate != null ? endDate : this.endDate;
        this.assignedTo = assignedTo != null ? assignedTo : this.assignedTo;
        this.batchId = batchId != null ? batchId : this.batchId;
        this.suppliesIds = suppliesIds != null ? suppliesIds : this.suppliesIds;

        return this;
    }
}
