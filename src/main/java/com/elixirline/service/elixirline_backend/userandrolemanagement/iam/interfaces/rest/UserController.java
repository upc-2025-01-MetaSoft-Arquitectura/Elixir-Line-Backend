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
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    /**
     * Get all users
     * @return list of user resources
     */
    @GetMapping
    public ResponseEntity<List<UserResource>> getAllUsers(){
        var getAllUsersQuery = new GetAllUsersQuery();
        var users= userQueryService.handle(getAllUsersQuery);
        var userResources= users.stream().map(
                UserResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(userResources);
    }

    /**
     * this method returns the user with the given id
     * @param userId the user id
     * @return the user resource with the given id
     */
    @GetMapping(value = "/id/{userId}")
    public ResponseEntity<UserResource> getUserById(@PathVariable Long userId) {
        var getUserByIdQuery = new GetUserByIdQuery(userId);
        var user = userQueryService.handle(getUserByIdQuery);
        return user.map(value -> ResponseEntity.ok(
                UserResourceFromEntityAssembler.toResourceFromEntity(value)
        )).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * this method returns the user with the given email
     * @param email the user email
     * @return the user resource with the given email
     */
    @GetMapping(value = "/email/{email}")
    public ResponseEntity<UserResource> getUserByEmail(@PathVariable String email) {
        var getUserByEmailQuery = new GetUserByEmailQuery(email);
        var user = userQueryService.handle(getUserByEmailQuery);
        return user.map(value -> ResponseEntity.ok(
                UserResourceFromEntityAssembler.toResourceFromEntity(value)
        )).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PutMapping(value = "/{userId}")
    public ResponseEntity<UserResource> updateUser(@PathVariable Long userId, @RequestBody UpdateUserResource userResource) {
        var command = UpdateUserCommandFromEntityAssembler.toCommandFromResource(userId, userResource);
        var updatedUser = userCommandService.handle(command);

        return updatedUser.map(value -> ResponseEntity.ok(
                        UserResourceFromEntityAssembler.toResourceFromEntity(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping(value = "/{userId}/password")
    public ResponseEntity<ApiResponseDto> updateUserPassword(@PathVariable Long userId, @RequestBody UpdateUserPasswordResource userResource) {
        var command = UpdateUserPasswordCommandFromResourceAssembler.toCommandFromResource(userId, userResource);
        userCommandService.handle(command);

        return ResponseEntity.ok(ApiResponseDto.successWithMessage("Password updated successfully"));
    }

    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<ApiResponseDto> deleteUser(@PathVariable Long userId) {
        var command = new DeleteUserCommand(userId);
        userCommandService.handle(command);
        return ResponseEntity.ok(ApiResponseDto.successWithMessage("User deleted successfully"));
    }
}