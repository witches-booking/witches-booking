package com.witches.booking.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.witches.booking.entity.Schedule;
import com.witches.booking.service.CalendarService;

@RestController
public class CalendarRestController {

	private CalendarService calendarService;
	
	
//	@PostMapping("/showScheduleList")
//	public List<Schedule> showScheduleList() { 
//		
//		System.out.println("조회함수 컨트롤러 도착");
//		return calendarService.showScheduleList(month);
//		
//	}
	
}
