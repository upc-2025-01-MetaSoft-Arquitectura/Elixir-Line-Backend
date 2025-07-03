package com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.aggregates;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.valueobjects.*;
import com.elixirline.service.elixirline_backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

@Getter
@Setter
@Entity(name = "field_worker")
@NoArgsConstructor
public class FieldWorker extends AuditableAbstractAggregateRoot<FieldWorker> {
    @Column(name = "user_id", unique = true)
    private Long userId;

    @Embedded
    @NotNull(message = "Name is required")
    private Name name;

    @Embedded
    @NotNull(message = "Lastname is required")
    private Lastname lastname;

    @Embedded
    @NotNull(message = "Phone number is required")
    private PhoneNumber phoneNumber;

    @Embedded
    private ProfilePicture profilePicture;

    @Column(name = "winegrower_id")
    @NotNull(message = "Vinegrower Id is required")
    private Long winegrowerId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private EmployeeStatus status = EmployeeStatus.ACTIVE;

    public FieldWorker(Long userId, Name name, Lastname lastname, PhoneNumber phoneNumber, Long winegrowerId) {
        this.userId = userId;
        this.name = name;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
        this.winegrowerId = winegrowerId;
        this.profilePicture = new ProfilePicture();
    }
}
