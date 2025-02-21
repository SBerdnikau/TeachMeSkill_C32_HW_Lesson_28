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
       String login = req.getParameter("login");
       String password = req.getParameter("password");

         LoggerUtil.logToFile("Login attempt -> login=" + login);
         UserRepository userRepository = new UserRepository();
         boolean isValid = userRepository.isValid(login, password);

        if (isValid){
            HttpSession newSession = req.getSession();
            newSession.setAttribute("login", login);
            LoggerUtil.logToFile("Successful login -> " + login);
            req.getRequestDispatcher("/page/about.html").forward(req,resp);
        }else {
            LoggerUtil.logToFile("The user was not found -> " + login);
            resp.sendRedirect("/login.html?error=true");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String login = (String) session.getAttribute("login");
        if (login == null){
            req.getRequestDispatcher("/login.html").forward(req,resp);
        }else {
            req.getRequestDispatcher("/page/about.html").forward(req,resp);
        }
    }
}