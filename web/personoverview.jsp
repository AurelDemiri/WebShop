<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <title>User Overview</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
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
            <c:when test="${not empty people}">
                <table>
                    <tr>
                        <th>E-mail</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Delete</th>
                        <th>Check Password</th>
                    </tr>
                    <c:forEach var="person" items="${people}">
                        <tr>
                            <td><c:out value='${person.email}'/></td>
                            <td><c:out value='${person.firstName}'/></td>
                            <td><c:out value='${person.lastName}'/></td>
                            <td>
                                <a href="Controller?action=deleteperson&userid=<c:out value='${person.userId}'/>">Delete</a>
                            </td>
                            <td>
                                <a href="Controller?action=checkpassword&userid=<c:out value='${person.userId}'/>">Check</a>
                            </td>
                        </tr>
                    </c:forEach>

                    <caption>Users Overview</caption>
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