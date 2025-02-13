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
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final UserRepository userRepository;

    public LoginServlet() {
        userRepository = new UserRepository();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String username = req.getParameter("username");
       String password = req.getParameter("password");

         LoggerUtil.logToFile("Login attempt -> username=" + username);
         UserRepository userRepository = new UserRepository();
         boolean isValid = userRepository.isValid(username, password);

        if (isValid){
            HttpSession newSession = req.getSession();
            newSession.setAttribute("username", username);
            LoggerUtil.logToFile("Successful login -> " + username);
            req.getRequestDispatcher("/page/about.html").forward(req,resp);
        }else {
            LoggerUtil.logToFile("The user was not found -> " + username);
            resp.sendRedirect("/login.html?error=true");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("username");
        if (username == null){
            req.getRequestDispatcher("/login.html").forward(req,resp);
        }else {
            req.getRequestDispatcher("/page/about.html").forward(req,resp);
        }
    }
}