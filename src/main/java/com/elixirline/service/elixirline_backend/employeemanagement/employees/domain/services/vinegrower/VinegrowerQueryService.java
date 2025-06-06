package com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.services.vinegrower;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.aggregates.FieldWorker;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.aggregates.Vinegrower;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.queries.*;
import java.util.List;
import java.util.Optional;

public interface VinegrowerQueryService {
    List<Vinegrower> handle(GetAllVinegrowersQuery query);
    Optional<Vinegrower> handle(GetVinegrowerByIdQuery query);
    List<FieldWorker> handle(GetAllFieldWorkersByVinegrowerIdQuery query);
    List<FieldWorker> handle(GetAllFieldWorkersByVinegrowerIdByEmployeeStatusQuery query);
}
