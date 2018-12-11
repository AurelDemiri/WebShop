<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link rel="icon" href="./images/favicon.ico" type="image/x-icon"/>
    <title>Order Confirmation</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script src="./js/sorttable.js"></script>
</head>
<body>
<div id="container">
    <header>
        <h1><span>Web shop</span></h1>
        <jsp:include page="navbar.jsp">
            <jsp:param name="title" value="Thank you for your purchase!"/>
        </jsp:include>

    </header>
    <main>
        <jsp:include page="errormessage.jsp"/>
        <c:if test="${error == null}">
        <p>Hi <c:out value='${sessionScope.loggedinUser.firstName}'/>, we're getting your order ready to be shipped. We will
            notify you when it has been sent.</p>
        </c:if>
    </main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>