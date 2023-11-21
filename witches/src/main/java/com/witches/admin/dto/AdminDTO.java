package com.witches.admin.dto;

import com.witches.schedule.vo.ScheduleVO;
import com.witches.user.vo.UserVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdminDTO {

	// User 테이블
	private String loginId;
	private String sns;
	
	// Schedule 테이블
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
	
	// 전체
    public static AdminDTO toAdminDTOA(ScheduleVO schedulevo, UserVO uservo) {
    	AdminDTO adminDTO =new AdminDTO();
    	
    	// 스케쥴
    	adminDTO.setId(schedulevo.getId());
    	adminDTO.setStart(schedulevo.getStart());
    	adminDTO.setEnd(schedulevo.getEnd());
    	adminDTO.setPeopleNum(schedulevo.getPeopleNum());
    	adminDTO.setName(schedulevo.getName());
    	adminDTO.setDepartment(schedulevo.getDepartment());
    	adminDTO.setContents(schedulevo.getContents());
    	adminDTO.setIsDelete(schedulevo.getIsDelete());
    	adminDTO.setCancelNm(schedulevo.getCancelNm());
    	adminDTO.setCancelReason(schedulevo.getCancelReason());
    	adminDTO.setYear(schedulevo.getYear());
    	adminDTO.setMonth(schedulevo.getMonth());
    	adminDTO.setDay(schedulevo.getDay());
    	adminDTO.setCreateNm(schedulevo.getCreateNm());
    	
    	//  유저
    	adminDTO.setLoginId(uservo.getLoginId());
    	adminDTO.setSns(uservo.getSns());
    	
        return adminDTO;
    }
    
    // only 스케쥴
    public static AdminDTO toAdminDTOS(ScheduleVO schedulevo) {
    	AdminDTO adminDTO =new AdminDTO();
    	
    	// 스케쥴
    	adminDTO.setId(schedulevo.getId());
    	adminDTO.setStart(schedulevo.getStart());
    	adminDTO.setEnd(schedulevo.getEnd());
    	adminDTO.setPeopleNum(schedulevo.getPeopleNum());
    	adminDTO.setName(schedulevo.getName());
    	adminDTO.setDepartment(schedulevo.getDepartment());
    	adminDTO.setContents(schedulevo.getContents());
    	adminDTO.setIsDelete(schedulevo.getIsDelete());
    	adminDTO.setCancelNm(schedulevo.getCancelNm());
    	adminDTO.setCancelReason(schedulevo.getCancelReason());
    	adminDTO.setYear(schedulevo.getYear());
    	adminDTO.setMonth(schedulevo.getMonth());
    	adminDTO.setDay(schedulevo.getDay());
    	adminDTO.setCreateNm(schedulevo.getCreateNm());
    	
    	
        return adminDTO;
    }
	
    // only 유저
    public static AdminDTO toAdminDTOU(UserVO uservo) {
    	AdminDTO adminDTO =new AdminDTO();
 	
    	//  유저
    	adminDTO.setLoginId(uservo.getLoginId());
    	adminDTO.setSns(uservo.getSns());
    	
        return adminDTO;
    }
}
