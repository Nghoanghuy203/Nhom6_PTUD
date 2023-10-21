package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
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

import connectDB.ConnectDB;
import custom.RoundedCornerBorder;
import dao.DonDatHang_DAO;
import entities.DonDatHang;
import javax.swing.JComboBox;

public class ManHinhCapNhatNhanVien extends JPanel {

	private JTextField txtSearch;
	private JButton btnSearch;

	private JScrollPane scr_Ds;
	private JTable tbl_Ds;
	private DefaultTableModel model_ds;

	private JLabel lbl_kqMa;

	private DecimalFormat df;

	private JTextField txt_hoTenNV;
	private JTextField txt_ngaySinh;
	private JTextField txt_CCCD;
	private JTextField txt_soDienThoai;
	private JTextField txt_email;
	private JTextField txt_diaChi;
	private JTextField txt_LuongCoBan;
	private JTextField txt_heSoLuong;
	private JTextField txt_PhuCap;
	private JTextField txt_matKhau;

	/**
	 * Create the panel.
	 */
	public ManHinhCapNhatNhanVien() {

		/**
		 * Create the panel.
		 */

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

		

		JPanel pn_thaotac = new JPanel();
		pn_thaotac.setBackground(new Color(255, 255, 255));
		pn_thaotac.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true), "C\u1EADp nh\u1EADt th\u00F4ng tin nh\u00E2n vi\u00EAn", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(65, 105, 225)));
		pn_thaotac.setBounds(10, 50, 1100, 300);
		add(pn_thaotac);
		pn_thaotac.setLayout(null);

		JPanel timKiem = new JPanel();
		timKiem.setBackground(new Color(255, 255, 255));
		timKiem.setBounds(10, 20, 300, 30);
		timKiem.setBorder(new RoundedCornerBorder());
		pn_thaotac.add(timKiem);
		timKiem.setLayout(null);

		txtSearch = new JTextField();
		txtSearch.setText("Nhập mã nhân viên...");
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
		// btnSearch.setBorder(new RoundedCornerBorder());
		btnSearch.setBackground(new Color(65, 105, 225));
		btnSearch.setIcon(new ImageIcon(ManHinhBanHang.class.getResource("/images/search.png")));
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
		pn_kqTimKiem.setBounds(10, 90, 1080, 200);
		pn_thaotac.add(pn_kqTimKiem);

		JLabel lblMaNV = new JLabel("Mã nhân viên:");
		lblMaNV.setHorizontalAlignment(SwingConstants.LEFT);
		lblMaNV.setFont(new Font("Arial", Font.PLAIN, 11));
		lblMaNV.setBounds(10, 15, 70, 20);
		pn_kqTimKiem.add(lblMaNV);

		lbl_kqMa = new JLabel("");
		lbl_kqMa.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(128, 128, 128)));
		lbl_kqMa.setBounds(80, 15, 80, 20);
		pn_kqTimKiem.add(lbl_kqMa);

		JLabel lblTenNV = new JLabel("Họ tên:");
		lblTenNV.setHorizontalAlignment(SwingConstants.LEFT);
		lblTenNV.setFont(new Font("Arial", Font.PLAIN, 11));
		lblTenNV.setBounds(170, 15, 40, 20);
		pn_kqTimKiem.add(lblTenNV);

		JLabel lblCCCD = new JLabel("CCCD:");
		lblCCCD.setHorizontalAlignment(SwingConstants.LEFT);
		lblCCCD.setFont(new Font("Arial", Font.PLAIN, 11));
		lblCCCD.setBounds(10, 65, 40, 20);
		pn_kqTimKiem.add(lblCCCD);

		JLabel lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setHorizontalAlignment(SwingConstants.LEFT);
		lblGioiTinh.setFont(new Font("Arial", Font.PLAIN, 11));
		lblGioiTinh.setBounds(580, 15, 50, 20);
		pn_kqTimKiem.add(lblGioiTinh);

		JLabel lblNgaySinh = new JLabel("Ngày sinh:");
		lblNgaySinh.setHorizontalAlignment(SwingConstants.LEFT);
		lblNgaySinh.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNgaySinh.setBounds(360, 15, 60, 20);
		pn_kqTimKiem.add(lblNgaySinh);

		JButton btnSua = new JButton("Sửa");
		btnSua.setBounds(860, 160, 100, 30);
		pn_kqTimKiem.add(btnSua);
		btnSua.setFont(new Font("Arial", Font.BOLD, 14));
		btnSua.setBackground(new Color(244, 164, 96));
		
		txt_hoTenNV = new JTextField();
		txt_hoTenNV.setBackground(new Color(255, 250, 240));
		txt_hoTenNV.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_hoTenNV.setHorizontalAlignment(SwingConstants.LEFT);
		txt_hoTenNV.setBounds(210, 15, 140, 20);
		pn_kqTimKiem.add(txt_hoTenNV);
		txt_hoTenNV.setColumns(10);
		
		txt_ngaySinh = new JTextField();
		txt_ngaySinh.setHorizontalAlignment(SwingConstants.LEFT);
		txt_ngaySinh.setColumns(10);
		txt_ngaySinh.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_ngaySinh.setBackground(new Color(255, 250, 240));
		txt_ngaySinh.setBounds(416, 15, 140, 20);
		pn_kqTimKiem.add(txt_ngaySinh);
		
		JComboBox cmb_gioiTinh = new JComboBox();
		cmb_gioiTinh.setBackground(new Color(245, 222, 179));
		cmb_gioiTinh.setBounds(630, 14, 70, 25);
		pn_kqTimKiem.add(cmb_gioiTinh);
		
		txt_CCCD = new JTextField();
		txt_CCCD.setHorizontalAlignment(SwingConstants.LEFT);
		txt_CCCD.setColumns(10);
		txt_CCCD.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_CCCD.setBackground(new Color(255, 250, 240));
		txt_CCCD.setBounds(50, 65, 100, 20);
		pn_kqTimKiem.add(txt_CCCD);
		
		JLabel lblSoDienThoai = new JLabel("Số điện thoại:");
		lblSoDienThoai.setHorizontalAlignment(SwingConstants.LEFT);
		lblSoDienThoai.setFont(new Font("Arial", Font.PLAIN, 11));
		lblSoDienThoai.setBounds(170, 65, 70, 20);
		pn_kqTimKiem.add(lblSoDienThoai);
		
		txt_soDienThoai = new JTextField();
		txt_soDienThoai.setHorizontalAlignment(SwingConstants.LEFT);
		txt_soDienThoai.setColumns(10);
		txt_soDienThoai.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_soDienThoai.setBackground(new Color(255, 250, 240));
		txt_soDienThoai.setBounds(240, 65, 100, 20);
		pn_kqTimKiem.add(txt_soDienThoai);
		
		JLabel lblemail = new JLabel("Email:");
		lblemail.setHorizontalAlignment(SwingConstants.LEFT);
		lblemail.setFont(new Font("Arial", Font.PLAIN, 11));
		lblemail.setBounds(360, 65, 30, 20);
		pn_kqTimKiem.add(lblemail);
		
		txt_email = new JTextField();
		txt_email.setHorizontalAlignment(SwingConstants.LEFT);
		txt_email.setColumns(10);
		txt_email.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_email.setBackground(new Color(255, 250, 240));
		txt_email.setBounds(390, 65, 160, 20);
		pn_kqTimKiem.add(txt_email);
		
		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setHorizontalAlignment(SwingConstants.LEFT);
		lblDiaChi.setFont(new Font("Arial", Font.PLAIN, 11));
		lblDiaChi.setBounds(580, 65, 40, 20);
		pn_kqTimKiem.add(lblDiaChi);
		
		txt_diaChi = new JTextField();
		txt_diaChi.setHorizontalAlignment(SwingConstants.LEFT);
		txt_diaChi.setColumns(10);
		txt_diaChi.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_diaChi.setBackground(new Color(255, 250, 240));
		txt_diaChi.setBounds(620, 65, 160, 20);
		pn_kqTimKiem.add(txt_diaChi);
		
		JLabel lblChucVu = new JLabel("Chức vụ:");
		lblChucVu.setHorizontalAlignment(SwingConstants.LEFT);
		lblChucVu.setFont(new Font("Arial", Font.PLAIN, 11));
		lblChucVu.setBounds(10, 115, 50, 20);
		pn_kqTimKiem.add(lblChucVu);
		
		JComboBox cmb_chucVu = new JComboBox();
		cmb_chucVu.setBackground(new Color(245, 222, 179));
		cmb_chucVu.setBounds(61, 114, 90, 25);
		pn_kqTimKiem.add(cmb_chucVu);
		
		JLabel lblLuongCoBan = new JLabel("Lương cơ bản:");
		lblLuongCoBan.setHorizontalAlignment(SwingConstants.LEFT);
		lblLuongCoBan.setFont(new Font("Arial", Font.PLAIN, 11));
		lblLuongCoBan.setBounds(170, 115, 80, 20);
		pn_kqTimKiem.add(lblLuongCoBan);
		
		txt_LuongCoBan = new JTextField();
		txt_LuongCoBan.setHorizontalAlignment(SwingConstants.LEFT);
		txt_LuongCoBan.setColumns(10);
		txt_LuongCoBan.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_LuongCoBan.setBackground(new Color(255, 250, 240));
		txt_LuongCoBan.setBounds(250, 115, 100, 20);
		pn_kqTimKiem.add(txt_LuongCoBan);
		
		JLabel lblNewLabel = new JLabel("VND");
		lblNewLabel.setBounds(350, 115, 25, 20);
		pn_kqTimKiem.add(lblNewLabel);
		
		JLabel lblHeSoLuong = new JLabel("Hệ số lương:");
		lblHeSoLuong.setHorizontalAlignment(SwingConstants.LEFT);
		lblHeSoLuong.setFont(new Font("Arial", Font.PLAIN, 11));
		lblHeSoLuong.setBounds(390, 115, 70, 20);
		pn_kqTimKiem.add(lblHeSoLuong);
		
		txt_heSoLuong = new JTextField();
		txt_heSoLuong.setHorizontalAlignment(SwingConstants.LEFT);
		txt_heSoLuong.setColumns(10);
		txt_heSoLuong.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_heSoLuong.setBackground(new Color(255, 250, 240));
		txt_heSoLuong.setBounds(460, 115, 50, 20);
		pn_kqTimKiem.add(txt_heSoLuong);
		
		JLabel lblPhuCap = new JLabel("Phụ cấp:");
		lblPhuCap.setHorizontalAlignment(SwingConstants.LEFT);
		lblPhuCap.setFont(new Font("Arial", Font.PLAIN, 11));
		lblPhuCap.setBounds(530, 115, 50, 20);
		pn_kqTimKiem.add(lblPhuCap);
		
		txt_PhuCap = new JTextField();
		txt_PhuCap.setHorizontalAlignment(SwingConstants.LEFT);
		txt_PhuCap.setColumns(10);
		txt_PhuCap.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_PhuCap.setBackground(new Color(255, 250, 240));
		txt_PhuCap.setBounds(580, 115, 100, 20);
		pn_kqTimKiem.add(txt_PhuCap);
		
		JLabel lblNewLabel_1 = new JLabel("VND");
		lblNewLabel_1.setBounds(680, 115, 25, 20);
		pn_kqTimKiem.add(lblNewLabel_1);
		
		JLabel lblMatKhau = new JLabel("*Mật khẩu:");
		lblMatKhau.setHorizontalAlignment(SwingConstants.LEFT);
		lblMatKhau.setFont(new Font("Arial", Font.PLAIN, 11));
		lblMatKhau.setBounds(10, 155, 60, 20);
		pn_kqTimKiem.add(lblMatKhau);
		
		txt_matKhau = new JTextField();
		txt_matKhau.setHorizontalAlignment(SwingConstants.LEFT);
		txt_matKhau.setColumns(10);
		txt_matKhau.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_matKhau.setBackground(new Color(255, 250, 240));
		txt_matKhau.setBounds(70, 155, 100, 20);
		pn_kqTimKiem.add(txt_matKhau);
		
		JPanel pnl_calam = new JPanel();
		pnl_calam.setBackground(new Color(255, 250, 240));
		pnl_calam.setBorder(new MatteBorder(0, 2, 0, 0, (Color) new Color(245, 222, 179)));
		pnl_calam.setBounds(860, 10, 210, 120);
		pn_kqTimKiem.add(pnl_calam);
		pnl_calam.setLayout(null);
		
		JLabel lblCaLam = new JLabel("Ca làm:");
		lblCaLam.setBounds(10, 5, 45, 20);
		pnl_calam.add(lblCaLam);
		
		JComboBox cmb_caLam = new JComboBox();
		cmb_caLam.setBackground(new Color(245, 222, 179));
		cmb_caLam.setBounds(55, 4, 80, 25);
		pnl_calam.add(cmb_caLam);
		
		JLabel lblTGBD = new JLabel("Thời gian bắt đầu:");
		lblTGBD.setBounds(10, 40, 100, 20);
		pnl_calam.add(lblTGBD);
		
		JLabel lbl_kqTGBD = new JLabel("8:00");
		lbl_kqTGBD.setBounds(110, 40, 46, 20);
		pnl_calam.add(lbl_kqTGBD);
		
		JLabel lblTGKT = new JLabel("Thời gian kết thúc:");
		lblTGKT.setBounds(10, 89, 100, 20);
		pnl_calam.add(lblTGKT);
		
		JLabel lbl_kqTGKT = new JLabel("14:00");
		lbl_kqTGKT.setBounds(110, 89, 46, 20);
		pnl_calam.add(lbl_kqTGKT);
		
		JButton btnXoaTrang = new JButton("Xóa trắng");
		btnXoaTrang.setFont(new Font("Arial", Font.BOLD, 14));
		btnXoaTrang.setBackground(new Color(255, 0, 0));
		btnXoaTrang.setBounds(750, 160, 100, 30);
		pn_kqTimKiem.add(btnXoaTrang);
		
				JButton btnThemNV = new JButton("Thêm");
				btnThemNV.setBounds(970, 160, 100, 30);
				pn_kqTimKiem.add(btnThemNV);
				btnThemNV.setFont(new Font("Arial", Font.BOLD, 14));
				btnThemNV.setBackground(new Color(0, 128, 0));

		JPanel pn_dsnv = new JPanel();
		pn_dsnv.setBackground(new Color(255, 255, 255));
		pn_dsnv.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true), "Danh s\u00E1ch nh\u00E2n vi\u00EAn", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(65, 105, 225)));
		pn_dsnv.setBounds(10, 350, 1100, 350);
		add(pn_dsnv);
		pn_dsnv.setLayout(null);

		model_ds = new DefaultTableModel(new Object[][] {
				
		}, new String[] { "Mã nhân viên", "Họ tên", "Ngày sinh", "Giới tính", "Số điện thoại", "Địa chỉ", "Chức vụ", "Ca làm" }

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
		scr_Ds.setBounds(10, 20, 1080, 320);
		scr_Ds.getViewport().setBackground(Color.white);
		pn_dsnv.add(scr_Ds);
		
		JLabel lblTenManHinh = new JLabel("CẬP NHẬT THÔNG TIN NHÂN VIÊN");
		lblTenManHinh.setFont(new Font("Arial", Font.BOLD, 20));
		lblTenManHinh.setForeground(new Color(100, 149, 237));
		lblTenManHinh.setHorizontalAlignment(SwingConstants.CENTER);
		lblTenManHinh.setBounds(0, 0, 1120, 50);
		add(lblTenManHinh);

		

		tbl_Ds.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int selected = tbl_Ds.getSelectedRow();
				
			}
		});

		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
