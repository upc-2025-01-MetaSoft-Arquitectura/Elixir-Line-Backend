package com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.commands;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.valueobjects.*;

public record CreateFieldWorkerCommand (
        Long userId,
        Name name,
        Lastname lastname,
        PhoneNumber phoneNumber,
<<<<<<< HEAD
<<<<<<< HEAD
        Long vinegrowerId
=======
        Long winegrowerId
>>>>>>> develop
=======
        Long winegrowerId
>>>>>>> feature/winemakingprocess
) { }
