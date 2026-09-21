package com.odji.spring_back_end.config;

import com.odji.spring_back_end.user.entity.User;
import com.odji.spring_back_end.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class PasswordMigration implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        List<User> users = userRepository.findAll();
        int count = 0;
        for (User u : users) {
            if (u.getPassword() != null && !u.getPassword().startsWith("$2a$")) {
                u.setPassword(passwordEncoder.encode(u.getPassword()));
                userRepository.save(u);
                count++;
            }
        }
        if (count > 0) {
            log.info("{} mots de passe migrés vers BCrypt", count);
        }
    }
}
