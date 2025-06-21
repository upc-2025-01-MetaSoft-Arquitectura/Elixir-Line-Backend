package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.processstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public abstract class ProcessStageResource {
    private Long id;
    private Long batchId;
    private Employee employee;
    private StartDate startDate;
    private EndDate endDate;
    private Comment comment;
    private CompletionStatus completionStatus;
    private LocalDateTime completedAt;
}
