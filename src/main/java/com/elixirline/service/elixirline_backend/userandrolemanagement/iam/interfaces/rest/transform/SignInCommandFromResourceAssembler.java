package com.elixirline.service.elixirline_backend.userandrolemanagement.iam.interfaces.rest.transform;

import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.domain.model.commands.SignInCommand;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource signInResource) {
        return new SignInCommand(signInResource.email(), signInResource.password());
    }
}