package com.witches.schedule.service;

import com.witches.schedule.vo.ResultVO;
import com.witches.schedule.vo.ScheduleVO;

public interface ScheduleService {

	ResultVO scheduleInsert(ScheduleVO scheduleVo);

	ResultVO scheduleDelete(ScheduleVO scheduleVo);

}
