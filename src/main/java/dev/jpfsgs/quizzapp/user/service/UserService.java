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

    public List<UserDTO> findActiveUsers() {
        return userRepository.findAllByActiveTrue().stream().map(userMapper::toUserDTO).toList();
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
        userRepository.findByEmail(user.email())
                .ifPresent(existingUser -> {
                    throw new UserAlreadyExistsException("User already exists");
                });

        User newUser = userMapper.toUser(user);

        newUser.setHashedPassword(passwordEncoder.encode(user.password()));
        newUser.setActive(true);
        userRepository.save(newUser);
        logger.info("New user created: {}, Id: {}", newUser.getEmail(), newUser.getId());
        return userMapper.toUserDTO(newUser);
    }

    public UserDTO deactivateUser(UUID id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );
        user.setActive(false);
        userRepository.save(user);
        logger.info("User deactivated: {}, Id: {}", user.getEmail(), user.getId());
        return userMapper.toUserDTO(user);
    }

    public UserDTO activateUser(UUID id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );
        user.setActive(true);
        userRepository.save(user);
        logger.info("User activated: {}, Id: {}", user.getEmail(), user.getId());
        return userMapper.toUserDTO(user);
    }

    public UserDTO changePassword(UUID id, String password) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );
        user.setHashedPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        logger.info("Password changed for user: {}, Id: {}", user.getEmail(), user.getId());
        return userMapper.toUserDTO(user);
    }

    public UserDTO removeUser(UUID id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );
        userRepository.delete(user);
        logger.info("User deleted: {}, Id: {}", user.getName(), user.getId());
        return userMapper.toUserDTO(user);
    }
}
