<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="content container_12">
	<!-- Java script for hide and show -->
	<script language=javascript>
	function Layer_HideOrShow(cur_div){ 
		var current=document.getElementById(cur_div);
		if(current.style.visibility=="hidden"){
			current.style.visibility ="visible";
		} else {
			current.style.visibility ="hidden";
		}
	}
	</script>
	<p>${message }</p>
	<!-- Add Project button bar -->
	<div class="sm-box grid_12">
		<div style="position:relative;left:15px;top:15px;">
		<img src="img/nini_plus.png" alt="" /></div>
		<div style="position:relative;left:50px;top:-10px;">
		<input type="button" class="button blue" value="Add Project" onclick="Layer_HideOrShow('projectform');">
		<div id="projectform" style="visibility:hidden;position:relative;left:100px;top:-28px;">
		<form action="addProject.htm" method="post">
			<p class="form-label">Project Name</p><input type="text" name="name" />
			<input type="submit" class="button orange" value="Add">
		</form>
		</div>
		</div> 
	</div>   
      
     <!-- Show all involved projects --> 
     
     <c:forEach var="project" items="${involvedProjects}" >

      <div class="box grid_3">
        <div class="box-head"><h2>${project.name}</h2></div>
        <div class="box-content">
          <p>Create Time: &nbsp; ${project.createDate }
          <br>
          Current Status: &nbsp; ${project.status }
          <br>
          Project ID: &nbsp; ${project.projectID }
          <br><br></p>
	      <form action="project.htm">
	      	<input type="hidden" name="projectID" value="${project.projectID }"/>
	      	<input type="hidden" name="message" value=" "/>
	      	<input type="submit" class="button green" value="Read more" />
	      </form>          
          
        </div>
      </div>
      
      </c:forEach>
      
  </div>
