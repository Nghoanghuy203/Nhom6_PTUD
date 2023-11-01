package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entities.LoaiSanPham;
import my_Interfaces.I_LoaiSanPham;

public class LoaiSanPham_DAO implements I_LoaiSanPham{

	@Override
	public List<LoaiSanPham> getDsLoaiSP() {
		// TODO Auto-generated method stub
		List<LoaiSanPham> ds = new ArrayList<LoaiSanPham>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select * from LoaiSanPham";
		try {
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String ma = rs.getNString("maLoai");
				String ten = rs.getNString("tenLoai");
				LoaiSanPham l = new LoaiSanPham(ma, ten);
				ds.add(l);
			}
			statement.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ds;
	}

}
