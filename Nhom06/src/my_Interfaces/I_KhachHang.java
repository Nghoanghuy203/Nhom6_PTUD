package my_Interfaces;

import java.util.List;

import entities.KhachHang;

public interface I_KhachHang {
	public List<KhachHang> getDsKhachHang();
	public KhachHang getKH(String sdt);
	public boolean thamKH(KhachHang kh);
	public boolean capNhatKhachHang(String maKH, KhachHang khNew);
}
