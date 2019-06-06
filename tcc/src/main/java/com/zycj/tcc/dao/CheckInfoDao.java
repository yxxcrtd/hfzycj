package com.zycj.tcc.dao;

import java.util.Date;

import com.zycj.tcc.domain.CheckInfo;

public interface CheckInfoDao {

	public CheckInfo selectCheckInfoByEmpAndCheckDate(String empNo, Date checkDate);
}
