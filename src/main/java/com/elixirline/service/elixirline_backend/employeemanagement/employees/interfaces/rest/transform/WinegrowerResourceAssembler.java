package com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.transform;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.aggregates.Winegrower;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.resources.WinegrowerResource;

public class WinegrowerResourceAssembler {
    public static WinegrowerResource toResource(Winegrower vinegrower) {
        return new WinegrowerResource(
                vinegrower.getWinegrowerId(),
                vinegrower.getUserId(),
                vinegrower.getName(),
                vinegrower.getLastname(),
                vinegrower.getCountry(),
                vinegrower.getPhoneNumber(),
                vinegrower.getProfilePicture(),
                vinegrower.getStatus()
        );
    }
}
