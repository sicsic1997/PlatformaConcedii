package com.platforma.concedii.service;

import com.platforma.concedii.dao.UserDAO;
import com.platforma.concedii.dto.UserDTO;

import java.util.List;

public final class UserService {

    private static final UserService userServiceInstance = new UserService();
    private UserService(){}
    public static UserService getInstance() {
        return userServiceInstance;
    }
    public static final String USER_EXISTS_ERROR = "0User already exists";
    public static final String SUCCESS_REGISTER = "1User registered with success";

    /**/
    public UserDTO getUserById(int userId) {
        return UserDAO.getInstance().getUserById(userId);
    }

    /**/
    public UserDTO getUserByUserName(String userName) {
        return UserDAO.getInstance().getUserByUserName(userName);
    }

    /**/
    public void addUser(UserDTO userDTO) {
        UserDAO.getInstance().addUser(userDTO);
    }

    /**/
    public List<UserDTO> getAllManagers() {
        return UserDAO.getInstance().getAllManagers();
    }

    /**/
    public UserDTO logIn(String userName, String password) {
        UserDTO userDTO = getUserByUserName(userName);
        if(userDTO != null && userDTO.getPassword().equals(password)) {
            return userDTO;
        }
        return null;
    }

    /**/
    public String registerUser(UserDTO userDTO) {
        UserDTO userDTOExisting = getUserByUserName(userDTO.getUserName());
        if(userDTOExisting != null) {
            return USER_EXISTS_ERROR;
        }
        UserDAO.getInstance().addUser(userDTO);
        return SUCCESS_REGISTER;
    }

    /**/
    public List<UserDTO> getAllUsers() { return UserDAO.getInstance().getAllEmployees(); }

}
