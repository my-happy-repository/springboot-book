package com.study.security.config;

import com.study.security.domain.User;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class TokenProvider {

    // TODO - deprecated 된 메서드들 수정이 필요 !
    private final JwtProperties jwtProperties;

    private final Logger logger = LoggerFactory.getLogger(TokenProvider.class);

    public String generateToken(User user, Duration expiredAt) {
        Date now = new Date();
        return makeToken(new Date(now.getTime() + expiredAt.toMillis()), user);
    }

    // 1. JWT 토큰 생성 메서드
    // 인자는 만료 시간, 유저 정보를 받음, set 계열의 메서드를 통하여 여러 값을 지정, 헤더는 type, iss, iat, exp, sub, 클레임에는 USER ID 정보를 저장
    private String makeToken(Date expiry, User user) {
        Date now = new Date();

        byte[] secretKeyBytes = jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8);
        Key key = new SecretKeySpec(secretKeyBytes, SignatureAlgorithm.HS512.getJcaName());

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)   // 헤더 type : JWT
                .setIssuer(jwtProperties.getIssuer())   // 내용 issuer
                .setIssuedAt(now)                       // 내용 iat: 현재 시간
                .setExpiration(expiry)                  // 내용 exp: expiry 멤버 변숫값
                .setSubject(user.getEmail())            // 내용 sub : 유저의 이메일
                .claim("id", user.getId())           // 클레임 id : 유저 ID
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())   // 서명 : 비밀값과 함께 해시 값을 HS256 방식으로 암호하
                .compact();
    }

    // 2. JWT 토큰 유효성 검증 메서드
    // 토큰 유효성 검증 메서드, 프로퍼티 파일에 선언한 비밀값과 함께 토큰 복호화를 진행, 만약 복호화 과정에서 에러 발생 시 유효한 토큰이 아님,
    public boolean validateToken(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(jwtProperties.getSecretKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            logger.info("CLAIMS DATA CHECK : " + claims);
            logger.info("CLAIMS TO STRING DATA CHECK : " + claims.toString());

            return true;
        } catch (Exception e) {
            logger.info("FAIL GENERATE TOKEN : " + e.getMessage());
            return false;
        }
    }

    // 3. 토큰을 받아 인증 정보를 반환
    // 프로퍼티즈 파일에 저장 한 비밀값으로 토큰을 복호화 한 뒤 클레임을 가져오는 private 메서드
    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
        Set<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
        org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(claims.getSubject(), "",  authorities);

        return new UsernamePasswordAuthenticationToken(user, token, authorities);
    }

    public Long getUserId(String token) {
        Claims claims = getClaims(token);
        return claims.get("id", Long.class);
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}

