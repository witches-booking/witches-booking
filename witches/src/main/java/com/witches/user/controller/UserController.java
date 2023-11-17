package com.witches.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.witches.schedule.vo.ResultVO;
import com.witches.schedule.vo.resultResponse;
import com.witches.user.dto.UserDTO;
import com.witches.user.service.UserService;
import com.witches.user.vo.UserVO;
import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Mono;


@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 로그인
	 * @param UserVO userVo
	 * @param String loginId
	 * @param String sns
	 * @param ResultVO resultVo
	 * @return Gson gson
	 * ------------ 이력 ------------
	 * 2023.11.17 / 정윤지 / 최초 적용
	 */
	@PostMapping("/login")
	public ResponseEntity<Object> login(@ModelAttribute UserVO userVo
			, @RequestParam String loginId, @RequestParam String sns) {
		userVo.setLoginId(loginId);
		userVo.setSns(sns);
		System.out.println("로그인 id:::::::::"+userVo.getLoginId());
		System.out.println("로그인 sns:::::::::"+userVo.getSns());
		Gson gson = new GsonBuilder().create();
		ResultVO resultVo = userService.login(userVo);
		return ResponseEntity.ok(new resultResponse(gson.toJson(resultVo)));
	}

	// 카카오 로그인 restApi
	@RequestMapping("/kakaoLogin")
	public Mono<String> kakaoLogin() {
		System.out.println("rest카카오로그인 컨트롤러 실행");
	    String restApiKey = "02b86e71e0895cda12a9361c1cdb773a";
	    String redirectUri = "http://localhost:8449/kakaoLogin";

	    // WebClient 인스턴스 생성
	    WebClient webClient = WebClient.create();
	    String code= null;
	    // 토큰 받아오기
	    String tokenUrl = "https://kauth.kakao.com/oauth/token?grant_type=authorization_code&client_id=" + restApiKey + "&redirect_uri=" + redirectUri + "&code=" + code;
	    Mono<String> tokenResponse = webClient.get()
	            .uri(tokenUrl)
	            .retrieve()
	            .bodyToMono(String.class);

	    return tokenResponse.flatMap(response -> {
	        // JSON 데이터 파싱
	        JsonParser jsonParser = new JsonParser();
	        JsonObject jsonObject = jsonParser.parse(response).getAsJsonObject();

	        String accessToken = jsonObject.get("access_token").getAsString();

	        // 사용자 정보 받아오기
	        String userUrl = "https://kapi.kakao.com/v2/user/me";
	        Mono<String> userResponse = webClient.get()
	                .uri(userUrl)
	                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
	                .retrieve()
	                .bodyToMono(String.class);

	        return userResponse.map(userResponseStr -> {
	            // JSON 데이터 파싱
	            JsonObject userJson = jsonParser.parse(userResponseStr).getAsJsonObject();

	            String id = userJson.get("id").getAsString();

	            // TODO: DB에서 사용자 데이터 확인 및 저장하는 코드를 추가하세요.

	            // 토큰 값과 ID 값을 반환
	            return "{\"access_token\": \"" + accessToken + "\", \"id\": \"" + id + "\"}";
	        });
	    });
	
	
	} //kakaoLogin함수 끝
	
	

	
}
