package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
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
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

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

public class ManHinhTimKiemSanPham extends JPanel implements ActionListener, MouseListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnXoaTrangSP;
	private JScrollPane scr_Ds;
	private JTable tbl_Ds;
	private DefaultTableModel model_ds;

	private DecimalFormat df;
	private DateTimeFormatter dtf;

	private JTextField txt_TenSP;
	private JTextField txt_ngaySinh;
	private JTextField txt_GiaNhap;
	private JComboBox cmb_MauSac;
	private JComboBox cmb_LoaiSanPham;
	private JComboBox cmb_KichCo ;
	private JComboBox cmb_ChatLieu ;
	private JComboBox cmb_NCC;
	private JComboBox cmb_TinhTrang;
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
	private JButton btnSearchSP;
	private JTextField txt_maSP;
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
		pn_thaotac.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true), "Tìm thông tin sản phẩm", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(65, 105, 225)));
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
		pn_kqTimKiem.setBounds(10, 32, 1080, 257);
		pn_thaotac.add(pn_kqTimKiem);

		JLabel lblMaSP = new JLabel("Mã sản phẩm:");
		lblMaSP.setHorizontalAlignment(SwingConstants.LEFT);
		lblMaSP.setFont(new Font("Arial", Font.PLAIN, 11));
		lblMaSP.setBounds(10, 15, 80, 20);
		pn_kqTimKiem.add(lblMaSP);

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

		model_date = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		datePanel = new JDatePanelImpl(model_date,p);
		model_date.setValue(new Date(123, 00, 01));
		datePicker = new JDatePickerImpl(datePanel, new custom.DateLabelFormatter());
		datePicker.setBackground(new Color(255, 255, 255));
		datePicker.getJFormattedTextField().setBackground(new Color(255, 255, 255));
		datePicker.setBounds(75, 100, 150, 27);
		datePicker.getJDateInstantPanel().setShowYearButtons(true);
		datePicker.getJFormattedTextField().setText("01-01-2023");
		datePicker.setButtonFocusable(false);
		//datePicker.getModel().setDate(2000, 1, 1);
		pn_kqTimKiem.add(datePicker);

		String [] mauSac = capNhatCmbMauSac();
		cmb_MauSac = new JComboBox();
		cmb_MauSac.setModel(new DefaultComboBoxModel(mauSac));
		cmb_MauSac.setBackground(new Color(245, 222, 179));
		cmb_MauSac.setBounds(498, 100, 90, 25);
		pn_kqTimKiem.add(cmb_MauSac);

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

		String [] loai = capNhatCmbLoai();
		cmb_LoaiSanPham = new JComboBox();
		cmb_LoaiSanPham.setModel(new DefaultComboBoxModel(loai));
		cmb_LoaiSanPham.setBackground(new Color(245, 222, 179));
		cmb_LoaiSanPham.setBounds(338, 100, 100, 25);
		pn_kqTimKiem.add(cmb_LoaiSanPham);

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
		btnXoaTrangSP.setBounds(529, 174, 100, 30);
		pn_kqTimKiem.add(btnXoaTrangSP);

		

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

		String [] chatlieu = capNhatCmbChatLieu();
		cmb_ChatLieu = new JComboBox();
		cmb_ChatLieu.setModel(new DefaultComboBoxModel(chatlieu));
		cmb_ChatLieu.setBackground(new Color(245, 222, 179));
		cmb_ChatLieu.setBounds(673, 100, 151, 25);
		pn_kqTimKiem.add(cmb_ChatLieu);

		JLabel lblChatLieu = new JLabel("Chất liệu:");
		lblChatLieu.setHorizontalAlignment(SwingConstants.LEFT);
		lblChatLieu.setFont(new Font("Arial", Font.PLAIN, 11));
		lblChatLieu.setBounds(613, 100, 50, 20);
		pn_kqTimKiem.add(lblChatLieu);

		String [] kc = capNhatCmbKichCo();
		cmb_KichCo = new JComboBox();
		cmb_KichCo.setModel(new DefaultComboBoxModel(kc));
		cmb_KichCo.setBackground(new Color(245, 222, 179));
		cmb_KichCo.setBounds(884, 100, 60, 25);
		pn_kqTimKiem.add(cmb_KichCo);

		JLabel lblKichCo = new JLabel("Kích cỡ:");
		lblKichCo.setHorizontalAlignment(SwingConstants.LEFT);
		lblKichCo.setFont(new Font("Arial", Font.PLAIN, 11));
		lblKichCo.setBounds(834, 100, 50, 20);
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
		cmb_NCC = new JComboBox();
		cmb_NCC.setModel(new DefaultComboBoxModel(ncc));
		cmb_NCC.setBounds(90, 55, 237, 22);
		pn_kqTimKiem.add(cmb_NCC);
		
		btnSearchSP = new JButton("Tìm");
		btnSearchSP.setFont(new Font("Arial", Font.BOLD, 14));
		btnSearchSP.setBackground(Color.WHITE);
		btnSearchSP.setBounds(398, 174, 100, 30);
		pn_kqTimKiem.add(btnSearchSP);
		
		cmb_TinhTrang = new JComboBox();
		cmb_TinhTrang.setModel(new DefaultComboBoxModel(new String[] {"Đang kinh doanh", "Hết hàng", "Ngừng kinh doanh"}));
		cmb_TinhTrang.setBounds(712, 55, 129, 22);
		pn_kqTimKiem.add(cmb_TinhTrang);
		
		txt_maSP = new JTextField();
		txt_maSP.setHorizontalAlignment(SwingConstants.LEFT);
		txt_maSP.setColumns(10);
		txt_maSP.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_maSP.setBackground(new Color(255, 250, 240));
		txt_maSP.setBounds(90, 15, 100, 20);
		pn_kqTimKiem.add(txt_maSP);

		//hình
		Icon image = new ImageIcon(ManHinhCapNhatSanPham.class.getResource("/images/null.jpg"));
		Image ima = ((ImageIcon) image).getImage();
		Image newimg = ima.getScaledInstance(70, 70, Image.SCALE_SMOOTH);

		image = new ImageIcon(newimg);
		
		


		JPanel pn_dsnv = new JPanel();
		pn_dsnv.setBackground(new Color(255, 255, 255));
		pn_dsnv.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true), "Danh sách sản phẩm", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(65, 105, 225)));
		pn_dsnv.setBounds(10, 350, 1100, 370);
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
		
		btnSearchSP.addActionListener(this);
		btnXoaTrangSP.addActionListener(this);
		docDuLieu();
		tbl_Ds.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = tbl_Ds.getSelectedRow();
		txt_maSP.setText(tbl_Ds.getValueAt(row, 0).toString());
		txt_TenSP.setText(tbl_Ds.getValueAt(row, 1).toString());
		txt_GiaNhap.setText(tbl_Ds.getValueAt(row, 2).toString());
		txt_GiaBan.setText(tbl_Ds.getValueAt(row, 3).toString());
		
		String date = tbl_Ds.getValueAt(row, 4).toString();
		datePicker.getJFormattedTextField().setText(date);
		SanPham sp = sanPham_DAO.getSanPham(tbl_Ds.getValueAt(row, 0).toString());
		model_date.setValue(new Date(sp.getNgayNhap().getYear() - 1900, sp.getNgayNhap().getMonthValue() - 1,
				sp.getNgayNhap().getDayOfMonth()));
		
		cmb_LoaiSanPham.setSelectedItem(tbl_Ds.getValueAt(row, 5).toString());
		cmb_MauSac.setSelectedItem(tbl_Ds.getValueAt(row, 6).toString());
		cmb_ChatLieu.setSelectedItem(tbl_Ds.getValueAt(row, 7).toString());
		cmb_KichCo.setSelectedItem(tbl_Ds.getValueAt(row, 8).toString());
		cmb_NCC.setSelectedItem(tbl_Ds.getValueAt(row, 9).toString());
		txt_TonKho.setText(tbl_Ds.getValueAt(row, 10).toString());
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
		if (o.equals(btnSearchSP)) {
			String maSP = txt_maSP.getText();
			String tenSP = txt_TenSP.getText();
			String giaBan = txt_GiaBan.getText();
			String giaNhap = txt_GiaNhap.getText();
			String tenNCC = cmb_NCC.getSelectedItem().toString();
			tenNCC = tenNCC.equalsIgnoreCase("Tất cả")?"":tenNCC;
			String tonKho = txt_TonKho.getText();
			String tinhTrang = cmb_TinhTrang.getSelectedItem().toString();
			Date selectedDate = (Date) datePicker.getModel().getValue();
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			String mydate = dateFormat.format(selectedDate);
			
			String tenloaiSP = cmb_LoaiSanPham.getSelectedItem().toString();
			tenloaiSP = tenloaiSP.equalsIgnoreCase("Tất cả")?"":tenloaiSP;
			String tenMauSac = cmb_MauSac.getSelectedItem().toString();
			tenMauSac = tenMauSac.equalsIgnoreCase("Tất cả")?"":tenMauSac;
			String tenChatLieu = cmb_ChatLieu.getSelectedItem().toString();
			tenChatLieu = tenChatLieu.equalsIgnoreCase("Tất cả")?"":tenChatLieu;
			String tenKichCo = cmb_KichCo.getSelectedItem().toString();
			tenKichCo = tenKichCo.equalsIgnoreCase("Tất cả")?"":tenKichCo;

			

			DefaultTableModel model = (DefaultTableModel) tbl_Ds.getModel();
			TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
			tbl_Ds.setRowSorter(sorter);
			List<RowFilter<Object, Object>> filters = new ArrayList<>();
			filters.add(RowFilter.regexFilter(maSP, 0));
			filters.add(RowFilter.regexFilter(tenSP, 1));
			filters.add(RowFilter.regexFilter(giaBan, 2));
			filters.add(RowFilter.regexFilter(giaNhap, 3));
			filters.add(RowFilter.regexFilter(mydate, 4));
			filters.add(RowFilter.regexFilter(tenloaiSP, 5));
			filters.add(RowFilter.regexFilter(tenMauSac, 6));
			filters.add(RowFilter.regexFilter(tenChatLieu, 7));
			filters.add(RowFilter.regexFilter(tenKichCo, 8));
			filters.add(RowFilter.regexFilter(tenNCC, 9));
			filters.add(RowFilter.regexFilter(tonKho, 10));
			filters.add(RowFilter.regexFilter(tinhTrang, 11));
			
			RowFilter<Object, Object> af = RowFilter.andFilter(filters);
			sorter.setRowFilter(af);
		}
		else if (o.equals(btnXoaTrangSP)) {
			xoaRong();
		}
	}
	public void docDuLieu() {
		// TODO Auto-generated method stub
		df = new DecimalFormat("#,###");
		dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		SanPham_DAO ds = new SanPham_DAO(); 
		List<SanPham> list = ds.getDsSanPham(); 
		for (SanPham sp : list) {
			String [] rowData = {
					sp.getMaSP(),
					sp.getTenSP(),
					Double.toString(sp.getGiaNhap()),
					Double.toString(sp.getGiaBan()),
					dtf.format(sp.getNgayNhap()).substring(0, 10),
					sp.getLoaiSP().getTenLoai(),
					sp.getMauSac().getTenMauSac(),
					sp.getChatLieu().getTenChatLieu(),
					sp.getKichCo().getTenKichCo(),
					sp.getNhaCungCap().getTenNCC(),
					Integer.toString(sp.getSoLuongTon()),
					sp.getTrangThai()
			};
			model_ds.addRow(rowData);
		}
		tbl_Ds.setModel (model_ds);
	}
	private void xoaRong() {
		txt_maSP.setText("");
		txt_TenSP.setText("");
		txt_GiaNhap.setText("");
		txt_GiaBan.setText("");
		txt_TonKho.setText("");
		cmb_ChatLieu.setSelectedIndex(0);
		cmb_KichCo.setSelectedIndex(0);
		cmb_LoaiSanPham.setSelectedIndex(0);
		cmb_MauSac.setSelectedIndex(0);
		cmb_NCC.setSelectedIndex(0);
		cmb_TinhTrang.setSelectedIndex(0);
		txt_maSP.requestFocus();
	}

	private String[] capNhatCmbNCC() {
		String[] s = {};
		List<String> list = new ArrayList<>(Arrays.asList(s));
		list.add("Tất cả");
		for (NhaCungCap ncc : nhaCungCap_DAO.getDsNCC()) {
			list.add(ncc.getTenNCC());
		}
		s = list.toArray(new String[0]);
		return s;
	}
	private String[] capNhatCmbLoai() {
		String[] s = {};
		List<String> list = new ArrayList<>(Arrays.asList(s));
		list.add("Tất cả");
		for (LoaiSanPham ct : loaiSanPham_DAO.getDsLoaiSP()) {
			list.add(ct.getTenLoai());
		}
		s = list.toArray(new String[0]);
		return s;
	}

	private String[] capNhatCmbMauSac() {
		String[] s = {};
		List<String> list = new ArrayList<>(Arrays.asList(s));
		list.add("Tất cả");
		for (MauSac ct : mauSac_DAO.getDsMauSac()) {
			list.add(ct.getTenMauSac());
		}
		s = list.toArray(new String[0]);
		return s;
	}

	private String[] capNhatCmbKichCo() {
		String[] s = {};
		List<String> list = new ArrayList<>(Arrays.asList(s));
		list.add("Tất cả");
		for (KichCo ct : kichCo_DAO.getDsKichCo()) {
			list.add(ct.getTenKichCo());
		}
		s = list.toArray(new String[0]);
		return s;
	}


	private String[] capNhatCmbChatLieu() {
		String[] s = {};
		List<String> list = new ArrayList<>(Arrays.asList(s));
		list.add("Tất cả");
		for (ChatLieu cl : chatLieu_DAO.getDsChatLieu()) {
			list.add(cl.getTenChatLieu());
		}
		s = list.toArray(new String[0]);
		return s;
	}
}
