package com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.exceptions;

public class FieldWorkerNotFoundException extends RuntimeException{
    public FieldWorkerNotFoundException(Long along) {
        super("Could not find field worker " + along);
    }
}
