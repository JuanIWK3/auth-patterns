package com.example.authpoo.user;

import com.example.authpoo.error.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserService userService;

    @GetMapping("/{email}")
    public User getUserByEmail(@PathVariable String email){
        return this.userService.getUserByEmail(email);
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleNoSuchElementExeption(NoSuchElementException exception, HttpServletRequest request){
        ApiError error = new ApiError(404, "User not found",
                new Date().getTime(),"/"+ request.getContextPath());
        return error;
    }
}
