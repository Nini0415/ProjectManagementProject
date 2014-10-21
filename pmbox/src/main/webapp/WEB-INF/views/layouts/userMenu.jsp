
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
            <p><a href="login.htm">Log out</a></p>
          </div>
        </li>
        <li>
        <ul id="top-nav">
         <li class="nav-item">
         	<!-- link to user home page -->
           <a href="home.htm?message=start"><img src=${currentMenu=="Projects"?"img/nav/tb-active.png":"img/nav/tb.png"} alt="" />
           <p>Projects</p></a>
         </li>
<!--          <li class="nav-item"> -->
<%--            <a href="analytics.html"><img src=${currentMenu=="Progress"?"img/nav/anlt-active.png":"img/nav/anlt.png"} alt="" /> --%>
<!--            <p>Progress</p></a> -->
<!--          </li> -->
         <li class="nav-item">
           <a href="calendar.htm"><img src=${currentMenu=="Calendar"?"img/nav/cal-active.png":"img/nav/cal.png"} alt="" />
           <p>Calendar</p></a>
         </li>
         <li class="nav-item">
           <a href="userRequests.htm"><img src=${currentMenu=="Requests"?"img/nav/icn-active.png":"img/nav/icn.png"} alt="" />
           <p>Requests</p></a>
         </li>
         <li class="nav-item">
           <a href="document.htm?message= "><img src=${currentMenu=="Document"?"img/nav/typ-active.png":"img/nav/typ.png"} alt="" /><p>Document</p></a>
         </li>
         <li class="nav-item">
           <a href="userfile.htm?message= "><img src=${currentMenu=="File"?"img/nav/flm-active.png":"img/nav/flm.png"} alt="" /><p>File</p></a>
         </li>
         
<!--          <li class="nav-item"> -->
<%--            <a href="##"><img src=${currentMenu=="Me"?"img/nav/grid-active.png":"img/nav/grid.png"} alt="" /> --%>
<!--            <p>Me</p></a> -->
<!--            </li> -->
          </ul>
         </li>
         
       </ul>
      </li>
     </ul>
  </div>

