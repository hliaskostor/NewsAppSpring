package com.example.register.services;

import com.example.register.models.User;
import com.example.register.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUserDetailsByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public int signup(String username, String fName, String lName, String email, String password, String checkpref) {
        return userRepository.newUser(username, fName, lName, email, password, checkpref);
    }

    public User changePref(String username, List<String> checkPrefList) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setCheckPref(String.join(",", checkPrefList));
            return userRepository.save(user);
        } else {
            return null;
        }
    }
}
