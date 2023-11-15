package com.witches.schedule.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ScheduleVO {
    private Integer id;
	
	private String year;		// db에는 타입을 year로 넣음
	private String month;
	private String day;
	private String start;
	private String end;
	private String peopleNum;
	private String name;
	private String department;
	private String contents;
	private String isDelete;
	private String cancelReason;
	private String cancelNm;
	
	

}
