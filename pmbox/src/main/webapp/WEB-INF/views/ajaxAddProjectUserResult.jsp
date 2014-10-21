<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${result == 1}">
			<p>User ${ajaxUserName} has been added as ${ajaxprojectUserRole} </p>
</c:if>


<c:if test="${ result == 0}">
<p>Operation fail, please try again. </p></c:if>