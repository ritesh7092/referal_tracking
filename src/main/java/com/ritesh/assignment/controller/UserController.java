package com.ritesh.assignment.controller;

import com.ritesh.assignment.dto.ProfileCompletionRequest;
import com.ritesh.assignment.dto.UserSignupRequest;
import com.ritesh.assignment.model.User;
import com.ritesh.assignment.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@Valid @RequestBody UserSignupRequest request) {
        User user = userService.signup(request);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/profile/{userId}")
    public ResponseEntity<User> completeProfile(
            @PathVariable Long userId,
            @Valid @RequestBody ProfileCompletionRequest request
    ) {
        User user = userService.completeProfile(userId, request);
        return ResponseEntity.ok(user);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleExceptions(Exception e) {
        return ResponseEntity.badRequest().body("Error: " + e.getMessage());
    }
}