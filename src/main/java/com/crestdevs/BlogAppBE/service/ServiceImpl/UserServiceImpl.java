package com.crestdevs.BlogAppBE.service.ServiceImpl;

import com.crestdevs.BlogAppBE.config.AppConstants;
import com.crestdevs.BlogAppBE.entity.Image;
import com.crestdevs.BlogAppBE.entity.Role;
import com.crestdevs.BlogAppBE.entity.User;
import com.crestdevs.BlogAppBE.exception.ResourceNotFoundException;
import com.crestdevs.BlogAppBE.payload.UserDto;
import com.crestdevs.BlogAppBE.repository.RoleRepo;
import com.crestdevs.BlogAppBE.repository.UserRepo;
import com.crestdevs.BlogAppBE.service.FileService;
import com.crestdevs.BlogAppBE.service.ImageService;
import com.crestdevs.BlogAppBE.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
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

    @Autowired
    private FileService fileService;

    @Autowired
    private ImageService imageService;

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
        fetchedUser.setLastName(userDto.getLastName());
        fetchedUser.setPassword(userDto.getPassword());
        fetchedUser.setEmail(userDto.getEmail());

        User updatedUser = this.userRepo.save(fetchedUser);

        return userToDto(updatedUser);
    }

    @Override
    public UserDto uploadUserProfileImage(Integer userId, String path, MultipartFile file) throws IOException {

        User fetchedUser = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "UserId", userId));

        Image image = this.imageService.saveImage(file);

        if (fetchedUser.getImage() != null && image.getData() != null) {
            this.imageService.deletePreviousImage(fetchedUser.getImage());
        }

        fetchedUser.setImage(image.getFileName());
        this.userRepo.save(fetchedUser);

        return this.modelMapper.map(fetchedUser, UserDto.class);
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

    @Override
    public void downloadUserProfileImage(int userId, String path, HttpServletResponse response) throws IOException {

        log.info("image from {},{}", "database", userId + " " + path);

        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
        String image = user.getImage();

        if (!image.isEmpty()) {
            Image img = imageService.getImage(image);
            StreamUtils.copy(img.getData(), response.getOutputStream());
        }
    }

    @Override
    public UserDto getUserByEmail(String email) {

        User fetchedUser = this.userRepo.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User", "user_with: " + email, 0));

        UserDto userDto = this.modelMapper.map(fetchedUser, UserDto.class);

        return userDto;
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
