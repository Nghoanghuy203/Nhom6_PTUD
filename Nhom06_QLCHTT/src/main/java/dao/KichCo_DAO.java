package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
	public static String getMaKichCo(String tenKichCo) {
	    String maKichCo = null;
	    ConnectDB.getInstance();
	    Connection con = ConnectDB.getInstance().getConnection();
	    PreparedStatement statement = null;
	    ResultSet rs = null;
	    String sql = "SELECT maKichCo FROM KichCo WHERE tenKichCo = ?";
	    try {
	        statement = con.prepareStatement(sql);
	        statement.setString(1, tenKichCo);
	        rs = statement.executeQuery();
	        if (rs.next()) {
	            maKichCo = rs.getString("maKichCo");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) {
	                rs.close();
	            }
	            if (statement != null) {
	                statement.close();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    return maKichCo;
	}
}
