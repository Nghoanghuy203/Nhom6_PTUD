package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entities.NhaCungCap;
import my_Interfaces.I_NhaCungCap;

public class NhaCungCap_DAO implements I_NhaCungCap{

	@Override
	public List<NhaCungCap> getDsNCC() {
		// TODO Auto-generated method stub
		List<NhaCungCap> ds = new ArrayList<NhaCungCap>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select * from NhaCungCap";
		try {
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String ma = rs.getNString("maNCC");
				String ten = rs.getNString("tenNCC");
				String sdt = rs.getNString("sdtNCC");
				String diachi = rs.getNString("diaChiNCC");
				NhaCungCap l = new NhaCungCap(ma, ten, sdt, diachi);
				ds.add(l);
			}
			statement.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ds;
	}
	
	public NhaCungCap getNCC(String maNCC) {
		// TODO Auto-generated method stub
		NhaCungCap ncc = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		String sql = "select * from NhaCungCap where maNCC=?";
		try {
			statement = con.prepareStatement(sql);
			statement.setNString(1, maNCC);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String ten = rs.getString("tenNCC");
				String sdt = rs.getString("sdtNCC");
				String diaChi = rs.getString("diaChiNCC");
				ncc = new NhaCungCap(maNCC,ten,sdt,diaChi);
			}
			statement.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ncc;
	}

	@Override
	public boolean themNCC(NhaCungCap ncc) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean capNhatNCC(String maNCC, NhaCungCap nccNew) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public static String taoMaNhaCungCap(String ten) {
		int stt=0;
        String id = "NCC-"+ten+"%";
		ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select stt = count(*) from NhaCungCap where maNCC like ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setNString(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) stt= rs.getInt("stt")+1;
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id.replace("%", "")+"-"+String.format("%05d", stt);
    }
}
