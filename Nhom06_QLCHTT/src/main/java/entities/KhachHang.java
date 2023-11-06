package entities;

public class KhachHang {
	private String maKH;
	private String tenKH;
	private String sdtKH;
	private boolean gioiTinh;
	private String diaChi;
	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}
	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}
	public void setSdtKH(String sdtKH) {
		this.sdtKH = sdtKH;
	}
	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getMaKH() {
		return maKH;
	}
	public String getTenKH() {
		return tenKH;
	}
	public String getSdtKH() {
		return sdtKH;
	}
	public boolean isGioiTinh() {
		return gioiTinh;
	}

	
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public KhachHang(String maKH, String tenKH, String sdtKH, boolean gioiTinh, String diaChi) {
		super();
		this.maKH = maKH;
		this.tenKH = tenKH;
		this.sdtKH = sdtKH;
		this.gioiTinh = gioiTinh;
		this.diaChi = diaChi;
	}
	public KhachHang(String maKH, String tenKH) {
		super();
		this.maKH = maKH;
		this.tenKH = tenKH;
	}
	
	
	
	
	
}
