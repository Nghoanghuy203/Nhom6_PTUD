package gui;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import connectDB.ConnectDB;
import custom.CustomScrollBarUI;
import custom.RoundedCornerBorder;
import custom.cell.TableActionCellEditor;
import custom.cell.TableActionCellRender;
import custom.cell.TableActionEvent;
import dao.ChuongTrinhKhuyenMai_DAO;
import dao.KhachHang_DAO;
import dao.SanPham_DAO;
import entities.ChuongTrinhKhuyenMai;
import entities.KhachHang;
import entities.SanPham;

import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;

import javax.swing.border.MatteBorder;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class ManHinhBanHang extends JPanel {
	private JPanel pn_timKiem;
	private JPanel timKiem;
	private JPanel pnl_formNhapThongTinKH;
	private JTextField txtSearch;
	private JTextField txt_timkiemKH;
	private JButton btnSearch;
	
	private JLabel lbl_kqTimKiem;
	private JLabel lbl_kqMa;
	private JLabel lbl_kqTen;
	private JLabel lbl_kqGiaban;
	private JLabel lbl_kqKichco;
	private JLabel lbl_kqMausac;
	private JLabel lbl_kqSoLuongTon;
	private JSpinner spn_soluong;
	
	private JButton btnThem;
	private JButton btn_timKH;
	private JButton btn_themKH;
	
	private JLabel lbl_kqTenKH;
	private JLabel lbl_kqTongtien;
	@SuppressWarnings("rawtypes")
	private JComboBox cmb_khuyenmai;
	private JComboBox cmb_gioiTinhKHMoi;
	private JLabel lbl_ptkm;
	
	private JLabel lbl_kqtongthanhtoan;
	private JLabel lbl_kqtienkhachtra;
	private JLabel lbl_kqtienthua;
	
	private JButton btn_dathang;
	private JButton btn_thanhtoan;

	private JTable tbl_gioHang;
	private DefaultTableModel model;
	private JButton btnSua;
	private JLabel lblChatLieu;
	private JLabel lbl_kqChatLieu;
	private JLabel lblNCC;
	private JLabel lbl_kqNCC;
	
	private SanPham_DAO sanPham_dao;
	private SanPham sp;
	private KhachHang_DAO khachHang_dao;
	private KhachHang khachHang;
	private ChuongTrinhKhuyenMai_DAO ctkm_dao;
	private ChuongTrinhKhuyenMai ctkm;
	
	private int rowselect;
	private JTextField txt_tienKhachTra;
	
	private DecimalFormat df;
	private JLabel lblNewLabel_1;
	private JTextField txt_kqHotenKHMoi;
	private JTextField txt_kqSdtKHMoi;
	private JTextField txt_kqDiaChiKHMoi;
	private JLabel lblNewLabel_1_1_2;
	
	private JLabel lbl_tenKH;
	private JLabel lbl_Tongtien;
	private JLabel lbl_khuyenmai;
	private JLabel lbl_tongthanhtoan;
	private JLabel lbl_tienkhachtra;
	private JLabel lbl_thienthua;
	
	/**
	 * Create the panel.
	 */

	
	
	@SuppressWarnings({ "serial", "rawtypes", "unchecked" })
	public ManHinhBanHang() {
		setBackground(new Color(255, 255, 255));
		setAutoscrolls(true);
		setSize(1120, 710);
		setLayout(null);
		
	    try {
				ConnectDB.getInstance().connect();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		
		df = new DecimalFormat("#,### VND");
		sanPham_dao = new SanPham_DAO();
        khachHang_dao = new KhachHang_DAO();
        ctkm_dao = new ChuongTrinhKhuyenMai_DAO();
		
        
		pn_timKiem = new JPanel();
		pn_timKiem.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true), "T\u00ECm ki\u1EBFm s\u1EA3n ph\u1EA9m", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(65, 105, 225)));
		pn_timKiem.setBackground(new Color(255, 255, 255));
		pn_timKiem.setBounds(10, 0, 1100, 200);
		add(pn_timKiem);
		pn_timKiem.setLayout(null);
		
		timKiem = new JPanel();
		timKiem.setBackground(new Color(255, 255, 255));
		timKiem.setBounds(10, 20, 300, 30);
		timKiem.setBorder(new RoundedCornerBorder());
		pn_timKiem.add(timKiem);
		timKiem.setLayout(null);
		
		txtSearch = new JTextField();
		txtSearch.setText("Nhập mã sản phẩm ...");
		txtSearch.setForeground(Color.GRAY);
		txtSearch.setEditable(false);
		txtSearch.setBackground(new Color(255, 255, 255));
		txtSearch.setBounds(10, 3, 200, 24);
		txtSearch.setBorder(null);
		txtSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				txtSearch.setText("");
				txtSearch.setForeground(Color.BLACK);
				txtSearch.setEditable(true);
			}
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				txtSearch.setText("Nhập mã sản phẩm ...");
				txtSearch.setForeground(Color.GRAY);
			}
		});
		timKiem.add(txtSearch);
		txtSearch.setColumns(10);
		
		btnSearch = new JButton("Tìm");
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String maSP = txtSearch.getText();
				sp = sanPham_dao.getSanPham(maSP);
				if (sp!=null) {
					lbl_kqTimKiem.setText("");
					lbl_kqMa.setText(sp.getMaSP());
					lbl_kqTen.setText(sp.getTenSP());
					lbl_kqChatLieu.setText(sp.getChatLieu().getTenChatLieu());
					lbl_kqMausac.setText(sp.getMauSac().getTenMauSac());
					lbl_kqKichco.setText(sp.getKichCo().getTenKichCo());
					lbl_kqNCC.setText(sp.getNhaCungCap().getTenNCC());
					lbl_kqGiaban.setText(df.format(sp.getGiaBan()));
					lbl_kqSoLuongTon.setText(sp.getSoLuongTon()+"");
					btnThem.setEnabled(true);
					spn_soluong.setModel(new SpinnerNumberModel(1, 1, sp.getSoLuongTon(), 1));
				}
				else {
					lbl_kqTimKiem.setText("Không tìm thấy!");
					xoaTrangKqTimKiem();
				}
			}
		});
		btnSearch.setBackground(new Color(65, 105, 225));
		btnSearch.setIcon(new ImageIcon(ManHinhBanHang.class.getResource("/images/search.png")));
		btnSearch.setBounds(220, 3, 70, 24);
		timKiem.add(btnSearch);
		
		JLabel lblKqTimKiem = new JLabel("Kết quả:");
		lblKqTimKiem.setBounds(10, 55, 50, 20);
		pn_timKiem.add(lblKqTimKiem);
		
		JPanel pn_kqTimKiem = new JPanel();
		pn_kqTimKiem.setBackground(new Color(255, 250, 240));
		pn_kqTimKiem.setBounds(10, 80, 1080, 100);
		pn_timKiem.add(pn_kqTimKiem);
		pn_kqTimKiem.setLayout(null);
		
		JLabel lblMa = new JLabel("Mã sản phẩm:");
		lblMa.setFont(new Font("Arial", Font.PLAIN, 11));
		lblMa.setHorizontalAlignment(SwingConstants.LEFT);
		lblMa.setBounds(10, 20, 70, 20);
		pn_kqTimKiem.add(lblMa);
		
		lbl_kqMa = new JLabel("");
		lbl_kqMa.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(128, 128, 128)));
		lbl_kqMa.setBounds(80, 20, 80, 20);
		pn_kqTimKiem.add(lbl_kqMa);
		
		JLabel lblTen = new JLabel("Tên sản phẩm:");
		lblTen.setHorizontalAlignment(SwingConstants.LEFT);
		lblTen.setFont(new Font("Arial", Font.PLAIN, 11));
		lblTen.setBounds(180, 20, 74, 20);
		pn_kqTimKiem.add(lblTen);
		
		lbl_kqTen = new JLabel("");
		lbl_kqTen.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(128, 128, 128)));
		lbl_kqTen.setBounds(255, 20, 196, 20);
		pn_kqTimKiem.add(lbl_kqTen);
		
		btnThem = new JButton("Thêm");
		btnThem.setBackground(new Color(0, 128, 0));
		btnThem.setFont(new Font("Arial", Font.BOLD, 14));
		btnThem.setIcon(new ImageIcon(ManHinhBanHang.class.getResource("/images/them.png")));
		btnThem.setBounds(960, 60, 110, 30);
		btnThem.setEnabled(false);
		pn_kqTimKiem.add(btnThem);
		
		JLabel lblSoluong = new JLabel("Số lượng:");
		lblSoluong.setHorizontalAlignment(SwingConstants.LEFT);
		lblSoluong.setFont(new Font("Arial", Font.PLAIN, 11));
		lblSoluong.setBounds(600, 60, 50, 20);
		pn_kqTimKiem.add(lblSoluong);
		
		spn_soluong = new JSpinner(new SpinnerNumberModel(1,1,10,1));
		spn_soluong.getModel().setValue(1);
		spn_soluong.setBounds(650, 55, 50, 30);
		pn_kqTimKiem.add(spn_soluong);
		
		JLabel lblGiaban = new JLabel("Giá bán:");
		lblGiaban.setHorizontalAlignment(SwingConstants.LEFT);
		lblGiaban.setFont(new Font("Arial", Font.PLAIN, 11));
		lblGiaban.setBounds(270, 60, 40, 20);
		pn_kqTimKiem.add(lblGiaban);
		
		lbl_kqGiaban = new JLabel("");
		lbl_kqGiaban.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(128, 128, 128)));
		lbl_kqGiaban.setBounds(320, 60, 90, 20);
		pn_kqTimKiem.add(lbl_kqGiaban);
		
		JLabel lblKichco = new JLabel("Kích cỡ:");
		lblKichco.setHorizontalAlignment(SwingConstants.LEFT);
		lblKichco.setFont(new Font("Arial", Font.PLAIN, 11));
		lblKichco.setBounds(732, 20, 40, 20);
		pn_kqTimKiem.add(lblKichco);
		
		lbl_kqKichco = new JLabel("");
		lbl_kqKichco.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(128, 128, 128)));
		lbl_kqKichco.setBounds(776, 20, 50, 20);
		pn_kqTimKiem.add(lbl_kqKichco);
		
		JLabel lblMausac = new JLabel("Màu sắc:");
		lblMausac.setHorizontalAlignment(SwingConstants.LEFT);
		lblMausac.setFont(new Font("Arial", Font.PLAIN, 11));
		lblMausac.setBounds(610, 20, 50, 20);
		pn_kqTimKiem.add(lblMausac);
		
		lbl_kqMausac = new JLabel("");
		lbl_kqMausac.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(128, 128, 128)));
		lbl_kqMausac.setBounds(662, 20, 60, 20);
		pn_kqTimKiem.add(lbl_kqMausac);
		
		btnSua = new JButton("Sửa");
		btnSua.setIcon(new ImageIcon(ManHinhBanHang.class.getResource("/images/sua.png")));
		btnSua.setFont(new Font("Arial", Font.BOLD, 14));
		btnSua.setBackground(new Color(255, 140, 0));
		btnSua.setBounds(840, 60, 110, 30);
		pn_kqTimKiem.add(btnSua);
		btnSua.setEnabled(false);
		
		
		lblChatLieu = new JLabel("Chất liệu:");
		lblChatLieu.setHorizontalAlignment(SwingConstants.LEFT);
		lblChatLieu.setFont(new Font("Arial", Font.PLAIN, 11));
		lblChatLieu.setBounds(470, 20, 50, 20);
		pn_kqTimKiem.add(lblChatLieu);
		
		lbl_kqChatLieu = new JLabel("");
		lbl_kqChatLieu.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(128, 128, 128)));
		lbl_kqChatLieu.setBounds(520, 20, 80, 20);
		pn_kqTimKiem.add(lbl_kqChatLieu);
		
		lblNCC = new JLabel("Nhà cung cấp:");
		lblNCC.setHorizontalAlignment(SwingConstants.LEFT);
		lblNCC.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNCC.setBounds(10, 60, 80, 20);
		pn_kqTimKiem.add(lblNCC);
		
		lbl_kqNCC = new JLabel("");
		lbl_kqNCC.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(128, 128, 128)));
		lbl_kqNCC.setBounds(90, 60, 150, 20);
		pn_kqTimKiem.add(lbl_kqNCC);
		
		JLabel lblSoLuongTon = new JLabel("Số lượng tồn:");
		lblSoLuongTon.setHorizontalAlignment(SwingConstants.LEFT);
		lblSoLuongTon.setFont(new Font("Arial", Font.PLAIN, 11));
		lblSoLuongTon.setBounds(440, 60, 70, 20);
		pn_kqTimKiem.add(lblSoLuongTon);
		
		lbl_kqSoLuongTon = new JLabel("");
		lbl_kqSoLuongTon.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(128, 128, 128)));
		lbl_kqSoLuongTon.setBounds(510, 60, 40, 20);
		pn_kqTimKiem.add(lbl_kqSoLuongTon);
		
		lbl_kqTimKiem = new JLabel("");
		lbl_kqTimKiem.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lbl_kqTimKiem.setForeground(new Color(255, 0, 0));
		lbl_kqTimKiem.setBounds(55, 55, 100, 20);
		pn_timKiem.add(lbl_kqTimKiem);
		
		JPanel pn_thongTinHD = new JPanel();
		pn_thongTinHD.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true), "Th\u00F4ng tin h\u00F3a \u0111\u01A1n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(65, 105, 225)));
		pn_thongTinHD.setBackground(new Color(255, 255, 255));
		pn_thongTinHD.setBounds(780, 200, 330, 500);
		add(pn_thongTinHD);
		pn_thongTinHD.setLayout(null);
		
		JPanel pn_KH = new JPanel();
		pn_KH.setBackground(new Color(255, 255, 255));
		pn_KH.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		pn_KH.setBounds(15, 20, 300, 30);
		pn_thongTinHD.add(pn_KH);
		pn_KH.setLayout(null);
		
		txt_timkiemKH = new JTextField();
		txt_timkiemKH.setBorder(null);
		txt_timkiemKH.setToolTipText("");
		txt_timkiemKH.setForeground(new Color(192, 192, 192));
		txt_timkiemKH.setText("Nhập số điện thoại khách hàng ...");
		txt_timkiemKH.setBounds(5, 5, 190, 20);
		pn_KH.add(txt_timkiemKH);
		txt_timkiemKH.setColumns(10);
		txt_timkiemKH.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				txt_timkiemKH.setText("");
				txt_timkiemKH.setForeground(Color.BLACK);
				txt_timkiemKH.setEditable(true);
			}
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				txt_timkiemKH.setText("Nhập số điện thoại khách hàng ...");
				txt_timkiemKH.setForeground(Color.GRAY);
			}
		});
		
		btn_timKH = new JButton("");
		btn_timKH.setHorizontalAlignment(SwingConstants.CENTER);
		btn_timKH.setIcon(new ImageIcon(ManHinhBanHang.class.getResource("/images/search.png")));
		btn_timKH.setBounds(210, 5, 40, 20);
		pn_KH.add(btn_timKH);
		
		btn_timKH.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				String sdt = txt_timkiemKH.getText();
				khachHang = khachHang_dao.getKH(sdt);
				if (khachHang!=null) {
					pnl_formNhapThongTinKH.setVisible(false);
					lbl_tenKH.setVisible(true);
					lbl_kqTenKH.setVisible(true);
					thayDoiViTriComponents(0);
					lbl_kqTenKH.setText(khachHang.getTenKH());
				} 
				else {
					pnl_formNhapThongTinKH.setVisible(false);
					lbl_tenKH.setVisible(true);
					lbl_kqTenKH.setVisible(true);
					thayDoiViTriComponents(0);
					JOptionPane.showMessageDialog(null,"Không tìm thấy khách hàng!");
					lbl_kqTenKH.setText("Khách lẻ");
				}
			}
		});
		
		btn_themKH = new JButton("");
		btn_themKH.setIcon(new ImageIcon(ManHinhBanHang.class.getResource("/images/icon_themKH.png")));
		btn_themKH.setHorizontalAlignment(SwingConstants.CENTER);
		btn_themKH.setBounds(260, 5, 40, 20);
		pn_KH.add(btn_themKH);
		
		
		
		lbl_tenKH = new JLabel("Khách hàng:");
		lbl_tenKH.setBounds(15, 60, 90, 20);
		pn_thongTinHD.add(lbl_tenKH);
		
		lbl_kqTenKH = new JLabel("Khách lẻ");
		lbl_kqTenKH.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_kqTenKH.setBounds(125, 60, 190, 20);
		pn_thongTinHD.add(lbl_kqTenKH);
		
		lbl_Tongtien = new JLabel("Tổng tiền:");
		lbl_Tongtien.setBounds(15, 110, 90, 20);
		pn_thongTinHD.add(lbl_Tongtien);
		
		lbl_kqTongtien = new JLabel("0 VND");
		lbl_kqTongtien.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_kqTongtien.setBounds(125, 110, 190, 20);
		pn_thongTinHD.add(lbl_kqTongtien);
		
		lbl_khuyenmai = new JLabel("Mã khuyến mãi:");
		lbl_khuyenmai.setBounds(15, 160, 90, 20);
		pn_thongTinHD.add(lbl_khuyenmai);
		
		String[] strKhuyenMai = capNhatCmbMaCTKM();
		cmb_khuyenmai = new JComboBox(strKhuyenMai);
		cmb_khuyenmai.setBorder(null);
		cmb_khuyenmai.setBackground(new Color(255, 255, 255));
		cmb_khuyenmai.setBounds(125, 160, 190, 20);
		pn_thongTinHD.add(cmb_khuyenmai);
		
		cmb_khuyenmai.getModel().addListDataListener(new ListDataListener() {			
			@Override
			public void contentsChanged(ListDataEvent e) {
				// TODO Auto-generated method stub
				ctkm = ctkm_dao.getCTKM((String)cmb_khuyenmai.getSelectedItem());
				if (ctkm==null) {
					lbl_ptkm.setText("Khuyến mãi 0%");
				}
				else {
					lbl_ptkm.setText("Khuyến mãi "+ctkm.getPhanTramKhuyenMai()+"%");
					
				}
				lbl_kqtongthanhtoan.setText(df.format(tinhTongThanhToan()));
				thayDoiGiaTriTxtTienThua();
			}

			@Override
			public void intervalAdded(ListDataEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void intervalRemoved(ListDataEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		lbl_ptkm = new JLabel("Khuyến mãi 0%");
		lbl_ptkm.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lbl_ptkm.setForeground(new Color(255, 0, 0));
		lbl_ptkm.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_ptkm.setBounds(125, 180, 190, 20);
		pn_thongTinHD.add(lbl_ptkm);
		
		lbl_tongthanhtoan = new JLabel("Tổng thanh toán:");
		lbl_tongthanhtoan.setBounds(15, 210, 100, 20);
		pn_thongTinHD.add(lbl_tongthanhtoan);
		
		lbl_kqtongthanhtoan = new JLabel("0 VND");
		lbl_kqtongthanhtoan.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_kqtongthanhtoan.setBounds(125, 210, 190, 20);
		pn_thongTinHD.add(lbl_kqtongthanhtoan);
		
		lbl_tienkhachtra = new JLabel("Tiền khách trả:");
		lbl_tienkhachtra.setBounds(15, 260, 90, 20);
		pn_thongTinHD.add(lbl_tienkhachtra);
		
		lbl_kqtienkhachtra = new JLabel("VND");
		lbl_kqtienkhachtra.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_kqtienkhachtra.setBounds(290, 260, 25, 20);
		pn_thongTinHD.add(lbl_kqtienkhachtra);
		
		lbl_thienthua = new JLabel("Tiền thừa trả khách:");
		lbl_thienthua.setBounds(15, 310, 120, 20);
		pn_thongTinHD.add(lbl_thienthua);
		
		lbl_kqtienthua = new JLabel("0 VND");
		lbl_kqtienthua.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_kqtienthua.setBounds(125, 310, 190, 20);
		pn_thongTinHD.add(lbl_kqtienthua);
		
		btn_dathang = new JButton("Đặt hàng");
		btn_dathang.setBackground(new Color(255, 127, 80));
		btn_dathang.setBounds(15, 360, 130, 30);
		pn_thongTinHD.add(btn_dathang);
		
		btn_thanhtoan = new JButton("Thanh toán");
		btn_thanhtoan.setBackground(new Color(255, 127, 80));
		btn_thanhtoan.setBounds(185, 360, 130, 30);
		pn_thongTinHD.add(btn_thanhtoan);
		
		txt_tienKhachTra = new JTextField();
		txt_tienKhachTra.setHorizontalAlignment(SwingConstants.RIGHT);
		txt_tienKhachTra.setBounds(125, 257, 165, 25);
		pn_thongTinHD.add(txt_tienKhachTra);
		txt_tienKhachTra.setColumns(10);		
		
		pnl_formNhapThongTinKH = new JPanel();
		pnl_formNhapThongTinKH.setBackground(new Color(255, 255, 255));
		pnl_formNhapThongTinKH.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		pnl_formNhapThongTinKH.setBounds(10, 60, 310, 145);
		pnl_formNhapThongTinKH.setVisible(false);
		pn_thongTinHD.add(pnl_formNhapThongTinKH);
		
		JButton btnThemKHMoi = new JButton("Thêm");
		btnThemKHMoi.setBackground(new Color(0, 128, 0));
		btnThemKHMoi.setBounds(210, 112, 90, 25);
		btnThemKHMoi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String tenKH = txt_kqHotenKHMoi.getText();
				String sdt = txt_kqSdtKHMoi.getText();
				String diaChi = txt_kqDiaChiKHMoi.getText();
				boolean gt = (String)cmb_gioiTinhKHMoi.getSelectedItem()=="Nam"?true:false;
				khachHang = new KhachHang("KH006", tenKH, sdt, gt, diaChi);
				khachHang_dao.themKH(khachHang);
				pnl_formNhapThongTinKH.setVisible(false);
				lbl_tenKH.setVisible(true);
				lbl_kqTenKH.setVisible(true);
				lbl_kqTenKH.setText(khachHang.getTenKH());
				thayDoiViTriComponents(0);
				JOptionPane.showMessageDialog(null, "Thêm khách hàng mới thành công!");
			}
		});
		pnl_formNhapThongTinKH.setLayout(null);
		pnl_formNhapThongTinKH.add(btnThemKHMoi);
		
		JLabel lblNewLabel = new JLabel("Nhập thông tin khách hàng");
		lblNewLabel.setForeground(new Color(65, 105, 225));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(55, 0, 200, 20);
		pnl_formNhapThongTinKH.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Họ tên:");
		lblNewLabel_1.setBounds(10, 25, 100, 20);
		pnl_formNhapThongTinKH.add(lblNewLabel_1);
		
		txt_kqHotenKHMoi = new JTextField();
		txt_kqHotenKHMoi.setBounds(110, 21, 190, 27);
		pnl_formNhapThongTinKH.add(txt_kqHotenKHMoi);
		txt_kqHotenKHMoi.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Số điện thoại:");
		lblNewLabel_1_1.setBounds(10, 55, 100, 20);
		pnl_formNhapThongTinKH.add(lblNewLabel_1_1);
		
		txt_kqSdtKHMoi = new JTextField();
		txt_kqSdtKHMoi.setColumns(10);
		txt_kqSdtKHMoi.setBounds(110, 51, 190, 27);
		pnl_formNhapThongTinKH.add(txt_kqSdtKHMoi);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Địa chỉ:");
		lblNewLabel_1_1_1.setBounds(10, 85, 100, 20);
		pnl_formNhapThongTinKH.add(lblNewLabel_1_1_1);
		
		txt_kqDiaChiKHMoi = new JTextField();
		txt_kqDiaChiKHMoi.setColumns(10);
		txt_kqDiaChiKHMoi.setBounds(110, 81, 190, 27);
		pnl_formNhapThongTinKH.add(txt_kqDiaChiKHMoi);
		
		lblNewLabel_1_1_2 = new JLabel("Giới tính:");
		lblNewLabel_1_1_2.setBounds(10, 115, 100, 20);
		pnl_formNhapThongTinKH.add(lblNewLabel_1_1_2);
		
		String[] strGioiTinh = {"Nam","Nữ"};
		cmb_gioiTinhKHMoi = new JComboBox(strGioiTinh);
		cmb_gioiTinhKHMoi.setBounds(110, 112, 90, 25);
		pnl_formNhapThongTinKH.add(cmb_gioiTinhKHMoi);
		
		txt_tienKhachTra.addCaretListener(new CaretListener() {
			
			@Override
			public void caretUpdate(CaretEvent e) {
				// TODO Auto-generated method stub
				thayDoiGiaTriTxtTienThua();
			}
		});
		
		btn_themKH.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				thayDoiViTriComponents(100);				
				lbl_tenKH.setVisible(false);
				lbl_kqTenKH.setVisible(false);
				pnl_formNhapThongTinKH.setVisible(true);
			}
		});
		//
		JPanel pn_DSGioHang = new JPanel();
		pn_DSGioHang.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true), "Gi\u1ECF h\u00E0ng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(65, 105, 225)));
		pn_DSGioHang.setBackground(new Color(255, 255, 255));
		pn_DSGioHang.setBounds(10, 200, 760, 500);
		add(pn_DSGioHang);
		
		JScrollPane scr_giohang = new JScrollPane();
		scr_giohang.getVerticalScrollBar().setUI(new CustomScrollBarUI());
		scr_giohang.getVerticalScrollBar().setPreferredSize(new Dimension(8,300));
		model = new DefaultTableModel(
	            new Object [][] {
	    
	            },
	            new String [] {
	                "Hình ảnh","Mã", "Tên", "Số lượng", "Giá bán","Thành tiền",""
	            }
	        ) {
	            boolean[] canEdit = new boolean [] {
	                false, false, false, false, false, false, true
	            };

	            public boolean isCellEditable(int rowIndex, int columnIndex) {
	                return canEdit [columnIndex];
	            }
	        };
		tbl_gioHang = new JTable(model);
		tbl_gioHang.setBackground(Color.white);

		
		ImageIcon im = new ImageIcon(ManHinhBanHang.class.getResource("/images/ao.jpg"));
		
		
		tbl_gioHang.setRowHeight(80);
		tbl_gioHang.setSelectionBackground(new java.awt.Color(65, 105, 225));
		
		tbl_gioHang.getColumnModel().getColumn(0).setMinWidth(50);
		tbl_gioHang.getColumnModel().getColumn(1).setMinWidth(30);
		tbl_gioHang.getColumnModel().getColumn(2).setMinWidth(170);
		tbl_gioHang.getColumnModel().getColumn(3).setMinWidth(40);
		tbl_gioHang.getColumnModel().getColumn(4).setMinWidth(80);
		tbl_gioHang.getColumnModel().getColumn(5).setMinWidth(90);
		tbl_gioHang.getColumnModel().getColumn(6).setMaxWidth(90);
		
		DefaultTableCellRenderer head_render = new DefaultTableCellRenderer(); 
	    head_render.setBackground(new Color(135, 205, 230));
	    tbl_gioHang.getTableHeader().setDefaultRenderer(head_render);
		
		scr_giohang.setViewportView(tbl_gioHang);
		scr_giohang.getViewport().setBackground(new Color(255,255,255));
		
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(pn_DSGioHang);
        pn_DSGioHang.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scr_giohang, javax.swing.GroupLayout.DEFAULT_SIZE, 818, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scr_giohang, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
                .addContainerGap())
        );
        
        
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
            	btnSua.setEnabled(true);
            	btnThem.setEnabled(false);
                System.out.println("Edit row : " + row);
                String ma = (String) model.getValueAt(row, 1);
                sp = sanPham_dao.getSanPham(ma);
                
                lbl_kqMa.setText(sp.getMaSP());
				lbl_kqTen.setText(sp.getTenSP());
				lbl_kqChatLieu.setText(sp.getChatLieu().getTenChatLieu());
				lbl_kqMausac.setText(sp.getMauSac().getTenMauSac());
				lbl_kqKichco.setText(sp.getKichCo().getTenKichCo());
				lbl_kqNCC.setText(sp.getNhaCungCap().getTenNCC());
				lbl_kqGiaban.setText(df.format(sp.getGiaBan()));
				lbl_kqSoLuongTon.setText(sp.getSoLuongTon()+"");
				spn_soluong.setModel(new SpinnerNumberModel((int) model.getValueAt(row, 3), 1,sp.getSoLuongTon(), 1));
				rowselect=row;
            }

            @Override
            public void onDelete(int row) {
                if (tbl_gioHang.isEditing()) {
                    tbl_gioHang.getCellEditor().stopCellEditing();
                }
                DefaultTableModel model = (DefaultTableModel) tbl_gioHang.getModel();
                model.removeRow(row);
                lbl_kqTongtien.setText(df.format(tinhTongTienHD()));
                lbl_kqtongthanhtoan.setText(df.format(tinhTongThanhToan()));
                thayDoiGiaTriTxtTienThua();
            }

            @Override
            public void onView(int row) {
                System.out.println("View row : " + row);
            }
        };
        tbl_gioHang.getColumnModel().getColumn(6).setCellRenderer(new TableActionCellRender());
        tbl_gioHang.getColumnModel().getColumn(6).setCellEditor(new TableActionCellEditor(event));
        tbl_gioHang.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
                setHorizontalAlignment(SwingConstants.CENTER);          
                
                Component com = super.getTableCellRendererComponent(tbl_gioHang, o, bln, bln1, i, i1);
                
                if (o == null) {
                	Icon image = new ImageIcon(ManHinhBanHang.class.getResource("/images/null.jpg"));
            		Image ima = ((ImageIcon) image).getImage();
            		Image newimg = ima.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
            		image = new ImageIcon(newimg);
            		JLabel label = new JLabel(image);
                	label.setHorizontalAlignment(JLabel.CENTER);
            		label.setOpaque(true);
                	label.setBackground(com.getBackground());
                	return label;
                } 
                else {
                	if (o instanceof Icon) {
                		Icon image = (ImageIcon)o;
                		Image ima = ((ImageIcon) image).getImage(); 
                		Image newimg = ima.getScaledInstance(70, 70,Image.SCALE_SMOOTH); 
                		image = new ImageIcon(newimg); 
                		JLabel label = new JLabel(image);
                		label.setHorizontalAlignment(JLabel.CENTER);
                		label.setOpaque(true);
                		label.setBackground(com.getBackground());
                		return label;
                	} 
                	else {
                		Icon image = new ImageIcon(ManHinhBanHang.class.getResource("/images/null.jpg"));
                		Image ima = ((ImageIcon) image).getImage();
                		Image newimg = ima.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
                		image = new ImageIcon(newimg);
                		JLabel label = new JLabel(image);
                    	label.setHorizontalAlignment(JLabel.CENTER);
                		label.setOpaque(true);
                    	label.setBackground(com.getBackground());
                    	return label;
                	}
                }
                
            }
        });
        
        btnSua.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				btnSua.setEnabled(false);
				btnThem.setEnabled(false);
				model.setValueAt((int) spn_soluong.getValue(), rowselect, 3);
				int soLuong = (int) model.getValueAt(rowselect, 3);
				double gia = dinhDangTien((String) model.getValueAt(rowselect, 4));
				model.setValueAt(df.format((double)(soLuong*gia)), rowselect, 5);
				lbl_kqTongtien.setText(df.format(tinhTongTienHD()));
				lbl_kqtongthanhtoan.setText(df.format(tinhTongThanhToan()));
				thayDoiGiaTriTxtTienThua();
				
				xoaTrangKqTimKiem();
			}
		});
        
        btnThem.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		// TODO Auto-generated method stub
        		btnSua.setEnabled(false);
				btnThem.setEnabled(false);
				ImageIcon im = new ImageIcon(sp.getHinhAnh());
				Object data[] = {im,sp.getMaSP(),sp.getTenSP(),spn_soluong.getValue(),df.format(sp.getGiaBan()),df.format((int)spn_soluong.getValue()*sp.getGiaBan())};
				model.addRow(data);
				lbl_kqTongtien.setText(df.format(tinhTongTienHD()));
				lbl_kqtongthanhtoan.setText(df.format(tinhTongThanhToan()));
				thayDoiGiaTriTxtTienThua();
				
				xoaTrangKqTimKiem();
        	}
        });
        
    
        
	}
	
	private double tinhTongTienHD() {
		double sum=0;
		for (int i = 0; i < tbl_gioHang.getRowCount(); i++) {
			sum+=dinhDangTien((String)model.getValueAt(i, 5));
		}
		return sum;
	}
	
	public double dinhDangTien(String s) {
		s=s.replace(" VND", "");
		s=s.replace(",", "");
		return Double.parseDouble(s);
	}
	
	private String[] capNhatCmbMaCTKM() {
		String[] s = {};
		List<String> list = new ArrayList<>(Arrays.asList(s));
		list.add("Không có");
		for (ChuongTrinhKhuyenMai ct : ctkm_dao.getDsCTKM()) {
			list.add(ct.getMaKM());
		}
		s = list.toArray(new String[0]);
		return s;
	}
	private double tinhTongThanhToan() {
		if (ctkm==null) return tinhTongTienHD();
		return tinhTongTienHD()*((100-ctkm.getPhanTramKhuyenMai())/100);
	}
	private double tinhTienThua() {
		if (txt_tienKhachTra.getText().isEmpty()) return 0;
		return Double.parseDouble(txt_tienKhachTra.getText())-tinhTongThanhToan();
	}
	private void thayDoiGiaTriTxtTienThua() {
		double tienThua = tinhTienThua();
		if (tienThua<0) lbl_kqtienthua.setForeground(Color.red);
		else lbl_kqtienthua.setForeground(Color.black);
		lbl_kqtienthua.setText(df.format(tienThua));
	}
	private void xoaTrangKqTimKiem() {
		lbl_kqMa.setText("");
		lbl_kqTen.setText("");
		lbl_kqChatLieu.setText("");
		lbl_kqMausac.setText("");
		lbl_kqKichco.setText("");
		lbl_kqNCC.setText("");
		lbl_kqGiaban.setText("");
		lbl_kqSoLuongTon.setText("");
		spn_soluong.setValue(1);
	}
	
	private void thayDoiViTriComponents(int x) {
		lbl_Tongtien.setBounds(15, 110+x, 90, 20);
		lbl_kqTongtien.setBounds(125, 110+x, 190, 20);
		lbl_khuyenmai.setBounds(15, 160+x, 90, 20);
		cmb_khuyenmai.setBounds(125, 160+x, 190, 20);
		lbl_ptkm.setBounds(125, 180, 190+x, 20);
		lbl_tongthanhtoan.setBounds(15, 210+x, 100, 20);
		lbl_kqtongthanhtoan.setBounds(125, 210+x, 190, 20);
		lbl_tienkhachtra.setBounds(15, 260+x, 90, 20);
		lbl_kqtongthanhtoan.setBounds(125, 210+x, 190, 20);
		lbl_tienkhachtra.setBounds(15, 260+x, 90, 20);
		txt_tienKhachTra.setBounds(125, 257+x, 165, 25);
		lbl_kqtienkhachtra.setBounds(290, 260+x, 25, 20);
		lbl_thienthua.setBounds(15, 310+x, 120, 20);
		lbl_kqtienthua.setBounds(125, 310+x, 190, 20);
		btn_dathang.setBounds(15, 360+x, 130, 30);
		btn_thanhtoan.setBounds(185, 360+x, 130, 30);
	}
}
