<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="/Join.module.css">
<!DOCTYPE html>
<html>
<head>
<style>
.container {
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

.center {
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	height: calc(100vh - 300px);
	font-size: 50px;
	border: 1px solid lightgray;
	border-radius: 10px;
	width: 20cm;
	 margin-top: -50px; 
}

.kakao-login-btn {
	padding: 10px 20px;
	border: none;
	background: none;
	margin-top: 200px;
	background: none;
}
</style>
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

		const restApiKey = "${kakaoRestApiKey}";
		const redirectUri = "${kakaoRedirectUrl}";
		console.log('apikey env용', restApiKey)
		console.log('apikey env용', redirectUri)
		//인가 코드 요청
		const codeUrl = 'https://kauth.kakao.com/oauth/authorize?response_type=code&client_id='
				+ restApiKey + '&redirect_uri=' + redirectUri;
		window.location.href = codeUrl;

	};
</script>
</head>
<body>
	<div class="container">
		<div class="center">
			<h4>LOGIN</h4>
			<p style="font-size:1.5rem;">회의 예약을 하려면 로그인을 해주세요!</p>
			<button type="button" class="kakao-login-btn" onclick="kakao()">
				<img src="/img/kakao_login_medium_narrow.png" alt="Kakao Login">
			</button>
		</div>
	</div>
</body>
</body>
</html>
