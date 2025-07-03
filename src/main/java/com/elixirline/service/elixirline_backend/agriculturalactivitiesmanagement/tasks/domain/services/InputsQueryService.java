package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.services;

import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.aggregates.Inputs;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.queries.GetAllInputsQuery;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.queries.GetInputsByIdQuery;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.queries.GetInputsByNameQuery;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.queries.GetInputsByWinegrowerIdQuery;

import java.util.List;
import java.util.Optional;

public interface InputsQueryService {
    List<Inputs> handle(GetAllInputsQuery query);
    List<Inputs> handle(GetInputsByWinegrowerIdQuery query);
    Optional<Inputs> handle(GetInputsByIdQuery query);
    Optional<Inputs> handle(GetInputsByNameQuery query);
}
