<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript">
$(document).ready(function(){
  $("#addbutton").click(function(){
  $("#hiddenpart").toggle();
  });
});
</script>


 <div class="content container_12">	
     
     
     <!-- Project Detail box -->
     <div class="box grid_12">
     	<div class="box-head"><h2>${project.name}</h2></div>
        <div class="box-content">
          <p>Create Time: &nbsp; ${project.createDate }
          <br>
          Current Status: &nbsp; ${project.status }
          <br>
          project ID: &nbsp; ${project.projectID }
          <br><br></p>
	  	</div>
  
      </div>
		
		<p>${message }</p>		
		
		<!-- Loop all task -->
	<c:forEach var="tasktodo" items="${tasktodoList}" >   
		<div class="box grid_12">
		<div class="box-head"><h2>${tasktodo.task.name }
        	&nbsp;&nbsp;<a href="deleteTask.htm?taskID=${tasktodo.task.taskID }&projectID=${project.projectID}">
        	Delete</a></h2></div>
        <div class="box-content no-pad">
          <table class="display">
          <thead>
            <tr>
              <th>No.</th>
              <th>To do</th>
              <th>Assignee</th>
              <th>Start Date</th>
              <th>Due Date</th>
              <th>Status</th>
              <th>Operation</th>
            </tr>
          </thead>
          <tbody>
          	<c:set var="count" value="1" scope="page" />
			<c:forEach var="todo" items="${tasktodo.todoList }">
		        <c:set value="odd gradeX" var="rowStyle"/>
		        <c:if test="${count%2==0}">
		        	<c:set value="even gradeC" var="rowStyle"/>
		        </c:if>
            	<tr class="${rowStyle }">
	       			<td>${count }</td>
	              	<td><a href="todo.htm?todoID=${todo.todoID }&taskName=${tasktodo.task.name}&projectID=${project.projectID}&message=ok">
	              		${todo.description }</a></td>
	              	<td>${tasktodo.assigneeList[count-1].userName }</td>
	              	<td class="center">${todo.startDate }</td>
	              	<td class="center">${todo.dueDate }</td>
	              	<td class="center">${todo.status }</td>
	              	<td class="center">
		              	<a href="deleteTodo.htm?todoID=${todo.todoID }&taskID=${tasktodo.task.taskID }&projectID=${project.projectID}">
		              	Remove</a>
	              	</td>
	            </tr>
            <c:set var="count" value="${count + 1}" scope="page"/>
            </c:forEach>
          </tbody>
         </table>
        </div>
      </div> 
      </c:forEach>
	
</div>   
