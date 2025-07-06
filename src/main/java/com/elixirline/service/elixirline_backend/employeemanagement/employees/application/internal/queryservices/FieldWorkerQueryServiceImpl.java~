package com.elixirline.service.elixirline_backend.employeemanagement.employees.application.internal.queryservices;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.aggregates.FieldWorker;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.queries.GetAllFieldWorkersQuery;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.queries.GetFieldWorkerByIdQuery;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.services.fieldworker.FieldWorkerQueryService;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.infrastructure.persistance.jpa.repositories.FieldWorkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FieldWorkerQueryServiceImpl implements FieldWorkerQueryService {
    private final FieldWorkerRepository fieldWorkerRepository;

    @Override
    public List<FieldWorker> handle(GetAllFieldWorkersQuery query) {
        return fieldWorkerRepository.findAll();
    }

    @Override
    public Optional<FieldWorker> handle(GetFieldWorkerByIdQuery query) {
        return fieldWorkerRepository.findById(query.fieldWorkerId());
    }
}
