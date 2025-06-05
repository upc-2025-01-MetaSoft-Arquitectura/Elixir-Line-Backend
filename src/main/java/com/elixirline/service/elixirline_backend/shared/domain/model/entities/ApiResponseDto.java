package com.elixirline.service.elixirline_backend.shared.domain.model.entities;

import java.util.Map;

public class ApiResponseDto<T> {
    private String status;
    private T response;

    public ApiResponseDto(String status, T response) {
        this.status = status;
        this.response = response;
    }

    public String getStatus() {
        return status;
    }

    public T getResponse() {
        return response;
    }

    public static <T> ApiResponseDto<T> success(T response) {
        return new ApiResponseDto<>("success", response);
    }

    public static ApiResponseDto<Map<String, String>> successWithMessage(String message) {
        return new ApiResponseDto<>("success", Map.of("message", message));
    }

    public static ApiResponseDto<Map<String, String>> error(String message) {
        return new ApiResponseDto<>("error", Map.of("message", message));
    }
}
