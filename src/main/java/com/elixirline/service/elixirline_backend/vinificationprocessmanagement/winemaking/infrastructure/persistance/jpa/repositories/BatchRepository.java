package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.infrastructure.persistance.jpa.repositories;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Long> {
    List<Batch> findByVineyardCode(String vineyardCode);
    List<Batch> findByCampaignId(Long campaignId);
    List<Batch> findByWinegrowerId(Long winegrowerId);
    List<Batch> findByWinegrowerIdAndCampaignId(Long winegrowerId, Long campaignId);
}
