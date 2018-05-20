package com.example.jwt.token.auth.domain;

import java.util.Collection;
import java.util.HashSet;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "role")
public class Role extends BaseEntity {

  @Column(name = "name", unique = true, nullable = false)
  @Getter
  @Setter
  @NotBlank
  private String name;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "permission_assignment",
      joinColumns = @JoinColumn(
          name = "role_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(
          name = "permission_id", referencedColumnName = "id"))
  @Getter
  @Setter
  private Collection<Permission> permissions = new HashSet<>();

  @Column(name = "decryption")
  @Getter
  @Setter
  private String decryption;

}
