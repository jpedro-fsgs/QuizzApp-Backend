package dev.jpfsgs.quizzapp.user.controller;

import dev.jpfsgs.quizzapp.user.dto.request.RegisterUserDTO;
import dev.jpfsgs.quizzapp.user.dto.response.UserDTO;
import dev.jpfsgs.quizzapp.user.service.UserService;
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
@Tag(name = "Users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/")
    public String index() {
        return "Hello User";
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> all() {
        List<UserDTO> users = userService.findAll();
        HttpStatus status = users.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return ResponseEntity.status(status).body(users);
    }

    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable String id) {
        return userService.getUserById(UUID.fromString(id));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> save(@Valid @RequestBody RegisterUserDTO user) {
        UserDTO newUser = userService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
}
