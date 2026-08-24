package com.payflow.userservice.controller;

import com.payflow.userservice.dto.CreateUserRequest;
import com.payflow.userservice.dto.UpdateUserRequest;
import com.payflow.userservice.dto.UserResponse;
import com.payflow.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(
            @Valid @RequestBody CreateUserRequest request){

        UserResponse user = userService.createUser(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(user);

    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id){

        UserResponse user = userService.getUserById(id);

        return ResponseEntity.ok(user);

    }



    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@Valid @RequestBody UpdateUserRequest request,
                                                   @PathVariable Long id){

        UserResponse user = userService.updateUser(request, id);

        return  ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deactivateUser(@PathVariable Long id){
        userService.deactivateUser(id);
        return ResponseEntity.ok("User deactivated successfully");
    }



}
