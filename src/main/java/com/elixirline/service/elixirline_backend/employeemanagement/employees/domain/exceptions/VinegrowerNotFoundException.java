package com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.exceptions;

public class VinegrowerNotFoundException extends RuntimeException{
    public VinegrowerNotFoundException(Long along) {
        super("Could not find vinegrower " + along);
    }
}
