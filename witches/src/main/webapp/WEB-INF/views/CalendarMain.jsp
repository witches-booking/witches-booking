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
//	var message = '${message}';
	// 현재 페이지의 URL을 가져옵니다.
	var url = window.location.href;

	var urlParams = new URLSearchParams(url);

	var message = urlParams.get('message');
	
	console.log("메세지 : ", message);
	
    function getCookie(name) {
        var value = "; " + document.cookie;
        var parts = value.split("; " + name + "=");
        if (parts.length == 2) return parts.pop().split(";").shift();
    }

    const accessToken = getCookie('accessToken');
	console.log('accessToken',accessToken);
	if (message === "fail") {
		alert("로그인에 실패하셨습니다.");

		// 값이 존재하는 경우에만 sessionStorage에 저장
	} else {
		if (accessToken) {
				localStorage.setItem('createNm', createNm);
				console.log("로컬스토리지 값확인용" + localStorage.getItem('createNm'));

		}
	}
</script>

<script type="text/javascript">
	function kakao() {
		const restApiKey = "02b86e71e0895cda12a9361c1cdb773a";
		const redirectUri = "http://localhost:8449/api/kakaoLogin";
		//const redirectUri = "http://meet.witches.co.kr/api/kakaoLogin";
		

		//인가 코드 요청
		const codeUrl = 'https://kauth.kakao.com/oauth/authorize?response_type=code&client_id='
				+ restApiKey + '&redirect_uri=' + redirectUri;
		window.location.href = codeUrl;

	};
</script>

<script type="text/javascript">

	function logout() {
		// 1. 로컬스토리지에 createNm 제거
		//localStorage.removeItem('createNm');
		localStorage.clear();

		// 2. 쿠키 속 토큰 제거
		// 2-1 토큰 조회

	    function getCookie(name) {
	        var value = "; " + document.cookie;
	        var parts = value.split("; " + name + "=");
	        if (parts.length == 2) return parts.pop().split(";").shift();
	    }

	    const accessToken = getCookie('accessToken');
	    
	    // 2-2 카카오에 토큰 만료 요청
	    $.ajax({
	        url: 'https://kapi.kakao.com/v1/user/logout',
	        type: 'POST',
	        beforeSend: function(xhr) {
	            xhr.setRequestHeader('Authorization', 'Bearer ' +accessToken);
	        },
	        success: function(data) {
	            console.log("로그아웃 성공 :",data);
	        },
	        error: function(error) {
	            console.log(error);
	        }
	    });
	    
	    var cookies = document.cookie.split(";");

	    for (var i = 0; i < cookies.length; i++) {
	        var cookie = cookies[i];
	        var eqPos = cookie.indexOf("=");
	        var name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
	        document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT;path=/;";
	    }
	    location.reload();

		
		
	}


</script>

<script>
// 로그인 여부에 따른 버튼 활성화
window.onload = function() {
    var createNm = localStorage.getItem('createNm');
    var loginBtn = document.getElementById('kakao-login-btn');
    var logoutBtn = document.getElementById('logout-btn');


}
</script>

<style type="text/css">
#logout-btn {
    width: 183px;
    height: 35px;
},
#logout-btn img {
    width: 183px;
    height: 35px;
}

.scheduleScroll{
	 margin-top:-100px;
	 height: 100px;
	 overflow-y: scroll;
	 -ms-overflow-style: none;
}
::-webkit-scrollbar {
  display: none;
}

@keyframes fadeIn {
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}

.delayed-fade-in {
  opacity: 0; /* 초기 상태는 투명하게 */
  animation-name: fadeIn; /* 애니메이션 이름 지정 */
  animation-duration: 1s; /* 애니메이션 지속 시간 */
  animation-fill-mode: forwards; /* 애니메이션 종료 후 상태 유지 */
  animation-delay: 0.5s; /* 애니메이션 시작 전 지연 시간 */
}
</style>
</head>
<body>
<%
String loginId = (String)session.getAttribute("createNm");
%>


<div>
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            var createNm = localStorage.getItem('createNm');
            if (createNm === null) {
                // 로그인되지 않은 경우
                document.getElementById('kakao-login-btn').style.display = 'block';
                document.getElementById('logout-btn').style.display = 'none';
            } else {
                // 로그인된 경우
                document.getElementById('kakao-login-btn').style.display = 'none';
                document.getElementById('logout-btn').style.display = 'block';
            }
        });
    </script>

    <button type="button" id="kakao-login-btn" onclick="kakao()">
        <img src="/img/kakao_login_medium_narrow.png" alt="Kakao Login">
    </button>

    <button id="logout-btn" onclick="logout()" style="display: none;">
        <img alt="로그아웃" src="/img/kakao_logout2.png">
    </button>
</div>


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
												<c:choose>
													<c:when
														test="${year * 10000 + month * 100 + daysOfMonth[index] >= today}">
														<div id="calendarData" class="calendarData" data-day="${daysOfMonth[index]}" onclick="scheduleWrite(event)">
														    <c:if test="${j eq 0 }">
															    <button style="color:red;">
															        <span>${daysOfMonth[index]}</span>
															    </button>
														    </c:if>
														    <c:if test="${j eq 6 }">
														    	<button style="color:blue;">
															        <span>${daysOfMonth[index]}</span>
															    </button>
														    </c:if>
														    <c:if test="${j ne 0 && j ne 6 }">
														    	<button style="color:black;">
															        <span>${daysOfMonth[index]}</span>
															    </button>
														    </c:if>
														</div>
													</c:when>
													<c:otherwise>
														<c:if test="${j eq 0 }">
															    <button style="color:red;">
															        <span>${daysOfMonth[index]}</span>
															    </button>
														    </c:if>
														    <c:if test="${j eq 6 }">
														    	<button style="color:blue;">
															        <span>${daysOfMonth[index]}</span>
															    </button>
														    </c:if>
														    <c:if test="${j ne 0 && j ne 6 }">
														    	<button style="color:black;">
															        <span>${daysOfMonth[index]}</span>
															    </button>
														    </c:if>
													</c:otherwise>
												</c:choose>
											</div>
											<ul class="scheduleScroll">
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
    	  if(month > 12){
    		  month = month-12;
    		  year = year+1;
    	  }else if(month == 0){
    		  month =12;
    		  year= year-1
    	  }
         location.href = '/CalendarMain?year=' + year + '&month='+ month;
      }
   </script>

	<script type="text/javascript">
   function scheduleWrite() {
	    var createNm = localStorage.getItem('createNm');
	    var year = "${year}";
	    var month = "${month}";
	    var day = event.currentTarget.getAttribute('data-day');

	    console.log(year + month + day);

	    $.ajax({
	        url: "/schedule",
	        data: JSON.stringify({
	            "createNm": createNm,
	            "year": year,
	            "month": month,
	            "day": day
	        }),
	        type: "POST",
	        contentType: "application/json",
	        dataType: "json", // 데이터 형식을 JSON으로 지정
	        success: function (result) {
	            var message = result.message;
	            if (message === "성공") {
	                window.location.href = "/scheduleWrite?year="+year+"&month="+month+"&day="+day;
	            } else if (message === "실패") {
	                alert("로그인 후 이용해주세요.");
	                location.reload();
	            }
	        },
	         
	        error: function () {
	            alert("에러 발생");
	            location.reload();
	        }
	    });
	}
   </script>
</body>
</html>
