package com.witches.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.witches.admin.dao.AdminDAO;
import com.witches.admin.vo.AdminVO;

@Service
public class AdminServiceImp implements AdministratorService {
	
	@Autowired
	private AdminDAO adminDao;
	
	// 예약 조회
	@Override
	public List<AdminVO> adminSelect(AdminVO adminVo) {
		return adminDao.adminSelect(adminVo);
	}

}
