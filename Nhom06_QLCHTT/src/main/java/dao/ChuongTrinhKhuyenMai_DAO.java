package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entities.ChuongTrinhKhuyenMai;
import my_Interfaces.I_ChuongTrinhKhuyenMai;

public class ChuongTrinhKhuyenMai_DAO implements I_ChuongTrinhKhuyenMai{

	@Override
	public List<ChuongTrinhKhuyenMai> getDsCTKM() {
		// TODO Auto-generated method stub
		List<ChuongTrinhKhuyenMai> dsCTKM = new ArrayList<ChuongTrinhKhuyenMai>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select * from ChuongTrinhKhuyenMai";
		try {
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maKM = rs.getNString("maKM");
				double phanTramKhuyenMai = rs.getDouble("phanTramKhuyenMai");
				LocalDateTime ngayBatDau = rs.getTimestamp("ngayBatDau").toLocalDateTime(); 
				LocalDateTime ngayKetThuc =  rs.getTimestamp("ngayKetThuc").toLocalDateTime();  
				String trangThai = rs.getNString("trangThai");
				ChuongTrinhKhuyenMai ctkm = new ChuongTrinhKhuyenMai(maKM, phanTramKhuyenMai, ngayBatDau, ngayKetThuc, trangThai);
				dsCTKM.add(ctkm);
			}
			statement.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsCTKM;
	}

	@Override
	public ChuongTrinhKhuyenMai getCTKM(String maKM) {
		// TODO Auto-generated method stub
		ChuongTrinhKhuyenMai ctkm = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		String sql = "select * from ChuongTrinhKhuyenMai where maKM=?";
		try {
			statement = con.prepareStatement(sql);
			statement.setNString(1, maKM);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				double phanTramKhuyenMai = rs.getDouble("phanTramKhuyenMai");
				LocalDateTime ngayBatDau = rs.getTimestamp("ngayBatDau").toLocalDateTime(); 
				LocalDateTime ngayKetThuc =  rs.getTimestamp("ngayKetThuc").toLocalDateTime();  
				String trangThai = rs.getNString("trangThai");
				ctkm = new ChuongTrinhKhuyenMai(maKM, phanTramKhuyenMai, ngayBatDau, ngayKetThuc, trangThai);
			}
			statement.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ctkm;
	}

	@Override
	public boolean themCTKM(ChuongTrinhKhuyenMai ctkm) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean capNhatCTKM(String maKM, ChuongTrinhKhuyenMai ctkmNew) {
		// TODO Auto-generated method stub
		return false;
	}

}
