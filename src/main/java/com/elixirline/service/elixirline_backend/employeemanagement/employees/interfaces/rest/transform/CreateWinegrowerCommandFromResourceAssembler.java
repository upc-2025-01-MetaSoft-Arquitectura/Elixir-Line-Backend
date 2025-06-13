package com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.transform;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.commands.CreateWinegrowerCommand;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.resources.CreateWinegrowerResource;

public class CreateWinegrowerCommandFromResourceAssembler {
    public static CreateWinegrowerCommand toCommandFromResource(CreateWinegrowerResource resource) {
        return new CreateWinegrowerCommand(
                resource.userId(),
                resource.name(),
                resource.lastname(),
                resource.country(),
                resource.phoneNumber()
        );
    }
}
