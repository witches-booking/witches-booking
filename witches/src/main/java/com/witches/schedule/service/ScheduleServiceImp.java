package com.witches.schedule.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.witches.schedule.dao.ScheduleDAO;
import com.witches.schedule.vo.ResultVO;
import com.witches.schedule.vo.ScheduleVO;

@Service
public class ScheduleServiceImp implements ScheduleService {

	@Autowired
	private ScheduleDAO scheduleDao;

	// 예약 등록
	@Override
	public ResultVO scheduleInsert(ScheduleVO scheduleVo) {

		try {
			if (scheduleVo.getName() != null && scheduleVo.getYear() != 0 && scheduleVo.getStart() != null
					&& scheduleVo.getEnd() != null && scheduleVo.getPeopleNum() != null && scheduleVo.getCreateNm() != null) {
				if (scheduleDao.scheduleCheck(scheduleVo) == 0) {
					scheduleDao.scheduleInsert(scheduleVo);
					System.out.println(scheduleVo);
					return new ResultVO("00");
				} else {
					return new ResultVO("01");
				}
			} else {
				return new ResultVO("02");
			}
		} catch (Exception e) {
			return new ResultVO("99");
		}

	}
	
	// 예약 조회
	@Override
	public ScheduleVO scheduleSelect(Integer id) {
		ScheduleVO scheduleVo = scheduleDao.scheduleSelect(id);
		return scheduleVo;
	}

	// 예약 취소
	@Override
	public ResultVO scheduleCancel(ScheduleVO scheduleVo) {
		try {
			if (scheduleVo.getId() != null && scheduleVo.getCancelReason() != null
					&& scheduleVo.getCancelNm() != null) {
				scheduleDao.scheduleCancel(scheduleVo);
				return new ResultVO("00");
			} else {
				return new ResultVO("02");
			}
		} catch (Exception e) {
			return new ResultVO("99");
		}
	}

}
