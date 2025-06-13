package com.elixirline.service.elixirline_backend.employeemanagement.employees.application.internal.commandservices;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.exceptions.WinegrowerNotBeCreated;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.aggregates.Winegrower;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.commands.*;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.valueobjects.EmployeeStatus;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.services.winegrower.WinegrowerCommandService;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.infrastructure.persistance.jpa.repositories.WinegrowerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WinegrowerCommandServiceImpl implements WinegrowerCommandService {
    private final WinegrowerRepository vinegrowerRepository;

    @Transactional
    @Override
    public Optional<Winegrower> handle(CreateWinegrowerCommand command) {
        Winegrower vinegrower = new Winegrower(
                command.userId(),
                command.name(),
                command.lastname(),
                command.country(),
                command.phoneNumber()
        );

        try {
            return Optional.of(vinegrowerRepository.save(vinegrower));
        } catch (Exception e) {
            throw new WinegrowerNotBeCreated(e.getMessage());
        }
    }

    @Transactional
    @Override
    public Optional<Winegrower> update(UpdateWinegrowerCommand command) {
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
    public Optional<Winegrower> updatePartial(UpdateWinegrowerCommand command) {
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
    public void logicallyDelete(DeleteWinegrowerCommand command) {
        vinegrowerRepository.findById(command.vinegrowerId()).ifPresent(vinegrower -> {
            vinegrower.setStatus(EmployeeStatus.INACTIVE);
            vinegrowerRepository.save(vinegrower);
        });
    }

    @Transactional
    @Override
    public void physicallyDelete(DeleteWinegrowerCommand command) {
        vinegrowerRepository.deleteById(command.vinegrowerId());
    }

    @Transactional
    @Override
    public Optional<Winegrower> activate(ActivateWinegrowerCommand command) {
        return vinegrowerRepository.findById(command.vinegrowerId()).map(vinegrower -> {
            vinegrower.setStatus(EmployeeStatus.ACTIVE);
            return vinegrowerRepository.save(vinegrower);
        });
    }
}
