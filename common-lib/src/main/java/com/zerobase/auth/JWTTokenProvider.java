package com.zerobase.auth;

import java.util.Date;

import org.springframework.util.StringUtils;

import com.zerobase.util.AES256Encoder;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTTokenProvider {

    // todo 환경설정에서 읽어오기
    private static final String SECRET_KEY = "ZerobaseProject2";
    private static final long TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 2; // 2시간.

    private static final String KEY_USERID = "userId";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_ROLE = "role";

    public String generateToken(Long userId, String userName, String email, String role) {
        Claims claim = Jwts.claims();

        String encrytedUserName = AES256Encoder.encrypt(userName);
        claim.setSubject(encrytedUserName);

        String encrytedemail = AES256Encoder.encrypt(email);
        claim.put(KEY_EMAIL, encrytedemail);

        claim.put(KEY_USERID, userId.toString());
        claim.put(KEY_ROLE, role);

        Date now = new Date();
        Date expireDate = new Date(now.getTime() + TOKEN_EXPIRE_TIME);

        return Jwts.builder()
                .setClaims(claim)
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public boolean isExpiredToken(String token) {
        if (!StringUtils.hasText(token)) {
            return true;
        }

        Claims claims = this.parseClaims(token);
        return claims.getExpiration().before(new Date());
    }

    public String getUsername(String token) {
        String encryptedUserName = this.parseClaims(token).getSubject();
        return AES256Encoder.decrypt(encryptedUserName);
    }

    public String getEmail(String token) {
        String encryptedUserName = (String) this.parseClaims(token).get(KEY_EMAIL);
        return AES256Encoder.decrypt(encryptedUserName);
    }

    public Long getUserId(String token) {
        return Long.parseLong((String) this.parseClaims(token).get(KEY_USERID));
    }

    public String getRole(String token) {
        return (String) this.parseClaims(token).get(KEY_ROLE);
    }

    private Claims parseClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException ex) {
            return ex.getClaims();
        }
    }
}