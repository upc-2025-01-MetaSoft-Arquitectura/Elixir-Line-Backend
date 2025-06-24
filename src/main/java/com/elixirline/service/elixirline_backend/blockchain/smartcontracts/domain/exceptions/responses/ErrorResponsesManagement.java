package com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.exceptions.responses;

import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.exceptions.TransactionDetailsNotBeRetrieved;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.exceptions.TransactionNotBeCreated;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.exceptions.TransactionNotBeVerified;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

public class ErrorResponsesManagement {
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(TransactionNotBeCreated.class)
    public ErrorResponse handleTransactionNotBeCreated(TransactionNotBeCreated ex) {
        return new ErrorResponse("TRANSACTION_CREATION_ERROR", ex.getMessage());
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(TransactionNotBeVerified.class)
    public ErrorResponse handleTransactionNotBeVerified(TransactionNotBeVerified ex) {
        return new ErrorResponse("TRANSACTION_VERIFICATION_ERROR", ex.getMessage());
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(TransactionDetailsNotBeRetrieved.class)
    public ErrorResponse handleTransactionDetailsNotBeRetrieved(TransactionDetailsNotBeRetrieved ex) {
        return new ErrorResponse("TRANSACTION_DETAILS_ERROR", ex.getMessage());
    }
}
