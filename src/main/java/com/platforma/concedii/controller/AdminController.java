package com.platforma.concedii.controller;

import com.platforma.concedii.dto.UserDTO;
import com.platforma.concedii.service.UserService;
import com.platforma.concedii.util.UserRoles;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.platforma.concedii.util.UserRoles.ADMIN;

public class AdminController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/admin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        UserRoles userRole = UserRoles.getRoleByString(req.getParameter("userRole"));

        UserDTO userDTO = new UserDTO();
        userDTO.setUserName(userName);
        userDTO.setPassword(password);
        userDTO.setFirstName(firstName);
        userDTO.setLastName(lastName);
        userDTO.setUserRole(userRole);

        String message = UserService.getInstance().registerUser(userDTO);
        resp.getWriter().write(message);
    }
}