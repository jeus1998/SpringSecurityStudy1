package com.example.testsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf((csrf) -> csrf.disable())

                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/login", "/join", "/joinProc").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated())

                .formLogin((form) -> form
                        .loginPage("/loginPage")
                        .loginProcessingUrl("/loginProc")
                        .permitAll())

                .sessionManagement(session -> session
                        .maximumSessions(1)
                        // true: 초과시 새로운 로그인 차단 false: 초과시 기존 세션 하나 삭제
                        .maxSessionsPreventsLogin(true))

                // default: changeSessionId 세션 공격 보호
                .sessionManagement(session -> session
                        .sessionFixation().changeSessionId());

        return http.build();
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
