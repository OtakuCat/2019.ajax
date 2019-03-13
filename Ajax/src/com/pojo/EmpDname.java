package com.pojo;

import java.util.Date;

public class EmpDname extends Emp {
	private String dname;
	
	
	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public EmpDname() {
		
	}

	public EmpDname(int eid, String ename, int eage, int esex, Date ehireDate, int did,String dname) {
		super(eid, ename, eage, esex, ehireDate, did);
		setDname(dname);
	}

}
