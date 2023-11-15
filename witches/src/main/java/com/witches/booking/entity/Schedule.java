package com.witches.booking.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {

	private Integer id;			// 고유 식별 id
//	@DateTimeFormat(pattern="yyyy-MM-dd")
//	private Date time;		// 예약날짜 -> 날짜를 연 월 일 쪼개는 편이 조회하기 편할 것같아 변경
	private int year;		// db에는 타입을 year로 넣음
	private int month;
	private int day;
	private String start;		//시작시간
	private String end;			// 종료시간
	private String peopleNum;    // 사용인원수
	private String name; 		// 작성자명
	private String department;  // 부서명
	private String contents;   // 회의내용
	private String isDelete;  // y or n ( 예약 취소 여부 (y= 취소))
	private String cancleReason;  // 취소 사유
	private String cancleName; 		// 취소자 이름
	
}
