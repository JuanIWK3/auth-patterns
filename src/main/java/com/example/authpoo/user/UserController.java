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

    @GetMapping("/getEmail")
    public ResponseEntity<?> getUserByEmail(@RequestParam String email) throws EmailNotFoundException, EmailExistException {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @GetMapping("/getId")
    public ResponseEntity<?> getUserById(@RequestParam Integer id) throws IdExistException, IdNotFoundException {
        return ResponseEntity.ok(userService.getUserById(id));
    }

}
