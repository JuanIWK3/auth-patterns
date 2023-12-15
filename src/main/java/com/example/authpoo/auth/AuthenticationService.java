package com.example.authpoo.auth;

import com.example.authpoo.config.JwtService;
import com.example.authpoo.error.EmailExistsException;
import com.example.authpoo.error.EmailNotFoundException;
import com.example.authpoo.user.Role;
import com.example.authpoo.user.User;
import com.example.authpoo.user.UserRepository;
import com.example.authpoo.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) throws EmailExistsException, EmailNotFoundException {
        // Create user object
        var user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        // Check if user exists
        Optional<User> userExists = this.repository.findByEmail(request.getEmail());


        // If user exists, throw exception
        if (userExists.isPresent()){
            throw new EmailExistsException(request.getEmail());
        }

        // Save user in database
        repository.save(user);

        // Generate JWT token
        var jwtToken = jwtService.generateToken(user);

        // Return token and user
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .user_dto(new UserDto(user))
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) throws EmailNotFoundException {
        // Authenticate user
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // Get user from database
        // If user does not exist, throw exception
        User user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException(request.getEmail()));

        // Generate JWT token
        var jwtToken = jwtService.generateToken(user);

        // Return token and user
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .user_dto(new UserDto(user))
                .build();
    }
}
