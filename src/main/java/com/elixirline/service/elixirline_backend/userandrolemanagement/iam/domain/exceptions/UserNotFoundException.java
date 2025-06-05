package com.elixirline.service.elixirline_backend.userandrolemanagement.iam.domain.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long userId) {
        String message = String.format("User with id %d not found", userId);
        super(message);
    }
}
