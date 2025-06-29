package com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class BlockchainGlobalExceptionHandler {

    @ExceptionHandler(TransactionNotBeCreated.class)
    public ResponseEntity<Map<String, Object>> handleTransactionNotBeCreated(TransactionNotBeCreated ex) {
        Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("timestamp", Instant.now());
        errorBody.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorBody.put("error", "Transaction Creation Failed");
        errorBody.put("message", ex.getMessage());
        errorBody.put("operation", ex.getOperation());
        errorBody.put("details", ex.getDetails());

        if (ex.getCause() != null) {
            errorBody.put("rootCause", ex.getCause().getMessage());
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorBody);
    }

    @ExceptionHandler(TransactionNotBeVerified.class)
    public ResponseEntity<Map<String, Object>> handleTransactionNotVerified(TransactionNotBeVerified ex) {
        Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("timestamp", Instant.now());
        errorBody.put("status", HttpStatus.BAD_REQUEST.value());
        errorBody.put("error", "Transaction Verification Failed");
        errorBody.put("message", ex.getMessage());
        errorBody.put("transactionHash", ex.getTransactionHash());
        errorBody.put("reason", ex.getReason());

        if (ex.getCause() != null) {
            errorBody.put("rootCause", ex.getCause().getMessage());
        }

        return ResponseEntity.badRequest().body(errorBody);
    }

    @ExceptionHandler(TransactionDetailsNotBeRetrieved.class)
    public ResponseEntity<Map<String, Object>> handleTransactionDetailsNotRetrieved(TransactionDetailsNotBeRetrieved ex) {
        Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("timestamp", Instant.now());
        errorBody.put("status", HttpStatus.NOT_FOUND.value());
        errorBody.put("error", "Transaction Details Not Found");
        errorBody.put("message", ex.getMessage());
        errorBody.put("transactionHash", ex.getTransactionHash());
        errorBody.put("resource", ex.getResource());

        if (ex.getCause() != null) {
            errorBody.put("rootCause", ex.getCause().getMessage());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorBody);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        fieldError -> fieldError.getDefaultMessage() != null ?
                                fieldError.getDefaultMessage() : "Validation failed"
                ));

        Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("timestamp", Instant.now());
        errorBody.put("status", HttpStatus.BAD_REQUEST.value());
        errorBody.put("error", "Validation Error");
        errorBody.put("message", "Validation failed for request");
        errorBody.put("validationErrors", errors);

        return ResponseEntity.badRequest().body(errorBody);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleAllUncaughtException(Exception ex) {
        Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("timestamp", Instant.now());
        errorBody.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorBody.put("error", "Internal Server Error");
        errorBody.put("message", "An unexpected error occurred");
        errorBody.put("details", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorBody);
    }
}