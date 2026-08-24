package com.payflow.userservice.service.serviceImpl;

import com.payflow.userservice.dto.CreateUserRequest;
import com.payflow.userservice.dto.UpdateUserRequest;
import com.payflow.userservice.dto.UserResponse;
import com.payflow.userservice.entity.User;
import com.payflow.userservice.exception.UserNotFoundException;
import com.payflow.userservice.repository.UserRepository;
import com.payflow.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;



    @Override
    public UserResponse createUser(CreateUserRequest userRequest) {

        User user = modelMapper.map(userRequest, User.class);

        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));


        User savedUser = userRepository.save(user);

        return  modelMapper.map(savedUser,UserResponse.class);
    }

    @Override
    public UserResponse getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        return modelMapper.map(user,UserResponse.class);
    }

    @Override
    public UserResponse updateUser(UpdateUserRequest userRequest,Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        modelMapper.map(userRequest,user);

        User updatedUser = userRepository.save(user);


        return modelMapper.map(updatedUser,UserResponse.class);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

       userRepository.delete(user);
    }


    @Override
    public void deactivateUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        user.setActive(false);
        userRepository.save(user);


    }
}
