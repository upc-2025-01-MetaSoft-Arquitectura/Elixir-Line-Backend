package com.elixirline.service.elixirline_backend.userandrolemanagement.iam.domain.model.aggregates;

import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.domain.model.entities.Role;
import com.elixirline.service.elixirline_backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * User aggregate root
 * This class represents the aggregate root for the User entity.
 *
 * @see AuditableAbstractAggregateRoot
 */
@Getter
@Setter
@Entity(name = "user")
public class User extends AuditableAbstractAggregateRoot<User> {
    @NotBlank
    @Size(max = 50)
    @Column(unique = true)
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="role_id")
    private Role role;

    public User() { }

    public User(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User updateUser(String email, String password) {
        this.email = email;
        this.password = password;
        return this;
    }

    public User updateUserPassword(String password) {
        this.password = password;
        return this;
    }
}
