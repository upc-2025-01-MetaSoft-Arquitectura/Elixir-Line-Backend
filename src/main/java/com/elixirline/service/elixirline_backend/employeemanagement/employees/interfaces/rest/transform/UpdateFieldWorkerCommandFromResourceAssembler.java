package com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.transform;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.commands.UpdateFieldWorkerCommand;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.valueobjects.*;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.resources.UpdateFieldWorkerResource;
<<<<<<< HEAD
<<<<<<< HEAD

public class UpdateFieldWorkerCommandFromResourceAssembler {
    public static UpdateFieldWorkerCommand toCommandFromResource (Long fieldWorkerId, UpdateFieldWorkerResource resource) {
        return new UpdateFieldWorkerCommand(
                fieldWorkerId,
                new Name(resource.name()),
                new Lastname(resource.lastname()),
                new PhoneNumber(resource.phoneNumber()),
                new ProfilePicture(resource.profilePicture()),
                resource.vinegrowerId()
=======
=======
>>>>>>> feature/winemakingprocess
import org.springframework.web.multipart.MultipartFile;

public class UpdateFieldWorkerCommandFromResourceAssembler {
    public static UpdateFieldWorkerCommand toCommandFromResource (Long fieldWorkerId, UpdateFieldWorkerResource fieldWorkerResource) {
        return new UpdateFieldWorkerCommand(
                fieldWorkerId,
                fieldWorkerResource.name() != null ? new Name(fieldWorkerResource.name()) : null,
                fieldWorkerResource.lastname() != null ? new Lastname(fieldWorkerResource.lastname()) : null,
                fieldWorkerResource.phoneNumber() != null ? new PhoneNumber(fieldWorkerResource.phoneNumber()) : null,
                fieldWorkerResource.winegrowerId() != null ? fieldWorkerResource.winegrowerId() : null,
                fieldWorkerResource.image() != null ? fieldWorkerResource.image() : null
<<<<<<< HEAD
>>>>>>> develop
=======
>>>>>>> feature/winemakingprocess
        );
    }
}
