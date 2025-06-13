package com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.transform;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.commands.UpdateWinegrowerCommand;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.valueobjects.*;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.resources.UpdateWinegrowerResource;

public class UpdateWinegrowerCommandFromResourceAssembler {
    public static UpdateWinegrowerCommand toCommandFromResource(Long vinegrowerId, UpdateWinegrowerResource vinegrowerResource) {
        return new UpdateWinegrowerCommand(
          vinegrowerId,
          vinegrowerResource.name() != null ? new Name(vinegrowerResource.name()) : null,
          vinegrowerResource.lastname() != null ? new Lastname(vinegrowerResource.lastname()) : null,
          vinegrowerResource.country() != null ? new Country(vinegrowerResource.country()) : null,
          vinegrowerResource.phoneNumber() != null ? new PhoneNumber(vinegrowerResource.phoneNumber()) : null,
          vinegrowerResource.profilePicture() != null ? new ProfilePicture(vinegrowerResource.profilePicture()) : null
        );
    }
}
