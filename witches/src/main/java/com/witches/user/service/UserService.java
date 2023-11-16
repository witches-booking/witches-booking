package com.witches.user.service;

import com.witches.schedule.vo.ResultVO;
import com.witches.user.vo.UserVO;

public interface UserService {

	ResultVO login(UserVO userVo);

}
