package com.witches.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.witches.admin.dto.AdminDTO;
import com.witches.admin.mapper.AdminMapper;
import com.witches.schedule.vo.ScheduleVO;
import com.witches.user.vo.UserVO;

@Service
public class AdminService {

	@Autowired
	private AdminMapper adminMapper;
	
	String message = null;

	public List<AdminDTO> showScheduleTable() {
		
		List<ScheduleVO> schedule = adminMapper.showScheduleTable();
		List<AdminDTO> data =null;
		
		// dto에 결과 값 넣어주기
		for(int i =0 ;i<schedule.size();i++) {
		 data.set(i, AdminDTO.toAdminDTOS(schedule.get(i))) ;
		}
		
		return data;
	}
	
	public List<AdminDTO> showUserTable() {
		
		List<UserVO> user = adminMapper.showUserTable();
		List<AdminDTO> data =null;
		
		// dto에 결과 값 넣어주기
		for(int i =0 ;i<user.size();i++) {
		 data.set(i, AdminDTO.toAdminDTOU(user.get(i))) ;
		}
		
		return data;
	}

	
	//********** 삭제 **************
	// 스케쥴 데이터 삭제
	public String DeleteScheduleData(List<AdminDTO> data) {

		List<ScheduleVO> schedulevo = new ArrayList<>();
		for(int i =0 ;i<data.size();i++) {
			 schedulevo.add(i, AdminDTO.toScheduleVO(data.get(i)));
			}
			
		int cnt = adminMapper.DeleteScheduleData(schedulevo);
		
		if(cnt == data.size()) {
			message = "schedule 테이블 데이터 삭제 완료";
			
		}else {
			message = "schedule 테이블 데이터 삭제 실패";
		}
		return message;
	}

	public String DeleteUserData(List<AdminDTO> data) {
		List<UserVO> uservo = new ArrayList<>();
		for(int i =0 ;i<data.size();i++) {
			 uservo.add(i, AdminDTO.toUserVO(data.get(i)));
			}		
		int cnt = adminMapper.DeleteUserData(uservo);
		if(cnt == data.size()) {
			message = "user 테이블 데이터 삭제 완료";
			
		}else {
			message = "user 테이블 데이터 삭제 실패";
		}
		
		return message;
	}





}
