<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <title>Product Overview</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
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
                <table>
                    <tr>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th></th>
                    </tr>
                    <c:forEach var="product" items="${products}">
                        <tr>
                            <td>
                                <a href="Controller?action=updateproduct&productid=<c:out value='${product.productId}'/>"><c:out
                                        value='${product.name}'/></a></td>
                            <td><c:out value='${product.description}'/></td>
                            <td><c:out value='${product.price}'/></td>
                            <td>
                                <a href="Controller?action=deleteproduct&productid=<c:out value='${product.productId}'/>">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>

                    <caption>Products Overview</caption>
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