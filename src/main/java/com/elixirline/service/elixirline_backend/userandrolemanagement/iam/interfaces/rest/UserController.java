package com.elixirline.service.elixirline_backend.userandrolemanagement.iam.interfaces.rest;

import com.elixirline.service.elixirline_backend.shared.domain.model.entities.ApiResponseDto;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.application.internal.commandservices.UserCommandServiceImpl;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.domain.model.commands.DeleteUserCommand;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.domain.model.queries.GetAllUsersQuery;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.domain.model.queries.GetUserByEmailQuery;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.domain.model.queries.GetUserByIdQuery;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.domain.services.UserQueryService;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.interfaces.rest.resources.UpdateUserPasswordResource;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.interfaces.rest.resources.UpdateUserResource;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.interfaces.rest.resources.UserResource;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.interfaces.rest.transform.UpdateUserCommandFromEntityAssembler;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.interfaces.rest.transform.UpdateUserPasswordCommandFromResourceAssembler;
import com.elixirline.service.elixirline_backend.userandrolemanagement.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.util.ErrorHandler;
import io.swagger.v3.oas.annotations.Parameter;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Users", description = "User Management Endpoints")
public class UserController {
    private final UserQueryService userQueryService;
    private final UserCommandServiceImpl userCommandService;

    public UserController(UserQueryService userQueryService, UserCommandServiceImpl userCommandService, UserCommandServiceImpl userCommandService1) {
        this.userQueryService = userQueryService;
        this.userCommandService = userCommandService1;
    }




