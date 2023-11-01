package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import javax.swing.DefaultComboBoxModel;
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
import custom.RoundedCornerBorder;

public class ManHinhTimKiemSanPham extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtSearchSP;
	private JButton btnSearchSP;

	private JScrollPane scr_Ds;
	private JTable tbl_Ds;
	private DefaultTableModel model_ds;

	private JLabel txt_maSP;

	private DecimalFormat df;
	private DateTimeFormatter dtf;

	private JTextField txt_TenSP;
	private JTextField txt_ngaySinh;
	private JTextField txt_GiaNhap;
	private JTextField txt_NCC;
	private JComboBox combo_MauSac;
	private JComboBox combo_LoaiSanPham;

	private UtilDateModel model_date;
	private JDatePanelImpl datePanel;
	private JDatePickerImpl datePicker;
	private JTextField txt_GiaBan;
	private JTextField txt_TonKho;
	private JTextField txt_TinhTrang;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManHinhTimKiemSanPham frame = new ManHinhTimKiemSanPham();
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
	public ManHinhTimKiemSanPham() {
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
		pn_thaotac.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true), "Cập nhật thông tin sản phẩm", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(65, 105, 225)));
		pn_thaotac.setBounds(10, 50, 1100, 238);
		add(pn_thaotac);
		pn_thaotac.setLayout(null);

		JLabel lbl_thongBaoKq = new JLabel();
		lbl_thongBaoKq.setBounds(60, 60, 100, 14);
		lbl_thongBaoKq.setForeground(Color.red);
		pn_thaotac.add(lbl_thongBaoKq);

		JPanel pn_kqTimKiem = new JPanel();
		pn_kqTimKiem.setLayout(null);
		pn_kqTimKiem.setBackground(new Color(255, 250, 240));
		pn_kqTimKiem.setBounds(10, 61, 1080, 166);
		pn_thaotac.add(pn_kqTimKiem);

		JLabel lblMaSP = new JLabel("Mã sản phẩm:");
		lblMaSP.setHorizontalAlignment(SwingConstants.LEFT);
		lblMaSP.setFont(new Font("Arial", Font.PLAIN, 11));
		lblMaSP.setBounds(10, 15, 80, 20);
		pn_kqTimKiem.add(lblMaSP);

		txt_maSP = new JLabel("");
		txt_maSP.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(128, 128, 128)));
		txt_maSP.setBounds(90, 15, 100, 20);
		pn_kqTimKiem.add(txt_maSP);

		JLabel lblTenSP = new JLabel("Tên sản phẩm:");
		lblTenSP.setHorizontalAlignment(SwingConstants.LEFT);
		lblTenSP.setFont(new Font("Arial", Font.PLAIN, 11));
		lblTenSP.setBounds(200, 15, 80, 20);
		pn_kqTimKiem.add(lblTenSP);

		JLabel lblMauSac = new JLabel("Màu sắc:");
		lblMauSac.setHorizontalAlignment(SwingConstants.LEFT);
		lblMauSac.setFont(new Font("Arial", Font.PLAIN, 11));
		lblMauSac.setBounds(448, 100, 50, 20);
		pn_kqTimKiem.add(lblMauSac);

		JLabel lblNgayNhap = new JLabel("Ngày nhập:");
		lblNgayNhap.setHorizontalAlignment(SwingConstants.LEFT);
		lblNgayNhap.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNgayNhap.setBounds(10, 100, 60, 20);
		pn_kqTimKiem.add(lblNgayNhap);

		txt_TenSP = new JTextField();
		txt_TenSP.setBackground(new Color(255, 250, 240));
		txt_TenSP.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_TenSP.setHorizontalAlignment(SwingConstants.LEFT);
		txt_TenSP.setBounds(280, 15, 160, 20);
		pn_kqTimKiem.add(txt_TenSP);
		txt_TenSP.setColumns(10);

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
		datePicker = new JDatePickerImpl(datePanel, new custom.DateLabelFormatter());
		datePicker.setBackground(new Color(255, 255, 255));
		datePicker.getJFormattedTextField().setBackground(new Color(255, 255, 255));
		datePicker.setBounds(75, 100, 150, 27);
		datePicker.getJDateInstantPanel().setShowYearButtons(true);
		datePicker.getJFormattedTextField().setText("01-01-2023");
		datePicker.setButtonFocusable(false);
		//datePicker.getModel().setDate(2000, 1, 1);
		pn_kqTimKiem.add(datePicker);

		combo_MauSac = new JComboBox();
		combo_MauSac.setModel(new DefaultComboBoxModel(new String[] {"Xanh", "Đỏ", "Tím", "Vàng"}));
		combo_MauSac.setBackground(new Color(245, 222, 179));
		combo_MauSac.setBounds(498, 100, 90, 25);
		pn_kqTimKiem.add(combo_MauSac);

		JLabel lblNCC = new JLabel("Nhà cung cấp:");
		lblNCC.setHorizontalAlignment(SwingConstants.LEFT);
		lblNCC.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNCC.setBounds(10, 56, 80, 20);
		pn_kqTimKiem.add(lblNCC);

		txt_NCC = new JTextField();
		txt_NCC.setHorizontalAlignment(SwingConstants.LEFT);
		txt_NCC.setColumns(10);
		txt_NCC.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_NCC.setBackground(new Color(255, 250, 240));
		txt_NCC.setBounds(88, 56, 352, 20);
		pn_kqTimKiem.add(txt_NCC);

		JLabel lblLoaiSanPham = new JLabel("Loại sản phẩm:");
		lblLoaiSanPham.setHorizontalAlignment(SwingConstants.LEFT);
		lblLoaiSanPham.setFont(new Font("Arial", Font.PLAIN, 11));
		lblLoaiSanPham.setBounds(258, 100, 80, 20);
		pn_kqTimKiem.add(lblLoaiSanPham);

		combo_LoaiSanPham = new JComboBox();
		combo_LoaiSanPham.setModel(new DefaultComboBoxModel(new String[] {"Quần dài", "Quần ngắn", "Quần jean", "Áo thun", "Áo sơ mi", "Váy"}));
		combo_LoaiSanPham.setBackground(new Color(245, 222, 179));
		combo_LoaiSanPham.setBounds(338, 100, 90, 25);
		pn_kqTimKiem.add(combo_LoaiSanPham);

		JLabel lblTonKho = new JLabel("Tồn kho:");
		lblTonKho.setHorizontalAlignment(SwingConstants.LEFT);
		lblTonKho.setFont(new Font("Arial", Font.PLAIN, 11));
		lblTonKho.setBounds(447, 57, 44, 20);
		pn_kqTimKiem.add(lblTonKho);

		JLabel lblGiaNhap = new JLabel("Giá nhập:");
		lblGiaNhap.setHorizontalAlignment(SwingConstants.LEFT);
		lblGiaNhap.setFont(new Font("Arial", Font.PLAIN, 11));
		lblGiaNhap.setBounds(448, 15, 60, 20);
		pn_kqTimKiem.add(lblGiaNhap);

		txt_GiaNhap = new JTextField();
		txt_GiaNhap.setHorizontalAlignment(SwingConstants.LEFT);
		txt_GiaNhap.setColumns(10);
		txt_GiaNhap.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_GiaNhap.setBackground(new Color(255, 250, 240));
		txt_GiaNhap.setBounds(498, 16, 120, 20);
		pn_kqTimKiem.add(txt_GiaNhap);

		JLabel lblGiaBan = new JLabel("Giá bán:");
		lblGiaBan.setHorizontalAlignment(SwingConstants.LEFT);
		lblGiaBan.setFont(new Font("Arial", Font.PLAIN, 11));
		lblGiaBan.setBounds(651, 15, 60, 20);
		pn_kqTimKiem.add(lblGiaBan);

		txt_GiaBan = new JTextField();
		txt_GiaBan.setHorizontalAlignment(SwingConstants.LEFT);
		txt_GiaBan.setColumns(10);
		txt_GiaBan.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_GiaBan.setBackground(new Color(255, 250, 240));
		txt_GiaBan.setBounds(712, 15, 129, 20);
		pn_kqTimKiem.add(txt_GiaBan);

		JComboBox combo_ChatLieu = new JComboBox(new Object[]{});
		combo_ChatLieu.setModel(new DefaultComboBoxModel(new String[] {"Vải Canvas", "Vải Kate", "Vải Denim", "Vải Đũi", "Vải Nỉ", "Vải Len", "Vải Voan", "Vải Lanh", "Vải Lụa", "Vải Ren", "Vải Polyester", "Vải Chiffon"}));
		combo_ChatLieu.setBackground(new Color(245, 222, 179));
		combo_ChatLieu.setBounds(673, 100, 90, 25);
		pn_kqTimKiem.add(combo_ChatLieu);

		JLabel lblChatLieu = new JLabel("Chất liệu:");
		lblChatLieu.setHorizontalAlignment(SwingConstants.LEFT);
		lblChatLieu.setFont(new Font("Arial", Font.PLAIN, 11));
		lblChatLieu.setBounds(613, 100, 50, 20);
		pn_kqTimKiem.add(lblChatLieu);

		JComboBox combo_KichCo = new JComboBox(new Object[]{});
		combo_KichCo.setModel(new DefaultComboBoxModel(new String[] {"XL", "L", "M", "S"}));
		combo_KichCo.setBackground(new Color(245, 222, 179));
		combo_KichCo.setBounds(847, 100, 90, 25);
		pn_kqTimKiem.add(combo_KichCo);

		JLabel lblKichCo = new JLabel("Kích cỡ:");
		lblKichCo.setHorizontalAlignment(SwingConstants.LEFT);
		lblKichCo.setFont(new Font("Arial", Font.PLAIN, 11));
		lblKichCo.setBounds(797, 100, 50, 20);
		pn_kqTimKiem.add(lblKichCo);

		txt_TonKho = new JTextField();
		txt_TonKho.setHorizontalAlignment(SwingConstants.LEFT);
		txt_TonKho.setColumns(10);
		txt_TonKho.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_TonKho.setBackground(new Color(255, 250, 240));
		txt_TonKho.setBounds(498, 56, 120, 20);
		pn_kqTimKiem.add(txt_TonKho);

		JLabel lblTinhTrang = new JLabel("Tình trạng:");
		lblTinhTrang.setHorizontalAlignment(SwingConstants.LEFT);
		lblTinhTrang.setFont(new Font("Arial", Font.PLAIN, 11));
		lblTinhTrang.setBounds(651, 56, 60, 20);
		pn_kqTimKiem.add(lblTinhTrang);

		txt_TinhTrang = new JTextField();
		txt_TinhTrang.setHorizontalAlignment(SwingConstants.LEFT);
		txt_TinhTrang.setColumns(10);
		txt_TinhTrang.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_TinhTrang.setBackground(new Color(255, 250, 240));
		txt_TinhTrang.setBounds(712, 56, 129, 20);
		pn_kqTimKiem.add(txt_TinhTrang);

		btnSearchSP = new JButton("Tìm");
		btnSearchSP.setBounds(970, 97, 100, 30);
		pn_kqTimKiem.add(btnSearchSP);
		// btnSearch.setBorder(new RoundedCornerBorder());
		btnSearchSP.setBackground(new Color(255, 255, 255));
		btnSearchSP.setIcon(new ImageIcon(ManHinhChinh.class.getResource("/images/search.png")));

		JButton btnXoaTrangSP = new JButton("Xóa trắng");
		btnXoaTrangSP.setBounds(970, 55, 100, 30);
		pn_kqTimKiem.add(btnXoaTrangSP);
		btnXoaTrangSP.setFont(new Font("Arial", Font.BOLD, 14));
		btnXoaTrangSP.setBackground(new Color(255, 0, 0));

		JPanel timKiem = new JPanel();
		timKiem.setBounds(10, 19, 300, 30);
		pn_thaotac.add(timKiem);
		timKiem.setBackground(new Color(255, 255, 255));
		timKiem.setBorder(new RoundedCornerBorder());
		timKiem.setLayout(null);

		txtSearchSP = new JTextField();
		txtSearchSP.setText("Nhập thông tin...");
		txtSearchSP.setForeground(Color.GRAY);
		txtSearchSP.setEditable(false);
		txtSearchSP.setBackground(new Color(255, 255, 255));
		txtSearchSP.setBounds(10, 3, 200, 24);
		txtSearchSP.setBorder(null);
		txtSearchSP.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				txtSearchSP.setText("");
				txtSearchSP.setForeground(Color.BLACK);
				txtSearchSP.setEditable(true);
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
				txtSearchSP.setText("Nhập mã sản phẩm ...");
				txtSearchSP.setForeground(Color.GRAY);
			}
		});
		timKiem.add(txtSearchSP);
		txtSearchSP.setColumns(10);

		JPanel pn_dsnv = new JPanel();
		pn_dsnv.setBackground(new Color(255, 255, 255));
		pn_dsnv.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true), "Danh sách sản phẩm", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(65, 105, 225)));
		pn_dsnv.setBounds(10, 299, 1100, 420);
		add(pn_dsnv);
		pn_dsnv.setLayout(null);

		model_ds = new DefaultTableModel(new Object[][] {

		}, new String[] { "Mã sản phẩm", "Tên", "Giá nhập", "Giá bán", "Ngày nhập", "Loại", "Màu sắc", "Chất liệu",
				"Kích cỡ", "Nhà cung cấp","Tồn kho","Tình trạng"}

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
		scr_Ds.setBounds(10, 20, 1080, 390);
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
		
		JLabel lblTenManHinh = new JLabel("TÌM THÔNG TIN SẢN PHẨM");
		lblTenManHinh.setFont(new Font("Arial", Font.BOLD, 20));
		lblTenManHinh.setForeground(new Color(100, 149, 237));
		lblTenManHinh.setHorizontalAlignment(SwingConstants.CENTER);
		lblTenManHinh.setBounds(20, 0, 1080, 50);
		add(lblTenManHinh);


	}

}
