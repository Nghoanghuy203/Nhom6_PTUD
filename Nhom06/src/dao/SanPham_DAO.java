package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entities.ChatLieu;
import entities.KichCo;
import entities.LoaiSanPham;
import entities.MauSac;
import entities.NhaCungCap;
import entities.SanPham;
import my_Interfaces.I_SanPham;

public class SanPham_DAO implements I_SanPham {

	@Override
	public List<SanPham> getDsSanPham() {
		// TODO Auto-generated method stub
		List<SanPham> dsSP = new ArrayList<SanPham>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select * \r\n"
				+ "from SanPham sp join LoaiSanPham l\r\n"
				+ "on sp.maLoai = l.maLoai join KichCo kc\r\n"
				+ "on sp.maKichCo = kc.maKichCo join ChatLieu cl\r\n"
				+ "on sp.maChatLieu = cl.maChatLieu join MauSac ms\r\n"
				+ "on sp.maMauSac = ms.maMauSac join NhaCungCap ncc\r\n"
				+ "on sp.maNCC = ncc.maNCC";
		try {
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maSP = rs.getNString("maSP");
				String tenSP = rs.getNString("tenSP");
				double giaNhap = rs.getDouble("giaNhap");
				double giaBan = rs.getDouble("giaBan");
				String maLoai = rs.getNString("maLoai");
				String tenLoai = rs.getNString("tenLoai");
				LoaiSanPham loaiSP = new LoaiSanPham(maLoai, tenLoai);
				byte[] hinhAnh = rs.getBytes("hinhAnh");
				String maKichCo = rs.getNString("maKichCo");
				String tenKichCo = rs.getNString("tenKichCo");
				KichCo kichCo = new KichCo(maKichCo, tenKichCo);
				String maChatLieu = rs.getNString("maChatLieu");
				String tenChatLieu = rs.getNString("tenChatLieu");
				ChatLieu chatLieu = new ChatLieu(maChatLieu, tenChatLieu);
				String maMauSac = rs.getNString("maMauSac");
				String tenMauSac = rs.getNString("tenMauSac");
				MauSac mauSac = new MauSac(maMauSac, tenMauSac);
				int soLuongTon = rs.getInt("soLuongTon");
				String ngay = rs.getNString("ngayNhap");
				LocalDateTime ngayNhap = LocalDateTime.parse(ngay);
				String trangThai = rs.getNString("trangThai");
				String maNCC = rs.getNString("maNCC");
				String tenNCC = rs.getNString("tenNCC");
				String sdtNCC = rs.getNString("sdtNCC");
				String diaChiNCC = rs.getNString("diaChiNCC");
				NhaCungCap nhaCungCap = new NhaCungCap(maNCC, tenNCC, sdtNCC, diaChiNCC);
				SanPham sp = new SanPham(maSP, tenSP, giaNhap, giaBan, loaiSP, hinhAnh, kichCo, chatLieu, mauSac, soLuongTon, ngayNhap, trangThai, nhaCungCap);
				dsSP.add(sp);
			}
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dsSP;
	}

	@Override
	public SanPham getSanPham(String maSP) {
		// TODO Auto-generated method stub
		SanPham sp = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement= null;
		String sql = "select * \r\n"
				+ "from SanPham sp join LoaiSanPham l\r\n"
				+ "on sp.maLoai = l.maLoai join KichCo kc\r\n"
				+ "on sp.maKichCo = kc.maKichCo join ChatLieu cl\r\n"
				+ "on sp.maChatLieu = cl.maChatLieu join MauSac ms\r\n"
				+ "on sp.maMauSac = ms.maMauSac join NhaCungCap ncc\r\n"
				+ "on sp.maNCC = ncc.maNCC\r\n"
				+ "where maSP = ? and trangThai=N'Đang kinh doanh'";
		try {
			statement = con.prepareStatement(sql);
			statement.setNString(1, maSP);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String ma = rs.getNString("maSP");
				String tenSP = rs.getNString("tenSP");
				double giaNhap = rs.getDouble("giaNhap");
				double giaBan = rs.getDouble("giaBan");
				String maLoai = rs.getNString("maLoai");
				String tenLoai = rs.getNString("tenLoai");
				LoaiSanPham loaiSP = new LoaiSanPham(maLoai, tenLoai);
				byte[] hinhAnh = rs.getBytes("hinhAnh");
				String maKichCo = rs.getNString("maKichCo");
				String tenKichCo = rs.getNString("tenKichCo");
				KichCo kichCo = new KichCo(maKichCo, tenKichCo);
				String maChatLieu = rs.getNString("maChatLieu");
				String tenChatLieu = rs.getNString("tenChatLieu");
				ChatLieu chatLieu = new ChatLieu(maChatLieu, tenChatLieu);
				String maMauSac = rs.getNString("maMauSac");
				String tenMauSac = rs.getNString("tenMauSac");
				MauSac mauSac = new MauSac(maMauSac, tenMauSac);
				int soLuongTon = rs.getInt("soLuongTon");
				LocalDateTime ngayNhap = rs.getTimestamp("ngayNhap").toLocalDateTime();
				String trangThai = rs.getNString("trangThai");
				String maNCC = rs.getNString("maNCC");
				String tenNCC = rs.getNString("tenNCC");
				String sdtNCC = rs.getNString("sdtNCC");
				String diaChiNCC = rs.getNString("diaChiNCC");
				NhaCungCap nhaCungCap = new NhaCungCap(maNCC, tenNCC, sdtNCC, diaChiNCC);
				sp = new SanPham(ma, tenSP, giaNhap, giaBan, loaiSP, hinhAnh, kichCo, chatLieu, mauSac, soLuongTon, ngayNhap, trangThai, nhaCungCap);
			}
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sp;
	}

	@Override
	public boolean themSanPham(SanPham sp) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean capNhatSanPham(String maSP, SanPham spNew) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean themSoLuongTon(String maSP, int soLuong) {
		// TODO Auto-generated method stub
		return false;
	}
	public static String taoMaSanPham(String maLoai, String kichCo) {
		int stt=0;
		String id = "SP-";
		String loai = maLoai.substring(2, 4);
		id=id+loai+"-"+kichCo+"%";
		ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from SanPham where maSP like ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setNString(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) stt= rs.getInt("stt");
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id.replace("%", "")+"-"+String.format("%05d", stt+1);
	}
}
