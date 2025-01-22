package com.teachmeskills.lesson_28.servlet;

import com.teachmeskills.lesson_28.logger.LoggerUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession();

            if (session != null){
                String username = session.getAttribute("username").toString();
                LoggerUtil.logToFile("The user logs out of the system -> " + username);
                session.invalidate();
                LoggerUtil.logToFile("User session " + username + " completed successfully");
            }

            resp.setStatus(HttpServletResponse.SC_OK);
            resp.sendRedirect("/login.html");
        }catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            LoggerUtil.logToFile("User redirected to 500 error page");
            resp.sendRedirect("error/500.html");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
