package com.witches.schedule.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

//	@RequestMapping("/kakakoLogin")
//	public void kakaoLogin (@RequestBody  ) {
//		
//	}
//	
	
	// 로그인 여부 확인
		@PostMapping("/schedule")
	    public Map<String, Object> scheduleEnter(@RequestBody Map<String, Object> requestData) {
			System.out.println(requestData);
	        Object createNm = requestData.get("createNm");
	        Map<String, Object> response = new HashMap<>();
	        response.put("requestData", requestData);

	        if (createNm != null) {
	            response.put("message", "성공");
	        } else {
	            response.put("message", "실패");
	        }

	        return response;
	    }
}
