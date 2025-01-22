package com.teachmeskills.lesson_28.listener;

import com.teachmeskills.lesson_28.logger.LoggerUtil;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener{
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        String message = "The session has been created:  " + se.getSession().getId();
        LoggerUtil.logToFile(message);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        String message = "The session is destroyed:  " + se.getSession().getId();
        LoggerUtil.logToFile(message);
    }
}
