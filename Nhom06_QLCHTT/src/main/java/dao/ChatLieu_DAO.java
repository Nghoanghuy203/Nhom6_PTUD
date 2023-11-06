package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entities.ChatLieu;
import my_Interfaces.I_ChatLieu;

public class ChatLieu_DAO implements I_ChatLieu{

	@Override
	public List<ChatLieu> getDsChatLieu() {
		// TODO Auto-generated method stub
		List<ChatLieu> ds = new ArrayList<ChatLieu>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select * from ChatLieu";
		try {
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String ma = rs.getNString("maChatLieu");
				String ten = rs.getNString("tenChatLieu");
				ChatLieu l = new ChatLieu(ma, ten);
				ds.add(l);
			}
			statement.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ds;
	}

	@Override
	public boolean themChatLieu(ChatLieu cl) {
		// TODO Auto-generated method stub
		return false;
	}

}
