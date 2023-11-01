package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import connectDB.ConnectDB;
import custom.CustomScrollBarUI;
import custom.GeneratorID;
import custom.RoundedCornerBorder;
import dao.DonDatHang_DAO;
import dao.HoaDon_DAO;
import dao.NhanVien_DAO;
import dao.TaiKhoan_DAO;
import entities.DonDatHang;
import entities.HoaDon;
import entities.NhanVien;
import entities.TaiKhoan;

import entities.ChuongTrinhKhuyenMai;
import dao.ChuongTrinhKhuyenMai_DAO;

import javax.swing.JComboBox;

public class ManHinhTimKiemHoaDon extends JPanel {

	private JTextField txtSearch;
	private JButton btnSearch;

	private JScrollPane scr_Ds;
	private static JTable tbl_Ds;
	private static DefaultTableModel model_ds;

	private JLabel lbl_kqMa;

	private static DecimalFormat df;
	private static DateTimeFormatter dtf;

	private JComboBox cmb_chuongTrinhKM;
	private JComboBox cmb_nhanVien;
	private JComboBox cmb_khachHang;
	
	private JTextField txt_tongTien;
	private JTextField txt_tienTra;
	

	private UtilDateModel model_date1;
	private JDatePanelImpl datePanel1;
	private JDatePickerImpl datePicker1;
	
	
	private HoaDon_DAO hoaDon_DAO;
	private List<HoaDon> dsHD;

	
	
