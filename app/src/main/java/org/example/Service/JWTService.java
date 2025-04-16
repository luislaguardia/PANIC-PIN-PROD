package org.example.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {

    @Value("${jwt.secret}")
    private String secretKey;


    public String generateToken(String username) {

        Map<String, Object> claims = new HashMap<String, Object>();

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + (60 * 60 * 30 * 10))) //The 60*60*30 gives 108 seconds, i dunno why. By multiplying by 10, i get 1080 secs or 18mins
                .and()
                .signWith(getKey())
                .compact();

    }

    private SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    
    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
               .verifyWith(getKey())
               .build()
               .parseSignedClaims(token)
               .getPayload();
    }

    // TODO: remove all comments before deployment
    public boolean validateToken(String token, UserDetails userDetails) {
        System.out.println("=== Token Validation Debug ===");
        System.out.println("Current secret key: " + secretKey);
        System.out.println("Token being validated: " + token);
        
        try {
            String username = extractUsername(token);
            System.out.println("Extracted username: " + username);
            
            boolean expired = isTokenExpired(token);
            System.out.println("Token expired: " + expired);
            
            boolean userMatch = username.equals(userDetails.getUsername());
            System.out.println("Username matches: " + userMatch);
            
            return userMatch && !expired;
        } catch (Exception e) {
            System.out.println("Validation error: " + e.getMessage());
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    
}
