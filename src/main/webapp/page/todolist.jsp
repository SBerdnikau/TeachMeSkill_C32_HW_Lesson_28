<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Todo-list</title>
    <style>
        <%@include file="/css/style.css"%>
        <%@include file="/css/todolist.css"%>
    </style>
</head>

<body>

<header class="header-wrapper">
    <p class="header-text">Welcome, dear <span class="red-span">${sessionScope["username"]}</span></p>
    <div id="header-buttons">
        <form action="about" method="GET">
            <button type="submit" class="button button-about">About me</button>
        </form>
        <form action="logout" method="POST" >
            <button type="submit" class="button button-logout">Logout</button>
        </form>
    </div>
</header>

<main class="main-wrapper">

    <h2 class="main-header">My Todo <span class="red-span">LIST</span>!</h2>

    <div class="wrapper-center">
        <form action="todolist" method="post">
            <input type="text" name="newTask" id="new-task" placeholder="Input description task.." required>
            <input type="hidden" name="action" value="add">
            <button class="button button-add" type="submit">Add task</button>
        </form>
    </div>

    <div class="wrapper-center">
        <c:if test="${tasks == null || tasks.isEmpty()}">
                <div class="todolist-empty">The task list is empty, add a new task</div>
        </c:if>
        <ul class="todo-list">
        <c:forEach var="task" items="${tasks}">
            <li class="todo-row">
                <p class="todo-text">${task}</p>
                <form action="todolist" method="POST">
                    <input type="hidden" name="deleteTask" value="${task}">
                    <button type="submit" class="button button-delete">Delete</button>
                </form>
            </li>
        </c:forEach>
        </ul>
    </div>

</main>

</body>
</html>