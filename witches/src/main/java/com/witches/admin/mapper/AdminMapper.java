package com.witches.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.witches.schedule.vo.ScheduleVO;
import com.witches.user.vo.UserVO;

@Mapper
public interface AdminMapper {

	// table 전체 조회 
	@Select("select * from schedule")
	public List<ScheduleVO> showScheduleTable();
	
	@Select("select * from USER")
	public List<UserVO> showUserTable();

//	@Delete()
//	public List<ScheduleVO> DeleteScheduleData();
//	
}
