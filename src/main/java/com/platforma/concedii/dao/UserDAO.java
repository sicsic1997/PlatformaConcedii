package com.platforma.concedii.dao;

import com.platforma.concedii.dto.UserDTO;
import com.platforma.concedii.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class UserDAO {

    private static final UserDAO userDAOInstance = new UserDAO();
    private Connection dbConnection;

    private UserDAO() {
        dbConnection = DbUtil.getConnection();
    };

    public static UserDAO getInstance() {
        return userDAOInstance;
    }

    public UserDTO getUserById(int userId) {

        UserDTO userDTO = null;
        String sqlSelect = "" +
                "SELECT " +
                "   ID, " +
                "   USER_NAME, " +
                "   PASSWORD, " +
                "   FIRST_NAME, " +
                "   LAST_NAME, " +
                "   MANAGER_ID " +
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
                userDTO.setIdManager(rs.getInt("MANAGER_ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userDTO;

    }


}
