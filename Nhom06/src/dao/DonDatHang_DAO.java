package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entities.ChiTietDonDatHang;
import entities.DonDatHang;
import entities.KhachHang;
import entities.NhanVien;
import my_Interfaces.I_DonDatHang;

public class DonDatHang_DAO implements I_DonDatHang{

	@Override
	public List<DonDatHang> getDsDonDatHang() {
		// TODO Auto-generated method stub
		List<DonDatHang> dsDDH = new ArrayList<DonDatHang>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select maDDH,kh.maKH,tenKH,nv.maNV,nv.tenNV,ngayLap,tongTienHD,tienKhachTra,trangThai\r\n"
				+ "from DonDatHang ddh join KhachHang kh\r\n"
				+ "on ddh.maKH = kh.maKH join NhanVien nv\r\n"
				+ "on ddh.maNV = nv.maNV";
		try {
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maDDH = rs.getNString("maDDH");
				String maKH = rs.getNString("maKH");
				String tenKH = rs.getNString("tenKH");
				KhachHang kh = new KhachHang(maKH, tenKH);
				String maNV = rs.getNString("maNV");
				String tenNV = rs.getNString("tenNV");
				NhanVien nv = new NhanVien(maNV, tenNV);
				LocalDateTime ngayDat = rs.getTimestamp("ngayLap").toLocalDateTime();
				double tongTien = rs.getDouble("tongTienHD");
				double tienKhachTra = rs.getDouble("tienKhachTra");
				String trangThai = rs.getNString("trangThai");
				DonDatHang ddh = new DonDatHang(maDDH, kh, ngayDat, nv, tongTien, tienKhachTra, trangThai);
				dsDDH.add(ddh);
			}
			statement.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsDDH;
	}

	@Override
	public DonDatHang getDDH(String sdt) {
		// TODO Auto-generated method stub
		DonDatHang ddh = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		String sql = "select maDDH,kh.maKH,tenKH,nv.maNV,nv.tenNV,ngayLap,tongTienHD,tienKhachTra,trangThai,kh.sdtKH\r\n"
				+ "from DonDatHang ddh join KhachHang kh\r\n"
				+ "on ddh.maKH = kh.maKH join NhanVien nv\r\n"
				+ "on ddh.maNV = nv.maNV\r\n"
				+ "where sdtKH=? ";
		try {
			statement = con.prepareStatement(sql);
			statement.setNString(1, sdt);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String ma = rs.getNString("maDDH");
				String maKH = rs.getNString("maKH");
				String tenKH = rs.getNString("tenKH");
				KhachHang kh = new KhachHang(maKH, tenKH);
				String maNV = rs.getNString("maNV");
				String tenNV = rs.getNString("tenNV");
				NhanVien nv = new NhanVien(maNV, tenNV);
				LocalDateTime ngayDat = rs.getTimestamp("ngayLap").toLocalDateTime();
				double tongTien = rs.getDouble("tongTienHD");
				double tienKhachTra = rs.getDouble("tienKhachTra");
				String trangThai = rs.getNString("trangThai");
				ddh = new DonDatHang(ma, kh, ngayDat, nv, tongTien, tienKhachTra, trangThai);
			}
			statement.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ddh;
	}

}
