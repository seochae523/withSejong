package sejongZoo.sejongZoo.common.token;


import io.jsonwebtoken.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import sejongZoo.sejongZoo.common.exception.token.InvalidJwtToken;
import sejongZoo.sejongZoo.common.exception.token.TokenExpired;
import sejongZoo.sejongZoo.common.token.dto.AuthToken;
import sejongZoo.sejongZoo.user.service.CustomUserDetailService;

import java.util.Base64;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class AuthTokenProvider {
    @Value("${spring.jwt.secret}")
    private String key;
    @Value("${spring.jwt.access-token-expiration-time}")
    private Long authTokenExpirationTime;
    @Value("${spring.jwt.refresh-token-expiration-time}")
    private Long refreshTokenExpirationTime;
    private final CustomUserDetailService customUserDetailService;
    @PostConstruct
    protected void init(){
        this.key = Base64.getEncoder().encodeToString(this.key.getBytes());
    }



    public AuthToken generateToken(Authentication authentication, List<String> roles) {
        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim("roles", roles)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + authTokenExpirationTime))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();

        String refreshToken = Jwts.builder()
                .setSubject(authentication.getName())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenExpirationTime))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();

        return AuthToken.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public Authentication getAuthentication(String accessToken) {

        Claims claims = parseClaims(accessToken);
        String studentId = claims.getSubject();

        UserDetails userDetails = customUserDetailService.loadUserByUsername(studentId);
        // 여기서 auth목록을 다 가져와야되는데 이럴라면 detailservice에서 뽑아와야댐
        UserDetails principal = new User(claims.getSubject() , "", userDetails.getAuthorities());
        return new UsernamePasswordAuthenticationToken(principal, "", userDetails.getAuthorities());
    }

    public boolean validateToken(String token) {
        try{
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        }
        catch (SecurityException e) {
            log.info("Invalid JWT Signature");
            throw e;
        } catch (MalformedJwtException e) {
            log.info("Invalid JWT token.");
            throw new InvalidJwtToken(token);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token.");
            throw new TokenExpired(token);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token.");
            throw e;
        } catch (IllegalArgumentException e) {
            log.info("JWT token compact of handler are invalid.");
            throw e;
        }
    }
    private Claims parseClaims(String accessToken){
        try{
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        }
        catch (ExpiredJwtException e){
            return e.getClaims();
        }
    }
}
