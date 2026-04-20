package com.quantity.service;


import com.quantity.dto.LoginDTO;
import com.quantity.dto.RegisterDTO;
import com.quantity.entity.User;
import com.quantity.repository.UserRepository;
import com.quantity.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    // REGISTER
    public String register(RegisterDTO dto) {

    	User existingUser = userRepository.findByEmail(dto.getEmail());

        if (existingUser != null) {
            return "User already exists";
        }

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());

        // HASH PASSWORD HERE
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        userRepository.save(user);

        return "User Registered Successfully";
    }

    // LOGIN
    public String login(LoginDTO dto) {

    	User user = userRepository.findByEmail(dto.getEmail());

        if (user == null) {
            return "User not found";
        }

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            return "Invalid password ";
        }

        return jwtUtil.generateToken(user.getEmail());
    }
}