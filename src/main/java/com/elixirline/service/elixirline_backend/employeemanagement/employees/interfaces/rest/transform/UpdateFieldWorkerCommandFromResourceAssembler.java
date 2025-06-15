package com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.transform;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.commands.UpdateFieldWorkerCommand;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.valueobjects.*;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.resources.UpdateFieldWorkerResource;
import org.springframework.web.multipart.MultipartFile;

public class UpdateFieldWorkerCommandFromResourceAssembler {
    public static UpdateFieldWorkerCommand toCommandFromResource (Long fieldWorkerId, UpdateFieldWorkerResource resource) {
        return new UpdateFieldWorkerCommand(
                fieldWorkerId,
                resource.name() != null ? new Name(resource.name()) : null,
                resource.lastname() != null ? new Lastname(resource.lastname()) : null,
                resource.phoneNumber() != null ? new PhoneNumber(resource.phoneNumber()) : null,
                resource.vinegrowerId() != null ? resource.vinegrowerId() : null,
                resource.image()
        );
    }
}
