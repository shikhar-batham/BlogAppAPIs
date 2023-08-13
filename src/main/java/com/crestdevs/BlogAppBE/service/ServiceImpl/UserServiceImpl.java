package com.crestdevs.BlogAppBE.service.ServiceImpl;

import com.crestdevs.BlogAppBE.config.AppConstants;
import com.crestdevs.BlogAppBE.entity.Role;
import com.crestdevs.BlogAppBE.entity.User;
import com.crestdevs.BlogAppBE.exception.ResourceNotFoundException;
import com.crestdevs.BlogAppBE.payload.UserDto;
import com.crestdevs.BlogAppBE.repository.RoleRepo;
import com.crestdevs.BlogAppBE.repository.UserRepo;
import com.crestdevs.BlogAppBE.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public UserDto registerNewUser(UserDto userDto) {

        User user = this.modelMapper.map(userDto, User.class);

        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();

        user.getRoles().add(role);

        User savedUser = this.userRepo.save(user);

        return this.modelMapper.map(savedUser, UserDto.class);
    }

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
