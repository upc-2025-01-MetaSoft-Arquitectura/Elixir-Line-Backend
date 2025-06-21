package com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.services.winegrower;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.aggregates.Winegrower;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.commands.*;

import java.util.Optional;

public interface WinegrowerCommandService {
    Optional<Winegrower> handle(CreateWinegrowerCommand command);
    Optional<Winegrower> update(UpdateWinegrowerCommand command);
<<<<<<< HEAD
<<<<<<< HEAD
    Optional<Winegrower> updatePartial(UpdateWinegrowerCommand command);
=======
    //Optional<Winegrower> updatePartial(UpdateWinegrowerCommand command);
>>>>>>> develop
=======
    //Optional<Winegrower> updatePartial(UpdateWinegrowerCommand command);
>>>>>>> feature/winemakingprocess
    void logicallyDelete(DeleteWinegrowerCommand command);
    void physicallyDelete(DeleteWinegrowerCommand command);
    Optional<Winegrower> activate(ActivateWinegrowerCommand command);
}
