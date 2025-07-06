package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.queries;

import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.valueobjetcs.TaskType;

public record GetTasksByTypeQuery(Long winegrowerId, TaskType type) { }
