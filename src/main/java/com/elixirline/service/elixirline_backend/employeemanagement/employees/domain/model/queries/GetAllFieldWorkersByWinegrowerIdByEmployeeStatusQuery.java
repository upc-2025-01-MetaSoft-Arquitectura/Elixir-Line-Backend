package com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.queries;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.valueobjects.EmployeeStatus;

public record GetAllFieldWorkersByWinegrowerIdByEmployeeStatusQuery(Long vinegrowerId, EmployeeStatus employeeStatus) {
}
