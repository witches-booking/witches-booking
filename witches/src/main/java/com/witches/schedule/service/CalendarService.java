package com.witches.schedule.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.witches.schedule.dao.ScheduleDAO;
import com.witches.schedule.dto.CalendarDTO;
import com.witches.schedule.mapper.ScheduleMapper;
import com.witches.schedule.vo.ResultVO;
import com.witches.schedule.vo.ScheduleVO;
import com.witches.user.vo.UserVO;

import lombok.RequiredArgsConstructor;


@Service
public class CalendarService {
	
	@Autowired
	private ScheduleDAO scheduleDao;
	
	@Autowired
	private ScheduleMapper scheduleMapper;

	
	public List<ScheduleVO> showScheduleList(ScheduleVO schedulevo){
		
		
		return scheduleMapper.showScheduleList(schedulevo);
		
		
	}

	// admin
	public List<ScheduleVO> adminScheduleList(ScheduleVO schedulevo){
		
		
		return scheduleMapper.adminScheduleList(schedulevo);
		
		
	}

	public List<ScheduleVO> showSchedule() {
		return scheduleMapper.showSchedule();
		
	}

	public List<ScheduleVO> showScheduleListAll() {
		return scheduleMapper.showSchduleAll();
		
	}

	public void insertSchedule(CalendarDTO calendarDTO) {
	
		ScheduleVO schedulevo = new ScheduleVO();
		UserVO uservo = new UserVO();
		
		schedulevo.setStart(calendarDTO.getStart());
		schedulevo.setEnd(calendarDTO.getEnd());
		schedulevo.setPeopleNum(calendarDTO.getPeopleNum());
		schedulevo.setName(calendarDTO.getName());
		schedulevo.setDepartment(calendarDTO.getDepartment());
		schedulevo.setContents(calendarDTO.getContents());
		schedulevo.setIsDelete("N");
		schedulevo.setYear(calendarDTO.getYear());
		schedulevo.setMonth(calendarDTO.getMonth());
		schedulevo.setDay(calendarDTO.getDay());
		schedulevo.setCreateNm(calendarDTO.getCreateNm());

		
		ScheduleVO scheduleVo = new ScheduleVO();
		
		if (scheduleDao.scheduleCheck(scheduleVo) == 0) {
		scheduleMapper.insertSchedule(schedulevo);
		}
	}

	
	public String cancleSchedule(CalendarDTO calendarDTO) {
		
		ScheduleVO schedulevo = new ScheduleVO();
		schedulevo.setId(calendarDTO.getId());
		schedulevo.setCreateNm(calendarDTO.getCreateNm());
		schedulevo.setCancelNm(calendarDTO.getCancleName());
		schedulevo.setCancelReason(calendarDTO.getCancleReason());
				
		// 회원 확인
		int ox = scheduleMapper.checkCreateNm(schedulevo.getCreateNm());
		String message = null;
		if(ox >0) {			
			scheduleMapper.cancleSchedule(schedulevo);
			message = "예약 취소 완료";
		}else {
			message = "예약 취소 실패";
		}
		
		return message;
		
	}

	public List<ScheduleVO> showScheduleListDay(ScheduleVO schedulevo) {
		
		return scheduleMapper.showScheduleListDay(schedulevo);
	}

	
	
	
}
