package com.yedam;

import java.io.Serializable;

public class Product implements Serializable {
	private int pno; // 물품번호
	private String pname; // 물품이름
	private String pcate; // 물품카테고리
	private int pcost; // 물품가격
	private int pquan; // 물품수량
	
	// 생성자
	public Product() {}
	public Product(int pno, String pname, String pcate, int pcost, int pquan) {
		this.pno = pno;
		this.pname = pname;
		this.pcate = pcate;
		this.pcost = pcost;
		this.pquan = pquan;
	}
	
	
	// getter, setter
	
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPcate() {
		return pcate;
	}
	public void setPcate(String pcate) {
		this.pcate = pcate;
	}
	public int getPcost() {
		return pcost;
	}
	public void setPcost(int pcost) {
		this.pcost = pcost;
	}
	public int getPquan() {
		return pquan;
	}
	public void setPquan(int pquan) {
		this.pquan = pquan;
	}
//	public String getPinfo() {
//		return pinfo;
//	}
//	public void setPinfo(String pinfo) {
//		this.pinfo = pinfo;
//	}
	
	// toString
	@Override
	public String toString() {
		return "Product [pno=" + pno + ", pname=" 
				+ pname + ", pcate=" 
				+ pcate + ", pcost=" 
				+ pcost + ", pquan=" 
				+ pquan + "]";
	}
	
	
	
}
