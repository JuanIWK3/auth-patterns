package com.example.authpoo.user;

import com.example.authpoo.error.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController extends ExceptionHandling {
    private final UserService userService;

    @GetMapping()
    public ResponseEntity<?> getAllUsers() {
        System.out.println("getAllUsers");
        try {
            return ResponseEntity.ok(userService.getAllUsers());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/by-email")
    public ResponseEntity<?> getUserByEmail(@RequestParam String email) throws EmailNotFoundException {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @GetMapping("/by-id")
    public ResponseEntity<?> getUserById(@RequestParam Integer id) throws IdNotFoundException {
        try {
            return ResponseEntity.ok(userService.getUserById(id));
        } catch (IdNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
