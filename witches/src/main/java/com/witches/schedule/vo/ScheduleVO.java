package com.witches.schedule.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ScheduleVO {
	
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
	
	private String loginId;

}
