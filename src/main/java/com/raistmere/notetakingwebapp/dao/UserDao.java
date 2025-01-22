package com.raistmere.notetakingwebapp.dao;

import com.raistmere.notetakingwebapp.model.UserModel;

import java.util.List;

public interface UserDao {

    UserModel getUserByUsername(String username);

    List<UserModel> getAllUsers();
}
