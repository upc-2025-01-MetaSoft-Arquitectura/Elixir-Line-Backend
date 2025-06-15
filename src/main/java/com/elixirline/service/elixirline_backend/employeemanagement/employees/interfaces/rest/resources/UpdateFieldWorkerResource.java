package com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.resources;

import org.springframework.web.multipart.MultipartFile;

public record UpdateFieldWorkerResource(
        String name,
        String lastname,
        String phoneNumber,
        String profilePicture,
        Long vinegrowerId,
        MultipartFile image
) { }
