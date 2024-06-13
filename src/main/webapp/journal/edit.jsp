<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Crime Journal Entry</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 50%;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        input[type=text], input[type=date], input[readonly], select, button {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        input[readonly] {
            background-color: #e9e9e9;
        }
        button {
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
        }
        button:hover {
            background-color: #45a049;
        }
        .btn-cancel {
            background-color: #c0c0c0;
            color: white;
            border: none;
            cursor: pointer;
        }
        .btn-cancel:hover {
            background-color: #a9a9a9;
        }
    </style>
    <script>
        function setMinMaxDate() {
            var today = new Date().toISOString().split('T')[0];
            document.getElementById("date_crime").max = today;
            document.getElementById("date_crime").min = "1900-01-01";
        }

        // Функция для возврата на страницу списка журналов
        function cancelEdit() {
            window.location.href = "<%=request.getContextPath()%>/journal/list";
        }
    </script>
</head>
<body onload="setMinMaxDate()">

<div class="container">
    <h2>Edit Crime Journal Entry</h2>
    <form action="<%=request.getContextPath()%>/journal/edit" method="POST">
        <input type="hidden" name="id" value="${id}" />

        <label for="description">Description:</label>
        <input type="text" id="description" name="description" value="${description}" required>

        <label for="date_crime">Date of Crime:</label>
        <input type="date" id="date_crime" name="date_crime"
               value="<fmt:formatDate value="${date_crime}" pattern="yyyy-MM-dd" />" required>

        <label for="is_closed">Is Closed?</label>
        <select id="is_closed" name="is_closed">
            <option value="true" ${is_closed ? 'selected' : ''}>Yes</option>
            <option value="false" ${!is_closed ? 'selected' : ''}>No</option>
        </select>

        <button type="submit">Update</button>
        <button type="button" class="btn-cancel" onclick="cancelEdit()">Cancel</button>
    </form>

</div>

</body>
</html>