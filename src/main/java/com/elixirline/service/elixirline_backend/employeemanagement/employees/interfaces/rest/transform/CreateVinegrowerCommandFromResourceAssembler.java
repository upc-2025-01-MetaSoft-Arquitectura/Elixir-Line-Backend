package com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.transform;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.commands.CreateVinegrowerCommand;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.valueobjects.Country;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.resources.CreateVinegrowerResource;

public class CreateVinegrowerCommandFromResourceAssembler {
    public static CreateVinegrowerCommand toCommandFromResource(CreateVinegrowerResource resource) {
        return new CreateVinegrowerCommand(
                resource.userId(),
                resource.name(),
                resource.lastname(),
                resource.country(),
                resource.phoneNumber(),
                resource.profilePicture()
        );
    }
}
