package sejongZoo.sejongZoo.common.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import sejongZoo.sejongZoo.common.token.AuthTokenProvider;
import sejongZoo.sejongZoo.common.token.filter.JwtFilter;


@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthTokenProvider authTokenProvider;
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sesstionManagement -> sesstionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // 세션을 사용하지 않기 때문에 STATELESS로 설정
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers( "/find-nickname", "/login", "/signup", "/swagger-ui/**", "/v3/api-docs/**", "/check-nickname",
                                "/check-student-id", "/change-forget-password", "/refresh","/ws/chat"
                        ).permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/user/**").hasRole("USER")
                )
                .addFilterBefore(new JwtFilter(authTokenProvider), UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

}
