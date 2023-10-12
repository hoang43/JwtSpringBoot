package com.example.myproject.domain.security;

import com.example.myproject.domain.exception.BaseException;
import com.example.myproject.domain.exception.ExceptionResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;

@Component
public class UnauthorizedHandlerFilter implements AuthenticationEntryPoint {
  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
      response.setContentType(MediaType.APPLICATION_JSON_VALUE);
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      OutputStream responseStream = response.getOutputStream();
      ObjectMapper mapper = new ObjectMapper();
      mapper.writeValue(responseStream, ExceptionResponse.createFrom(new BaseException(authException.getMessage())));
      responseStream.flush();
  }
}
