package com.witches.schedule.dao;

import org.apache.ibatis.annotations.Mapper;

import com.witches.schedule.vo.ScheduleVO;

@Mapper
public interface ScheduleDAO {
	
	// 예약 중복 확인
	int scheduleCheck(ScheduleVO scheduleVo);
	
	// 예약 등록
	void scheduleInsert(ScheduleVO scheduleVo);
	
	// 예약 조회
	ScheduleVO scheduleSelect(Integer id);
	
	// 예약 취소
	void scheduleCancel(ScheduleVO scheduleVo);


}
