<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<tiles:insertDefinition name="home" />
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	PM BOX!  
</h1>
<p>login page </p>
<P>  The time on the server is ${serverTime}. </P>
<form action="login" method="post">
User Name: <input type="text" name="username" /><br/>
Password: <input type="password" name="password" /><br/>
<input type="submit" name="Sign in"><input type="reset" name="Reset">
</form>

</body>
</html>
