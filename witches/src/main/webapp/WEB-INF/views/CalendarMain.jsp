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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- 카카오 로그인  -->
<script src="https://t1.kakaocdn.net/kakao_js_sdk/2.4.0/kakao.min.js"
	integrity="sha384-mXVrIX2T/Kszp6Z0aEWaA8Nm7J6/ZeWXbL8UpGRjKwWe56Srd/iyNmWMBhcItAjH"
	crossorigin="anonymous"></script>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">

function kakaoLogin() {
    // SDK를 초기화합니다. 사용할 앱의 JavaScript 키를 설정해야 합니다.
    Kakao.init('570250ea5e6af0b22c661c29eb516746');

    // SDK 초기화 여부를 판단합니다.
    console.log(Kakao.isInitialized());


    Kakao.Auth.login({
        success: function(authObj) {
            console.log(authObj.access_token);
            Kakao.Auth.setAccessToken(authObj.access_token);

            Kakao.API.request({
                url: '/v2/user/me',
                data: {
                    property_keys: ['kakao_account.email'],
                },
            }).then(function(response) {
                console.log(response);
                var loginId = response.id;
                var sns = "kakao";
                $.ajax({
                    url: "/login",
                    data: {
                        "loginId": loginId,
                        "sns": sns
                    },
                    type: "POST",
                    success: function(result) {
                        var parsedData = JSON.parse(result.reData);
                        var message = parsedData.reMsg;
                        console.log(result.msg);
                        if (message === "성공") {
                            alert("로그인 되었습니다.");
                        }
                    },
                    error: function() {
                        alert("로그인에 실패했습니다.");
                        window.location.href = "/";
                    }
                });
            }).catch(function(error) {
                console.log(error);
            });
        }
    }).catch(function(error) {
        console.log(error);
    });
}
</script>
</head>
<body>

	<button type="button" id="kakao-login-btn" onclick="kakaoLogin();">

		<img src="/img/kakao_login_medium_narrow.png" alt="Kakao Login">

	</button>

	<button onclick="kakao()"> 카카오rest로그인</button>



	<h3 id="pageTit">회의실 사용 ${loginId.getLoginId() }</h3>
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
						<!-- 날짜 및 예약 정보 동적 생성 -->
						<%
						// Java 코드로 날짜 및 예약 정보 생성
						// 예시: 데이터베이스로부터 날짜와 예약 정보를 조회하여 표시
						String currentDate = "2023-11-01"; // 현재 날짜 예시

						// 날짜 정보를 파싱하여 Calendar 객체 생성
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						Calendar cal = Calendar.getInstance();
						cal.setTime(sdf.parse(currentDate));

						// 해당 월의 첫 날과 마지막 날을 구함
						int startDayOfMonth = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
						int endDayOfMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

						// 해당 월의 첫 날의 요일을 구함 (1: 일요일, 2: 월요일, ..., 7: 토요일)
						int startDayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

						List<ScheduleVO> data = (List) request.getAttribute("data");
						%>
					
					<tbody>
						<tr>
							<%
							for (int i = 1; i < startDayOfWeek; i++) {
								// 첫 날이 시작되기 전까지는 빈 칸으로 채움
							%>
							<td></td>
							<%
							}

							for (int day = startDayOfMonth; day <= endDayOfMonth; day++) {
							// 해당 날짜의 요일을 구함 (1: 일요일, 2: 월요일, ..., 7: 토요일)
							cal.set(Calendar.DAY_OF_MONTH, day);
							int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

							// 예약 정보 표시
							ScheduleVO schedule = null;
							for (ScheduleVO s : data) {
								if (s.getDay() == day) {
									schedule = s;
									break;
								}
							}

							if (schedule != null) {
							%>
							<td>
								<div>
									<a
										href='/schedule?year=<%=cal.get(Calendar.YEAR)%>&month=<%=(cal.get(Calendar.MONTH) + 1)%>&day=<%=String.format("%02d", day)%>&id=${loginId}'>
										<span><%=day%></span>
									</a>
									<ul>
										<li class='state01'><a
											href='/detail?id=<%=schedule.getId()%>'><%=schedule.getStart()%>~
												<em><%=schedule.getEnd()%></em>
												<div class='calTooltip'>
													<p><%=schedule.getDepartment()%>
														-
														<%=schedule.getName()%></p>
												</div> </a></li>
									</ul>
								</div>
							</td>
							<%
							} else {
							%>
							<td>
								<div>
									<a
										href='/schedule?year=<%=cal.get(Calendar.YEAR)%>&month=<%=(cal.get(Calendar.MONTH) + 1)%>&day=<%=String.format("%02d", day)%>&id=${loginId}'>
										<span><%=day%></span>
									</a>
								</div>
							</td>
							<%
							}

							// 주말이거나 마지막 날이면 새로운 행(<tr>)을 시작
							if (dayOfWeek == 7 || day == endDayOfMonth) {
							%>
						</tr>
						<tr>
							<%
							}
							}
							%>
						</tr>
					</tbody>

					</tbody>
				</table>
			</article>
		</div>
	</div>


	<script type="text/javascript">
		function moveMonth(year, month) {
			location.href = '/scheduler.jsp?pid=&year=' + year + '&month='
					+ month;
		}
	</script>




</body>
</html>
