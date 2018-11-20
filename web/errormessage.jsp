<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${error != null}">
    <div class="alert-danger">
        <ul>
            <li><c:out value='${error}'/></li>
        </ul>
    </div>
</c:if>