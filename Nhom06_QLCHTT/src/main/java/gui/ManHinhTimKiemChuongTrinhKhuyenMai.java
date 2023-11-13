package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import custom.DateLabelFormatter;
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManHinhTimKiemChuongTrinhKhuyenMai extends JPanel {

	private JTextField txtSearch;
	private JButton btnSearch;

	private JScrollPane scr_Ds;
	private JTable tbl_Ds;
	private DefaultTableModel model_ds;

	private JTextField txt_maKM;

	private DecimalFormat df;
	private DateTimeFormatter dtf;

	private JTextField txt_phanTramKhuyenMai;
	private JComboBox cmb_trangThai;
	
	private JLabel lbl_kqTGBD;
	private JLabel lbl_kqTGKT;

	private UtilDateModel model_date1;
	private JDatePanelImpl datePanel1;
	private JDatePickerImpl datePicker1;
	
	private UtilDateModel model_date2;
	private JDatePanelImpl datePanel2;
	private JDatePickerImpl datePicker2;
	
	private ChuongTrinhKhuyenMai_DAO khuyenMai_dao;
	private List<ChuongTrinhKhuyenMai> dsKM;

	
	
	/**
	 * Create the panel.
	 */
	public ManHinhTimKiemChuongTrinhKhuyenMai() {

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
			
//		hoaDon_DAO = new HoaDon_DAO();
//		dsHD = hoaDon_DAO.getDsHoaDon();
		khuyenMai_dao = new ChuongTrinhKhuyenMai_DAO();
		dsKM = khuyenMai_dao.getDsCTKM();
		
		JPanel pn_thaotac = new JPanel();
		pn_thaotac.setBackground(new Color(255, 255, 255));
		pn_thaotac.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true), "Tìm Kiếm Chương Trình Khuyến Mãi", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(65, 105, 225)));
		pn_thaotac.setBounds(10, 50, 1100, 158);
		add(pn_thaotac);
		pn_thaotac.setLayout(null);


		btnSearch = new JButton("Tìm");
		// btnSearch.setBorder(new RoundedCornerBorder());
		btnSearch.setBackground(new Color(65, 105, 225));
		btnSearch.setIcon(new ImageIcon(ManHinhTimKiemChuongTrinhKhuyenMai.class.getResource("/images/search.png")));
		btnSearch.setBounds(804, 88, 100, 30);
		

		JPanel pn_kqTimKiem = new JPanel();
		pn_kqTimKiem.setLayout(null);
		pn_kqTimKiem.setBackground(new Color(255, 250, 240));
		pn_kqTimKiem.setBounds(10, 22, 1080, 122);
		pn_thaotac.add(pn_kqTimKiem);

		pn_kqTimKiem.add(btnSearch);
		
		
		JLabel lblMaKM = new JLabel("Mã Khuyến Mãi:");
		lblMaKM.setHorizontalAlignment(SwingConstants.LEFT);
		lblMaKM.setFont(new Font("Arial", Font.PLAIN, 11));
		lblMaKM.setBounds(10, 20, 80, 20);
		pn_kqTimKiem.add(lblMaKM);

		JPanel pnl_nhapMa = new JPanel();
		pnl_nhapMa.setLayout(null);
		pnl_nhapMa.setBorder(new RoundedCornerBorder());
		pnl_nhapMa.setBackground(Color.WHITE);
		pnl_nhapMa.setBounds(90, 16, 160, 30);
		pn_kqTimKiem.add(pnl_nhapMa);
		txt_maKM = new JTextField();
		txt_maKM.setText("Nhập mã..");
		txt_maKM.setForeground(Color.GRAY);
		txt_maKM.setEditable(false);
		txt_maKM.setColumns(10);
		txt_maKM.setBorder(null);
		txt_maKM.setBackground(Color.WHITE);
		txt_maKM.setBounds(15, 5, 140, 20);
		txt_maKM.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				txt_maKM.setText("");
				txt_maKM.setForeground(Color.BLACK);
				txt_maKM.setEditable(true);
			}
		});
		pnl_nhapMa.add(txt_maKM);

		JLabel lblPhanTramKhuyenMai = new JLabel("Phần trăm khuyến mãi:");
		lblPhanTramKhuyenMai.setHorizontalAlignment(SwingConstants.LEFT);
		lblPhanTramKhuyenMai.setFont(new Font("Arial", Font.PLAIN, 11));
		lblPhanTramKhuyenMai.setBounds(300, 20, 121, 20);
		pn_kqTimKiem.add(lblPhanTramKhuyenMai);

		JLabel lblNgayBatDau = new JLabel("Ngày bắt đầu:");
		lblNgayBatDau.setHorizontalAlignment(SwingConstants.LEFT);
		lblNgayBatDau.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNgayBatDau.setBounds(10, 57, 67, 20);
		pn_kqTimKiem.add(lblNgayBatDau);

		JLabel lblNgayKetThuc = new JLabel("Ngày kết thúc:");
		lblNgayKetThuc.setHorizontalAlignment(SwingConstants.LEFT);
		lblNgayKetThuc.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNgayKetThuc.setBounds(300, 57, 88, 20);
		pn_kqTimKiem.add(lblNgayKetThuc);

		JLabel lblTrangThai = new JLabel("Trạng thái:");
		lblTrangThai.setHorizontalAlignment(SwingConstants.LEFT);
		lblTrangThai.setFont(new Font("Arial", Font.PLAIN, 11));
		lblTrangThai.setBounds(601, 20, 60, 20);
		pn_kqTimKiem.add(lblTrangThai);

		JPanel pnl_khuyenMai = new JPanel();
		pnl_khuyenMai.setLayout(null);
		pnl_khuyenMai.setBorder(new RoundedCornerBorder());
		pnl_khuyenMai.setBackground(Color.WHITE);
		pnl_khuyenMai.setBounds(416, 15, 160, 30);
		pn_kqTimKiem.add(pnl_khuyenMai);
		txt_phanTramKhuyenMai = new JTextField();
		txt_phanTramKhuyenMai.setText("Nhập % khuyến mãi..");
		txt_phanTramKhuyenMai.setForeground(Color.GRAY);
		txt_phanTramKhuyenMai.setEditable(false);
		txt_phanTramKhuyenMai.setColumns(10);
		txt_phanTramKhuyenMai.setBorder(null);
		txt_phanTramKhuyenMai.setBackground(Color.WHITE);
		txt_phanTramKhuyenMai.setBounds(15, 5, 140, 20);
		txt_phanTramKhuyenMai.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				txt_phanTramKhuyenMai.setText("");
				txt_phanTramKhuyenMai.setForeground(Color.BLACK);
				txt_phanTramKhuyenMai.setEditable(true);
			}
		});
		pnl_khuyenMai.add(txt_phanTramKhuyenMai);
		
		
		String[] a = new String[] {"Hết hạn","Đang diễn ra","Chưa diễn ra"};
		cmb_trangThai = new JComboBox(a);
		cmb_trangThai.setBackground(new Color(255, 250, 240));
		cmb_trangThai.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		cmb_trangThai.setBounds(671, 16, 160, 30);
		pn_kqTimKiem.add(cmb_trangThai);
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
		datePicker1.setBounds(90, 57, 150, 30);
		datePicker1.getJDateInstantPanel().setShowYearButtons(true);
		datePicker1.getJFormattedTextField().setText("2000-01-01");
		datePicker1.setButtonFocusable(false);
		//datePicker.getModel().setDate(2000, 1, 1);
		pn_kqTimKiem.add(datePicker1);
		
		model_date2 = new UtilDateModel();
		Properties p2 = new Properties();
		p2.put("text.today", "Today");
		p2.put("text.month", "Month");
		p2.put("text.year", "Year");
		datePanel2 = new JDatePanelImpl(model_date2,p2);
		model_date2.setDate(2000, 1, 1);
		datePicker2 = new JDatePickerImpl(datePanel2, new custom.DateLabelFormatter());
		datePicker2.setBackground(new Color(255, 255, 255));
		datePicker2.getJFormattedTextField().setBackground(new Color(255, 255, 255));
		datePicker2.setBounds(380, 57, 150, 30);
		datePicker2.getJDateInstantPanel().setShowYearButtons(true);
		datePicker2.getJFormattedTextField().setText("2000-01-01");
		datePicker2.setButtonFocusable(false);
		//datePicker.getModel().setDate(2000, 1, 1);
		pn_kqTimKiem.add(datePicker2);
		
			
		JButton btnXoaTrang = new JButton("Xóa trắng");
		btnXoaTrang.setFont(new Font("Arial", Font.BOLD, 14));
		btnXoaTrang.setBackground(new Color(255, 0, 0));
		btnXoaTrang.setBounds(937, 87, 100, 30);
		pn_kqTimKiem.add(btnXoaTrang);
		

		JPanel pn_dsnv = new JPanel();
		pn_dsnv.setBackground(new Color(255, 255, 255));
		pn_dsnv.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true), "Danh sách kết quả", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(65, 105, 225)));
		pn_dsnv.setBounds(10, 230, 1100, 490);
		add(pn_dsnv);
		pn_dsnv.setLayout(null);

		model_ds = new DefaultTableModel(new Object[][] {
				
		}, new String[] { "Mã Chương Trình Khuyến Mãi", "Phần Trăm Khuyến Mãi", "Ngày Bắt Đầu", "Ngày Kết Thúc","Trạng Thái"}

		) {
			boolean[] canEdit = new boolean[] {false , false, false, false, false };

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
		
		JLabel lblTenManHinh = new JLabel("Tìm Kiếm Chương Trình Khuyến Mãi");
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
		updateTable(dsKM);
		

		tbl_Ds.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int selected = tbl_Ds.getSelectedRow();
				String maKM = (String) model_ds.getValueAt(selected, 0);
				ChuongTrinhKhuyenMai ctkm = khuyenMai_dao.getCTKM(maKM);
				hienThiThongTinKetQua(ctkm);
				
			}
		});

		
		btnXoaTrang.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				xoaTrang();
			}
		});
		
		btnSearch.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				String maKM = txt_maKM.getText();
				maKM = maKM.equalsIgnoreCase("Nhập mã..")?"":maKM;
				double phanTram = txt_phanTramKhuyenMai.getText().equalsIgnoreCase("Nhập % khuyến mãi..")?0:Integer.parseInt(txt_phanTramKhuyenMai.getText());
			
				
