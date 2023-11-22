package com.witches.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.witches.admin.vo.AdminVO;

@Mapper
public interface AdminDAO {

	List<AdminVO> adminSelect(AdminVO adminVo);

}
