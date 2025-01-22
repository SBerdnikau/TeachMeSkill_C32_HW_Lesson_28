<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Todo-list</title>
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="../css/todolist.css">
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
        <form action="" method="post">
            <input type="text" name="newTask" id="new-task" placeholder="Input new task.." required>
            <button class="button button-add" type="button">Add task</button>
        </form>
    </div>

    <div class="wrapper-center">
        <table class="todo-list">
        <c:forEach var="task" items="${tasks}">
            <tr class="todo-row">
                <td class="todo-number">1</td>
                <td class="todo-text">${task}</td>
                <td class="todo-buttons">
                    <form action="" method="post">
                        <button type="button" class="button button-delete">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </table>
    </div>

</main>

</body>
</html>