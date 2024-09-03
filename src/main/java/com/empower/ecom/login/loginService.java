package com.empower.ecom.login;

import com.empower.ecom.dto.jwtResponce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.empower.ecom.dto.LoginRequest;
import com.empower.ecom.dto.RegisterRequest;

@Service
public class loginService {

    @Autowired
    private loginRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public void register(RegisterRequest registerRequest) {
        // Check if email already exists
        if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use");
        }

        // Check if username already exists
        if (userRepository.findByUsername(registerRequest.getUsername()).isPresent()) {
            throw new RuntimeException("Username already in use");
        }

        // If email and username are unique, proceed with registration
        login user = new login();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userRepository.save(user);
    }

    public jwtResponce login(LoginRequest loginRequest) {
        // Find user by email
        login user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if passwords match
        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            // Generate JWT token
            String token = jwtUtil.generateToken(user.getUsername());

            // Return token only
            return new jwtResponce(token);
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }

    public String validateTokenAndGetMessage(String token) {
        // Validate token
        String username = jwtUtil.extractUsername(token);
        if (username != null && jwtUtil.validateToken(token, username)) {
            return "Login successful";
        } else {
            throw new RuntimeException("Failed to authorize token");
        }
    }
}
