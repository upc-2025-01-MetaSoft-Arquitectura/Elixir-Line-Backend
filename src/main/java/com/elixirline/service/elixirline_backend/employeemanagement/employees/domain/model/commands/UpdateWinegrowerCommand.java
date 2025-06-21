package com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.commands;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.valueobjects.*;
<<<<<<< HEAD
<<<<<<< HEAD

public record UpdateWinegrowerCommand(
        Long vinegrowerId,
=======
=======
>>>>>>> feature/winemakingprocess
import org.springframework.web.multipart.MultipartFile;

public record UpdateWinegrowerCommand(
        Long winegrowerId,
<<<<<<< HEAD
>>>>>>> develop
=======
>>>>>>> feature/winemakingprocess
        Name name,
        Lastname lastname,
        Country country,
        PhoneNumber phoneNumber,
<<<<<<< HEAD
<<<<<<< HEAD
        ProfilePicture profilePicture
=======
        MultipartFile image
>>>>>>> develop
=======
        MultipartFile image
>>>>>>> feature/winemakingprocess
) { }
