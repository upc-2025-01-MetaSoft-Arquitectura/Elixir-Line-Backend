package com.elixirline.service.elixirline_backend.userandrolemanagement.iam.interfaces.rest.resources;

import java.util.List;

public record SignUpResource(String email, String password , List<String> roles) {
}
