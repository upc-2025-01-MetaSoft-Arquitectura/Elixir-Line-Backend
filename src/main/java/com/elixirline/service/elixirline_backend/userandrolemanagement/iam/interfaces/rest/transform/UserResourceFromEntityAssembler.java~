package com.elixirline.service.elixirline_backend.userandrolemanagement.iam.interfaces.rest.transform;

import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.domain.model.aggregates.User;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.domain.model.entities.Role;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User user) {
        String role = user.getRole() != null ? user.getRole().getStringName() : null;
        return new UserResource(user.getUserId(), user.getEmail(), role);
    }
}
