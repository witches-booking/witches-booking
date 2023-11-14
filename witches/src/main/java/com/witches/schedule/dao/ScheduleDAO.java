package com.witches.schedule.dao;

import org.apache.ibatis.annotations.Mapper;

import com.witches.schedule.vo.ScheduleVO;

@Mapper
public interface ScheduleDAO {

	void scheduleInsert(ScheduleVO scheduleVo);

	void scheduleDelete(ScheduleVO scheduleVo);


}
