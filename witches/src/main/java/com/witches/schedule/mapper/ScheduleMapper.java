package com.witches.schedule.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.witches.schedule.vo.ScheduleVO;

@Mapper
public interface ScheduleMapper {


	@Select("select * from schedule")
	public List<ScheduleVO> showSchedule();

	@Select("SELECT id, year, month, day, DATE_FORMAT(start, '%H:%i') as start, DATE_FORMAT(end, '%H:%i') as end, people_num, name, department, contents, is_delete, cancle_reason, cancle_name, create_nm FROM schedule WHERE month=#{month} AND is_delete='N'")
	public List<ScheduleVO> showScheduleList(int month);	
	
	@Select("SELECT id, year, month, day, DATE_FORMAT(start, '%H:%i') as start, DATE_FORMAT(end, '%H:%i') as end, people_num, name, department, contents, is_delete, cancle_reason, cancle_name, create_nm FROM schedule WHERE is_delete='N'")
	public List<ScheduleVO> showSchduleAll();
}
