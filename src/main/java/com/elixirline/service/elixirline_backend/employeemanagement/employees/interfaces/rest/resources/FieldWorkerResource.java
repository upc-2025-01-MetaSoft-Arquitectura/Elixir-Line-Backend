package com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.resources;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.valueobjects.*;

public record FieldWorkerResource(
        Long fieldWorkerId,
        Long userId,
        Name name,
        Lastname lastname,
        PhoneNumber phoneNumber,
        ProfilePicture profilePicture,
<<<<<<< HEAD
<<<<<<< HEAD
        Long vinegrowerId,
=======
        Long winegrowerId,
>>>>>>> develop
=======
        Long winegrowerId,
>>>>>>> feature/winemakingprocess
        EmployeeStatus status
) { }
