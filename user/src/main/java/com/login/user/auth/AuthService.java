package com.login.user.auth;

import com.login.user.usuario.User;
import com.login.user.usuario.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUsernameByUsername(String username){
        return userRepository.getUserByUsername(username);
    }

    public boolean createUser(User user) {
        try {
            userRepository.save(user);
        } catch(Exception e){
            return false;
        }
        return true;
    }
}
