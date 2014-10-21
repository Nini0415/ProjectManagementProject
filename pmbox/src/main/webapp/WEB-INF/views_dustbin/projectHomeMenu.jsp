
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
            <p id="usr-name">Welcome back, ${user.userName}.</p>
<!--             <p id="usr-notif">You have 6 notifications. <a href="#">View</a></p> -->
<!--             <p><a href="#">Preferences</a><a href="#">Profile</a><a href="index.html">Log out</a></p> -->
            <p><a href="login.htm">Log out</a></p>
          </div>
        </li>
        <li>
        <ul id="top-nav">
         <li class="nav-item">
         	<!-- link to user home page -->
           <a href="home.htm"><img src=${currentMenu=="Projects"?"img/nav/tb-active.png":"img/nav/tb.png"} alt="" /><p>Projects</p></a>
         </li>
         <li class="nav-item">
           <a href="analytics.html"><img src="img/nav/anlt.png" alt="" /><p>Progress</p></a>
         </li>
         <li class="nav-item">
           <a href="calendar.html"><img src="img/nav/cal.png" alt="" /><p>Calendar</p></a>
         </li>
         <li class="nav-item">
           <a href="icons.html"><img src="img/nav/icn.png" alt="" /><p>Everyone</p></a>
         </li>
         <li class="nav-item">
           <a href="grid.html"><img src="img/nav/grid.png" alt="" /><p>Me</p></a>
          </ul>
         </li>
         
       </ul>
      </li>
     </ul>
  </div>

