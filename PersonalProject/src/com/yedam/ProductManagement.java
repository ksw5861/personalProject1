package com.yedam;

import java.util.List;
/*
 * 업무로직(등록,변경,삭제,출력)
 * 구현클래스에 의해서 기능이 구현됨.
 * 1. 스트림을 활용한 저장 기능(MemberServiceStream)
 * 2. 데이터베이스를 활용한 저장 기능(MemberServiceDAO)
 */
public interface ProductManagement {
	public boolean addProduct(Product product);// 등록.
	public boolean modifyProduct(Product product);// 변경.
	public boolean removeProduct(int pno);// 삭제.
	public List<Product> productList();// 출력.
	public void save();
	
}