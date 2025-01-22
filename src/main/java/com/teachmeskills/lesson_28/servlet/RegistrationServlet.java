package com.teachmeskills.lesson_28.servlet;

import com.teachmeskills.lesson_28.logger.LoggerUtil;
import com.teachmeskills.lesson_28.repository.UserRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

      @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");

        LoggerUtil.logToFile("Registration attempt -> username=" + username);

        boolean isAddUser = UserRepository.addUser(username, password, confirmPassword);

        if (isAddUser) {
            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            LoggerUtil.logToFile("Successful registration -> " + username);
            req.getRequestDispatcher("/page/about.html").forward(req, resp);
        }else {
            req.setAttribute("error",true);
            LoggerUtil.logToFile("Registration error");
            resp.sendRedirect("/registration.html?error=true");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
