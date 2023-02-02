package com.example.authpoo.user;

import com.example.authpoo.error.ApiError;
import com.example.authpoo.error.NotFoundException;
import com.example.authpoo.user.dto.GetUserDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/get")
    public ResponseEntity<?> getUserByEmail(@RequestBody GetUserDTO getUserDTO, HttpServletRequest request){
        try{
            return ResponseEntity.ok(userService.getUserByEmail(getUserDTO));
        }catch (NotFoundException exception){
            ApiError error = new ApiError(
                    404,
                    exception.getMessage(),
                    request.getServletPath()
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }catch (Exception exception){
            ApiError error = new ApiError(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    exception.getMessage(),
                    request.getServletPath()
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}
