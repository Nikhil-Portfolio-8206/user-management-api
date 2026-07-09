package com.nikhil.usermanagementapi.controller;

import com.nikhil.usermanagementapi.dto.request.CreateUserRequest;
import com.nikhil.usermanagementapi.dto.response.UserResponse;
import com.nikhil.usermanagementapi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;


@RestController
@RequestMapping("/api/users")
@Tag(
        name = "User Management",
        description = "REST APIs for managing users"
)
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(
            summary = "Create a new user",
            description = "Creates a new user and stores it in the database."
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@Valid @RequestBody CreateUserRequest request) {
        return userService.createUser(request);
    }


    @Operation(
            summary = "Get user by ID",
            description = "Returns a user for the given ID."
    )
    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @Operation(
            summary = "Get all users",
            description = "Returns a list of all users."
    )
    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @Operation(
            summary = "Update user",
            description = "Updates an existing user."
    )
    @PutMapping("/{id}")
    public UserResponse updateUser(
            @PathVariable Long id,
            @Valid @RequestBody CreateUserRequest request) {

        return userService.updateUser(id, request);
    }

    @Operation(
            summary = "Delete user",
            description = "Deletes a user by ID."
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

}