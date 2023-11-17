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

}
