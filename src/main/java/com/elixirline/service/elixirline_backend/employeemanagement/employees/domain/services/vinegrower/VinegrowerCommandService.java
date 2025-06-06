package com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.services.vinegrower;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.aggregates.Vinegrower;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.commands.*;

import java.util.Optional;

public interface VinegrowerCommandService {
    Optional<Vinegrower> handle(CreateVinegrowerCommand command);
    Optional<Vinegrower> update(UpdateVinegrowerCommand command);
    Optional<Vinegrower> updatePartial(UpdateVinegrowerCommand command);
    void logicallyDelete(DeleteVinegrowerCommand command);
    void physicallyDelete(DeleteVinegrowerCommand command);
    Optional<Vinegrower> activate(ActivateVinegrowerCommand command);
}
