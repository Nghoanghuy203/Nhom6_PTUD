package entities;

public class NhanVien {
	private String maNV;
	private String tenNV;
	private String sdt;
	private boolean gioiTinh;
	private int tuoi;
	private String chucVu;
	private String caLamViec;
	private String diaChi;
	private String email;
	private TaiKhoan taiKhoan;
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public void setTuoi(int tuoi) {
		this.tuoi = tuoi;
	}
	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}
	public void setCaLamViec(String caLamViec) {
		this.caLamViec = caLamViec;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public NhanVien(String maNV, String tenNV, String sdt, boolean gioiTinh, int tuoi, String chucVu, String caLamViec,
			String diaChi, String email, TaiKhoan taiKhoan) {
		super();
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.sdt = sdt;
		this.gioiTinh = gioiTinh;
		this.tuoi = tuoi;
		this.chucVu = chucVu;
		this.caLamViec = caLamViec;
		this.diaChi = diaChi;
		this.email = email;
		this.setTaiKhoan(taiKhoan);
	}
	public NhanVien() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getMaNV() {
		return maNV;
	}
	public String getTenNV() {
		return tenNV;
	}
	public String getSdt() {
		return sdt;
	}
	public boolean isGioiTinh() {
		return gioiTinh;
	}
	public int getTuoi() {
		return tuoi;
	}
	public String getChucVu() {
		return chucVu;
	}
	public String getCaLamViec() {
		return caLamViec;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public String getEmail() {
		return email;
	}
	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}
	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}
	
	
}
