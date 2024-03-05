package com.userManagement.userManagement.audit;

import java.util.Optional;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

//To tell JPA about currently login user
public class SpringSecurityAuditorAwareImpl implements AuditorAware<String> {

  @Override
  public Optional<String> getCurrentAuditor() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if(authentication == null || !authentication.isAuthenticated()){
      return Optional.empty();
    }
    return Optional.of(authentication.getName());
  }
}
