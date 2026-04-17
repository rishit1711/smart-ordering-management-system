package com.OnlineOrderSystem.SOOMS.Controller;

import com.OnlineOrderSystem.SOOMS.dto.LoginRequest;
import com.OnlineOrderSystem.SOOMS.dto.RegisterRequest;
import com.OnlineOrderSystem.SOOMS.dto.UserResponse;
import com.OnlineOrderSystem.SOOMS.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
public class usercontroller {
    @Autowired
    public UserService userService;
    @PostMapping("/register")
    public ResponseEntity<UserResponse> TakeUser(@RequestBody RegisterRequest registerRequest){
        UserResponse CreatedUser = userService.CreateUser(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(CreatedUser);
    }
    @PostMapping("/login")
    public ResponseEntity<UserResponse> FetchUser(@RequestBody LoginRequest loginRequest){
        UserResponse CheckUser = userService.FindUser(loginRequest);
        return ResponseEntity.ok(CheckUser);

    }
}
