package com.elixirline.service.elixirline_backend.employeemanagement.employees.application.internal.commandservices;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.exceptions.WinegrowerNotBeCreated;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.aggregates.Winegrower;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.commands.*;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.valueobjects.EmployeeStatus;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.valueobjects.ProfilePicture;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.services.winegrower.WinegrowerCommandService;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.infrastructure.persistance.jpa.repositories.WinegrowerRepository;
import com.elixirline.service.elixirline_backend.shared.infrastructure.storage.FirebaseFileService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WinegrowerCommandServiceImpl implements WinegrowerCommandService {
    private final WinegrowerRepository winegrowerRepository;
    private final FirebaseFileService firebaseFileService;

    @Transactional
    @Override
    public Optional<Winegrower> handle(CreateWinegrowerCommand command) {
        Winegrower winegrower = new Winegrower(
                command.userId(),
                command.name(),
                command.lastname(),
                command.country(),
                command.phoneNumber()
        );

        try {
            return Optional.of(winegrowerRepository.save(winegrower));
        } catch (Exception e) {
            throw new WinegrowerNotBeCreated(e.getMessage());
        }
    }

    @Transactional
    @Override
    public Optional<Winegrower> update(UpdateWinegrowerCommand command) {
        return winegrowerRepository.findById(command.winegrowerId()).map(winegrower -> {
            winegrower.setName(command.name());
            winegrower.setLastname(command.lastname());
            winegrower.setCountry(command.country());
            winegrower.setPhoneNumber(command.phoneNumber());

            if (command.image() != null && !command.image().isEmpty()) {
                try {
                    String imageUrl = firebaseFileService.saveImage(command.image());
                    winegrower.setProfilePicture(new ProfilePicture(imageUrl));
                } catch (IOException e) {
                    throw new RuntimeException("Error uploading the image", e);
                }
            }

            return winegrowerRepository.save(winegrower);
        });
    }

    /*
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
    }*/

    @Transactional
    @Override
    public void logicallyDelete(DeleteWinegrowerCommand command) {
        winegrowerRepository.findById(command.winegrowerId()).ifPresent(winegrower -> {
            winegrower.setStatus(EmployeeStatus.INACTIVE);
            winegrowerRepository.save(winegrower);
        });
    }

    @Transactional
    @Override
    public void physicallyDelete(DeleteWinegrowerCommand command) {
        winegrowerRepository.deleteById(command.winegrowerId());
    }

    @Transactional
    @Override
    public Optional<Winegrower> activate(ActivateWinegrowerCommand command) {
        return winegrowerRepository.findById(command.winegrowerId()).map(winegrower -> {
            winegrower.setStatus(EmployeeStatus.ACTIVE);
            return winegrowerRepository.save(winegrower);
        });
    }
}
