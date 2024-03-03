package com.userManagement.userManagement.service.interfaces;

import com.userManagement.userManagement.exception.UserNotFoundException;
import com.userManagement.userManagement.model.User;

public interface UserService{
    User getUserById(Long id) throws UserNotFoundException;

    User createUser(User user);

    User updateUser(Long id, User updatedUser) throws UserNotFoundException;
}
