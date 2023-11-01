package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entities.KichCo;
import my_Interfaces.I_KichCo;

public class KichCo_DAO implements I_KichCo{

	@Override
	public List<KichCo> getDsKichCo() {
		// TODO Auto-generated method stub
		List<KichCo> ds = new ArrayList<KichCo>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select * from KichCo";
		try {
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String ma = rs.getNString("maKichCo");
				String ten = rs.getNString("tenKichCo");
				KichCo l = new KichCo(ma, ten);
				ds.add(l);
			}
			statement.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ds;
	}
	
}
