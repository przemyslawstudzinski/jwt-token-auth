package com.example.jwt.token.auth.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {

  @RequestMapping(value = "/token", method = RequestMethod.GET)
  public String getToken() {
    return "Token is created";
  }
}
