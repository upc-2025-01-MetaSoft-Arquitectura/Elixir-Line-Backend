package com.elixirline.service.elixirline_backend.employeemanagement.employees.application.internal.commandservices;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.aggregates.Vinegrower;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.commands.CreateVinegrowerCommand;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.services.vinegrower.VinegrowerCommandService;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.infrastructure.persistance.jpa.repositories.VinegrowerRepository;
import org.springframework.stereotype.Service;

@Service
public class VinegrowerCommandServiceImpl  implements VinegrowerCommandService{
    private final VinegrowerRepository vinegrowerRepository;

    public VinegrowerCommandServiceImpl(VinegrowerRepository vinegrowerRepository) {
        this.vinegrowerRepository = vinegrowerRepository;
    }

    @Override
    public Vinegrower handle(CreateVinegrowerCommand command) {
        Vinegrower vinegrower = new Vinegrower(command.userId(), command.fullName(), command.country());
        return vinegrowerRepository.save(vinegrower);
    }
}
