package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.services;

import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.aggregates.Tasks;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.commands.CreateTasksCommand;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.commands.DeleteTaskCommand;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.commands.PatchTaskCommand;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.commands.UpdateTaskCommand;

import java.util.Optional;

public interface TasksCommandService {
    Optional<Tasks> createTask(CreateTasksCommand command);
    Optional<Tasks> handle(UpdateTaskCommand command);
    Optional<Tasks> handle(PatchTaskCommand command);
    void handle(DeleteTaskCommand command);
}
