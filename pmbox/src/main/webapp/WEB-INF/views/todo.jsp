<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
$(document).ready(function(){
  $("#showEditForm").click(function(){
    $("#todoInfo").hide();
    $("#showEditForm").hide();
    $("#completeTodo").hide();
    $("#editTodoForm").show();
    
  });
  $("#cancelEdit").click(function(){
	$("#todoInfo").show();
	$("#showEditForm").show();
	$("#completeTodo").show();
    $("#editTodoForm").hide();
   });
});

$(function() {

	  $('#tip-left').tipTip({attribute: "value", delay: "100", defaultPosition: "left"});
	  $('#tip-top').tipTip({attribute: "value", delay: "100", defaultPosition: "top"});
	  $('#tip-right').tipTip({attribute: "value", delay: "100", defaultPosition: "right"});
	  $('#tip-bottom').tipTip({attribute: "value", delay: "100", defaultPosition: "bottom"}); 

	   $('#slider').slider({
	      range: "min",
	      value: 50
	   });

	   $('#range-slider').slider({ 
	      range: true, 
	      min: 0,
	      max: 400,
	      values: [ 100, 300 ] 
	   });

	   $( "#eq > span" ).each(function() {
	      var value = parseInt( $( this ).text(), 10 );
	      $( this ).empty().slider({
	        value: value,
	        range: "min",
	        animate: true,
	        orientation: "vertical"
	      });
	    });

	   $( "#accordion" ).accordion({ fillSpace: true });

	   $( "#tabs" ).tabs();

	    $("#progressbar").progressbar({
	      value: 1
	    });
	    $("#progressbar .ui-progressbar-value").addClass("ui-corner-right");

	    new_width = "100%";
	    loop_width = "1%";
	    $("#progressbar .ui-progressbar-value").animate({width: new_width}, 60000);

	    $( "#dialog" ).dialog({
	      autoOpen: false,
	      resizable: false,
	      buttons: {
	        Close: function() {
	          $( this ).dialog( "close" );
	        }
	      }
	    });

	    $( "#open-dialog" ).click(function() {
	      $( "#dialog" ).dialog( "open" );
	      return false;
	    });

	    $( "#modal-dialog" ).dialog({
	      autoOpen: false,
	      resizable: false,
	      modal: true,
	      buttons: {
	        Close: function() {
	          $( this ).dialog( "close" );
	        }
	      }
	    });

	    $( "#open-modal-dialog" ).click(function() {
	      $( "#modal-dialog" ).dialog( "open" );
	      return false;
	    });

	    $('#open-notif').click(function () {
	        $.sticky('I am a simple notification.')
	    });

	    $('#open-comp-notif').click(function () {
	        $.sticky('<b>I am a complex notification.</b><br><br><p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>')
	    });

	    SyntaxHighlighter.all()

	    $('#iphone-check').iphoneStyle();

	    $("#datepicker").datepicker();
	    
	    $("#duedatepicker").datepicker();

	    $("#colorpicker").ColorPicker();

	});
</script>

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
          
          <!-- Todo Edit form -->
          <form id="editTodoForm" action="updateTodo.htm" method="post" style="display:none;">          
          	<div class="form-row">
           		<p class="form-label">Description</p>
           		<div class="form-item"><input type="text" name="description" value="${todo.description }" /></div>
           	</div>
           	<div class="form-row">
           		<p class="form-label">Assignee</p>
           		<div class="form-item"><input type="text" name="assignee" value="${assignee.userName }" /></div>
           	</div>
           	<div class="form-row">
           		<p class="form-label">Start Date</p>
           		<div class="form-item">
              		<input type="text" id="datepicker" name="startDate" value="${todo.startDate }"/>
              	<span class="form-icon fugue-2 calendar-day"></span>
            </div>
           	</div>
           	<div class="form-row">
           		<p class="form-label">Due Date</p>
           		<div class="form-item">
              		<input type="text" id="duedatepicker" name="dueDate" value="${todo.dueDate }"/>
              		<span class="form-icon fugue-2 calendar-day"></span>
            	</div>
           	</div>
           	<input type="hidden" name="projectID" value="${projectID }"/>
           	<input type="hidden" name="todoID" value="${todo.todoID }"/>
           	<input type="hidden" name="taskName" value="${taskName }"/>
           	<input type="submit" class="button orange" value="Save" />
           	<input id="cancelEdit" type="button" class="button orange" value="Cancel" />
          </form>
          <!-- Todo Edit form -->
          
          <!-- edit complete delete -->
          <p>
          	<a id="showEditForm" href="##">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp;
          	<a id="completeTodo" href="####">Complete</a>
          </p>
          <!-- edit complete delete -->
          <br><br>         
	             
	              
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