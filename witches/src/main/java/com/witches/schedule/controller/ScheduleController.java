package com.witches.schedule.controller;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.witches.schedule.service.CalendarService;
import com.witches.schedule.service.ScheduleService;
import com.witches.schedule.vo.ScheduleVO;
import com.witches.user.dto.UserDTO;

@Controller
public class ScheduleController {

	@Autowired
	private ScheduleService scheduleService;

	@Autowired
	CalendarService calendarService;

	@Autowired
	private UserDTO userDto;
	
	// 로그인 여부 확인
	@PostMapping("/schedule")
	public String scheduleEnter(@RequestBody String createNm, Model model) {
		if (createNm != null) {
			model.addAttribute("msg", "성공");
			return "msg";
		}else {
			model.addAttribute("msg", "실패");
			return "msg";
		}
	}

	// 날짜 클릭시 작성페이지 이동
	@RequestMapping("/scheduleWrite")
	public String scheduleWrite(@ModelAttribute ScheduleVO scheduleVo, 
			@RequestParam int year, int month, int day,
			Model model, HttpSession session) {
		System.out.println("날짜 확인" + year + month + day);
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

	@RequestMapping("/CalendarMain")
	public String CalendarMain2(Model model) {
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
	
	/**
	 * 예약 조회
	 * @param ScheduleVO scheduleVo
	 * @param Model model
	 * @param Integer id
	 * @return ScheduleVO
	 * ------------ 이력 ------------
	 * 2023.11.15 / 정윤지 / 최초 적용
	 */
	@RequestMapping(value="/api/detail", method = { RequestMethod.GET, RequestMethod.POST })
	public String scheduleSelect(@ModelAttribute ScheduleVO scheduleVo, Model model,
			@RequestParam Integer id) {
		ScheduleVO detailMap = scheduleService.scheduleSelect(id);
		model.addAttribute("detailMap", detailMap);
		System.out.println(detailMap);
		return "/detail";
	}

}
