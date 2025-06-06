package com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.resources;

public record PartialUpdateFieldWorkerResource(
        String name,
        String lastname,
        String phoneNumber,
        String profilePicture
) {
    public boolean hasNoUpdates() {
        return name == null && lastname == null && phoneNumber == null && profilePicture == null;
    }
}
