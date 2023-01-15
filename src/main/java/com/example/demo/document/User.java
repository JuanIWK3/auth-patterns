package com.example.demo.document;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
  @Id
  private String id;

  @NonNull
  private String username;

  @NonNull
  private String password;

  @Override
  protected Object clone() throws CloneNotSupportedException {
      return super.clone();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
      return Collections.EMPTY_LIST;
  }

  @Override
  public String getPassword() {
      return null;
  }

  @Override
  public String getUsername() {
      return null;
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
