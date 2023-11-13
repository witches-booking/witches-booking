package com.witches.schedule.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ScheduleVO {
	
	private Integer id;
	private String time;
	private String start;
	private String end;
	private String peopleNum;
	private String name;
	private String department;
	private String contents;
	private String isDelete;

}
