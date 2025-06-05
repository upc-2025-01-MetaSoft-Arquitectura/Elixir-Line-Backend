package com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.aggregates;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.valueobjects.*;
import com.elixirline.service.elixirline_backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

@Getter
@Setter
@Entity(name = "field_worker")
@NoArgsConstructor
public class FieldWorker extends AuditableAbstractAggregateRoot<FieldWorker> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "field_worker_id", unique = true)
    private Long fieldWorkerId;

    @Column(name = "user_id", unique = true)
    private Long userId;

    @Embedded
    private Name name;

    @Embedded
    private Lastname lastname;

    @Embedded
    private PhoneNumber phoneNumber;

    @Embedded
    private ProfilePicture profilePicture;

    @Column(name = "vinegrower_id")
    private Long vinegrowerId;



    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private EmployeeStatus status = EmployeeStatus.ACTIVE;

    public FieldWorker(Long userId, Name name, Lastname lastname, PhoneNumber phoneNumber, ProfilePicture profilePicture, Long vinegrowerId) {
        this.userId = userId;
        this.name = name;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
        this.profilePicture = profilePicture;
        this.vinegrowerId = vinegrowerId;
    }
}
