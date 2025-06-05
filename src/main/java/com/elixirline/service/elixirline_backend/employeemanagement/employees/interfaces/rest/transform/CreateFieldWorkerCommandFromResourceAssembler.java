package com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.transform;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.commands.CreateFieldWorkerCommand;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.resources.CreateFieldWorkerResource;

public class CreateFieldWorkerCommandFromResourceAssembler {
    public static CreateFieldWorkerCommand toCommandFromResource(CreateFieldWorkerResource resource) {
        return new CreateFieldWorkerCommand(
                resource.userId(),
                resource.name(),
                resource.lastname(),
                resource.phoneNumber(),
                resource.profilePicture(),
                resource.vinegrowerId()
        );
    }
}
