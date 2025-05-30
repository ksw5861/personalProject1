package com.yedam;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO extends DAO {
	// 물품추가.

	public int insert(Product product) {
		String sql = "insert into tbl_product(product_no, product_name, product_cate, product_cost, product_quan)"//
				+ "   values(?,?,?,?,?)";
		// 접속.
		getConnect();
		try {
			// stmt = conn.createStatement();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, product.getPno());
			psmt.setString(2, product.getPname());
			psmt.setString(3, product.getPcate());
			psmt.setInt(4, product.getPcost());
			psmt.setInt(5, product.getPquan());
			int r = psmt.executeUpdate();

			return r;// 건수반환.
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return 0;// 반영X
	} // end of insert.

	// 물품수정.
	public int update(Product product) {
		String sql = "UPDATE tbl_product "//
				+ "   SET product_name = ? "//
				+ "      ,product_cate = ? "//
				+ "      ,product_cost = ?"//
		        + "      ,product_quan = ?"
				+ "   WHERE product_no = ?";
		// 접속.
		getConnect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, product.getPname());
			psmt.setString(2, product.getPcate());
			psmt.setInt(3, product.getPcost());
			psmt.setInt(4, product.getPquan());
			psmt.setInt(5,product.getPno());
			int r = psmt.executeUpdate();
			return r;// 건수반환.
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return 0;// 반영X
	} // end of update.

	// 삭제.
	public int delete(int pno) {
		String sql = "delete from tbl_product"//
				+ "   where product_no = ?";
		// 접속.
		getConnect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, pno);
			int r = psmt.executeUpdate();
			return r;// 건수반환.
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return 0;// 반영X
	} // end of delete.

	// 물품목록.
	public List<Product> select() {
		String sql = "select * from tbl_product order by product_no";
		getConnect();
		List<Product> list = new ArrayList<>(); // 컬렉션에 저장.
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery(); // 조회.
			while (rs.next()) {
				Product product = new Product();
				product.setPno(rs.getInt("product_no"));
				product.setPname(rs.getString("product_name"));
				product.setPcate(rs.getString("product_cate"));
				product.setPcost(rs.getInt("product_cost"));
				product.setPquan(rs.getInt("product_quan"));
				// 추가.
				list.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	// 검색
	public List<Product> search() {
		String sql = "select * from tbl_product order by product_no";
		getConnect();
		List<Product> list = new ArrayList<>(); // 컬렉션에 저장.
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery(); // 조회.
			while (rs.next()) {
				Product product = new Product();
				product.setPno(rs.getInt("product_no"));
				product.setPname(rs.getString("product_name"));
				product.setPcate(rs.getString("product_cate"));
				product.setPcost(rs.getInt("product_cost"));
				product.setPquan(rs.getInt("product_quan"));
				// 추가.
				list.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// 상세보기.
		public void detailInfo() {
			// 글번호: 5    작성자: 홍길동
			// 제목: 날씨가 좋아요
			// 내용: 오늘 30도가 넘는데도 좋아요.
			String strFormat = "물품이름: %d   물품카테고리: %s\n";
			strFormat += "물품가격: %s\n";
			strFormat += "물품상세정보: %s";
			//System.out.printf(strFormat, pname, getPcate(), writer, content);
		}
}
