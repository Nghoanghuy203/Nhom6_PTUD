package entities;

import java.time.LocalDateTime;
import java.util.List;

public class DonDatHang {
	private String maDDH;
	private KhachHang khachHang;
	private LocalDateTime ngayLap;
	private NhanVien nhanVien;
	private List<ChiTietDonDatHang> dsChiTietDonDatHang;
	private double tongTienDonDat;
	private LocalDateTime ngayNhanHang;
	public DonDatHang() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DonDatHang(String maDDH, KhachHang khachHang, LocalDateTime ngayLap, NhanVien nhanVien,
			List<ChiTietDonDatHang> dsChiTietDonDatHang, double tongTienDonDat, LocalDateTime ngayNhanHang) {
		super();
		this.maDDH = maDDH;
		this.khachHang = khachHang;
		this.ngayLap = ngayLap;
		this.nhanVien = nhanVien;
		this.dsChiTietDonDatHang = dsChiTietDonDatHang;
		this.tongTienDonDat = tinhTongTienDonDat();
		this.ngayNhanHang = ngayNhanHang;
	}
	
	
	
	public String getMaDDH() {
		return maDDH;
	}
	public void setMaDDH(String maDDH) {
		this.maDDH = maDDH;
	}
	public KhachHang getKhachHang() {
		return khachHang;
	}
	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}
	public LocalDateTime getNgayLap() {
		return ngayLap;
	}
	public void setNgayLap(LocalDateTime ngayLap) {
		this.ngayLap = ngayLap;
	}
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	public List<ChiTietDonDatHang> getDsChiTietDonDatHang() {
		return dsChiTietDonDatHang;
	}
	public void setDsChiTietDonDatHang(List<ChiTietDonDatHang> dsChiTietDonDatHang) {
		this.dsChiTietDonDatHang = dsChiTietDonDatHang;
	}
	public double getTongTienDonDat() {
		return tongTienDonDat;
	}
	public void setTongTienDonDat(double tongTienDonDat) {
		this.tongTienDonDat = tongTienDonDat;
	}
	public LocalDateTime getNgayNhanHang() {
		return ngayNhanHang;
	}
	public void setNgayNhanHang(LocalDateTime ngayNhanHang) {
		this.ngayNhanHang = ngayNhanHang;
	}
	private double tinhTongTienDonDat() {
		double sum=0;
		for (ChiTietDonDatHang chiTietDonDatHang : dsChiTietDonDatHang) {
			sum+=chiTietDonDatHang.getTongTien();
		}
		return sum;
	}
}
