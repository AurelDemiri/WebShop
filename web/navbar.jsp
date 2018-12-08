<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav>
    <ul>
        <li<c:if test="${param.title == 'Home'}"> id="actual"</c:if>><a href="./">Home</a></li>
        <li<c:if test="${param.title == 'User Overview'}"> id="actual"</c:if>><a href="Controller?action=useroverview">Users</a></li>
        <li<c:if test="${param.title == 'Product Overview'}"> id="actual"</c:if>><a href="Controller?action=productoverview">Products</a></li>
        <c:choose>
            <c:when test="${sessionScope.loggedinUser != null}">
                <li<c:if test="${param.title == 'My Cart'}"> id="actual"</c:if>><a href="Controller?action=mycart">My Cart</a></li>
                <li<c:if test="${param.title == 'Log Out'}"> id="actual"</c:if>><a href="Controller?action=logout">Logout</a></li>
            </c:when>
            <c:otherwise>
                <li<c:if test="${param.title == 'Sign Up'}"> id="actual"</c:if>><a href="Controller?action=signup">Sign Up</a></li>
                <li<c:if test="${param.title == 'Login'}"> id="actual"</c:if>><a href="Controller?action=login">Login</a></li>
            </c:otherwise>
        </c:choose>
    </ul>
</nav>
<h2>${param.title}</h2>