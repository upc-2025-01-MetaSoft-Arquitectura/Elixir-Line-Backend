package com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.services.fieldworker;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.aggregates.FieldWorker;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.queries.GetAllFieldWorkersQuery;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.queries.GetFieldWorkerByIdQuery;

import java.util.List;
import java.util.Optional;

public interface FieldWorkerQueryService {
    List<FieldWorker> handle(GetAllFieldWorkersQuery query);
    Optional<FieldWorker> handle(GetFieldWorkerByIdQuery query);
}
