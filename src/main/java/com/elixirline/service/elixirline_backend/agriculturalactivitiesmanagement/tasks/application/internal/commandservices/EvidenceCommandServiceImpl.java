package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.application.internal.commandservices;

import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.commands.CreateEvidenceCommand;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.entities.Evidence;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.services.EvidenceCommandService;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.infrastructure.persistance.jpa.repositories.EvidenceRepository;
import com.elixirline.service.elixirline_backend.shared.infrastructure.storage.AzureBlobService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EvidenceCommandServiceImpl implements EvidenceCommandService {
    private final EvidenceRepository evidenceRepository;
    private final AzureBlobService azureBlobService;

    public EvidenceCommandServiceImpl(EvidenceRepository evidenceRepository, AzureBlobService azureBlobService) {
        this.evidenceRepository = evidenceRepository;
        this.azureBlobService = azureBlobService;
    }

    @Override
    public Optional<Evidence> handle(CreateEvidenceCommand command) {
        String imageUrl = azureBlobService.upload(command.imageFile());
        var evidence = new Evidence(
                command.taskId(),
                command.description(),
                command.progressPercentage(),
                imageUrl
        );
        evidenceRepository.save(evidence);
        return Optional.of(evidence);
    }
}