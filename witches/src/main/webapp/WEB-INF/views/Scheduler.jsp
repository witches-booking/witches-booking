<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 위치스 회의실 예약</title>

<!-- fullcalendar css -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/fullcalendar@5.10.1/main.css">

<script src="https://cdn.jsdelivr.net/npm/fullcalendar@5.10.1/main.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.18.1/moment.min.js"></script>

</head>
<body>

<script type="text/javascript">



</script>

<h3> 회의실 예약 </h3>


<div id='calendar'></div>
<script src="fullcalendar/lib/locales-all.js"></script>
<script type="text/javascript">
document.addEventListener('DOMContentLoaded', function() {
	var calendarEl = document.getElementById('calendar');
	var calendar = new FullCalendar.Calendar(calendarEl, {
		initialView : 'dayGridMonth',
		headerToolbar : {
			start : 'prev next today',
			center : 'title',
			end : 'dayGridMonth,dayGridWeek,dayGridDay'
		},
		titleFormat : function(date) {
			return date.date.year + '년 ' + (parseInt(date.date.month) + 1) + '월';
		},
		selectable : true,
		droppable : true,
		editable : true,
		nowIndicator: true,
		locale: 'ko',
		events: function(fetchInfo, successCallback, failureCallback) {
			$.ajax({
				url: '/showScheduleList',
				type: 'GET',
				success: function(data) {
					var events = [];
					data.forEach(function(item) {
						events.push({
							start: item.start,
							end: item.end
						});
					});
					successCallback(events);
				},
				error: function(err) {
					failureCallback(err);
				}
			});
		}
	});
	calendar.render();
});
</script>





</body>
</html>