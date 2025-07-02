package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.application.internal.queryservices;

import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.entities.Evidence;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.queries.GetEvidenceByTaskIdQuery;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.services.EvidenceQueryService;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.infrastructure.persistance.jpa.repositories.EvidenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvidenceQueryServiceImpl implements EvidenceQueryService {
    private final EvidenceRepository evidenceRepository;
    public EvidenceQueryServiceImpl(EvidenceRepository evidenceRepository) {
        this.evidenceRepository = evidenceRepository;
    }

    @Override
    public List<Evidence> handle(GetEvidenceByTaskIdQuery query) {
        return evidenceRepository.findByTaskId(query.taskId());
    }
}
