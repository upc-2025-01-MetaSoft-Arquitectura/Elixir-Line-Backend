package com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.transform;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.aggregates.Winegrower;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.resources.WinegrowerResource;

public class WinegrowerResourceAssembler {
    public static WinegrowerResource toResource(Winegrower winegrower) {
        return new WinegrowerResource(
                winegrower.getId(),
                winegrower.getUserId(),
                winegrower.getName(),
                winegrower.getLastname(),
                winegrower.getCountry(),
                winegrower.getPhoneNumber(),
                winegrower.getProfilePicture(),
                winegrower.getStatus()
        );
    }
}
