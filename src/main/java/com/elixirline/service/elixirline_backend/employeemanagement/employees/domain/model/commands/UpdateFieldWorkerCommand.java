package com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.commands;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.valueobjects.*;
<<<<<<< HEAD
<<<<<<< HEAD
=======
import org.springframework.web.multipart.MultipartFile;
>>>>>>> develop
=======
import org.springframework.web.multipart.MultipartFile;
>>>>>>> feature/winemakingprocess

public record UpdateFieldWorkerCommand(
        Long fieldWorkerId,
        Name name,
        Lastname lastname,
        PhoneNumber phoneNumber,
<<<<<<< HEAD
<<<<<<< HEAD
        ProfilePicture profilePicture,
        Long vinegrowerId
=======
        Long winegrowerId,
        MultipartFile image
>>>>>>> develop
=======
        Long winegrowerId,
        MultipartFile image
>>>>>>> feature/winemakingprocess
) { }
