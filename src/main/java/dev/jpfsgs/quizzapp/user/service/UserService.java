package dev.jpfsgs.quizzapp.user.service;

import dev.jpfsgs.quizzapp.user.dto.mapper.UserMapper;
import dev.jpfsgs.quizzapp.user.dto.request.RegisterUserDTO;
import dev.jpfsgs.quizzapp.user.dto.response.UserDTO;
import dev.jpfsgs.quizzapp.user.customexception.UserAlreadyExistsException;
import dev.jpfsgs.quizzapp.user.customexception.UserNotFoundException;
import dev.jpfsgs.quizzapp.user.model.User;
import dev.jpfsgs.quizzapp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(UserService.class);

    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(userMapper::toUserDTO).toList();
    }

    public UserDTO getUserById(UUID id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );

        return userMapper.toUserDTO(user);
    }

    public UserDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );

        return userMapper.toUserDTO(user);
    }

    public UserDTO registerUser(RegisterUserDTO user) {
        Optional<User> existentUser = userRepository.findByEmail(user.email());
        if (existentUser.isPresent()) {
            throw new UserAlreadyExistsException("User already exists");
        }

        User newUser = userMapper.toUser(user);

        newUser.setHashedPassword(passwordEncoder.encode(user.password()));
        newUser.setActive(true);
        userRepository.save(newUser);
        logger.info("New user created: {}, Id: {}", newUser.getName(), newUser.getId());
        return userMapper.toUserDTO(newUser);
    }
}
