<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link rel="icon" href="./images/favicon.ico" type="image/x-icon" />
    <title>User Overview</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script src="./js/sorttable.js"></script>
</head>
<body>
<div id="container">
    <header>
        <h1><span>Web shop</span></h1>
        <jsp:include page="navbar.jsp">
            <jsp:param name="title" value="User Overview"/>
        </jsp:include>

    </header>
    <main>
        <c:choose>
            <c:when test="${not empty users}">
                <table id="mainTable">
                    <tr>
                        <th onclick="sortTable(0)" style="cursor:pointer">E-mail</th>
                        <th onclick="sortTable(1)" style="cursor:pointer">First Name</th>
                        <th onclick="sortTable(2)" style="cursor:pointer">Last Name</th>
                        <th>Check Password</th>
                    </tr>
                    <c:forEach var="user" items="${users}">
                        <tr>
                            <td><c:out value='${user.email}'/></td>
                            <td><c:out value='${user.firstName}'/></td>
                            <td><c:out value='${user.lastName}'/></td>
                            <td>
                                <a href="Controller?action=checkpassword&userid=<c:out value='${user.userId}'/>">Check</a>
                            </td>
                        </tr>
                    </c:forEach>

                    <caption>User Overview</caption>
                </table>
            </c:when>
            <c:otherwise>
                <p>There are no users in the database at the moment.</p>
            </c:otherwise>
        </c:choose>
    </main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>