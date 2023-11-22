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

	// table 데이터 삭제
	@Delete("<script> DELETE FROM schedule WHERE id IN <foreach item='listMap' collection='list' open='(' separator=',' close=')'> #{listMap.id} </foreach> </script>")
	public int DeleteScheduleData(List<ScheduleVO> schedulevo);
	
	@Delete("<script> DELETE FROM USER WHERE id IN <foreach item='item' collection='list' open='(' separator=',' close=')'> #{item.id} </foreach> </script>")
	public int DeleteUserData(List<UserVO> uservo);
	
}
