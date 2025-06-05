package com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.aggregates;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.valueobjects.*;
import com.elixirline.service.elixirline_backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "vinegrower")
@NoArgsConstructor
public class Vinegrower extends AuditableAbstractAggregateRoot<Vinegrower> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vinegrower_id", unique = true)
    private Long vinegrowerId;

    @Column(name = "user_id", unique = true)
    private Long userId;

    @Embedded
    private Name name;

    @Embedded
    private Lastname lastname;

    @Embedded
    private Country country;

    @Embedded
    private PhoneNumber phoneNumber;

    @Embedded
    private ProfilePicture profilePicture;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private EmployeeStatus status = EmployeeStatus.ACTIVE;

    public Vinegrower(Long userId, Name name, Lastname lastname, Country country, PhoneNumber phoneNumber, ProfilePicture profilePicture) {
        this.userId = userId;
        this.name = name;
        this.lastname = lastname;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.profilePicture = profilePicture;
    }
}
