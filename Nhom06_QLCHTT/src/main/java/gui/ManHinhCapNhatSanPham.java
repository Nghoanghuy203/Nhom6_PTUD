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
import dao.KichCo_DAO;
import dao.LoaiSanPham_DAO;
import dao.MauSac_DAO;
import dao.NhaCungCap_DAO;
import dao.SanPham_DAO;
import entities.ChatLieu;
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
	private JTextField txtSearchSP;
	private JButton btnSearchSP;
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

	private JTextField txt_TenSP;
	private JTextField txt_ngaySinh;
	private JTextField txt_GiaNhap;
	private JLabel lblHinh;
	private JComboBox combo_MauSac;
	private JComboBox combo_LoaiSanPham;
	private JComboBox combo_KichCo;
	private JComboBox combo_ChatLieu;
	private JComboBox combo_NCC;
	private JComboBox combo_TinhTrang;
	private UtilDateModel model_date;
	private JDatePanelImpl datePanel;
	private JDatePickerImpl datePicker;
	private JTextField txt_GiaBan;
	private JTextField txt_TonKho;
	private NhaCungCap_DAO nhaCungCap_DAO = new NhaCungCap_DAO();
	private SanPham_DAO sanPham_DAO = new SanPham_DAO();
	private LoaiSanPham_DAO loaiSanPham_DAO = new LoaiSanPham_DAO();
	private MauSac_DAO mauSac_DAO = new MauSac_DAO();
	private KichCo_DAO kichCo_DAO = new KichCo_DAO();
	private ChatLieu_DAO chatLieu_DAO = new ChatLieu_DAO();
	private ArrayList<SanPham> dsSP = new ArrayList<SanPham>();
	
	private JFileChooser fileChooser;
	private String filePath;
	private byte[] hinhAnh;

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

		JPanel timKiem = new JPanel();
		timKiem.setBackground(new Color(255, 255, 255));
		timKiem.setBounds(10, 20, 300, 30);
		timKiem.setBorder(new RoundedCornerBorder());
		pn_thaotac.add(timKiem);
		timKiem.setLayout(null);

		txtSearchSP = new JTextField();
		txtSearchSP.setText("Nhập mã sản phẩm...");
		txtSearchSP.setForeground(Color.GRAY);
		txtSearchSP.setEditable(false);
		txtSearchSP.setBackground(new Color(255, 255, 255));
		txtSearchSP.setBounds(10, 3, 191, 24);
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

		btnSearchSP = new JButton("Tìm");
		// btnSearch.setBorder(new RoundedCornerBorder());
		btnSearchSP.setBackground(new Color(65, 105, 225));
		btnSearchSP.setIcon(new ImageIcon(ManHinhChinh.class.getResource("/images/search.png")));
		btnSearchSP.setBounds(205, 3, 85, 24);
		timKiem.add(btnSearchSP);

		JLabel lbl_thongBaoKq = new JLabel();
		lbl_thongBaoKq.setBounds(60, 60, 100, 14);
		lbl_thongBaoKq.setForeground(Color.red);
		pn_thaotac.add(lbl_thongBaoKq);

		JPanel pn_kqTimKiem = new JPanel();
		pn_kqTimKiem.setLayout(null);
		pn_kqTimKiem.setBackground(new Color(255, 250, 240));
		pn_kqTimKiem.setBounds(10, 61, 1080, 229);
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

		btnSuaSP = new JButton("Sửa");
		btnSuaSP.setBounds(437, 188, 100, 30);
		pn_kqTimKiem.add(btnSuaSP);
		btnSuaSP.setFont(new Font("Arial", Font.BOLD, 14));
		btnSuaSP.setBackground(new Color(244, 164, 96));

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
		datePicker.setBounds(75, 100, 150, 27);
		datePicker.getJDateInstantPanel().setShowYearButtons(true);
		datePicker.getJFormattedTextField().setText("2023-01-01");
		datePicker.setButtonFocusable(false);
		// datePicker.getModel().setDate(2000, 1, 1);
		pn_kqTimKiem.add(datePicker);

		String[] mauSac = capNhatCmbMauSac();
		combo_MauSac = new JComboBox();
		combo_MauSac.setModel(new DefaultComboBoxModel(mauSac));
		combo_MauSac.setBackground(new Color(245, 222, 179));
		combo_MauSac.setBounds(498, 100, 90, 25);
		pn_kqTimKiem.add(combo_MauSac);

		JLabel lblNCC = new JLabel("Nhà cung cấp:");
		lblNCC.setHorizontalAlignment(SwingConstants.LEFT);
		lblNCC.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNCC.setBounds(10, 56, 80, 20);
		pn_kqTimKiem.add(lblNCC);

		JLabel lblLoaiSanPham = new JLabel("Loại sản phẩm:");
		lblLoaiSanPham.setHorizontalAlignment(SwingConstants.LEFT);
		lblLoaiSanPham.setFont(new Font("Arial", Font.PLAIN, 11));
		lblLoaiSanPham.setBounds(258, 100, 80, 20);
		pn_kqTimKiem.add(lblLoaiSanPham);

		String[] loai = capNhatCmbLoai();
		combo_LoaiSanPham = new JComboBox();
		combo_LoaiSanPham.setModel(new DefaultComboBoxModel(loai));
		combo_LoaiSanPham.setBackground(new Color(245, 222, 179));
		combo_LoaiSanPham.setBounds(338, 100, 100, 25);
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
		lblGiaBan.setBounds(651, 15, 60, 20);
		pn_kqTimKiem.add(lblGiaBan);

		txt_GiaBan = new JTextField();
		txt_GiaBan.setHorizontalAlignment(SwingConstants.LEFT);
		txt_GiaBan.setColumns(10);
		txt_GiaBan.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_GiaBan.setBackground(new Color(255, 250, 240));
		txt_GiaBan.setBounds(712, 15, 129, 20);
		pn_kqTimKiem.add(txt_GiaBan);

		String[] chatlieu = capNhatCmbChatLieu();
		combo_ChatLieu = new JComboBox();
		combo_ChatLieu.setModel(new DefaultComboBoxModel(chatlieu));
		combo_ChatLieu.setBackground(new Color(245, 222, 179));
		combo_ChatLieu.setBounds(673, 100, 151, 25);
		pn_kqTimKiem.add(combo_ChatLieu);

		JLabel lblChatLieu = new JLabel("Chất liệu:");
		lblChatLieu.setHorizontalAlignment(SwingConstants.LEFT);
		lblChatLieu.setFont(new Font("Arial", Font.PLAIN, 11));
		lblChatLieu.setBounds(613, 100, 50, 20);
		pn_kqTimKiem.add(lblChatLieu);

		String[] kc = capNhatCmbKichCo();
		combo_KichCo = new JComboBox();
		combo_KichCo.setModel(new DefaultComboBoxModel(kc));
		combo_KichCo.setBackground(new Color(245, 222, 179));
		combo_KichCo.setBounds(749, 138, 60, 25);
		pn_kqTimKiem.add(combo_KichCo);

		JLabel lblKichCo = new JLabel("Kích cỡ:");
		lblKichCo.setHorizontalAlignment(SwingConstants.LEFT);
		lblKichCo.setFont(new Font("Arial", Font.PLAIN, 11));
		lblKichCo.setBounds(699, 138, 50, 20);
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

		String[] ncc = capNhatCmbNCC();
		combo_NCC = new JComboBox();
		combo_NCC.setModel(new DefaultComboBoxModel(ncc));
		combo_NCC.setBounds(90, 55, 237, 22);
		pn_kqTimKiem.add(combo_NCC);

		btnThemAnh = new JButton("Thêm ảnh");
		btnThemAnh.setBounds(903, 183, 100, 20);
		pn_kqTimKiem.add(btnThemAnh);

		// hình
		Icon image = new ImageIcon(ManHinhCapNhatSanPham.class.getResource("/images/null.jpg"));
		Image ima = ((ImageIcon) image).getImage();
		Image newimg = ima.getScaledInstance(140, 140, Image.SCALE_SMOOTH);

		image = new ImageIcon(newimg);
		lblHinh = new JLabel(image);
		lblHinh.setBounds(873, 20, 160, 160);
		pn_kqTimKiem.add(lblHinh);
		lblHinh.setHorizontalAlignment(JLabel.CENTER);
		lblHinh.setOpaque(true);

		combo_TinhTrang = new JComboBox();
		combo_TinhTrang
				.setModel(new DefaultComboBoxModel(new String[] { "Đang kinh doanh", "Hết hàng", "Ngừng kinh doanh" }));
		combo_TinhTrang.setBounds(712, 55, 129, 22);
		pn_kqTimKiem.add(combo_TinhTrang);

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
		btnSearchSP.addActionListener(this);
		btnSuaSP.addActionListener(this);
		btnXoaTrangSP.addActionListener(this);
		btnThemAnh.addActionListener(this);
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
		combo_LoaiSanPham.setSelectedItem(tbl_Ds.getValueAt(row, 5).toString());
		combo_MauSac.setSelectedItem(tbl_Ds.getValueAt(row, 6).toString());
		combo_ChatLieu.setSelectedItem(tbl_Ds.getValueAt(row, 7).toString());
		combo_KichCo.setSelectedItem(tbl_Ds.getValueAt(row, 8).toString());
		combo_NCC.setSelectedItem(tbl_Ds.getValueAt(row, 9).toString());
		txt_TonKho.setText(tbl_Ds.getValueAt(row, 10).toString());
		combo_TinhTrang.setSelectedItem(tbl_Ds.getValueAt(row, 11).toString());
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

			String tenSP = txt_TenSP.getText();
			double giaBan = Double.parseDouble(txt_GiaBan.getText());
			double giaNhap = Double.parseDouble(txt_GiaNhap.getText());
			String tenNCC = combo_NCC.getSelectedItem().toString();
			String maNCC = NhaCungCap_DAO.getMaNCC(tenNCC);
			NhaCungCap NCC = new NhaCungCap(maNCC, tenNCC);

			int tonKho = Integer.parseInt(txt_TonKho.getText());
			String tinhTrang = combo_TinhTrang.getSelectedItem().toString();

			Date selectedDate = (Date) datePicker.getModel().getValue();
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			String mydate = dateFormat.format(selectedDate);
			int nam = Integer.parseInt(mydate.substring(6, 10));
			int thang = Integer.parseInt(mydate.substring(3, 5));
			int ngay = Integer.parseInt(mydate.substring(0, 2));
			LocalDateTime ngayNhap = LocalDateTime.of(nam, thang, ngay, 0, 0);

			String tenloaiSP = combo_LoaiSanPham.getSelectedItem().toString();
			String maLoai = LoaiSanPham_DAO.getMaLoaiSanPham(tenloaiSP);
			LoaiSanPham loai = new LoaiSanPham(maLoai, tenloaiSP);

			String tenMauSac = combo_MauSac.getSelectedItem().toString();
			String maMauSac = MauSac_DAO.getMaMauSac(tenMauSac);
			MauSac mauSac = new MauSac(maMauSac, tenMauSac);

			String tenChatLieu = combo_ChatLieu.getSelectedItem().toString();
			String maChatLieu = ChatLieu_DAO.getMaChatLieu(tenChatLieu);
			ChatLieu chatLieu = new ChatLieu(maChatLieu, tenChatLieu);

			String tenKichCo = combo_KichCo.getSelectedItem().toString();
			String maKichCo = KichCo_DAO.getMaKichCo(tenKichCo);
			KichCo kichCo = new KichCo(maKichCo, tenKichCo);

			String maSP = GeneratorID.generateIDSanPham(maLoai, maKichCo);

			byte[] hinhAnh = getImageFromLbl(lblHinh);
			SanPham sp = new SanPham(maSP, tenSP, giaNhap, giaBan, loai, hinhAnh, kichCo, chatLieu, mauSac, tonKho,
					ngayNhap, tinhTrang, NCC);// ncc
			if (sanPham_DAO.themSanPham(sp)) {
				/*
				 * Object[] rowData = { maSP, tenSP, giaNhap, giaBan, ngayNhap, loai, mauSac,
				 * chatLieu , kichCo, NCC, tonKho, tinhTrang }; model_ds.addRow(rowData);
				 * dsSP.add(sp);
				 */
				docDuLieu();
				xoaRong();
				JOptionPane.showMessageDialog(null, "Thêm thành công");	
			}
		} else if (o.equals(btnSuaSP)) {
			int row = tbl_Ds.getSelectedRow();
			if (row >= 0) {
				String tenSP = txt_TenSP.getText();
				double giaBan = Double.parseDouble(txt_GiaBan.getText());
				double giaNhap = Double.parseDouble(txt_GiaNhap.getText());
				String tenNCC = combo_NCC.getSelectedItem().toString();
				String maNCC = NhaCungCap_DAO.getMaNCC(tenNCC);
				NhaCungCap NCC = new NhaCungCap(maNCC, tenNCC);

				int tonKho = Integer.parseInt(txt_TonKho.getText());
				String tinhTrang = combo_TinhTrang.getSelectedItem().toString();

				Date selectedDate = (Date) datePicker.getModel().getValue();
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				String mydate = dateFormat.format(selectedDate);
				int nam = Integer.parseInt(mydate.substring(6, 10));
				int thang = Integer.parseInt(mydate.substring(3, 5));
				int ngay = Integer.parseInt(mydate.substring(0, 2));
				LocalDateTime ngayNhap = LocalDateTime.of(nam, thang, ngay, 0, 0);

				String tenloaiSP = combo_LoaiSanPham.getSelectedItem().toString();
				String maLoai = LoaiSanPham_DAO.getMaLoaiSanPham(tenloaiSP);
				LoaiSanPham loai = new LoaiSanPham(maLoai, tenloaiSP);

				String tenMauSac = combo_MauSac.getSelectedItem().toString();
				String maMauSac = MauSac_DAO.getMaMauSac(tenMauSac);
				MauSac mauSac = new MauSac(maMauSac, tenMauSac);

				String tenChatLieu = combo_ChatLieu.getSelectedItem().toString();
				String maChatLieu = ChatLieu_DAO.getMaChatLieu(tenChatLieu);
				ChatLieu chatLieu = new ChatLieu(maChatLieu, tenChatLieu);

				String tenKichCo = combo_KichCo.getSelectedItem().toString();
				String maKichCo = KichCo_DAO.getMaKichCo(tenKichCo);
				KichCo kichCo = new KichCo(maKichCo, tenKichCo);
				
				byte[] hinhAnh = getImageFromLbl(lblHinh);
				
				String maSP = txt_maSP.getText();
				SanPham sp = new SanPham(maSP, tenSP, giaNhap, giaBan, loai, hinhAnh, kichCo, chatLieu, mauSac, tonKho,
						ngayNhap, tinhTrang, NCC);// ncc
				if (sanPham_DAO.capNhatSanPham(sp)) {
					/*
					tbl_Ds.setValueAt(txt_maSP.getText(), row, 0);
					tbl_Ds.setValueAt(txt_TenSP.getText(), row, 1);
					tbl_Ds.setValueAt(txt_GiaNhap.getText(), row, 2);
					tbl_Ds.setValueAt(txt_GiaBan.getText(), row, 3);
					tbl_Ds.setValueAt(txt_ngaySinh.getText(), row, 4);
					tbl_Ds.setValueAt(combo_ChatLieu.getSelectedItem(), row, 5);
					tbl_Ds.setValueAt(combo_MauSac.getSelectedItem(), row, 6);
					tbl_Ds.setValueAt(combo_ChatLieu.getSelectedItem(), row, 7);
					tbl_Ds.setValueAt(combo_KichCo.getSelectedItem(), row, 8);
					tbl_Ds.setValueAt(combo_NCC.getSelectedItem(), row, 9);
					tbl_Ds.setValueAt(txt_TonKho.getText(), row, 10);
					tbl_Ds.setValueAt(combo_TinhTrang.getSelectedItem(), row, 11);
					*/
					docDuLieu();
					JOptionPane.showMessageDialog(null, "Cập nhật thành công");				
				}

			}
		} else if (o.equals(btnSearchSP)) {
			String ten = txtSearchSP.getText();
			DefaultTableModel model = (DefaultTableModel) tbl_Ds.getModel();
			TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
			tbl_Ds.setRowSorter(sorter);
			List<RowFilter<Object, Object>> filters = new ArrayList<>();
			filters.add(RowFilter.regexFilter(ten, 0));
			RowFilter<Object, Object> af = RowFilter.andFilter(filters);
			sorter.setRowFilter(af);
		} else if (o.equals(btnXoaTrangSP)) {
			xoaRong();
		} else if (o.equals(btnThemAnh)) {
			hinhAnh= chooseImage(lblHinh);
		}
	}

	private void xoaRong() {
		txtSearchSP.setText("Nhập mã sản phẩm...");
		txtSearchSP.setForeground(Color.GRAY);
		txt_maSP.setText("");
		txt_TenSP.setText("");
		txt_GiaNhap.setText("");
		txt_GiaBan.setText("");
		txt_TonKho.setText("");
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

	private String[] capNhatCmbNCC() {
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
	
	private byte[] getImageFromLbl(JLabel lable) {
		Icon icon = lable.getIcon();
		BufferedImage bi = new BufferedImage(
                 icon.getIconWidth(),
                 icon.getIconHeight(),
                 BufferedImage.TYPE_INT_RGB);
		Graphics g = bi.createGraphics();
        icon.paintIcon(null, g, 0, 0);
        g.dispose();
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		try {
			ImageIO.write(bi, "jpg", baos );
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
			if (pathImage.equals("")) return getImageFromLbl(lblHinh);

		}
		return getBytesImg(pathImage);
	}

	private byte[] getBytesImg(String filePath) {
		//byte[] imageBytes = null;
		if (filePath==null) return getImageFromLbl(lblHinh);
		File fnew=new File(filePath);
		BufferedImage originalImage = null;
		try {
			originalImage = ImageIO.read(fnew);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		try {
			ImageIO.write(originalImage, "jpg", baos );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return baos.toByteArray();
		//return imageBytes;
	}
	
	
}
