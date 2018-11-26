<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <header>
        <h1>
            <span>Web shop</span>
        </h1>
        <jsp:include page="navbar.jsp">
            <jsp:param name="title" value="Home"/>
        </jsp:include>

    </header>
    <main> Sed ut perspiciatis unde omnis iste natus error sit
        voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque
        ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae
        dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit
        aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos
        qui ratione voluptatem sequi nesciunt.
        <br />
        <form method="post" action="Controller?action=changepreferences" novalidate>
            <p>Do you want to see a quote?</p>
            <p><input type="radio" name="showquote" value="yes"<c:if test="${showquote == 'yes'}"> checked</c:if>> Yes
               <input type="radio" name="showquote" value="no"<c:if test="${showquote == 'no'}"> checked</c:if>> No
            </p>
            <p><input type="submit" id="send" value="Send"></p>
        </form>
        <c:if test="${quote != null}"><p><c:out value='${quote}'/></p></c:if>
    </main>
    <footer> &copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
</div>
</body>
</html>