package com.crestdevs.BlogAppBE.security;

import com.crestdevs.BlogAppBE.entity.User;
import com.crestdevs.BlogAppBE.exception.ResourceNotFoundException;
import com.crestdevs.BlogAppBE.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //load user from database by username
        //hare we are using Email as username
        User fetchedUser = this.userRepo.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("User", "user_email" + username, -1));

        return fetchedUser;
    }
}
