package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.application.internal.queryservices;

import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.aggregates.Tasks;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.entities.Evidence;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.queries.GetTasksByTypeQuery;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.queries.GetTaskByIdQuery;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.valueobjetcs.TaskType;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.services.TasksQueryService;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.infrastructure.persistance.jpa.repositories.EvidenceRepository;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.infrastructure.persistance.jpa.repositories.TasksRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TasksQueryServiceImpl implements TasksQueryService {
    private final TasksRepository tasksRepository;
    private final EvidenceRepository evidenceRepository;
    public TasksQueryServiceImpl(TasksRepository tasksRepository,  EvidenceRepository evidenceRepository) {
        this.tasksRepository = tasksRepository;
        this.evidenceRepository = evidenceRepository;
    }

    @Override
    public List<Tasks> handle(GetTasksByTypeQuery query){
        return tasksRepository.findByWinegrowerIdAndType(query.winegrowerId(), query.type());
    }

    @Override
    public Optional<Tasks> handle(GetTaskByIdQuery query) {
        return tasksRepository.findById(query.taskId());
    }

    @Override
    public List<Tasks> findByWinegrowerId(Long winegrowerId) {
        return tasksRepository.findByWinegrowerId(winegrowerId);
    }

    @Override
    public List<Tasks> findByTypeWithEvidence(Long winegrowerId,TaskType type) {
        List<Tasks> tasks = tasksRepository.findByWinegrowerIdAndType(winegrowerId, type);
        if (tasks.isEmpty()) {
            return List.of();
        }

        List<Long> taskIds = tasks.stream()
                .map(Tasks::getId)
                .collect(Collectors.toList());

        List<Long> taskIdsWithEvidence = evidenceRepository.findDistinctTaskIdsIn(taskIds);

        return tasks.stream()
                .filter(task -> taskIdsWithEvidence.contains(task.getId()))
                .collect(Collectors.toList());
    }
}
