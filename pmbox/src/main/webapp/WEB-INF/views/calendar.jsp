<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <!--- CONTENT AREA -->

  <div class="content container_12">
      <div class="box grid_12">
        <div class="box-head"><h2>Calendar</h2></div>
        <div class="box-content">
          <div id="calendar"></div>
        </div>
      </div>
  </div>

<script> /* SCRIPTS */

  $(document).ready(function() {
  
    var date = new Date();
    var d = date.getDate();
    var m = date.getMonth();
    var y = date.getFullYear();
    
    $('#calendar').fullCalendar({
      header: {
        left: 'prev,next today',
        center: 'title',
        right: 'month,agendaWeek,agendaDay'
      },
      editable: true,
      events: [
//             {
//             	title: 'Today',
//                 start: new Date(y, m, d)   
//             },
		<c:set var="count" value="1" scope="page" />
        <c:forEach var="todo" items="${todoList}">              
	        {
	          title: '${todo.description}',
	          start: new Date(${todo.startYear}, ${todo.startMonth}, ${todo.startDay}),
	          end: new Date(${todo.dueYear}, ${todo.dueMonth}, ${todo.dueDay}),
	          url: 'todo.htm?projectID=${taskList[count-1].projectID}&taskName=${taskList[count-1].name}&todoID=${todo.todoID}&message=ok'
	        },
	        <c:set var="count" value="${count + 1}" scope="page"/>
        </c:forEach>
      ]
    });
    
  });
</script>


