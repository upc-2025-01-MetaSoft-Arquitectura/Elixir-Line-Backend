package com.elixirline.service.elixirline_backend.shared.domain.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiErrorResponse {
    private String status; // "ERROR", "ADVERTENCIA", etc.
    private String message; // Descripción del error
    private List<String> details; // Errores posibles u observaciones
}
