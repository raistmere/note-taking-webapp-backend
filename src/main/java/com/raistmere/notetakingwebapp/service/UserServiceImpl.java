package com.raistmere.notetakingwebapp.service;

import com.raistmere.notetakingwebapp.dao.UserDaoImpl;
import com.raistmere.notetakingwebapp.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserDaoImpl userDao;

    @Autowired
    public UserServiceImpl(UserDaoImpl userDao) {

        this.userDao = userDao;
    }

    @Override
    public Long getUserID(String username) {

        // get user from database
        UserModel user = userDao.getUserByUsername(username);

        // get id from user
        Long id = user.getId();

        return id;
    }
}
