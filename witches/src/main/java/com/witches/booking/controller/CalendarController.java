package com.witches.booking.controller;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.witches.booking.entity.Schedule;
import com.witches.booking.service.CalendarService;

@Controller
public class CalendarController {

	@Autowired
	CalendarService calendarService ;
	
	// 메인 jsp 띄우기
	@RequestMapping("/")
	public String welcome() {	
		calendarService.showSchedule();
		return "/Scheduler";
	}
	
	// 풀 캘린더 띄우기 
	@PostMapping("Scheduler")
	public void showSchedule() {
		
		calendarService.showSchedule();
		
	}
	
	// 날짜 클릭시 작성페이지 이동
	@RequestMapping("schedule")
	public String moveSchedule(@RequestParam int year, int month, int day) {
		System.out.println("날짜 확인"+year+month+day);
		return "/schedule";
	}
	
	// 캘린더 띄우기
	@RequestMapping("/CalendarMain")
	public String CalendarMain(Model model) {
		Calendar cal = Calendar.getInstance();

		// 현재 월을 구함 
		int month = cal.get(Calendar.MONTH) + 1; // 1을 더해 실제 월을 표현
		
//		List<Schedule> data  =calendarService.showScheduleList(month);
//		System.out.println("조회해온 데이터리스트"+data);
//		model.addAttribute("data",data);
		return "/CalendarMain";
	}
	
	
}
