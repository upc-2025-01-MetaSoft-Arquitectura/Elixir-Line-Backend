package com.elixirline.service.elixirline_backend.userandrolemanagement.iam.application.internal.queryservices;

import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.domain.model.aggregates.User;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.domain.model.queries.GetAllUsersQuery;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.domain.model.queries.GetUserByIdQuery;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.domain.model.queries.GetUserByEmailQuery;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.domain.services.UserQueryService;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link UserQueryService} interface.
 * @version 1.0
 */
@Service
public class UserQueryServiceImpl implements UserQueryService {
    private final UserRepository userRepository;
    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * This method is used to handle {@link GetAllUsersQuery} query.
     * @param query {@link GetAllUsersQuery} instance.
     * @return {@link List} of {@link User} instances.
     */
    @Override
    public List<User> handle(GetAllUsersQuery query) {
        return userRepository.findAll();
    }

    /**
     * This method is used to handle {@link GetUserByIdQuery} query.
     * @param query {@link GetUserByIdQuery} instance.
     * @return {@link Optional} of {@link User} instance.
     */
    @Override
    public Optional<User> handle(GetUserByIdQuery query) {
        return userRepository.findById(query.userId());
    }

    /**
     * This method is used to handle {@link GetUserByEmailQuery} query.
     * @param query {@link GetUserByEmailQuery} instance.
     * @return {@link Optional} of {@link User} instance.
     */
    @Override
    public Optional<User> handle(GetUserByEmailQuery query) {
        return userRepository.findByEmail(query.email());
    }
}