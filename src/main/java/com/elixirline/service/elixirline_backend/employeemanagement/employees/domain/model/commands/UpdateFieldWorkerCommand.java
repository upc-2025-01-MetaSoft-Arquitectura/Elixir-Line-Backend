package com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.commands;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.valueobjects.*;
<<<<<<< HEAD
=======
import org.springframework.web.multipart.MultipartFile;
>>>>>>> develop

public record UpdateFieldWorkerCommand(
        Long fieldWorkerId,
        Name name,
        Lastname lastname,
        PhoneNumber phoneNumber,
<<<<<<< HEAD
        ProfilePicture profilePicture,
        Long vinegrowerId
=======
        Long winegrowerId,
        MultipartFile image
>>>>>>> develop
) { }
