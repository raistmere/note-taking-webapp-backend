package com.raistmere.notetakingwebapp.dao;

import com.raistmere.notetakingwebapp.model.User;

import java.util.List;

public interface UserDao {

    User getUserByUsername(String username);

    List<User> getAllUsers();
}
