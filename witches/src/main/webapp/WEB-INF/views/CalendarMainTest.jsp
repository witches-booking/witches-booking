<%@page import="com.witches.booking.entity.Schedule"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, target-densitydpi=medium-dpi" />
<title>위치스 회의실 예약</title>
<link rel="stylesheet" type="text/css" href="/food/base.css" />
<link rel="stylesheet" type="text/css" href="/food/sub.css" />
<link rel="stylesheet" type="text/css" href="/food/food.css" />

<!-- 카카오 로그인  -->
<script src="https://t1.kakaocdn.net/kakao_js_sdk/2.4.0/kakao.min.js"
	integrity="sha384-mXVrIX2T/Kszp6Z0aEWaA8Nm7J6/ZeWXbL8UpGRjKwWe56Srd/iyNmWMBhcItAjH"
	crossorigin="anonymous"></script>
<script>
// 카카오 로그인 
	window.onload = function() {
		document.getElementById('kakao-login-btn').addEventListener('click',
				function() {
					// SDK를 초기화 합니다. 사용할 앱의 JavaScript 키를 설정해야 합니다.
					Kakao.init('570250ea5e6af0b22c661c29eb516746');

					// SDK 초기화 여부를 판단합니다.
					console.log(Kakao.isInitialized());

					Kakao.Auth.authorize({
						redirectUri : 'http://localhost:8585/CalendarMain',
					});

					console.log('${ACCESS_TOKEN}')
					Kakao.Auth.setAccessToken('${ACCESS_TOKEN}');

				});
	}
</script>


    <script type="text/javascript">
    data = {month : '11'};
    
        $(document).ready(function() {
            // REST API를 호출하여 데이터를 가져옵니다.
            $.ajax({
                url: '/CalendarMainTestFind',  // REST API의 URL을 입력하세요.
                type: 'GET',
                data: JSON.stringify(data),
                success: function(data) {
                    // 데이터를 가져온 후에 달력에 표시합니다.
                    console.log("가져온 데이터 확인 :", data )
                    var month = data.month;
                    var day = data.day;
                    var contents = data.contents;

                    // 달력의 특정 날짜 셀을 찾습니다.
                    var cell = $('#calendar').find(`[data-month="${month}"][data-day="${day}"]`);

                    // 셀에 내용을 추가합니다.
                    cell.text(content);
                }
            });
        });
    </script>
</head>
<body>

	<button id="kakao-login-btn" style="position: absolute; right: 0;">
		<img src="/img/kakao_login_medium_narrow.png" alt="Kakao Login">
	</button>




	<h3 id="pageTit">회의실 사용</h3>
	<div id="content">
		<div class="contentWrap">
			<article class="calendar">
				<div class="top">
					<div class="date">
						<button type="button" class="prev"
							onclick="moveMonth('2023','10')">이전</button>
						<h4>2023. 11.</h4>
						<button type="button" class="next"
							onclick="moveMonth('2023','12')">다음</button>
					</div>
				</div>
				<table>
					<caption>회의실 사용 - 일 별 회의실 사용 관련 예약 현황의 정보를 제공하는 달력</caption>
					<colgroup>
						<!-- 열 정보 추가 -->
					</colgroup>
					<thead>
						<tr>
							<th scope="col">일</th>
							<th scope="col">월</th>
							<th scope="col">화</th>
							<th scope="col">수</th>
							<th scope="col">목</th>
							<th scope="col">금</th>
							<th scope="col">토</th>
						</tr>
					</thead>
					<tbody>
				   <script>
        $(document).ready(function() {
            var date = new Date();
            var month = date.getMonth() + 1;
            var year = date.getFullYear();

            for (var i = 1; i <= 31; i++) {
                // 각 날짜에 대한 셀을 생성합니다.
                var cell = $('<td>').text(i).attr('data-day', i).attr('data-month', month);

                // 셀을 달력에 추가합니다.
                $('#calendar').append(cell);
            }
        });
    </script>

					</tbody>
				</table>
			</article>
		</div>
	</div>


	<script type="text/javascript">
		function moveMonth(year, month) {
			location.href = '/scheduler.jsp?pid=&year=' + year + '&month=' + month;
		}
	</script>

	<div id="divView"></div>

	<script type="text/javascript">
		//-- 버튼 클릭시 버튼을 클릭한 위치 근처에 레이어 생성 --//
		$('.imgSelect')
				.click(
						function(e) {
							var divTop = e.clientY - 40; //상단 좌표 위치 안맞을시 e.pageY
							var divLeft = e.clientX; //좌측 좌표 위치 안맞을시 e.pageX
							var serial = $(this).attr("serial");
							var idx = $(this).attr("idx");
							$('#divView')
									.empty()
									.append(
											'<div style="position:absolute;top:5px;right:5px"><span id="close" style="cursor:pointer;font-size:1.5em" title="닫기">X</span> </div><br><a href="?serial='
													+ serial
													+ '">serial</a><BR><a href="?idx='
													+ idx + '">idx</a>');
							$('#divView').css({
								"top" : divTop,
								"left" : divLeft,
								"position" : "absolute"
							}).show();
							$('#close')
									.click(
											function() {
												document
														.getElementById('divView').style.display = 'none'
											});
						});
	</script>
</body>
</html>
