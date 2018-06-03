package com.example.jwt.token.auth.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TokenController {

  @RequestMapping(value = "/token", method = RequestMethod.POST)
  public String getToken() {
    return "Token has been created successfully.";
  }
}
