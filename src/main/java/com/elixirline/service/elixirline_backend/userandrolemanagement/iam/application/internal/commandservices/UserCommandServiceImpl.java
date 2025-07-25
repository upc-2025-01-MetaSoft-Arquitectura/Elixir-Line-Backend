package com.elixirline.service.elixirline_backend.userandrolemanagement.iam.application.internal.commandservices;

import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.application.internal.outboundservices.hashing.HashingService;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.application.internal.outboundservices.tokens.TokenService;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.domain.exceptions.UserNotFoundException;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.domain.model.aggregates.User;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.domain.model.commands.*;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.domain.model.entities.Role;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.domain.model.valueobjects.Roles;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.domain.services.UserCommandService;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * User command service implementation
 * <p>
 *     This class implements the {@link UserCommandService} interface and provides the implementation for the
 *     {@link SignInCommand} and {@link SignUpCommand} commands.
 * </p>
 * @version 1.0.0
 */

@Service
public class UserCommandServiceImpl implements UserCommandService {
    private final UserRepository userRepository;
    private final HashingService hashingService;
    private final TokenService tokenService;
    private final RoleRepository roleRepository;

    public UserCommandServiceImpl(UserRepository userRepository, HashingService hashingService, TokenService tokenService, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
        this.roleRepository = roleRepository;
    }

    /**
     * Handle the sign-in command
     * <p>
     *     This method handles the {@link SignInCommand} command and returns the user and the token.
     *     If the user is not found or the password is invalid, a {@link RuntimeException} will be thrown.
     * </p>
     * @param command the sign-in command containing the username and password
     * @return and optional containing the user matching the username and the generated token
     * @throws RuntimeException if the user is not found or the password is invalid
     */
    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        var user = userRepository.findByEmail(command.email());

        if(user.isEmpty())
            throw new RuntimeException("User not found");

        if(!hashingService.matches(command.password(), user.get().getPassword()))
            throw new RuntimeException("Invalid password");

        var token = tokenService.generateToken(user.get().getEmail());
        return Optional.of(ImmutablePair.of(user.get(), token));
    }

    /**
     * Handle the sign-up command
     * <p>
     *     This method handles the {@link SignUpCommand} command and creates a new user.
     * </p>
     * @param command the sign-up command containing the username and password
     * @return an optional containing the created user
     */
    @Override
    public Optional<User> handle(SignUpCommand command) {
        if(userRepository.existsByEmail(command.email())) {
            throw new RuntimeException("Username already exists");
        }

        Role role = command.role() != null ?
                roleRepository.findByName(command.role().getName())
                        .orElseThrow(() -> new RuntimeException("Role not found")) :
                roleRepository.findByName(Roles.WINEGROWER)
                        .orElseThrow(() -> new RuntimeException("Default role not found"));

        var user = new User(command.email(), hashingService.encode(command.password()), role);
        userRepository.save(user);

        return userRepository.findByEmail(command.email());
    }

    @Override
    public Optional<User> handle(UpdateUserCommand command) {
        User user = userRepository.findById(command.userId())
                .orElseThrow(() -> new UserNotFoundException(command.userId()));

        if (command.email() != null) {
            user.setEmail(command.email());
        }

        if (command.password() != null) {
            user.setPassword(hashingService.encode(command.password()));
        }

        return Optional.of(userRepository.save(user));
    }

    @Override
    public Optional<User> handle(UpdateUserPasswordCommand command) {
        User user = userRepository.findById(command.userId())
                .orElseThrow(() -> new UserNotFoundException(command.userId()));

        user.updateUserPassword(hashingService.encode(command.password()));
        return Optional.of(userRepository.save(user));
    }

    @Transactional
    @Override
    public void handle(DeleteUserCommand command) {
        User user = userRepository.findById(command.userId())
                .orElseThrow(() -> new UserNotFoundException(command.userId()));
        userRepository.delete(user);
    }
}
