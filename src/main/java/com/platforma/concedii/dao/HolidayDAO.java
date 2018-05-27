package com.platforma.concedii.dao;

import com.platforma.concedii.dto.HolidayDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HolidayDAO {

    private static final HolidayDAO holidayDAOInstance = new HolidayDAO();
    private Connection dbConnection;
    private HolidayDAO(){};
    public static HolidayDAO getInstance() {
        return holidayDAOInstance;
    }

    /**/
    public void saveHoliday(HolidayDTO holidayDTO) {
        String sqlInsert = "INSERT INTO HOLIDAYS (START_DATE, END_DATE, USER_ID, STATUS) VALUES (?, ?, ?, ?)";
        try(PreparedStatement ps = dbConnection.prepareStatement(sqlInsert)) {
            ps.setDate(1, (Date) holidayDTO.getStartDate());
            ps.setDate(2, (Date) holidayDTO.getEndDate());
            ps.setInt(3, holidayDTO.getUserId());
            ps.setString(4, holidayDTO.getStatus());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**/
    public HolidayDTO getHolidayById(int id) {

        HolidayDTO holidayDTO= null;
        String sqlSelect = "" +
                "SELECT " +
                "   ID, " +
                "   START_DATE, " +
                "   END_DATE, " +
                "   USER_ID, " +
                "   STATUS " +
                "FROM HOLIDAYS " +
                "WHERE ID = ?";
        try(PreparedStatement ps = dbConnection.prepareStatement(sqlSelect)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                holidayDTO = new HolidayDTO();
                holidayDTO.setId(rs.getInt("ID"));
                holidayDTO.setStartDate(rs.getDate("START_DATE"));
                holidayDTO.setEndDate(rs.getDate("END_DATE"));
                holidayDTO.setUserId(rs.getInt("USER_ID"));
                holidayDTO.setStatus(rs.getString("STATUS"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return holidayDTO;


    }

    /**/
    public List<HolidayDTO> getAllHolidaysForUserIfSpecified(int userId) {

        List<HolidayDTO> holidayDTOList = new ArrayList<>();
        String sqlSelect = "" +
                "SELECT " +
                "   ID, " +
                "   START_DATE, " +
                "   END_DATE, " +
                "   USER_ID, " +
                "   STATUS " +
                "FROM HOLIDAYS " +
                "WHERE USER_ID = ?";
        try(PreparedStatement ps = dbConnection.prepareStatement(sqlSelect)) {
            if(userId > 0) {
                ps.setInt(1, userId);
            } else {
                ps.setString(1, "USER_ID");
            }
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                HolidayDTO holidayDTO = new HolidayDTO();
                holidayDTO.setId(rs.getInt("ID"));
                holidayDTO.setStartDate(rs.getDate("START_DATE"));
                holidayDTO.setEndDate(rs.getDate("END_DATE"));
                holidayDTO.setUserId(rs.getInt("USER_ID"));
                holidayDTO.setStatus(rs.getString("STATUS"));
                holidayDTOList.add(holidayDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return holidayDTOList;


    }

}
