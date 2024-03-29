package com.aadim.project.security;

import com.aadim.project.entity.UserLogin;
import com.aadim.project.repository.UserLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserLoginRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserLogin user =userRepository.getUserByUserName(username);
        if(user == null) throw new UsernameNotFoundException("Bad Credentials");
        return user;
    }
}
