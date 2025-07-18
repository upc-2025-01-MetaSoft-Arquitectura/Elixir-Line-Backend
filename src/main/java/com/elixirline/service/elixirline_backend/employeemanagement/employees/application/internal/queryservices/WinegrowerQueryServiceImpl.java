package com.elixirline.service.elixirline_backend.employeemanagement.employees.application.internal.queryservices;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.aggregates.FieldWorker;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.aggregates.Winegrower;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.queries.GetAllFieldWorkersByWinegrowerIdByEmployeeStatusQuery;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.queries.GetAllFieldWorkersByWinegrowerIdQuery;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.queries.GetAllWinegrowersQuery;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.queries.GetWinegrowerByIdQuery;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.services.winegrower.WinegrowerQueryService;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.infrastructure.persistance.jpa.repositories.FieldWorkerRepository;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.infrastructure.persistance.jpa.repositories.WinegrowerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class WinegrowerQueryServiceImpl implements WinegrowerQueryService {
    private final WinegrowerRepository winegrowerRepository;
    private final FieldWorkerRepository fieldWorkerRepository;

    @Override
    public List<Winegrower> handle(GetAllWinegrowersQuery query) {
        return winegrowerRepository.findAll();
    }

    @Override
    public Optional<Winegrower> handle(GetWinegrowerByIdQuery query) {
        return winegrowerRepository.findById(query.winegrowerId());
    }

    @Override
    public List<FieldWorker> handle(GetAllFieldWorkersByWinegrowerIdQuery query) {
        return fieldWorkerRepository.findByWinegrowerId(query.winegrowerId());
    }

    @Override
    public List<FieldWorker> handle(GetAllFieldWorkersByWinegrowerIdByEmployeeStatusQuery query) {
        return fieldWorkerRepository.findByWinegrowerIdAndStatus(
                query.winegrowerId(),
                query.employeeStatus()
        );
    }
}
