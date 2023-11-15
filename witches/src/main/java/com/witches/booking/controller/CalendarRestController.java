package com.witches.booking.controller;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.witches.booking.entity.Schedule;
import com.witches.booking.service.CalendarService;

@RestController
public class CalendarRestController {

	@Autowired
	private CalendarService calendarService;
	
	
//	@RequestMapping("/showScheduleList")
//	public List<Schedule> showScheduleList() { 
//		
//		System.out.println("조회함수 컨트롤러 도착");
//		return calendarService.showScheduleList(month);
//		
//	}

	// 월별 조회 (jsp에서 해당 달력이 몇월인지 보내면 db에서 확인후 일정 보냄)
	@RequestMapping("/CalendarMainTestFind")
	public List<Schedule> CalendarMainTestFind (@RequestParam int month) {
		System.out.println("rest컨트롤러 실행");
		System.out.println("month 확인"+month);
		Schedule schedule =new Schedule();
		
		schedule.setMonth(month);
		
		System.out.println("엔티티 속 month확인"+schedule.getMonth());
		
		List<Schedule> data  =calendarService.showScheduleList(schedule.getMonth());

		
		return data;
	}
	
	
	
	
	
}
