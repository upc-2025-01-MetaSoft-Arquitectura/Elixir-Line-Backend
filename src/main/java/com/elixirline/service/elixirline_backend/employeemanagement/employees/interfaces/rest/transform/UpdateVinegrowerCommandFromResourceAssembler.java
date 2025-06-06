package com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.transform;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.commands.UpdateVinegrowerCommand;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.valueobjects.*;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.resources.UpdateVinegrowerResource;

public class UpdateVinegrowerCommandFromResourceAssembler {
    public static UpdateVinegrowerCommand toCommandFromResource(Long vinegrowerId, UpdateVinegrowerResource vinegrowerResource) {
        return new UpdateVinegrowerCommand(
          vinegrowerId,
          vinegrowerResource.name() != null ? new Name(vinegrowerResource.name()) : null,
          vinegrowerResource.lastname() != null ? new Lastname(vinegrowerResource.lastname()) : null,
          vinegrowerResource.country() != null ? new Country(vinegrowerResource.country()) : null,
          vinegrowerResource.phoneNumber() != null ? new PhoneNumber(vinegrowerResource.phoneNumber()) : null,
          vinegrowerResource.profilePicture() != null ? new ProfilePicture(vinegrowerResource.profilePicture()) : null
        );
    }
}
