package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.application.internal.commandservices;

import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.aggregates.Tasks;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.commands.CreateTasksCommand;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.commands.DeleteTaskCommand;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.commands.PatchTaskCommand;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.commands.UpdateTaskCommand;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.valueobjetcs.TaskType;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.services.TasksCommandService;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.infrastructure.persistance.jpa.repositories.TasksRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TasksCommandServiceImpl implements TasksCommandService {
    private final TasksRepository tasksRepository;
    public TasksCommandServiceImpl(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    @Override
    public Optional<Tasks> createFieldTask(CreateTasksCommand command){
        var task = new Tasks(
                command.title(),
                command.description(),
                command.startDate(),
                command.endDate(),
                command.winegrowerId(),
                command.fieldWorkerName(),
                command.batchId(),
                command.suppliesIds(),
                TaskType.TASK_FIELD
        );
        tasksRepository.save(task);
        return Optional.of(task);
    }

    @Override
    public Optional<Tasks> createIndustrialTask(CreateTasksCommand command){
        var task = new Tasks(
                command.title(),
                command.description(),
                command.startDate(),
                command.endDate(),
                command.winegrowerId(),
                command.fieldWorkerName(),
                command.batchId(),
                command.suppliesIds(),
                TaskType.TASK_INDUSTRY
        );
        tasksRepository.save(task);
        return Optional.of(task);
    }

    @Override
    public Optional<Tasks> handle(UpdateTaskCommand command) {
        var task = tasksRepository.findById(command.taskId());
        if(task.isEmpty()) return Optional.empty();
        var updated = task.get().updateInformation(command.title(), command.description(), command.startDate(), command.endDate(), command.winegrowerId(), command.fieldWorkerName(), command.batchId(), command.suppliesIds(), command.type());
        tasksRepository.save(updated);
        return Optional.of(updated);
    }

    @Override
    public void handle(DeleteTaskCommand command) {
        if(!tasksRepository.existsById(command.taskId())){
            throw new IllegalArgumentException("Task does not exist");
        }
        try {
            tasksRepository.deleteById(command.taskId());
        }catch (Exception ex){
            throw new IllegalArgumentException("Task does not exist");
        }
    }

    @Override
    public Optional<Tasks> handle(PatchTaskCommand command) {
        var taskOptional = tasksRepository.findById(command.taskId());
        if (taskOptional.isEmpty()) return Optional.empty();

        var task = taskOptional.get();
        task.updateInformation(
                command.title(),
                command.description(),
                command.startDate(),
                command.endDate(),
                command.winegrowerId(),
                command.fieldWorkerName(),
                command.batchId(),
                command.suppliesIds(),
                command.type()
        );

        return Optional.of(tasksRepository.save(task));
    }
}
