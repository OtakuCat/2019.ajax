package com.pojo;

import java.util.Date;

public class Emp {
	private int eid;
	private String ename;
	private int eage;
	private int esex;
	private Date ehireDate;
	private int did;

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public int getEage() {
		return eage;
	}

	public void setEage(int eage) {
		this.eage = eage;
	}

	public int getEsex() {
		return esex;
	}

	public void setEsex(int esex) {
		this.esex = esex;
	}

	public Date getEhireDate() {
		return ehireDate;
	}

	public void setEhireDate(Date ehireDate) {
		this.ehireDate = ehireDate;
	}

	public int getDid() {
		return did;
	}

	public void setDid(int did) {
		this.did = did;
	}

	

	public Emp() {
	
	}
	public Emp(String ename, int eage, int esex, Date ehireDate, int did) {
		setEid(did);
		setEname(ename);
		setEage(eage);
		setEsex(esex);
		setEhireDate(ehireDate);
	}

	public Emp(int eid,String ename, int eage, int esex, Date ehireDate, int did) {
		this(ename,eage,esex,ehireDate,did);
		setEid(eid);
	}
	
}
