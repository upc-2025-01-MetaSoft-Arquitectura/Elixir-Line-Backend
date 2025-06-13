package com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.resources;

import javax.annotation.Nullable;

public record UpdateWinegrowerResource(
        @Nullable String name,
        @Nullable String lastname,
        @Nullable String country,
        @Nullable String phoneNumber,
        @Nullable String profilePicture
) { }
