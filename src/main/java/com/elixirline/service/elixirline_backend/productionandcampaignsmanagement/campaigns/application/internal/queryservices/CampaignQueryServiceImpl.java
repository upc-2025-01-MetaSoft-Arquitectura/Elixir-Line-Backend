package com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.application.internal.queryservices;

import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.domain.model.aggregates.Campaign;
import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.domain.model.queries.GetAllCampaignsQuery;
import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.domain.model.queries.GetCampaignByIdQuery;
import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.domain.model.queries.GetCampaignByNameQuery;
import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.domain.services.CampaignQueryService;
import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.infrastructure.persistence.jpa.repositories.CampaignRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CampaignQueryServiceImpl implements CampaignQueryService {

    private final CampaignRepository campaignRepository;

    public CampaignQueryServiceImpl(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    @Override
    public List<Campaign> handle(GetAllCampaignsQuery query) {
        return campaignRepository.findAll();
    }

    @Override
    public Optional<Campaign> handle(GetCampaignByIdQuery query) {
        return campaignRepository.findById(query.id());
    }

    @Override
    public Optional<Campaign> handle(GetCampaignByNameQuery query) {
        return campaignRepository.findByName(query.name());
    }

}
