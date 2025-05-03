package com.example.register.controllers;

import com.example.register.models.changePref;
import com.example.register.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UpdateApiController {

    @Autowired
    UserService userService;

    @PostMapping("/user/updatePref")
    public ResponseEntity<String> updatePref(@RequestBody changePref changePref) {
        if (changePref == null || changePref.getUsername() == null || changePref.getPrefCheck() == null) {
            return ResponseEntity.badRequest().body("{\"error\": \"Invalid request data\"}");
        }

        if (userService.changePref(changePref.getUsername(), changePref.getPrefCheck()) != null) {
            return ResponseEntity.ok("{\"message\": \"Preferences updated successfully\"}");
        } else {
            return ResponseEntity.status(404).body("{\"error\": \"User not found or preferences update failed\"}");
        }
    }
}
