package com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.commands;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.valueobjects.*;

public record CreateWinegrowerCommand(
        Long userId,
        Name name,
        Lastname lastname,
        Country country,
        PhoneNumber phoneNumber
) { }
