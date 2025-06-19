package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.infrastructure.persistance.jpa.repositories;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.FiltrationStage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FiltrationStageRepository extends JpaRepository<FiltrationStage, Long> {
    Optional<FiltrationStage> findByBatchId(Long batchId);
    void deleteByBatchId(Long batchId);
}
