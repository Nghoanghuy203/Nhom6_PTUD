package entities;

import java.time.LocalDateTime;
import java.util.List;

public class HoaDon {
	private String maHD;
	private LocalDateTime ngayLap;
	private NhanVien nhanVien;
	private KhachHang khachHang;
	private List<ChiTietHoaDon> dsChiTietHoaDon;
	private double tongTienHD;
	private double tienKhachTra;
	private double tienThuaTraKhach;
	public HoaDon(String maHD, LocalDateTime ngayLap, NhanVien nhanVien, KhachHang khachHang,
			List<ChiTietHoaDon> dsChiTietHoaDon, double tongTienHD, double tienKhachTra, double tienThuaTraKhach) {
		super();
		this.maHD = maHD;
		this.ngayLap = ngayLap;
		this.nhanVien = nhanVien;
		this.khachHang = khachHang;
		this.dsChiTietHoaDon = dsChiTietHoaDon;
		this.tongTienHD = tinhTongTienHD();
		this.tienKhachTra = tienKhachTra;
		this.tienThuaTraKhach = tienThuaTraKhach;
	}
	public String getMaHD() {
		return maHD;
	}
	public void setMaHD(String maHD) {
		this.maHD = maHD;
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
	public KhachHang getKhachHang() {
		return khachHang;
	}
	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}
	public List<ChiTietHoaDon> getDsChiTietHoaDon() {
		return dsChiTietHoaDon;
	}
	public void setDsChiTietHoaDon(List<ChiTietHoaDon> dsChiTietHoaDon) {
		this.dsChiTietHoaDon = dsChiTietHoaDon;
	}
	public double getTongTienHD() {
		return tongTienHD;
	}
	public void setTongTienHD(double tongTienHD) {
		this.tongTienHD = tinhTongTienHD();
	}
	public double getTienKhachTra() {
		return tienKhachTra;
	}
	public void setTienKhachTra(double tienKhachTra) {
		this.tienKhachTra = tienKhachTra;
	}
	public double getTienThuaTraKhach() {
		return tienThuaTraKhach;
	}
	public void setTienThuaTraKhach(double tienThuaTraKhach) {
		this.tienThuaTraKhach = tienThuaTraKhach;
	}
	
	private double tinhTongTienHD() {
		double sum=0;
		for (ChiTietHoaDon chiTietHoaDon : dsChiTietHoaDon) {
			sum+=chiTietHoaDon.getTongTien();
		}
		return sum;
	}
	
	
}
