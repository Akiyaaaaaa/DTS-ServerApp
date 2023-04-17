package id.co.metrodata.serverapp.configs;

import id.co.metrodata.serverapp.services.AppUserDetailService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

  private AppUserDetailService appUserDetailService;
  private PasswordEncoder passwordEncoder;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
      .userDetailsService(appUserDetailService)
      .passwordEncoder(passwordEncoder);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .cors()
      .disable()
      .csrf()
      .disable()
      .authorizeRequests()
      .antMatchers("/region/**")
      .hasRole("USER")
      .antMatchers("/country/**")
      .hasRole("ADMIN")
      .anyRequest()
      .authenticated()
      .and()
      .httpBasic();
  }
}
