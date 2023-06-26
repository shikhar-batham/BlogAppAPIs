package com.crestdevs.BlogAppBE.service;

import com.crestdevs.BlogAppBE.entity.User;
import com.crestdevs.BlogAppBE.payload.UserDto;
import com.crestdevs.BlogAppBE.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDto createUser(UserDto userDto) {

        User user=this.DtoToUser(userDto);

        User createdUser=this.userRepo.save(user);

        return this.userToDto(createdUser);
    }

    private User DtoToUser(UserDto userDto) {

        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setAbout(userDto.getAbout());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());

        return user;
    }

    private UserDto userToDto(User user){

        UserDto userDto=new UserDto();

        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setAbout(user.getAbout());
        userDto.setPassword(user.getPassword());

        return userDto;
    }

}
