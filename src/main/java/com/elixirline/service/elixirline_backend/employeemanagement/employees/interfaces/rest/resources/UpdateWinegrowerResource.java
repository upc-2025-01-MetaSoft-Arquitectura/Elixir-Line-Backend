package com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.resources;

<<<<<<< HEAD
=======
import org.springframework.web.multipart.MultipartFile;
>>>>>>> develop
import javax.annotation.Nullable;

public record UpdateWinegrowerResource(
        @Nullable String name,
        @Nullable String lastname,
        @Nullable String country,
        @Nullable String phoneNumber,
<<<<<<< HEAD
        @Nullable String profilePicture
=======
        @Nullable MultipartFile image
>>>>>>> develop
) { }
