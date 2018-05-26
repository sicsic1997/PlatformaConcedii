package com.platforma.concedii.service;

import com.platforma.concedii.dao.UserDAO;
import com.platforma.concedii.dto.UserDTO;

public final class UserService {

    public static final UserService userServiceInstance = new UserService();
    private UserService(){}
    public static UserService getInstance() {
        return userServiceInstance;
    }

    public UserDTO getUserById(int userId) {
        return UserDAO.getInstance().getUserById(userId);
    }

}
