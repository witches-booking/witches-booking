package com.witches.schedule.service;

import java.util.List;

import com.witches.schedule.vo.ResultVO;
import com.witches.schedule.vo.ScheduleVO;

public interface ScheduleService {

	// 예약 등록
	ResultVO scheduleInsert(ScheduleVO scheduleVo);

	// 예약 조회
	ScheduleVO scheduleSelect(Integer id);

	// 예약 취소
	ResultVO scheduleCancel(ScheduleVO scheduleVo);

}
