package com.elixirline.service.elixirline_backend.userandrolemanagement.iam.interfaces.rest;

import com.elixirline.service.elixirline_backend.shared.domain.model.entities.ApiErrorResponse;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.domain.model.commands.SignInCommand;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.domain.model.queries.GetUserByEmailQuery;
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
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;

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

    public AuthenticationController(UserCommandService userCommandService) {
        this.userCommandService = userCommandService;
    }




    /*POST: /api/v1/authentication/sign-in*/
    @Operation(
            summary = "Sign in",
            description = "Inicia sesión con las credenciales proporcionadas. Este endpoint requiere el correo electrónico y la contraseña del usuario.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Credenciales del usuario para iniciar sesión",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SignInResource.class)
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Inicio de sesión exitoso. Se devuelve el recurso del usuario autenticado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AuthenticatedUserResource.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de usuario autenticado",
                                    summary = "Respuesta exitosa de inicio de sesión",
                                    value = """
                                    {
                                      "id": 1,
                                      "email": "user@example.com",
                                      "token": "eyJhbGciOiJIUzI1NiIsInR..."
                                    }
                                    """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuario no encontrado. Las credenciales proporcionadas son incorrectas.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Error de credenciales",
                                    summary = "Respuesta cuando las credenciales son incorrectas",
                                    value = """
                                    {
                                      "status": "ERROR",
                                      "message": "Usuario no encontrado.",
                                      "details": [
                                        "Las credenciales proporcionadas son incorrectas."
                                      ]
                                    }
                                    """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor. Ocurrió un problema al intentar iniciar sesión.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Error inesperado",
                                    summary = "Error al iniciar sesión",
                                    value = """
                                    {
                                      "status": "ERROR",
                                      "message": "No se pudo iniciar sesión.",
                                      "details": [
                                        "Error en el servicio de autenticación.",
                                        "Revisar logs del backend para más detalles."
                                      ]
                                    }
                                    """
                            )
                    )
            )
    })
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




    /*POST: /api/v1/authentication/sign-up*/
    @Operation(
            summary = "Sign up",
            description = "Registra un nuevo usuario en el sistema con las credenciales proporcionadas. Este endpoint requiere el correo electrónico, la contraseña y los roles del usuario.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del nuevo usuario para registrarse",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SignUpResource.class)
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Usuario creado correctamente. Se devuelve el recurso del usuario autenticado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AuthenticatedUserResource.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de usuario registrado",
                                    summary = "Respuesta exitosa de registro",
                                    value = """
                                    {
                                      "id": 1,
                                      "email": "newuser@example.com",
                                      "token": "eyJhbGciOiJIUzI1NiIsInR..."
                                    }
                                    """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Entrada inválida. Datos incorrectos o incompletos.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Error de validación",
                                    summary = "Faltan campos obligatorios",
                                    value = """
                                    {
                                      "status": "ERROR",
                                      "message": "Hay errores de validación en los datos enviados.",
                                      "details": [
                                        "email: no puede ser nulo",
                                        "password: no puede ser nulo",
                                        "roles: no puede ser nulo"
                                      ]
                                    }
                                    """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor. Ocurrió un problema al intentar registrar el usuario.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "Error inesperado",
                                    summary = "Error al registrar el usuario",
                                    value = """
                                    {
                                      "status": "ERROR",
                                      "message": "No se pudo registrar el usuario.",
                                      "details": [
                                        "Error en el servicio de registro.",
                                        "Revisar logs del backend para más detalles."
                                      ]
                                    }
                                    """
                            )
                    )
            )
    })
    @PostMapping("/sign-up")
    public ResponseEntity<AuthenticatedUserResource> signUp(@RequestBody SignUpResource signUpResource) {
        var signUpCommand = SignUpCommandFromResourceAssembler.toCommandFromResource(signUpResource);
        var user = userCommandService.handle(signUpCommand);

        if (user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var signInCommand = new SignInCommand(signUpResource.email(), signUpResource.password());
        var authenticatedUser = userCommandService.handle(signInCommand);

        if (authenticatedUser.isEmpty()) {
            return ResponseEntity.internalServerError().build();
        }

        var authenticatedUserResource = AuthenticatedUserResourceFromEntityAssembler.toResourceFromEntity(
                authenticatedUser.get().getLeft(), authenticatedUser.get().getRight());
        return new ResponseEntity<>(authenticatedUserResource, HttpStatus.CREATED);
    }
}