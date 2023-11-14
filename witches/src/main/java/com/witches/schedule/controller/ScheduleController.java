package com.witches.schedule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.witches.schedule.service.ScheduleService;
import com.witches.schedule.vo.ResultVO;
import com.witches.schedule.vo.ScheduleVO;
import com.witches.schedule.vo.resultResponse;

@Controller
@RequestMapping("schedule")
public class ScheduleController {
	
	@Autowired
	private ScheduleService scheduleService;

	/**
	 * 예약 등록
	 * @param ResponseEntity<Object>
	 * @param ScheduleVO scheduleVo
	 * @param ResultVO resultVo
	 * @return Gson gson
	 * ------------ 이력 ------------
	 * 2023.11.13 / 정윤지 / 최초 적용
	 */
	@PostMapping("/success")
	public ResponseEntity<Object> scheduleInsert(@ModelAttribute ScheduleVO scheduleVo, @RequestParam String date) {
		scheduleVo.setTime(date);
		Gson gson = new GsonBuilder().create();
		ResultVO resultVo = scheduleService.scheduleInsert(scheduleVo);
		return ResponseEntity.ok(new resultResponse(gson.toJson(resultVo)));
	}

	/**
	 * 예약 취소
	 * @param ResponseEntity<Object>
	 * @param ScheduleVO scheduleVo
	 * @param ResultVO resultVo
	 * @return Gson gson
	 * ------------ 이력 ------------
	 * 2023.11.14 / 정윤지 / 최초 적용
	 */
	@PostMapping("/cancel")
	public ResponseEntity<Object> scheduleCancel(@ModelAttribute ScheduleVO scheduleVo) {
		Gson gson = new GsonBuilder().create();
		ResultVO resultVo = scheduleService.scheduleDelete(scheduleVo);
		return ResponseEntity.ok(new resultResponse(gson.toJson(resultVo)));
	}

}
