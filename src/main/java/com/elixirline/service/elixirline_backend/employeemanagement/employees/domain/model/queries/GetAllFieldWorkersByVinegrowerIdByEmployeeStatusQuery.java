package com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.queries;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.valueobjects.EmployeeStatus;

public record GetAllFieldWorkersByVinegrowerIdByEmployeeStatusQuery(Long vinegrowerId, EmployeeStatus employeeStatus) {
}
