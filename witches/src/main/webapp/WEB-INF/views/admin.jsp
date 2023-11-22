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

</head>
<body>

	<h3 id="pageTit">회의실 사용관리</h3>
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
							<th scope="col"></th>
							<th scope="col">날짜</th>
							<th scope="col">시간</th>
							<th scope="col">인원수</th>
							<th scope="col">신청자</th>
							<th scope="col">부서</th>
							<th scope="col">회의내용</th>
							<th></th>
						</tr>
					</thead>


					<tbody>
						<c:forEach var="item" items="${listMap }">
							<tr>
								<td>${item.id }</td>
								<td>${item.year }-${item.month }-${item.day }</td>
								<td>${item.start } ~ ${item.end }</td>
								<td>${item.peopleNum }</td>
								<td>${item.name }</td>
								<td>${item.department }</td>
								<td>${item.contents }</td>
								<td><button>삭제</button></td>
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

</body>
</html>
