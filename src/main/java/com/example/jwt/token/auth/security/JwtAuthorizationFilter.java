package com.example.jwt.token.auth.security;

import static com.example.jwt.token.auth.security.SecurityConstants.HEADER_STRING;
import static com.example.jwt.token.auth.security.SecurityConstants.TOKEN_PREFIX;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

  @Value("${jwt.secret.key}")
  private String secret;

  public JwtAuthorizationFilter(
      AuthenticationManager authenticationManager) {
    super(authenticationManager);
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request,
      HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

    String header = request.getHeader(HEADER_STRING);
    if (header == null || !header.startsWith(TOKEN_PREFIX)) {
      chain.doFilter(request, response);
      return;
    }
    UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
    SecurityContextHolder.getContext().setAuthentication(authentication);

    chain.doFilter(request, response);
  }

  private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
    String header = request.getHeader(HEADER_STRING);
    // Parse the token.
    if (header != null) {
      String token = header.replace(TOKEN_PREFIX, "");

      Jws<Claims> jwsClaims = Jwts.parser()
          .setSigningKey(secret)
          .parseClaimsJws(token);

      String username = jwsClaims.getBody().getSubject();
      List<String> scopes = jwsClaims.getBody().get("scopes", List.class);
      List<GrantedAuthority> authorities = scopes.stream()
          .map(authority -> new SimpleGrantedAuthority(authority))
          .collect(Collectors.toList());

      if (username != null) {
        return new UsernamePasswordAuthenticationToken(username, null, authorities);
      }
      return null;
    }
    return null;
  }

}
