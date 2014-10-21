<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="content container_12">

	<p>${message}</p>

	<!-- Create new user form -->
	<div class="box grid_8">
		<div class="box-head">
			<h2>Create New Account</h2>
		</div>
		<div class="box-content">
			<form:form commandName="newUser" action="createAccount.htm" method="post">
				<div class="form-row">
					<p class="form-label">Name</p>
					<div class="form-item">
						<form:input path="userName" />
						<p style="color:red"><form:errors path="userName"></form:errors></p>
						<!-- 						<input type="text" placeholder="Edit Account name" name="name" /> -->
					</div>
				</div>
				<div class="form-row">
					<p class="form-label">Password</p>
					<div class="form-item">
						<form:input path="password" />
						<p style="color:red"><form:errors path="password"></form:errors></p>
<!-- 						<input type="password" placeholder="Edit initial password" name="password" /> -->
					</div>
				</div>
				<div class="form-row">
					<label class="form-label">Account Type</label>
					<div class="form-item">
						<form:select path="role">
							<option value='user'>User</option>
							<option value='admin'>Admin</option>
						</form:select>
<!-- 						<select name="role"> -->
<!-- 							<option value='user'>User</option> -->
<!-- 							<option value='admin'>Admin</option> -->
<!-- 						</select> -->
					</div>
				</div>
				<input type="submit" class="button orange" value="Create" />
			</form:form>
		</div>
		<div class="clear"></div>
	</div>
	<!-- Create new user form -->

</div>
