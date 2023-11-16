package com.witches.schedule.controller;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.witches.schedule.service.CalendarService;
import com.witches.schedule.vo.ScheduleVO;

@CrossOrigin(origins ="*")
@RequestMapping("/api")
@RestController
public class CalendarRestController {

	@Autowired
	private CalendarService calendarService;
	

	// 월별 조회 (jsp에서 해당 달력이 몇월인지 보내면 db에서 확인후 일정 보냄)
	@RequestMapping("/FindScheduleList")
	public List<ScheduleVO> FindScheduleList (@RequestParam int month) {
		System.out.println("rest컨트롤러 실행");
		System.out.println("month 확인"+month);
		ScheduleVO schedule =new ScheduleVO();
		
		schedule.setMonth(month);
		
		System.out.println("엔티티 속 month확인"+schedule.getMonth());
		List<ScheduleVO> data  =calendarService.showScheduleList(schedule.getMonth());

		
		return data;
	}
	
	
	@RequestMapping("/FindScheduleListAll")
	public List<ScheduleVO> FindScheduleListAll () {
		System.out.println("rest컨트롤러 실행");
		ScheduleVO schedule =new ScheduleVO();
		
		
		List<ScheduleVO> data  =calendarService.showScheduleListAll();

		return data;
	}
	
	
//	@RequestMapping("/kakakoLogin")
//	public void kakaoLogin (@RequestBody ) {
//		
//	}
	
	
}
