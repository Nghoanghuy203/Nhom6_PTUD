package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entities.NhanVien;
import entities.TaiKhoan;
import my_Interfaces.I_NhanVien;

public class NhanVien_DAO implements I_NhanVien{

	@Override
	public List<NhanVien> getDsNhanVien() {
		// TODO Auto-generated method stub
		List<NhanVien> ds = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select * from NhanVien nv join TaiKhoan tk on nv.maNV=tk.maNV";
		try {
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maNV = rs.getNString("maNV");
				String tenNV = rs.getNString("tenNV");
				String CCCD = rs.getNString("CCCD");
				String sdtNV = rs.getNString("sdtNV");
				int gt = rs.getInt("gioiTinh");
				boolean gioiTinh = gt==1?true:false;
				LocalDateTime ngaySinh = rs.getTimestamp("ngaySinh").toLocalDateTime();
				String diaChi = rs.getNString("diaChi");
				String email = rs.getNString("email");
				String cccd = rs.getNString("CCCD");
				String chucVu = rs.getNString("chucVu");
				String caLamViec = rs.getNString("caLamViec");
				double phuCap = rs.getDouble("phuCap");
				double heSoLuong = rs.getDouble("heSoLuong");
				double luongCoBan = rs.getDouble("luongCoBan");
				String matKhau = rs.getNString("matKhau");
				TaiKhoan tk = new TaiKhoan(maNV, matKhau);
				NhanVien nv = new NhanVien(maNV, tenNV, sdtNV, gioiTinh, ngaySinh, chucVu, caLamViec, diaChi, email, cccd, tk, phuCap, heSoLuong, luongCoBan);
				ds.add(nv);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ds;
	}

	@Override
	public NhanVien getNhanVien(String maNV) {
		// TODO Auto-generated method stub
		NhanVien nv = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		String sql = "select * from NhanVien nv join TaiKhoan tk on nv.maNV=tk.maNV where nv.maNV = ?";
		try {
			statement = con.prepareStatement(sql);
			statement.setNString(1, maNV);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				//String maNV = rs.getNString("maNV");
				String tenNV = rs.getNString("tenNV");
				String CCCD = rs.getNString("CCCD");
				String sdtNV = rs.getNString("sdtNV");
				int gt = rs.getInt("gioiTinh");
				boolean gioiTinh = gt==1?true:false;
				LocalDateTime ngaySinh = rs.getTimestamp("ngaySinh").toLocalDateTime();
				String diaChi = rs.getNString("diaChi");
				String email = rs.getNString("email");
				String cccd = rs.getNString("CCCD");
				String chucVu = rs.getNString("chucVu");
				String caLamViec = rs.getNString("caLamViec");
				double phuCap = rs.getDouble("phuCap");
				double heSoLuong = rs.getDouble("heSoLuong");
				double luongCoBan = rs.getDouble("luongCoBan");
				String matKhau = rs.getNString("matKhau");
				TaiKhoan tk = new TaiKhoan(maNV, matKhau);
				nv = new NhanVien(maNV, tenNV, sdtNV, gioiTinh, ngaySinh, chucVu, caLamViec, diaChi, email, cccd, tk, phuCap, heSoLuong, luongCoBan);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return nv;
	}

	@Override
	public boolean themNhanVien(NhanVien nv) {
		// TODO Auto-generated method stub
		int n = 0;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		String sql = "INSERT INTO NhanVien([maNV], [tenNV], [gioiTinh], [diaChi], [email], [CCCD], [sdtNV], [ngaySinh],[caLamViec],[chucVu],[phuCap],[luongCoBan],[heSoLuong] ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			statement = con.prepareStatement(sql);
			statement.setNString(1, nv.getMaNV());
			statement.setNString(2, nv.getTenNV());
			statement.setInt(3, nv.isGioiTinh()?1:0);
			statement.setNString(4, nv.getDiaChi());
			statement.setNString(5, nv.getEmail());
			statement.setNString(6, nv.getCccd());
			statement.setNString(7, nv.getSdt());
			statement.setNString(8, dtf.format(nv.getNgaySinh()));
			statement.setNString(9, nv.getCaLamViec());
			statement.setNString(10, nv.getChucVu());
			statement.setDouble(11, nv.getPhuCap());
			statement.setDouble(12, nv.getLuongCoBan());
			statement.setDouble(13, nv.getHeSoLuong());
			n = statement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return n>0;
	}

	@Override
	public boolean capNhatNhanVien(String maNV, NhanVien nvNew) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public static String taoMaNhanVienMoi(String chucVu) {
		int stt=0;
        int y = LocalDate.now().getYear();
		String nam = y+"";
		nam=nam.substring(2, 4);
		String cv = chucVu.equalsIgnoreCase("Nhân viên")?"NV":"QL";
		cv=cv+"-"+nam+"%";
		ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select stt = count(*) from NhanVien where maNV like ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setNString(1, cv);
            rs = pstmt.executeQuery();
            while (rs.next()) stt= rs.getInt("stt")+1;
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cv.replace("%", "")+"-"+String.format("%05d", stt);
    }
}
