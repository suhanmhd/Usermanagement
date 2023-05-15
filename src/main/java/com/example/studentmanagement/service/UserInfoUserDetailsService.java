package com.example.studentmanagement.service;

import com.example.studentmanagement.config.UserInfoUserDetails;
import com.example.studentmanagement.entity.UserInfo;
import com.example.studentmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {
    @Autowired
     private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> user = userRepository.findByUsername(username);
        return  user.map(UserInfoUserDetails::new)
                .orElseThrow(()->new UsernameNotFoundException("user name not found"+username));
    }
}
