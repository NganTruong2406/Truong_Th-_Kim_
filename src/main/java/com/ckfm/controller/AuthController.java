package com.ckfm.controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Map;

import com.ckfm.entity.User;
import com.ckfm.service.AuthService;
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        User savedUser = authService.registerUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> body) {
        boolean isSuccess = authService.login(
                body.get("username"),
                body.get("password")
        );

        return isSuccess
                ? ResponseEntity.ok("Login Success!")
                : ResponseEntity.status(401).body("Fail");
    }
}