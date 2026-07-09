package com.nikhil.usermanagementapi.service.impl;

import com.nikhil.usermanagementapi.dto.request.CreateUserRequest;
import com.nikhil.usermanagementapi.dto.response.UserResponse;
import com.nikhil.usermanagementapi.entity.User;
import com.nikhil.usermanagementapi.repository.UserRepository;
import com.nikhil.usermanagementapi.service.UserService;
import org.springframework.stereotype.Service;
import com.nikhil.usermanagementapi.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger =
            LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse createUser(CreateUserRequest request) {
        logger.info("Creating a new user with email: {}", request.getEmail());
        User user = new User();

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());

        User savedUser = userRepository.save(user);

        logger.info("User created successfully with ID: {}", savedUser.getId());

        return new UserResponse(
                savedUser.getId(),
                savedUser.getFirstName(),
                savedUser.getLastName(),
                savedUser.getEmail()
        );
    }

//    @Override
//    public List<UserResponse> getAllUsers() {
//        throw new UnsupportedOperationException("Not implemented yet");
//    }

    @Override
    public List<UserResponse> getAllUsers() {
        logger.info("Fetching all users");
        List<User> users = userRepository.findAll();

        logger.info("Total users found: {}", users.size());

        return users.stream()
                .map(user -> new UserResponse(
                        user.getId(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getEmail()
                ))
                .toList();

    }

    @Override
    public UserResponse getUserById(Long id) {

        logger.info("Fetching user with ID: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with id: " + id));

        logger.info("User found with ID: {}", id);

        return new UserResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
    }

    @Override
    public UserResponse updateUser(Long id, CreateUserRequest request) {

        logger.info("Updating user with ID: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with id: " + id));

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());

        User updatedUser = userRepository.save(user);

        logger.info("User updated successfully with ID: {}", updatedUser.getId());

        return new UserResponse(
                updatedUser.getId(),
                updatedUser.getFirstName(),
                updatedUser.getLastName(),
                updatedUser.getEmail()
        );
    }

    @Override
    public void deleteUser(Long id) {
        logger.info("Deleting user with ID: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with id: " + id));

        userRepository.delete(user);
        logger.info("User deleted successfully with ID: {}", id);
    }
}