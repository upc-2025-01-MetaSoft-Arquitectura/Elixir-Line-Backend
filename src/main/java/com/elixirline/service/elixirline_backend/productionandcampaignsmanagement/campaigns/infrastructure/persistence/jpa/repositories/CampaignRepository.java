package com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.infrastructure.persistence.jpa.repositories;

import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.domain.model.aggregates.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {
}
