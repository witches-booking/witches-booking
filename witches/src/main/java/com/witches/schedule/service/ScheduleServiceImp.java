package com.witches.schedule.service;

import java.time.LocalDate;
import java.time.LocalTime;

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

		LocalDate now = LocalDate.now();
		int nowYear = now.getYear();
		int nowMonth = now.getMonthValue();
		int nowDay = now.getDayOfMonth();

		LocalTime time = LocalTime.now();
		int hour = time.getHour();

		try {
			if (scheduleVo.getName() != null && scheduleVo.getYear() != 0 && scheduleVo.getStart() != null
					&& scheduleVo.getEnd() != null && scheduleVo.getPeopleNum() != null
					&& scheduleVo.getCreateNm() != null) {

				int start = Integer.parseInt(scheduleVo.getStart().substring(0, 2));

				if ((nowYear <= scheduleVo.getYear() && nowMonth <= scheduleVo.getMonth()
						&& nowDay <= scheduleVo.getDay() && hour <= start)
						|| (nowYear <= scheduleVo.getYear() && nowMonth < scheduleVo.getMonth() && hour <= start)) {
					
					if (scheduleDao.scheduleCheck(scheduleVo) == 0) {
						scheduleDao.scheduleInsert(scheduleVo);
						System.out.println(scheduleVo);
						return new ResultVO("00");
					} else {
						return new ResultVO("01");
					}
				} else {
					return new ResultVO("03");
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
			if (scheduleVo.getId() != null && scheduleVo.getCancelReason() != null && scheduleVo.getCancelNm() != null
					&& scheduleVo.getCreateNm().equals(scheduleVo.getLoginId())) {
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
