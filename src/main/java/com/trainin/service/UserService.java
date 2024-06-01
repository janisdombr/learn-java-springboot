package com.trainin.service;

import com.trainin.persist.model.User;
import com.trainin.persist.repo.UserRepository;
import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public boolean emailFormatValidation(@Nonnull String email) {
        if (!email.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")) {
            return false;
        }
        return true;
    }
    public User getUserById(Integer id) {
        return userRepository.findById(id).orElseThrow();
    }
}
