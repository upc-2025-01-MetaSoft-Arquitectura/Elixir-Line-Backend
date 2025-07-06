package com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.valueobjects;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record ProfilePicture(String url) {
    private static final String DEFAULT_PROFILE_PICTURE_URL = "https://img.freepik.com/free-vector/blue-circle-with-white-user_78370-4707.jpg?semt=ais_hybrid&w=740";
    public ProfilePicture {
        if (url == null || url.isBlank()) {
            url = DEFAULT_PROFILE_PICTURE_URL;
        }
    }

    public ProfilePicture() {
        this(DEFAULT_PROFILE_PICTURE_URL);
    }

    @JsonValue
    public String getUrl() {
        return url;
    }
}
