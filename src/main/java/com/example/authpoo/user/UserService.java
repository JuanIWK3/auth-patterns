package com.example.authpoo.user;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    public User getUserByEmail(String email) {
        return this.userRepository.findByEmail(email).orElseThrow();
    }
}