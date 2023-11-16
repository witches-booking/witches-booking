<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>위치스 회의실 예약</title>

<!-- fullcalendar css -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/fullcalendar@5.10.1/main.css">

<script src="https://cdn.jsdelivr.net/npm/fullcalendar@5.10.1/main.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.18.1/moment.min.js"></script>

<script src="https://t1.kakaocdn.net/kakao_js_sdk/2.4.0/kakao.min.js"
	integrity="sha384-mXVrIX2T/Kszp6Z0aEWaA8Nm7J6/ZeWXbL8UpGRjKwWe56Srd/iyNmWMBhcItAjH"
	crossorigin="anonymous"></script>
<script>
window.onload = function() {
    document.getElementById('kakao-login-btn').addEventListener('click', function() {
        // SDK를 초기화 합니다. 사용할 앱의 JavaScript 키를 설정해야 합니다.
        Kakao.init('570250ea5e6af0b22c661c29eb516746');

        // SDK 초기화 여부를 판단합니다.
        console.log(Kakao.isInitialized());

        Kakao.Auth.authorize({
            redirectUri : 'http://localhost:8585/',
        });
        
        console.log('${ACCESS_TOKEN}')
        Kakao.Auth.setAccessToken('${ACCESS_TOKEN}');
        
    });
}
</script>

</head>
<body>

	<script type="text/javascript">
		
	</script>
<button id = 'kakao-login-btn'>
    <img src="/img/kakao_login_small.png" alt="Kakao Login">
</button>
	<h3>회의실 예약</h3>


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
					return date.date.year + '년 '
							+ (parseInt(date.date.month) + 1) + '월';
				},
				selectable : true,
				droppable : true,
				editable : true,
				nowIndicator : true,
				locale : 'ko',
				events : function(fetchInfo, successCallback, failureCallback) {
					$.ajax({
						url : '/showScheduleList',
						type : 'GET',
						success : function(data) {
							var events = [];
							data.forEach(function(item) {
								events.push({
									start : item.start,
									end : item.end
								});
							});
							successCallback(events);
						},
						error : function(err) {
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