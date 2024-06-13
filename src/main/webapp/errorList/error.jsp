<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error Page</title>
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

        .error-container {
            text-align: center;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }

        .error-message {
            font-size: 24px;
            margin-bottom: 20px;
            color: #ff0000;
        }

        .back-button {
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .back-button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<div class="error-container">
    <h1 class="error-message">Oops! Something went wrong.</h1>
    <p>An error occurred while processing your request.</p>
    <p>Please try again later or contact support.</p>
    <button class="back-button" onclick="goBack()">Back</button>
</div>

<script>
    // Возврат на предыдущую страницу при нажатии на кнопку "Назад"
    function goBack() {
        window.history.back();
    }
</script>

</body>
</html>