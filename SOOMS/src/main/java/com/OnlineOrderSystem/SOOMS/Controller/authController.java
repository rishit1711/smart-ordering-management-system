package com.OnlineOrderSystem.SOOMS.Controller;

import com.OnlineOrderSystem.SOOMS.Security.CustomUserDetails;
import com.OnlineOrderSystem.SOOMS.Security.JwtUtils;
import com.OnlineOrderSystem.SOOMS.dto.AuthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class authController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomUserDetails userdetailService;
    @Autowired
    private JwtUtils jwtUtils;
    @PostMapping("/signup")
    public  String login(@RequestBody AuthRequest authRequest){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(),authRequest.getPassword()));
            UserDetails userDetails=userdetailService.loadUserByUsername(authRequest.getEmail());
            System.out.println("Login Success");
            return  jwtUtils.GenerateToken(authRequest.getEmail());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }





    }
}
