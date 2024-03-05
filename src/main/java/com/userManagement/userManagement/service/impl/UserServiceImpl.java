package com.userManagement.userManagement.service.impl;

import com.userManagement.userManagement.dao.UserRepository;
import com.userManagement.userManagement.exception.UserManagementException;
import com.userManagement.userManagement.exception.UserNotFoundException;
import com.userManagement.userManagement.mailer.EmailHelper;
import com.userManagement.userManagement.model.User;
import com.userManagement.userManagement.response.ErrorResponse;
import com.userManagement.userManagement.service.interfaces.UserService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * The type User service.
 */
@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final EmailHelper emailHelper;
  @Autowired
  private PasswordEncoder passwordEncoder;

  /**
   * Instantiates a new User service.
   *
   * @param userRepository the user repository
   * @param emailHelper
   */
  public UserServiceImpl(UserRepository userRepository, EmailHelper emailHelper) {
    this.userRepository = userRepository;
    this.emailHelper = emailHelper;
  }

  /**
   * Retrieves a user by their unique identifier.
   *
   * @param id The unique identifier of the user to retrieve.
   * @return The user object if found.
   * @throws UserNotFoundException if the user with the specified ID is not found.
   */

  @Override
  public User getUserById(Long id) throws UserNotFoundException {
    final Optional<User> optionalUser = this.userRepository.findById(id);
    if (optionalUser.isPresent()) {
      return optionalUser.get();
    }
    throw new UserNotFoundException(
        new ErrorResponse("User not found with id " + id, "100-01", false));
  }

  @Override
  public User createUser(final User user) throws UserManagementException {

    validateUserEmailId(user.getEmail());
    user.setPassword(passwordEncoder.encode(user.getPassword()));

    emailHelper.sendWelcomeMail(user.getEmail());

    return userRepository.save(user);
  }

  public void validateUserEmailId(final String emailId) throws UserManagementException {
    if (userRepository.existsByEmail(emailId)) {
      throw new UserManagementException(
          new ErrorResponse("User already exist with email id: " + emailId,
              "100-04",
              false));
    }
  }

  public void validateUserId(final Long id) throws UserNotFoundException {
    if (!(userRepository.existsById(id))) {
      throw new UserNotFoundException(
          new ErrorResponse("User not found with id: " + id, "100-01", false));
    }
  }

  @Override
  public User updateUser(Long id, User updatedUser) throws UserNotFoundException {
    User existingUser = this.userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException(
            new ErrorResponse("User not found with id: " + id, "100-01", false)));

    existingUser.setFirstName(updatedUser.getFirstName());
    existingUser.setLastName(updatedUser.getLastName());
    existingUser.setMobileNumber(updatedUser.getMobileNumber());
    existingUser.setEmail(updatedUser.getEmail());
    existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));

    return userRepository.save(existingUser);
  }
}

