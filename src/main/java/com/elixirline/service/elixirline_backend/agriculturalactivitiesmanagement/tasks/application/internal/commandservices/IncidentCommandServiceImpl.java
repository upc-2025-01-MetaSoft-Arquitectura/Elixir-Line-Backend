package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.application.internal.commandservices;

import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.commands.CreateIncidentCommand;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.commands.DeleteIncidentCommand;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.commands.PatchIncidentCommand;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.entities.Incident;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.services.IncidentCommandService;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.infrastructure.persistance.jpa.repositories.IncidentRepository;
import com.elixirline.service.elixirline_backend.shared.infrastructure.storage.AzureBlobService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IncidentCommandServiceImpl implements IncidentCommandService {
    private final IncidentRepository incidentRepository;
    private final AzureBlobService azureBlobService;

    public IncidentCommandServiceImpl(IncidentRepository incidentRepository, AzureBlobService azureBlobService) {
        this.incidentRepository = incidentRepository;
        this.azureBlobService = azureBlobService;
    }

    @Override
    public Optional<Incident> handle(CreateIncidentCommand command) {
        String imageUrl = azureBlobService.upload(command.imageFile());
        var incident = new Incident(command.taskId(), command.description(), imageUrl);
        incidentRepository.save(incident);
        return Optional.of(incident);
    }

    @Override
    public Optional<Incident> handle(PatchIncidentCommand command) {
        var existing = incidentRepository.findById(command.incidentId());
        if (existing.isEmpty()) return Optional.empty();

        var incident = existing.get();
        String imageUrl = incident.getImageUrl();

        if (command.imageFile() != null && !command.imageFile().isEmpty()) {
            imageUrl = azureBlobService.upload(command.imageFile());
        }

        incident.updateInformation(command.taskId(), command.description(), imageUrl);
        return Optional.of(incidentRepository.save(incident));
    }

    @Override
    public void handle(DeleteIncidentCommand command) {
        if (!incidentRepository.existsById(command.incidentId())) {
            throw new IllegalArgumentException("Incident not found");
        }
        incidentRepository.deleteById(command.incidentId());
    }
}
