<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link rel="icon" href="./images/favicon.ico" type="image/x-icon"/>
    <title>Delete User</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <header>
        <h1><span>Web shop</span></h1>
        <jsp:include page="navbar.jsp">
            <jsp:param name="title" value="Are you sure?"/>
        </jsp:include>

    </header>
    <main>
        <jsp:include page="errormessage.jsp"/>
        <form method="post" action="Controller?action=deleteuser&userid=<c:out value='${param.userid}'/>">
            <input type="submit" id="ok" name="ok" value="Ok"><input type="submit" id="cancel" name="cancel"
                                                                     value="Cancel">
        </form>
    </main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>
