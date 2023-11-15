package com.witches.booking.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.witches.booking.entity.Schedule;

@Mapper
public interface ScheduleMapper {

	@Select("select * from schedule")
	public List<Schedule> showSchedule();

	@Select("SELECT id, year, month, day, DATE_FORMAT(start, '%H:%i') as start, DATE_FORMAT(end, '%H:%i') as end, people_num, name, department, contents, is_delete, cancle_reason, cancle_name,email FROM schedule WHERE month=#{month}")
	public List<Schedule> showScheduleList(int month);	
	
}
