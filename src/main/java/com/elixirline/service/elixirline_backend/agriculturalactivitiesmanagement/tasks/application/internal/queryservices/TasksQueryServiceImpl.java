package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.application.internal.queryservices;

import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.aggregates.Tasks;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.queries.GetFieldTasksQuery;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.queries.GetIndustrialTasksQuery;
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
    public List<Tasks> handle(GetFieldTasksQuery query){
        return tasksRepository.findByType(TaskType.TASK_FIELD);
    }
    @Override
    public List<Tasks> handle(GetIndustrialTasksQuery query){
        return tasksRepository.findByType(TaskType.TASK_INDUSTRY);
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
    public List<Tasks> findByTypeWithEvidence(TaskType type) {
        Set<Long> taskIdsWithEvidence = evidenceRepository.findAll().stream().map(e -> e.getTaskId()).collect(Collectors.toSet());
        return tasksRepository.findAllById(taskIdsWithEvidence).stream().filter(task -> task.getType() == type).collect(Collectors.toList());
    }
}
