package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.application.internal.queryservices;

import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.aggregates.Tasks;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.entities.Incident;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.queries.GetIncidentByTaskIdQuery;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.valueobjetcs.TaskType;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.services.IncidentQueryService;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.infrastructure.persistance.jpa.repositories.IncidentRepository;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.infrastructure.persistance.jpa.repositories.TasksRepository;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.interfaces.rest.resources.IncidentWithTaskInfoResource;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class IncidentQueryServiceImpl implements IncidentQueryService {
    private final IncidentRepository incidentRepository;
    private final TasksRepository tasksRepository;

    public IncidentQueryServiceImpl(IncidentRepository incidentRepository, TasksRepository tasksRepository) {
        this.incidentRepository = incidentRepository;
        this.tasksRepository = tasksRepository;
    }

    @Override
    public List<IncidentWithTaskInfoResource> handleAllWithTaskInfo() {
        List<Incident> incidents = incidentRepository.findAll();

        return incidents.stream()
                .map(incident -> {
                    var task = tasksRepository.findById(incident.getTaskId()).orElse(null);
                    if (task == null) return null;

                    return new IncidentWithTaskInfoResource(
                            incident.getId(),
                            incident.getTaskId(),
                            task.getTitle(),
                            task.getBatchId(),
                            task.getWinegrowerId(),
                            task.getType().name(),
                            incident.getDescription(),
                            incident.getImageUrl(),
                            incident.getCreatedAt().toString()
                    );
                })
                .filter(Objects::nonNull)
                .toList();
    }

    @Override
    public List<IncidentWithTaskInfoResource> handleByTaskType(TaskType type) {
        List<Incident> incidents = incidentRepository.findAll();

        return incidents.stream()
                .map(incident -> {
                    var task = tasksRepository.findById(incident.getTaskId()).orElse(null);
                    if (task == null || !task.getType().equals(type)) return null;

                    return new IncidentWithTaskInfoResource(
                            incident.getId(),                         // ✅ incidentId
                            incident.getTaskId(),                     // ✅ taskId
                            task.getTitle(),                          // ✅ taskTitle
                            task.getBatchId(),                        // ✅ batchId
                            task.getWinegrowerId(),                   // ✅ winegrowerId
                            task.getType().name(),                    // ✅ taskType as String
                            incident.getDescription(),                // ✅ incidentDescription
                            incident.getImageUrl(),                   // ✅ imageUrl
                            incident.getCreatedAt().toString()        // ✅ createdAt
                    );
                })
                .filter(Objects::nonNull)
                .toList();
    }

    @Override
    public Optional<Incident> handle(Long incidentId) {
        return incidentRepository.findById(incidentId);
    }
}
