package com.userManagement.userManagement.dao;

import com.userManagement.userManagement.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

  boolean existsByEmail(String emailId);

  Optional<User> findByEmail(String email);
}
