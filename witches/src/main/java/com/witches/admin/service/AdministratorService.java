package com.witches.admin.service;

import java.util.List;

import com.witches.admin.vo.AdminVO;
import com.witches.schedule.vo.ResultVO;

public interface AdministratorService {

	List<AdminVO> adminSelect(AdminVO adminVo);

	ResultVO adminLogin(AdminVO adminVo);


}
