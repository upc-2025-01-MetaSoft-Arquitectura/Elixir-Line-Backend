package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.aggregates;

import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.valueobjetcs.UnitType;
import com.elixirline.service.elixirline_backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Inputs extends AuditableAbstractAggregateRoot<Inputs> {
    private String name;
    private String description;
    private Double quantity;
    private Long winegrowerId;

    @Enumerated(EnumType.STRING)
    private UnitType units;

    private String image;


    public Inputs(String name, String description, Double quantity, Long winegrowerId,UnitType units, String imageUrl){
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.winegrowerId = winegrowerId;
        this.units = units;
        this.image = imageUrl;
    }

    public Inputs updateInformation(String name, String description, Double quantity, Long winegrowerId, UnitType unit){
        this.name = name != null ? name : this.name;
        this.description = description != null ? description : this.description;
        this.quantity = quantity != null ? quantity : this.quantity;
        this.winegrowerId = winegrowerId != null ? winegrowerId : this.winegrowerId;
        this.units = unit != null ? unit : this.units;
        return this;
    }

    public void updateImage(String imageUrl) {
        if (imageUrl != null && !imageUrl.isBlank()) {
            this.image = imageUrl;
        }
    }

}
