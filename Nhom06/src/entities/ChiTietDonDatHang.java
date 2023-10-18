package entities;

public class ChiTietDonDatHang {
	private String maDDH;
	private SanPham sanPham;
	private int soLuong;

	public ChiTietDonDatHang(String maDDH, SanPham sanPham, int soLuong) {
		super();
		this.maDDH = maDDH;
		this.sanPham = sanPham;
		this.soLuong = soLuong;
	}
	public String getMaDDH() {
		return maDDH;
	}
	public void setMaDDH(String maDDH) {
		this.maDDH = maDDH;
	}
	public SanPham getSanPham() {
		return sanPham;
	}
	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	
	
}
