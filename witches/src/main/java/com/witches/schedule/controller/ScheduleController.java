package com.witches.schedule.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.witches.schedule.service.CalendarService;
import com.witches.schedule.service.ScheduleService;
import com.witches.schedule.vo.ScheduleVO;
import com.witches.user.dto.UserDTO;

@Controller
public class ScheduleController {

	@Autowired
	private ScheduleService scheduleService;

	@Autowired
	private CalendarService calendarService;

	@Autowired
	private UserDTO userDto;
	
	@Value("${kakao.redirect-url}")
	private String kakaoRedirectUrl;

	@Value("${kakao.rest-api-key}")
	private String kakaoRestApiKey;
	
	// 날짜 클릭시 작성페이지 이동
	@RequestMapping("/scheduleWrite")
	public String scheduleWrite(@ModelAttribute ScheduleVO scheduleVo, 
			@RequestParam int year, int month, int day, Model model) {
		System.out.println("날짜 확인" + year + month + day);
		model.addAttribute("date", scheduleVo);
		
		List<ScheduleVO> dayData  =calendarService.showScheduleListDay(scheduleVo);
		model.addAttribute("dayData", dayData);
		return "/schedule";
	}

	// 로그인 페이지 이동
	@RequestMapping("/login")
	public String moveLoginPage(Model model) {
		model.addAttribute("kakaoRedirectUrl", kakaoRedirectUrl); // 카카오 로그인 용 리다이렉트 url
		model.addAttribute("kakaoRestApiKey", kakaoRestApiKey); // 카카오 로그인용 key
		return "/login";
		
	}
	
	// 캘린더 일정 띄우기
	@RequestMapping("/")
	public String CalendarMain(Model model, @RequestParam(name = "year", defaultValue = "0") int year,
			@RequestParam(name = "month", defaultValue = "0") int month) {
		LocalDate currentDate;

		if (year == 0 || month == 0) {
// URL에 year 또는 month가 없을 경우, 현재 날짜 기준으로 설정
			currentDate = LocalDate.now();
		} else {
			currentDate = LocalDate.of(year, month, 1);
		}

		int currentYear = currentDate.getYear();
		int currentMonth = currentDate.getMonthValue();

// 해당 월의 시작일과 마지막 날짜 계산
		LocalDate firstDayOfMonth = currentDate.withDayOfMonth(1);
		LocalDate lastDayOfMonth = currentDate.withDayOfMonth(currentDate.lengthOfMonth());

// 달력에 출력할 날짜 리스트 생성
		List<Integer> daysOfMonth = new ArrayList<>();
		int dayValue = 1;
		int maxDays = lastDayOfMonth.getDayOfMonth();

		for (int i = 1; i <= 42; i++) { // 6주를 표시하기 위해 최대 42일까지 고려
			if (i >= firstDayOfMonth.getDayOfWeek().getValue() + 1 && dayValue <= maxDays) {
				daysOfMonth.add(dayValue);
				dayValue++;
			} else {
				daysOfMonth.add(null); // 빈 셀 처리
			}
		}

		model.addAttribute("year", currentYear);
		model.addAttribute("month", currentMonth);
		model.addAttribute("currentDate", currentDate);
		model.addAttribute("daysOfMonth", daysOfMonth); // 달력에 출력할 날짜 리스트 추가
		model.addAttribute("kakaoRedirectUrl", kakaoRedirectUrl); // 카카오 로그인 용 리다이렉트 url
		model.addAttribute("kakaoRestApiKey", kakaoRestApiKey); // 카카오 로그인용 key

		LocalDate today = LocalDate.now();
	    int todayNumber = today.getYear() * 10000 + today.getMonthValue() * 100 + today.getDayOfMonth();
	    model.addAttribute("today", todayNumber);
		System.out.println("몇월? "+month);
		
		ScheduleVO schedulevo = new ScheduleVO();
		
		List<ScheduleVO> data ;
		
		if(month==0) {
			schedulevo.setMonth(currentMonth);
			schedulevo.setYear(currentYear);
			data = calendarService.showScheduleList(schedulevo);
			
		}else {
			schedulevo.setMonth(month);
			schedulevo.setYear(year);
			data = calendarService.showScheduleList(schedulevo);
			
		}
		
		if (data != null && !data.isEmpty()) {
			model.addAttribute("data", data);
		} else {
			System.out.println("data x");
		}
		return "/CalendarMain";
	}

	@RequestMapping("/CalendarMain")
	public String CalendarMain2(Model model, @RequestParam(name = "year", defaultValue = "0") int year,
			@RequestParam(name = "month", defaultValue = "0") int month) {
		LocalDate currentDate;

		if (year == 0 || month == 0) {
// URL에 year 또는 month가 없을 경우, 현재 날짜 기준으로 설정
			currentDate = LocalDate.now();
		} else {
			currentDate = LocalDate.of(year, month, 1);
		}

		int currentYear = currentDate.getYear();
		int currentMonth = currentDate.getMonthValue();

// 해당 월의 시작일과 마지막 날짜 계산
		LocalDate firstDayOfMonth = currentDate.withDayOfMonth(1);
		LocalDate lastDayOfMonth = currentDate.withDayOfMonth(currentDate.lengthOfMonth());

// 달력에 출력할 날짜 리스트 생성
		List<Integer> daysOfMonth = new ArrayList<>();
		int dayValue = 1;
		int maxDays = lastDayOfMonth.getDayOfMonth();

		for (int i = 1; i <= 42; i++) { // 6주를 표시하기 위해 최대 42일까지 고려
			if (i >= firstDayOfMonth.getDayOfWeek().getValue() + 1 && dayValue <= maxDays) {
				daysOfMonth.add(dayValue);
				dayValue++;
			} else {
				daysOfMonth.add(null); // 빈 셀 처리
			}
		}

		model.addAttribute("year", currentYear);
		model.addAttribute("month", currentMonth);
		model.addAttribute("currentDate", currentDate);
		model.addAttribute("daysOfMonth", daysOfMonth); // 달력에 출력할 날짜 리스트 추가
		model.addAttribute("kakaoRedirectUrl", kakaoRedirectUrl);
		model.addAttribute("kakaoRestApiKey", kakaoRestApiKey);

		LocalDate today = LocalDate.now();
	    int todayNumber = today.getYear() * 10000 + today.getMonthValue() * 100 + today.getDayOfMonth();
	    model.addAttribute("today", todayNumber);
		System.out.println("몇월? "+month);
		
		ScheduleVO schedulevo = new ScheduleVO();
		
		List<ScheduleVO> data ;
		
		if(month==0) {
			schedulevo.setMonth(currentMonth);
			schedulevo.setYear(currentYear);
			data = calendarService.showScheduleList(schedulevo);
			
		}else {
			schedulevo.setMonth(month);
			schedulevo.setYear(year);
			data = calendarService.showScheduleList(schedulevo);
			
		}
		
		if (data != null && !data.isEmpty()) {
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
			@RequestParam Integer id, HttpSession session) {
		if(session.getAttribute("createNm") != null) {
		String loginId2 = session.getAttribute("createNm").toString();
		model.addAttribute("loginId2", loginId2);}
		ScheduleVO detailMap = scheduleService.scheduleSelect(id);
		model.addAttribute("detailMap", detailMap);
		System.out.println(detailMap);
		return "/detail";
	}

}
