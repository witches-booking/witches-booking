<%@page import="com.witches.schedule.vo.ScheduleVO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- 카카오 로그인  -->
<script src="https://t1.kakaocdn.net/kakao_js_sdk/2.4.0/kakao.min.js"
	integrity="sha384-mXVrIX2T/Kszp6Z0aEWaA8Nm7J6/ZeWXbL8UpGRjKwWe56Srd/iyNmWMBhcItAjH"
	crossorigin="anonymous"></script>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	// ModelAndView로 보낸 값 JSP 페이지에서 createNm 값을 가져옴
	var createNm = '${createNm}';
	var message = '${message}';
	console.log("메세지", message);
	if (message === "fail") {
		alert("로그인에 실패하셨습니다.");

		// 값이 존재하는 경우에만 sessionStorage에 저장
	} else {
		if (createNm) {
			localStorage.setItem('createNm', createNm);
			console.log("로컬스토리지 값확인용" + localStorage.getItem('createNm'));

		}
	}
</script>

<script type="text/javascript">
	function kakao() {
		const restApiKey = "02b86e71e0895cda12a9361c1cdb773a";
		const redirectUri = "http://localhost:8449/kakaoLogin";

		//인가 코드 요청
		const codeUrl = 'https://kauth.kakao.com/oauth/authorize?response_type=code&client_id='
				+ restApiKey + '&redirect_uri=' + redirectUri;
		window.location.href = codeUrl;

	};
	

</script>
</head>
<body>

	<button type="button" id="kakao-login-btn" onclick="kakao()">

		<img src="/img/kakao_login_medium_narrow.png" alt="Kakao Login">

	</button>



	<h3 id="pageTit">회의실 사용</h3>
	<div id="content">
		<div class="contentWrap">
			<article class="calendar">
				<div class="top">
					<div class="date">
						<button type="button" class="prev"
							onclick="moveMonth(${year}, ${month - 1})">이전</button>
						<h4>${year}.${month}</h4>
						<button type="button" class="next"
							onclick="moveMonth(${year}, ${month + 1})">다음</button>
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
						<c:set var="index" value="0" />
						<c:forEach var="i" begin="1" end="6">
							<tr>
								<c:forEach var="j" begin="0" end="6">

									<td><c:if test="${not empty daysOfMonth[index]}">
											<div>
												<a onclick="scheduleWrite();"
													href="/scheduleWrite?year=${year}&month=${month}&day=${dayOfMonth[index]}">
													<span>${daysOfMonth[index]}</span>
												</a>
											</div>
											<ul>
												<c:forEach var="item" items="${data}">
													<c:if test="${item.day == daysOfMonth[index]}">
														<li class='state01'><a
															href='/api/detail?id=${item.id}'> ${item.start} ~ <em>${item.end}</em>
																<div class='calTooltip'>
																	<p>${item.department}-${item.name}</p>
																</div>
														</a></li>
													</c:if>
												</c:forEach>
											</ul>
										</c:if> <c:set var="index" value="${index + 1}" /></td>
								</c:forEach>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</article>
		</div>
	</div>


	<script type="text/javascript">
      function moveMonth(year, month) {
         location.href = '
															/scheduler.jsp?pid=&year=
															' + year + '&month='
               + month;
      }
   </script>

	<script type="text/javascript">
   function scheduleWrite() {
	    var createNm = localStorage.getItem('
															createNm');
	    $.ajax({
	        url: "/schedule",
	        data:
															JSON.stringify({ "createNm": createNm }), // 데이터를 JSON
															형식으로 변환하여 전송
															type: "POST",
	        contentType: "application/json", // 컨텐츠
															타입 설정 success: function (result) {
	        	var
															year=cal.get(Calendar.YEAR); var
															month=(cal.get(Calendar.MONTH) + 1);
	        	var
															day=String.format( "%02d", day);
	            var
															message=result.message; if (message===
															"성공") {
	                window.location.href="/scheduleWrite"
															;
	            } else if (message===
															"실패") {
	                alert("로그인 후
															이용해주세요.");
	                location.reload();
	            }
	        },
	        error:
															function () {
	            alert("에러발생");
	            location.reload();
	        }
	    });
	}
   </script>
</body>
</html>
