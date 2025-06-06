package com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.transform;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.commands.UpdateFieldWorkerCommand;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.valueobjects.*;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.resources.UpdateFieldWorkerResource;

public class UpdateFieldWorkerCommandFromResourceAssembler {
    public static UpdateFieldWorkerCommand toCommandFromResource (Long fieldWorkerId, UpdateFieldWorkerResource resource) {
        return new UpdateFieldWorkerCommand(
                fieldWorkerId,
                new Name(resource.name()),
                new Lastname(resource.lastname()),
                new PhoneNumber(resource.phoneNumber()),
                new ProfilePicture(resource.profilePicture()),
                resource.vinegrowerId()
        );
    }
}
