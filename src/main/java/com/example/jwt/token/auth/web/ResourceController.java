package com.example.jwt.token.auth.web;

import com.example.jwt.token.auth.annotations.CurrentlyLoggedUser;
import java.security.Principal;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ResourceController {

  @RequestMapping(value = "/create", method = RequestMethod.GET)
  @ResponseStatus(HttpStatus.OK)
  @PreAuthorize("hasAuthority('CREATE')")
  public String create(@CurrentlyLoggedUser Principal user) {
    return "CREATED";
  }

  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  @ResponseStatus(HttpStatus.OK)
  @PreAuthorize("hasAuthority('EDIT')")
  public String edit() {
    return "EDITED";
  }

  @RequestMapping(value = "/view", method = RequestMethod.GET)
  @ResponseStatus(HttpStatus.OK)
  public String view() {
    return "VIEW";
  }

  @RequestMapping(value = "/delete", method = RequestMethod.GET)
  @ResponseStatus(HttpStatus.OK)
  public String delete() {
    return "DELETED";
  }

}
