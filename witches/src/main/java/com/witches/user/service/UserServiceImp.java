package com.witches.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.witches.schedule.vo.ResultVO;
import com.witches.user.dao.UserDAO;
import com.witches.user.vo.UserVO;

@Service
public class UserServiceImp implements UserService {
	
	@Autowired
	private UserDAO userDao;
	
	// 로그인
	@Override
	public ResultVO login(UserVO userVo) {
		try {
			if (userVo.getLoginId() != null && userVo.getSns() != null) {
				System.out.println("==========> 로그인 서비스 진입");
				if (userDao.userCheck(userVo) == 1) {
					userDao.userSelect(userVo);
					return new ResultVO("00");
				} else if (userDao.userCheck(userVo) == 0) {
					userDao.userInsert(userVo);
					return new ResultVO("00");
				} else {
					return new ResultVO("01");
				}
			} else {
				return new ResultVO("02");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultVO("99");
		}
	}

}
