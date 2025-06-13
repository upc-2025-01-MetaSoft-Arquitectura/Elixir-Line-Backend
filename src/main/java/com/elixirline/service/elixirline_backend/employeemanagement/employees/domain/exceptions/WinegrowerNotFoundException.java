package com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.exceptions;

public class WinegrowerNotFoundException extends RuntimeException{
    public WinegrowerNotFoundException(Long along) {
        super("Could not find vinegrower " + along);
    }
}
