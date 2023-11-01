package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entities.ChuongTrinhKhuyenMai;
import entities.HoaDon;
import entities.KhachHang;
import entities.NhanVien;
import my_Interfaces.I_HoaDon;

public class HoaDon_DAO implements I_HoaDon{

	@Override
	public List<HoaDon> getDsHoaDon() {
		// TODO Auto-generated method stub
		NhanVien_DAO nhanVien_DAO = new NhanVien_DAO();
		KhachHang_DAO khachHang_DAO = new KhachHang_DAO();
		ChuongTrinhKhuyenMai_DAO chuongTrinhKhuyenMai_DAO = new ChuongTrinhKhuyenMai_DAO();
		List<HoaDon> ds = new ArrayList<HoaDon>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select * from HoaDon";
		try {
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maHD = rs.getNString("maHD");
				String maKH = rs.getNString("maKH");
				KhachHang kh = khachHang_DAO.getKHMaKH(maKH);
				String maNV = rs.getNString("maNV");
				NhanVien nv = nhanVien_DAO.getNhanVien(maNV);
				LocalDateTime ngayLap = rs.getTimestamp("ngayLap").toLocalDateTime();
				String maKM = rs.getNString("maKM");
				ChuongTrinhKhuyenMai ctkm = chuongTrinhKhuyenMai_DAO.getCTKM(maKM);
				double tongTienHD = rs.getDouble("tongTienHD");
				double tienKhachTra = rs.getDouble("tienKhachTra");
				HoaDon hd = new HoaDon(maHD, ngayLap, nv, kh, ctkm, tongTienHD, tienKhachTra);
				ds.add(hd);
			}
			statement.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ds;
	}

	@Override
	public HoaDon getHD(String maHD) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean themHD(HoaDon hd) {
		int n=0;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		String sql = "insert into HoaDon values (?, ?, ?, ?, ?, ?, ?)";
		try {
			statement = con.prepareStatement(sql);
			statement.setNString(1, hd.getMaHD());
			if (hd.getKhachHang()==null) {
				statement.setNull(2, Types.NVARCHAR);
			}
			else {
				statement.setNString(2,hd.getKhachHang().getMaKH());
			}
			statement.setNString(3, hd.getNhanVien().getMaNV());
			statement.setNString(4, dtf.format(hd.getNgayLap()));
			if (hd.getCtKhuyenMai()==null) {
				statement.setNull(5, Types.NVARCHAR);
			}
			else {
				statement.setNString(5,hd.getCtKhuyenMai().getMaKM());
			}
			statement.setDouble(6, hd.getTongTienHD());
			statement.setDouble(7, hd.getTienKhachTra());
			n = statement.executeUpdate();
			statement.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n>0;
	}
	
	public static String taoMaHoaDon() {
		int stt=0;
		String id = "HD-";
		int y =(LocalDate.now().getYear());
		String nam=(y+"");
		nam=nam.substring(2, 4);
		int m = LocalDate.now().getMonthValue();
		String thang= null;
		if (m<10) thang="0"+m;
		else thang=m+"";
		int d = LocalDate.now().getDayOfMonth();
		String ngay= null;
		if (d<10) ngay="0"+d;
		else ngay=d+"";
		id=id+ngay+"-"+thang+"-"+nam+"%";
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		String sql = "select stt = count(*) from HoaDon where maHD like ?";
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
