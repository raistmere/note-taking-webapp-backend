package com.raistmere.notetakingwebapp.dao;

import com.raistmere.notetakingwebapp.model.UserModel;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    UserModel getUserByUsername(String username);
    void saveUser(UserModel user) throws SQLException;
    List<UserModel> getAllUsers();
}