	/**
	 * Create the panel.
	 */
	public ManHinhTimKiemHoaDon() {

		/**
		 * Create the panel.
		 */

		setBackground(new Color(255, 255, 255));
		setLayout(null);
		setSize(1120, 730);

		try {
			ConnectDB.getInstance().connect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		df = new DecimalFormat("#,###");
		dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			
		hoaDon_DAO = new HoaDon_DAO();
		dsHD = hoaDon_DAO.getDsHoaDon();
		
		JPanel pn_thaotac = new JPanel();
		pn_thaotac.setBackground(new Color(255, 255, 255));
		pn_thaotac.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true), "Tìm Kiếm Hóa Đơn", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(65, 105, 225)));
		pn_thaotac.setBounds(10, 50, 1100, 170);
		add(pn_thaotac);
		pn_thaotac.setLayout(null);


		btnSearch = new JButton("Tìm");
		// btnSearch.setBorder(new RoundedCornerBorder());
		btnSearch.setBackground(new Color(65, 105, 225));
		btnSearch.setIcon(new ImageIcon(ManHinhTimKiemHoaDon.class.getResource("/images/search.png")));
		btnSearch.setBounds(804, 109, 100, 30);
		

		JPanel pn_kqTimKiem = new JPanel();
		pn_kqTimKiem.setLayout(null);
		pn_kqTimKiem.setBackground(new Color(255, 250, 240));
		pn_kqTimKiem.setBounds(10, 22, 1080, 141);
		pn_thaotac.add(pn_kqTimKiem);

		pn_kqTimKiem.add(btnSearch);
		
		JLabel lblKqTimKiem = new JLabel("Kết quả:");
		lblKqTimKiem.setBounds(10, 117, 67, 30);
		pn_kqTimKiem.add(lblKqTimKiem);

		JLabel lbl_thongBaoKq = new JLabel();
		lbl_thongBaoKq.setBounds(62, 126, 100, 14);
		lbl_thongBaoKq.setForeground(Color.red);
		pn_kqTimKiem.add(lbl_thongBaoKq);
		
		JLabel lblmaHD = new JLabel("Mã Hóa Đơn:");
		lblmaHD.setHorizontalAlignment(SwingConstants.LEFT);
		lblmaHD.setFont(new Font("Arial", Font.PLAIN, 11));
		lblmaHD.setBounds(10, 15, 80, 20);
		pn_kqTimKiem.add(lblmaHD);

		lbl_kqMa = new JLabel("");
		lbl_kqMa.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(128, 128, 128)));
		lbl_kqMa.setBounds(90, 15, 100, 20);
		pn_kqTimKiem.add(lbl_kqMa);

		JLabel lblnhanVien = new JLabel("Nhân viên:");
		lblnhanVien.setHorizontalAlignment(SwingConstants.LEFT);
		lblnhanVien.setFont(new Font("Arial", Font.PLAIN, 11));
		lblnhanVien.setBounds(223, 15, 67, 20);
		pn_kqTimKiem.add(lblnhanVien);

		JLabel lblngayLap = new JLabel("Ngày lập:");
		lblngayLap.setHorizontalAlignment(SwingConstants.LEFT);
		lblngayLap.setFont(new Font("Arial", Font.PLAIN, 11));
		lblngayLap.setBounds(10, 60, 67, 20);
		pn_kqTimKiem.add(lblngayLap);
		
		JLabel lbltongTien = new JLabel("Tổng tiền:");
		lbltongTien.setHorizontalAlignment(SwingConstants.LEFT);
		lbltongTien.setFont(new Font("Arial", Font.PLAIN, 11));
		lbltongTien.setBounds(244, 60, 67, 20);
		pn_kqTimKiem.add(lbltongTien);
		
		JLabel lbltienTra = new JLabel("Tiền trả:");
		lbltienTra.setHorizontalAlignment(SwingConstants.LEFT);
		lbltienTra.setFont(new Font("Arial", Font.PLAIN, 11));
		lbltienTra.setBounds(502, 60, 67, 20);
		pn_kqTimKiem.add(lbltienTra);
		
		JLabel lblkhuyenMai = new JLabel("Khuyến mãi:");
		lblkhuyenMai.setHorizontalAlignment(SwingConstants.LEFT);
		lblkhuyenMai.setFont(new Font("Arial", Font.PLAIN, 11));
		lblkhuyenMai.setBounds(647, 15, 67, 20);
		pn_kqTimKiem.add(lblkhuyenMai);

		JLabel lblkhachHang = new JLabel("Khách hàng:");
		lblkhachHang.setHorizontalAlignment(SwingConstants.LEFT);
		lblkhachHang.setFont(new Font("Arial", Font.PLAIN, 11));
		lblkhachHang.setBounds(424, 15, 67, 20);
		pn_kqTimKiem.add(lblkhachHang);

		String[] item_cv = {"Nhân viên","Quản lí"};
		cmb_nhanVien = new JComboBox(item_cv);
		cmb_nhanVien.setBackground(new Color(245, 222, 179));
		cmb_nhanVien.setBounds(305, 13, 90, 25);
		pn_kqTimKiem.add(cmb_nhanVien);
		
		String[] item_cv2 = {"Nhân viên","Quản lí"};
		cmb_khachHang = new JComboBox(item_cv);
		cmb_khachHang.setBackground(new Color(245, 222, 179));
		cmb_khachHang.setBounds(501, 13, 90, 25);
		pn_kqTimKiem.add(cmb_khachHang);
		
		String[] item_cv3 = {"Nhân viên","Quản lí"};
		cmb_chuongTrinhKM = new JComboBox(item_cv);
		cmb_chuongTrinhKM.setBackground(new Color(245, 222, 179));
		cmb_chuongTrinhKM.setBounds(719, 13, 90, 25);
		pn_kqTimKiem.add(cmb_chuongTrinhKM);
		
		txt_tienTra = new JTextField();
		txt_tienTra.setBackground(new Color(255, 250, 240));
		txt_tienTra.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_tienTra.setHorizontalAlignment(SwingConstants.LEFT);
		txt_tienTra.setBounds(546, 61, 160, 20);
		pn_kqTimKiem.add(txt_tienTra);
		txt_tienTra.setColumns(10);
		txt_tienTra.setEditable(false);
		
		txt_tongTien = new JTextField();
		txt_tongTien.setBackground(new Color(255, 250, 240));
		txt_tongTien.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_tongTien.setHorizontalAlignment(SwingConstants.LEFT);
		txt_tongTien.setBounds(305, 61, 160, 20);
		pn_kqTimKiem.add(txt_tongTien);
		txt_tongTien.setColumns(10);
		txt_tongTien.setEditable(false);
		
		
		//
		model_date1 = new UtilDateModel();
		Properties p1 = new Properties();
		p1.put("text.today", "Today");
		p1.put("text.month", "Month");
		p1.put("text.year", "Year");
		datePanel1 = new JDatePanelImpl(model_date1,p1);
		model_date1.setDate(2000, 1, 1);
		datePicker1 = new JDatePickerImpl(datePanel1, new custom.DateLabelFormatter());
		datePicker1.setBackground(new Color(255, 255, 255));
		datePicker1.getJFormattedTextField().setBackground(new Color(255, 255, 255));
		datePicker1.setBounds(62, 58, 150, 27);
		datePicker1.getJDateInstantPanel().setShowYearButtons(true);
		datePicker1.getJFormattedTextField().setText("01-01-2000");
		datePicker1.setButtonFocusable(false);
		//datePicker.getModel().setDate(2000, 1, 1);
		pn_kqTimKiem.add(datePicker1);
		
		
		JButton btnXoaTrang = new JButton("Xóa trắng");
		btnXoaTrang.setFont(new Font("Arial", Font.BOLD, 14));
		btnXoaTrang.setBackground(new Color(255, 0, 0));
		btnXoaTrang.setBounds(937, 109, 100, 30);
		pn_kqTimKiem.add(btnXoaTrang);
		

		JPanel pn_dsnv = new JPanel();
		pn_dsnv.setBackground(new Color(255, 255, 255));
		pn_dsnv.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true), "Danh sách hóa đơn", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(65, 105, 225)));
		pn_dsnv.setBounds(10, 231, 1100, 490);
		add(pn_dsnv);
		pn_dsnv.setLayout(null);

		model_ds = new DefaultTableModel(new Object[][] {
				
		}, new String[] { "Mã hóa đơn", "Ngày lập", "Nhân viên", "Khách hàng", "Khuyến mãi (%)","Tổng tiền","Tiền trả"}

		) {
			boolean[] canEdit = new boolean[] { false, false, false, false, false,false,false };

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
		scr_Ds.setBounds(10, 20, 1080, 460);
		scr_Ds.getViewport().setBackground(Color.white);
		scr_Ds.getVerticalScrollBar().setUI(new CustomScrollBarUI());
		pn_dsnv.add(scr_Ds);
		
		JLabel lblTenManHinh = new JLabel("Tìm Kiếm Hóa Đơn");
		lblTenManHinh.setFont(new Font("Arial", Font.BOLD, 20));
		lblTenManHinh.setForeground(new Color(100, 149, 237));
		lblTenManHinh.setHorizontalAlignment(SwingConstants.CENTER);
		lblTenManHinh.setBounds(20, 0, 1080, 50);
		add(lblTenManHinh);
		
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
		dsHD = hoaDon_DAO.getDsHoaDon();
		updateTable(dsHD);
	}

		
