package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.aggregates;

import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.valueobjetcs.UnitType;
import com.elixirline.service.elixirline_backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Inputs extends AuditableAbstractAggregateRoot<Inputs> {
    private String name;
    private String description;
    private Long quantity;
    @Enumerated(EnumType.STRING)
    private UnitType units;
    private String image;


    public Inputs(String name, String description, Long quantity, UnitType units, String imageUrl){
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.units = units;
        this.image = imageUrl;
    }

    public Inputs updateInformation(String name, String description, Long quantity, UnitType unit){
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.units = unit;
        return this;
    }

    public void updateImage(String imageUrl){
        this.image = imageUrl;
    }

}
