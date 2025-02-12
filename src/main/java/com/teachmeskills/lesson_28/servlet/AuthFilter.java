package com.teachmeskills.lesson_28.servlet;

import com.teachmeskills.lesson_28.logger.LoggerUtil;
import com.teachmeskills.lesson_28.repository.UserRepository;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter({"/todolist", "/about"})
public class AuthFilter implements Filter {

    private final UserRepository userRepository;

    public AuthFilter() {
        userRepository = new UserRepository();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session =  request.getSession();
        String login = (String) session.getAttribute("login");

        if(login != null && userRepository.isContainsUserByName(login)) {
            LoggerUtil.logToFile("Filter -> The user  " + login + "was redirect to ...");
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            request.getRequestDispatcher("/login.jsp").forward(request, servletResponse);
        }
    }
}
