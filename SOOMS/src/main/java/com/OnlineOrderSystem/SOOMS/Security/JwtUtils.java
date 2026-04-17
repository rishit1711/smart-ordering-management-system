package com.OnlineOrderSystem.SOOMS.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Component

public class JwtUtils {
    private  final String SECRET = "dhjsabdjsbcsanbsxcbsadsd1212@$%$%232sncjshdc";
    private final SecretKey Signedkey = Keys.hmacShaKeyFor(SECRET.getBytes());
    public  String GenerateToken(String username) {

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(Signedkey, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims extractClaims(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(Signedkey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return  claims;
    }
    public  String ExtractUsername(String token){
        return extractClaims(token).getSubject();
    }
    public boolean isTokenExpired(String token){
        try{
            Claims claims = extractClaims(token);
            return claims.getExpiration().before(new Date());
        }
        catch (Exception e){
            return true; // invalid token = expired
        }
    }





    
}
