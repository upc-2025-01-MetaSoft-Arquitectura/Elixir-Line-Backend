package com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.services.fieldworker;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.aggregates.FieldWorker;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.commands.*;

import java.util.Optional;

public interface FieldWorkerCommandService {
    Optional<FieldWorker> handle(CreateFieldWorkerCommand command);
    Optional<FieldWorker> update(UpdateFieldWorkerCommand command);
    Optional<FieldWorker> updatePartial(UpdateFieldWorkerPartialCommand command);
    void logicallyDelete(DeleteFieldWorkerCommand command);
    void physicallyDelete(DeleteFieldWorkerCommand command);
    Optional<FieldWorker> activate(ActivateFieldWorkerCommand command);
}
