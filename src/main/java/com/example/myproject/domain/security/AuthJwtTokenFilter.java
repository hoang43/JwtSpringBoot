package com.example.myproject.domain.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class AuthJwtTokenFilter extends OncePerRequestFilter {
  @Autowired
  private JwtUtils jwtUtils;
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    final String requestTokenHeader = request.getHeader("Authorization");

    if (requestTokenHeader != null  && jwtUtils.validateJwtToken(requestTokenHeader)) {
      Claims claims = jwtUtils.getInfo(requestTokenHeader);
      UserDetailsImpl userDetails = new UserDetailsImpl(claims);

      if (userDetails.getPhone().equals("0959535496")){
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    }
    filterChain.doFilter(request, response);
  }
}
