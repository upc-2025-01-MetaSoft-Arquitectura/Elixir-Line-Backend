package com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.commands;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.valueobjects.*;
import org.springframework.web.multipart.MultipartFile;

public record UpdateWinegrowerCommand(
        Long vinegrowerId,
        Name name,
        Lastname lastname,
        Country country,
        PhoneNumber phoneNumber,
        ProfilePicture profilePicture,
        MultipartFile image
) { }
