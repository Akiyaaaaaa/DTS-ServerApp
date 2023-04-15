package id.co.metrodata.serverapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import id.co.metrodata.serverapp.models.user;
import id.co.metrodata.serverapp.models.Dto.request.userReq;
import id.co.metrodata.serverapp.repositories.userRepo;
import id.co.metrodata.serverapp.utils.myUserDetail;

public class authUser implements UserDetailsService {

  @Autowired
  private userRepo userRepo;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    user user = userRepo.getUserByUserName(username);
    if (user == null) {
      throw new UsernameNotFoundException("Could'nt find user");
    }
    return new myUserDetail(user);
  }

}
