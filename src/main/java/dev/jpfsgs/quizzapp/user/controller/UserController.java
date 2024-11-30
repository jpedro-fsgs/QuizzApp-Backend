package dev.jpfsgs.quizzapp.user.controller;

import dev.jpfsgs.quizzapp.user.dto.request.RegisterUserDTO;
import dev.jpfsgs.quizzapp.user.dto.response.UserDTO;
import dev.jpfsgs.quizzapp.user.model.User;
import dev.jpfsgs.quizzapp.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/")
    public String index() {
        return "Hello User";
    }

    @GetMapping("/all")
    @Operation(summary = "Get all Users")
    public ResponseEntity<List<UserDTO>> all() {
        List<UserDTO> users = userService.findAll();
        HttpStatus status = users.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return ResponseEntity.status(status).body(users);
    }

    @PostMapping("/register")
    @Operation(summary = "Register an User")
    public ResponseEntity<UserDTO> save(@Valid @RequestBody RegisterUserDTO user) {
        UserDTO newUser = userService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
}
