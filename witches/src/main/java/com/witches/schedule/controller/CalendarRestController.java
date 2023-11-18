package com.witches.schedule.controller;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.witches.schedule.dto.CalendarDTO;
import com.witches.schedule.service.CalendarService;
import com.witches.schedule.vo.ScheduleVO;

import lombok.RequiredArgsConstructor;


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
	
	

	// 회의실 예약 rest 버전
	/*
	 * 회의실 예약 메소드 ( 앱 / 웹 사용가능 )
	 * 11월 17일 20:30
	 * post 요청 Json형태로 데이터 전송할것
	 * 
	 * */
	@RequestMapping("/insertSchedule")
	public String insertSchedule (@RequestBody CalendarDTO calendarDTO) {
		System.out.println("rest컨트롤러 실행 예약 저장");
		
		calendarService.insertSchedule(calendarDTO);
		
		
		return "예약 성공";
	}
	
	// 회의실 예약 취소 rest버전
	/*
	 * 회의실 예약 메소드 ( 앱 / 웹 사용가능 )
	 * 11월 17일 20:30
	 * patch 요청 Json형태 id, createNm 담아 전송할것
	 * { id: ???? ,
	 *  createNm: ????,
	 *  cancleName : ???
	 *  cancleReason : ??? }
	 *  >> 근데 취소 날짜도 있어야되지 않나?
	 * */
	@RequestMapping(value="/cancleSchedule" , method = RequestMethod.PATCH)
	public String cancleSchedule(@RequestBody CalendarDTO calendarDTO  ) {
		System.out.println("rest컨트롤러 실행 예약 취소");
		
		String message = calendarService.cancleSchedule(calendarDTO);
		
		return message;
	}
	
	
	
}
