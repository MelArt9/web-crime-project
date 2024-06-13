<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Crime Journal Entry</title>
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
        input[type=text], input[type=date], select, button {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        button {
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        button:hover {
            background-color: #45a049;
        }
        h2 {
            text-align: center;
            color: #333;
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
        function cancelCreate() {
            window.location.href = "<%=request.getContextPath()%>/journal/list";
        }
    </script>
</head>
<body onload="setMinMaxDate()">

<div class="container">
    <h2>Create New Crime Journal Entry</h2>
    <form action="<%=request.getContextPath()%>/journal/create" method="POST">
        <label for="description">Description:</label>
        <input type="text" id="description" name="description" required>

        <label for="date_crime">Date of Crime:</label>
        <input type="date" id="date_crime" name="date_crime" required>

        <label for="is_closed">Is Closed?</label>
        <select id="is_closed" name="is_closed">
            <option value="false">No</option>
            <option value="true">Yes</option>
        </select>

        <button type="submit">Create</button>
        <button type="button" class="btn-cancel" onclick="cancelCreate()">Cancel</button>
    </form>
</div>

</body>
</html>
