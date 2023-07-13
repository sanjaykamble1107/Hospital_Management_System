package com.hms.dummy;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtDummyAuthentication {
    private static final String SECRET_KEY = "47636c477a637a5270347855343271713448487368304e3453676b5a62537277"; // Replace with your secret key

    public static String generateDummyToken(String username, long expiresInMilliseconds) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + expiresInMilliseconds);

        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles", "ROLE_USER");

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}

