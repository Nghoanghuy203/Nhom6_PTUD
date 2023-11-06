package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entities.ChiTietHoaDon;
import entities.SanPham;
import my_Interfaces.I_ChiTietHoaDon;

public class ChiTietHoaDon_DAO implements I_ChiTietHoaDon{
	private SanPham_DAO sanPham_DAO;

	@Override
	public List<ChiTietHoaDon> getChiTietHD(String maHD) {
		// TODO Auto-generated method stub
		sanPham_DAO = new SanPham_DAO();
		List<ChiTietHoaDon> ds = new ArrayList<ChiTietHoaDon>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		String sql = "select * from ChiTietHoaDon where maHD = ?";
		try {
			statement = con.prepareStatement(sql);
			statement.setNString(1, maHD);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maSP = rs.getNString("maSP");
				SanPham sp = sanPham_DAO.getSanPham(maSP);
				double giaBan = rs.getDouble("giaBan");
				int soLuong = rs.getInt("soLuong");
				ChiTietHoaDon ct = new ChiTietHoaDon(maHD, sp, soLuong, giaBan);
				ds.add(ct);
			}
			statement.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ds;
	}

	@Override
	public boolean themChiTietHD(ChiTietHoaDon cthd) {
		// TODO Auto-generated method stub
		int n=0;
		sanPham_DAO = new SanPham_DAO();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		String sql = "insert into ChiTietHoaDon values (?, ?, ? ,?)";
		try {
			statement = con.prepareStatement(sql);
			statement.setNString(1, cthd.getMaHD());
			statement.setNString(2, cthd.getSanPham().getMaSP());
			statement.setDouble(3, cthd.getGiaBan());
			statement.setInt(4, cthd.getSoLuong());
			n = statement.executeUpdate();
			statement.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n>0;
	}

}
