package com.witches.booking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.witches.booking.entity.Schedule;
import com.witches.booking.mapper.ScheduleMapper;

@Service
public class CalendarService {

	@Autowired 
	private ScheduleMapper scheduleMapper;
	
	public List<Schedule> showScheduleList(int month){
		
		
		return scheduleMapper.showScheduleList(month);
		
		
	}

	public List<Schedule> showSchedule() {
		return scheduleMapper.showSchedule();
		
	}
	
	
	
	
	
	
}
