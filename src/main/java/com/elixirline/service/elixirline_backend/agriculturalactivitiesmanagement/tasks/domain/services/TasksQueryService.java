package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.services;

import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.aggregates.Tasks;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.queries.GetAllTasksByWinegrowerIdByFieldWorkerIdQuery;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.queries.GetTaskByIdQuery;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.queries.GetTasksByTypeQuery;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.valueobjetcs.TaskType;

import java.util.List;
import java.util.Optional;

public interface TasksQueryService {
    List<Tasks> handle(GetTasksByTypeQuery query);
    Optional<Tasks> handle(GetTaskByIdQuery query);
    List<Tasks> findByWinegrowerId(Long winegrowerId);
    List<Tasks> findByTypeWithEvidence(Long winegrowerId,TaskType type);
    List<Tasks> findByAllTasksByWinegrowerIdAndFieldWorkerId(GetAllTasksByWinegrowerIdByFieldWorkerIdQuery query);
}
