package com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.infrastructure.persistence.jpa.repositories;

import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.domain.model.aggregates.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    Optional<Campaign> findByName(String name);
    List<Campaign> findByWinegrowerId(Long winegrowerId);
}
