package com.example.authpoo.user;

import com.example.authpoo.error.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public User getUserByEmail(String email) throws EmailNotFoundException {
        Optional<User> userByNewEmail = userRepository.findByEmail(email);

        if (userByNewEmail.isEmpty()) {
            throw new EmailNotFoundException(email);
        }

        return userByNewEmail.get();
    }

    public User getUserById(Integer id) throws IdNotFoundException {

        Optional<User> userById = userRepository.findUserById(id);

        if (userById.isEmpty()) {
            throw new IdNotFoundException(id);
        }

        return userById.get();
    }
}