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
<<<<<<< HEAD
<<<<<<< HEAD
    private final WinegrowerRepository vinegrowerRepository;
=======
    private final WinegrowerRepository winegrowerRepository;
>>>>>>> develop
=======
    private final WinegrowerRepository winegrowerRepository;
>>>>>>> feature/winemakingprocess
    private final FieldWorkerRepository fieldWorkerRepository;

    @Override
    public List<Winegrower> handle(GetAllWinegrowersQuery query) {
<<<<<<< HEAD
<<<<<<< HEAD
        return vinegrowerRepository.findAll();
=======
        return winegrowerRepository.findAll();
>>>>>>> develop
=======
        return winegrowerRepository.findAll();
>>>>>>> feature/winemakingprocess
    }

    @Override
    public Optional<Winegrower> handle(GetWinegrowerByIdQuery query) {
<<<<<<< HEAD
<<<<<<< HEAD
        return vinegrowerRepository.findById(query.vinegrowerId());
=======
        return winegrowerRepository.findById(query.winegrowerId());
>>>>>>> develop
=======
        return winegrowerRepository.findById(query.winegrowerId());
>>>>>>> feature/winemakingprocess
    }

    @Override
    public List<FieldWorker> handle(GetAllFieldWorkersByWinegrowerIdQuery query) {
<<<<<<< HEAD
<<<<<<< HEAD
        return fieldWorkerRepository.findByVinegrowerId(query.vinegrowerId());
=======
        return fieldWorkerRepository.findByWinegrowerId(query.winegrowerId());
>>>>>>> develop
=======
        return fieldWorkerRepository.findByWinegrowerId(query.winegrowerId());
>>>>>>> feature/winemakingprocess
    }

    @Override
    public List<FieldWorker> handle(GetAllFieldWorkersByWinegrowerIdByEmployeeStatusQuery query) {
<<<<<<< HEAD
<<<<<<< HEAD
        return fieldWorkerRepository.findByVinegrowerIdAndStatus(
                query.vinegrowerId(),
=======
        return fieldWorkerRepository.findByWinegrowerIdAndStatus(
                query.winegrowerId(),
>>>>>>> develop
=======
        return fieldWorkerRepository.findByWinegrowerIdAndStatus(
                query.winegrowerId(),
>>>>>>> feature/winemakingprocess
                query.employeeStatus()
        );
    }
}
