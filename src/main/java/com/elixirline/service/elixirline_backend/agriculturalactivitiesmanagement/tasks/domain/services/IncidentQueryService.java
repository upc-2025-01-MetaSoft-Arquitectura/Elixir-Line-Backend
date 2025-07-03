package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.services;

import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.entities.Incident;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.queries.GetIncidentByTaskIdQuery;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.valueobjetcs.TaskType;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.resources.IncidentWithTaskInfoResource;

import java.util.List;
import java.util.Optional;

public interface IncidentQueryService {
    //    List<Incident> handle(GetIncidentByTaskIdQuery query);
    List<IncidentWithTaskInfoResource> handleAllWithTaskInfo();
    List<IncidentWithTaskInfoResource> handleByTaskType(TaskType type);
    Optional<Incident> handle(Long incidentId);
}
