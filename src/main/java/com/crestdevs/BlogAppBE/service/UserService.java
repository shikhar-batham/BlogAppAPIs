package com.crestdevs.BlogAppBE.service;

import com.crestdevs.BlogAppBE.payload.UserDto;
import com.crestdevs.bogBE.payload.UserDto;
import org.springframework.stereotype.Service;

public interface UserService {

    UserDto createUser(UserDto userDto);
}
