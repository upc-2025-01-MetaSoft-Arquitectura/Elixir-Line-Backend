package com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.resources;

import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Nullable;

public record UpdateFieldWorkerResource(
        @Nullable String name,
        @Nullable String lastname,
        @Nullable String phoneNumber,
        @Nullable Long winegrowerId,
        @Nullable MultipartFile image
) { }
