

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<html>
  <head>
    <title><tiles:getAsString name="title"/></title>
    <tiles:insertAttribute name="header" />
  </head>
  <body>
  		<div class="header">
   <a href="dashboard.html"><img src="img/logo.png" alt="Logo" /></a> 
   <div class="styler">
     <ul class="styler-show">
       <li><div id="colorSelector-top-bar"></div></li>
       <li><div id="colorSelector-box-head"></div></li>
     </ul>
   </div>
  </div>

<div class="content container_12">	
  <div class="box grid_12">
        <div class="box-head"><h2>Error Message</h2></div>
        <div class="box-content">
          <p>${errorMessage}
          </p>
        </div>
      </div>
</div>

    <tiles:insertAttribute name="footer" />
     
  </body>
</html>
