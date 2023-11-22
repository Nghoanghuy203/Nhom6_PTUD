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

import javax.swing.DefaultComboBoxModel;
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
import dao.NhanVien_DAO;
import dao.TaiKhoan_DAO;
import entities.DonDatHang;
import entities.NhanVien;
import entities.TaiKhoan;

import entities.ChuongTrinhKhuyenMai;
import dao.ChuongTrinhKhuyenMai_DAO;

import javax.swing.JComboBox;

public class ManHinhCapNhatChuongTrinhKhuyenMai extends JPanel {

	private JTextField txtSearch;
	private JButton btnSearch;

	private JScrollPane scr_Ds;
	private JTable tbl_Ds;
	private DefaultTableModel model_ds;

	private JTextField txt_maKM;

	private DecimalFormat df;
	private DateTimeFormatter dtf;

	private JTextField txt_phanTramKhuyenMai;
	private JTextField txt_trangThai;

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
	public ManHinhCapNhatChuongTrinhKhuyenMai() {

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

		khuyenMai_dao = new ChuongTrinhKhuyenMai_DAO();
		dsKM = khuyenMai_dao.getDsCTKM();

		JPanel pn_thaotac = new JPanel();
		pn_thaotac.setBackground(new Color(255, 255, 255));
		pn_thaotac.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true),
				"Cập nhật thông tin chương trình khuyến mãi", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(65, 105, 225)));
		pn_thaotac.setBounds(10, 50, 1100, 207);
		add(pn_thaotac);
		pn_thaotac.setLayout(null);

		JPanel timKiem = new JPanel();
		timKiem.setBackground(new Color(255, 255, 255));
		timKiem.setBounds(10, 20, 300, 30);
		timKiem.setBorder(new RoundedCornerBorder());
		pn_thaotac.add(timKiem);
		timKiem.setLayout(null);

		txtSearch = new JTextField();
		txtSearch.setText("Nhập mã phiếu khuyến mãi...");
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
				txtSearch.setText("Nhập mã phiếu khuyến mãi..");
				txtSearch.setForeground(Color.GRAY);
			}
		});
		timKiem.add(txtSearch);
		txtSearch.setColumns(10);

		btnSearch = new JButton("Tìm");
		// btnSearch.setBorder(new RoundedCornerBorder());
		btnSearch.setBackground(new Color(65, 105, 225));
		btnSearch.setIcon(new ImageIcon(ManHinhCapNhatChuongTrinhKhuyenMai.class.getResource("/images/search.png")));
		btnSearch.setBounds(220, 3, 70, 24);
		timKiem.add(btnSearch);

		JLabel lblKqTimKiem = new JLabel("Kết quả:");
		lblKqTimKiem.setBounds(10, 60, 50, 14);
		pn_thaotac.add(lblKqTimKiem);

		JLabel lbl_thongBaoKq = new JLabel();
		lbl_thongBaoKq.setBounds(60, 60, 100, 14);
		lbl_thongBaoKq.setForeground(Color.red);
		pn_thaotac.add(lbl_thongBaoKq);

		JPanel pn_kqTimKiem = new JPanel();
		pn_kqTimKiem.setLayout(null);
		pn_kqTimKiem.setBackground(new Color(255, 250, 240));
		pn_kqTimKiem.setBounds(10, 90, 1080, 110);
		pn_thaotac.add(pn_kqTimKiem);

		JLabel lblMaKM = new JLabel("Mã Khuyến Mãi:");
		lblMaKM.setHorizontalAlignment(SwingConstants.LEFT);
		lblMaKM.setFont(new Font("Arial", Font.PLAIN, 11));
		lblMaKM.setBounds(10, 15, 80, 20);
		pn_kqTimKiem.add(lblMaKM);

		txt_maKM = new JTextField();
		txt_maKM.setBackground(new Color(255, 250, 240));
		txt_maKM.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_maKM.setHorizontalAlignment(SwingConstants.LEFT);
		txt_maKM.setBounds(90, 15, 100, 20);
		pn_kqTimKiem.add(txt_maKM);
		txt_maKM.setColumns(10);

		JLabel lblPhanTramKhuyenMai = new JLabel("Phần trăm khuyến mãi:");
		lblPhanTramKhuyenMai.setHorizontalAlignment(SwingConstants.LEFT);
		lblPhanTramKhuyenMai.setFont(new Font("Arial", Font.PLAIN, 11));
		lblPhanTramKhuyenMai.setBounds(230, 15, 121, 20);
		pn_kqTimKiem.add(lblPhanTramKhuyenMai);

		JLabel lblNgayBatDau = new JLabel("Ngày bắt đầu:");
		lblNgayBatDau.setHorizontalAlignment(SwingConstants.LEFT);
		lblNgayBatDau.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNgayBatDau.setBounds(10, 65, 67, 20);
		pn_kqTimKiem.add(lblNgayBatDau);

		JLabel lblNgayKetThuc = new JLabel("Ngày kết thúc:");
		lblNgayKetThuc.setHorizontalAlignment(SwingConstants.LEFT);
		lblNgayKetThuc.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNgayKetThuc.setBounds(300, 65, 88, 20);
		pn_kqTimKiem.add(lblNgayKetThuc);

		JLabel lblTrangThai = new JLabel("Trạng thái:");
		lblTrangThai.setHorizontalAlignment(SwingConstants.LEFT);
		lblTrangThai.setFont(new Font("Arial", Font.PLAIN, 11));
		lblTrangThai.setBounds(550, 15, 60, 20);
		pn_kqTimKiem.add(lblTrangThai);

		JButton btnSua = new JButton("Sửa");
		btnSua.setBounds(857, 59, 100, 30);
		pn_kqTimKiem.add(btnSua);
		btnSua.setFont(new Font("Arial", Font.BOLD, 14));
		btnSua.setBackground(new Color(244, 164, 96));

		txt_phanTramKhuyenMai = new JTextField();
		txt_phanTramKhuyenMai.setBackground(new Color(255, 250, 240));
		txt_phanTramKhuyenMai.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_phanTramKhuyenMai.setHorizontalAlignment(SwingConstants.LEFT);
		txt_phanTramKhuyenMai.setBounds(340, 15, 160, 20);
		pn_kqTimKiem.add(txt_phanTramKhuyenMai);
		txt_phanTramKhuyenMai.setColumns(10);

		txt_trangThai = new JTextField();
		txt_trangThai.setBackground(new Color(255, 250, 240));
		txt_trangThai.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_trangThai.setHorizontalAlignment(SwingConstants.LEFT);
		txt_trangThai.setBounds(605, 15, 160, 20);
		pn_kqTimKiem.add(txt_trangThai);
		txt_trangThai.setColumns(10);
		txt_trangThai.setEditable(false);
		//
		model_date1 = new UtilDateModel();
		Properties p1 = new Properties();
		p1.put("text.today", "Today");
		p1.put("text.month", "Month");
		p1.put("text.year", "Year");
		datePanel1 = new JDatePanelImpl(model_date1, p1);
		model_date1.setDate(2000, 1, 1);
		datePicker1 = new JDatePickerImpl(datePanel1, new custom.DateLabelFormatter());
		datePicker1.setBackground(new Color(255, 255, 255));
		datePicker1.getJFormattedTextField().setBackground(new Color(255, 255, 255));
		datePicker1.setBounds(90, 65, 150, 27);
		datePicker1.getJDateInstantPanel().setShowYearButtons(true);
		datePicker1.getJFormattedTextField().setText("01-01-2000");
		datePicker1.setButtonFocusable(false);
		// datePicker.getModel().setDate(2000, 1, 1);
		pn_kqTimKiem.add(datePicker1);

		model_date2 = new UtilDateModel();
		Properties p2 = new Properties();
		p2.put("text.today", "Today");
		p2.put("text.month", "Month");
		p2.put("text.year", "Year");
		datePanel2 = new JDatePanelImpl(model_date2, p2);
		model_date2.setDate(2000, 1, 1);
		datePicker2 = new JDatePickerImpl(datePanel2, new custom.DateLabelFormatter());
		datePicker2.setBackground(new Color(255, 255, 255));
		datePicker2.getJFormattedTextField().setBackground(new Color(255, 255, 255));
		datePicker2.setBounds(380, 65, 150, 27);
		datePicker2.getJDateInstantPanel().setShowYearButtons(true);
		datePicker2.getJFormattedTextField().setText("01-01-2000");
		datePicker2.setButtonFocusable(false);
		// datePicker.getModel().setDate(2000, 1, 1);
		pn_kqTimKiem.add(datePicker2);

		JButton btnXoaTrang = new JButton("Xóa trắng");
		btnXoaTrang.setFont(new Font("Arial", Font.BOLD, 14));
		btnXoaTrang.setBackground(new Color(255, 0, 0));
		btnXoaTrang.setBounds(747, 59, 100, 30);
		pn_kqTimKiem.add(btnXoaTrang);

		JButton btnThemNV = new JButton("Thêm");
		btnThemNV.setBounds(967, 59, 100, 30);
		pn_kqTimKiem.add(btnThemNV);
		btnThemNV.setFont(new Font("Arial", Font.BOLD, 14));
		btnThemNV.setBackground(new Color(0, 128, 0));

		JPanel pn_dsnv = new JPanel();
		pn_dsnv.setBackground(new Color(255, 255, 255));
		pn_dsnv.setBorder(
				new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true), "Danh sách chương trình khuyến mãi",
						TitledBorder.LEADING, TitledBorder.TOP, null, new Color(65, 105, 225)));
		pn_dsnv.setBounds(10, 280, 1100, 440);
		add(pn_dsnv);
		pn_dsnv.setLayout(null);

		model_ds = new DefaultTableModel(new Object[][] {

		}, new String[] { "Mã chương trình khuyến mãi", "Phần trăm khuyến mãi", "Ngày bắt đầu", "Ngày kết thúc",
				"Trạng thái" }

		) {
			boolean[] canEdit = new boolean[] { false, false, false, false, false };

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
		scr_Ds.setBounds(10, 20, 1080, 410);
		scr_Ds.getViewport().setBackground(Color.white);
		scr_Ds.getVerticalScrollBar().setUI(new CustomScrollBarUI());
		pn_dsnv.add(scr_Ds);

		JLabel lblTenManHinh = new JLabel("CẬP NHẬT CHƯƠNG TRÌNH KHUYẾN MÃI");
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
				if (JOptionPane.showConfirmDialog(null, "Nhấn OK để thoát khỏi chương trình.", "Thoat",
						JOptionPane.WARNING_MESSAGE, JOptionPane.OK_CANCEL_OPTION) == 0) {
					System.exit(0);
				}
			}
		});

		updateTable();

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

		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				String ma = txtSearch.getText();
				ChuongTrinhKhuyenMai ctkm = khuyenMai_dao.getCTKM(ma);
				if (ctkm != null) {
					hienThiThongTinKetQua(ctkm);
				} else {
					lbl_thongBaoKq.setText("Không tìm thấy");
					xoaTrang();
				}

			}
		});

		btnXoaTrang.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				xoaTrang();
			}
		});
		btnThemNV.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (validDataChuongTrinhKhuyenMai()) {
					double phanTramKM = Double.parseDouble(txt_phanTramKhuyenMai.getText());
					String trangThai = "Hết hạn";

					datePicker1.getJFormattedTextField().setText("02-01-2000");

					Date selectedDate1 = (Date) datePicker1.getModel().getValue();
					DateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
					String mydate1 = dateFormat1.format(selectedDate1);
					int nam1 = Integer.parseInt(mydate1.substring(6, 10));
					int thang1 = Integer.parseInt(mydate1.substring(3, 5));
					int ngay1 = Integer.parseInt(mydate1.substring(0, 2));
					LocalDateTime ngayBatDau = LocalDateTime.of(nam1, thang1, ngay1, 0, 0);

					datePicker2.getJFormattedTextField().setText("03-01-2000");

					Date selectedDate2 = (Date) datePicker2.getModel().getValue();
					DateFormat dateFormat2 = new SimpleDateFormat("dd-MM-yyyy");
					String mydate2 = dateFormat2.format(selectedDate2);
					int nam2 = Integer.parseInt(mydate2.substring(6, 10));
					int thang2 = Integer.parseInt(mydate2.substring(3, 5));
					int ngay2 = Integer.parseInt(mydate2.substring(0, 2));
					LocalDateTime ngayKetThuc = LocalDateTime.of(nam2, thang2, ngay2, 0, 0);
					String ma = txt_maKM.getText();

					ChuongTrinhKhuyenMai ctkm = new ChuongTrinhKhuyenMai(ma, phanTramKM, ngayBatDau, ngayKetThuc,
							trangThai);

					if (validDataChuongTrinhKhuyenMai()) {
						if (khuyenMai_dao.themCTKM(ctkm)) {
							String[] item = ManHinhLapHoaDon.capNhatCmbMaCTKM();
							ManHinhLapHoaDon.cmb_khuyenmai.setModel(new DefaultComboBoxModel<>(item));
							updateTable();
							JOptionPane.showMessageDialog(null, "thanh cong");
						} else
							JOptionPane.showMessageDialog(null, "that bai!");
					}
				}
			}
		});

		btnSua.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (validDataChuongTrinhKhuyenMai()) {
					double phanTramKM = Double.parseDouble(txt_phanTramKhuyenMai.getText());
					String trangThai = "Hết hạn";

					datePicker1.getJFormattedTextField().setText("01-01-2000");
					Date selectedDate1 = (Date) datePicker1.getModel().getValue();
					DateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
					String mydate1 = dateFormat1.format(selectedDate1);
					int nam1 = Integer.parseInt(mydate1.substring(6, 10));
					int thang1 = Integer.parseInt(mydate1.substring(3, 5));
					int ngay1 = Integer.parseInt(mydate1.substring(0, 2));
					LocalDateTime ngayBatDau = LocalDateTime.of(nam1, thang1, ngay1, 0, 0);

					datePicker2.getJFormattedTextField().setText("01-01-2000");
					Date selectedDate2 = (Date) datePicker2.getModel().getValue();
					DateFormat dateFormat2 = new SimpleDateFormat("dd-MM-yyyy");
					String mydate2 = dateFormat2.format(selectedDate2);
					int nam2 = Integer.parseInt(mydate2.substring(6, 10));
					int thang2 = Integer.parseInt(mydate2.substring(3, 5));
					int ngay2 = Integer.parseInt(mydate2.substring(0, 2));
					LocalDateTime ngayKetThuc = LocalDateTime.of(nam2, thang2, ngay2, 0, 0);
					String ma = txt_maKM.getText();

					ChuongTrinhKhuyenMai ctkm = new ChuongTrinhKhuyenMai(ma, phanTramKM, ngayBatDau, ngayKetThuc,
							trangThai);

					if (khuyenMai_dao.capNhatCTKM(ma, ctkm)) {
						updateTable();
						JOptionPane.showMessageDialog(null, "thanh cong");
					} else
						JOptionPane.showMessageDialog(null, "that bai!");

				}
			}

		});
	}

	private void xoaTrang() {
		txt_maKM.setText("");
		txt_phanTramKhuyenMai.setText("");
		txt_trangThai.setText("");
		datePicker1.getJFormattedTextField().setText("01-01-2000");
		datePicker2.getJFormattedTextField().setText("01-01-2000");
	}

	private void hienThiThongTinKetQua(ChuongTrinhKhuyenMai ctkm) {
		txt_maKM.setText(ctkm.getMaKM());
		txt_phanTramKhuyenMai.setText(ctkm.getPhanTramKhuyenMai() + "");
		txt_trangThai.setText(ctkm.getTrangThai());
		datePicker1.getJFormattedTextField().setText(dtf.format(ctkm.getNgayBatDau()));
		datePicker2.getJFormattedTextField().setText(dtf.format(ctkm.getNgayKetThuc()));
	}

	private void xoaTable() {
		DefaultTableModel dtm = (DefaultTableModel) tbl_Ds.getModel();
		dtm.getDataVector().removeAllElements();
	}

	private void updateTable() {
		xoaTable();
		dsKM = khuyenMai_dao.getDsCTKM();
		for (ChuongTrinhKhuyenMai chuongTrinhKhuyenMai : dsKM) {
			Object data[] = { chuongTrinhKhuyenMai.getMaKM(), (int) chuongTrinhKhuyenMai.getPhanTramKhuyenMai(),
					dtf.format(chuongTrinhKhuyenMai.getNgayBatDau()), dtf.format(chuongTrinhKhuyenMai.getNgayKetThuc()),
					chuongTrinhKhuyenMai.getTrangThai() };
			model_ds.addRow(data);
		}
	}

	public boolean validDataChuongTrinhKhuyenMai() {
		try {
			int phanTram = Integer.parseInt((String) txt_phanTramKhuyenMai.getText());
			datePicker1.getJFormattedTextField().setText("01-01-2000");
			Date selectedDate1 = (Date) datePicker1.getModel().getValue();
			DateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
			String mydate1 = dateFormat1.format(selectedDate1);
			int nam1 = Integer.parseInt(mydate1.substring(6, 10));
			int thang1 = Integer.parseInt(mydate1.substring(3, 5));
			int ngay1 = Integer.parseInt(mydate1.substring(0, 2));
			LocalDateTime ngayBatDau = LocalDateTime.of(nam1, thang1, ngay1, 0, 0);

			datePicker2.getJFormattedTextField().setText("01-01-2000");
			Date selectedDate2 = (Date) datePicker2.getModel().getValue();
			DateFormat dateFormat2 = new SimpleDateFormat("dd-MM-yyyy");
			String mydate2 = dateFormat2.format(selectedDate2);
			int nam2 = Integer.parseInt(mydate2.substring(6, 10));
			int thang2 = Integer.parseInt(mydate2.substring(3, 5));
			int ngay2 = Integer.parseInt(mydate2.substring(0, 2));
			LocalDateTime ngayKetThuc = LocalDateTime.of(nam2, thang2, ngay2, 0, 0);

			String ma = txt_maKM.getText();
			if (!(ma.length() >= 4 && ma.matches("(^[A-Z0-9]*$)"))) {
				JOptionPane.showMessageDialog(null, "Mã phải lớn hơn hoặc bằng 4 ký và các chữ cái phải in đậm!");
				txt_maKM.requestFocus();
				txt_maKM.selectAll();
				return false;
			}
			if (!(phanTram > 0)) {
				JOptionPane.showMessageDialog(null, "Phần trăm phải là số nguyên dương!");
				txt_phanTramKhuyenMai.requestFocus();
				txt_phanTramKhuyenMai.selectAll();
				return false;
			}
			if (!(ngayBatDau.isBefore(ngayKetThuc))) {
				JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải trước ngày kết thúc!");
				datePicker1.requestFocus();
				return false;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
