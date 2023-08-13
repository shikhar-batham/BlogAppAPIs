package com.crestdevs.BlogAppBE.service;

import com.crestdevs.BlogAppBE.payload.UserDto;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;


public interface UserService {

    UserDto registerNewUser(UserDto userDto);
    //create user
    UserDto createUser(UserDto userDto);

    //update user
    UserDto updateUser(UserDto userDto, Integer userId);

    //get user by id
    UserDto getUserById(Integer userId);

    //get all users
    List<UserDto> getAllUsers();

    //delete user by id
    void deleteUser(Integer userId);
}
