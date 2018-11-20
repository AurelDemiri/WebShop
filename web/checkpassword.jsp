<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <title>Check Password</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <header>
        <h1><span>Web shop</span></h1>
        <jsp:include page="navbar.jsp">
            <jsp:param name="title" value="Check Password"/>
        </jsp:include>

    </header>
    <main>
        <jsp:include page="errormessage.jsp"/>
        <c:choose>
            <c:when test="${message == null}">
                <p>Fill out your password:</p>
                <form method="post" action="Controller?action=checkpassword&userid=<c:out value='${param.userid}'/>"
                      novalidate="novalidate">
                    <!-- novalidate in order to be able to run tests correctly -->
                    <p><label for="password">Password</label><input type="password" id="password" name="password"
                                                                    required></p>
                    <p><input type="submit" id="check" value="Check"></p>
                </form>
            </c:when>
            <c:otherwise>
                <c:out value='${message}'/>
            </c:otherwise>
        </c:choose>
    </main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>
