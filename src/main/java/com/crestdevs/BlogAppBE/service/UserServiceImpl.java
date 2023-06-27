package com.crestdevs.BlogAppBE.service;

import com.crestdevs.BlogAppBE.entity.User;
import com.crestdevs.BlogAppBE.exception.ResourceNotFoundException;
import com.crestdevs.BlogAppBE.payload.UserDto;
import com.crestdevs.BlogAppBE.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {

        User user = this.DtoToUser(userDto);

        User createdUser = this.userRepo.save(user);

        return this.userToDto(createdUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {

        User fetchedUser = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user", "userid", userId));

        fetchedUser.setName(userDto.getName());
        fetchedUser.setAbout(userDto.getAbout());
        fetchedUser.setPassword(userDto.getPassword());
        fetchedUser.setEmail(userDto.getEmail());

        User updatedUser = this.userRepo.save(fetchedUser);

        return userToDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {

        User fetchedUser = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user", "user_id", userId));

        return userToDto(fetchedUser);
    }

    @Override
    public List<UserDto> getAllUsers() {

        List<User> userList = this.userRepo.findAll();

        return userList.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Integer userId) {

        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "user id", userId));

        this.userRepo.delete(user);

    }

    private User DtoToUser(UserDto userDto) {

        User user = this.modelMapper.map(userDto, User.class);
        return user;
    }

    private UserDto userToDto(User user) {

        UserDto userDto = this.modelMapper.map(user, UserDto.class);

        return userDto;
    }

}
