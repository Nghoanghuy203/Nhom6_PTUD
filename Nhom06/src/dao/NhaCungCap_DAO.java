package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;

import connectDB.ConnectDB;
import entities.NhaCungCap;
import my_Interfaces.I_NhaCungCap;

public class NhaCungCap_DAO implements I_NhaCungCap{

	@Override
	public List<NhaCungCap> getDsNCC() {
		// TODO Auto-generated method stub
		return null;
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
