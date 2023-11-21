package com.witches.schedule.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.witches.schedule.vo.ScheduleVO;
import com.witches.user.vo.UserVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonAutoDetect
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CalendarDTO implements Serializable{

	private int id;
	private String cancleName;
	private String cancleReason;
	private String start;
	private String end;
	private String peopleNum;
	private String name;
	private String department;
	private String contents;
	private int year;		// db에는 타입을 year로 넣음
	private int month;
	private int day;
	private String createNm;
	private String loginId;
	
    public static CalendarDTO toCalendarDTO(ScheduleVO schedulevo, UserVO uservo) {
    	CalendarDTO calendarDTO =new CalendarDTO();
    	
    	calendarDTO.setStart(schedulevo.getStart());
    	calendarDTO.setEnd(schedulevo.getEnd());
    	calendarDTO.setPeopleNum(schedulevo.getPeopleNum());
    	calendarDTO.setName(schedulevo.getName());
    	calendarDTO.setDepartment(schedulevo.getDepartment());
    	calendarDTO.setContents(schedulevo.getContents());
    	calendarDTO.setYear(schedulevo.getYear());
    	calendarDTO.setMonth(schedulevo.getMonth());
    	calendarDTO.setDay(schedulevo.getDay());
    	calendarDTO.setCreateNm(schedulevo.getCreateNm());
    	calendarDTO.setLoginId(uservo.getLoginId());
    	
        return calendarDTO;
    }
	
	
}
