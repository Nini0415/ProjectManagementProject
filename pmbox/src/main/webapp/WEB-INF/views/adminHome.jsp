<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="content container_12">

	<p>${message}</p>

	<!-- Add New Account button bar -->
	<div class="sm-box grid_12">
		<div style="position: relative; left: 15px; top: 15px;">
			<img src="img/nini_plus.png" alt="" />
		</div>
		<div style="position: relative; left: 50px; top: -10px;">
		<form action="createAccount.htm" method="get">
			<input type="submit" class="button blue" value="Create New Account" />
		</form>
		</div>
	</div>
	
</div>
