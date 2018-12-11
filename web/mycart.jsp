<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link rel="icon" href="./images/favicon.ico" type="image/x-icon"/>
    <title>My Cart</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script src="./js/sorttable.js"></script>
</head>
<body>
<div id="container">
    <header>
        <h1><span>Web shop</span></h1>
        <jsp:include page="navbar.jsp">
            <jsp:param name="title" value="My Cart"/>
        </jsp:include>

    </header>
    <main>
        <c:choose>
            <c:when test="${sessionScope.cart.uniqueSize() > 0 }">
                <p><a href="Controller?action=clearcart">Clear your cart</a></p>
                <table id="mainTable">
                    <tr>
                        <th onclick="sortTable(0)" style="cursor:pointer">Product</th>
                        <th onclick="sortTable(1)" style="cursor:pointer">Amount</th>
                        <th onclick="sortTable(2)" style="cursor:pointer">Price</th>
                        <th></th>
                        <th></th>
                    </tr>
                    <c:forEach var="item" items="${sessionScope.cart.getAll()}">
                        <tr>
                            <td><c:out value='${item.name}'/></td>
                            <td><c:out value='${sessionScope.cart.getCount(item)}'/></td>
                            <td>&euro; <c:out value='${sessionScope.cart.getCount(item) * item.price}'/></td>
                            <td>
                                <a href="Controller?action=removefromcart&productid=<c:out value='${item.productId}'/>&amount=1">Remove
                                    1</a>
                            </td>
                            <td>
                                <a href="Controller?action=removefromcart&productid=<c:out value='${item.productId}'/>">Remove
                                    All</a>
                            </td>
                        </tr>
                    </c:forEach>

                    <caption>My cart</caption>
                </table>
                <br/>
                <br/>
                <h3>Total price: &euro; <c:out value='${sessionScope.cart.calculateTotal()}'/></h3>
                <a class="button button1" href="Controller?action=directorder">Direct Order</a>
            </c:when>
            <c:otherwise>
                <p>There are currently no items in your cart.</p>
            </c:otherwise>
        </c:choose>
    </main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>