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
    // vo -> dto (DTO화 시키기)
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
	
    // dto -> vo (VO화 시키기)
    public static ScheduleVO toScheduleVO(AdminDTO adminDTO) {
    	ScheduleVO schedulevo =new ScheduleVO();
    	
    	// 스케쥴
    	schedulevo.setId(adminDTO.getId());
    	schedulevo.setStart(adminDTO.getStart());
    	schedulevo.setEnd(adminDTO.getEnd());
    	schedulevo.setPeopleNum(adminDTO.getPeopleNum());
    	schedulevo.setName(adminDTO.getName());
    	schedulevo.setDepartment(adminDTO.getDepartment());
    	schedulevo.setContents(adminDTO.getContents());
    	schedulevo.setIsDelete(adminDTO.getIsDelete());
    	schedulevo.setCancelNm(adminDTO.getCancelNm());
    	schedulevo.setCancelReason(adminDTO.getCancelReason());
    	schedulevo.setYear(adminDTO.getYear());
    	schedulevo.setMonth(adminDTO.getMonth());
    	schedulevo.setDay(adminDTO.getDay());
    	schedulevo.setCreateNm(adminDTO.getCreateNm());
    	
    	
        return schedulevo;
    }
    
    
    // only 유저
    // vo -> dto
    public static AdminDTO toAdminDTOU(UserVO uservo) {
    	AdminDTO adminDTO =new AdminDTO();
 	
    	//  유저
    	adminDTO.setLoginId(uservo.getLoginId());
    	adminDTO.setSns(uservo.getSns());
    	
        return adminDTO;
    }
    
    // dto -> vo
    public static UserVO toUserVO(AdminDTO adminDTO) {
    	UserVO uservo = new UserVO();
 	
    	//  유저
    	uservo.setLoginId(adminDTO.getLoginId());
    	uservo.setSns(adminDTO.getSns());
    	
        return uservo;
    }
    
    
    
}
