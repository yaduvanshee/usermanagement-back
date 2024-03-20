package com.userManagement.userManagement.security;

import com.userManagement.userManagement.dao.UserRepository;
import com.userManagement.userManagement.model.UserDetailModel;
import com.userManagement.userManagement.model.UserInfo;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  public UserDetailsServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<UserInfo> userInfo = this.userRepository.findByEmail(username);
    return userInfo.map(UserDetailModel::new).orElseThrow(()->new UsernameNotFoundException("Invalid Username"));
  }
}
