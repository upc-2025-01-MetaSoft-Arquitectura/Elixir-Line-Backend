package com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.resources;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.valueobjects.*;

public record WinegrowerResource(
        Long id,
        Long userId,
        Name name,
        Lastname lastname,
        Country country,
        PhoneNumber phoneNumber,
        ProfilePicture profilePicture,
        EmployeeStatus status
) { }