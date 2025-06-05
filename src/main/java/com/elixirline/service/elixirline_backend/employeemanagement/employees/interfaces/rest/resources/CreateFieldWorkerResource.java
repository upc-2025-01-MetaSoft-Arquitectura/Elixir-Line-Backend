package com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.resources;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.valueobjects.*;

public record CreateFieldWorkerResource(
        Long userId,
        Name name,
        Lastname lastname,
        PhoneNumber phoneNumber,
        ProfilePicture profilePicture,
        Long vinegrowerId
) { }
