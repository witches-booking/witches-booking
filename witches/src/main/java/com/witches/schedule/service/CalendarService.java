package com.witches.schedule.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.witches.schedule.dto.CalendarDTO;
import com.witches.schedule.mapper.ScheduleMapper;
import com.witches.schedule.vo.ScheduleVO;

import lombok.RequiredArgsConstructor;


@Service
public class CalendarService {
	
	@Autowired
	private ScheduleMapper scheduleMapper;
	
	public List<ScheduleVO> showScheduleList(int month){
		
		
		return scheduleMapper.showScheduleList(month);
		
		
	}

	public List<ScheduleVO> showSchedule() {
		return scheduleMapper.showSchedule();
		
	}

	public List<ScheduleVO> showScheduleListAll() {
		return scheduleMapper.showSchduleAll();
		
	}

	public void insertSchdule(CalendarDTO calendarDTO) {
	
		ScheduleVO schedulevo = new ScheduleVO();
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
		
		
		scheduleMapper.insertSchedule(schedulevo);
		
	}

	
	
	
}
