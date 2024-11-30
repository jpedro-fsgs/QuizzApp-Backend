package dev.jpfsgs.quizzapp.user.repository;

import dev.jpfsgs.quizzapp.user.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldSaveAndFindUserByUsername() {
        User user = new User();
        user.setEmail("myoui_mina");
        user.setHashedPassword("chaeng");
        user.setActive(true);
        userRepository.save(user);

        Optional<User> userOptional = userRepository.findByEmail("myoui_mina");
        assertTrue(userOptional.isPresent());
        assertTrue(userOptional.get().getActive());
        assertEquals("chaeng", userOptional.get().getHashedPassword());
    }

    @Test
    void shouldSaveAndDeleteUser() {
        User user = new User();
        user.setEmail("myoui_mina");
        user.setHashedPassword("chaeng");
        user.setActive(true);
        userRepository.save(user);

        Optional<User> userOptional = userRepository.findByEmail("myoui_mina");
        assertTrue(userOptional.isPresent());

        userRepository.delete(userOptional.get());
        Optional<User> userOptional2 = userRepository.findByEmail("myoui_mina");
        assertFalse(userOptional2.isPresent());
    }

    @Test
    void shouldDeleteUserAfterEdit() {
        User user = new User();
        user.setEmail("myoui_mina");
        user.setHashedPassword("chaeng");
        user.setActive(true);

        userRepository.save(user);

        Optional<User> userOptional = userRepository.findByEmail("myoui_mina");
        assertTrue(userOptional.isPresent());
        UUID userId = userOptional.get().getId();

        userOptional.get().setEmail("sharon_myoui");
        userRepository.save(userOptional.get());

        Optional<User> userOptional2 = userRepository.findByEmail("myoui_mina");
        assertFalse(userOptional2.isPresent());

        Optional<User> userOptional3 = userRepository.findByEmail("sharon_myoui");
        assertTrue(userOptional3.isPresent());
        assertEquals(userOptional3.get().getId(), userId);

        userRepository.delete(userOptional3.get());

        Optional<User> userOptional4 = userRepository.findByEmail("sharon_myoui");
        assertFalse(userOptional4.isPresent());
    }

}
