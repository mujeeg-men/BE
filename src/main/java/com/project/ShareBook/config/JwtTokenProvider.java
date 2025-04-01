//package com.project.ShareBook.config;
//
//import java.security.Key;
//import java.util.Date;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//@Component
//public class JwtTokenProvider {
//
//    private final Key secretKey;
//    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1시간
//
//    public JwtTokenProvider(@Value("${jwt.secret}") String secret) {
//        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
//    }
//
//    // 토큰 생성
//    public String generateToken(String email) {
//        return Jwts.builder()
//            .setSubject(email)
//            .setIssuedAt(new Date())
//            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//            .signWith(secretKey, SignatureAlgorithm.HS256)
//            .compact();
//    }
//
//    // 토큰 검증
//    public boolean validateToken(String token) {
//        try {
//            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    // 토큰에서 사용자 이메일 추출
//    public String getUserEmail(String token) {
//        return Jwts.parserBuilder()
//            .setSigningKey(secretKey)
//            .build()
//            .parseClaimsJws(token)
//            .getBody()
//            .getSubject();
//    }
//}
