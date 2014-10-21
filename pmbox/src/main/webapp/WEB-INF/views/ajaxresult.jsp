<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${not empty ajaxUser}">
			<div style="float:left;"><p>&nbsp; &nbsp;user ${ajaxUser.userName} exists, please choose role&nbsp;</p></div>
	      	<input type="hidden" value="${ajaxUser.userID}" id="requireUserID"/>
	      	<input type="hidden" value="${ajaxUser.userName}" id="requireUserName"/>
	      	<input type="hidden" value="${ajaxProjectID }" id="requireProjectID"/>
			<select id="requireProjectUserRole" name="role">
				<option value='ProjectMember'>Project Member</option>
				<option value='ProjectClient'>Project Client</option>
				<option value='ProjectAdmin'>Project Admin</option>
			</select>
			<input type="submit" class="button orange" value="Add" onclick="ajaxAddProjectUser()" >
</c:if>


<c:if test="${ empty ajaxUser}">
<div style="float:left;"><p>&nbsp; &nbsp;user ${userName} does not exist, send request to create account for this user?&nbsp;</p></div>
<input type="hidden" value="${userName}" id="requireUserName"/>
<input type="submit" class="button orange" value="Send Request" onclick="ajaxFunction2()" ></c:if>