package com.platforma.concedii.controller;

import com.platforma.concedii.dto.UserDTO;
import com.platforma.concedii.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        UserDTO userDTO = UserService.getInstance().logIn(userName, password);

        if(userDTO != null) {
            req.getSession().setAttribute("userName", userDTO.getUserName());
            req.getSession().setAttribute("userRole", userDTO.getUserRole());

            switch (userDTO.getUserRole()) {
                case ADMIN:
                    resp.getWriter().write("admin.jsp");
                    break;
                case MANAGER:
                    resp.getWriter().write("managerRequest.jsp");
                    break;
                case EMPLOYEE:
                    resp.getWriter().write("employeeRequest.jsp");
                    break;
                default:
                    req.setAttribute("errorMessage", "User has not enough rights.");
                    resp.getWriter().write("login.jsp");
            }
        } else {
            resp.getWriter().write("0");
        }
    }
}
