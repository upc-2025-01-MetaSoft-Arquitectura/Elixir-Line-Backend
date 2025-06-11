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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //editable
    private String name;
    //editable
    private String age;

    private Long driverUserId;

    private Long lots;

    @Enumerated(EnumType.STRING)
    private CampaignStatus status;
    //editable
    private LocalDate startDate;
    //editable
    private LocalDate endDate;

    public Campaign(CreateCampaignCommand command) {
        this.name = command.name();
        this.age = command.age();
        this.driverUserId = command.driverUserId();
        this.lots = command.lots() != null ? command.lots() : 0L;
        this.status = command.status() != null
                ? CampaignStatus.valueOf(command.status().toUpperCase())
                : CampaignStatus.NO_INICIADO;
        this.startDate = command.startDate();
        this.endDate = command.endDate();
    }

    public Campaign updateInformation(String name, String age, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.age = age;
        this.startDate = startDate;
        this.endDate = endDate;
        return this;
    }
}
