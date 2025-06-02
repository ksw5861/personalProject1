package com.yedam;

import java.util.List;

import com.yedam.ProductManagementDAO;

public class ProductManagementDAO implements ProductManagement {
	ProductDAO dao = new ProductDAO(); // 필드.
	
	@Override
	public boolean addProduct(Product product) {
		return dao.insert(product) == 1;
	}

	@Override
	public boolean modifyProduct(Product product) {
		return dao.update(product) == 1;
//		if(dao.update(product) == 1) {return true;}
//		else return false;
	}

	@Override
	public List<Product> searchProduct(String pcate) {
		return dao.search(pcate);
	}
	
	@Override
	public boolean removeProduct(int pno) {
		return dao.delete(pno) == 1;
	}

	@Override
	public List<Product> productList() {
		return dao.select();
	}

	@Override
	public void save() {

	}
	

}
