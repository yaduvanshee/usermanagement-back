package com.userManagement.userManagement.dao;

import com.userManagement.userManagement.model.UserInfo;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserInfo,Long> {

  boolean existsByEmail(String emailId);

  Optional<UserInfo> findByEmail(String email);
}
