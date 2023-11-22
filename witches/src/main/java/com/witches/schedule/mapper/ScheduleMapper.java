package com.witches.schedule.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.witches.schedule.vo.ScheduleVO;

@Mapper
public interface ScheduleMapper {


	@Select("select * from schedule")
	public List<ScheduleVO> showSchedule();

	@Select("SELECT id, year, month, day, DATE_FORMAT(start, '%H:%i') as start, DATE_FORMAT(end, '%H:%i') as end, people_num, name, department, contents, is_delete, cancle_reason, cancle_name, create_nm FROM schedule WHERE year=#{year} AND month=#{month} AND is_delete='N' ORDER BY day ASC, start ASC")
	public List<ScheduleVO> showScheduleList(ScheduleVO schedulevo);	
	
	@Select("SELECT id, year, month, day, DATE_FORMAT(start, '%H:%i') as start, DATE_FORMAT(end, '%H:%i') as end, people_num, name, department, contents, is_delete, cancle_reason, cancle_name, create_nm FROM schedule WHERE is_delete='N'")
	public List<ScheduleVO> showSchduleAll();

	@Insert("INSERT INTO schedule (start, end, people_num, name, department, is_delete, contents, year, month, day, create_nm) VALUES (#{start}, #{end}, #{peopleNum}, #{name}, #{department}, #{isDelete}, #{contents}, #{year}, #{month}, #{day}, #{createNm})")
	public void insertSchedule(ScheduleVO schedulevo);

	@Select("SELECT COUNT(*) from USER where id= #{createNm}")
	public int checkCreateNm(String createNm);
	
	@Update("UPDATE schedule SET is_delete = 'Y', cancle_name=#{cancelNm}, cancle_reason=#{cancelReason} WHERE id = #{id} ")
	public void cancleSchedule(ScheduleVO scheduleVO);

	@Select("SELECT id, year, month, day, DATE_FORMAT(start, '%H:%i') as start, DATE_FORMAT(end, '%H:%i') as end, people_num, name, department, contents, is_delete, cancle_reason, cancle_name, create_nm FROM schedule WHERE year=#{year} AND month=#{month} AND day =#{day} AND is_delete='N' ORDER BY start ASC")
	public List<ScheduleVO> showScheduleListDay(ScheduleVO schedulevo);
	
	@Select("SELECT MAX(id) FROM schedule")
	public int showScheduleId();
	

}
