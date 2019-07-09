package com.koev.retailwebsite.api.services;

import com.koev.retailwebsite.api.dto.AuthDto;
import com.koev.retailwebsite.api.dto.UserDto;
import com.koev.retailwebsite.api.entities.User;
import com.koev.retailwebsite.api.repository.UserRepository;
import com.koev.retailwebsite.api.security.JwtTokenProvider;
import com.koev.retailwebsite.api.services.interfaces.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link AuthService} implementation
 */
@Service
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private PasswordEncoder passwordEncoder;

    public AuthServiceImpl(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            JwtTokenProvider jwtTokenProvider,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Map<String, String> login(AuthDto data) {
        String username = data.getUsername();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
        String token = jwtTokenProvider.createToken(username, this.userRepository.findByUsername(username).getRoles());

        Map<String, String> model = new HashMap<>();
        model.put("username", username);
        model.put("token", token);
        return model;
    }

    @Override
    public Long register(UserDto data) {
        return this.userRepository.save(User.builder()
                .username(data.getUsername())
                .password(this.passwordEncoder.encode(data.getPassword()))
                .roles(data.getRoles())
                .build()
        ).getId();
    }
}
