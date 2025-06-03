package com.elixirline.service.elixirline_backend.employeemanagement.employees.application.internal.queryservices;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.aggregates.FieldWorker;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.aggregates.Vinegrower;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.queries.GetAllVinegrowersQuery;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.queries.GetVinegrowerByIdQuery;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.services.vinegrower.VinegrowerQueryService;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.infrastructure.persistance.jpa.repositories.VinegrowerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class VinegrowerQueryServiceImpl implements VinegrowerQueryService {
    private final VinegrowerRepository vinegrowerRepository;

    @Override
    public List<Vinegrower> handle(GetAllVinegrowersQuery query) {
        return vinegrowerRepository.findAll();
    }

    @Override
    public Optional<Vinegrower> handle(GetVinegrowerByIdQuery query) {
        return vinegrowerRepository.findById(query.vinegrowerId());
    }
}
