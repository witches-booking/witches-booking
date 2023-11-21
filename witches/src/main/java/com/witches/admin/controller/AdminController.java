package com.witches.admin.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.witches.admin.dto.AdminDTO;
import com.witches.admin.service.AdminService;
import com.witches.schedule.vo.ScheduleVO;
import com.witches.user.vo.UserVO;

@Controller
public class AdminController {

	private AdminService adminService;
	
	// 관리자 페이지 테이블 데이터 조회 
	@RequestMapping("/admin/showTable")
	public List<AdminDTO> showTable(int tableNum){ // 테이블의 번호를 보내면 그에 맞는 테이블의 데이터를 반환함 
		
		int scheduleNum = 1; // 회의실 예약 관련 테이블
		int userNum = 2; // 사용자 정보 관련 테이블
		
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
	
	
	
}
