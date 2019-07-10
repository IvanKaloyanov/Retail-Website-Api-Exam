package com.koev.retailwebsite.api.controllers;

import com.koev.retailwebsite.api.dto.AuthDto;
import com.koev.retailwebsite.api.dto.UserDto;
import com.koev.retailwebsite.api.services.interfaces.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private AuthService authService;

    /**
     * Class {@link AuthenticationController} REST API controller
     * performing authentication based operations (login/register)
     *
     * @param authService the {@link AuthService implementation}
     */
    public AuthenticationController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Endpoint for obtaining JWT using credentials (username/password)
     *
     * @param authDto {@link AuthDto} the user credentials
     */
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthDto authDto) {
        try {
            return ResponseEntity.ok(authService.login(authDto));
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid credentials supplied");
        }
    }

    /**
     * Endpoint registering a user
     *
     * @param userDto {@link UserDto} the user information
     */
    @PostMapping("/register")
    public ResponseEntity signin(@RequestBody UserDto userDto) {
        authService.register(userDto);
        return new ResponseEntity(HttpStatus.OK);
    }
}