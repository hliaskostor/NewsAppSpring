package com.example.register.rest_controllers;

import com.example.register.models.Login;
import com.example.register.models.User;
import com.example.register.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class LoginApiController {

    @Autowired
    UserService userService;

    @PostMapping("/user/login")
    public ResponseEntity<?> authenticateUser(@RequestBody Login login) {
        Optional<User> userOptional = userService.getUserDetailsByUsername(login.getUsername());

        if (userOptional.isEmpty()) {
            return new ResponseEntity<>("Username does not exist", HttpStatus.NOT_FOUND);
        }

        User user = userOptional.get();

        if (!BCrypt.checkpw(login.getPassword(), user.getPassword())) {
            return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("username", user.getUsername());
        response.put("email", user.getEmail());

        List<String> prefs = (user.getCheckPref() != null && !user.getCheckPref().isEmpty())
                ? user.getprefList()
                : List.of();

        response.put("check_pref", prefs);

        return ResponseEntity.ok(response);
    }
}
