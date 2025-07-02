package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.services;


import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.entities.Evidence;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.queries.GetEvidenceByTaskIdQuery;

import java.util.List;

public interface EvidenceQueryService {
    List<Evidence> handle(GetEvidenceByTaskIdQuery query);
}
