package com.elixirline.service.elixirline_backend.employeemanagement.employees.application.internal.commandservices;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.exceptions.FieldWorkerNotBeCreated;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.aggregates.FieldWorker;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.commands.*;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.valueobjects.EmployeeStatus;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.valueobjects.ProfilePicture;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.services.fieldworker.FieldWorkerCommandService;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.infrastructure.persistance.jpa.repositories.FieldWorkerRepository;
import com.elixirline.service.elixirline_backend.shared.infrastructure.storage.FirebaseFileService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FieldWorkerCommandServiceImpl implements FieldWorkerCommandService {
    private final FieldWorkerRepository fieldWorkerRepository;
    private final FirebaseFileService firebaseFileService;

    @Transactional
    @Override
    public Optional<FieldWorker> handle(CreateFieldWorkerCommand command) {
        FieldWorker fieldWorker = new FieldWorker(
                command.userId(),
                command.name(),
                command.lastname(),
                command.phoneNumber(),
                command.vinegrowerId()
        );

        try {
            return Optional.of(fieldWorkerRepository.save(fieldWorker));
        } catch (Exception e) {
            throw new FieldWorkerNotBeCreated(e.getMessage());
        }
    }

    @Transactional
    @Override
    public Optional<FieldWorker> update(UpdateFieldWorkerCommand command) {
        return fieldWorkerRepository.findById(command.fieldWorkerId()).map(fieldWorker -> {
            fieldWorker.setName(command.name());
            fieldWorker.setLastname(command.lastname());
            fieldWorker.setPhoneNumber(command.phoneNumber());
            fieldWorker.setVinegrowerId(command.vinegrowerId());

            if (command.image() != null && !command.image().isEmpty()) {
                try {
                    String imageUrl = firebaseFileService.saveImage(command.image());
                    fieldWorker.setProfilePicture(new ProfilePicture(imageUrl));
                } catch (IOException e) {
                    throw new RuntimeException("Error uploading the image", e);
                }
            }

            return fieldWorkerRepository.save(fieldWorker);
        });
    }

    @Transactional
    @Override
    public Optional<FieldWorker> updatePartial(UpdateFieldWorkerPartialCommand command) {
        return fieldWorkerRepository.findById(command.fieldWorkerId()).map(fieldWorker -> {
            if (command.name() != null) {
                fieldWorker.setName(command.name());
            }
            if (command.lastname() != null) {
                fieldWorker.setLastname(command.lastname());
            }
            if (command.phoneNumber() != null) {
                fieldWorker.setPhoneNumber(command.phoneNumber());
            }
            if (command.profilePicture() != null) {
                fieldWorker.setProfilePicture(command.profilePicture());
            }
            return fieldWorkerRepository.save(fieldWorker);
        });
    }

    @Transactional
    @Override
    public void logicallyDelete(DeleteFieldWorkerCommand command) {
        fieldWorkerRepository.findById(command.fieldWorkerId()).ifPresent(fieldWorker -> {
            fieldWorker.setStatus(EmployeeStatus.INACTIVE);
            fieldWorkerRepository.save(fieldWorker);
        });
    }

    @Transactional
    @Override
    public void physicallyDelete(DeleteFieldWorkerCommand command) {
        fieldWorkerRepository.deleteById(command.fieldWorkerId());
    }

    @Transactional
    @Override
    public Optional<FieldWorker> activate(ActivateFieldWorkerCommand command) {
        return fieldWorkerRepository.findById(command.fieldWorkerId()).map(fieldWorker -> {
            fieldWorker.setStatus(EmployeeStatus.ACTIVE);
            return fieldWorkerRepository.save(fieldWorker);
        });
    }
}
