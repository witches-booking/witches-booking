package com.witches.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.witches.schedule.vo.ResultVO;
import com.witches.schedule.vo.resultResponse;
import com.witches.user.service.UserService;
import com.witches.user.vo.UserVO;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	// 로그인
	@PostMapping("/login")
	public ResponseEntity<Object> login(@ModelAttribute UserVO userVo, Model model
			, @RequestParam String loginId, @RequestParam String sns) {
		System.out.println("==========> 로그인 진입");
		userVo.setLoginId(loginId);
		userVo.setSns(sns);
		Gson gson = new GsonBuilder().create();
		ResultVO resultVo = userService.login(userVo);
		return ResponseEntity.ok(new resultResponse(gson.toJson(resultVo)));
	}

}
