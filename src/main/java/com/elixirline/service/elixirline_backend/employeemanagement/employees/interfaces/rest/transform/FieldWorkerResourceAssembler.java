package com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.transform;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.aggregates.FieldWorker;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.interfaces.rest.resources.FieldWorkerResource;

public class FieldWorkerResourceAssembler {
    public static FieldWorkerResource toResource(FieldWorker fieldWorker) {
        return new FieldWorkerResource(
<<<<<<< Updated upstream
                fieldWorker.getUserId(),
                fieldWorker.getFullName().fullname(),
=======
                fieldWorker.getFieldWorkerId(),
>>>>>>> Stashed changes
                fieldWorker.getUserId(),
                fieldWorker.getName(),
                fieldWorker.getLastname(),
                fieldWorker.getPhoneNumber(),
                fieldWorker.getProfilePicture(),
                fieldWorker.getVinegrowerId()
        );
    }
}

