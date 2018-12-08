<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link rel="icon" href="./images/favicon.ico" type="image/x-icon" />
    <title>Product Overview</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script src="./js/sorttable.js"></script>
</head>
<body>
<div id="container">
    <header>
        <h1><span>Web shop</span></h1>
        <jsp:include page="navbar.jsp">
            <jsp:param name="title" value="Product Overview"/>
        </jsp:include>

    </header>
    <main>
        <c:choose>
            <c:when test="${not empty products}">
                <table id="mainTable">
                    <tr>
                        <th onclick="sortTable(0)" style="cursor:pointer">Name</th>
                        <th onclick="sortTable(1)" style="cursor:pointer">Description</th>
                        <th onclick="sortTable(2)" style="cursor:pointer">Price</th>
                        <th></th>
                    </tr>
                    <c:forEach var="product" items="${products}">
                        <tr>
                            <td><c:out value='${product.name}'/></td>
                            <td><c:out value='${product.description}'/></td>
                            <td>&euro; <c:out value='${product.price}'/></td>
                            <td>
                                <a href="Controller?action=addtocart&productid=<c:out value='${product.productId}'/>">Add to cart</a>
                            </td>
                        </tr>
                    </c:forEach>

                    <caption>Product Overview</caption>
                </table>
            </c:when>
            <c:otherwise>
                <p>There are no products in the database at the moment.</p>
            </c:otherwise>
        </c:choose>
    </main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>