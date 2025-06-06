package com.elixirline.service.elixirline_backend.employeemanagement.employees.application.internal.commandservices;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.exceptions.VinegrowerNotBeCreated;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.aggregates.FieldWorker;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.aggregates.Vinegrower;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.commands.*;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.valueobjects.EmployeeStatus;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.services.vinegrower.VinegrowerCommandService;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.infrastructure.persistance.jpa.repositories.VinegrowerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VinegrowerCommandServiceImpl  implements VinegrowerCommandService{
    private final VinegrowerRepository vinegrowerRepository;

    @Transactional
    @Override
    public Optional<Vinegrower> handle(CreateVinegrowerCommand command) {
        Vinegrower vinegrower = new Vinegrower(
                command.userId(),
                command.name(),
                command.lastname(),
                command.country(),
                command.phoneNumber()
        );

        try {
            return Optional.of(vinegrowerRepository.save(vinegrower));
        } catch (Exception e) {
            throw new VinegrowerNotBeCreated(e.getMessage());
        }
    }

    @Transactional
    @Override
    public Optional<Vinegrower> update(UpdateVinegrowerCommand command) {
        return vinegrowerRepository.findById(command.vinegrowerId()).map(vinegrower -> {
            vinegrower.setName(command.name());
            vinegrower.setLastname(command.lastname());
            vinegrower.setCountry(command.country());
            vinegrower.setPhoneNumber(command.phoneNumber());
            vinegrower.setProfilePicture(command.profilePicture());
            return vinegrowerRepository.save(vinegrower);
        });
    }

    @Transactional
    @Override
    public Optional<Vinegrower> updatePartial(UpdateVinegrowerCommand command) {
        return vinegrowerRepository.findById(command.vinegrowerId()).map(vinegrower -> {
            if (command.name() != null) {
                vinegrower.setName(command.name());
            }
            if (command.lastname() != null) {
                vinegrower.setLastname(command.lastname());
            }
            if (command.country() != null) {
                vinegrower.setCountry(command.country());
            }
            if (command.phoneNumber() != null) {
                vinegrower.setPhoneNumber(command.phoneNumber());
            }
            if (command.profilePicture() != null) {
                vinegrower.setProfilePicture(command.profilePicture());
            }
            return vinegrowerRepository.save(vinegrower);
        });
    }

    @Transactional
    @Override
    public void logicallyDelete(DeleteVinegrowerCommand command) {
        vinegrowerRepository.findById(command.vinegrowerId()).ifPresent(vinegrower -> {
            vinegrower.setStatus(EmployeeStatus.INACTIVE);
            vinegrowerRepository.save(vinegrower);
        });
    }

    @Transactional
    @Override
    public void physicallyDelete(DeleteVinegrowerCommand command) {
        vinegrowerRepository.deleteById(command.vinegrowerId());
    }

    @Transactional
    @Override
    public Optional<Vinegrower> activate(ActivateVinegrowerCommand command) {
        return vinegrowerRepository.findById(command.vinegrowerId()).map(vinegrower -> {
            vinegrower.setStatus(EmployeeStatus.ACTIVE);
            return vinegrowerRepository.save(vinegrower);
        });
    }
}
