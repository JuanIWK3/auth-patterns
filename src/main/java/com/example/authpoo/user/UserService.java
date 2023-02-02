package com.example.authpoo.user;

import com.example.authpoo.error.EmailExistException;
import com.example.authpoo.error.NotFoundException;
import com.example.authpoo.error.UserNotFoundException;
import com.example.authpoo.error.UsernameExistException;
import com.example.authpoo.user.dto.GetUserDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User getUserByEmail(GetUserDTO getUserDTO) throws UserNotFoundException, EmailExistException, UsernameExistException {
        validateNewUsernameAndEmail("",getUserDTO.getName(),getUserDTO.getEmail());
        return this
                .userRepository
                .findByEmail(getUserDTO.getEmail())
                .orElseThrow(() -> new NotFoundException("User does not exist"));
    }
    private User validateNewUsernameAndEmail(String currentUsername, String newUsername, String newEmail) throws UserNotFoundException, UsernameExistException, EmailExistException {
        User userByNewUsername = userRepository.findUserByName(newUsername).get();
        User userByNewEmail = userRepository.findByEmail(newEmail).get();
        if(StringUtils.isNotBlank(currentUsername)) {
            User currentUser = userRepository.findUserByName(currentUsername).get();
            if(currentUser == null) {
                throw new UserNotFoundException(currentUsername);
            }
            if(userByNewUsername != null && !currentUser.getId().equals(userByNewUsername.getId())) {
                throw new UsernameExistException(currentUsername);
            }
            if(userByNewEmail != null && !currentUser.getId().equals(userByNewEmail.getId())) {
                throw new EmailExistException(newEmail);
            }
            return currentUser;
        } else {
            if(userByNewUsername != null) {
                throw new UsernameExistException(currentUsername);
            }
            if(userByNewEmail != null) {
                throw new EmailExistException(newEmail);
            }
            return null;
        }
    }
}