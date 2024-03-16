package com.crestdevs.BlogAppBE.controller;

import com.crestdevs.BlogAppBE.payload.ApiResponse;
import com.crestdevs.BlogAppBE.payload.UserDto;
import com.crestdevs.BlogAppBE.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Value("${project.userProfileImages}")
    private String path;

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

    @GetMapping("/getUserByEmail/email")
    public ResponseEntity<UserDto> getUserByEmail(@RequestParam("email") String email) {

        UserDto userByEmail = this.userService.getUserByEmail(email);

        return new ResponseEntity<>(userByEmail, HttpStatus.OK);
    }

    @PostMapping("/uploadUserImage/{userId}")
    public ResponseEntity<String> uploadUserProfileImage(@PathVariable Integer userId,
                                                         @RequestParam("image") MultipartFile image) throws IOException {

        try {
            this.userService.uploadUserProfileImage(userId, path, image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new ResponseEntity<>("Successfully uploaded image", HttpStatus.OK);
    }

    @GetMapping(value = "/getImage/{userId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadUserProfileImage(@PathVariable int userId, HttpServletResponse response) {

        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        try {
            this.userService.downloadUserProfileImage(userId, path, response);
        } catch (IOException ignored) {

        }

    }

    @PreAuthorize("hasRole('ADMIN')")
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
