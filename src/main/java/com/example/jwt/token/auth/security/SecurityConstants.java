package com.example.jwt.token.auth.security;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public final class SecurityConstants {

  @Value("${jwt.secret.key}")
  @Getter
  private String secret;

  @Value("${jwt.expiration.time}")
  @Getter
  private long expirationTime;

  public static final String TOKEN_PREFIX = "Bearer ";

  public static final String HEADER_STRING = "Authorization";
}
