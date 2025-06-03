package com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.aggregates;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.valueobjects.Country;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.valueobjects.FullName;
import com.elixirline.service.elixirline_backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;

@Getter
@Setter
@Entity(name = "vinegrower")
@AllArgsConstructor
@NoArgsConstructor
public class Vinegrower extends AuditableAbstractAggregateRoot<Vinegrower> {
    @Column(name = "user_id", unique = true)
    private Long userId;

    @Embedded
    @Column(name = "full_name")
    private FullName fullName;

    @Embedded
    @Column(name = "country")
    private Country country;
}
