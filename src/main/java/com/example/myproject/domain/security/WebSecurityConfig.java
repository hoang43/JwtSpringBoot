package com.example.myproject.domain.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

  @Autowired
  @Qualifier("unauthorizedHandlerFilter")
  AuthenticationEntryPoint authEntryPoint;

  @Bean
  public AuthJwtTokenFilter authenticationJwtTokenFilter() {
    return new AuthJwtTokenFilter();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//    http.cors().and().csrf().disable()
//            .exceptionHandling().authenticationEntryPoint(authEntryPoint).and()
//            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//            .authorizeHttpRequests()
//            .requestMatchers("/api/tests/**").permitAll()
//            .anyRequest().authenticated();
//    http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

    http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/api/tests/**").permitAll()
                    .anyRequest().authenticated()
            )
            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
            http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }
}

