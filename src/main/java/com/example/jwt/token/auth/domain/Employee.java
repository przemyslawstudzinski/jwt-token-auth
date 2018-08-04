package com.example.jwt.token.auth.domain;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "employee")
public class Employee extends BaseEntity {

  @Column(name = "name")
  @Setter
  @Getter
  String name;

  @Column(name = "last_name")
  @Setter
  @Getter
  String lastName;

  @Column(name = "birth_date")
  @Setter
  @Getter
  LocalDate birthDate;

  @Column(name = "start_work")
  @Setter
  @Getter
  LocalDate startWork;

  @Column(name = "end_work")
  @Setter
  @Getter
  LocalDate endWork;

  @Column(name = "salary")
  @Setter
  @Getter
  double salary;

  @Column(name = "position")
  @Setter
  @Getter
  String position;
}
