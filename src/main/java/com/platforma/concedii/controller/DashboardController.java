package com.platforma.concedii.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.platforma.concedii.dao.UserDAO;
import com.platforma.concedii.domain.HolidayFilter;
import com.platforma.concedii.dto.HolidayDTO;
import com.platforma.concedii.dto.UserDTO;
import com.platforma.concedii.service.HolidayService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DashboardController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/dashboard.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String status = req.getParameter("status");
        String useDateRange = req.getParameter("useDateRange");
        String startDateString = req.getParameter("startDate");
        String endDateString = req.getParameter("endDate");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        int userId = 0;
        if(!userName.equals("All")) {
            userId = UserDAO.getInstance().getUserByUserName(userName).getId();
        }
        LocalDate startDate = LocalDate.parse(startDateString, formatter);
        LocalDate endDate = LocalDate.parse(endDateString, formatter);

        HolidayFilter holidayFilter = new HolidayFilter();
        List<Integer> userList = new ArrayList<>();
        if(userId > 0) {
            userList.add(userId);
        }
        holidayFilter.setUserIdList(userList);

        List<String> stateList = new ArrayList<>();
        if(!status.equals("All")) {
            stateList.add(status);
        }
        holidayFilter.setStatusList(stateList);

        holidayFilter.setMinDate(startDate);
        holidayFilter.setMaxDate(endDate);
        holidayFilter.setUseDateRange(Boolean.parseBoolean(useDateRange));

        List<HolidayDTO> holidayDTOList = HolidayService.getInstance().getAllHolidaysByFilter(holidayFilter);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(holidayDTOList);
        resp.getWriter().write(json);

    }
}
