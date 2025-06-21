package com.elixirline.service.elixirline_backend.employeemanagement.employees.application.internal.commandservices;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.exceptions.WinegrowerNotBeCreated;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.aggregates.Winegrower;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.commands.*;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.valueobjects.EmployeeStatus;
<<<<<<< HEAD
<<<<<<< HEAD
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.services.winegrower.WinegrowerCommandService;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.infrastructure.persistance.jpa.repositories.WinegrowerRepository;
=======
=======
>>>>>>> feature/winemakingprocess
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.valueobjects.ProfilePicture;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.services.winegrower.WinegrowerCommandService;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.infrastructure.persistance.jpa.repositories.WinegrowerRepository;
import com.elixirline.service.elixirline_backend.shared.infrastructure.storage.FirebaseFileService;
<<<<<<< HEAD
>>>>>>> develop
=======
>>>>>>> feature/winemakingprocess
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
<<<<<<< HEAD
=======
import java.io.IOException;
>>>>>>> develop
=======
import java.io.IOException;
>>>>>>> feature/winemakingprocess
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WinegrowerCommandServiceImpl implements WinegrowerCommandService {
<<<<<<< HEAD
<<<<<<< HEAD
    private final WinegrowerRepository vinegrowerRepository;
=======
    private final WinegrowerRepository winegrowerRepository;
    private final FirebaseFileService firebaseFileService;
>>>>>>> develop
=======
    private final WinegrowerRepository winegrowerRepository;
    private final FirebaseFileService firebaseFileService;
>>>>>>> feature/winemakingprocess

    @Transactional
    @Override
    public Optional<Winegrower> handle(CreateWinegrowerCommand command) {
<<<<<<< HEAD
<<<<<<< HEAD
        Winegrower vinegrower = new Winegrower(
=======
        Winegrower winegrower = new Winegrower(
>>>>>>> develop
=======
        Winegrower winegrower = new Winegrower(
>>>>>>> feature/winemakingprocess
                command.userId(),
                command.name(),
                command.lastname(),
                command.country(),
                command.phoneNumber()
        );

        try {
<<<<<<< HEAD
<<<<<<< HEAD
            return Optional.of(vinegrowerRepository.save(vinegrower));
=======
            return Optional.of(winegrowerRepository.save(winegrower));
>>>>>>> develop
=======
            return Optional.of(winegrowerRepository.save(winegrower));
>>>>>>> feature/winemakingprocess
        } catch (Exception e) {
            throw new WinegrowerNotBeCreated(e.getMessage());
        }
    }

    @Transactional
    @Override
    public Optional<Winegrower> update(UpdateWinegrowerCommand command) {
<<<<<<< HEAD
<<<<<<< HEAD
        return vinegrowerRepository.findById(command.vinegrowerId()).map(vinegrower -> {
            vinegrower.setName(command.name());
            vinegrower.setLastname(command.lastname());
            vinegrower.setCountry(command.country());
            vinegrower.setPhoneNumber(command.phoneNumber());
            vinegrower.setProfilePicture(command.profilePicture());
            return vinegrowerRepository.save(vinegrower);
        });
    }

=======
=======
>>>>>>> feature/winemakingprocess
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
<<<<<<< HEAD
>>>>>>> develop
=======
>>>>>>> feature/winemakingprocess
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
<<<<<<< HEAD
<<<<<<< HEAD
    }
=======
    }*/
>>>>>>> develop
=======
    }*/
>>>>>>> feature/winemakingprocess

    @Transactional
    @Override
    public void logicallyDelete(DeleteWinegrowerCommand command) {
<<<<<<< HEAD
<<<<<<< HEAD
        vinegrowerRepository.findById(command.vinegrowerId()).ifPresent(vinegrower -> {
            vinegrower.setStatus(EmployeeStatus.INACTIVE);
            vinegrowerRepository.save(vinegrower);
=======
        winegrowerRepository.findById(command.winegrowerId()).ifPresent(winegrower -> {
            winegrower.setStatus(EmployeeStatus.INACTIVE);
            winegrowerRepository.save(winegrower);
>>>>>>> develop
=======
        winegrowerRepository.findById(command.winegrowerId()).ifPresent(winegrower -> {
            winegrower.setStatus(EmployeeStatus.INACTIVE);
            winegrowerRepository.save(winegrower);
>>>>>>> feature/winemakingprocess
        });
    }

    @Transactional
    @Override
    public void physicallyDelete(DeleteWinegrowerCommand command) {
<<<<<<< HEAD
<<<<<<< HEAD
        vinegrowerRepository.deleteById(command.vinegrowerId());
=======
        winegrowerRepository.deleteById(command.winegrowerId());
>>>>>>> develop
=======
        winegrowerRepository.deleteById(command.winegrowerId());
>>>>>>> feature/winemakingprocess
    }

    @Transactional
    @Override
    public Optional<Winegrower> activate(ActivateWinegrowerCommand command) {
<<<<<<< HEAD
<<<<<<< HEAD
        return vinegrowerRepository.findById(command.vinegrowerId()).map(vinegrower -> {
            vinegrower.setStatus(EmployeeStatus.ACTIVE);
            return vinegrowerRepository.save(vinegrower);
=======
        return winegrowerRepository.findById(command.winegrowerId()).map(winegrower -> {
            winegrower.setStatus(EmployeeStatus.ACTIVE);
            return winegrowerRepository.save(winegrower);
>>>>>>> develop
=======
        return winegrowerRepository.findById(command.winegrowerId()).map(winegrower -> {
            winegrower.setStatus(EmployeeStatus.ACTIVE);
            return winegrowerRepository.save(winegrower);
>>>>>>> feature/winemakingprocess
        });
    }
}
