<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${result == 1}">
			<p>New Account Request:${request.requestID} has been sent for ${request.requestUserName} </p>
</c:if>


<c:if test="${ result == 0}">
<p>Operation fail, please try again. </p></c:if>