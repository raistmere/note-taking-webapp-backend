package com.raistmere.notetakingwebapp.service;

import com.raistmere.notetakingwebapp.dao.UserDaoImpl;
import com.raistmere.notetakingwebapp.model.UserModel;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class CustomUserDetailsService implements UserDetailsService {

    private final UserDaoImpl userDao;

    public CustomUserDetailsService(UserDaoImpl userDao) {

        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // get user data from database and store it into a userModel
        UserModel  userData = userDao.getUserByUsername(username);

        // take that user data and create a new UserDetails user
        User user = new User(userData.getName(), userData.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_USER")));

        // return user
        return user;
    }
}
