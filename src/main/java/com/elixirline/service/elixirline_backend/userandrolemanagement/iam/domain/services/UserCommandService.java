package com.elixirline.service.elixirline_backend.userandrolemanagement.iam.domain.services;

import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.domain.model.aggregates.User;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.domain.model.commands.*;
import org.apache.commons.lang3.tuple.ImmutablePair;
import java.util.Optional;

public interface UserCommandService {
    Optional<ImmutablePair<User, String>> handle(SignInCommand command);
    Optional<User> handle(SignUpCommand command);
    Optional<User> handle(UpdateUserCommand command);
    Optional<User> handle(UpdateUserPasswordCommand command);
    void handle(DeleteUserCommand command);
}
