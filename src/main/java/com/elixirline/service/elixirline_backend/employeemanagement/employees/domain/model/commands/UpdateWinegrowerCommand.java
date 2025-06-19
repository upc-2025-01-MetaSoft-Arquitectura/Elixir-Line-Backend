package com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.commands;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.valueobjects.*;
<<<<<<< HEAD

public record UpdateWinegrowerCommand(
        Long vinegrowerId,
=======
import org.springframework.web.multipart.MultipartFile;

public record UpdateWinegrowerCommand(
        Long winegrowerId,
>>>>>>> develop
        Name name,
        Lastname lastname,
        Country country,
        PhoneNumber phoneNumber,
<<<<<<< HEAD
        ProfilePicture profilePicture
=======
        MultipartFile image
>>>>>>> develop
) { }
