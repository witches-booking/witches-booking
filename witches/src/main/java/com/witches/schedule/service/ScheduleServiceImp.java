package com.witches.schedule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.witches.schedule.dao.ScheduleDAO;
import com.witches.schedule.vo.ResultVO;
import com.witches.schedule.vo.ScheduleVO;

@Service
public class ScheduleServiceImp implements ScheduleService {

	@Autowired
	private ScheduleDAO scheduleDao;

	@Override
	public ResultVO scheduleInsert(ScheduleVO scheduleVo) {

		try {
			if (scheduleVo.getName() != null && scheduleVo.getTime() != null && scheduleVo.getStart() != null
					&& scheduleVo.getEnd() != null && scheduleVo.getPeopleNum() != null) {
				scheduleDao.scheduleInsert(scheduleVo);
				return new ResultVO("00");
			} else {
				return new ResultVO("02");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultVO("99");
		}

	}
	
	@Override
	public ResultVO scheduleDelete(ScheduleVO scheduleVo) {
		try {
			if(scheduleVo.getId() != null) {
				scheduleDao.scheduleDelete(scheduleVo);
				return new ResultVO("00");
			} else {
				return new ResultVO("02");
			}
		}catch (Exception e) {
			e.printStackTrace();
			return new ResultVO("99");
		}
	}
	
}
