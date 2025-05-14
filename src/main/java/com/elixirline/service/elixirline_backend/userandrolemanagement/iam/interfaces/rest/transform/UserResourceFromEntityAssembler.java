package com.elixirline.service.elixirline_backend.userandrolemanagement.iam.interfaces.rest.transform;

import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.domain.model.aggregates.User;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.domain.model.entities.Role;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User user) {
        var roles = user.getRoles().stream().map(Role::getStringName).toList();
        return new UserResource(user.getId(), user.getEmail(), roles);
    }
}
