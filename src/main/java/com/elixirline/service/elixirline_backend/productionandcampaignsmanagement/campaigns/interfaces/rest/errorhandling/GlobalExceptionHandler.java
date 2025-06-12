package com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.interfaces.rest.errorhandling;

import com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.domain.model.exceptions.DuplicateCampaignNameException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DuplicateCampaignNameException.class)
    public ResponseEntity<Map<String, String>> handleDuplicateCampaignName(DuplicateCampaignNameException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(Map.of("message", ex.getMessage()));
    }
}
