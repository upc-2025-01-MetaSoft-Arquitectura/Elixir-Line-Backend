package com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.interfaces.rest;

import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.domain.model.commands.DeleteCampaignCommand;
import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.domain.model.queries.GetAllCampaignsQuery;
import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.domain.model.queries.GetCampaignByIdQuery;
import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.domain.services.CampaignCommandService;
import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.domain.services.CampaignQueryService;
import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.interfaces.rest.resources.CampaignResource;
import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.interfaces.rest.resources.CreateCampaignResource;
import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.interfaces.rest.resources.UpdateCampaignResource;
import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.interfaces.rest.transform.CampaignResourceFromEntityAssembler;
import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.interfaces.rest.transform.CreateCampaignCommandFromResourceAssembler;
import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.interfaces.rest.transform.UpdateCampaignCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/campaigns", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Campaigns", description = "Campaigns Management Endpoints")
public class CapaignsController {
    private final CampaignQueryService campaignQueryService;
    private final CampaignCommandService campaignCommandService;
    public CapaignsController(CampaignQueryService campaignQueryService, CampaignCommandService campaignCommandService) {
        this.campaignQueryService = campaignQueryService;
        this.campaignCommandService = campaignCommandService;
    }

    @GetMapping
    public ResponseEntity<List<CampaignResource>> getAllCampaigns() {
        var getAllCampaignsQuery = new GetAllCampaignsQuery();
        var campaigns = campaignQueryService.handle(getAllCampaignsQuery);
        var campaignResource = campaigns.stream().map(CampaignResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(campaignResource);
    }

    @PostMapping
    public ResponseEntity<CampaignResource> createCampaign(@RequestBody CreateCampaignResource resource) {
        var command = CreateCampaignCommandFromResourceAssembler.toCommandFromResource(resource);
        var campaign = campaignCommandService.handle(command);

        if (campaign.isEmpty()) return ResponseEntity.badRequest().build();

        var campaignResource = CampaignResourceFromEntityAssembler.toResourceFromEntity(campaign.get());
        return new ResponseEntity<>(campaignResource, HttpStatus.CREATED);
    }

    @GetMapping("/{campaignId}")
    public ResponseEntity<CampaignResource> getCampaignById(@PathVariable Long campaignId) {
        var getCampaignByIdQuery = new GetCampaignByIdQuery(campaignId);
        var campaign = campaignQueryService.handle(getCampaignByIdQuery);
        if (campaign.isEmpty()) return ResponseEntity.badRequest().build();
        var campaignResource = CampaignResourceFromEntityAssembler.toResourceFromEntity(campaign.get());
        return ResponseEntity.ok(campaignResource);
    }

    @DeleteMapping("/{campaignId}")
    public ResponseEntity<?> deleteCampaign(@PathVariable Long campaignId) {
        var deleteCampaignCommand = new DeleteCampaignCommand(campaignId);
        campaignCommandService.handle(deleteCampaignCommand);
        return ResponseEntity.ok("Deleted Campaign");
    }

    @PutMapping("/{campaignId}")
    public ResponseEntity<CampaignResource> updateCampaign(@PathVariable Long campaignId, @RequestBody UpdateCampaignResource updateCampaignResource) {
        var updateCommand = UpdateCampaignCommandFromResourceAssembler.toCommandFromResource(campaignId, updateCampaignResource);
        var updated = campaignCommandService.handle(updateCommand);
        if (updated.isEmpty()) return ResponseEntity.badRequest().build();
        var campaignResource = CampaignResourceFromEntityAssembler.toResourceFromEntity(updated.get());
        return ResponseEntity.ok(campaignResource);
    }
}
