package com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.resources;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.valueobjects.*;

import javax.annotation.Nullable;

public record UpdateVinegrowerResource(
        @Nullable String name,
        @Nullable String lastname,
        @Nullable String country,
        @Nullable String phoneNumber,
        @Nullable String profilePicture
) { }
