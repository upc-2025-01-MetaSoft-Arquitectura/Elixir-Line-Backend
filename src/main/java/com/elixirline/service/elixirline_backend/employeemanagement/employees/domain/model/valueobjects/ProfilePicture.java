package com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.valueobjects;

public record ProfilePicture(String url) {
    public ProfilePicture {
        if (url == null || url.isBlank()) {
            throw new IllegalArgumentException("Profile picture URL cannot be null or empty");
        }
    }
}
