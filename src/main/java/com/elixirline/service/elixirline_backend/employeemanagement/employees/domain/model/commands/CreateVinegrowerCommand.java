package com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.commands;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.valueobjects.Country;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.valueobjects.FullName;

public record CreateVinegrowerCommand (Long userId, FullName fullName, Country country) {
}
