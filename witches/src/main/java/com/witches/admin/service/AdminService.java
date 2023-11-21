package com.witches.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.witches.admin.dto.AdminDTO;
import com.witches.admin.mapper.AdminMapper;
import com.witches.schedule.vo.ScheduleVO;
import com.witches.user.vo.UserVO;

@Service
public class AdminService {

	private AdminMapper adminMapper;

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





}
