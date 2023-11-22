package com.witches.admin.vo;

import com.witches.schedule.vo.ScheduleVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdminVO {
	
	private Integer id;
	private String start;
	private String end;
	private String peopleNum;
	private String name;
	private String department;
	private String contents;
	private String isDelete;
	private String cancelReason;
	private String cancelNm;
	private int year;		// db에는 타입을 year로 넣음
	private int month;
	private int day;
	private String createNm;

}
