package com.demoJWT.util;

import com.demoJWT.model.JwtResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Jwts.SIG;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "AHDJHFEUH475893DFHKDJSHFUIEFJBDJ4759DFHJEHUFREJBFIEI397583DFBEHFUEKFUEEFUG";

    public JwtResponse generateToken(String username){

        JwtResponse jwtResponse = new JwtResponse();

        String token=  Jwts.builder()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000*60*2)) // for 2 minute only
                // .signWith(getKey(), SignatureAlgorithm.HS256)
                .signWith(getKey()) // No need to take signature algorithm in this, it will automatically take the recommended algorithm.
                .compact();
        jwtResponse.setJwtToken(token);
        return jwtResponse;
    }

    private Key getKey() {
        byte[] decode = Base64.getDecoder().decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(decode);
    }

    // private Key getKey() {
        // byte[] keyBytes = this.SECRET_KEY.getBytes(Standard.UTF_8);
        // return Keys.hmacShaKeyFor(keyBytes);
    // }

    private Claims getClaims(String token){
        return Jwts.parser().verifyWith((SecretKey) getKey())
                .build().parseSignedClaims(token).getPayload();
    }

    public String extractUsername(String token) {
        Claims claims = getClaims(token);
        return claims.getSubject();
    }

    private boolean isTokenNotExpired(String token){
        Claims claims = getClaims(token);
        Date expiration = claims.getExpiration();
        return expiration.after(new Date());
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        String us = extractUsername(token);
        if(us.equals(userDetails.getUsername()) && isTokenNotExpired(token)){
            return true;
        }
        return false;
    }
}
