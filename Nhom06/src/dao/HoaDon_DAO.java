package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;

import connectDB.ConnectDB;
import entities.HoaDon;
import my_Interfaces.I_HoaDon;

public class HoaDon_DAO implements I_HoaDon{

	@Override
	public List<HoaDon> getDsHoaDon() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HoaDon getHD(String maHD) {
		// TODO Auto-generated method stub
		return null;
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
