package com.userManagement.userManagement.audit;

import java.util.Optional;
import org.springframework.data.domain.AuditorAware;

public class AuditAwareImpl implements AuditorAware<String> {
  @Override
  public Optional<String> getCurrentAuditor() {
    return Optional.of("Saurabh");
  }
}
