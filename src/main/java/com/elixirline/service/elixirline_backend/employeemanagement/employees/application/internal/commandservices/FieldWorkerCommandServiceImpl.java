package com.elixirline.service.elixirline_backend.employeemanagement.employees.application.internal.commandservices;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.aggregates.FieldWorker;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.commands.CreateFieldWorkerCommand;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.services.fieldworker.FieldWorkerCommandService;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.infrastructure.persistance.jpa.repositories.FieldWorkerRepository;
import org.springframework.stereotype.Service;

@Service
public class FieldWorkerCommandServiceImpl implements FieldWorkerCommandService {
    private final FieldWorkerRepository fieldWorkerRepository;

    public FieldWorkerCommandServiceImpl(FieldWorkerRepository fieldWorkerRepository) {
        this.fieldWorkerRepository = fieldWorkerRepository;
    }

    @Override
    public FieldWorker handle(CreateFieldWorkerCommand command) {
        FieldWorker fieldWorker = new FieldWorker(
                command.userId(),
                command.name(),
                command.lastname(),
                command.phoneNumber(),
                command.profilePicture(),
                command.vinegrowerId()
        );
        return fieldWorkerRepository.save(fieldWorker);
    }
}
