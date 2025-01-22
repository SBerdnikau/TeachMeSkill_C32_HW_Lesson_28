package com.teachmeskills.lesson_28.servlet;

import com.teachmeskills.lesson_28.logger.LoggerUtil;
import com.teachmeskills.lesson_28.repository.ToDoRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@WebServlet("/todolist")
public class TodoListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String userName = (String) session.getAttribute("username");

        if (userName != null) {
            Set<String> tasks = new HashSet<>();
            tasks.add("Learn Java CORE");
            tasks.add("Learn Java EE");
            tasks.add("Learn Spring FrameWork");
            req.setAttribute("tasks", tasks);

            LoggerUtil.logToFile("The user -> " + userName + " went to the todolist page");
            req.getRequestDispatcher("/page/todolist.jsp").forward(req, resp);
        }else {
            LoggerUtil.logToFile("User redirected to 401 error page");
            resp.sendRedirect("error/401.html");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Set<String> tasks = new HashSet<>();
//        tasks.add("Learn Java CORE");
//        tasks.add("Learn Java EE");
//        tasks.add("Learn Spring FrameWork");
//        req.setAttribute("tasks", tasks);
//        req.getRequestDispatcher("/page/todolist.jsp").forward(req, resp);
    }
}
