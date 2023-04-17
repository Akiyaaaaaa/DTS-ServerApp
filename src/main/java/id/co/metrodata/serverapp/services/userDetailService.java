package id.co.metrodata.serverapp.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import id.co.metrodata.serverapp.models.myUserDetail;
import id.co.metrodata.serverapp.models.user;
import id.co.metrodata.serverapp.repositories.userRepo;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class userDetailService implements UserDetailsService {

  private userRepo userRepo;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    user user = userRepo.findByUsernameOrEmployeeEmail(username, username)
        .orElseThrow(() -> new UsernameNotFoundException("Username or Email not valid!!!"));
    return new myUserDetail(user);
  }

}
