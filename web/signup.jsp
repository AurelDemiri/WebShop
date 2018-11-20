<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <header>
        <h1><span>Web shop</span></h1>
        <jsp:include page="navbar.jsp">
            <jsp:param name="title" value="Sign Up"/>
        </jsp:include>

    </header>
    <main>
        <jsp:include page="errormessage.jsp" />

        <form method="post" action="Controller?action=signup" novalidate="novalidate">
            <!-- novalidate in order to be able to run tests correctly -->
            <p><label for="userId">User id</label><input type="text" id="userId" name="userId"
                                                         required value="<c:out value='${keptUserId}'/>"></p>
            <p><label for="firstName">First Name</label><input type="text" id="firstName" name="firstName"
                                                               required value="<c:out value='${keptFirstName}'/>"></p>
            <p><label for="lastName">Last Name</label><input type="text" id="lastName" name="lastName"
                                                             required value="<c:out value='${keptLastName}'/>"></p>
            <p><label for="email">Email</label><input type="email" id="email" name="email"
                                                      required value="<c:out value='${keptEmail}'/>"></p>
            <p><label for="password">Password</label><input type="password" id="password"
                                                            required name="password"></p>
            <p><input type="submit" id="signUp" value="Sign Up"></p>

        </form>
    </main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>
