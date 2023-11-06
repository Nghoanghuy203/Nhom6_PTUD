package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;

import connectDB.ConnectDB;
import entities.KhachHang;
import my_Interfaces.I_KhachHang;

public class KhachHang_DAO implements I_KhachHang{

	@Override
	public List<KhachHang> getDsKhachHang() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KhachHang getKH(String sdt) {
		// TODO Auto-generated method stub
		KhachHang kh = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		String sql = "select * from KhachHang where sdtKH = ?";
		try {
			statement = con.prepareStatement(sql);
			statement.setNString(1, sdt);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maKH = rs.getNString("maKH");
				String tenKH = rs.getNString("tenKH");
				boolean gioiTinh = rs.getBoolean("gioiTinh");
				String diaChi = rs.getNString("diaChi");
				String sdtKH = rs.getNString("sdtKH");
				kh = new KhachHang(maKH, tenKH, sdtKH, gioiTinh, diaChi);
			}
			statement.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return kh;
	}

	@Override
	public boolean themKH(KhachHang kh) {
		// TODO Auto-generated method stub
		int n=0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		String sql = "INSERT INTO KhachHang ([maKH], [tenKH],[sdtKH],[gioiTinh], [diaChi]) VALUES	(?, ?,?,?,?)";
		try {
			statement = con.prepareStatement(sql);
			statement.setNString(1, kh.getMaKH());
			statement.setNString(2, kh.getTenKH());
			statement.setNString(3, kh.getSdtKH());
			int gt = kh.isGioiTinh()?1:0;  
			statement.setInt(4, gt);
			statement.setNString(5, kh.getDiaChi());
			n=statement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n>0;
	}

	@Override
	public boolean capNhatKhachHang(String maKH, KhachHang khNew) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public KhachHang getKHMaKH(String maKH) {
		// TODO Auto-generated method stub
		KhachHang kh = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		String sql = "select * from KhachHang where maKH = ?";
		try {
			statement = con.prepareStatement(sql);
			statement.setNString(1, maKH);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				//String maKH = rs.getNString("maKH");
				String tenKH = rs.getNString("tenKH");
				boolean gioiTinh = rs.getBoolean("gioiTinh");
				String diaChi = rs.getNString("diaChi");
				String sdtKH = rs.getNString("sdtKH");
				kh = new KhachHang(maKH, tenKH, sdtKH, gioiTinh, diaChi);
			}
			statement.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return kh;
	}
	
	public static String taoMaKhachHang() {
		int stt=0;
		String id = "KH-";
		int y =(LocalDate.now().getYear());
		String nam=(y+"");
		nam=nam.substring(2, 4);
		//int m = LocalDate.now().getMonthValue();
		//String thang= null;
		//if (m<10) thang="0"+m;
		//else thang=m+"";
		id=id+nam+"%";
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		String sql = "select stt = count(*) from KhachHang where maKH like ?";
		try {
			statement = con.prepareStatement(sql);
			statement.setNString(1, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				stt = rs.getInt("stt");
			}
			statement.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return id.replace("%", "")+"-"+String.format("%05d", stt+1);
	}
}
