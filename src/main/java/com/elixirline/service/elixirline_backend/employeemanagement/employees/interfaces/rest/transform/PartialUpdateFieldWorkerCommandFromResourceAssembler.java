package com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.transform;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.commands.UpdateFieldWorkerPartialCommand;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.valueobjects.*;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.resources.PartialUpdateFieldWorkerResource;

public class PartialUpdateFieldWorkerCommandFromResourceAssembler {
    public static UpdateFieldWorkerPartialCommand toCommandFromResource(Long fieldWorkerId, PartialUpdateFieldWorkerResource resource) {
        return new UpdateFieldWorkerPartialCommand(
                fieldWorkerId,
                new Name(resource.name()),
                new Lastname(resource.lastname()),
                new PhoneNumber(resource.phoneNumber()),
                new ProfilePicture(resource.profilePicture())
        );
    }
}
