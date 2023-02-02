package com.example.authpoo.user;

import com.example.authpoo.error.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User getUserByEmail(String email) throws EmailNotFoundException, EmailExistException{
        //validateNewUsernameAndEmail("", "", email);
        User userByNewEmail = userRepository.findByEmail(email).get();

        if(userByNewEmail == null) {
            throw new EmailExistException(email);
        }

        return this.userRepository
                .findByEmail(email)
                .orElseThrow(() -> new EmailNotFoundException(email));
    }

    public User getUserById(Integer id) throws IdNotFoundException, IdExistException {

        User userById = userRepository.findUserById(id).get();

        if(userById == null){
            throw new IdExistException(id);
        }

        return this.userRepository
                .findById(id)
                .orElseThrow(() -> new IdNotFoundException(id));
    }
//    private User validateNewUsernameAndEmail(String currentUsername, String newUsername, String newEmail) throws UserNotFoundException, UsernameExistException, EmailExistException {
//        User userByNewUsername = userRepository.findUserByName(newUsername).get();
//        User userByNewEmail = userRepository.findByEmail(newEmail).get();
//        if(StringUtils.isNotBlank(currentUsername)) {
//            User currentUser = userRepository.findUserByName(currentUsername).get();
//            if(currentUser == null) {
//                throw new UserNotFoundException(currentUsername);
//            }
//            if(userByNewUsername != null && !currentUser.getId().equals(userByNewUsername.getId())) {
//                throw new UsernameExistException(currentUsername);
//            }
//            if(userByNewEmail != null && !currentUser.getId().equals(userByNewEmail.getId())) {
//                throw new EmailExistException(newEmail);
//            }
//            return currentUser;
//        } else {
////            if(userByNewUsername != null) {
////                throw new UsernameExistException(currentUsername);
////            }
//            if(userByNewEmail != null) {
//                throw new EmailExistException(newEmail);
//            }
//            return null;
//        }
//    }
}