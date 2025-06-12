package com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.application.internal.commandservices;

import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.domain.model.aggregates.Campaign;
import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.domain.model.commands.CreateCampaignCommand;
import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.domain.model.commands.DeleteCampaignCommand;
import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.domain.model.commands.UpdateCampaignCommand;
import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.domain.model.exceptions.DuplicateCampaignNameException;
import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.domain.services.CampaignCommandService;
import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.infrastructure.persistence.jpa.repositories.CampaignRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CampaignCommandServiceImpl implements CampaignCommandService {
    private final CampaignRepository campaignRepository;
    CampaignCommandServiceImpl(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    @Override
    public Optional<Campaign> handle(CreateCampaignCommand command) {
        var existingCampaign = campaignRepository.findByName(command.name());
        if (existingCampaign.isPresent()) {
            throw new DuplicateCampaignNameException("Ya existe una campaña con el nombre: " + command.name());
        }
        var campaign = new Campaign(command);
        campaignRepository.save(campaign);
        return Optional.of(campaign);
    }

    public void handle(DeleteCampaignCommand command) {
        if(!campaignRepository.existsById(command.CampaignId())){
            throw new IllegalArgumentException("Campaign does not exist");
        }
        try{
            campaignRepository.deleteById(command.CampaignId());
        }catch (Exception e){
            throw new IllegalArgumentException("Error deleting campaign");
        }
    }

    @Override
    public Optional<Campaign> handle(UpdateCampaignCommand command) {
        var campaign = campaignRepository.findById(command.campaignId());
        if (campaign.isEmpty()) return Optional.empty();

        var updated = campaign.get()
                .updateInformation(command.name(), command.year(), command.startDate(), command.endDate());
        campaignRepository.save(updated);
        return Optional.of(updated);
    }
}
