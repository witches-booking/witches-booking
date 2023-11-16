package com.witches.schedule.controller;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.witches.schedule.service.CalendarService;
import com.witches.schedule.service.ScheduleService;
import com.witches.schedule.vo.ScheduleVO;

@Controller
public class ScheduleController {

	@Autowired
	private ScheduleService scheduleService;

	@Autowired
	CalendarService calendarService;

	// 메인 jsp 띄우기
	@RequestMapping("/Scheduler")
	public String welcome() {
		calendarService.showSchedule();
		return "/Scheduler";
	}

	// 날짜 클릭시 작성페이지 이동
	@RequestMapping("/schedule")
	public String moveSchedule(@ModelAttribute ScheduleVO scheduleVo, @RequestParam int year, int month, int day,
			Model model) {
		System.out.println("날짜 확인" + year + month + day);
//		System.out.println("email확인 : "+email);
		model.addAttribute("date", scheduleVo);
		return "/schedule";
	}

	// 캘린더 일정 띄우기
	@RequestMapping("/")
	public String CalendarMain(Model model) {
		Calendar cal = Calendar.getInstance();

		// 현재 월을 구함
		int month = cal.get(Calendar.MONTH) + 1; // 1을 더해 실제 월을 표현

		List<ScheduleVO> data = calendarService.showScheduleList(month);
		if (data != null && !data.isEmpty()) {
			System.out.println("조회해온 데이터리스트" + data);
			model.addAttribute("data", data);
		} else {
			System.out.println("data x");
		}
		return "/CalendarMain";
	}

	@RequestMapping("/CalendarMainTest")
	public String CalendarMainTest() {
		calendarService.showSchedule();
		return "/CalendarMainTest";
	}

}
