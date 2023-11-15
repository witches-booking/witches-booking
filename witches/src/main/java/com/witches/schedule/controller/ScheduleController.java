package com.witches.schedule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.witches.schedule.service.ScheduleService;
import com.witches.schedule.vo.ResultVO;
import com.witches.schedule.vo.ScheduleVO;
import com.witches.schedule.vo.resultResponse;

@Controller
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
	@PostMapping("/schedule/success")
	public ResponseEntity<Object> scheduleInsert(@ModelAttribute ScheduleVO scheduleVo, Model model) {
		Gson gson = new GsonBuilder().create();
		ResultVO resultVo = scheduleService.scheduleInsert(scheduleVo);
		return ResponseEntity.ok(new resultResponse(gson.toJson(resultVo)));
	}

	/**
	 * 예약 조회
	 * @param ScheduleVO scheduleVo
	 * @param Model model
	 * @param Integer id
	 * @return ScheduleVO
	 * ------------ 이력 ------------
	 * 2023.11.15 / 정윤지 / 최초 적용
	 */
	@GetMapping("/detail")
	public ScheduleVO scheduleSelect(@ModelAttribute ScheduleVO scheduleVo, Model model,
			@RequestParam Integer id) {
		ScheduleVO detailMap = scheduleService.scheduleSelect(id);
		model.addAttribute("detailMap", detailMap);
		System.out.println(detailMap);
		return detailMap;
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
	public ResponseEntity<Object> scheduleCancel(@ModelAttribute ScheduleVO scheduleVo, @RequestParam Integer id) {
		Gson gson = new GsonBuilder().create();
		ResultVO resultVo = scheduleService.scheduleCancel(scheduleVo);
		return ResponseEntity.ok(new resultResponse(gson.toJson(resultVo)));
	}

}
