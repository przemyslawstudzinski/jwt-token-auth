package com.example.jwt.token.auth.security;

import static com.example.jwt.token.auth.security.SecurityConstants.EXPIRATION_TIME;
import static com.example.jwt.token.auth.security.SecurityConstants.HEADER_STRING;
import static com.example.jwt.token.auth.security.SecurityConstants.SECRET;
import static com.example.jwt.token.auth.security.SecurityConstants.TOKEN_PREFIX;

import com.example.jwt.token.auth.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

  public JwtAuthenticationFilter(
      AuthenticationManager authenticationManager) {
    super(authenticationManager);
  }

  @Override
  protected void onSuccessfulAuthentication(HttpServletRequest req,
      HttpServletResponse res,
      Authentication auth) {

    Claims claims = Jwts.claims().setSubject(((User) auth.getPrincipal()).getUsername());
    claims.put("scopes", ((User) auth.getPrincipal()).getAuthorities()
            .stream()
            .map(s -> s.toString())
            .collect(Collectors.toList()));

    String token = Jwts.builder()
        .setClaims(claims)
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .signWith(SignatureAlgorithm.HS512, SECRET)
        .compact();

    res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
  }
}
