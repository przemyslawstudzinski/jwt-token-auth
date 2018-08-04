package com.example.jwt.token.auth.service;

import com.example.jwt.token.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Qualifier(value = "UserDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // TODO resolve hide info of nested exception
    return userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException(
            String.format("User with username=%s was not found", username)
        ));
  }
}