    /*GET: api/v1/users*/
    @Operation(
            summary = "Get all users",
            description = "Recupera una lista de todos los usuarios disponibles en el sistema.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operación exitosa. Se devuelve una lista de usuarios.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = UserResource.class),
                                    examples = @ExampleObject(
                                            name = "Ejemplo de usuarios recuperados",
                                            summary = "Respuesta exitosa con una lista de usuarios",
                                            value = """
                                            [
                                              {
                                                "id": 1,
                                                "email": "user1@example.com",
                                                "roles": "VINEGROWER"
                                              },
                                              {
                                                "id": 2,
                                                "email": "user2@example.com",
                                                "roles": "FIELD_WORKER"
                                              }
                                            ]
                                            """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorHandler.class),
                                    examples = @ExampleObject(
                                            name = "Error inesperado",
                                            summary = "Error al recuperar los usuarios",
                                            value = """
                                            {
                                              "status": "ERROR",
                                              "message": "No se pudo recuperar la lista de usuarios.",
                                              "details": [
                                                "Error en el servicio de consulta de usuarios.",
                                                "Revisar logs del backend para más detalles."
                                              ]
                                            }
                                            """
                                    )
                            )
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<UserResource>> getAllUsers(){
        var getAllUsersQuery = new GetAllUsersQuery();
        var users= userQueryService.handle(getAllUsersQuery);
        var userResources= users.stream().map(
                UserResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(userResources);
    }




    /*GET: api/v1/users/{userId}*/
    @Operation(
            summary = "Get user by ID",
            description = "Recupera un usuario específico utilizando su ID.",
            parameters = {
                    @Parameter(
                            name = "userId",
                            description = "El ID del usuario a recuperar.",
                            required = true,
                            schema = @Schema(type = "integer", format = "int64")
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operación exitosa. Se devuelve el usuario solicitado.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = UserResource.class),
                                    examples = @ExampleObject(
                                            name = "Ejemplo de usuario recuperado",
                                            summary = "Respuesta exitosa con los detalles del usuario",
                                            value = """
                                            {
                                              "id": 1,
                                              "email": "user1@example.com",
                                              "roles": "VINEGROWER"
                                            }
                                            """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Usuario no encontrado. No existe un usuario con el ID proporcionado.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorHandler.class),
                                    examples = @ExampleObject(
                                            name = "Error de usuario no encontrado",
                                            summary = "Respuesta cuando no se encuentra el usuario",
                                            value = """
                                            {
                                              "status": "ERROR",
                                              "message": "Usuario no encontrado.",
                                              "details": [
                                                "No existe un usuario con el ID proporcionado."
                                              ]
                                            }
                                            """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorHandler.class),
                                    examples = @ExampleObject(
                                            name = "Error inesperado",
                                            summary = "Error al recuperar el usuario con el ID proporcionado",
                                            value = """
                                            {
                                              "status": "ERROR",
                                              "message": "No se pudo recuperar el usuario con el ID proporcionado.",
                                              "details": [
                                                "Error en el servicio de consulta de usuarios.",
                                                "Revisar logs del backend para más detalles."
                                              ]
                                            }
                                            """
                                    )
                            )
                    )
            }
    )
    @GetMapping(value = "/id/{userId}")
    public ResponseEntity<UserResource> getUserById(@PathVariable Long userId) {
        var getUserByIdQuery = new GetUserByIdQuery(userId);
        var user = userQueryService.handle(getUserByIdQuery);
        return user.map(value -> ResponseEntity.ok(
                UserResourceFromEntityAssembler.toResourceFromEntity(value)
        )).orElseGet(() -> ResponseEntity.notFound().build());
    }




    /*GET: api/v1/users/email/{email}*/
    @Operation(
            summary = "Get user by email",
            description = "Recupera un usuario específico utilizando su correo electrónico.",
            parameters = {
                    @Parameter(
                            name = "email",
                            description = "El correo electrónico del usuario a recuperar.",
                            required = true,
                            schema = @Schema(type = "string", format = "email")
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operación exitosa. Se devuelve el usuario solicitado.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = UserResource.class),
                                    examples = @ExampleObject(
                                            name = "Ejemplo de usuario recuperado por email",
                                            summary = "Respuesta exitosa con los detalles del usuario",
                                            value = """
                                            {
                                              "id": 1,
                                              "email": "user1@example.com",
                                              "roles": "VINEGROWER"
                                            }
                                            """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Usuario no encontrado. No existe un usuario con el correo electrónico proporcionado.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorHandler.class),
                                    examples = @ExampleObject(
                                            name = "Error de usuario no encontrado por email",
                                            summary = "Respuesta cuando no se encuentra el usuario",
                                            value = """
                                            {
                                              "status": "ERROR",
                                              "message": "Usuario no encontrado.",
                                              "details": [
                                                "No existe un usuario con el correo electrónico proporcionado."
                                              ]
                                            }
                                            """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorHandler.class),
                                    examples = @ExampleObject(
                                            name = "Error inesperado",
                                            summary = "Error al recuperar el usuario con el email proporcionado",
                                            value = """
                                            {
                                              "status": "ERROR",
                                              "message": "No se pudo recuperar el usuario con el email proporcionado.",
                                              "details": [
                                                "Error en el servicio de consulta de usuarios.",
                                                "Revisar logs del backend para más detalles."
                                              ]
                                            }
                                            """
                                    )
                            )
                    )
            }
    )
    @GetMapping(value = "/email/{email}")
    public ResponseEntity<UserResource> getUserByEmail(@PathVariable String email) {
        var getUserByEmailQuery = new GetUserByEmailQuery(email);
        var user = userQueryService.handle(getUserByEmailQuery);
        return user.map(value -> ResponseEntity.ok(
                UserResourceFromEntityAssembler.toResourceFromEntity(value)
        )).orElseGet(() -> ResponseEntity.notFound().build());
    }



    /*PUT: api/v1/users/{userId}*/
    @Operation(
            summary = "Update user",
            description = "Actualiza un usuario existente utilizando su ID y los datos proporcionados.",
            parameters = {
                    @Parameter(
                            name = "userId",
                            description = "El ID del usuario a actualizar.",
                            required = true,
                            schema = @Schema(type = "integer", format = "int64")
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del usuario a actualizar",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UpdateUserResource.class)
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operación exitosa. Se devuelve el usuario actualizado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserResource.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de usuario actualizado",
                                    summary = "Respuesta exitosa con los detalles del usuario actualizado",
                                    value = """
                                    {
                                      "id": 1,
                                      "email": "updated_user@example.com",
                                      "roles": "VINEGROWER"
                                    }
                                    """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuario no encontrado. No existe un usuario con el ID proporcionado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorHandler.class),
                            examples = @ExampleObject(
                                    name = "Error de usuario no encontrado",
                                    summary = "Respuesta cuando no se encuentra el usuario",
                                    value = """
                                    {
                                      "status": "ERROR",
                                      "message": "Usuario no encontrado.",
                                      "details": [
                                        "No existe un usuario con el ID proporcionado."
                                      ]
                                    }
                                    """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorHandler.class),
                            examples = @ExampleObject(
                                    name = "Error inesperado",
                                    summary = "Error al actualizar el usuario con el ID proporcionado",
                                    value = """
                                            {
                                              "status": "ERROR",
                                              "message": "No se pudo actualizar el usuario con el ID proporcionado.",
                                              "details": [
                                                "Error en el servicio de consulta de actualización de usuarios.",
                                                "Revisar logs del backend para más detalles."
                                              ]
                                            }
                                            """
                            )
                    )
            )
    })
    @PutMapping(value = "/{userId}")
    public ResponseEntity<UserResource> updateUser(@PathVariable Long userId, @RequestBody UpdateUserResource userResource) {
        var command = UpdateUserCommandFromEntityAssembler.toCommandFromResource(userId, userResource);
        var updatedUser = userCommandService.handle(command);

        return updatedUser.map(value -> ResponseEntity.ok(
                        UserResourceFromEntityAssembler.toResourceFromEntity(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }




    /*PUT: api/v1/users/{userId}/password*/
    @Operation(
            summary = "Update user password",
            description = "Actualiza la contraseña de un usuario existente utilizando su ID.",
            parameters = {
                    @Parameter(
                            name = "userId",
                            description = "El ID del usuario cuya contraseña se actualizará.",
                            required = true,
                            schema = @Schema(type = "integer", format = "int64")
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos de la nueva contraseña",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UpdateUserPasswordResource.class)
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operación exitosa. La contraseña se actualizó correctamente.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseDto.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de respuesta de éxito",
                                    summary = "Respuesta exitosa al actualizar la contraseña",
                                    value = """
                                    {
                                      "status": "success",
                                      "response": {
                                        "message": "Password updated successfully"
                                      }
                                    }
                                    """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuario no encontrado. No existe un usuario con el ID proporcionado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorHandler.class),
                            examples = @ExampleObject(
                                    name = "Error de usuario no encontrado",
                                    summary = "Respuesta cuando no se encuentra el usuario",
                                    value = """
                                    {
                                      "status": "ERROR",
                                      "message": "Usuario no encontrado.",
                                      "details": [
                                        "No existe un usuario con el ID proporcionado."
                                      ]
                                    }
                                    """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorHandler.class),
                            examples = @ExampleObject(
                                    name = "Error inesperado",
                                    summary = "Error al actualizar el password con el ID de usuario proporcionado",
                                    value = """
                                            {
                                              "status": "ERROR",
                                              "message": "No se pudo actualizar el password con el ID de usuario proporcionado.",
                                              "details": [
                                                "Error en el servicio de consulta de actualización de contraseña.",
                                                "Revisar logs del backend para más detalles."
                                              ]
                                            }
                                            """
                            )
                    )
            )
    })
    @PutMapping(value = "/{userId}/password")
    public ResponseEntity<ApiResponseDto> updateUserPassword(@PathVariable Long userId, @RequestBody UpdateUserPasswordResource userResource) {
        var command = UpdateUserPasswordCommandFromResourceAssembler.toCommandFromResource(userId, userResource);
        userCommandService.handle(command);

        return ResponseEntity.ok(ApiResponseDto.successWithMessage("Password updated successfully"));
    }




    /*DELETE: api/v1/users/{userId}*/
    @Operation(
            summary = "Delete user",
            description = "Elimina un usuario existente utilizando su ID.",
            parameters = {
                    @Parameter(
                            name = "userId",
                            description = "El ID del usuario a eliminar.",
                            required = true,
                            schema = @Schema(type = "integer", format = "int64")
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Operación exitosa. El usuario fue eliminado correctamente.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResponseDto.class),
                                    examples = @ExampleObject(
                                            name = "Ejemplo de respuesta de éxito",
                                            summary = "Respuesta exitosa al eliminar el usuario",
                                            value = """
                                            {
                                              "status": "success",
                                              "response": {
                                                "message": "User  deleted successfully"
                                              }
                                            }
                                            """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Usuario no encontrado. No existe un usuario con el ID proporcionado.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorHandler.class),
                                    examples = @ExampleObject(
                                            name = "Error de usuario no encontrado",
                                            summary = "Respuesta cuando no se encuentra el usuario",
                                            value = """
                                            {
                                              "status": "ERROR",
                                              "message": "Usuario no encontrado.",
                                              "details": [
                                                "No existe un usuario con el ID proporcionado."
                                              ]
                                            }
                                            """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorHandler.class),
                                    examples = @ExampleObject(
                                            name = "Error inesperado",
                                            summary = "Error al eliminar el usuario con el ID proporcionado",
                                            value = """
                                            {
                                              "status": "ERROR",
                                              "message": "No se pudo eliminar el usuario con el ID proporcionado.",
                                              "details": [
                                                "Error en el servicio de consulta de eliminación de usuario.",
                                                "Revisar logs del backend para más detalles."
                                              ]
                                            }
                                            """
                                    )
                            )
                    )
            }
    )
    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<ApiResponseDto> deleteUser(@PathVariable Long userId) {
        var command = new DeleteUserCommand(userId);
        userCommandService.handle(command);
        return ResponseEntity.ok(ApiResponseDto.successWithMessage("User deleted successfully"));
    }



}