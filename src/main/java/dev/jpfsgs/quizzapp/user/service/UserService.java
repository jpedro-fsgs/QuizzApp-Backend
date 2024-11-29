package dev.jpfsgs.quizzapp.user.service;

import dev.jpfsgs.quizzapp.user.dto.request.CreateUserDTO;
import dev.jpfsgs.quizzapp.user.exception.custom.UserAlreadyExistsException;
import dev.jpfsgs.quizzapp.user.model.User;
import dev.jpfsgs.quizzapp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(UUID id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByEmail(String username) {
        return userRepository.findByEmail(username);
    }

    public User createUser(CreateUserDTO user) {
        Optional<User> existentUser = userRepository.findByEmail(user.email());
        if(existentUser.isPresent()) {
            throw new UserAlreadyExistsException("User already exists");
        }

        User newUser = new User();
        newUser.setEmail(user.email());
        //TODO adicionar criptografia da senha
        newUser.setHashedPassword(user.password());
        newUser.setActive(true);
        return userRepository.save(newUser);
    }
}
