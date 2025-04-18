package com.example.register;

import com.example.register.services.EmailService;
import com.example.register.services.UserService;
import jakarta.mail.MessagingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.StringJoiner;

@RestController
@RequestMapping("/api/user")
public class RegisterApiController {

    UserService userService;
    EmailService emailService;

    public RegisterApiController(UserService userService, EmailService emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerNewUser(@RequestBody UserRegister request) throws MessagingException {
        if (request.getFirstName().isEmpty() || request.getLastName().isEmpty() ||
                request.getEmail().isEmpty() || request.getPassword().isEmpty() ||
                request.getUsername().isEmpty()) {
            return new ResponseEntity<>("Please fill all the fields", HttpStatus.BAD_REQUEST);
        }

        List<String> checkPrefList = request.getCheckPref();
        if (checkPrefList == null || checkPrefList.isEmpty()) {
            checkPrefList = List.of();
        }

        StringJoiner joiner = new StringJoiner(",");
        for (String pref : checkPrefList) {
            joiner.add(pref);
        }
        String checkPrefString = joiner.toString();

        String hashedPass = BCrypt.hashpw(request.getPassword(), BCrypt.gensalt());

        int result = userService.signup(
                request.getUsername(), request.getFirstName(),
                request.getLastName(), request.getEmail(), hashedPass, checkPrefString
        );

        if (result != 1) {
            return new ResponseEntity<>("Registration failed", HttpStatus.BAD_REQUEST);
        }

    

        String subject = "Καλώς ήρθατε στην εφαρμογή! | Welcome to the application!";
        String body = String.format(
                "Γεια σου %s %s, Καλώς ήρθες στην εφαρμογή μας! Το όνομα χρήστη σου είναι: %s. " +
                        "Αν θέλεις να κάνεις αλλαγές ώστε να εμφανίζονται οι κατηγορίες που επιθυμείς, μπορείς να κάνεις επεξεργασία μέσα από την εφαρμογή, " +
                        "αφού συνδεθείς πρώτα στον λογαριασμό σου."+
                        "\nΚαλή περιήγηση!"+
                        "\nΜε εκτίμηση, Η ομάδα υποστήριξης.\n" +
                        "-------------------------------------- " +
                        "Hello %s %s, Welcome to our application! Your username is: %s. " +
                        "If you want to make changes so that only the categories you prefer are displayed, " +
                        "you can edit them within the application after logging into your account."+
                        "\nEnjoy your experience!"+
                        "\nBest regards, The team support.",
                request.getFirstName(), request.getLastName(), request.getUsername(),  
                request.getFirstName(), request.getLastName(), request.getUsername()
        );



        emailService.sendEmail(request.getEmail(), subject, body);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
