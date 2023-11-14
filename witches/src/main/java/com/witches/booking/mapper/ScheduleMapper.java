package com.witches.booking.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.witches.booking.entity.Schedule;

@Mapper
public interface ScheduleMapper {

	@Select("select * from schedule")
	public List<Schedule> showSchedule();

	
	
}
