package com.platforma.concedii.service;

import com.platforma.concedii.dao.UserDAO;
import com.platforma.concedii.dto.UserDTO;

import java.util.List;

public final class UserService {

    public static final UserService userServiceInstance = new UserService();
    private UserService(){}
    public static UserService getInstance() {
        return userServiceInstance;
    }

    public UserDTO getUserById(int userId) {
        return UserDAO.getInstance().getUserById(userId);
    }

    public UserDTO getUserByUserName(String userName) {
        return UserDAO.getInstance().getUserByUserName(userName);
    }

    public void addUser(UserDTO userDTO) {
        UserDAO.getInstance().addUser(userDTO);
    }

    public List<UserDTO> getAllManagers() {
        return UserDAO.getInstance().getAllManagers();
    }

}