//
//		tbl_Ds.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				// TODO Auto-generated method stub
//				int selected = tbl_Ds.getSelectedRow();
//				String maHD = (String) model_ds.getValueAt(selected, 0);
//				HoaDon hd = hoaDon_DAO.getHD(maHD);
//				
//				hienThiThongTinKetQua(hd);
//				
//			}
//		});
//
//		btnSearch.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				// TODO Auto-generated method stub
//				String ma = txtSearch.getText();
//				HoaDon hd = hoaDon_DAO.getHD(ma);
//				if (hd!=null) {
//					hienThiThongTinKetQua(hd);
//				}
//				else {
//					lbl_thongBaoKq.setText("Không tìm thấy");
//					xoaTrang();
//				}
//				
//			}
//		});
//		
//		btnXoaTrang.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				// TODO Auto-generated method stub
//				xoaTrang();
//			}
//		});
//	}
//	
//	private void xoaTrang() {
//		lbl_kqMa.setText("");
//		txt_tongTien.setText("");
//		txt_tienTra.setText("");
//		datePicker1.getJFormattedTextField().setText("01-01-2000");
//		
//	}
//	
//	private void hienThiThongTinKetQua(HoaDon hd) {
//		
//	}
//	
	private static void xoaTable() {
        DefaultTableModel dtm = (DefaultTableModel) tbl_Ds.getModel();
        dtm.getDataVector().removeAllElements();
    }
	private static void updateTable(List<HoaDon> ds) {
		xoaTable();
		for (HoaDon hoaDon : ds) {
			Object data[] = {hoaDon.getMaHD(),dtf.format(hoaDon.getNgayLap()),hoaDon.getNhanVien().getTenNV(),hoaDon.getKhachHang()==null? "Khách lẻ":hoaDon.getKhachHang().getTenKH(),hoaDon.getCtKhuyenMai()==null?"0":hoaDon.getCtKhuyenMai().getPhanTramKhuyenMai(),df.format(hoaDon.getTongTienHD())+" VND",df.format(hoaDon.getTienKhachTra())+" VND"};
			model_ds.addRow(data);	
		}
	}
	public static void resetData() {
		HoaDon_DAO hoaDon_DAO = new HoaDon_DAO();
		List<HoaDon> ds = hoaDon_DAO.getDsHoaDon();
		ManHinhTimKiemHoaDon.updateTable(ds);
	}
}
//}
