<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript">
$(document).ready(function(){
  $("#addbutton").click(function(){
  $("#hiddenpart").toggle();
  });
});
</script>

<!-- AJAX script -->
<script>
function loadXMLDoc()
{
	var xmlhttp;
	var userName = document.getElementById("ajaxUserName").value; 
	var projectID = document.getElementById("ajaxprojectID").value;
 	//alert(userName);
 	//alert(projectID);
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
	    document.getElementById("ajaxResponse").innerHTML=xmlhttp.responseText;
	    }
	  }
	xmlhttp.open("GET","ajaxSearchUser.htm?userName="+userName+"&projectID="+projectID,true);
	xmlhttp.send();
}

function ajaxFunction2()
{
	var xmlhttp;
	var userName = document.getElementById("requireUserName").value; 
	//alert(userName);
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
	    document.getElementById("ajaxResponse").innerHTML=xmlhttp.responseText;
	    }
	  }
	xmlhttp.open("GET","ajaxRequest.htm?userName="+userName,true);
	xmlhttp.send();
}


function ajaxAddProjectUser()
{
	var xmlhttp;
	var userID = document.getElementById("requireUserID").value;
	var userName = document.getElementById("requireUserName").value; 
	var projectRole = document.getElementById("requireProjectUserRole").value;
	var projectID = document.getElementById("requireProjectID").value;
		
	//alert(userID);
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
	    document.getElementById("ajaxResponse").innerHTML=xmlhttp.responseText;
	    }
	  }
	xmlhttp.open("GET","ajaxAddProjectUser.htm?projectUserID="+userID+"&projectUserRole="+projectRole+"&projectID="+projectID+"&projectUserName="+userName,true);
	xmlhttp.send();
}
</script>
<!-- AJAX script above -->

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
          <div style="float:left">
	      <form action="deleteProject.htm" method="post">
	      	<input type="hidden" name="projectID" value="${project.projectID }"/>
	      	<input type="submit" class="button green" value="Delete" />
	      </form> </div>
	      <div style="float:left">
	      <form action="project.htm?projectID=${project.projectID }" method="post" >
	      	 &nbsp; &nbsp;<input type="submit" class="button green" value="Edit" />
	      </form></div>
	      
	      <!-- AJAX add member part -->
	      <div style="float:left"><p>&nbsp; &nbsp;Invite other user:&nbsp;</p></div>
	      <div id="checkUserNameForm" style="float:left">
	      	<input type="text" name="userName" id="ajaxUserName" placeholder="Enter username"/>	</div>	      	
	      	<input type="hidden" name="projectID" id="ajaxprojectID" value="${project.projectID }"/>
	      <div style="float:left">&nbsp;
	      	<input type="submit" class="button blue" value="search" onclick="loadXMLDoc()" /></div>      	
	      <div id="ajaxResponse"style="float:left">	      	
		  </div>
	      <br><br>
	  	</div>	      
	    <!-- AJAX part -->  
      </div>
     
		<!-- Add Task button bar -->
       	<div class="sm-box grid_12">
		<div style="position:relative;left:15px;top:15px;">
		<img src="img/nini_plus.png" alt="" /></div>
		<div style="position:relative;left:50px;top:-10px;">
			<input id="addbutton" type="submit" class="button yellow" value="Add Task">
		</div> 
		</div>
		
		<p>${message }</p>
		
		<!-- Add Task form -->
		<div id="hiddenpart" style="display:none;">
		<div class="box grid_6">
       	<div class="box-head"><h2>Create New Task</h2></div>
		<div class="box-content">
		<form action="addTask.htm" method="post">
			<input type="hidden" name="projectID" value="${project.projectID }">
          	<div class="form-row">
           		<p class="form-label">Name</p>
           		<div class="form-item"><input type="text" placeholder="Edit Task name" name="name"/></div>
           	</div>
			<input type="submit" class="button orange" value="Add" />
			</form>
         </div>
         </div>
		  
         <div class="clear"></div>
       	</div>
		
		
		
		<!-- Loop all task -->
	<c:forEach var="tasktodo" items="${tasktodoList}" >   
		<div class="box grid_12">
		<div class="box-head"><h2>${tasktodo.task.name }
        	&nbsp;&nbsp;<a href="deleteTask.htm?taskID=${tasktodo.task.taskID }&projectID=${project.projectID}">
        	Delete</a></h2></div>
        <div class="box-content no-pad">
          <ul class="table-toolbar">
            <li><a><img src="img/icons/basic/plus.png" alt="" />Add Todo</a></li>
            <li>
            <form action="addTodo.htm" method="post">
            	<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;description:&nbsp;<input type="text" name="description" />
            	<input type="hidden" name="projectID" value="${project.projectID}">
            	<input type="hidden" name="taskID" value="${tasktodo.task.taskID}">
            	<input type="submit" class="button orange" value="Add" /></p>
            </form>
            </li>
<!--             <li><a href="#"><img src="img/icons/basic/delete.png" alt="" /> Remove</a></li> -->
<!--             <li><a href="#"><img src="img/icons/basic/save.png" alt="" /> Save</a></li> -->
<!--             <li><a href="#"><img src="img/icons/basic/up.png" alt="" /> Ascending</a></li> -->
<!--             <li><a href="#"><img src="img/icons/basic/down.png" alt="" /> Descending</a></li> -->
          </ul>
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
<!--             <tr class="even gradeC"> -->
<!--             <form action="addTodo.htm" method="post"> -->
<%--               <td>${count }</td> --%>
<!--               <td><input type="text" name="description" /></td> -->
<!--               <td>Win 95+</td> -->
<!--               <td class="center">5</td> -->
<!--               <td class="center">Open</td> -->
<!--               <td class="center">C</td> -->
<!--             </tr> -->
          </tbody>
         </table>
        </div>
      </div> 
      </c:forEach>
	
</div>   
