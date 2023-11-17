package com.witches.user.controller;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;

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

@CrossOrigin(origins = "*")
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * 로그인
	 * 
	 * @param UserVO   userVo
	 * @param String   loginId
	 * @param String   sns
	 * @param ResultVO resultVo
	 * @return Gson gson ------------ 이력 ------------ 2023.11.17 / 정윤지 / 최초 적용
	 */
	@PostMapping("/login")
	public ResponseEntity<Object> login(@ModelAttribute UserVO userVo, Model model, @RequestParam String loginId,
			@RequestParam String sns) {

		userVo.setLoginId(loginId);
		userVo.setSns(sns);
		System.out.println("로그인 id:::::::::" + userVo.getLoginId());
		System.out.println("로그인 sns:::::::::" + userVo.getSns());
		model.addAttribute("loginId", userVo.getLoginId());
		Gson gson = new GsonBuilder().create();
		ResultVO resultVo = userService.login(userVo);
		return ResponseEntity.ok(new resultResponse(gson.toJson(resultVo)));
	}

	// 카카오 로그인 restApi
	@RequestMapping("/kakaoLogin")
	public String kakaoLogin(@RequestParam String code, HttpSession session) {
	    System.out.println("rest카카오로그인 컨트롤러 실행");
	    String restApiKey = "02b86e71e0895cda12a9361c1cdb773a";
	    String redirectUri = "http://localhost:8449/kakaoLogin";

	    // WebClient 인스턴스 생성
	    WebClient webClient = WebClient.create();

	    // 토큰 받아오기
	    String tokenUrl = "https://kauth.kakao.com/oauth/token?grant_type=authorization_code&client_id=" + restApiKey
	            + "&redirect_uri=" + redirectUri + "&code=" + code;
	    String tokenResponse = webClient.get().uri(tokenUrl).retrieve().bodyToMono(String.class).block();

	    // JSON 데이터 파싱
	    JsonParser jsonParser = new JsonParser();
	    JsonObject jsonObject = jsonParser.parse(tokenResponse).getAsJsonObject();

	    String accessToken = jsonObject.get("access_token").getAsString();

	    // 사용자 정보 받아오기
	    String userUrl = "https://kapi.kakao.com/v2/user/me";
	    String userResponse = webClient.get().uri(userUrl)
	            .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken).retrieve().bodyToMono(String.class).block();

	    // JSON 데이터 파싱
	    JsonObject userJson = jsonParser.parse(userResponse).getAsJsonObject();
	    String id = userJson.get("id").getAsString();
	    session.setAttribute("createNm", id);

	    // TODO: DB에서 사용자 데이터 확인 및 저장하는 코드를 추가하세요.

	    // JSP 페이지 이름 반환
	    return "redirect:/"; // 여기에 원하는 JSP 페이지 이름을 입력하세요.
	} // kakaoLogin함수 끝

}
