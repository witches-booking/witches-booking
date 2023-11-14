package com.witches.booking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {

	private Integer id;			// 고유 식별 id
	private String time;		// 예약날짜
	private String start;		//시작시간
	private String end;			// 종료시간
	private String peopleNum;    // 사용인원수
	private String name; 		// 작성자명
	private String department;  // 부서명
	private String contents;   // 회의내용
	private String isDelete;  // y or n ( 예약 취소 여부 (y= 취소))
	
	
}
