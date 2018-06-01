package com.example.jwt.token.auth.security.config;

import com.example.jwt.token.auth.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@Order(1)
public class TokenEndpointConfiguration extends WebSecurityConfigurerAdapter {

  @Override
  public void configure(final HttpSecurity http) throws Exception {

    // No session will be created or used by spring security.
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    // Disable CSRF (cross site request forgery).
    http.csrf().disable();

    // Entry point configuration.
    http
        .antMatcher("/api/token")
        .authorizeRequests()
        .anyRequest()
        .authenticated()
        .and()
        .httpBasic();

    http.addFilterBefore(jwtAuthenticationFilter(), BasicAuthenticationFilter.class);
  }

  @Bean
  public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
    return new JwtAuthenticationFilter(this.authenticationManager());
  }
}
