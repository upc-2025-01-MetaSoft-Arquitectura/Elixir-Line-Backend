package com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.services.fieldworker;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.aggregates.FieldWorker;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.commands.CreateFieldWorkerCommand;

public interface FieldWorkerCommandService {
    FieldWorker handle(CreateFieldWorkerCommand command);
}
