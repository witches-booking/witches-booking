package com.witches.user.dao;

import org.apache.ibatis.annotations.Mapper;

import com.witches.user.vo.UserVO;

@Mapper
public interface UserDAO {

	int userCheck(UserVO userVo);

	void userSelect(UserVO userVo);

	void userInsert(UserVO userVo);

}
