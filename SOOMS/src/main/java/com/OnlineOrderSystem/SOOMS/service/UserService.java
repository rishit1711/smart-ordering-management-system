package com.OnlineOrderSystem.SOOMS.service;

import com.OnlineOrderSystem.SOOMS.Role;
import com.OnlineOrderSystem.SOOMS.dto.LoginRequest;
import com.OnlineOrderSystem.SOOMS.dto.RegisterRequest;
import com.OnlineOrderSystem.SOOMS.dto.UserResponse;
import com.OnlineOrderSystem.SOOMS.repository.UserRepository;
import com.OnlineOrderSystem.SOOMS.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse CreateUser(RegisterRequest registerRequest){
        User user = new User();
        user.setName(registerRequest.getName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole(Role.CUSTOMER);

        User saved = userRepository.save(user);
        UserResponse userResponse = new UserResponse();
        userResponse.setId(saved.getId());
        userResponse.setName(saved.getName());
        userResponse.setEmail(saved.getEmail());
        userResponse.setRole(user.getRole().name());
        return  userResponse;

    }


    public UserResponse FindUser(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));
        if(!user.getPassword().equals(loginRequest.getPassword())) throw new RuntimeException("Invalid Credentials");
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole().name());

        return  response;

    }
}
