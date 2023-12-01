package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

import connectDB.ConnectDB;
import entities.HoaDon;
import entities.HoaDonTra;

public class HoaDonTra_DAO {
	public static String taoMaHoaDon(String maDT) {
		int stt=0;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		String sql = "select stt = count(*) from HoaDonTra where maDT like ?";
		try {
			statement = con.prepareStatement(sql);
			statement.setNString(1, maDT+"%");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				stt = rs.getInt("stt");
			}
			statement.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return maDT+"-DT"+String.format("%05d", stt+1);
	}
	
	public boolean themHDT(HoaDonTra hd) {
		int n=0;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		String sql = "insert into HoaDonTra values (?, ?, ?, ?, ?,?)";
		try {
			statement = con.prepareStatement(sql);
			statement.setNString(1, hd.getMaDT());
			statement.setNString(2, hd.getNhanVien().getMaNV());
			if (hd.getKhachHang()==null) {
				statement.setNull(3, Types.NVARCHAR);
			}
			else {
				statement.setNString(3,hd.getKhachHang().getMaKH());
			}
			statement.setDouble(4, hd.getTongTienTra());
			statement.setNString(5, dtf.format(hd.getNgayTra()));
			statement.setNString(6, hd.getMaDT().substring(0, 17));
			n = statement.executeUpdate();
			statement.close();
		} catch (Exception e) {
			// TODO: handle exception
			//JOptionPane.showMessageDialog(null, "Mỗi hóa đơn chỉ được đổi trả 1 lần!");
			e.printStackTrace();
		}
		return n>0;
	}
}
