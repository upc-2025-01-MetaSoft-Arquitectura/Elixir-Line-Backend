package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.entities;

import com.elixirline.service.elixirline_backend.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Evidence extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long taskId;

    private String description;

    private Integer progressPercentage;

    private String imageUrl;

    public Evidence(Long taskId, String description, Integer progressPercentage, String imageUrl) {
        this.taskId = taskId;
        this.description = description;
        this.progressPercentage = progressPercentage;
        this.imageUrl = imageUrl;
    }
}
