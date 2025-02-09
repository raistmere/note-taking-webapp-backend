package com.raistmere.notetakingwebapp.service;

import com.raistmere.notetakingwebapp.dto.ChangePasswordDto;
import com.raistmere.notetakingwebapp.model.UserModel;

public interface UserService {

    Long getUserID(String username);
    String createUser(UserModel user);
    String changePassword(Long userID, ChangePasswordDto changePasswordDto);
}
