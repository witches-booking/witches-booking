<%@page import="com.witches.schedule.vo.ScheduleVO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
	
	<script>
        function sortTable(n) {
            // 테이블과 행, 전환 여부 등을 관리하는 변수들 선언
            var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
            
            // 테이블 엘리먼트를 가져옴
            table = document.getElementById("dataTable");
            // 전환을 계속할지 여부를 나타내는 변수를 true로 초기화
            switching = true;
            // 정렬 방향을 오름차순으로 설정
            dir = "asc";

            // 전환이 없을 때까지 반복
            while (switching) {
                // 전환 여부를 false로 초기화
                switching = false;
                // 테이블의 행들을 가져옴
                rows = table.rows;

                // 첫 번째 행을 제외한 모든 행에 대해 반복
                for (i = 1; i < (rows.length - 1); i++) {
                    // 전환 여부를 false로 초기화
                    shouldSwitch = false;
                    // 현재 행과 다음 행에서 비교할 두 엘리먼트를 가져옴
                    x = rows[i].getElementsByTagName("td")[n];
                    y = rows[i + 1].getElementsByTagName("td")[n];

                    // 정렬 방향에 따라 두 엘리먼트를 비교하고 전환 여부 결정
                    if (dir == "asc") {
                        if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                            // 전환되어야 한다면 전환 여부를 true로 설정하고 반복문 종료
                            shouldSwitch = true;
                            break;
                        }
                    } else if (dir == "desc") {
                        if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
                            // 전환되어야 한다면 전환 여부를 true로 설정하고 반복문 종료
                            shouldSwitch = true;
                            break;
                        }
                    }
                }

                // 전환 여부가 true일 경우 전환을 수행하고 전환 횟수를 증가
                if (shouldSwitch) {
                    rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                    switching = true;
                    switchcount++;
                } else {
                    // 전환 여부가 없고 정렬 방향이 "asc"일 경우 방향을 "desc"로 설정하고 다시 반복
                    if (switchcount == 0 && dir == "asc") {
                        dir = "desc";
                        switching = true;
                    }
                }
            }
        }

        function deleteSelectedRows() {
            // 테이블 엘리먼트를 가져옴
            var table = document.getElementById("dataTable");
            // 테이블의 행 개수를 가져옴
            var rowCount = table.rows.length;

            // 테이블의 마지막 행부터 첫 번째 행까지 역순으로 반복
            for (var i = rowCount - 1; i > 0; i--) {
                // 현재 행을 가져옴
                var row = table.rows[i];
                // 현재 행의 첫 번째 셀에서 체크박스 엘리먼트를 가져옴
                var checkBox = row.cells[0].getElementsByTagName("input")[0];

                // 체크박스가 체크되어 있으면 해당 행을 삭제
                if (checkBox.checked) {
                    table.deleteRow(i);
                }
            }
        }
    </script>
	

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
				<table id="dataTable">
					<caption>회의실 사용 - 일 별 회의실 사용 관련 예약 현황의 정보를 제공하는 달력</caption>
					<colgroup>
						<!-- 열 정보 추가 -->
					</colgroup>
					<thead>
						<tr>
							<th><input type="checkbox" id="selectAll" onclick="selectAllRows(this)"/></th>
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
							<tr style="font-size:1rem;">
								<td style="padding:5px; text-align: center;"><input type="checkbox" class="selectRow"/></td>
								<td style="padding:5px;">${item.year }-${item.month }-${item.day }</td>
								<td style="padding:5px;">${item.start } ~ ${item.end }</td>
								<td style="padding:5px;">${item.peopleNum }</td>
								<td style="padding:5px;">${item.name }</td>
								<td style="padding:5px;">${item.department }</td>
								<td style="padding:5px;">${item.contents }</td>
								<td style="padding:5px; text-align: center;"><button onclick=adminDel()>삭제</button></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<button onclick="deleteSelectedRows()">선택된 행 삭제</button>
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
         location.href = '/admin/main?year=' + year + '&month='+ month;
      }
   </script>
   
   <script type="text/javascript">
   function adminDel() {
	    var id = "${dayData.getId()}";
	    var data = [{"id": id}]; // 데이터를 배열로 감싸서 보냄
	    $.ajax({
	        url: "/admin/DeleteTableData?tableNum=1",
	        data: JSON.stringify(data),
	        type: "DELETE",
	        contentType: "application/json",
	        dataType: "json",
	        success: function(result) {
	            alert("삭제 성공");
	            window.location.href = "/admin/main";
	        },
	        error: function() {
	            alert("삭제 실패");
	        }
	    });
	}
   </script>

</body>
</html>
