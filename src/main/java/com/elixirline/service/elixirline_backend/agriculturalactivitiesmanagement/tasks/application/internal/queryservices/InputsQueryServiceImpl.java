package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.application.internal.queryservices;

import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.aggregates.Inputs;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.queries.GetAllInputsQuery;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.queries.GetInputsByIdQuery;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.queries.GetInputsByNameQuery;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.queries.GetInputsByWinegrowerIdQuery;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.services.InputsQueryService;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.infrastructure.persistance.jpa.repositories.InputsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InputsQueryServiceImpl implements InputsQueryService {

    private final InputsRepository inputsRepository;

    public InputsQueryServiceImpl(InputsRepository inputsRepository) {
        this.inputsRepository = inputsRepository;
    }


    @Override
    public List<Inputs> handle(GetAllInputsQuery query) {
        return inputsRepository.findAll();
    }

    @Override
    public Optional<Inputs> handle (GetInputsByIdQuery query) {
        return inputsRepository.findById(query.id());
    }

    @Override
    public Optional<Inputs> handle(GetInputsByNameQuery query) {
        return inputsRepository.findByNameIgnoreCase(query.name());
    }

    @Override
    public List<Inputs> handle(GetInputsByWinegrowerIdQuery query) {
        return inputsRepository.findByWinegrowerId(query.winegrowerId());
    }
}