//				Calendar defaultDate = Calendar.getInstance();
//		        defaultDate.set(2023, Calendar.JANUARY, 1); // Ngày mặc định là 1/1/2023
//		        model_date1.setValue(defaultDate.getTime());
//		        Properties properties = new Properties();
//		        JDatePanelImpl datePanel = new JDatePanelImpl(model_date1, properties);
//		        datePicker1 = new JDatePickerImpl(datePanel, new DateLabelFormatter());
//		        
//				Date selectedDate1 = (Date) datePicker1.getModel().getValue();
//				DateFormat dateFormat1 = new SimpleDateFormat("dd-MM-YYYY");
//				String mydate1 = dateFormat1.format(selectedDate1);
//				int nam1 = Integer.parseInt(mydate1.substring(0, 2));
//				int thang1 = Integer.parseInt(mydate1.substring(3, 5));
//				int ngay1 = Integer.parseInt(mydate1.substring(6, 10));
//				LocalDateTime ngayBatDau = LocalDateTime.of(nam1, thang1, ngay1, 0, 0);
//				
//				Calendar defaultDate2 = Calendar.getInstance();
//		        defaultDate2.set(2023, Calendar.JANUARY, 1); // Ngày mặc định là 1/1/2023
//		        model_date2.setValue(defaultDate2.getTime());
//		        Properties properties2 = new Properties();
//		        JDatePanelImpl datePanel2 = new JDatePanelImpl(model_date2, properties2);
//		        datePicker2 = new JDatePickerImpl(datePanel2, new DateLabelFormatter());
//				
//				Date selectedDate2 = (Date) datePicker2.getModel().getValue();
//				DateFormat dateFormat2 = new SimpleDateFormat("dd-MM-YYYY");
//				String mydate2 = dateFormat2.format(selectedDate2);
//				int nam2 = Integer.parseInt(mydate2.substring(0,2));
//				int thang2 = Integer.parseInt(mydate2.substring(3, 5));
//				int ngay2 = Integer.parseInt(mydate2.substring(6,10));
//				LocalDateTime ngayKetThuc = LocalDateTime.of(nam2, thang2, ngay2, 0, 0);
				
				
				
				
				String trangThai =(String) cmb_trangThai.getSelectedItem();
				dsKM = khuyenMai_dao.timKiem(maKM + "%",phanTram,"2023-01-01%","2023-01-01%",trangThai.equalsIgnoreCase("Chưa diễn ra")?"Chưa kích hoạt":trangThai+"%");
				updateTable(dsKM);
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private void xoaTrang() {
		txt_maKM.setText("Nhập mã..");
		txt_phanTramKhuyenMai.setText("Nhập % khuyến mãi..");
		cmb_trangThai.setSelectedIndex(0);
		datePicker1.getJFormattedTextField().setText("01-01-2000");
		datePicker2.getJFormattedTextField().setText("01-01-2000");
		dsKM = khuyenMai_dao.getDsCTKM();
		updateTable(dsKM);
	}
	
	private void hienThiThongTinKetQua(ChuongTrinhKhuyenMai ctkm) {
		txt_maKM.setText(ctkm.getMaKM());
		txt_phanTramKhuyenMai.setText(ctkm.getPhanTramKhuyenMai()+"");
		cmb_trangThai.setSelectedItem(ctkm.isTrangThai());
		datePicker1.getJFormattedTextField().setText(dtf.format(ctkm.getNgayBatDau()));
		datePicker2.getJFormattedTextField().setText(dtf.format(ctkm.getNgayKetThuc()));
	}
	
	private void xoaTable() {
        DefaultTableModel dtm = (DefaultTableModel) tbl_Ds.getModel();
        dtm.getDataVector().removeAllElements();
    }
	private void updateTable(List<ChuongTrinhKhuyenMai> ds) {
		xoaTable();
		for (ChuongTrinhKhuyenMai km : ds) {
			Object data[] = {km.getMaKM(),km.getPhanTramKhuyenMai(),dtf.format( km.getNgayBatDau()),dtf.format( km.getNgayKetThuc()),km.isTrangThai()};
			model_ds.addRow(data);	
		}
	}
}
