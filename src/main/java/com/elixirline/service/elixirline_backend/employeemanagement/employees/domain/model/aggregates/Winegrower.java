package com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.aggregates;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.valueobjects.*;
import com.elixirline.service.elixirline_backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "winegrower")
@NoArgsConstructor
public class Winegrower extends AuditableAbstractAggregateRoot<Winegrower> {
    @Column(name = "user_id", unique = true)
    private Long userId;

    @Embedded
    @NotNull(message = "Name is required")
    private Name name;

    @Embedded
    @NotNull(message = "Lastname is required")
    private Lastname lastname;

    @Embedded
    @NotNull(message = "Country is required")
    private Country country;

    @Embedded
    @NotNull(message = "Phone number is required")
    private PhoneNumber phoneNumber;

    @Embedded
    private ProfilePicture profilePicture;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private EmployeeStatus status = EmployeeStatus.ACTIVE;

    public Winegrower(Long userId, Name name, Lastname lastname, Country country, PhoneNumber phoneNumber) {
        this.userId = userId;
        this.name = name;
        this.lastname = lastname;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.profilePicture = new ProfilePicture();
    }
}
