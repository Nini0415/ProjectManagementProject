<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- JQuery for show and hide -->
<script type="text/javascript">
$(document).ready(function(){
  $("#addbutton").click(function(){
  $("#hiddenpart").toggle();
  });
});
</script>
<!-- JQuery for show and hide above-->

<div class="content container_12">
	<p>${message}</p>
    
		
		
	<!-- Loop all Requests -->
	<div class="box grid_12">
		<div class="box-head"><h2>All Requests you have sent</h2></div>
        <div class="box-content no-pad">
			<table class="display">
			<thead>
	        <tr>
            	<th>No.</th>
            	<th>Required User Name</th>
              	<th>Request Send Date</th>
              	<th>Account Create Date</th>
              	<th>Initial Password</th>
            </tr>
          	</thead>
          	<tbody>
	        	<c:set var="count" value="1" scope="page" />
	        	<c:forEach var="request" items="${myRequests }">
	        	<c:set value="odd gradeX" var="rowStyle"/>
	        	<c:if test="${count%2==0}">
	        		<c:set value="even gradeC" var="rowStyle"/>
	        	</c:if>
            	<tr class="${rowStyle }">
              	<td>${count }</td>
              	<td>${request.requestUserName }</td>
              	<td>${request.sendDate }</td>
              	<td>${request.finishDate }</td>
              	<td>${request.initialPass }</td>
            </tr>
            <c:set var="count" value="${count + 1}" scope="page"/>
            </c:forEach>
          </tbody>
         </table>
        </div>
      </div>
  </div>
