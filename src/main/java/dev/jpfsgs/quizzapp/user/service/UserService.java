package dev.jpfsgs.quizzapp.user.service;

import dev.jpfsgs.quizzapp.user.dto.mapper.UserMapper;
import dev.jpfsgs.quizzapp.user.dto.request.RegisterUserDTO;
import dev.jpfsgs.quizzapp.user.dto.response.UserDTO;
import dev.jpfsgs.quizzapp.user.customexception.UserAlreadyExistsException;
import dev.jpfsgs.quizzapp.user.customexception.UserNotFoundException;
import dev.jpfsgs.quizzapp.user.model.User;
import dev.jpfsgs.quizzapp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(userMapper::toUserDTO).toList();
    }

    public UserDTO getUserById(UUID id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }

        return userMapper.toUserDTO(user.get());
    }

    public UserDTO getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }

        return userMapper.toUserDTO(user.get());
    }

    public UserDTO registerUser(RegisterUserDTO user) {
        Optional<User> existentUser = userRepository.findByEmail(user.email());
        if (existentUser.isPresent()) {
            throw new UserAlreadyExistsException("User already exists");
        }

        User newUser = userMapper.toUser(user);

        newUser.setHashedPassword(passwordEncoder.encode(user.password()));
        newUser.setActive(true);
        return userMapper.toUserDTO(userRepository.save(newUser));
    }
}
