package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.application.internal.queryservices;

import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.aggregates.Tasks;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.entities.Evidence;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.queries.GetEvidenceByTaskIdQuery;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.valueobjetcs.TaskType;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.services.EvidenceQueryService;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.infrastructure.persistance.jpa.repositories.EvidenceRepository;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.infrastructure.persistance.jpa.repositories.TasksRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvidenceQueryServiceImpl implements EvidenceQueryService {
    private final EvidenceRepository evidenceRepository;
    private final TasksRepository tasksRepository;
    public EvidenceQueryServiceImpl(EvidenceRepository evidenceRepository,  TasksRepository tasksRepository) {
        this.evidenceRepository = evidenceRepository;
        this.tasksRepository = tasksRepository;
    }

    @Override
    public List<Evidence> handle(GetEvidenceByTaskIdQuery query) {
        return evidenceRepository.findByTaskId(query.taskId());
    }

    @Override
    public List<Evidence> handleByTaskType(TaskType taskType) {
        List<Long> taskIds = tasksRepository.findByType(taskType)
                .stream().map(Tasks::getId).toList();
        if (taskIds.isEmpty()) return List.of();
        return evidenceRepository.findByTaskIdIn(taskIds);
    }


}
