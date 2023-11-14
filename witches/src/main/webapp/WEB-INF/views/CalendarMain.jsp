<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
</head>
<body>
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
    
    // 날짜 정보 동적 생성
    out.print("<tbody><tr>");
    for (int i = 1; i < startDayOfWeek; i++) {
        // 첫 날이 시작되기 전까지는 빈 칸으로 채움
        out.print("<td></td>");
    }
    for (int day = startDayOfMonth; day <= endDayOfMonth; day++) {
        // 예약 정보 표시
  out.print("<td><a href='/schedule?date=" + cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + String.format("%02d", day) + "'><span>" + day + "</span></a></td>");


        // 주말이거나 마지막 날이면 새로운 행(<tr>)을 시작
        if (day % 7 == 0 || day == endDayOfMonth) {
            out.print("</tr><tr>");
        }
    }
    out.print("</tr></tbody>");
%>
						<tr>
							<!-- 예약 정보 표시 -->
							<!--  <td><a href="/schedule?date=1"><span>1</span></a></td>-->
							<!-- 나머지 날짜 정보 동적 생성 -->
						</tr>
						<!-- 나머지 주차의 날짜 및 예약 정보 생성 -->
					</tbody>
				</table>
			</article>
		</div>
	</div>


	<script type="text/javascript">
		function moveMonth(year, month) {
			location.href = '/meet.jsp?pid=&year=' + year + '&month=' + month;
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
