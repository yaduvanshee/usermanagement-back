package com.userManagement.userManagement.security;

import com.userManagement.userManagement.dao.UserRepository;
import com.userManagement.userManagement.enums.ErrorEnum;
import com.userManagement.userManagement.exception.UserManagementException;
import com.userManagement.userManagement.model.CustomUserDetails;
import com.userManagement.userManagement.model.User;
import com.userManagement.userManagement.response.ErrorResponse;
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
    User user;
    try {
      user = userRepository.findByEmail(username)
          .orElseThrow(()->new UserManagementException(new ErrorResponse(
              ErrorEnum.INVALID_USER_NAME.getErrorMsg(),
              ErrorEnum.INVALID_USER_NAME.getErrorCode(),
              false
          )));
    } catch (UserManagementException e) {
      throw new RuntimeException(e);
    }
    return new CustomUserDetails(user);
  }
}
