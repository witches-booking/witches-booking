package com.witches.schedule.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.test.schedule.service.ScheduleService;
import com.test.schedule.vo.ResultVO;
import com.test.schedule.vo.ScheduleVO;
import com.test.schedule.vo.resultResponse;

@Controller
public class ScheduleController {
	
	@Autowired
	private ScheduleService scheduleService;

	// 예약페이지로 이동
	@RequestMapping(value = "/schedule", method = RequestMethod.GET)
	public String chatPage() {
		return "/schedule";
	}

	// 커뮤니티 삭제
	@PostMapping("/actform")
	public ResponseEntity<Object> scheduleInsert(@ModelAttribute ScheduleVO scheduleVo) {
		Gson gson = new GsonBuilder().create();
		ResultVO resultVo = scheduleService.scheduleInsert(scheduleVo);
		return ResponseEntity.ok(new resultResponse(gson.toJson(resultVo)));
	}

}
