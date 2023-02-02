package com.example.authpoo.user;

import com.example.authpoo.error.NotFoundException;
import com.example.authpoo.user.dto.GetUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User getUserByEmail(GetUserDTO getUserDTO) {
        return this
                .userRepository
                .findByEmail(getUserDTO.getEmail())
                .orElseThrow(() -> new NotFoundException("User does not exist"));
    }
}