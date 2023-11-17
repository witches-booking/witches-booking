<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
        <script type="text/javascript" src="https://code.jquery.com/jquery-latest.js"></script>
        <script type="text/javascript" src="/food/jquery.timepicker.min.js"></script>

    </head>
<body>            
			<h3 id="pageTit">회의실 사용</h3>
            <div id="content">
                <div class="contentWrap">                  
                    <div class="inputForm">
                            <input type="hidden" id="idx" name="idx" value="16">
                            <input type="hidden" id="formtype" name="formtype" value="ing_meetCancel">
                            <h4>회의실 취소</h4>
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
                                        <th scope="row"><label for="meeting_date">예약일</label></th>
                                        <td colspan="4" class="date">${detailMap.getYear() }-${detailMap.getMonth() }-${detailMap.getDay() }</td>
                                    </tr>
                                    <tr>
                                        <th scope="row"><label for="stime">예약시간</label></th>
                                        <td colspan="4">${detailMap.getStart() }
                                        <span class="formHyphen"></span>${detailMap.getEnd() }
                                        </td>
                                    </tr>
                                    <tr>
                                        <th scope="row"><label for="people">사용인원</label></th>
                                        <td colspan="4">${detailMap.getPeopleNum() } 명
                                        </td>
                                    </tr>
                                    <tr>
                                        <th scope="row">신청인 이름</th>
                                        <td class="tc"><label for="name">이름</label></td>
                                        <td>${detailMap.getName() }</td>
                                        <td class="tc"><label for="deptname">부서</label></td>
                                        <td>${detailMap.getDepartment() }</td>
                                    </tr>
                                    <tr>
                                        <th scope="row"><label for="etc">회의내용 및 참여인원</label></th>
                                        <td colspan="4">${detailMap.getContents() }
                                            <br>
                                            </td>
                                    </tr>
                                    <tr>
                                        <th scope="row">취소자 이름<span class="compul">필수</span></th>
                                        <td colspan="4">
											<input type="text" id="cancelNm" name="cancelNm" required="required" placeholder="이름입력">
										</td>
                                    </tr>
                                    <tr>
                                        <th scope="row"><label for="canceletc">취소 사유</label></th>
                                        <td colspan="4">
                                            <textarea id="cancelReason" name="cancelReason" cols="100" rows="10" placeholder="취소 사유를 입력하세요."></textarea>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                    </div>
					<!--showDialog($('#wrap'),'','poplayer')-->
                    <p class="tc">
						<button type="submit" class="btns02" onclick="fnList()" tabindex="0">목록보기</button>
						<button id="cancelBtn" type="submit" class="btns02 color2" onclick="inputAjax()" tabindex="1">예약취소</button>
					</p>
                    <script>
                    function inputAjax() {
                        var cancelName = $("#cancelNm").val();
                        var cancelReason = $("#cancelReason").val();

                        if (cancelName === "") {
                            alert("취소자 이름은 필수 입력입니다.");
                            $("#cancelNm").focus();
                            return false;
                        }

                        if (confirm("회의실 예약을 취소합니다.")) {
                            processAfterInput(); // 예약 취소 작업을 수행하는 함수 호출
                        }

                        return false;
                    }

                    function processAfterInput() {
                        var id = "${detailMap.getId()}";
                        var cancelNm = $("#cancelNm").val();
                        var cancelReason = $("#cancelReason").val();

                        $.ajax({
                            url: "/api/cancel",
                            data: {
                                "id": id,
                                "cancelNm": cancelNm,
                                "cancelReason": cancelReason
                            },
                            type: "POST",
                            success: function(result) {
                                var parsedData = JSON.parse(result.reData);
                                var message = parsedData.reMsg;
                                if (message === "성공") {
                                    alert("예약 취소에 성공했습니다.");
                                    window.location.href = "/";
                                }
                            },
                            error: function() {
                                alert("예약 취소에 실패했습니다.");
                                location.reload();
                            }
                        });
                    }
						
						function fnList() {
							history.go(-1);
						}
                    </script>
                </div>
            </div>

</body>
</html>