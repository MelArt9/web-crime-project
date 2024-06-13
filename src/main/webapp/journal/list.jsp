<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Crime Journal</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 650px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
            color: #333;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            padding: 10px;
            border-bottom: 1px solid #ddd;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px;
            background-color: #f2f2f2;
        }

        .header h1 {
            margin: 0;
        }

        .action-buttons {
            display: flex;
            gap: 10px;
        }

        .button {
            padding: 5px 10px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            cursor: pointer;
            border-radius: 5px;
            border: none;
            transition: background-color 0.3s;
        }

        .edit-button {
            background-color: #4CAF50;
            color: white;
        }

        .edit-button:hover {
            background-color: #45a049;
        }

        .delete-button {
            background-color: #ff6347;
            color: white;
        }

        .delete-button:hover {
            background-color: #d43f3a;
        }

        .create-button {
            padding: 5px 10px;
            background-color: #3bc0c0;
            color: white;
            border: none;
            border-radius: 50px;
            cursor: pointer;
            text-decoration: none;
        }

        .create-button:hover {
            background-color: #2d9191;
        }

        .logout-button {
            padding: 5px 10px;
            background-color: #f44336;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
            margin-left: 10px;
        }

        .logout-button:hover {
            background-color: #dc210e;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="header">
        <h1>Crime Journal</h1>
        <div>
            <a href="<%=request.getContextPath()%>/journal/create" class="create-button">+</a>
            <form action="<%=request.getContextPath()%>/logout" method="POST" style="display:inline;">
                <button type="submit" class="button logout-button">Logout</button>
            </form>
        </div>
    </div>
    <c:choose>
        <c:when test="${not empty crimes}">
            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Description</th>
                    <th>Date of Crime</th>
                    <th>Is Closed?</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${crimes}" var="crime" varStatus="status">
                    <tr>
                        <td>${status.index + 1}</td>
                        <td><c:out value="${crime.description}"/></td>
                        <td><c:out value="${crime.date_crime}"/></td>
                        <td><c:out value="${crime.is_closed ? 'Yes' : 'No'}"/></td>
                        <td>
                            <div class="action-buttons">
                                <form action="<%=request.getContextPath()%>/journal/edit" method="GET">
                                    <input type="hidden" name="Id" value="${crime.id}" />
                                    <button type="submit" class="button edit-button">Edit</button>
                                </form>
                                <form action="<%=request.getContextPath()%>/journal/delete" method="POST">
                                    <input type="hidden" name="id" value="${crime.id}" />
                                    <button type="submit" class="button delete-button">Delete</button>
                                </form>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <p class="no-crimes">No crimes found.</p>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>