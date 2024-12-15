package dev.jpfsgs.quizzapp.user.controller;

import dev.jpfsgs.quizzapp.exception.ErrorDetails;
import dev.jpfsgs.quizzapp.user.dto.request.RegisterUserDTO;
import dev.jpfsgs.quizzapp.user.dto.response.UserDTO;
import dev.jpfsgs.quizzapp.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "Users", description = "Controller to manage Users")
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    @Operation(summary = "Get all users", description = "Retrieve a list of all users.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of users.")
    @ApiResponse(responseCode = "204", description = "No users found.")
    public ResponseEntity<List<UserDTO>> allUsers() {
        List<UserDTO> users = userService.findAll();
        HttpStatus status = users.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return ResponseEntity.status(status).body(users);
    }

    @GetMapping("/all/active")
    @Operation(summary = "Get all active users", description = "Retrieve a list of all active users.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of active users.")
    @ApiResponse(responseCode = "204", description = "No active users found.")
    public ResponseEntity<List<UserDTO>> allActiveUsers() {
        List<UserDTO> users = userService.findActiveUsers();
        HttpStatus status = users.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return ResponseEntity.status(status).body(users);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get User by ID", description = "Retrieve a user by their unique ID.")
    @ApiResponse(responseCode = "200", description = "User successfully found.")
    @ApiResponse(responseCode = "404", description = "User not found.", content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = ErrorDetails.class)
    ))
    public UserDTO findById(@PathVariable String id) {
        return userService.getUserById(UUID.fromString(id));
    }

    @GetMapping("/{email}")
    @Operation(summary = "Get User by Email", description = "Retrieve a user by their email address.")
    @ApiResponse(responseCode = "200", description = "User successfully found.")
    @ApiResponse(responseCode = "404", description = "User not found.", content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = ErrorDetails.class)
    ))
    public UserDTO findByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @PostMapping("/register")
    @Operation(summary = "Register new Users", description = "Create a new user in the system.")
    @ApiResponse(responseCode = "201", description = "User successfully registered.")
    @ApiResponse(responseCode = "409", description = "User already registered.", content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = ErrorDetails.class)
    ))
    public ResponseEntity<UserDTO> save(@Valid @RequestBody RegisterUserDTO user) {
        UserDTO newUser = userService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
}
