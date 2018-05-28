package com.platforma.concedii.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.platforma.concedii.dao.UserDAO;
import com.platforma.concedii.dto.HolidayDTO;
import com.platforma.concedii.dto.UserDTO;
import com.platforma.concedii.service.HolidayService;
import com.platforma.concedii.service.UserService;
import com.platforma.concedii.util.HolidayStates;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class EmployeeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/employeeRequest.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String startDateString = req.getParameter("startDate");
        String endDateString = req.getParameter("endDate");
        String status = req.getParameter("status");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        int userId = UserDAO.getInstance().getUserByUserName(userName).getId();
        LocalDate startDate = LocalDate.parse(startDateString, formatter);
        LocalDate endDate = LocalDate.parse(endDateString, formatter);

        HolidayDTO holidayDTO = new HolidayDTO();
        holidayDTO.setUserId(userId);
        holidayDTO.setStartDate(startDate);
        holidayDTO.setEndDate(endDate);
        holidayDTO.setStatus(HolidayStates.getRoleByString(status));

        String message = HolidayService.getInstance().saveHoliday(holidayDTO);

        resp.getWriter().write(message);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserDTO> users = UserService.getInstance().getAllUsers();
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(users);
        resp.getWriter().write(json);
    }
}
