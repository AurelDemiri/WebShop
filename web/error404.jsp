<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link rel="icon" href="./images/favicon.ico" type="image/x-icon" />
    <title>404 Not Found</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <header>
        <h1>
            <span>Web shop</span>
        </h1>
        <jsp:include page="navbar.jsp">
            <jsp:param name="title" value="404 Not Found"/>
        </jsp:include>
    </header>
    <main>
        <p>This page could not be found.</p>
        <img src="https://i.imgur.com/jOpIuFy.gif" width="500" alt="shitty 404 gif">
    </main>
    <footer> &copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
</div>
</body>
</html>