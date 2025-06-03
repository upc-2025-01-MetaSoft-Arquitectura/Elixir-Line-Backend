package com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.services.vinegrower;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.aggregates.Vinegrower;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.commands.CreateVinegrowerCommand;

public interface VinegrowerCommandService {
    Vinegrower handle(CreateVinegrowerCommand command);
}
