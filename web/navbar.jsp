<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav>
    <ul>
        <li<c:if test="${param.title == 'Home'}"> id="actual"</c:if>><a href="./">Home</a></li>
        <li<c:if test="${param.title == 'User Overview'}"> id="actual"</c:if>><a href="Controller?action=personoverview">Users</a></li>
        <li<c:if test="${param.title == 'Product Overview'}"> id="actual"</c:if>><a href="Controller?action=productoverview">Products</a></li>
        <li<c:if test="${param.title == 'Add Product'}"> id="actual"</c:if>><a href="Controller?action=addproduct">Add Product</a></li>
        <li<c:if test="${param.title == 'Sign Up'}"> id="actual"</c:if>><a href="Controller?action=signup">Sign Up</a></li>
    </ul>
</nav>
<h2>${param.title}</h2>