package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.valueobjects.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.Embeddable;

@Embeddable
public record Comment(String comment) {
    public Comment {
        if ( comment != null && comment.length() > 255) {
            throw new IllegalArgumentException("Comment cannot be longer than 255 characters.");
        }
    }

    @JsonValue
    public String getComment() {
        return comment;
    }

    @JsonCreator
    public static Comment from(String comment) {
        return new Comment(comment);
    }
}
