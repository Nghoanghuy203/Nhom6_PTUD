package dao;

import java.util.List;

import entities.SanPham;
import my_Interfaces.I_SanPham;

public class SanPham_DAO implements I_SanPham{

	@Override
	public List<SanPham> getDsSanPham() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SanPham getSanPham(String maSP) {
		// TODO Auto-generated method stub
		return null;
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

}
