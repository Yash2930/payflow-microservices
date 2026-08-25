package com.payflow.userservice.service;

import com.payflow.userservice.dto.CreateUserRequest;
import com.payflow.userservice.dto.UserResponse;
import com.payflow.userservice.entity.User;
import com.payflow.userservice.repository.UserRepository;
import com.payflow.userservice.service.serviceImpl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

   @Test
    void shouldCreateUserSuccessfully(){

       CreateUserRequest request=new CreateUserRequest();

       request.setName("Yash");
       request.setEmail("yash@test.com");
       request.setPassword("password123");




       User user=new User();

       user.setName("Yash");
       user.setEmail("yash@test.com");
       user.setPassword("password123");

       UserResponse userResponse = new UserResponse();
       userResponse.setName("Yash");
       userResponse.setEmail("yash@test.com");

       when(modelMapper.map(request, User.class)).thenReturn(user);

       when(passwordEncoder.encode("password123")).thenReturn("hashedPassword");

       when(userRepository.save(any(User.class))).thenReturn(user);

       when(modelMapper.map(user,UserResponse.class)).thenReturn(userResponse);

       UserResponse response = userService.createUser(request);

       assertNotNull(response);
       assertEquals("Yash",response.getName());


   }

}
