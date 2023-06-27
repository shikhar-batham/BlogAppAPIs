package com.crestdevs.BlogAppBE.controller;

import com.crestdevs.BlogAppBE.payload.ApiResponse;
import com.crestdevs.BlogAppBE.payload.UserDto;
import com.crestdevs.BlogAppBE.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {

        UserDto createdUser = this.userService.createUser(userDto);

        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer userId) {

        UserDto updatedUser = this.userService.updateUser(userDto, userId);

        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<ApiResponse> deleteUser(@RequestParam Integer userId) {

        this.userService.deleteUser(userId);

        return new ResponseEntity<>(new ApiResponse("user deleted successfully!", true), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public UserDto getUserById(@PathVariable("userId") Integer userId) {

        UserDto userDto = this.userService.getUserById(userId);

        return userDto;
    }

    @GetMapping("/getAllUser")
    public List<UserDto> getAllUsers() {

        return this.userService.getAllUsers();
    }
}
