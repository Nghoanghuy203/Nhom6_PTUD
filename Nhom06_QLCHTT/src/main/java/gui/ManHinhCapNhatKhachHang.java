package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
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
import dao.NhanVien_DAO;
import dao.TaiKhoan_DAO;
import entities.NhanVien;
import entities.TaiKhoan;

public class ManHinhCapNhatKhachHang extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtSearchKH;
	private JButton btnSearchKH;

	private JScrollPane scr_Ds;
	private JTable tbl_Ds;
	private DefaultTableModel model_ds;

	private DecimalFormat df;
	private DateTimeFormatter dtf;

	private JTextField txt_hoTenKH;
	private JTextField txt_ngaySinh;
	private JTextField txt_soDienThoai;
	private JTextField txt_diaChi;
	private JComboBox combo_gioiTinh;

	private UtilDateModel model_date;
	private JDatePanelImpl datePanel;
	private JTextField txt_maKH;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManHinhCapNhatKhachHang frame = new ManHinhCapNhatKhachHang();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ManHinhCapNhatKhachHang() {
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
		
		

		JPanel pn_thaotac = new JPanel();
		pn_thaotac.setBackground(new Color(255, 255, 255));
		pn_thaotac.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true), "Cập nhật thông tin khách hàng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(65, 105, 225)));
		pn_thaotac.setBounds(10, 50, 1100, 190);
		add(pn_thaotac);
		pn_thaotac.setLayout(null);

		JPanel timKiem = new JPanel();
		timKiem.setBackground(new Color(255, 255, 255));
		timKiem.setBounds(10, 20, 300, 30);
		timKiem.setBorder(new RoundedCornerBorder());
		pn_thaotac.add(timKiem);
		timKiem.setLayout(null);

		txtSearchKH = new JTextField();
		txtSearchKH.setText("Nhập thông tin...");
		txtSearchKH.setForeground(Color.GRAY);
		txtSearchKH.setEditable(false);
		txtSearchKH.setBackground(new Color(255, 255, 255));
		txtSearchKH.setBounds(10, 3, 188, 24);
		txtSearchKH.setBorder(null);
		txtSearchKH.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				txtSearchKH.setText("");
				txtSearchKH.setForeground(Color.BLACK);
				txtSearchKH.setEditable(true);
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
				txtSearchKH.setText("Nhập mã sản phẩm ...");
				txtSearchKH.setForeground(Color.GRAY);
			}
		});
		timKiem.add(txtSearchKH);
		txtSearchKH.setColumns(10);

		btnSearchKH = new JButton("Tìm");
		// btnSearch.setBorder(new RoundedCornerBorder());
		btnSearchKH.setBackground(new Color(65, 105, 225));
		btnSearchKH.setIcon(new ImageIcon(ManHinhChinh.class.getResource("/images/search.png")));
		btnSearchKH.setBounds(202, 3, 88, 24);
		timKiem.add(btnSearchKH);

		JLabel lbl_thongBaoKq = new JLabel();
		lbl_thongBaoKq.setBounds(60, 60, 100, 14);
		lbl_thongBaoKq.setForeground(Color.red);
		pn_thaotac.add(lbl_thongBaoKq);

		JPanel pn_kqTimKiem = new JPanel();
		pn_kqTimKiem.setLayout(null);
		pn_kqTimKiem.setBackground(new Color(255, 250, 240));
		pn_kqTimKiem.setBounds(10, 74, 1080, 111);
		pn_thaotac.add(pn_kqTimKiem);

		JLabel lblMaKH = new JLabel("Mã khách hàng:");
		lblMaKH.setHorizontalAlignment(SwingConstants.LEFT);
		lblMaKH.setFont(new Font("Arial", Font.PLAIN, 11));
		lblMaKH.setBounds(10, 15, 80, 20);
		pn_kqTimKiem.add(lblMaKH);

		JLabel lblTenKH = new JLabel("Họ tên:");
		lblTenKH.setHorizontalAlignment(SwingConstants.LEFT);
		lblTenKH.setFont(new Font("Arial", Font.PLAIN, 11));
		lblTenKH.setBounds(230, 15, 40, 20);
		pn_kqTimKiem.add(lblTenKH);

		JLabel lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setHorizontalAlignment(SwingConstants.LEFT);
		lblGioiTinh.setFont(new Font("Arial", Font.PLAIN, 11));
		lblGioiTinh.setBounds(460, 58, 50, 20);
		pn_kqTimKiem.add(lblGioiTinh);

		JButton btnSuaKH = new JButton("Sửa");
		btnSuaKH.setBounds(803, 25, 110, 30);
		pn_kqTimKiem.add(btnSuaKH);
		btnSuaKH.setFont(new Font("Arial", Font.BOLD, 14));
		btnSuaKH.setBackground(new Color(244, 164, 96));
		
		txt_hoTenKH = new JTextField();
		txt_hoTenKH.setBackground(new Color(255, 250, 240));
		txt_hoTenKH.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_hoTenKH.setHorizontalAlignment(SwingConstants.LEFT);
		txt_hoTenKH.setBounds(270, 15, 160, 20);
		pn_kqTimKiem.add(txt_hoTenKH);
		txt_hoTenKH.setColumns(10);
		
		txt_ngaySinh = new JTextField();
		txt_ngaySinh.setHorizontalAlignment(SwingConstants.LEFT);
		txt_ngaySinh.setColumns(10);
		txt_ngaySinh.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_ngaySinh.setBackground(new Color(255, 250, 240));
		txt_ngaySinh.setBounds(520, 15, 140, 20);
		//pn_kqTimKiem.add(txt_ngaySinh);
		
		//
		model_date = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		datePanel = new JDatePanelImpl(model_date,p);
		model_date.setDate(2000, 1, 1);
		
		String[] item_gt = {"Nam","Nữ"};
		combo_gioiTinh = new JComboBox(item_gt);
		combo_gioiTinh.setBackground(new Color(245, 222, 179));
		combo_gioiTinh.setBounds(533, 56, 90, 25);
		pn_kqTimKiem.add(combo_gioiTinh);
		
		JLabel lblSoDienThoai = new JLabel("Số điện thoại:");
		lblSoDienThoai.setHorizontalAlignment(SwingConstants.LEFT);
		lblSoDienThoai.setFont(new Font("Arial", Font.PLAIN, 11));
		lblSoDienThoai.setBounds(460, 15, 73, 20);
		pn_kqTimKiem.add(lblSoDienThoai);
		
		txt_soDienThoai = new JTextField();
		txt_soDienThoai.setHorizontalAlignment(SwingConstants.LEFT);
		txt_soDienThoai.setColumns(10);
		txt_soDienThoai.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_soDienThoai.setBackground(new Color(255, 250, 240));
		txt_soDienThoai.setBounds(533, 15, 100, 20);
		pn_kqTimKiem.add(txt_soDienThoai);
		
		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setHorizontalAlignment(SwingConstants.LEFT);
		lblDiaChi.setFont(new Font("Arial", Font.PLAIN, 11));
		lblDiaChi.setBounds(10, 58, 80, 20);
		pn_kqTimKiem.add(lblDiaChi);
		
		txt_diaChi = new JTextField();
		txt_diaChi.setHorizontalAlignment(SwingConstants.LEFT);
		txt_diaChi.setColumns(10);
		txt_diaChi.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_diaChi.setBackground(new Color(255, 250, 240));
		txt_diaChi.setBounds(90, 58, 340, 20);
		pn_kqTimKiem.add(txt_diaChi);
		
		
		JButton btnXoaTrangKH = new JButton("Xóa trắng");
		btnXoaTrangKH.setFont(new Font("Arial", Font.BOLD, 14));
		btnXoaTrangKH.setBackground(new Color(255, 0, 0));
		btnXoaTrangKH.setBounds(941, 25, 110, 30);
		pn_kqTimKiem.add(btnXoaTrangKH);
		
		JButton btnThemKH = new JButton("Thêm");
		btnThemKH.setBounds(672, 25, 110, 30);
		pn_kqTimKiem.add(btnThemKH);
		btnThemKH.setFont(new Font("Arial", Font.BOLD, 14));
		btnThemKH.setBackground(new Color(0, 128, 0));
		
		txt_maKH = new JTextField();
		txt_maKH.setHorizontalAlignment(SwingConstants.LEFT);
		txt_maKH.setColumns(10);
		txt_maKH.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_maKH.setBackground(new Color(255, 250, 240));
		txt_maKH.setBounds(90, 15, 127, 20);
		pn_kqTimKiem.add(txt_maKH);

		JPanel pn_dsnv = new JPanel();
		pn_dsnv.setBackground(new Color(255, 255, 255));
		pn_dsnv.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true), "Danh sách khách hàng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(65, 105, 225)));
		pn_dsnv.setBounds(10, 253, 1100, 470);
		add(pn_dsnv);
		pn_dsnv.setLayout(null);

		model_ds = new DefaultTableModel(new Object[][] {
				
		}, new String[] { "Mã khách hàng", "Họ tên", "Số điện thoại", "Giới tính", "Địa chỉ"}

		) {
			boolean[] canEdit = new boolean[] { false, false, false, false, false, false, false, false };

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
		scr_Ds.getVerticalScrollBar().setUI(new CustomScrollBarUI());
		pn_dsnv.add(scr_Ds);
		
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
		
		JLabel lblTenManHinh = new JLabel("CẬP NHẬT THÔNG TIN KHÁCH HÀNG");
		lblTenManHinh.setFont(new Font("Arial", Font.BOLD, 20));
		lblTenManHinh.setForeground(new Color(100, 149, 237));
		lblTenManHinh.setHorizontalAlignment(SwingConstants.CENTER);
		lblTenManHinh.setBounds(20, 0, 1080, 50);
		add(lblTenManHinh);

	
	}

}
