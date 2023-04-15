package id.co.metrodata.serverapp.utils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import id.co.metrodata.serverapp.models.role;
import id.co.metrodata.serverapp.models.user;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class myUserDetail implements UserDetails {

  private user user;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<String> roles = this.user.getRoles().stream().map(role -> role.getName()).collect(Collectors.toList());
    return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
  }

  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return user.getUsername();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return user.getIsAccountNonLocked();
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return user.getIsEnabled();
  }

}
