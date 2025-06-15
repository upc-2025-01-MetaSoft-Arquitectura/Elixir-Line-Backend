package com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.transform;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.commands.UpdateWinegrowerCommand;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.valueobjects.*;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.resources.UpdateWinegrowerResource;
import org.springframework.web.multipart.MultipartFile;

public class UpdateWinegrowerCommandFromResourceAssembler {
    public static UpdateWinegrowerCommand toCommandFromResource(Long vinegrowerId, UpdateWinegrowerResource winegrowerResource) {
        return new UpdateWinegrowerCommand(
          vinegrowerId,
          winegrowerResource.name() != null ? new Name(winegrowerResource.name()) : null,
          winegrowerResource.lastname() != null ? new Lastname(winegrowerResource.lastname()) : null,
          winegrowerResource.country() != null ? new Country(winegrowerResource.country()) : null,
          winegrowerResource.phoneNumber() != null ? new PhoneNumber(winegrowerResource.phoneNumber()) : null,
          winegrowerResource.image()
        );
    }
}
