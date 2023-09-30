package entities;

public class ChiTietHoaDon {
	private String maHD;
	private SanPham sanPham;
	private int soLuong;
	private double tongTien;
	
	public ChiTietHoaDon() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public ChiTietHoaDon(String maHD, SanPham sanPham, int soLuong, double tongTien) {
		super();
		this.maHD = maHD;
		this.sanPham = sanPham;
		this.soLuong = soLuong;
		this.tongTien = sanPham.getGiaBan()*soLuong;
	}



	public String getMaHD() {
		return maHD;
	}

	public void setMaHD(String maHD) {
		this.maHD = maHD;
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

	public double getTongTien() {
		return tongTien;
	}

	public void setTongTien(double tongTien) {
		this.tongTien = this.sanPham.getGiaBan()*this.soLuong;
	}
	
	
}
