package com.yildiz.controller.auth;

import com.yildiz.dto.SingupRequeust;
import com.yildiz.dto.UserDto;
import com.yildiz.services.auth.AuthService;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")

public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUpUser(@RequestBody SingupRequeust singupRequeust) {
        try {
            UserDto createdUser = authService.createUser(singupRequeust);
            return new ResponseEntity<>(createdUser, HttpStatus.OK);

        }catch (EntityExistsException entityExistsException) {
            return new ResponseEntity<>("user already exists", HttpStatus.NOT_ACCEPTABLE);
        }catch (Exception e) {
            return new ResponseEntity<>("User not created, come againg later", HttpStatus.BAD_REQUEST);
        }
    }
}
