package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.infrastructure.persistance.jpa.repositories;

import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.entities.Evidence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvidenceRepository extends JpaRepository<Evidence, Long> {
    List<Evidence> findByTaskId(Long taskId);
}
