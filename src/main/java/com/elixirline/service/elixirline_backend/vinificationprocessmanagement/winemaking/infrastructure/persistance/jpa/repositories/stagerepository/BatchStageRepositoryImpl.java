package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.infrastructure.persistance.jpa.repositories.stagerepository;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.entities.ProcessStage;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BatchStageRepositoryImpl implements BatchStageRepository {
    private final EntityManager entityManager;

    @Override
    public List<ProcessStage> findStagesByBatchId(Long batchId) {
        String jpql = "SELECT ps FROM ProcessStage ps WHERE ps.batchId = :batchId";
        return entityManager.createQuery(jpql, ProcessStage.class)
                .setParameter("batchId", batchId)
                .getResultList();
    }
}
