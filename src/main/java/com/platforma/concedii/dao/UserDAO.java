package com.platforma.concedii.dao;

import com.platforma.concedii.dto.UserDTO;
import com.platforma.concedii.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public final class UserDAO {

    private static final UserDAO userDAOInstance = new UserDAO();
    private Connection dbConnection;
    private UserDAO() {
        dbConnection = DbUtil.getConnection();
    };
    public static UserDAO getInstance() {
        return userDAOInstance;
    }

    /**/
    public UserDTO getUserById(int userId) {

        UserDTO userDTO = null;
        String sqlSelect = "" +
                "SELECT " +
                "   ID, " +
                "   USER_NAME, " +
                "   PASSWORD, " +
                "   FIRST_NAME, " +
                "   LAST_NAME, " +
                "   MANAGER_ID, " +
                "   USER_ROLE " +
                "FROM USERS " +
                "WHERE ID = ?";
        try(PreparedStatement ps = dbConnection.prepareStatement(sqlSelect)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                userDTO = new UserDTO();
                userDTO.setId(rs.getInt("ID"));
                userDTO.setUserName(rs.getString("USER_NAME"));
                userDTO.setPassword(rs.getString("PASSWORD"));
                userDTO.setFirstName(rs.getString("FIRST_NAME"));
                userDTO.setLastName(rs.getString("LAST_NAME"));
                userDTO.setManagerId(rs.getInt("MANAGER_ID"));
                userDTO.setUserRole(rs.getString("USER_ROLE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userDTO;

    }

    /**/
    public UserDTO getUserByUserName(String userName) {

        UserDTO userDTO = null;
        String sqlSelect = "" +
                "SELECT " +
                "   ID, " +
                "   USER_NAME, " +
                "   PASSWORD, " +
                "   FIRST_NAME, " +
                "   LAST_NAME, " +
                "   MANAGER_ID," +
                "   USER_ROLE " +
                "FROM USERS " +
                "WHERE USER_NAME = ?";
        try(PreparedStatement ps = dbConnection.prepareStatement(sqlSelect)) {
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                userDTO = new UserDTO();
                userDTO.setId(rs.getInt("ID"));
                userDTO.setUserName(rs.getString("USER_NAME"));
                userDTO.setPassword(rs.getString("PASSWORD"));
                userDTO.setFirstName(rs.getString("FIRST_NAME"));
                userDTO.setLastName(rs.getString("LAST_NAME"));
                userDTO.setManagerId(rs.getInt("MANAGER_ID"));
                userDTO.setUserRole(rs.getString("USER_ROLE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userDTO;

    }

    /**/
    public void addUser(UserDTO userDTO) {
        String sqlInsert = "" +
                "INSERT INTO USERS (USER_NAME, PASSWORD, FIRST_NAME, LAST_NAME, MANAGER_ID, USER_ROLE) VALUES (?, ?, ?, ?, ?, ?)";
        try(PreparedStatement ps = dbConnection.prepareStatement(sqlInsert)) {
            ps.setString(1, userDTO.getUserName());
            ps.setString(2, userDTO.getPassword());
            ps.setString(3, userDTO.getFirstName());
            ps.setString(4, userDTO.getLastName());
            ps.setInt(5, userDTO.getManagerId());
            ps.setString(6, userDTO.getUserRole());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**/
    public List<UserDTO> getAllManagers() {
        List<UserDTO> userDTOList = null;
        String sqlSelect = "" +
                "SELECT " +
                "   ID, " +
                "   USER_NAME, " +
                "   PASSWORD, " +
                "   FIRST_NAME, " +
                "   LAST_NAME, " +
                "   MANAGER_ID, " +
                "   USER_ROLE  " +
                "FROM USERS " +
                "WHERE ID = MANAGER_ID";
        try(PreparedStatement ps = dbConnection.prepareStatement(sqlSelect)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                UserDTO userDTO = new UserDTO();
                userDTO.setId(rs.getInt("ID"));
                userDTO.setUserName(rs.getString("USER_NAME"));
                userDTO.setPassword(rs.getString("PASSWORD"));
                userDTO.setFirstName(rs.getString("FIRST_NAME"));
                userDTO.setLastName(rs.getString("LAST_NAME"));
                userDTO.setManagerId(rs.getInt("MANAGER_ID"));
                userDTO.setUserRole(rs.getString("USER_ROLE"));
                userDTOList.add(userDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userDTOList;
    }

    /**/
    public List<UserDTO> getAllEmployees() {
        List<UserDTO> userDTOList = null;
        String sqlSelect = "" +
                "SELECT " +
                "   ID, " +
                "   USER_NAME, " +
                "   PASSWORD, " +
                "   FIRST_NAME, " +
                "   LAST_NAME, " +
                "   MANAGER_ID, " +
                "   USER_ROLE  " +
                "FROM USERS";
        try(PreparedStatement ps = dbConnection.prepareStatement(sqlSelect)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                UserDTO userDTO = new UserDTO();
                userDTO.setId(rs.getInt("ID"));
                userDTO.setUserName(rs.getString("USER_NAME"));
                userDTO.setPassword(rs.getString("PASSWORD"));
                userDTO.setFirstName(rs.getString("FIRST_NAME"));
                userDTO.setLastName(rs.getString("LAST_NAME"));
                userDTO.setManagerId(rs.getInt("MANAGER_ID"));
                userDTO.setUserRole(rs.getString("USER_ROLE"));
                userDTOList.add(userDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userDTOList;
    }

}
