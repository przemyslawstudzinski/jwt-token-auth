package com.example.jwt.token.auth.dto;

import com.example.jwt.token.auth.domain.BaseEntity;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class EmployeeDto extends BaseEntity {

  @Setter
  @Getter
  @NotNull
  String name;

  @Setter
  @Getter
  @NotNull
  String lastName;

  @Setter
  @Getter
  LocalDate birthDate;

  @Setter
  @Getter
  LocalDate startWork;

  @Setter
  @Getter
  LocalDate endWork;

  @Setter
  @Getter
  double salary;

  @Setter
  @Getter
  String position;
}
