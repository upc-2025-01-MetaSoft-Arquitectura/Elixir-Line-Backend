package com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.commands;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.valueobjects.*;

public record UpdateFieldWorkerCommand(
        Long fieldWorkerId,
        Name name,
        Lastname lastname,
        PhoneNumber phoneNumber,
        ProfilePicture profilePicture,
        Long vinegrowerId
) { }
