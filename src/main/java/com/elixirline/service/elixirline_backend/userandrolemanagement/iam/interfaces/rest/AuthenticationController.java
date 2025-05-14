package com.elixirline.service.elixirline_backend.userandrolemanagement.iam.interfaces.rest;

import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.domain.model.commands.SignInCommand;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.domain.model.queries.GetUserByUsernameQuery;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.domain.services.UserCommandService;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.domain.services.UserQueryService;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.infrastructure.tokens.jwt.BearerTokenService;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.interfaces.rest.resources.AuthenticatedUserResource;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.interfaces.rest.resources.SignInResource;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.interfaces.rest.resources.SignUpResource;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.interfaces.rest.resources.UserResource;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.interfaces.rest.transform.AuthenticatedUserResourceFromEntityAssembler;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.interfaces.rest.transform.SignInCommandFromResourceAssembler;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.interfaces.rest.transform.SignUpCommandFromResourceAssembler;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Authentication Controller
 * This controller is responsible for handling all the requests related to authentication
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/api/v1/authentication", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Authentication", description = "Authentication Endpoints")
public class AuthenticationController {
    private final UserCommandService userCommandService;
    private final BearerTokenService tokenService;
    private final UserQueryService userQueryService;

    public AuthenticationController(UserCommandService userCommandService, BearerTokenService tokenService, UserQueryService userQueryService) {
        this.userCommandService = userCommandService;
        this.tokenService = tokenService;
        this.userQueryService = userQueryService;
    }

    /**
     * Handles the sign-in request
     * @param signInResource the sign-in request body
     * @return the authenticated user resource
     */
    @PostMapping("/sign-in")
    public ResponseEntity<AuthenticatedUserResource> signIn(@RequestBody SignInResource signInResource) {
        var signInCommand = SignInCommandFromResourceAssembler.toCommandFromResource(signInResource);
        var authenticatedUser = userCommandService.handle(signInCommand);
        if(authenticatedUser.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var authenticateUserResource = AuthenticatedUserResourceFromEntityAssembler.toResourceFromEntity(authenticatedUser.get().getLeft(), authenticatedUser.get().getRight());
        return ResponseEntity.ok(authenticateUserResource);
    }

    /**
     * Handles the sign-up request
     * @param signUpResource the sign-up request body
     * @return the created user resource
     */
    @PostMapping("/sign-up")
    public ResponseEntity<AuthenticatedUserResource> signUp(@RequestBody SignUpResource signUpResource) {
        var signUpCommand = SignUpCommandFromResourceAssembler.toCommandFromResource(signUpResource);
        var user = userCommandService.handle(signUpCommand);

        if (user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var signInCommand = new SignInCommand(signUpResource.username(), signUpResource.password());
        var authenticatedUser = userCommandService.handle(signInCommand);

        if (authenticatedUser.isEmpty()) {
            return ResponseEntity.internalServerError().build();
        }

        var authenticatedUserResource = AuthenticatedUserResourceFromEntityAssembler.toResourceFromEntity(
                authenticatedUser.get().getLeft(), authenticatedUser.get().getRight());
        return new ResponseEntity<>(authenticatedUserResource, HttpStatus.CREATED);
    }

    @GetMapping("/me")
    public ResponseEntity<UserResource> getCurrentUser(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");

        String username = tokenService.getUsernameFromToken(token);
        var user = userQueryService.handle(new GetUserByUsernameQuery(username));
        return user.map(value -> ResponseEntity.ok(UserResourceFromEntityAssembler.toResourceFromEntity(value))).orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @PostMapping("/validateToken")
    public ResponseEntity<Boolean> validateToken(@RequestHeader("Authorization") String token) {
        boolean isValid = tokenService.validateToken(token);
        return ResponseEntity.ok(isValid);
    }
}