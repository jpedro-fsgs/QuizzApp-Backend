package dev.jpfsgs.quizzapp.user.controller;

import dev.jpfsgs.quizzapp.user.dto.request.CreateUserDTO;
import dev.jpfsgs.quizzapp.user.model.User;
import dev.jpfsgs.quizzapp.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/")
    public String index() {
        return "Hello User";
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> all() {
        List<User> users = userService.findAll();
        HttpStatus status = users.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return new ResponseEntity<>(users, status);
    }

    @PostMapping("/save")
    public ResponseEntity<User> save(@RequestBody CreateUserDTO user) {
        return null;
    }
}
