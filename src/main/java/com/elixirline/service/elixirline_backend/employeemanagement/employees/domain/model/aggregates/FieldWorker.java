package com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.aggregates;

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
@Entity(name = "field_worker")
@AllArgsConstructor
@NoArgsConstructor
public class FieldWorker extends AuditableAbstractAggregateRoot<FieldWorker> {
    @Column(name = "user_id", unique = true)
    private Long userId;

    @Embedded
    @Column(name = "full_name")
    private FullName fullName;

    @Column(name = "vinegrower_id")
    private Long vinegrowerId;
}
