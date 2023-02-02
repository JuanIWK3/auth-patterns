package com.example.authpoo.user;

import com.example.authpoo.error.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserController extends ExceptionHandling {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getEmail")
    public ResponseEntity<?> getUserByEmail(@RequestParam String email) throws EmailNotFoundException, EmailNotExistException {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @GetMapping("/getId")
    public ResponseEntity<?> getUserById(@RequestParam Integer id) throws IdExistException, IdNotFoundException {
        return ResponseEntity.ok(userService.getUserById(id));
    }
}
