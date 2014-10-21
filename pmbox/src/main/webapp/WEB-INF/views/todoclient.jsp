<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


 <div class="content container_12">	
 	<p>${message }</p>
 	
 	<!-- Todo detail -->
 	<div class="box grid_8">
      <div class="box-head"><h2>Todo for ${taskName }</h2></div>
        <div class="box-content textsh">
          <h5>Description</h5><br>
          <div id="todoInfo">
          	<p>${todo.description }</p>
          	<p>Assignee: ${assignee.userName }</p>             
          	<p>Start Date: ${todo.startDate }</p> 
          	<p>Due Date: ${todo.dueDate }</p>   
          	<p>Status: ${todo.status }</p>    	
          	<p>${creater.userName } created at ${todo.createDate }</p>
          </div>	             
	              
          <h5>Comments</h5><br>
          <!-- comments loop -->
          <div>
          
          <c:set var="count" value="1" scope="page" />
          <c:forEach var="comment" items="${comments }">
          	<!-- show "You" or username -->
          	<p>${commenters[count-1].userID == user.userID?"You":commenters[count-1].userName }
          	 said at ${comment.commentDate }:</p>
          	<p>${comment.content }</p>
			<!-- remove link if username is session username -->
			<c:if test="${commenters[count-1].userID == user.userID}">
				<p><a href="deleteComment.htm?projectID=${projectID }&taskName=${taskName}&todoID=${todo.todoID}&commentID=${comment.commentID}">
					Remove</a></p>
			</c:if><br>
          <c:set var="count" value="${count + 1}" scope="page"/>
          </c:forEach>
          </div>
		</div>
	</div>

	<!-- Make comment box -->
	<div class="box grid_6">
		<div class="box-head"><h2>Make Comment</h2></div>
       	<div class="box-content">          
			<form action="addTodoComment.htm" method="post">
				<div class="form-row">   
				   	<div class="form-item">	 
				   	<textarea placeholder="Write your comment here!" name="comment" rows="5" cols="70"></textarea>
				   	</div>
			   	</div>
			   	<input type="hidden" name="projectID" value="${projectID }" />
			   	<input type="hidden" name="taskName" value="${taskName }" />
			   	<input type="hidden" name="todoID" value="${todo.todoID }" />
				<input type="submit" class="button orange" value="Submit" />
			</form>
         <div class="clear"></div>
       </div>	   
     </div>
     <!-- comment box end -->
     
 </div>