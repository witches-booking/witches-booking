package com.witches.user.controller;

import java.time.LocalDate;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
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
	

	// 카카오 로그인 restApi - jsp전용
	@RequestMapping("/api/kakaoLogin")
	public ModelAndView kakaoLogin(@RequestParam String code, HttpSession session, Model model, HttpServletResponse response) {
		System.out.println("rest카카오로그인 컨트롤러 실행");
		String restApiKey = "02b86e71e0895cda12a9361c1cdb773a";
		String redirectUri = "http://localhost:8449/api/kakaoLogin";

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
		System.out.println("엑세스 토큰 확인 : " + accessToken);
		
		// 엑세스 토큰 쿠키에 저장 --> 나중에 로그아웃에 사용
	    Cookie cookie = new Cookie("accessToken", accessToken);
	    // 쿠키 설정
	    cookie.setMaxAge(60 * 60 * 24); // 쿠키의 유효 기간 1일로 설정
	    cookie.setPath("/"); // 쿠키의 경로 설정
	    // 응답에 쿠키 추가
	    response.addCookie(cookie);
		
		// 사용자 정보 받아오기
		String userUrl = "https://kapi.kakao.com/v2/user/me";
		String userResponse = webClient.get().uri(userUrl).header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
				.retrieve().bodyToMono(String.class).block();

		// JSON 데이터 파싱
		JsonObject userJson = jsonParser.parse(userResponse).getAsJsonObject();
		String id = userJson.get("id").getAsString();
		System.out.println("id 값 확인 : "+ id);
		session.setAttribute("createNm", id);

		//DB에서 사용자 데이터 확인 및 저장
		UserVO userVo =new UserVO();
		
		userVo.setLoginId(id);
		userVo.setSns("kakao");
		System.out.println("로그인 id:::::::::" + userVo.getLoginId());
		System.out.println("로그인 sns:::::::::" + userVo.getSns());
		model.addAttribute("loginId", userVo.getLoginId());
		Gson gson = new GsonBuilder().create();
		ResultVO resultVo = userService.login(userVo);
		
		LocalDate currentDate = LocalDate.now();
		int currentYear = currentDate.getYear();
		int currentMonth = currentDate.getMonthValue();
		
		ModelAndView mav = new ModelAndView("redirect:/CalendarMain");
		mav.addObject("year", currentYear);
		mav.addObject("month", currentMonth);
		mav.addObject("createNm", id);
		if(id !=null) {
			mav.addObject("message", "success");			
		}else {
			mav.addObject("message", "fail");
		}
		
		
		return mav; 
	} // kakaoLogin함수 끝

	
	// 카카오 로그인 restapi 앱이든 다른 웹이든 가능하게 설계
    @RequestMapping("/api/kakaoLoginApp")
    public ResponseEntity<Map<String, String>> kakaoLogin(@RequestParam String code, HttpSession session) {
		System.out.println("rest카카오로그인 컨트롤러 실행");
		String restApiKey = "02b86e71e0895cda12a9361c1cdb773a";
		String redirectUri = "http://localhost:8449/api/kakaoLoginApp";

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
		System.out.println("엑세스 토큰 확인 : " + accessToken);
		// 사용자 정보 받아오기
		String userUrl = "https://kapi.kakao.com/v2/user/me";
		String userResponse = webClient.get().uri(userUrl).header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
				.retrieve().bodyToMono(String.class).block();

		// JSON 데이터 파싱
		JsonObject userJson = jsonParser.parse(userResponse).getAsJsonObject();
		String id = userJson.get("id").getAsString();
		System.out.println("id 값 확인 : "+ id);
		session.setAttribute("createNm", id);
    	
    	
        // DB에서 사용자 데이터 확인 및 저장
        UserVO userVo = new UserVO();
        userVo.setLoginId(id);
        userVo.setSns("kakao");
        System.out.println("로그인 id:::::::::" + userVo.getLoginId());
        System.out.println("로그인 sns:::::::::" + userVo.getSns());

        Gson gson = new GsonBuilder().create();
        ResultVO resultVo = userService.login(userVo);

        Map<String, String> response = new HashMap<>();
        response.put("createNm", id);

        if (id != null) {
            response.put("message", "success");
        } else {
            response.put("message", "fail");
        }

        return ResponseEntity.ok(response);
    }

	
	
}
