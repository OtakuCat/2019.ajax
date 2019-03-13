package com.impl;

import java.util.List;

import com.pojo.Emp;
import com.pojo.EmpDname;
import com.utils.Dao;

public interface EmpImpl extends Dao<Emp> {
	int getRows();
	List<EmpDname> findList(int page,int pageSize);
}
