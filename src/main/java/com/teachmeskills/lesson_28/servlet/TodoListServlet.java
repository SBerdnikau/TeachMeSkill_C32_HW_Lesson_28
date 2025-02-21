package com.teachmeskills.lesson_28.servlet;

import com.teachmeskills.lesson_28.logger.LoggerUtil;

import com.teachmeskills.lesson_28.repository.TaskRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Set;

@WebServlet("/todolist")
public class TodoListServlet extends HttpServlet {

    private TaskRepository taskRepository;

    @Override
    public void init() throws ServletException {
        taskRepository = new TaskRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String login = (String) session.getAttribute("login");
        if (login != null) {
            Set<String> tasks = taskRepository.getTasksByUsername(login);
            req.setAttribute("tasks", tasks);
            LoggerUtil.logToFile("The user -> " + req.getSession().getAttribute("login") + " went to the todolist page");
            req.getRequestDispatcher("/page/todolist.jsp").forward(req, resp);
        }else {
            LoggerUtil.logToFile("User redirected to 401 error page");
            resp.sendRedirect("error/401.html");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newTask = req.getParameter("newTask");
        String deleteTask = req.getParameter("deleteTask");
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("username");

        Set<String> tasks = taskRepository.getTasksByUsername(username);

        if (newTask != null){
            tasks.add(newTask);
            LoggerUtil.logToFile("User " + username + " add new task " + newTask);
            taskRepository.getTaskList().put(username, tasks);
        }

        if (deleteTask != null){
            tasks.remove(deleteTask);
            LoggerUtil.logToFile("User " + username + " remove task " + deleteTask);
            taskRepository.getTaskList().put(username, tasks);
        }

        req.setAttribute("tasks", tasks);
        req.getRequestDispatcher("/page/todolist.jsp").forward(req, resp);
    }
}
