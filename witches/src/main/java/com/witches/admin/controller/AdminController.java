package com.witches.admin.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.witches.admin.dto.AdminDTO;
import com.witches.admin.service.AdminService;
import com.witches.admin.service.AdministratorService;
import com.witches.admin.vo.AdminVO;
import com.witches.schedule.service.CalendarService;
import com.witches.schedule.service.ScheduleService;
import com.witches.schedule.vo.ScheduleVO;
import com.witches.user.vo.UserVO;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private AdministratorService adminService2;
	
	@Autowired
	private CalendarService calendarService;
	
	@RequestMapping("/main")
	public String adminSelect(@ModelAttribute AdminVO adminVo, Model model, @RequestParam(required = false) String pw,
			@RequestParam(name = "year", defaultValue = "0") int year, @RequestParam(name = "month", defaultValue = "0") int month) {
		
		if(pw != null) {
		if(pw.equals("1234")) {
		ScheduleVO scheduleVo = new ScheduleVO();
		
		LocalDate currentDate;

		if (year == 0 || month == 0) {
			currentDate = LocalDate.now();
		} else {
			currentDate = LocalDate.of(year, month, 1);
		}

		int currentYear = currentDate.getYear();
		int currentMonth = currentDate.getMonthValue();
		
		scheduleVo.setYear(currentYear);
		scheduleVo.setMonth(currentMonth);
		
		model.addAttribute("year", currentYear);
		model.addAttribute("month", currentMonth);
		model.addAttribute("currentDate", currentDate);
		
		
		System.out.println("엔티티 속 month확인"+scheduleVo.getMonth());
		List<ScheduleVO> listMap  =calendarService.showScheduleList(scheduleVo);

		model.addAttribute("listMap", listMap);
		return "/admin";
		}else {
			return "redirect:/";
		}
		}else {
			return "redirect:/";
		}
	}
	
	/* 테이블 분류 번호 확인  */
	int scheduleNum = 1; // 회의실 예약 관련 테이블
	int userNum = 2; // 사용자 정보 관련 테이블
	
	// 관리자 페이지 테이블 데이터 조회 
	
	@RequestMapping("/showTable")
	@ResponseBody
	public List<AdminDTO> showTable(@RequestParam int tableNum){ // 테이블의 번호를 보내면 그에 맞는 테이블의 데이터를 반환함 
		

		// 반환 값을 받아줄 list 생성
		List<AdminDTO> data = null;
		// 요청받은 테이블 조회
		if(tableNum == scheduleNum) {
			data = adminService.showScheduleTable();							
		}else if(tableNum == userNum) {
			data = adminService.showUserTable();			
		}
		
		return data;
	}
	
	// db 데이터 삭제 메소드 - 복수 삭제 가능 ( 필수 
	@RequestMapping(value="/DeleteTableData", method = RequestMethod.DELETE)
	@ResponseBody
	public String DeleteTableData (@RequestBody List<AdminDTO> data, @RequestParam  int tableNum) {
		System.out.println("delete 컨트롤러 도착");
		String message = null;
		
		// 요청받은 테이블 조회
		if(tableNum == scheduleNum) {
			message = adminService. DeleteScheduleData(data);							
		}else if(tableNum == userNum) {
			message = adminService.DeleteUserData(data);
		}
		
		
		return message;
	}
	
}
