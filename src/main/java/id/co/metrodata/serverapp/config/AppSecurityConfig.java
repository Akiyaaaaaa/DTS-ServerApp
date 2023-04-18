package id.co.metrodata.serverapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import id.co.metrodata.serverapp.services.userDetailService;
import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

  private userDetailService userDetailService;
  private PasswordEncoder passwordEncoder;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
        .userDetailsService(userDetailService)
        .passwordEncoder(passwordEncoder);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .cors().disable()
        .csrf().disable()
        .authorizeRequests()
        .antMatchers(HttpMethod.POST, "/register")
        .permitAll()
        .antMatchers(HttpMethod.POST, "/login")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .httpBasic();
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }
}

// authentication provider => username & pass from database
// @Bean
// public UserDetailsService userDetailsService() {
// return new userDetailService();
// }

// @Bean
// public DaoAuthenticationProvider authenticationProvider() {
// DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
// authProvider.setUserDetailsService(userDetailsService());
// authProvider.setPasswordEncoder(passwordEncoder());

// return authProvider;
// }

// auth.authenticationProvider(authenticationProvider());
// in memory security
// auth.inMemoryAuthentication()
// .withUser("user")
// .password("user")
// .roles("USER")
// .and()
// .withUser("admin")
// .password("admin")
// .roles("ADMIN");