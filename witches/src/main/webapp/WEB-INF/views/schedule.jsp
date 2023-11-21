<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, target-densitydpi=medium-dpi">
        <title>위치스 회의실 예약</title>
        <link rel="stylesheet" type="text/css" href="/food/base.css">
        <link rel="stylesheet" type="text/css" href="/food/sub.css">
        <link rel="stylesheet" type="text/css" href="/food/food.css">
        <link rel="stylesheet" type="text/css" href="/food/jquery.timepicker.min.css">
        <script type="text/javascript" src="//code.jquery.com/jquery-latest.js"></script>
        <script type="text/javascript" src="/food/jquery.timepicker.min.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

        <script type="text/javascript">
            //<![CDATA[
            function writeform_check(form) {
                if (form.name.value == "") {
                    alert("주문자 이름을 선택하세요.");
                    form.name.focus();
                    return false;
                }
                if (confirm("입력하신 내용으로 주문합니다.")) {
                    document.writeform.submit();
                    return true;
                }
                return false;

            }

            function noOrder() {

                if (document.writeform.name.value == "") {
                    alert("주문자 이름을 선택하세요.");
                    document.writeform.name.focus();
                    return false;
                }
                if (confirm("저녁 안먹습니다.")) {
                    document.writeform.diet.value = "ok";
                    document.writeform.submit();
                    return true;
                }
                return false;
            }

            function code_setting(code) {
                document.writeform.code.value = code;
            }
            //]]>

        </script>
        
        
    </head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>            
			<h3 id="pageTit">회의실 사용</h3>
            <div id="content">
                <div class="contentWrap">                  
                    <div class="inputForm">
                            <input type="hidden" id="formtype" name="formtype" value="ing_meetWrite">
                            <h4>회의실 예약 입력</h4>
                            <div class="msgBox">
                                <ul>
                                    <li><span class="compul">필수</span>표시는 필수 입력사항입니다.</li>
                                    <li>다음 이용자를 위해 반드시 자리를 정리해 주세요.</li>
                                </ul>
                            </div>  
                            <table>
                                <caption>회의실 예약 입력 - 예약일, 예약시간, 사용인원, 내선번호, 신청인 이름의 정보를 입력하는 표</caption>
                                <tbody>
                                    <tr>
                                        <th scope="row"><label for="meeting_date">예약일<span class="compul">필수</span></label></th>
                                        <td colspan="4" class="date"><input id="meeting_date" name="time" value='${date.getYear()}-${date.getMonth()}-${date.getDay() } ' type="text" readonly="" required="required" placeholder="선택하세요">
                                        <input type="text" id="year" name="year" value="${date.getYear() }" style="display : none;">
                                        <input type="text" id="month" name="month" value="${date.getMonth() }" style="display : none;">
                                        <input type="text" id="day" name="day" value="${date.getDay() }" style="display : none;">
                                        </td>
                                    </tr>
                                    <tr>
                                        <th scope="row"><label for="stime">예약시간<span class="compul">필수</span></label></th>
                                        <td colspan="4">
                                            <input id="start" name="start" type="text" class="timepicker timepicker1" placeholder="시작시간" title="시작시간" required="required" readonly="readonly">
                                            <span class="formHyphen"></span>
                                            <input id="end" name="end" type="text" class="timepicker timepicker2" placeholder="종료시간" title="종료시간" required="required" readonly="readonly">
                                        </td>
                                    </tr>
                                    <tr>
                                        <th scope="row"><label for="people">사용인원<span class="compul">필수</span></label></th>
                                        <td colspan="4">
                                            <input id="peopleNum" name="peopleNum" type="number" required="required" placeholder="인원수" style="width:60px;"> 명
                                        </td>
                                    </tr>
                                    <tr>
                                        <th scope="row">신청인 이름<span class="compul">필수</span></th>
                                        <td class="tc"><label for="name">이름</label></td>
                                        <td><input type="text" id="name" name="name" required="required" placeholder="이름입력"></td>
                                        <td class="tc"><label for="deptname">부서</label></td>
                                        <td><input type="text" id="department" name="department" required="required" placeholder="부서입력"></td>
                                    </tr>
                                    <tr>
                                        <th scope="row"><label for="etc">회의내용 및 참여인원</label></th>
                                        <td colspan="4">
                                            <textarea id="contents" name="contents" cols="100" rows="10" placeholder="회의내용 및 참여인원을 입력하세요."></textarea>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                    </div>
					<!--showDialog($('#wrap'),'','poplayer')-->
                    <p class="tc"><button id="submitBtn" type="submit" class="btns02" onclick="inputAjax();" tabindex="0">확인</button></p>
                    <script>
                    function inputAjax() {
                        if ($("#meeting_date").val() === "") {
                            alert("예약일은 필수 입력입니다.");
                            $("#meeting_date").focus();
                            return false;
                        }
                        if ($("#stime").val() === "") {
                            alert("예약 시작시간은 필수 입력입니다.");
                            $("#stime").focus();
                            return false;
                        }
                        if ($("#etime").val() === "") {
                            alert("예약 종료시간은 필수 입력입니다.");
                            $("#etime").focus();
                            return false;
                        }
                        if ($("#people").val() === "") {
                            alert("사용인원은 필수 입력입니다.");
                            $("#people").focus();
                            return false;
                        }
                        if ($("#name").val() === "") {
                            alert("신청인 이름은 필수 입력입니다.");
                            $("#name").focus();
                            return false;
                        }
                        if ($("#deptname").val() === "") {
                            alert("신청인 부서는 필수 입력입니다.");
                            $("#deptname").focus();
                            return false;
                        }

                        if (confirm("회의실을 신청합니다.")) {
                            processAfterInput();
                        }

                        return false;
                    }

                    function processAfterInput() {
                        var year = "${date.getYear()}";
                        var month = "${date.getMonth()}";
                        var day = "${date.getDay()}";
                        var start = $("#start").val();
                        var end = $("#end").val();
                        var peopleNum = $("#peopleNum").val();
                        var name = $("#name").val();
                        var department = $("#department").val();
                        var contents = $("#contents").val();
                        var createNm = localStorage.getItem('createNm');

                        $.ajax({
                            url: "/api/success",
                            data: JSON.stringify({
                                "year": year,
                                "month": month,
                                "day": day,
                                "start": start,
                                "end": end,
                                "peopleNum": peopleNum,
                                "name": name,
                                "department": department,
                                "contents": contents,
                                "createNm" : createNm
                            }),
                            type: "POST",
                            contentType: "application/json",
                            dataType: "json",
                            success: function (result) {
                                var parsedData = JSON.parse(result.reData);
                                var message = parsedData.reMsg;
                                console.log(result.msg);
                                if (message === "실패") {
                                    alert("이미 예약된 시간입니다.");
                                } else if (message === "성공") {
                                    alert("예약 등록에 성공했습니다."); 
                                } else if (message === "지난 날짜"){
                                	alert("지난 날짜는 예약이 불가능합니다.");
                                }
                                window.location.href = "/";
                            },
                            error: function () {
                                alert("예약 등록에 실패했습니다.");
                                window.location.href = "/";
                            }
                        });
                    }
                        
                    </script>
                    
                    <script type="text/javascript">
	                    $('.timepicker1').timepicker({
	                        timeFormat: 'HH:mm',
	                        interval: 30,
	                        minTime: '09',
	                        startTime: '09:00',
	                        maxTime: '21:30',
	                        //defaultTime: '11',
	
	                        dynamic: false,
	                        dropdown: true,
	                        setIs24HourView : true,
	                        scrollbar: true
	                    });
	                    $('.timepicker2').timepicker({
	                        timeFormat: 'HH:mm',
	                        interval: 30,
	                        minTime: '10',
	                        startTime: '10:00',
	                        maxTime: '22:00',
	                        //defaultTime: '11',
	
	                        dynamic: false,
	                        dropdown: true,
	                        setIs24HourView : true,
	                        scrollbar: true
	                    });
                    </script>
                    
                </div>
            </div><div class="ui-timepicker-container ui-timepicker-hidden ui-helper-hidden" style="display: none;"><div class="ui-timepicker ui-widget ui-widget-content ui-menu ui-corner-all"><ul class="ui-timepicker-viewport"></ul></div></div>
    


</body>
</html>