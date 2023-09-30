package entities;

import java.time.LocalDateTime;

public class ChuongTrinhKhuyenMai {
	private String maSP;
	private double phanTramKhuyenMai;
	private LocalDateTime ngayBatDau;
	private LocalDateTime ngayKetThuc;
	public ChuongTrinhKhuyenMai(String maSP, double phanTramKhuyenMai, LocalDateTime ngayBatDau,
			LocalDateTime ngayKetThuc) {
		super();
		this.maSP = maSP;
		this.phanTramKhuyenMai = phanTramKhuyenMai;
		this.ngayBatDau = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
	}
	public ChuongTrinhKhuyenMai() {
		super();
	}
	public String getMaSP() {
		return maSP;
	}
	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}
	public double getPhanTramKhuyenMai() {
		return phanTramKhuyenMai;
	}
	public void setPhanTramKhuyenMai(double phanTramKhuyenMai) {
		this.phanTramKhuyenMai = phanTramKhuyenMai;
	}
	public LocalDateTime getNgayBatDau() {
		return ngayBatDau;
	}
	public void setNgayBatDau(LocalDateTime ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}
	public LocalDateTime getNgayKetThuc() {
		return ngayKetThuc;
	}
	public void setNgayKetThuc(LocalDateTime ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}
	
	
}
