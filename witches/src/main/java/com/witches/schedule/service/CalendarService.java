package com.witches.schedule.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.witches.schedule.mapper.ScheduleMapper;
import com.witches.schedule.vo.ScheduleVO;

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

}
