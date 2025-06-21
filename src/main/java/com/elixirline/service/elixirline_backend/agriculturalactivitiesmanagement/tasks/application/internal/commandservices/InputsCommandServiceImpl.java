package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.application.internal.commandservices;

import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.aggregates.Inputs;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.commands.CreateInputsCommand;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.commands.DeleteInputsCommand;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.commands.UpdateInputsCommand;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.services.InputsCommandService;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.infrastructure.persistance.jpa.repositories.InputsRepository;
import com.elixirline.service.elixirline_backend.shared.infrastructure.storage.AzureBlobService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InputsCommandServiceImpl implements InputsCommandService {
    private InputsRepository inputsRepository;
    private AzureBlobService azureBlobService;
    public InputsCommandServiceImpl(InputsRepository inputsRepository, AzureBlobService azureBlobService) {
        this.inputsRepository = inputsRepository;
        this.azureBlobService = azureBlobService;
    }

    @Override
    public Optional<Inputs> handle(CreateInputsCommand command) {
        String imageUrl = azureBlobService.upload(command.imageFile());
        var inputs = new Inputs(command.name(), command.description(), command.quantity(), command.unit(), imageUrl);
        inputsRepository.save(inputs);
        return Optional.of(inputs);
    }

    @Override
    public Optional<Inputs> handle(UpdateInputsCommand command) {

        Optional<Inputs> existing = inputsRepository.findById(command.inputsId());
        if (existing.isEmpty()) return Optional.empty();

        Inputs input = existing.get();
        input.updateInformation(command.name(), command.description(), command.quantity(), command.unit());

        if (command.image() != null && !command.image().isEmpty()) {
            String imageUrl = azureBlobService.upload(command.image());
            input.updateImage(imageUrl);
        }
        inputsRepository.save(input);
        return Optional.of(input);
    }

    @Override
    public void handle(DeleteInputsCommand command) {
        if(!inputsRepository.existsById(command.InputsId())){
            throw new IllegalArgumentException("Input does not exist");
        }
        try{
            inputsRepository.deleteById(command.InputsId());
        }catch (Exception ex){
            throw new IllegalArgumentException("Error deleting input " + command.InputsId());
        }
    }
}
