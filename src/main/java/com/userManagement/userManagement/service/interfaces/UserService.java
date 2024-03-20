package com.userManagement.userManagement.service.interfaces;

import com.userManagement.userManagement.exception.UserManagementException;
import com.userManagement.userManagement.exception.UserNotFoundException;
import com.userManagement.userManagement.model.UserInfo;

public interface UserService{
    UserInfo getUserById(Long id) throws UserNotFoundException;

    UserInfo createUser(UserInfo user) throws UserManagementException;

    UserInfo updateUser(Long id, UserInfo updatedUser) throws UserNotFoundException;
}
