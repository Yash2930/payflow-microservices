package com.payflow.userservice.service;

import com.payflow.userservice.dto.CreateUserRequest;
import com.payflow.userservice.dto.UpdateUserRequest;
import com.payflow.userservice.dto.UserResponse;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;

public interface UserService {

 UserResponse createUser(CreateUserRequest userRequest);

 UserResponse getUserById(Long id);

 UserResponse updateUser(UpdateUserRequest userRequest,Long id);

 void deleteUser(Long id);


}
