package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.infrastructure.persistance.jpa.repositories;

import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.aggregates.Tasks;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.valueobjetcs.TaskType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TasksRepository extends JpaRepository<Tasks, Long> {
    List<Tasks> findByType(TaskType type);
    List<Tasks> findByWinegrowerId(Long winegrowerId);
    List<Tasks> findByIdInAndType(List<Long> ids, TaskType type);
    List<Tasks> findByWinegrowerIdAndType(Long winegrowerId, TaskType type);
    List<Tasks> findByWinegrowerIdAndFieldWorkerId(Long winegrowerId, Long fieldWorkerId);
}
