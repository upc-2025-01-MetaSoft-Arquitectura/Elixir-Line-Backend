package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.application.internal.commandservices;

import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.aggregates.Inputs;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.commands.CreateInputsCommand;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.commands.DeleteInputsCommand;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.commands.PatchInputsCommand;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.commands.UpdateInputsCommand;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.services.InputsCommandService;
import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.infrastructure.persistance.jpa.repositories.InputsRepository;
import com.elixirline.service.elixirline_backend.shared.infrastructure.storage.AzureBlobService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InputsCommandServiceImpl implements InputsCommandService {
    private final InputsRepository inputsRepository;
    private final AzureBlobService azureBlobService;

    @Override
    public Optional<Inputs> handle(CreateInputsCommand command) {
        String imageUrl = azureBlobService.upload(command.imageFile());
        var inputs = new Inputs(command.name(), command.description(), command.quantity(), command.winegrowerId(), command.unit(), imageUrl);
        inputsRepository.save(inputs);
        return Optional.of(inputs);
    }

    @Transactional
    @Override
    public Optional<Inputs> handle(UpdateInputsCommand command) {
        return inputsRepository.findById(command.inputsId()).map(input -> {
            if (command.name() != null) input.setName(command.name());
            if (command.description() != null) input.setDescription(command.description());
            if (command.quantity() != null) input.setQuantity(command.quantity());
            if (command.winegrowerId() != null) input.setWinegrowerId(command.winegrowerId());
            if (command.unit() != null) input.setUnits(command.unit());

            if (command.image() != null && !command.image().isEmpty()) {
                String imageUrl = azureBlobService.upload(command.image());
                input.setImage(imageUrl);
            }
            return inputsRepository.save(input);
        });
    }

    @Override
    public Optional<Inputs> handle(PatchInputsCommand command) {
        return inputsRepository.findById(command.inputsId()).map(input -> {
            command.name().ifPresent(input::setName);
            command.description().ifPresent(input::setDescription);
            command.quantity().ifPresent(input::setQuantity);
            command.winegrowerId().ifPresent(input::setWinegrowerId);
            command.unit().ifPresent(input::setUnits);

            command.image().ifPresent(image -> {
                if (!image.isEmpty()) {
                    String imageUrl = azureBlobService.upload(image);
                    input.setImage(imageUrl);
                }
            });

            return inputsRepository.save(input);
        });
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
