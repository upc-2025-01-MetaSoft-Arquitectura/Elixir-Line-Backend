package com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.services.winegrower;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.aggregates.FieldWorker;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.aggregates.Winegrower;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.queries.*;
import java.util.List;
import java.util.Optional;

public interface WinegrowerQueryService {
    List<Winegrower> handle(GetAllWinegrowersQuery query);
    Optional<Winegrower> handle(GetWinegrowerByIdQuery query);
    List<FieldWorker> handle(GetAllFieldWorkersByWinegrowerIdQuery query);
    List<FieldWorker> handle(GetAllFieldWorkersByWinegrowerIdByEmployeeStatusQuery query);
}
