<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html lang="vi">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Đăng nhập Admin - LaptopShop</title>
            <style>
                body {
                    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                    background-color: #f4f6f9;
                    margin: 0;
                    display: flex;
                    justify-content: center;
                    align-items: center;
                    height: 100vh;
                }

                .login-card {
                    background-color: white;
                    padding: 40px;
                    border-radius: 8px;
                    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                    width: 350px;
                    text-align: center;
                }

                .brand {
                    font-size: 32px;
                    font-weight: bold;
                    color: #333;
                    margin-bottom: 30px;
                }

                .input-group {
                    margin-bottom: 20px;
                    text-align: left;
                }

                input {
                    width: 100%;
                    padding: 12px;
                    border: 1px solid #ced4da;
                    border-radius: 4px;
                    box-sizing: border-box;
                }

                input:focus {
                    outline: none;
                    border-color: #4e73df;
                    box-shadow: 0 0 0 0.2rem rgba(78, 115, 223, 0.25);
                }

                .login-btn {
                    width: 100%;
                    padding: 12px;
                    background-color: #4e73df;
                    color: white;
                    border: none;
                    border-radius: 4px;
                    font-size: 16px;
                    font-weight: bold;
                    cursor: pointer;
                }

                .login-btn:hover {
                    background-color: #2e59d9;
                }

                .error-msg {
                    color: #e74a3b;
                    background-color: #fadbd8;
                    padding: 10px;
                    border-radius: 4px;
                    margin-bottom: 15px;
                    font-size: 14px;
                }
            </style>
        </head>

        <body>
            <div class="login-card">
                <div class="brand">LaptopShop</div>

                <form action="/admin/login" method="POST">

                    <c:if test="${not empty error}">
                        <div class="error-msg">
                            ${error}
                        </div>
                    </c:if>

                    <div class="input-group">
                        <input type="email" name="email" placeholder="Admin Email" required>
                    </div>
                    <div class="input-group">
                        <input type="password" name="password" placeholder="Password" required>
                    </div>
                    <button type="submit" class="login-btn">Đăng Nhập</button>
                </form>
            </div>
        </body>

        </html>