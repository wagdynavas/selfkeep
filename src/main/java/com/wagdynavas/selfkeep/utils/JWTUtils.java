package com.wagdynavas.selfkeep.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.Password;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.crypto.SecretKey;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JWTUtils {

    public static String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day
                .signWith(Keys.hmacShaKeyFor("SECRET_KEY".getBytes()), Jwts.SIG.HS256)//TODO get real secret key
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .verifyWith()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
