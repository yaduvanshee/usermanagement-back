package com.userManagement.userManagement.service.impl;

import com.userManagement.userManagement.dao.UserRepository;
import com.userManagement.userManagement.model.User;
import com.userManagement.userManagement.service.interfaces.UserService;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(String id) {
        Optional<User> optionalUser = this.userRepository.findById(id);
        if(optionalUser.isPresent()){
            return optionalUser.get();
        }
        throw new RuntimeException("No user found");
    }
}

