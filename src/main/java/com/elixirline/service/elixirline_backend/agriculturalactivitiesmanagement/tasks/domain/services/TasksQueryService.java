package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.services;

import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.aggregates.Tasks;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.queries.GetFieldTasksQuery;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.queries.GetIndustrialTasksQuery;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.queries.GetTaskByIdQuery;

import java.util.List;
import java.util.Optional;

public interface TasksQueryService {
    List<Tasks> handle(GetFieldTasksQuery query);
    List<Tasks> handle(GetIndustrialTasksQuery query);
    Optional<Tasks> handle(GetTaskByIdQuery query);
}
