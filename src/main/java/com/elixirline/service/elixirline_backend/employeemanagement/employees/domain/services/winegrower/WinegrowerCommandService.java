package com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.services.winegrower;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.aggregates.Winegrower;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.commands.*;

import java.util.Optional;

public interface WinegrowerCommandService {
    Optional<Winegrower> handle(CreateWinegrowerCommand command);
    Optional<Winegrower> update(UpdateWinegrowerCommand command);
    Optional<Winegrower> updatePartial(UpdateWinegrowerCommand command);
    void logicallyDelete(DeleteWinegrowerCommand command);
    void physicallyDelete(DeleteWinegrowerCommand command);
    Optional<Winegrower> activate(ActivateWinegrowerCommand command);
}
