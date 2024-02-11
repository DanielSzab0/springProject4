package com.example.springproject4.controller;

import com.example.springproject4.dto.request.user.SignInRequest;
import com.example.springproject4.dto.request.user.UserRequest;
import com.example.springproject4.dto.response.user.SignInResponse;
import com.example.springproject4.service.security.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("api/register")
    public ResponseEntity<Void> registerAdmin(@RequestBody @Valid UserRequest userRequest) {
        authService.getUserByEmail(userRequest.getEmail());
        authService.registerUser(userRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("api/signIn")
    public ResponseEntity<SignInResponse> signIn(@RequestBody @Valid SignInRequest signInRequest) {
        SignInResponse response = authService.singIn(signInRequest);

        return ResponseEntity.ok().body(response);
    }
}
