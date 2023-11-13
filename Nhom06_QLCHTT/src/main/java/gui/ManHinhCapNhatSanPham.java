package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.fasterxml.jackson.databind.ser.std.SerializableSerializer;

import connectDB.ConnectDB;
import custom.CustomScrollBarUI;
import custom.GeneratorID;
import custom.RoundedCornerBorder;
import dao.ChatLieu_DAO;
import dao.KhachHang_DAO;
import dao.KichCo_DAO;
import dao.LoaiSanPham_DAO;
import dao.MauSac_DAO;
import dao.NhaCungCap_DAO;
import dao.SanPham_DAO;
import entities.ChatLieu;
import entities.HoaDon;
import entities.KhachHang;
import entities.KichCo;
import entities.LoaiSanPham;
import entities.MauSac;
import entities.NhaCungCap;
import entities.SanPham;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;

public class ManHinhCapNhatSanPham extends JPanel implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private JButton btnThemSP;
	private JButton btnXoaTrangSP;
	private JButton btnSuaSP;
	private JButton btnThemAnh;
	private JScrollPane scr_Ds;
	private JTable tbl_Ds;
	private DefaultTableModel model_ds;

	private JLabel txt_maSP;

	private DecimalFormat df;
	private DateTimeFormatter dtf;

	private JComboBox cmb_TimLoai;
	private JLabel lblTimLoai;
	private JLabel lblTimMauSac;
	private JComboBox cmb_TimMauSac;
	private JComboBox cmb_TimKichCo;
	private JLabel lblTimChatLieu;
	private JComboBox cmb_TimChatLieu;
	private JButton btnSearch;
	private JTextField txt_TenSP;
	private JTextField txt_ngaySinh;
	private JTextField txt_GiaNhap;
	private JLabel lblHinh;
	private JComboBox cmb_MauSac;
	private JComboBox cmb_LoaiSanPham;
	private JComboBox cmb_KichCo;
	private JComboBox cmb_ChatLieu;
	public static JComboBox cmb_NCC;
	private JComboBox cmb_TinhTrang;
	private UtilDateModel model_date;
	private JDatePanelImpl datePanel;
	private JDatePickerImpl datePicker;
	private JTextField txt_GiaBan;
	private JTextField txt_soLuong;
	private static NhaCungCap_DAO nhaCungCap_DAO = new NhaCungCap_DAO();
	private SanPham_DAO sanPham_DAO = new SanPham_DAO();
	private LoaiSanPham_DAO loaiSanPham_DAO = new LoaiSanPham_DAO();
	private MauSac_DAO mauSac_DAO = new MauSac_DAO();
	private KichCo_DAO kichCo_DAO = new KichCo_DAO();
	private ChatLieu_DAO chatLieu_DAO = new ChatLieu_DAO();
	private ArrayList<SanPham> dsSP = new ArrayList<SanPham>();

	private JFileChooser fileChooser;
	private String filePath;
	private byte[] hinhAnh;
	private JTextField txtTimMaSP;
	private JTextField txtTimTenSP;
	private JLabel lblTimKichCo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManHinhCapNhatSanPham frame = new ManHinhCapNhatSanPham();
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
	public ManHinhCapNhatSanPham() {
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		setSize(1120, 730);

		try {
			ConnectDB.getInstance().connect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		fileChooser = new JFileChooser("./HinhAnhSanPham/");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "png", "gif", "bmp");
		fileChooser.setFileFilter(filter);

		df = new DecimalFormat("#,###");
		dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		JPanel pn_thaotac = new JPanel();
		pn_thaotac.setBackground(new Color(255, 255, 255));
		pn_thaotac.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true),
				"Cập nhật thông tin sản phẩm", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(65, 105, 225)));
		pn_thaotac.setBounds(10, 50, 1100, 300);
		add(pn_thaotac);
		pn_thaotac.setLayout(null);

		JLabel lbl_thongBaoKq = new JLabel();
		lbl_thongBaoKq.setBounds(60, 60, 100, 14);
		lbl_thongBaoKq.setForeground(Color.red);
		pn_thaotac.add(lbl_thongBaoKq);

		JPanel pn_kqTimKiem = new JPanel();
		pn_kqTimKiem.setLayout(null);
		pn_kqTimKiem.setBackground(new Color(255, 250, 240));
		pn_kqTimKiem.setBounds(10, 60, 1080, 230);
		pn_thaotac.add(pn_kqTimKiem);

		JLabel lblMaSP = new JLabel("Mã sản phẩm:");
		lblMaSP.setHorizontalAlignment(SwingConstants.LEFT);
		lblMaSP.setFont(new Font("Arial", Font.PLAIN, 11));
		lblMaSP.setBounds(10, 15, 80, 20);
		pn_kqTimKiem.add(lblMaSP);

		txt_maSP = new JLabel("");
		txt_maSP.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(128, 128, 128)));
		txt_maSP.setBounds(90, 15, 160, 20);
		pn_kqTimKiem.add(txt_maSP);

		JLabel lblTenSP = new JLabel("Tên sản phẩm:");
		lblTenSP.setHorizontalAlignment(SwingConstants.LEFT);
		lblTenSP.setFont(new Font("Arial", Font.PLAIN, 11));
		lblTenSP.setBounds(260, 15, 80, 20);
		pn_kqTimKiem.add(lblTenSP);

		JLabel lblMauSac = new JLabel("Màu sắc:");
		lblMauSac.setHorizontalAlignment(SwingConstants.LEFT);
		lblMauSac.setFont(new Font("Arial", Font.PLAIN, 11));
		lblMauSac.setBounds(430, 85, 60, 20);
		pn_kqTimKiem.add(lblMauSac);

		JLabel lblNgayNhap = new JLabel("Ngày nhập:");
		lblNgayNhap.setHorizontalAlignment(SwingConstants.LEFT);
		lblNgayNhap.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNgayNhap.setBounds(10, 120, 80, 20);
		pn_kqTimKiem.add(lblNgayNhap);

		btnSuaSP = new JButton("Sửa");
		btnSuaSP.setBounds(437, 188, 100, 30);
		pn_kqTimKiem.add(btnSuaSP);
		btnSuaSP.setFont(new Font("Arial", Font.BOLD, 14));
		btnSuaSP.setBackground(new Color(244, 164, 96));

		txt_TenSP = new JTextField();
		txt_TenSP.setBackground(new Color(255, 250, 240));
		txt_TenSP.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_TenSP.setHorizontalAlignment(SwingConstants.LEFT);
		txt_TenSP.setBounds(340, 15, 280, 20);
		pn_kqTimKiem.add(txt_TenSP);
		txt_TenSP.setColumns(10);

		txt_ngaySinh = new JTextField();
		txt_ngaySinh.setHorizontalAlignment(SwingConstants.LEFT);
		txt_ngaySinh.setColumns(10);
		txt_ngaySinh.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_ngaySinh.setBackground(new Color(255, 250, 240));
		txt_ngaySinh.setBounds(520, 15, 140, 20);

		model_date = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		datePanel = new JDatePanelImpl(model_date, p);
		model_date.setValue(new Date(100, 00, 01));
		datePicker = new JDatePickerImpl(datePanel, new custom.DateLabelFormatter());
		datePicker.setBackground(new Color(255, 255, 255));
		datePicker.getJFormattedTextField().setBackground(new Color(255, 255, 255));
		datePicker.setBounds(90, 117, 160, 27);
		datePicker.getJDateInstantPanel().setShowYearButtons(true);
		datePicker.getJFormattedTextField().setText("2023-01-01");
		datePicker.setButtonFocusable(false);
		// datePicker.getModel().setDate(2000, 1, 1);
		pn_kqTimKiem.add(datePicker);

		String[] mauSac = capNhatCmbMauSac();
		cmb_MauSac = new JComboBox();
		cmb_MauSac.setModel(new DefaultComboBoxModel(mauSac));
		cmb_MauSac.setBackground(new Color(245, 222, 179));
		cmb_MauSac.setBounds(490, 84, 130, 22);
		pn_kqTimKiem.add(cmb_MauSac);

		JLabel lblNCC = new JLabel("Nhà cung cấp:");
		lblNCC.setHorizontalAlignment(SwingConstants.LEFT);
		lblNCC.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNCC.setBounds(10, 50, 80, 20);
		pn_kqTimKiem.add(lblNCC);

		JLabel lblLoaiSanPham = new JLabel("Loại sản phẩm:");
		lblLoaiSanPham.setHorizontalAlignment(SwingConstants.LEFT);
		lblLoaiSanPham.setFont(new Font("Arial", Font.PLAIN, 11));
		lblLoaiSanPham.setBounds(630, 85, 80, 20);
		pn_kqTimKiem.add(lblLoaiSanPham);

		String[] loai = capNhatCmbLoai();
		cmb_LoaiSanPham = new JComboBox();
		cmb_LoaiSanPham.setModel(new DefaultComboBoxModel(loai));
		cmb_LoaiSanPham.setBackground(new Color(245, 222, 179));
		cmb_LoaiSanPham.setBounds(720, 84, 100, 22);
		pn_kqTimKiem.add(cmb_LoaiSanPham);

		JLabel lblsoLuong = new JLabel("Số lượng:");
		lblsoLuong.setHorizontalAlignment(SwingConstants.LEFT);
		lblsoLuong.setFont(new Font("Arial", Font.PLAIN, 11));
		lblsoLuong.setBounds(260, 50, 80, 20);
		pn_kqTimKiem.add(lblsoLuong);

		JLabel lblGiaNhap = new JLabel("Giá nhập:");
		lblGiaNhap.setHorizontalAlignment(SwingConstants.LEFT);
		lblGiaNhap.setFont(new Font("Arial", Font.PLAIN, 11));
		lblGiaNhap.setBounds(430, 50, 60, 20);
		pn_kqTimKiem.add(lblGiaNhap);

		txt_GiaNhap = new JTextField();
		txt_GiaNhap.setHorizontalAlignment(SwingConstants.LEFT);
		txt_GiaNhap.setColumns(10);
		txt_GiaNhap.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_GiaNhap.setBackground(new Color(255, 250, 240));
		txt_GiaNhap.setBounds(490, 50, 130, 20);
		pn_kqTimKiem.add(txt_GiaNhap);

		btnXoaTrangSP = new JButton("Xóa trắng");
		btnXoaTrangSP.setFont(new Font("Arial", Font.BOLD, 14));
		btnXoaTrangSP.setBackground(new Color(255, 0, 0));
		btnXoaTrangSP.setBounds(547, 188, 100, 30);
		pn_kqTimKiem.add(btnXoaTrangSP);

		btnThemSP = new JButton("Thêm");
		btnThemSP.setBounds(324, 188, 100, 30);
		pn_kqTimKiem.add(btnThemSP);
		btnThemSP.setFont(new Font("Arial", Font.BOLD, 14));
		btnThemSP.setBackground(new Color(0, 128, 0));

		JLabel lblGiaBan = new JLabel("Giá bán:");
		lblGiaBan.setHorizontalAlignment(SwingConstants.LEFT);
		lblGiaBan.setFont(new Font("Arial", Font.PLAIN, 11));
		lblGiaBan.setBounds(630, 15, 60, 20);
		pn_kqTimKiem.add(lblGiaBan);

		txt_GiaBan = new JTextField();
		txt_GiaBan.setHorizontalAlignment(SwingConstants.LEFT);
		txt_GiaBan.setColumns(10);
		txt_GiaBan.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_GiaBan.setBackground(new Color(255, 250, 240));
		txt_GiaBan.setBounds(690, 15, 130, 20);
		pn_kqTimKiem.add(txt_GiaBan);

		String[] chatlieu = capNhatCmbChatLieu();
		cmb_ChatLieu = new JComboBox();
		cmb_ChatLieu.setModel(new DefaultComboBoxModel(chatlieu));
		cmb_ChatLieu.setBackground(new Color(245, 222, 179));
		cmb_ChatLieu.setBounds(91, 84, 159, 22);
		pn_kqTimKiem.add(cmb_ChatLieu);

		JLabel lblChatLieu = new JLabel("Chất liệu:");
		lblChatLieu.setHorizontalAlignment(SwingConstants.LEFT);
		lblChatLieu.setFont(new Font("Arial", Font.PLAIN, 11));
		lblChatLieu.setBounds(10, 85, 80, 20);
		pn_kqTimKiem.add(lblChatLieu);

		String[] kc = capNhatCmbKichCo();
		cmb_KichCo = new JComboBox();
		cmb_KichCo.setModel(new DefaultComboBoxModel(kc));
		cmb_KichCo.setBackground(new Color(245, 222, 179));
		cmb_KichCo.setBounds(340, 84, 80, 22);
		pn_kqTimKiem.add(cmb_KichCo);

		JLabel lblKichCo = new JLabel("Kích cỡ:");
		lblKichCo.setHorizontalAlignment(SwingConstants.LEFT);
		lblKichCo.setFont(new Font("Arial", Font.PLAIN, 11));
		lblKichCo.setBounds(260, 85, 80, 20);
		pn_kqTimKiem.add(lblKichCo);

		txt_soLuong = new JTextField();
		txt_soLuong.setHorizontalAlignment(SwingConstants.LEFT);
		txt_soLuong.setColumns(10);
		txt_soLuong.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_soLuong.setBackground(new Color(255, 250, 240));
		txt_soLuong.setBounds(340, 51, 80, 20);
		pn_kqTimKiem.add(txt_soLuong);

		JLabel lblTinhTrang = new JLabel("Tình trạng:");
		lblTinhTrang.setHorizontalAlignment(SwingConstants.LEFT);
		lblTinhTrang.setFont(new Font("Arial", Font.PLAIN, 11));
		lblTinhTrang.setBounds(630, 50, 60, 20);
		pn_kqTimKiem.add(lblTinhTrang);

		String[] ncc = capNhatCmbNCC();
		cmb_NCC = new JComboBox();
		cmb_NCC.setBackground(new Color(255, 255, 255));
		cmb_NCC.setModel(new DefaultComboBoxModel(ncc));
		cmb_NCC.setBounds(90, 49, 160, 22);
		pn_kqTimKiem.add(cmb_NCC);

		btnThemAnh = new JButton("Thêm ảnh");
		btnThemAnh.setBounds(930, 190, 100, 20);
		pn_kqTimKiem.add(btnThemAnh);

		// hình
		Icon image = new ImageIcon(ManHinhCapNhatSanPham.class.getResource("/images/null.jpg"));
		Image ima = ((ImageIcon) image).getImage();
		Image newimg = ima.getScaledInstance(140, 140, Image.SCALE_SMOOTH);

		image = new ImageIcon(newimg);
		lblHinh = new JLabel(image);
		lblHinh.setBounds(900, 20, 160, 160);
		pn_kqTimKiem.add(lblHinh);
		lblHinh.setHorizontalAlignment(JLabel.CENTER);
		lblHinh.setOpaque(true);

		cmb_TinhTrang = new JComboBox();
		cmb_TinhTrang.setBackground(new Color(255, 255, 255));
		cmb_TinhTrang
				.setModel(new DefaultComboBoxModel(new String[] { "Đang kinh doanh", "Hết hàng", "Ngừng kinh doanh" }));
		cmb_TinhTrang.setBounds(690, 49, 130, 22);
		pn_kqTimKiem.add(cmb_TinhTrang);

		JLabel lblTimMa = new JLabel("Mã :");
		lblTimMa.setBounds(10, 19, 40, 30);
		pn_thaotac.add(lblTimMa);

		JPanel timKiem_1 = new JPanel();
		timKiem_1.setLayout(null);
		timKiem_1.setBorder(new RoundedCornerBorder());
		timKiem_1.setBackground(Color.WHITE);
		timKiem_1.setBounds(50, 19, 150, 30);
		pn_thaotac.add(timKiem_1);

		txtTimMaSP = new JTextField();
		txtTimMaSP.setForeground(Color.GRAY);
		txtTimMaSP.setEditable(false);
		txtTimMaSP.setColumns(10);
		txtTimMaSP.setBorder(null);
		txtTimMaSP.setBackground(Color.WHITE);
		txtTimMaSP.setBounds(10, 3, 130, 24);
		timKiem_1.add(txtTimMaSP);
		txtTimMaSP.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				txtTimMaSP.setText("");
				txtTimMaSP.setForeground(Color.BLACK);
				txtTimMaSP.setEditable(true);
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

			}
		});
		JLabel lblTimTen = new JLabel("Tên :");
		lblTimTen.setBounds(210, 19, 50, 30);
		pn_thaotac.add(lblTimTen);

		JPanel timKiem = new JPanel();
		timKiem.setLayout(null);
		timKiem.setBorder(new RoundedCornerBorder());
		timKiem.setBackground(Color.WHITE);
		timKiem.setBounds(260, 19, 200, 30);
		pn_thaotac.add(timKiem);

		txtTimTenSP = new JTextField();
		txtTimTenSP.setForeground(Color.GRAY);
		txtTimTenSP.setEditable(false);
		txtTimTenSP.setColumns(10);
		txtTimTenSP.setBorder(null);
		txtTimTenSP.setBackground(Color.WHITE);
		txtTimTenSP.setBounds(10, 3, 180, 24);
		timKiem.add(txtTimTenSP);
		txtTimTenSP.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				txtTimTenSP.setText("");
				txtTimTenSP.setForeground(Color.BLACK);
				txtTimTenSP.setEditable(true);
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

			}
		});
		cmb_TimLoai = new JComboBox();
		String[] timLoai = capNhatCmbTimLoai();
		cmb_TimLoai.setModel(new DefaultComboBoxModel(timLoai));
		cmb_TimLoai.setBackground(Color.WHITE);
		cmb_TimLoai.setBounds(500, 19, 101, 30);
		pn_thaotac.add(cmb_TimLoai);

		lblTimLoai = new JLabel("Loại:");
		lblTimLoai.setBounds(470, 19, 30, 30);
		pn_thaotac.add(lblTimLoai);

		lblTimMauSac = new JLabel("Màu sắc:");
		lblTimMauSac.setBounds(611, 19, 50, 30);
		pn_thaotac.add(lblTimMauSac);

		cmb_TimMauSac = new JComboBox();
		String[] timMauSac = capNhatCmbTimMauSac();
		cmb_TimMauSac.setModel(new DefaultComboBoxModel(timMauSac));
		cmb_TimMauSac.setBackground(Color.WHITE);
		cmb_TimMauSac.setBounds(661, 19, 80, 30);
		pn_thaotac.add(cmb_TimMauSac);

		lblTimKichCo = new JLabel("Kích cỡ:");
		lblTimKichCo.setBounds(751, 19, 50, 30);
		pn_thaotac.add(lblTimKichCo);

		cmb_TimKichCo = new JComboBox();
		String[] timKichCo = capNhatCmbTimKichCo();
		cmb_TimKichCo.setModel(new DefaultComboBoxModel(timKichCo));
		cmb_TimKichCo.setBackground(Color.WHITE);
		cmb_TimKichCo.setBounds(792, 19, 70, 30);
		pn_thaotac.add(cmb_TimKichCo);

		lblTimChatLieu = new JLabel("Chất liệu:");
		lblTimChatLieu.setBounds(867, 19, 60, 30);
		pn_thaotac.add(lblTimChatLieu);

		cmb_TimChatLieu = new JComboBox();
		String[] timChatLieu = capNhatCmbTimChatLieu();
		cmb_TimChatLieu.setModel(new DefaultComboBoxModel(timChatLieu));
		cmb_TimChatLieu.setBackground(Color.WHITE);
		cmb_TimChatLieu.setBounds(916, 19, 100, 30);
		pn_thaotac.add(cmb_TimChatLieu);

		btnSearch = new JButton("Tìm");
		btnSearch.setBackground(new Color(65, 105, 225));
		btnSearch.setBounds(1020, 19, 70, 30);
		pn_thaotac.add(btnSearch);

		JPanel pn_dsnv = new JPanel();
		pn_dsnv.setBackground(new Color(255, 255, 255));
		pn_dsnv.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true), "Danh sách sản phẩm",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(65, 105, 225)));
		pn_dsnv.setBounds(10, 350, 1100, 370);
		add(pn_dsnv);
		pn_dsnv.setLayout(null);

		model_ds = new DefaultTableModel(new Object[][] {

		}, new String[] { "Mã sản phẩm", "Tên", "Giá nhập", "Giá bán", "Ngày nhập", "Loại", "Màu sắc", "Chất liệu",
				"Kích cỡ", "Nhà cung cấp", "Tồn kho", "Tình trạng" }

		) {
			boolean[] canEdit = new boolean[] { false, false, false, false, false, false, false, false, false, false,
					false, false };

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
		scr_Ds.setBounds(10, 20, 1080, 340);
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
				if (JOptionPane.showConfirmDialog(null, "Nhấn OK để thoát khỏi chương trình.", "Thoat",
						JOptionPane.WARNING_MESSAGE, JOptionPane.OK_CANCEL_OPTION) == 0) {
					System.exit(0);
				}
			}
		});

		JLabel lblTenManHinh = new JLabel("CẬP NHẬT THÔNG TIN SẢN PHẨM");
		lblTenManHinh.setFont(new Font("Arial", Font.BOLD, 20));
		lblTenManHinh.setForeground(new Color(100, 149, 237));
		lblTenManHinh.setHorizontalAlignment(SwingConstants.CENTER);
		lblTenManHinh.setBounds(20, 0, 1080, 50);
		add(lblTenManHinh);

		docDuLieu();
		btnThemSP.addActionListener(this);
		btnSuaSP.addActionListener(this);
		btnXoaTrangSP.addActionListener(this);
		btnThemAnh.addActionListener(this);
		btnSearch.addActionListener(this);
		tbl_Ds.addMouseListener(this);
	}

	private void xoaTrangTable(JTable t) {
		DefaultTableModel dm = (DefaultTableModel) t.getModel();
		dm.getDataVector().removeAllElements();
	}

	public void docDuLieu() {
		// TODO Auto-generated method stub
		df = new DecimalFormat("#,###");
		dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		xoaTrangTable(tbl_Ds);
		SanPham_DAO ds = new SanPham_DAO();
		List<SanPham> list = ds.getDsSanPham();
		for (SanPham sp : list) {
			String[] rowData = { sp.getMaSP(), sp.getTenSP(), Double.toString(sp.getGiaNhap()),
					Double.toString(sp.getGiaBan()), dtf.format(sp.getNgayNhap()).substring(0, 10),
					sp.getLoaiSP().getTenLoai(), sp.getMauSac().getTenMauSac(), sp.getChatLieu().getTenChatLieu(),
					sp.getKichCo().getTenKichCo(), sp.getNhaCungCap().getTenNCC(), Integer.toString(sp.getSoLuongTon()),
					sp.getTrangThai() };
			model_ds.addRow(rowData);
		}
		tbl_Ds.setModel(model_ds);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = tbl_Ds.getSelectedRow();
		txt_maSP.setText(tbl_Ds.getValueAt(row, 0).toString());
		txt_TenSP.setText(tbl_Ds.getValueAt(row, 1).toString());
		txt_GiaNhap.setText(tbl_Ds.getValueAt(row, 2).toString());
		txt_GiaBan.setText(tbl_Ds.getValueAt(row, 3).toString());
		String date = tbl_Ds.getValueAt(row, 4).toString().substring(0, 10);
		datePicker.getJFormattedTextField().setText(date);
		SanPham sp = sanPham_DAO.getSanPham(tbl_Ds.getValueAt(row, 0).toString());
		model_date.setValue(new Date(sp.getNgayNhap().getYear() - 1900, sp.getNgayNhap().getMonthValue() - 1,
				sp.getNgayNhap().getDayOfMonth()));
		Icon ic = new ImageIcon(sp.getHinhAnh());
		Image ima = ((ImageIcon) ic).getImage();
		Image newimg = ima.getScaledInstance(160, 160, Image.SCALE_SMOOTH);
		ic = new ImageIcon(newimg);
		lblHinh.setIcon(ic);
		cmb_LoaiSanPham.setSelectedItem(tbl_Ds.getValueAt(row, 5).toString());
		cmb_MauSac.setSelectedItem(tbl_Ds.getValueAt(row, 6).toString());
		cmb_ChatLieu.setSelectedItem(tbl_Ds.getValueAt(row, 7).toString());
		cmb_KichCo.setSelectedItem(tbl_Ds.getValueAt(row, 8).toString());
		cmb_NCC.setSelectedItem(tbl_Ds.getValueAt(row, 9).toString());
		txt_soLuong.setText(tbl_Ds.getValueAt(row, 10).toString());
		cmb_TinhTrang.setSelectedItem(tbl_Ds.getValueAt(row, 11).toString());
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnThemSP)) {
			if (validDataSP()) {
				String tenSP = txt_TenSP.getText();
				double giaBan = Double.parseDouble(txt_GiaBan.getText());
				double giaNhap = Double.parseDouble(txt_GiaNhap.getText());
				String tenNCC = cmb_NCC.getSelectedItem().toString();
				String maNCC = NhaCungCap_DAO.getMaNCC(tenNCC);
				NhaCungCap NCC = new NhaCungCap(maNCC, tenNCC);

				int soLuong = Integer.parseInt(txt_soLuong.getText());
				String tinhTrang = cmb_TinhTrang.getSelectedItem().toString();

				Date selectedDate = (Date) datePicker.getModel().getValue();
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				String mydate = dateFormat.format(selectedDate);
				int nam = Integer.parseInt(mydate.substring(6, 10));
				int thang = Integer.parseInt(mydate.substring(3, 5));
				int ngay = Integer.parseInt(mydate.substring(0, 2));
				LocalDateTime ngayNhap = LocalDateTime.of(nam, thang, ngay, 0, 0);

				String tenloaiSP = cmb_LoaiSanPham.getSelectedItem().toString();
				String maLoai = LoaiSanPham_DAO.getMaLoaiSanPham(tenloaiSP);
				LoaiSanPham loai = new LoaiSanPham(maLoai, tenloaiSP);

				String tenMauSac = cmb_MauSac.getSelectedItem().toString();
				String maMauSac = MauSac_DAO.getMaMauSac(tenMauSac);
				MauSac mauSac = new MauSac(maMauSac, tenMauSac);

				String tenChatLieu = cmb_ChatLieu.getSelectedItem().toString();
				String maChatLieu = ChatLieu_DAO.getMaChatLieu(tenChatLieu);
				ChatLieu chatLieu = new ChatLieu(maChatLieu, tenChatLieu);

				String tenKichCo = cmb_KichCo.getSelectedItem().toString();
				String maKichCo = KichCo_DAO.getMaKichCo(tenKichCo);
				KichCo kichCo = new KichCo(maKichCo, tenKichCo);

				String maSP = GeneratorID.generateIDSanPham(maLoai, maKichCo);

				byte[] hinhAnh = getImageFromLbl(lblHinh);
				SanPham sp = new SanPham(maSP, tenSP, giaNhap, giaBan, loai, hinhAnh, kichCo, chatLieu, mauSac, soLuong,
						ngayNhap, tinhTrang, NCC);
				if (sanPham_DAO.themSanPham(sp)) {

					docDuLieu();
					xoaRong();
					ManHinhTimKiemSanPham.resetData();
					JOptionPane.showMessageDialog(null, "Thêm thành công");
				}
			}
		} else if (o.equals(btnSuaSP)) {
			int row = tbl_Ds.getSelectedRow();
			if (row >= 0) {
				String tenSP = txt_TenSP.getText();

				double giaBan = Double.parseDouble(txt_GiaBan.getText());
				double giaNhap = Double.parseDouble(txt_GiaNhap.getText());

				String tenNCC = cmb_NCC.getSelectedItem().toString();
				String maNCC = NhaCungCap_DAO.getMaNCC(tenNCC);
				NhaCungCap NCC = new NhaCungCap(maNCC, tenNCC);

				int soLuong = Integer.parseInt(txt_soLuong.getText());
				String tinhTrang = cmb_TinhTrang.getSelectedItem().toString();

				Date selectedDate = (Date) datePicker.getModel().getValue();
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				String mydate = dateFormat.format(selectedDate);
				int nam = Integer.parseInt(mydate.substring(6, 10));
				int thang = Integer.parseInt(mydate.substring(3, 5));
				int ngay = Integer.parseInt(mydate.substring(0, 2));
				LocalDateTime ngayNhap = LocalDateTime.of(nam, thang, ngay, 0, 0);

				String tenloaiSP = cmb_LoaiSanPham.getSelectedItem().toString();
				String maLoai = LoaiSanPham_DAO.getMaLoaiSanPham(tenloaiSP);
				LoaiSanPham loai = new LoaiSanPham(maLoai, tenloaiSP);

				String tenMauSac = cmb_MauSac.getSelectedItem().toString();
				String maMauSac = MauSac_DAO.getMaMauSac(tenMauSac);
				MauSac mauSac = new MauSac(maMauSac, tenMauSac);

				String tenChatLieu = cmb_ChatLieu.getSelectedItem().toString();
				String maChatLieu = ChatLieu_DAO.getMaChatLieu(tenChatLieu);
				ChatLieu chatLieu = new ChatLieu(maChatLieu, tenChatLieu);

				String tenKichCo = cmb_KichCo.getSelectedItem().toString();
				String maKichCo = KichCo_DAO.getMaKichCo(tenKichCo);
				KichCo kichCo = new KichCo(maKichCo, tenKichCo);

				byte[] hinhAnh = getImageFromLbl(lblHinh);

				String maSP = txt_maSP.getText();
				SanPham sp = new SanPham(maSP, tenSP, giaNhap, giaBan, loai, hinhAnh, kichCo, chatLieu, mauSac, soLuong,
						ngayNhap, tinhTrang, NCC);
				if (sanPham_DAO.capNhatSanPham(sp)) {
					docDuLieu();
					JOptionPane.showMessageDialog(null, "Cập nhật thành công");
				}

			}
		} else if (o.equals(btnSearch)) {

			String maSP = txtTimMaSP.getText();

			String tenSP = txtTimTenSP.getText();
			String loai = cmb_TimLoai.getSelectedItem().toString();
			loai = loai.equalsIgnoreCase("Tất cả") ? "" : loai;
			String mauSac = cmb_TimMauSac.getSelectedItem().toString();
			mauSac = mauSac.equalsIgnoreCase("Tất cả") ? "" : mauSac;
			String kichCo = cmb_TimKichCo.getSelectedItem().toString();
			kichCo = kichCo.equalsIgnoreCase("Tất cả") ? "" : kichCo;
			String chatLieu = cmb_TimChatLieu.getSelectedItem().toString();
			chatLieu = chatLieu.equalsIgnoreCase("Tất cả") ? "" : chatLieu;

			DefaultTableModel model = (DefaultTableModel) tbl_Ds.getModel();
			TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
			tbl_Ds.setRowSorter(sorter);
			List<RowFilter<Object, Object>> filters = new ArrayList<>();
			filters.add(RowFilter.regexFilter("(?i)" + Pattern.quote(maSP), 0));
			filters.add(RowFilter.regexFilter("(?i)" + Pattern.quote(tenSP), 1));
			filters.add(RowFilter.regexFilter("(?i)" + Pattern.quote(loai), 5));
			filters.add(RowFilter.regexFilter("(?i)" + Pattern.quote(mauSac), 6));
			filters.add(RowFilter.regexFilter("(?i)" + Pattern.quote(chatLieu), 7));
			filters.add(RowFilter.regexFilter("(?i)" + Pattern.quote(kichCo), 8));

			RowFilter<Object, Object> af = RowFilter.andFilter(filters);
			sorter.setRowFilter(af);

		} else if (o.equals(btnXoaTrangSP)) {
			xoaRong();
		} else if (o.equals(btnThemAnh)) {
			hinhAnh = chooseImage(lblHinh);
		}
	}

	private void xoaRong() {
		txtTimMaSP.setText("");
		txtTimMaSP.setForeground(Color.GRAY);
		txtTimTenSP.setText("");
		txtTimTenSP.setForeground(Color.GRAY);
		txt_maSP.setText("");
		txt_TenSP.setText("");
		txt_GiaNhap.setText("");
		txt_GiaBan.setText("");
		txt_soLuong.setText("");
		txt_maSP.setText("");
		Icon image = new ImageIcon(ManHinhCapNhatSanPham.class.getResource("/images/null.jpg"));
		Image ima = ((ImageIcon) image).getImage();
		Image newimg = ima.getScaledInstance(140, 140, Image.SCALE_SMOOTH);
		image = new ImageIcon(newimg);
		lblHinh.setIcon(image);
		LocalDate ngay = LocalDate.now();
		datePicker.getJFormattedTextField().setText(dtf.format(ngay));
		model_date.setValue(new Date(ngay.getYear() - 1900, ngay.getMonthValue() - 1, ngay.getDayOfMonth()));
		hinhAnh = getImageFromLbl(lblHinh);
	}

	public static String[] capNhatCmbNCC() {
		String[] s = {};
		List<String> list = new ArrayList<>(Arrays.asList(s));
		for (NhaCungCap ncc : nhaCungCap_DAO.getDsNCC()) {
			list.add(ncc.getTenNCC());
		}
		s = list.toArray(new String[0]);
		return s;
	}

	private String[] capNhatCmbLoai() {
		String[] s = {};
		List<String> list = new ArrayList<>(Arrays.asList(s));
		for (LoaiSanPham ct : loaiSanPham_DAO.getDsLoaiSP()) {
			list.add(ct.getTenLoai());
		}
		s = list.toArray(new String[0]);
		return s;
	}

	private String[] capNhatCmbMauSac() {
		String[] s = {};
		List<String> list = new ArrayList<>(Arrays.asList(s));
		for (MauSac ct : mauSac_DAO.getDsMauSac()) {
			list.add(ct.getTenMauSac());
		}
		s = list.toArray(new String[0]);
		return s;
	}

	private String[] capNhatCmbKichCo() {
		String[] s = {};
		List<String> list = new ArrayList<>(Arrays.asList(s));
		for (KichCo ct : kichCo_DAO.getDsKichCo()) {
			list.add(ct.getTenKichCo());
		}
		s = list.toArray(new String[0]);
		return s;
	}

	private String[] capNhatCmbChatLieu() {
		String[] s = {};
		List<String> list = new ArrayList<>(Arrays.asList(s));
		for (ChatLieu cl : chatLieu_DAO.getDsChatLieu()) {
			list.add(cl.getTenChatLieu());
		}
		s = list.toArray(new String[0]);
		return s;
	}

	public static String[] capNhatCmbTimNCC() {
		String[] s = {};
		List<String> list = new ArrayList<>(Arrays.asList(s));
		list.add("Tất cả");
		for (NhaCungCap ncc : nhaCungCap_DAO.getDsNCC()) {
			list.add(ncc.getTenNCC());
		}
		s = list.toArray(new String[0]);
		return s;
	}

	private String[] capNhatCmbTimLoai() {
		String[] s = {};
		List<String> list = new ArrayList<>(Arrays.asList(s));
		list.add("Tất cả");
		for (LoaiSanPham ct : loaiSanPham_DAO.getDsLoaiSP()) {
			list.add(ct.getTenLoai());
		}
		s = list.toArray(new String[0]);
		return s;
	}

	private String[] capNhatCmbTimMauSac() {
		String[] s = {};
		List<String> list = new ArrayList<>(Arrays.asList(s));
		list.add("Tất cả");
		for (MauSac ct : mauSac_DAO.getDsMauSac()) {
			list.add(ct.getTenMauSac());
		}
		s = list.toArray(new String[0]);
		return s;
	}

	private String[] capNhatCmbTimKichCo() {
		String[] s = {};
		List<String> list = new ArrayList<>(Arrays.asList(s));
		list.add("Tất cả");
		for (KichCo ct : kichCo_DAO.getDsKichCo()) {
			list.add(ct.getTenKichCo());
		}
		s = list.toArray(new String[0]);
		return s;
	}

	private String[] capNhatCmbTimChatLieu() {
		String[] s = {};
		List<String> list = new ArrayList<>(Arrays.asList(s));
		list.add("Tất cả");
		for (ChatLieu cl : chatLieu_DAO.getDsChatLieu()) {
			list.add(cl.getTenChatLieu());
		}
		s = list.toArray(new String[0]);
		return s;
	}

	private byte[] getImageFromLbl(JLabel lable) {
		Icon icon = lable.getIcon();
		BufferedImage bi = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics g = bi.createGraphics();
		icon.paintIcon(null, g, 0, 0);
		g.dispose();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ImageIO.write(bi, "jpg", baos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return baos.toByteArray();
	}

	private byte[] chooseImage(JLabel lable) {
		String pathImage = null;
		// file.setCurrentDirectory(new File(System.getProperty("user.dir")) );

		// if user select a file
		int fileState = fileChooser.showSaveDialog(null);
		if (fileState == JFileChooser.APPROVE_OPTION) {
			File selectFile = fileChooser.getSelectedFile();
			pathImage = selectFile.getAbsolutePath();

			// display the image in jlabel using resizeImage
			Icon myImage = null;

			if (pathImage != null) {
				myImage = new ImageIcon(pathImage);
				Image ima = ((ImageIcon) myImage).getImage();
				Image newimg = ima.getScaledInstance(160, 160, Image.SCALE_SMOOTH);
				myImage = new ImageIcon(newimg);
				lable.setIcon(myImage);
			}
			if (pathImage.equals(""))
				return getImageFromLbl(lblHinh);

		}
		return getBytesImg(pathImage);
	}

	private byte[] getBytesImg(String filePath) {
		// byte[] imageBytes = null;
		if (filePath == null)
			return getImageFromLbl(lblHinh);
		File fnew = new File(filePath);
		BufferedImage originalImage = null;
		try {
			originalImage = ImageIO.read(fnew);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ImageIO.write(originalImage, "jpg", baos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return baos.toByteArray();
		// return imageBytes;
	}

	public boolean validDataSP() {

		String giaNhap = txt_GiaNhap.getText().trim();
		String giaBan = txt_GiaBan.getText();
		String soLuong = txt_soLuong.getText().trim();

		if (!(giaBan.length() > 0 && giaBan.matches("^[0-9]+$"))) {
			JOptionPane.showMessageDialog(null, "Giá bán không hợp lệ ");
			return false;
		}
		if (!(soLuong.length() > 0 && soLuong.matches("^[0-9]+$"))) {
			JOptionPane.showMessageDialog(null, "Số lượng không hợp lệ ");
			return false;
		}
		if (!(giaNhap.length() > 0 && giaNhap.matches("^[0-9]+$"))) {
			JOptionPane.showMessageDialog(null, "Giá nhập không hợp lệ ");
			return false;
		}
		return true;
	}

	public void updateData(List<SanPham> ds) {
		// xoaTrangTable(tbl_Ds);
		df = new DecimalFormat("#,###");
		dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		List<SanPham> list = sanPham_DAO.getDsSanPham();
		for (SanPham sp : list) {
			String[] rowData = { sp.getMaSP(), sp.getTenSP(), Double.toString(sp.getGiaNhap()),
					Double.toString(sp.getGiaBan()), dtf.format(sp.getNgayNhap()).substring(0, 10),
					sp.getLoaiSP().getTenLoai(), sp.getMauSac().getTenMauSac(), sp.getChatLieu().getTenChatLieu(),
					sp.getKichCo().getTenKichCo(), sp.getNhaCungCap().getTenNCC(), Integer.toString(sp.getSoLuongTon()),
					sp.getTrangThai() };
			model_ds.addRow(rowData);
		}

	}

	public static void resetData() {
		KhachHang_DAO kh_DAO = new KhachHang_DAO();
		List<KhachHang> ds = kh_DAO.getDsKhachHang();
		ManHinhTimKiemKhachHang.updateData(ds);
	}
}
