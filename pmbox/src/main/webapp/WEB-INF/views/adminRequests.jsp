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
		<div class="box-head"><h2>All Open Requests</h2></div>
        <div class="box-content no-pad">
			<table class="display">
			<thead>
	        <tr>
            	<th>No.</th>
              	<th>Sender</th>
              	<th>Send Date</th>
              	<th>Required User Name</th>
              	<th>Operation</th>
            </tr>
          	</thead>
          	<tbody>
	        	<c:set var="count" value="1" scope="page" />
	        	<c:forEach var="request" items="${openRequests }">
	        	<c:set value="odd gradeX" var="rowStyle"/>
	        	<c:if test="${count%2==0}">
	        		<c:set value="even gradeC" var="rowStyle"/>
	        	</c:if>
            	<tr class="${rowStyle }">
              	<td>${count }</td>
              	<td>${senders[count-1].userName }</td>
              	<td>${request.sendDate }</td>
              	<td>${request.requestUserName }</td>
	            <td class="center">
		              <a href="createForRequest.htm?requestID=${request.requestID }">Create</a>
	        	</td>
            </tr>
            <c:set var="count" value="${count + 1}" scope="page"/>
            </c:forEach>
          </tbody>
         </table>
        </div>
      </div>
  </div>
