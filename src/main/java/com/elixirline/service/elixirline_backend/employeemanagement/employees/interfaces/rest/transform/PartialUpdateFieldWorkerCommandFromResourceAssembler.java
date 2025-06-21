package com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.transform;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.commands.UpdateFieldWorkerPartialCommand;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.valueobjects.*;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.resources.PartialUpdateFieldWorkerResource;

public class PartialUpdateFieldWorkerCommandFromResourceAssembler {
<<<<<<< HEAD
<<<<<<< HEAD
    public static UpdateFieldWorkerPartialCommand toCommandFromResource(Long fieldWorkerId, PartialUpdateFieldWorkerResource resource) {
        return new UpdateFieldWorkerPartialCommand(
                fieldWorkerId,
                new Name(resource.name()),
                new Lastname(resource.lastname()),
                new PhoneNumber(resource.phoneNumber()),
                new ProfilePicture(resource.profilePicture())
=======
=======
>>>>>>> feature/winemakingprocess
    public static UpdateFieldWorkerPartialCommand toCommandFromResource(Long fieldWorkerId, PartialUpdateFieldWorkerResource partialUpdateFieldWorkerResource) {
        return new UpdateFieldWorkerPartialCommand(
                fieldWorkerId,
                new Name(partialUpdateFieldWorkerResource.name()),
                new Lastname(partialUpdateFieldWorkerResource.lastname()),
                new PhoneNumber(partialUpdateFieldWorkerResource.phoneNumber()),
                new ProfilePicture(partialUpdateFieldWorkerResource.profilePicture())
<<<<<<< HEAD
>>>>>>> develop
=======
>>>>>>> feature/winemakingprocess
        );
    }
}
