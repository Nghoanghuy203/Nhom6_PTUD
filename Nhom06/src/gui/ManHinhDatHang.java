package gui;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.awt.Color;
import java.awt.Font;

import javax.swing.border.TitledBorder;
import javax.swing.plaf.TableHeaderUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import custom.RoundedCornerBorder;
import dao.DonDatHang_DAO;
import entities.DonDatHang;
import entities.SanPham;

import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class ManHinhDatHang extends JPanel {

	private JTextField txtSdt;
	private JButton btnSearch;
	
	private JScrollPane scr_Ds;
	private static JTable tbl_Ds;
	private static DefaultTableModel model_ds;
	
	private JLabel lbl_kqMa;
	private JLabel lbl_kqTenNV;
	private JLabel lbl_kqThanhTien;
	private JLabel lbl_kqNgayDat;
	private JLabel lbl_kqTenKH;
	private JLabel lbl_kqTrangThai;
	
	private static DecimalFormat df;
	private static DateTimeFormatter dtf;
	
	private DonDatHang_DAO donDatHang_dao;
	private JTextField txtTenKH;
	private JTextField txtTenNV;
	private JTextField txtNhpMn;
	private JDateChooser dc_ngayLap;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Create the panel.
	 */
	public ManHinhDatHang() {
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		setSize(1120, 730);
		
		try {
			ConnectDB.getInstance().connect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		df = new DecimalFormat("#,### VND");
		dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
		
		donDatHang_dao = new DonDatHang_DAO();
		
		
		JPanel pn_timKiem = new JPanel();
		pn_timKiem.setBackground(new Color(255, 255, 255));
		pn_timKiem.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true), "T\u00ECm ki\u1EBFm \u0111\u01A1n \u0111\u1EB7t h\u00E0ng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(65, 105, 225)));
		pn_timKiem.setBounds(10, 20, 1100, 230);
		add(pn_timKiem);
		pn_timKiem.setLayout(null);
		
		JPanel pnl_tkSdt = new JPanel();
		pnl_tkSdt.setBackground(new Color(255, 255, 255));
		pnl_tkSdt.setBounds(190, 20, 190, 30);
		pnl_tkSdt.setBorder(new RoundedCornerBorder());
		pn_timKiem.add(pnl_tkSdt);
		pnl_tkSdt.setLayout(null);
		
		txtSdt = new JTextField();
		txtSdt.setText("Nhập số điện thoại khách hàng...");
		txtSdt.setForeground(Color.GRAY);
		txtSdt.setEditable(false);
		txtSdt.setBackground(new Color(255, 255, 255));
		txtSdt.setBounds(10, 3, 170, 24);
		txtSdt.setBorder(null);
		txtSdt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				txtSdt.setText("");
				txtSdt.setForeground(Color.BLACK);
				txtSdt.setEditable(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				txtSdt.setText("Nhập mã sản phẩm ...");
				txtSdt.setForeground(Color.GRAY);
			}
		});
		pnl_tkSdt.add(txtSdt);
		txtSdt.setColumns(10);
		
		btnSearch = new JButton("Tìm");
		//btnSearch.setBorder(new RoundedCornerBorder());
		btnSearch.setBackground(new Color(65, 105, 225));
		btnSearch.setIcon(new ImageIcon(ManHinhLapHoaDon.class.getResource("/images/search.png")));
		btnSearch.setBounds(1020, 20, 70, 30);
		pn_timKiem.add(btnSearch);
		
		
		
		JLabel lblKqTimKiem = new JLabel("Kết quả:");
		lblKqTimKiem.setBounds(10, 60, 50, 14);
		//pn_timKiem.add(lblKqTimKiem);
		
		JLabel lbl_thongBaoKq = new JLabel();
		lbl_thongBaoKq.setBounds(60, 60, 100, 14);
		lbl_thongBaoKq.setForeground(Color.red);
		//pn_timKiem.add(lbl_thongBaoKq);
		
		JPanel pn_kqTimKiem = new JPanel();
		pn_kqTimKiem.setLayout(null);
		pn_kqTimKiem.setBackground(new Color(255, 250, 240));
		pn_kqTimKiem.setBounds(10, 79, 1080, 100);
		pn_timKiem.add(pn_kqTimKiem);
		
		JLabel lblMa = new JLabel("Mã đơn:");
		lblMa.setHorizontalAlignment(SwingConstants.LEFT);
		lblMa.setFont(new Font("Arial", Font.PLAIN, 11));
		lblMa.setBounds(10, 15, 50, 20);
		pn_kqTimKiem.add(lblMa);
		
		lbl_kqMa = new JLabel("");
		lbl_kqMa.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(128, 128, 128)));
		lbl_kqMa.setBounds(60, 15, 80, 20);
		pn_kqTimKiem.add(lbl_kqMa);
		
		JLabel lblTenNV = new JLabel("Nhân viên:");
		lblTenNV.setHorizontalAlignment(SwingConstants.LEFT);
		lblTenNV.setFont(new Font("Arial", Font.PLAIN, 11));
		lblTenNV.setBounds(150, 15, 60, 20);
		pn_kqTimKiem.add(lblTenNV);
		
		lbl_kqTenNV = new JLabel("");
		lbl_kqTenNV.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(128, 128, 128)));
		lbl_kqTenNV.setBounds(210, 15, 140, 20);
		pn_kqTimKiem.add(lbl_kqTenNV);
		
		JButton btnTiepTucThanhToan = new JButton("Tiếp tục thanh toán");
		btnTiepTucThanhToan.setFont(new Font("Arial", Font.BOLD, 14));
		btnTiepTucThanhToan.setBackground(new Color(0, 128, 0));
		btnTiepTucThanhToan.setBounds(900, 60, 170, 30);
		pn_kqTimKiem.add(btnTiepTucThanhToan);
		btnTiepTucThanhToan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				//ManHinhChinh.manHinhLapHoaDon = new ManHinhLapHoaDon(true, lbl_kqMa.getText());
				ManHinhLapHoaDon manHinhLapHoaDon = new ManHinhLapHoaDon(true, lbl_kqMa.getText());
				ManHinhChinh.pn_body.removeAll();
				ManHinhChinh.pn_body.add(manHinhLapHoaDon);
				ManHinhChinh.pn_body.validate();
				ManHinhChinh.pn_body.repaint();
			}
			
		});
		
		JLabel lblThanhTien = new JLabel("Thành tiền:");
		lblThanhTien.setHorizontalAlignment(SwingConstants.LEFT);
		lblThanhTien.setFont(new Font("Arial", Font.PLAIN, 11));
		lblThanhTien.setBounds(710, 15, 60, 20);
		pn_kqTimKiem.add(lblThanhTien);
		
		lbl_kqThanhTien = new JLabel("");
		lbl_kqThanhTien.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(128, 128, 128)));
		lbl_kqThanhTien.setBounds(770, 16, 90, 20);
		pn_kqTimKiem.add(lbl_kqThanhTien);
		
		JLabel lblNgayDat = new JLabel("Ngày đặt:");
		lblNgayDat.setHorizontalAlignment(SwingConstants.LEFT);
		lblNgayDat.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNgayDat.setBounds(580, 15, 50, 20);
		pn_kqTimKiem.add(lblNgayDat);
		
		lbl_kqNgayDat = new JLabel("");
		lbl_kqNgayDat.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(128, 128, 128)));
		lbl_kqNgayDat.setBounds(630, 15, 70, 20);
		pn_kqTimKiem.add(lbl_kqNgayDat);
		
		JLabel lblTenKH = new JLabel("Khách hàng:");
		lblTenKH.setHorizontalAlignment(SwingConstants.LEFT);
		lblTenKH.setFont(new Font("Arial", Font.PLAIN, 11));
		lblTenKH.setBounds(360, 15, 80, 20);
		pn_kqTimKiem.add(lblTenKH);
		
		lbl_kqTenKH = new JLabel("");
		lbl_kqTenKH.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(128, 128, 128)));
		lbl_kqTenKH.setBounds(430, 15, 140, 20);
		pn_kqTimKiem.add(lbl_kqTenKH);
		
		JButton btnGiaoHang = new JButton("Giao hàng");
		btnGiaoHang.setBounds(900, 60, 170, 30);
		pn_kqTimKiem.add(btnGiaoHang);
		btnGiaoHang.setFont(new Font("Arial", Font.BOLD, 14));
		btnGiaoHang.setBackground(new Color(244, 164, 96));
		
		lbl_kqTrangThai = new JLabel("");
		lbl_kqTrangThai.setForeground(new Color(255, 0, 0));
		lbl_kqTrangThai.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_kqTrangThai.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
		lbl_kqTrangThai.setBounds(970, 10, 100, 30);
		pn_kqTimKiem.add(lbl_kqTrangThai);
		
		JPanel pnl_tkTenKH = new JPanel();
		pnl_tkTenKH.setLayout(null);
		pnl_tkTenKH.setBorder(new RoundedCornerBorder());
		pnl_tkTenKH.setBackground(Color.WHITE);
		pnl_tkTenKH.setBounds(390, 20, 190, 30);
		pn_timKiem.add(pnl_tkTenKH);
		
		txtTenKH = new JTextField();
		txtTenKH.setText("Nhập tên khách hàng...");
		txtTenKH.setForeground(Color.GRAY);
		txtTenKH.setEditable(false);
		txtTenKH.setColumns(10);
		txtTenKH.setBorder(null);
		txtTenKH.setBackground(Color.WHITE);
		txtTenKH.setBounds(10, 3, 170, 24);
		pnl_tkTenKH.add(txtTenKH);
		
		JPanel pnl_tkTenNV = new JPanel();
		pnl_tkTenNV.setLayout(null);
		pnl_tkTenNV.setBorder(new RoundedCornerBorder());
		pnl_tkTenNV.setBackground(Color.WHITE);
		pnl_tkTenNV.setBounds(590, 20, 190, 30);
		pn_timKiem.add(pnl_tkTenNV);
		
		txtTenNV = new JTextField();
		txtTenNV.setText("Nhập tên nhân viên...");
		txtTenNV.setForeground(Color.GRAY);
		txtTenNV.setEditable(false);
		txtTenNV.setColumns(10);
		txtTenNV.setBorder(null);
		txtTenNV.setBackground(Color.WHITE);
		txtTenNV.setBounds(10, 3, 170, 24);
		pnl_tkTenNV.add(txtTenNV);
		
		JPanel pnl_tkMa = new JPanel();
		pnl_tkMa.setLayout(null);
		pnl_tkMa.setBorder(new RoundedCornerBorder());
		pnl_tkMa.setBackground(Color.WHITE);
		pnl_tkMa.setBounds(10, 20, 170, 30);
		pn_timKiem.add(pnl_tkMa);
		
		txtNhpMn = new JTextField();
		txtNhpMn.setText("Nhập mã đơn...");
		txtNhpMn.setForeground(Color.GRAY);
		txtNhpMn.setEditable(false);
		txtNhpMn.setColumns(10);
		txtNhpMn.setBorder(null);
		txtNhpMn.setBackground(Color.WHITE);
		txtNhpMn.setBounds(10, 3, 150, 24);
		pnl_tkMa.add(txtNhpMn);
		
		JLabel lblNewLabel = new JLabel("Ngày đặt:");
		lblNewLabel.setBounds(790, 20, 60, 30);
		pn_timKiem.add(lblNewLabel);
		
		dc_ngayLap = new JDateChooser();
		dc_ngayLap.setBounds(850, 20, 150, 30);
		dc_ngayLap.setDateFormatString("dd/MM/yyyy");
		pn_timKiem.add(dc_ngayLap);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(245, 222, 179)));
		panel.setBounds(10, 60, 1080, 160);
		//pn_timKiem.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("THÔNG TIN ĐƠN ĐẶT HÀNG");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setForeground(new Color(65, 105, 225));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 0, 1080, 30);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Tên khách hàng:");
		lblNewLabel_2.setBounds(10, 40, 100, 20);
		panel.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		textField.setBackground(new Color(255, 255, 255));
		textField.setBounds(110, 40, 160, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2_1 = new JLabel("Số điện thoại:");
		lblNewLabel_2_1.setBounds(280, 40, 70, 20);
		panel.add(lblNewLabel_2_1);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		textField_1.setBackground(Color.WHITE);
		textField_1.setBounds(350, 40, 130, 20);
		panel.add(textField_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("Địa chỉ:");
		lblNewLabel_2_2.setBounds(10, 70, 90, 20);
		panel.add(lblNewLabel_2_2);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		textField_2.setBackground(Color.WHITE);
		textField_2.setBounds(110, 70, 370, 20);
		panel.add(textField_2);
		
		JLabel lbl_tongthanhtoan = new JLabel("Tổng thanh toán:");
		lbl_tongthanhtoan.setBounds(540, 40, 100, 20);
		panel.add(lbl_tongthanhtoan);
		
		JLabel lbl_kqtongthanhtoan = new JLabel("0 VND");
		lbl_kqtongthanhtoan.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_kqtongthanhtoan.setBounds(650, 40, 190, 20);
		panel.add(lbl_kqtongthanhtoan);
		
		JLabel lbl_tienkhachtra = new JLabel("Tiền khách trả:");
		lbl_tienkhachtra.setBounds(540, 70, 90, 20);
		panel.add(lbl_tienkhachtra);
		
		textField_3 = new JTextField();
		textField_3.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_3.setColumns(10);
		textField_3.setBounds(650, 68, 165, 25);
		panel.add(textField_3);
		
		JLabel lbl_kqtienkhachtra = new JLabel("VND");
		lbl_kqtienkhachtra.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_kqtienkhachtra.setBounds(815, 70, 25, 20);
		panel.add(lbl_kqtienkhachtra);
		
		JLabel lbl_thienthua = new JLabel("Tiền thừa trả khách:");
		lbl_thienthua.setBounds(540, 100, 120, 20);
		panel.add(lbl_thienthua);
		
		JLabel lbl_kqtienthua = new JLabel("0 VND");
		lbl_kqtienthua.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_kqtienthua.setBounds(650, 100, 190, 20);
		panel.add(lbl_kqtienthua);
		
		JButton btn_thanhtoan = new JButton("Hủy đơn");
		btn_thanhtoan.setBackground(new Color(255, 51, 51));
		btn_thanhtoan.setBounds(940, 41, 130, 30);
		panel.add(btn_thanhtoan);
		
		JButton btn_thanhtoan_1 = new JButton("Mua thêm");
		btn_thanhtoan_1.setBackground(new Color(0, 153, 0));
		btn_thanhtoan_1.setBounds(940, 82, 130, 30);
		panel.add(btn_thanhtoan_1);
		
		JButton btn_thanhtoan_1_1 = new JButton("Thanh toán");
		btn_thanhtoan_1_1.setBackground(new Color(255, 127, 80));
		btn_thanhtoan_1_1.setBounds(940, 119, 130, 30);
		panel.add(btn_thanhtoan_1_1);
		
		JPanel pn_dsddh = new JPanel();
		pn_dsddh.setBackground(new Color(255, 255, 255));
		pn_dsddh.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true), "Danh s\u00E1ch \u0111\u01A1n \u0111\u1EB7t h\u00E0ng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(65, 105, 225)));
		pn_dsddh.setBounds(10, 250, 1100, 470);
		add(pn_dsddh);
		pn_dsddh.setLayout(null);
		
		JLabel lblTitleHeader = new JLabel("ĐẶT HÀNG");
		lblTitleHeader.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTitleHeader.setForeground(new Color(65, 105, 225));
		lblTitleHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitleHeader.setBounds(20, 0, 1080, 20);
		add(lblTitleHeader);
		
		JLabel btnExit = new JLabel("");
		btnExit.setIcon(new ImageIcon(ManHinhChinh.class.getResource("/images/close.png")));
		btnExit.setBounds(1100, 0, 20, 20);
		add(btnExit);
		
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(JOptionPane.showConfirmDialog(null, "Nhấn OK để thoát khỏi chương trình.", "Thoat", JOptionPane.WARNING_MESSAGE, JOptionPane.OK_CANCEL_OPTION) == 0) {
					System.exit(0);
				}
			}
		});
		
		model_ds = new DefaultTableModel(
			new Object[][] {
				/*
				{"DH001","Nguyễn Hoàng Huy","Phạm Thanh Hùng","15-10-2023","Chưa thanh toán","2.000.000 VND"},
				{"DH001","Nguyễn Hoàng Huy","Phạm Thanh Hùng","15-10-2023","Chưa thanh toán","2.000.000 VND"}*/
			},
			new String [] {
				"Mã đơn","Nhân viên","Khách hàng","Ngày đặt", "Thành tiền", "Tình trạng thanh toán"	
			}
			
		) {
			boolean[] canEdit = new boolean [] {
				false,false,false,false,false,false	
			};
			
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return canEdit[column];
			}
		};
		
		tbl_Ds = new JTable(model_ds);
		tbl_Ds.setSelectionBackground(new Color(65, 105, 225));
		tbl_Ds.setRowHeight(40);
		tbl_Ds.setGridColor(new Color(0, 0, 0));
		
		DefaultTableCellRenderer head_render = new DefaultTableCellRenderer(); 
	    head_render.setBackground(new Color(135, 205, 230));
	    tbl_Ds.getTableHeader().setDefaultRenderer(head_render);
	    
		scr_Ds = new JScrollPane();
		scr_Ds.setViewportView(tbl_Ds);
		scr_Ds.setBounds(10, 20, 1080, 440);
		scr_Ds.getViewport().setBackground(Color.white);
		pn_dsddh.add(scr_Ds);
		
		
		
		List<DonDatHang> ds = donDatHang_dao.getDsDonDatHang();
		updateDataTableDsDDH(ds);
		
		tbl_Ds.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int selected = tbl_Ds.getSelectedRow();
				lbl_kqMa.setText((String) model_ds.getValueAt(selected, 0));
				lbl_kqTenNV.setText((String) model_ds.getValueAt(selected, 1));
				lbl_kqTenKH.setText((String) model_ds.getValueAt(selected, 2));
				lbl_kqNgayDat.setText((String) model_ds.getValueAt(selected, 3).toString().replaceAll("\\d{2}:\\d{2}:\\d{2} ", ""));
				lbl_kqTrangThai.setText((String) model_ds.getValueAt(selected, 5));
				lbl_kqThanhTien.setText((String) model_ds.getValueAt(selected, 4));
				lbl_thongBaoKq.setText("");
				
				if (((String) model_ds.getValueAt(selected, 5)).equalsIgnoreCase("Đã thanh toán")) {
					btnGiaoHang.setVisible(true);
					btnTiepTucThanhToan.setVisible(false);
					lbl_kqTrangThai.setForeground(Color.green);
				}
				else {
					btnGiaoHang.setVisible(false);
					btnTiepTucThanhToan.setVisible(true);
					lbl_kqTrangThai.setForeground(Color.red);
				}
			}
		});
		
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				DonDatHang ddh = donDatHang_dao.getDDH(txtSdt.getText());
				if (ddh!=null) {
					lbl_kqMa.setText(ddh.getMaDDH());
					lbl_kqTenNV.setText(ddh.getNhanVien().getTenNV());
					lbl_kqTenKH.setText(ddh.getKhachHang().getTenKH());
					String ngay = dtf.format(ddh.getNgayLap()); 
					lbl_kqNgayDat.setText(ngay.toString().replaceAll("\\d{2}:\\d{2}:\\d{2} ", ""));
					lbl_kqTrangThai.setText(ddh.getTinhTrangThanhToan());
					lbl_kqThanhTien.setText(df.format(ddh.getTongTienDDH()));
					lbl_thongBaoKq.setText("");
					
					if (ddh.getTinhTrangThanhToan().equalsIgnoreCase("Đã thanh toán")) {
						btnGiaoHang.setVisible(true);
						btnTiepTucThanhToan.setVisible(false);
						lbl_kqTrangThai.setForeground(Color.green);
					}
					else {
						btnGiaoHang.setVisible(false);
						btnTiepTucThanhToan.setVisible(true);
						lbl_kqTrangThai.setForeground(Color.red);
					}
				}
				else {
					lbl_thongBaoKq.setText("Không tìm thấy!");
					lbl_kqMa.setText("");
					lbl_kqTenNV.setText("");
					lbl_kqTenKH.setText("");
					lbl_kqNgayDat.setText("");
					lbl_kqTrangThai.setText("");
					lbl_kqThanhTien.setText("");
				}
			}
		});
	}
	
	private static void xoaTrangTable(JTable t) {
		DefaultTableModel dm = (DefaultTableModel) t.getModel();
		dm.getDataVector().removeAllElements();
		t.revalidate();
	}
	
	public static void updateDataTableDsDDH(List<DonDatHang> ds) {
		xoaTrangTable(tbl_Ds);
		for (DonDatHang donDatHang : ds) {
			Object data[] = {donDatHang.getMaDDH(),donDatHang.getNhanVien().getTenNV(),donDatHang.getKhachHang().getTenKH(),dtf.format(donDatHang.getNgayLap()).toString(),df.format(donDatHang.getTongTienDDH()),donDatHang.getTinhTrangThanhToan()};
			model_ds.addRow(data);
		}
	}
	public static void resetData() {
		DonDatHang_DAO donDatHang_DAO = new DonDatHang_DAO();
		List<DonDatHang> ds = donDatHang_DAO.getDsDonDatHang();
		ManHinhDatHang.updateDataTableDsDDH(ds);
	}
}
