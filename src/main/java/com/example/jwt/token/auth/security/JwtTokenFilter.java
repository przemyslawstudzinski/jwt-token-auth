package com.example.jwt.token.auth.security;

import static com.example.jwt.token.auth.security.SecurityConstants.HEADER_STRING;
import static com.example.jwt.token.auth.security.SecurityConstants.SECRET;
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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JwtTokenFilter extends BasicAuthenticationFilter {

  public JwtTokenFilter(
      AuthenticationManager authenticationManager) {
    super(authenticationManager);
  }

  @Override
  protected void doFilterInternal(HttpServletRequest req,
      HttpServletResponse res,
      FilterChain chain) throws IOException, ServletException {
    String header = req.getHeader(HEADER_STRING);
    if (header == null || !header.startsWith(TOKEN_PREFIX)) {
      chain.doFilter(req, res);
      return;
    }
    UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
    SecurityContextHolder.getContext().setAuthentication(authentication);
    chain.doFilter(req, res);
  }

  private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
    String token = request.getHeader(HEADER_STRING);
    // Parse the token.
    if (token != null) {
      Jws<Claims> jwsClaims = Jwts.parser()
          .setSigningKey(SECRET)
          .parseClaimsJws(token.replace(TOKEN_PREFIX, ""));

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
