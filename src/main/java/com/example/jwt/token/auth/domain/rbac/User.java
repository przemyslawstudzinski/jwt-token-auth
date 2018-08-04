package com.example.jwt.token.auth.domain.rbac;

import com.example.jwt.token.auth.domain.BaseEntity;
import java.util.Collection;
import java.util.HashSet;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

@AllArgsConstructor
@NoArgsConstructor
@Entity
// 'user' is reversed word for PostgreSQL
@Table(name = "app_user")
public class User extends BaseEntity implements UserDetails {

  @Column(name = "username", nullable = false, unique = true)
  @Getter
  @Setter
  @NotBlank
  private String username;

  @Column(name = "password", nullable = false)
  @Getter
  @Setter
  @Size(min = 8, max = 100)
  private String password;

  @Column(name = "email", nullable = false, unique = true)
  @Getter
  @Setter
  @Email
  private String email;

  @Column(name = "first_name")
  @Getter
  @Setter
  private String firstName;

  @Column(name = "last_name")
  @Getter
  @Setter
  private String lastName;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "role_assignment",
      joinColumns = @JoinColumn(
          name = "user_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(
          name = "role_id", referencedColumnName = "id"))
  @Getter
  @Setter
  private Collection<Role> roles = new HashSet<>();

  @Column(name = "secret")
  @Getter
  @Setter
  private String secret;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return AuthorityUtils.createAuthorityList(
        roles.stream()
            .flatMap(role -> role.getPermissions().stream())
            .map(Permission::getDisplayName)
            .toArray(String[]::new)
    );
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
