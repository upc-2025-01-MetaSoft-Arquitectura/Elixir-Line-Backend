package com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.domain.model.aggregates;

import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.domain.model.commands.CreateCampaignCommand;
import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.domain.model.valueobjects.CampaignStatus;
import com.elixirline.service.elixirline_backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class Campaign extends AuditableAbstractAggregateRoot<Campaign> {
    private String name;
    private String year;
    private Long winegrowerId;
    private Long batches;
    @Enumerated(EnumType.STRING)
    private CampaignStatus status;
    private LocalDate startDate;
    private LocalDate endDate;

    public Campaign(CreateCampaignCommand command) {
        this.name = command.name();
        this.year = command.year();
        this.winegrowerId = command.winegrowerId();
        this.batches = command.batches() != null ? command.batches() : 0L;
        this.status = command.status() != null
                ? CampaignStatus.valueOf(command.status().toUpperCase())
                : CampaignStatus.NO_INICIADO;
        this.startDate = command.startDate();
        this.endDate = command.endDate();
    }

//    public Campaign updateInformation(String name, String year, LocalDate startDate, LocalDate endDate) {
//        this.name = name;
//        this.year = year;
//        this.startDate = startDate;
//        this.endDate = endDate;
//        return this;
//    }

    public Campaign updateInformation(String name, String year, Long winegrowerId, Long batches,String status, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.year = year;
        this.winegrowerId = winegrowerId;
        this.batches = batches;
        this.status = CampaignStatus.valueOf(status.toUpperCase());
        this.startDate = startDate;
        this.endDate = endDate;
        return this;
    }

    public Campaign patchInformation(String name, String year, Long winegrowerId, Long batches, String status, LocalDate startDate, LocalDate endDate) {
        if (name != null) this.name = name;
        if (year != null) this.year = year;
        if (winegrowerId != null) this.winegrowerId = winegrowerId;
        if (batches != null) this.batches = batches;
        if (status != null) this.status = CampaignStatus.valueOf(status.toUpperCase());
        if (startDate != null) this.startDate = startDate;
        if (endDate != null) this.endDate = endDate;
        return this;
    }
}
