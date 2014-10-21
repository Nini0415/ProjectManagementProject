<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript">
$(document).ready(function(){
  $("#addbutton").click(function(){
  $("#hiddenpart").toggle();
  });
});
</script>
 
 <div class="content container_12">	
	<p>${message }</p> 

 	<!-- Add Document button bar -->
	<div class="sm-box grid_12">
		<div style="position:relative;left:15px;top:15px;">
		<img src="img/nini_plus.png" alt="" /></div>
		<div style="position:relative;left:50px;top:-10px;">
			<input id="addbutton" type="submit" class="button yellow" value="Create Document">
		</div>
		<div id="hiddenpart" class="box-content" style="display:none;">
		<form action="editDocument.htm" method="post">
          	<div class="form-row">
           		<p class="form-label">Choose project:</p>
           		<div class="form-item">
	              <select name="docProjectID">
	        		<c:forEach var="project" items="${chooseProjects}" >   
	                	<option value="${project.projectID}">${project.name}</option>
	        		</c:forEach>
	              </select>
            	</div>
           	</div>
			<input type="submit" class="button orange" value="Edit" />
		</form>
		</div>
	</div>
 	<!-- Add Document button bar -->
 	
	<!-- Loop all documents -->  
	<div class="box grid_12">
		<div class="box-head"><h2>All documents of projects that you have been involved</h2></div>
        <div class="box-content no-pad">
           <table class="display">
	         <thead>
	            <tr>
	              <th>No.</th>
	              <th>Document Name</th>
	              <th>Author</th>
	              <th>ProjectName</th>
	              <th>Operation</th>
	            </tr>
	          </thead>
	          <tbody>
	          	<c:set var="count" value="1" scope="page" />
				<c:forEach var="document" items="${documentList }">
				
					<!-- decide row style -->
			        <c:set value="odd gradeX" var="rowStyle"/>
			        <c:if test="${count%2==0}">
			        	<c:set value="even gradeC" var="rowStyle"/>
			        </c:if>
			        <!-- decide set row style -->
			        
	            	<tr class="${rowStyle }">
		       			<td>${count }</td>
		       			
		       			<!-- PDF VIEW FROM HERE -->
		              	<td><a href="document${document.name}.pdf?documentID=${document.documentID}&projectName=${docProjectList[count-1].name}&authorName=${docAuthorList[count-1].userName}" target="_blank">
		              		${document.name}</a></td>
		              	<!-- PDF VIEW FROM HERE -->
		              		
		              	<td>${docAuthorList[count-1].userName}</td>
		              	<td class="center">${docProjectList[count-1].name}</td>
		              	<td class="center">
		              	
		              		<!-- operation action -->
			              	<a href="##">
			              		Remove</a>
			              	<!-- operation action -->
		              	</td>
		            </tr>
	            <c:set var="count" value="${count + 1}" scope="page"/>
	            </c:forEach>
	          </tbody>
        	</table>
        </div>
	</div> 
	<!-- Loop all documents -->   
	         
</div>