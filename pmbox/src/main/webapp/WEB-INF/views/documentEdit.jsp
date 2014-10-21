<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 <div class="content container_12">	
 	
 	<!-- Todo detail -->
 	<div class="box grid_10">
      <div class="box-head"><h2>Edit Document</h2></div>
		<div class="box-content textsh">          
			<form action="saveDocument.htm" method="post">
	           	<div class="form-row">
	           		<p class="form-label">Doc Name:</p>
	           		<div class="form-item">
	              		<input type="text" name="documentName" />
	            	</div>
	           	</div>
				<div class="form-row"> 
					<p class="form-label">Content:</p>  
				   	<div class="form-item">	 
				   	<textarea placeholder="Write your doc here!" name="documentContent" rows="20" cols="100"></textarea>
				   	</div>
			   	</div>
			   	<input type="hidden" name="docProjectID" value="${docProjectID }" />
				<input type="submit" class="button orange" value="Save" />
			</form>
         <div class="clear"></div>
       </div>
	</div>
     
 </div>