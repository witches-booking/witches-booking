package com.witches.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.witches.booking.service.CalendarService;

@Controller
public class CalendarController {

	@Autowired
	CalendarService calendarService ;
	
	// 메인 jsp 띄우기
	@RequestMapping("/")
	public String welcome() {	
		return "/Scheduler";
	}
	
	// 풀 캘린더 띄우기 
	@PostMapping("Scheduler")
	public void showSchedule() {
		
		calendarService.showSchedule();
		
	}
	
	// 날짜 클릭시 작성페이지 이동
	@RequestMapping("schedule")
	public String moveSchedule(@RequestParam String date) {
		System.out.println("날짜 확인"+date);
		return "/schedule";
	}
	
	// 캘린더 띄우기
	@RequestMapping("/CalendarMain")
	public String CalendarMain() {
		
		return "/CalendarMain";
	}
	
	
}
