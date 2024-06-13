<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .login-container {
            width: 300px;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        h2 {
            color: #333;
            margin-bottom: 20px;
        }

        .login-form {
            width: 100%;
        }

        .login-form label {
            font-weight: bold;
            display: block;
            margin-bottom: 5px;
        }

        .login-form input[type="text"],
        .login-form input[type="password"],
        .login-form input[type="email"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 3px;
            box-sizing: border-box;
        }

        .login-form input[type="submit"] {
            width: 100%;
            padding: 10px;
            border: none;
            background-color: #007bff;
            color: #fff;
            cursor: pointer;
            border-radius: 3px;
        }

        .login-form input[type="submit"]:hover {
            background-color: #0056b3;
        }

        .register-link {
            margin-top: 10px;
            width: 100%;
            text-align: center;
        }

        .register-link a {
            display: inline-block;
            padding: 10px;
            background-color: #f0f0f0;
            color: #007bff;
            text-decoration: none;
            border-radius: 3px;
            width: 94%;
            text-align: center;
        }

        .register-link a:hover {
            background-color: #e0e0e0;
        }

        .error-message {
            color: red;
            text-align: center;
            margin-top: 10px;
        }
    </style>
</head>
<body>
<div class="login-container">
    <h2>Login</h2>

    <!-- Сообщение об ошибке -->
    <% if (request.getAttribute("errorMessage") != null) { %>
    <p class="error-message"><%= request.getAttribute("errorMessage") %></p>
    <% } %>

    <!-- Форма входа -->
    <form class="login-form" action="<%= request.getContextPath() %>/login" method="POST">
        <input type="text" id="login" name="login" placeholder="Username" required>
        <input type="password" id="password" name="password" placeholder="Password" required>
        <input type="email" id="email" name="email" placeholder="Email" required>
        <input type="submit" value="Login">
    </form>

    <!-- Ссылка на страницу регистрации -->
    <div class="register-link">
        <a href="<%= request.getContextPath() %>/register">Register</a>
    </div>
</div>
</body>
</html>