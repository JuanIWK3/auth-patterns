package com.example.authpoo.user;

import com.example.authpoo.error.*;
import com.example.authpoo.user.dto.GetUserDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController extends ExceptionHandling {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/get")
    public ResponseEntity<?> getUserByEmail(@RequestBody GetUserDTO getUserDTO, HttpServletRequest request) throws UserNotFoundException, EmailExistException, UsernameExistException {
        return ResponseEntity.ok(userService.getUserByEmail(getUserDTO));
    }
}
