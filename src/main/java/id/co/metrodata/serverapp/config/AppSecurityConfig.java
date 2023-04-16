package id.co.metrodata.serverapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import id.co.metrodata.serverapp.services.authUser;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

  // authentication provider => username & pass from database
  @Bean
  public UserDetailsService userDetailsService() {
    return new authUser();
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService());
    authProvider.setPasswordEncoder(passwordEncoder());

    return authProvider;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    // in memory security
    // auth.inMemoryAuthentication()
    // .withUser("user")
    // .password("user")
    // .roles("USER")
    // .and()
    // .withUser("admin")
    // .password("admin")
    // .roles("ADMIN");

    auth.authenticationProvider(authenticationProvider());
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }

  // in memory security
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/employe/**").hasRole("EMPLOYEE")
        .antMatchers("/country/**").hasRole("EMPLOYEE")
        .antMatchers("/**").hasRole("MANAGER")
        .anyRequest().permitAll()
        .and()
        .httpBasic();
    // .anyRequest().authenticated()
    // .and()
    // .formLogin().permitAll()
    // .and()
    // .logout().permitAll();
  }

}
