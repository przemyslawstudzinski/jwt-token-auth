package com.example.jwt.token.auth.security;

import static com.example.jwt.token.auth.security.SecurityConstants.HEADER_STRING;
import static com.example.jwt.token.auth.security.SecurityConstants.TOKEN_PREFIX;

import com.example.jwt.token.auth.domain.rbac.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

  @Value("${jwt.secret.key}")
  private String secret;

  @Value("${jwt.expiration.time}")
  private long expirationTime;

  public JwtAuthenticationFilter(
      AuthenticationManager authenticationManager) {
    super(authenticationManager);
  }

  @Override
  protected void onSuccessfulAuthentication(HttpServletRequest request,
      HttpServletResponse response, Authentication authentication) {

    Claims claims = Jwts.claims().setSubject(((User) authentication.getPrincipal()).getUsername());
    claims.put("scopes", ((User) authentication.getPrincipal()).getAuthorities()
            .stream()
            .map(s -> s.toString())
            .collect(Collectors.toList()));

    String token = Jwts.builder()
        .setClaims(claims)
        .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
        .signWith(SignatureAlgorithm.HS512, secret)
        .compact();

    response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
  }
}
