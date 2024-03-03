package com.userManagement.userManagement.service.impl;

import com.userManagement.userManagement.dao.UserRepository;
import com.userManagement.userManagement.exception.UserNotFoundException;
import com.userManagement.userManagement.model.User;
import com.userManagement.userManagement.response.ErrorResponse;
import com.userManagement.userManagement.service.interfaces.UserService;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * The type User service.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    /**
     * Instantiates a new User service.
     *
     * @param userRepository the user repository
     */
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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
        if(optionalUser.isPresent()){
            return optionalUser.get();
        }
        throw new UserNotFoundException(new ErrorResponse("User not found with id " + id,"100-01",false));
    }

    @Override
    public User createUser(User user) {
        System.out.print(user);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User updatedUser) throws UserNotFoundException {
        User existingUser = this.userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(new ErrorResponse("User not found with id: " + id,"100-01",false)));

        if (updatedUser.getFirstName() != null) {
            existingUser.setFirstName(updatedUser.getFirstName());
        }
        if (updatedUser.getLastName() != null) {
            existingUser.setLastName(updatedUser.getLastName());
        }
        if (updatedUser.getMobileNumber() != null) {
            existingUser.setMobileNumber(updatedUser.getMobileNumber());
        }
        if (updatedUser.getPassword() != null) {
            existingUser.setPassword(updatedUser.getPassword());
        }

        return userRepository.save(existingUser);
    }
}

