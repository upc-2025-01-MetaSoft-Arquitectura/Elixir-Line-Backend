package com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.resources;

<<<<<<< HEAD
<<<<<<< HEAD
=======
import org.springframework.web.multipart.MultipartFile;
>>>>>>> develop
=======
import org.springframework.web.multipart.MultipartFile;
>>>>>>> feature/winemakingprocess
import javax.annotation.Nullable;

public record UpdateWinegrowerResource(
        @Nullable String name,
        @Nullable String lastname,
        @Nullable String country,
        @Nullable String phoneNumber,
<<<<<<< HEAD
<<<<<<< HEAD
        @Nullable String profilePicture
=======
        @Nullable MultipartFile image
>>>>>>> develop
=======
        @Nullable MultipartFile image
>>>>>>> feature/winemakingprocess
) { }
