package com.raistmere.notetakingwebapp.service;

import com.raistmere.notetakingwebapp.dao.UserDaoImpl;
import com.raistmere.notetakingwebapp.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserDaoImpl userDaoImpl;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDaoImpl userDao, PasswordEncoder passwordEncoder) {

        this.userDaoImpl = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Long getUserID(String username) {

        // get user from database
        UserModel user = userDaoImpl.getUserByUsername(username);

        // get id from user
        Long id = user.getId();

        return id;
    }

    @Override
    public String createUser(UserModel user) {

        try{

            // get the plain text password and hash/crypt it for database
            String password = passwordEncoder.encode(user.getPassword());
            user.setPassword(password);

            userDaoImpl.saveUser(user);
            return "User created success!";
        } catch(Exception e) {

            logger.error("Exception: {}",e.getMessage());
            return "User creation failed!";
        }
    }
}
