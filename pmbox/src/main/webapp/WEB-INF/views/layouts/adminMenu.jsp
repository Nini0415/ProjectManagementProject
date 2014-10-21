	<div class="header">
   <a href="dashboard.html"><img src="img/logo.png" alt="Logo" /></a> 
   <div class="styler">
     <ul class="styler-show">
       <li><div id="colorSelector-top-bar"></div></li>
       <li><div id="colorSelector-box-head"></div></li>
     </ul>
   </div>
  </div>

  <div class="top-bar">
      <ul id="nav">
        <li id="user-panel">
          <img src="img/nav/usr-avatar.jpg" id="usr-avatar" alt="" />
          <div id="usr-info">
            <p id="usr-name">Welcome back, Admin ${user.userName}.</p>
<!--             <p id="usr-notif">You have 6 notifications. <a href="#">View</a></p> -->
<!--             <p><a href="#">Preferences</a><a href="#">Profile</a><a href="logout.htm">Log out</a></p> -->
			<p><a href="login.htm">Log out</a></p>
          </div>
        </li>
        <li>
        <ul id="top-nav">
         <li class="nav-item">
           <a href="adminHome.htm"><img src=${currentMenu=="Users"?"img/nav/dash-active.png":"img/nav/dash.png"} alt="" /><p>Users</p></a>
         </li>
         <li class="nav-item">
           <a href="adminRequests.htm?message=OK"><img src=${currentMenu=="Requests"?"img/nav/icn-active.png":"img/nav/icn.png"} alt="" />
           <p>Requests</p></a>
         </li>
       </ul>
      </li>
     </ul>
  </div>

