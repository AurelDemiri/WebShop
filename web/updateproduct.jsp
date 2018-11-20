<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <title>Update Product</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <header>
        <h1><span>Web shop</span></h1>
        <jsp:include page="navbar.jsp">
            <jsp:param name="title" value="Update Product"/>
        </jsp:include>

    </header>
    <main>
        <jsp:include page="errormessage.jsp" />
        <form method="post" action="Controller?action=updateproduct&productid=${param.productid}" novalidate="novalidate">
            <!-- novalidate in order to be able to run tests correctly -->
            <p><label for="name">Name</label><input type="text" id="name" name="name"
                                                         required value="<c:out value='${keptName}'/>"></p>
            <p><label for="description">Description</label><input type="text" id="description" name="description"
                                                               required value="<c:out value='${keptDescription}'/>"></p>
            <p><label for="price">Price</label><input type="text" id="price" name="price"
                                                             required value="<c:out value='${keptPrice}'/>"></p>
            <p><input type="submit" id="updateProduct" value="Update Product"></p>

        </form>
    </main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>
