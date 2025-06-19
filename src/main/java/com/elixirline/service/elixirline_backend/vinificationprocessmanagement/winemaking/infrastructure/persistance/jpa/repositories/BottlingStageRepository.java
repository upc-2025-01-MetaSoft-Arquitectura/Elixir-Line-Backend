package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.infrastructure.persistance.jpa.repositories;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.BottlingStage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BottlingStageRepository extends JpaRepository<BottlingStage, Long> {
    Optional<BottlingStage> findByBatchId(Long batchId);
    void deleteByBatchId(Long batchId);
}
