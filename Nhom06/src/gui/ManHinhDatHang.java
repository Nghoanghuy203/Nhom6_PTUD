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

import connectDB.ConnectDB;
import custom.RoundedCornerBorder;
import dao.DonDatHang_DAO;
import entities.DonDatHang;

import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;

public class ManHinhDatHang extends JPanel {

	private JTextField txtSearch;
	private JButton btnSearch;
	
	private JScrollPane scr_Ds;
	private JTable tbl_Ds;
	private DefaultTableModel model_ds;
	
	private JLabel lbl_kqMa;
	private JLabel lbl_kqTenNV;
	private JLabel lbl_kqThanhTien;
	private JLabel lbl_kqNgayDat;
	private JLabel lbl_kqTenKH;
	private JLabel lbl_kqTrangThai;
	
	private DecimalFormat df;
	private DonDatHang_DAO donDatHang_dao;

	/**
	 * Create the panel.
	 */
	public ManHinhDatHang() {
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		setSize(1120, 710);
		
		try {
			ConnectDB.getInstance().connect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	
		df = new DecimalFormat("#,### VND");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("hh:mm:ss dd/MM/yyyy");
		
		donDatHang_dao = new DonDatHang_DAO();
		
		
		JPanel pn_timKiem = new JPanel();
		pn_timKiem.setBackground(new Color(255, 255, 255));
		pn_timKiem.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true), "T\u00ECm ki\u1EBFm \u0111\u01A1n \u0111\u1EB7t h\u00E0ng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(65, 105, 225)));
		pn_timKiem.setBounds(10, 0, 1100, 200);
		add(pn_timKiem);
		pn_timKiem.setLayout(null);
		
		JPanel timKiem = new JPanel();
		timKiem.setBackground(new Color(255, 255, 255));
		timKiem.setBounds(10, 20, 300, 30);
		timKiem.setBorder(new RoundedCornerBorder());
		pn_timKiem.add(timKiem);
		timKiem.setLayout(null);
		
		txtSearch = new JTextField();
		txtSearch.setText("Nhập số điện thoại khách hàng...");
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
				txtSearch.setText("Nhập mã sản phẩm ...");
				txtSearch.setForeground(Color.GRAY);
			}
		});
		timKiem.add(txtSearch);
		txtSearch.setColumns(10);
		
		btnSearch = new JButton("Tìm");
		//btnSearch.setBorder(new RoundedCornerBorder());
		btnSearch.setBackground(new Color(65, 105, 225));
		btnSearch.setIcon(new ImageIcon(ManHinhBanHang.class.getResource("/images/search.png")));
		btnSearch.setBounds(220, 3, 70, 24);
		timKiem.add(btnSearch);
		
		
		
		JLabel lblKqTimKiem = new JLabel("Kết quả:");
		lblKqTimKiem.setBounds(10, 60, 50, 14);
		pn_timKiem.add(lblKqTimKiem);
		
		JLabel lbl_thongBaoKq = new JLabel();
		lbl_thongBaoKq.setBounds(60, 60, 100, 14);
		lbl_thongBaoKq.setForeground(Color.red);
		pn_timKiem.add(lbl_thongBaoKq);
		
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
		
		JPanel pn_dsddh = new JPanel();
		pn_dsddh.setBackground(new Color(255, 255, 255));
		pn_dsddh.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true), "Danh s\u00E1ch \u0111\u01A1n \u0111\u1EB7t h\u00E0ng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(65, 105, 225)));
		pn_dsddh.setBounds(10, 200, 1100, 500);
		add(pn_dsddh);
		pn_dsddh.setLayout(null);
		
		
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
		scr_Ds.setBounds(10, 20, 1080, 470);
		scr_Ds.getViewport().setBackground(Color.white);
		pn_dsddh.add(scr_Ds);
		
		List<DonDatHang> ds = donDatHang_dao.getDsDonDatHang();
		for (DonDatHang donDatHang : ds) {
			Object data[] = {donDatHang.getMaDDH(),donDatHang.getNhanVien().getTenNV(),donDatHang.getKhachHang().getTenKH(),dtf.format(donDatHang.getNgayLap()).toString(),df.format(donDatHang.getTongTienDDH()),donDatHang.getTinhTrangThanhToan()};
			model_ds.addRow(data);
		}
		
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
				DonDatHang ddh = donDatHang_dao.getDDH(txtSearch.getText());
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
}
