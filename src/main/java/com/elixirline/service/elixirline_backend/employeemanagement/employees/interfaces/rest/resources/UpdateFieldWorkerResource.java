package com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.resources;

public record UpdateFieldWorkerResource(
        String name,
        String lastname,
        String phoneNumber,
        String profilePicture,
        Long vinegrowerId
) { }
