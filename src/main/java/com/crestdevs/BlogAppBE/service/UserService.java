package com.crestdevs.BlogAppBE.service;

import com.crestdevs.BlogAppBE.payload.UserDto;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public interface UserService {

    UserDto registerNewUser(UserDto userDto);

    //create user
    UserDto createUser(UserDto userDto);

    //update user
    UserDto updateUser(UserDto userDto, Integer userId);

    UserDto uploadUserProfileImage(Integer userId, String path, MultipartFile file) throws IOException;

    //get user by id
    UserDto getUserById(Integer userId);

    //get all users
    List<UserDto> getAllUsers();

    //delete user by id
    void deleteUser(Integer userId);

    void downloadUserProfileImage(int userId, String path, HttpServletResponse response) throws IOException;
}
