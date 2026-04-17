package com.OnlineOrderSystem.SOOMS.Security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private  JwtUtils jwtUtils;
    @Autowired
    private  CustomUserDetails customUserDetails;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        String token = null;
        String username = null;

       if(header!=null && header.startsWith("Bearer ")) {
           token = header.substring(7);

           try {
               username = jwtUtils.ExtractUsername(token);
           } catch (Exception e) {
               throw new RuntimeException(e);
           }
       }
       if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
           UserDetails userDetails = customUserDetails.loadUserByUsername(username);
           System.out.println(userDetails.getAuthorities());

           if(!jwtUtils.isTokenExpired(token)){
               UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
               SecurityContextHolder.getContext().setAuthentication(auth);



           }

       }

       filterChain.doFilter(request,response);



    }
}
