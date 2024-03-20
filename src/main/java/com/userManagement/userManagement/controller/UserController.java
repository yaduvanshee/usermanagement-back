package com.userManagement.userManagement.controller;

import com.userManagement.userManagement.exception.UserManagementException;
import com.userManagement.userManagement.exception.UserNotFoundException;
import com.userManagement.userManagement.model.UserInfo;
import com.userManagement.userManagement.service.interfaces.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The type User controller.
 */
@RestController
@RequestMapping("/user")
public class UserController {

  private final UserService userService;

  /**
   * Instantiates a new User controller.
   *
   * @param userService the user service
   */
  public UserController(UserService userService) {
    this.userService = userService;
  }

  /**
   * Retrieves a user by their unique identifier.
   *
   * @param userId The unique identifier of the user to retrieve.
   * @return ResponseEntity with HTTP status 200 (OK) and the user information if found.
   * @throws UserNotFoundException if the user with the specified ID is not found.
   */
  @GetMapping("/get/{userId}")
  public ResponseEntity<?> getUserById(@PathVariable Long userId) throws UserNotFoundException {
    final UserInfo user = this.userService.getUserById(userId);
    return ResponseEntity.ok().body(user);
  }

  //TODO remove password from response
  @PostMapping("/create")
  public ResponseEntity<?> createUser(@RequestBody UserInfo user) throws UserManagementException {
    UserInfo createdUser = this.userService.createUser(user);
    return ResponseEntity.ok().body(createdUser);
  }

  //TODO remove password from response
  @PutMapping("/{userId}")
  public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody UserInfo updatUser)
      throws UserNotFoundException {
    UserInfo updatedUser = this.userService.updateUser(userId, updatUser);
    return ResponseEntity.ok().body(updatedUser);
  }
}
