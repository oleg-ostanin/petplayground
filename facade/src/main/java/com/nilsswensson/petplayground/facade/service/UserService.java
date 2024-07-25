package com.nilsswensson.petplayground.facade.service;

import com.nilsswensson.petplayground.facade.security.user.User;
import com.nilsswensson.petplayground.facade.security.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Failed to find user with id " + id)
        );
    }
}
