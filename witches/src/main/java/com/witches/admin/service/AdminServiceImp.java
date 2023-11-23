package com.witches.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.witches.admin.dao.AdminDAO;
import com.witches.admin.vo.AdminVO;
import com.witches.schedule.vo.ResultVO;

@Service
public class AdminServiceImp implements AdministratorService {
	
	@Autowired
	private AdminDAO adminDao;
	
	// 예약 조회
	@Override
	public List<AdminVO> adminSelect(AdminVO adminVo) {
		return adminDao.adminSelect(adminVo);
	}
	
	// 관리자 로그인
	@Override
	public ResultVO adminLogin(AdminVO adminVo) {
		try {
			if(adminVo.getAdminId() != null && adminVo.getAdminPw() != null) {
				if(adminDao.adminLogin(adminVo) == 1) {
					return new ResultVO("00");
				}
				return new ResultVO("01");
			}
			return new ResultVO("02");
		}catch (Exception e) {
			return new ResultVO("99");
		}
	}

}
