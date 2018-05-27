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
                    getServletContext().getRequestDispatcher("/register.jsp").forward(req, resp);
                    break;
                case MAMANGER:
                    resp.sendRedirect("/platforma/manager");
                    break;
                case EMPLOYEE:
                    getServletContext().getRequestDispatcher("/employeeRequest.jsp").forward(req, resp);
                    break;
                default:
                    req.setAttribute("errorMessage", "User has not enough rights.");
                    getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
            }
        } else {
            resp.getWriter().write("0");
        }
    }
}
