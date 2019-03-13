package com.pojo;

public class Dapt {
	private int did;
	private String dname;
	private String dadder;
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getDadder() {
		return dadder;
	}
	public void setDadder(String dadder) {
		this.dadder = dadder;
	}
	public Dapt(String dname, String dadder) {
		setDname(dname);
		setDadder(dadder);
	}
	public Dapt() {
		
	}
	public Dapt(int did, String dname, String dadder) {
		this(dname,dadder);
		setDid(did);
	}

}
