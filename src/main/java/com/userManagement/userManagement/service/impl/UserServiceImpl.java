package com.userManagement.userManagement.service.impl;

import com.userManagement.userManagement.dao.UserRepository;
import com.userManagement.userManagement.errorEnum.ErrorEnum;
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
    ErrorEnum error = ErrorEnum.INVALID_USER_ID;
    throw new UserNotFoundException(
            new ErrorResponse(error.getErrorMsg() + id, error.getErrorCode(), false));

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
      ErrorEnum error = ErrorEnum.INVALID_EMAIL_ID;
      throw new UserManagementException(
          new ErrorResponse(error.getErrorMsg() + emailId,
              error.getErrorCode(),
              false));
    }
  }

  public void validateUserId(final Long id) throws UserNotFoundException {
    if (!(userRepository.existsById(id))) {
      ErrorEnum error = ErrorEnum.INVALID_USER_ID;
      throw new UserNotFoundException(
          new ErrorResponse(error.getErrorMsg() + id, error.getErrorCode(), false));
    }
  }

  @Override
  public User updateUser(Long id, User updatedUser) throws UserNotFoundException {
    User existingUser = this.userRepository.findById(id)
            .orElseThrow(() -> {
              ErrorEnum error = ErrorEnum.INVALID_USER_ID;
              return new UserNotFoundException(
                      new ErrorResponse(error.getErrorMsg() + id, error.getErrorCode(), false)
              );
            });
    existingUser.setFirstName(updatedUser.getFirstName());
    existingUser.setLastName(updatedUser.getLastName());
    existingUser.setMobileNumber(updatedUser.getMobileNumber());
    existingUser.setEmail(updatedUser.getEmail());
    existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));

    return userRepository.save(existingUser);
  }
}

