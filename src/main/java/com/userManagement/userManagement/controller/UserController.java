package com.userManagement.userManagement.controller;

import com.userManagement.userManagement.exception.UserNotFoundException;
import com.userManagement.userManagement.model.User;
import com.userManagement.userManagement.service.interfaces.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId) throws UserNotFoundException {
        final User user = this.userService.getUserById(userId);
        return ResponseEntity.ok().body(user);
    }
}
