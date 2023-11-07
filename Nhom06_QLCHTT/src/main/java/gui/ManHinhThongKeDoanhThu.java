package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.plaf.ScrollBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import custom.CustomScrollBarUI;
import dao.HoaDon_DAO;
import entities.HoaDon;
import javax.swing.border.MatteBorder;

@SuppressWarnings("serial")
public class ManHinhThongKeDoanhThu extends JPanel {

	private JScrollPane scr_Ds;
	public static JTable tbl_Ds;
	public static DefaultTableModel model_ds;

	private DecimalFormat df;

	private JDateChooser dc_ngayBatDau;
	private JDateChooser dc_ngayKetThuc;

	private JComboBox<String> cmb_tieuChi;
	private DateTimeFormatter dtf;
	
	private HoaDon_DAO hoaDon_DAO;
	public static List<HoaDon> dsHD;
	
	private BieuDoDoanhThu chart;
	private JPanel pn_bieuDo;
	/**
	 * Create the panel.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ManHinhThongKeDoanhThu() {

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

		df = new DecimalFormat("#,### VND");
		dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");

		hoaDon_DAO = new HoaDon_DAO();
		
		JPanel pn_timKiem = new JPanel();
		pn_timKiem.setBackground(new Color(255, 255, 255));
		pn_timKiem.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true),
				"Ti\u00EAu ch\u00ED th\u1ED1ng k\u00EA", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(65, 105, 225)));
		pn_timKiem.setBounds(10, 50, 1100, 80);
		add(pn_timKiem);
		pn_timKiem.setLayout(null);

		JLabel lbl_thongBaoKq = new JLabel();
		lbl_thongBaoKq.setBounds(60, 60, 100, 14);
		lbl_thongBaoKq.setForeground(Color.red);
		pn_timKiem.add(lbl_thongBaoKq);

		JPanel pn_kqTimKiem = new JPanel();
		pn_kqTimKiem.setLayout(null);
		pn_kqTimKiem.setBackground(new Color(255, 250, 240));
		pn_kqTimKiem.setBounds(10, 20, 1080, 50);
		pn_timKiem.add(pn_kqTimKiem);

		JLabel lblTieuChi = new JLabel("Tiêu chí:");
		lblTieuChi.setHorizontalAlignment(SwingConstants.LEFT);
		lblTieuChi.setFont(new Font("Arial", Font.PLAIN, 11));
		lblTieuChi.setBounds(10, 10, 50, 30);
		pn_kqTimKiem.add(lblTieuChi);

		String[] item = { "Ngày hôm nay", "7 ngày gần nhất", "Tháng", "Năm", "Tùy chỉnh" };
		cmb_tieuChi = new JComboBox(item);
		cmb_tieuChi.setBackground(new Color(245, 222, 179));
		cmb_tieuChi.setBounds(60, 10, 150, 30);
		pn_kqTimKiem.add(cmb_tieuChi);

		JButton btnTimNV = new JButton("Thống kê");
		btnTimNV.setIcon(null);
		btnTimNV.setBounds(970, 9, 100, 30);
		//pn_kqTimKiem.add(btnTimNV);
		btnTimNV.setFont(new Font("Arial", Font.BOLD, 14));
		btnTimNV.setBackground(new Color(147, 112, 219));
		
		JLabel lblThang = new JLabel("Tháng:");
		lblThang.setBounds(230, 10, 80, 30);
		pn_kqTimKiem.add(lblThang);
		lblThang.setVisible(false);
		
		
		String[] itemThang = {"Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"};
		JComboBox<String> cmb_thang = new JComboBox(itemThang);
		cmb_thang.setBackground(new Color(255, 255, 255));
		cmb_thang.setBounds(310, 10, 150, 30);
		pn_kqTimKiem.add(cmb_thang);
		cmb_thang.setVisible(false);
		
		JLabel lblNgayBatDau = new JLabel("Ngày bắt đầu:");
		lblNgayBatDau.setBounds(230, 10, 80, 30);
		pn_kqTimKiem.add(lblNgayBatDau);
		
		dc_ngayBatDau= new JDateChooser();
		dc_ngayBatDau.setBounds(310, 10, 150, 30);
		pn_kqTimKiem.add(dc_ngayBatDau);
		
		JLabel lblNgayKetThuc = new JLabel("Ngày kết thúc:");
		lblNgayKetThuc.setBounds(477, 10, 80, 30);
		pn_kqTimKiem.add(lblNgayKetThuc);
		
		dc_ngayKetThuc = new JDateChooser();
		dc_ngayKetThuc.setBounds(557, 10, 150, 30);
		pn_kqTimKiem.add(dc_ngayKetThuc);

		JPanel pn_dsnv = new JPanel();
		pn_dsnv.setBackground(new Color(255, 255, 255));
		pn_dsnv.setBounds(10, 481, 1100, 240);
		add(pn_dsnv);
		pn_dsnv.setLayout(null);

		model_ds = new DefaultTableModel(new Object[][] {

		}, new String[] { "Mã đơn", "Nhân viên", "Khách hàng", "Ngày lặp", "Khuyến mãi (%)", "Thành tiền" }

		) {
			boolean[] canEdit = new boolean[] { false, false, false, false, false, false };

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
		scr_Ds.setBounds(10, 30, 1080, 200);
		scr_Ds.getViewport().setBackground(Color.white);
		scr_Ds.getVerticalScrollBar().setUI(new CustomScrollBarUI());
		pn_dsnv.add(scr_Ds);

		JLabel lblNewLabel = new JLabel("Danh sách hóa đơn");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel.setForeground(new Color(65, 105, 225));
		lblNewLabel.setBounds(0, 0, 1100, 30);
		pn_dsnv.add(lblNewLabel);
		
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
		
		JLabel lblTmKimNhn = new JLabel("THỐNG KÊ DOANH THU");
		lblTmKimNhn.setHorizontalAlignment(SwingConstants.CENTER);
		lblTmKimNhn.setForeground(new Color(100, 149, 237));
		lblTmKimNhn.setFont(new Font("Arial", Font.BOLD, 20));
		lblTmKimNhn.setBounds(20, 0, 1080, 50);
		add(lblTmKimNhn);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(245, 222, 179)));
		panel.setBounds(10, 130, 1100, 350);
		add(panel);
		panel.setLayout(null);

		JLabel lblBiuDoanh = new JLabel("Biểu đồ doanh thu");
		lblBiuDoanh.setBounds(0, 0, 1100, 30);
		lblBiuDoanh.setHorizontalAlignment(SwingConstants.CENTER);
		lblBiuDoanh.setForeground(new Color(65, 105, 225));
		lblBiuDoanh.setFont(new Font("Arial", Font.BOLD, 16));
		panel.add(lblBiuDoanh);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(ManHinhThongKeDoanhThu.class.getResource("/images/doanhthu.png")));
		lblNewLabel_1.setBounds(10, 30, 1080, 310);
		//panel.add(lblNewLabel_1);
		
		pn_bieuDo = new JPanel();
		pn_bieuDo.setBounds(10, 30, 1080, 310);
		panel.add(pn_bieuDo);
		pn_bieuDo.setLayout(null);
		
		chart = new BieuDoDoanhThu();
		chart.thongKeNgayHomNay();
		pn_bieuDo.add(chart);
		
		
		dsHD = hoaDon_DAO.getDSHDHomNay();
		updateTable(dsHD);
		
		dc_ngayBatDau.setVisible(false);
		dc_ngayKetThuc.setVisible(false);
		lblNgayBatDau.setVisible(false);
		lblNgayKetThuc.setVisible(false);
		cmb_tieuChi.getModel().addListDataListener(new ListDataListener() {

			@Override
			public void intervalRemoved(ListDataEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void intervalAdded(ListDataEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void contentsChanged(ListDataEvent e) {
				// TODO Auto-generated method stub
				if (cmb_tieuChi.getSelectedItem().equals("Tùy chỉnh")) {
					dc_ngayBatDau.setVisible(true);
					dc_ngayKetThuc.setVisible(true);
					lblNgayBatDau.setVisible(true);
					lblNgayKetThuc.setVisible(true);
					lblThang.setVisible(false);
					cmb_thang.setVisible(false);
				}
				else {
					if (cmb_tieuChi.getSelectedItem().equals("Ngày hôm nay")) {
						lblThang.setVisible(false);
						cmb_thang.setVisible(false);
						chart = new BieuDoDoanhThu();
						pn_bieuDo.removeAll();
						pn_bieuDo.add(chart);
						chart.thongKeNgayHomNay();
						dsHD = hoaDon_DAO.getDSHDHomNay();
						updateTable(dsHD);
					}
					if (cmb_tieuChi.getSelectedItem().equals("7 ngày gần nhất")) {
						lblThang.setVisible(false);
						cmb_thang.setVisible(false);
						chart = new BieuDoDoanhThu();
						pn_bieuDo.removeAll();
						pn_bieuDo.add(chart);
						chart.thongKe7NgayGanNhat();
						dsHD= hoaDon_DAO.getDSHD7NgayGanNhat();
						updateTable(dsHD);
					}
					if (cmb_tieuChi.getSelectedItem().equals("Tháng")) {
						lblThang.setVisible(true);
						cmb_thang.setVisible(true);
						int thang = LocalDate.now().getMonthValue();
						cmb_thang.setSelectedIndex(thang-1);
						chart = new BieuDoDoanhThu();
						pn_bieuDo.removeAll();
						pn_bieuDo.add(chart);
						chart.thongKeTheoThang(thang);
						dsHD = hoaDon_DAO.getDSHDTheoThang(thang);
						updateTable(dsHD);
					}
					if (cmb_tieuChi.getSelectedItem().equals("Năm")) {
						lblThang.setVisible(false);
						cmb_thang.setVisible(false);
						chart = new BieuDoDoanhThu();
						pn_bieuDo.removeAll();
						pn_bieuDo.add(chart);
						chart.thongKeTheoNam(2023);
						dsHD = hoaDon_DAO.getDSHDTheoNam(2023);
						updateTable(dsHD);
					}
					dc_ngayBatDau.setVisible(false);
					dc_ngayKetThuc.setVisible(false);
					lblNgayBatDau.setVisible(false);
					lblNgayKetThuc.setVisible(false);
					
				}
			}
		});
		cmb_thang.getModel().addListDataListener(new ListDataListener() {
			
			@Override
			public void intervalRemoved(ListDataEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void intervalAdded(ListDataEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void contentsChanged(ListDataEvent e) {
				// TODO Auto-generated method stub
				if (cmb_thang.getSelectedItem().equals("Tháng 1")) {
					chart = new BieuDoDoanhThu();
					pn_bieuDo.removeAll();
					pn_bieuDo.add(chart);
					chart.thongKeTheoThang(1);
					dsHD = hoaDon_DAO.getDSHDTheoThang(1);
					updateTable(dsHD);
				}
				if (cmb_thang.getSelectedItem().equals("Tháng 2")) {
					chart = new BieuDoDoanhThu();
					pn_bieuDo.removeAll();
					pn_bieuDo.add(chart);
					chart.thongKeTheoThang(2);
					dsHD = hoaDon_DAO.getDSHDTheoThang(2);
					updateTable(dsHD);
				}
				if (cmb_thang.getSelectedItem().equals("Tháng 3")) {
					chart = new BieuDoDoanhThu();
					pn_bieuDo.removeAll();
					pn_bieuDo.add(chart);
					chart.thongKeTheoThang(3);
				}
				if (cmb_thang.getSelectedItem().equals("Tháng 4")) {
					chart = new BieuDoDoanhThu();
					pn_bieuDo.removeAll();
					pn_bieuDo.add(chart);
					chart.thongKeTheoThang(4);
				}
				if (cmb_thang.getSelectedItem().equals("Tháng 5")) {
					chart = new BieuDoDoanhThu();
					pn_bieuDo.removeAll();
					pn_bieuDo.add(chart);
					chart.thongKeTheoThang(5);
				}
				if (cmb_thang.getSelectedItem().equals("Tháng 6")) {
					chart = new BieuDoDoanhThu();
					pn_bieuDo.removeAll();
					pn_bieuDo.add(chart);
					chart.thongKeTheoThang(6);
				}
				if (cmb_thang.getSelectedItem().equals("Tháng 7")) {
					chart = new BieuDoDoanhThu();
					pn_bieuDo.removeAll();
					pn_bieuDo.add(chart);
					chart.thongKeTheoThang(7);
				}
				if (cmb_thang.getSelectedItem().equals("Tháng 8")) {
					chart = new BieuDoDoanhThu();
					pn_bieuDo.removeAll();
					pn_bieuDo.add(chart);
					chart.thongKeTheoThang(8);
				}
				if (cmb_thang.getSelectedItem().equals("Tháng 9")) {
					chart = new BieuDoDoanhThu();
					pn_bieuDo.removeAll();
					pn_bieuDo.add(chart);
					chart.thongKeTheoThang(9);
				}
				if (cmb_thang.getSelectedItem().equals("Tháng 10")) {
					chart = new BieuDoDoanhThu();
					pn_bieuDo.removeAll();
					pn_bieuDo.add(chart);
					chart.thongKeTheoThang(10);
				}
				if (cmb_thang.getSelectedItem().equals("Tháng 11")) {
					chart = new BieuDoDoanhThu();
					pn_bieuDo.removeAll();
					pn_bieuDo.add(chart);
					chart.thongKeTheoThang(11);
					dsHD = hoaDon_DAO.getDSHDTheoThang(11);
					updateTable(dsHD);
				}
				if (cmb_thang.getSelectedItem().equals("Tháng 12")) {
					chart = new BieuDoDoanhThu();
					pn_bieuDo.removeAll();
					pn_bieuDo.add(chart);
					chart.thongKeTheoThang(12);
				}
			}
		});
	}
	
		
	private void xoaTrangTable(JTable t) {
		DefaultTableModel dm = (DefaultTableModel) t.getModel();
		dm.getDataVector().removeAllElements();
	}
	private void updateTable(List<HoaDon> ds) {
		xoaTrangTable(tbl_Ds);
		tbl_Ds.revalidate();
		for (HoaDon hd : ds) {
			Object data[] = {hd.getMaHD(), hd.getNhanVien().getTenNV(), hd.getKhachHang()==null?"Khách lẻ":hd.getKhachHang().getTenKH(), dtf.format(hd.getNgayLap()), hd.getCtKhuyenMai()==null?0:hd.getCtKhuyenMai().getPhanTramKhuyenMai(), df.format(hd.getTongTienHD())};
			model_ds.addRow(data);
		}
	}
}
