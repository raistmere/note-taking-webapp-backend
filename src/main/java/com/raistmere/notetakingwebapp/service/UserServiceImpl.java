package com.raistmere.notetakingwebapp.service;

import com.raistmere.notetakingwebapp.dao.UserDaoImpl;
import com.raistmere.notetakingwebapp.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserDaoImpl userDaoImpl;

    @Autowired
    public UserServiceImpl(UserDaoImpl userDao) {

        this.userDaoImpl = userDao;
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

            userDaoImpl.saveUser(user);
            return "User created success!";
        } catch(SQLException e) {

            logger.error("SQLException: {}",e.getMessage());
            return "User creation failed!";
        }
    }
}
