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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import custom.RoundedCornerBorder;
import dao.NhanVien_DAO;
import entities.NhanVien;

public class ManHinhTimKiemNhanVien extends JPanel {

	private JScrollPane scr_Ds;
	private JTable tbl_Ds;
	private DefaultTableModel model_ds;

	private DecimalFormat df;
	private DateTimeFormatter dtf;
	private JTextField txtTimKiem;
	
	private NhanVien_DAO nhanVien_DAO;
	private List<NhanVien> dsNV;

	/**
	 * Create the panel.
	 */
	public ManHinhTimKiemNhanVien() {

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
		dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		nhanVien_DAO = new NhanVien_DAO();
		dsNV = nhanVien_DAO.getDsNhanVien();
		
		JPanel pn_timKiem = new JPanel();
		pn_timKiem.setBackground(new Color(255, 255, 255));
		pn_timKiem.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true),
				"Nh\u1EADp th\u00F4ng tin nh\u00E2n vi\u00EAn", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(65, 105, 225)));
		pn_timKiem.setBounds(10, 50, 1100, 170);
		add(pn_timKiem);
		pn_timKiem.setLayout(null);

		JLabel lbl_thongBaoKq = new JLabel();
		lbl_thongBaoKq.setBounds(60, 60, 100, 14);
		lbl_thongBaoKq.setForeground(Color.red);
		pn_timKiem.add(lbl_thongBaoKq);

		JPanel pn_kqTimKiem = new JPanel();
		pn_kqTimKiem.setLayout(null);
		pn_kqTimKiem.setBackground(new Color(255, 250, 240));
		pn_kqTimKiem.setBounds(10, 20, 1080, 140);
		pn_timKiem.add(pn_kqTimKiem);

		JLabel lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setHorizontalAlignment(SwingConstants.LEFT);
		lblGioiTinh.setFont(new Font("Arial", Font.PLAIN, 11));
		lblGioiTinh.setBounds(510, 70, 50, 20);
		pn_kqTimKiem.add(lblGioiTinh);

		String[] item_gt = { "Tất cả", "Nam", "Nữ" };
		JComboBox cmb_gioiTinh = new JComboBox(item_gt);
		cmb_gioiTinh.setBackground(new Color(245, 222, 179));
		cmb_gioiTinh.setBounds(560, 68, 70, 25);
		pn_kqTimKiem.add(cmb_gioiTinh);

		JLabel lblChucVu = new JLabel("Chức vụ:");
		lblChucVu.setHorizontalAlignment(SwingConstants.LEFT);
		lblChucVu.setFont(new Font("Arial", Font.PLAIN, 11));
		lblChucVu.setBounds(180, 70, 50, 20);
		pn_kqTimKiem.add(lblChucVu);

		String[] item_cv = { "Tất cả", "Nhân viên", "Quản lí" };
		JComboBox cmb_chucVu = new JComboBox(item_cv);
		cmb_chucVu.setBackground(new Color(245, 222, 179));
		cmb_chucVu.setBounds(230, 68, 90, 25);
		pn_kqTimKiem.add(cmb_chucVu);

		JButton btnXoaTrang = new JButton("Xóa trắng");
		btnXoaTrang.setFont(new Font("Arial", Font.BOLD, 14));
		btnXoaTrang.setBackground(new Color(255, 0, 0));
		btnXoaTrang.setBounds(860, 100, 100, 30);
		pn_kqTimKiem.add(btnXoaTrang);

		JButton btnTimNV = new JButton("Tìm");
		btnTimNV.setIcon(new ImageIcon(ManHinhTimKiemNhanVien.class.getResource("/images/icon_timkiem.png")));
		btnTimNV.setBounds(970, 100, 100, 30);
		pn_kqTimKiem.add(btnTimNV);
		btnTimNV.setFont(new Font("Arial", Font.BOLD, 14));
		btnTimNV.setBackground(new Color(65, 105, 225));

		String[] item_cl = { "Tất cả", "Ca 1", "Ca 2" };
		JComboBox cmb_caLam = new JComboBox(item_cl);
		cmb_caLam.setBounds(400, 68, 80, 25);
		pn_kqTimKiem.add(cmb_caLam);
		cmb_caLam.setBackground(new Color(245, 222, 179));

		JLabel lblCaLam = new JLabel("Ca làm:");
		lblCaLam.setBounds(350, 70, 45, 20);
		pn_kqTimKiem.add(lblCaLam);

		JPanel timKiem = new JPanel();
		timKiem.setLayout(null);
		timKiem.setBorder(new RoundedCornerBorder());
		timKiem.setBackground(Color.WHITE);
		timKiem.setBounds(260, 30, 370, 30);
		pn_kqTimKiem.add(timKiem);

		txtTimKiem = new JTextField();
		txtTimKiem.setText("Nhập mã, tên, số điện thoại của  nhân viên...");
		txtTimKiem.setForeground(Color.GRAY);
		txtTimKiem.setEditable(false);
		txtTimKiem.setColumns(10);
		txtTimKiem.setBorder(null);
		txtTimKiem.setBackground(Color.WHITE);
		txtTimKiem.setBounds(10, 3, 350, 24);
		txtTimKiem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				txtTimKiem.setText("");
				txtTimKiem.setForeground(Color.BLACK);
				txtTimKiem.setEditable(true);
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
				txtTimKiem.setText("Nhập mã, tên, số điện thoại của  nhân viên...");
				txtTimKiem.setForeground(Color.GRAY);
			}
		});
		timKiem.add(txtTimKiem);

		JLabel lblNhapThongTin = new JLabel("Nhập thông tin:");
		lblNhapThongTin.setHorizontalAlignment(SwingConstants.LEFT);
		lblNhapThongTin.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNhapThongTin.setBounds(180, 36, 80, 20);
		pn_kqTimKiem.add(lblNhapThongTin);

		JPanel pn_dsnv = new JPanel();
		pn_dsnv.setBackground(new Color(255, 255, 255));
		pn_dsnv.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true),
				"Danh s\u00E1ch k\u1EBFt qu\u1EA3 t\u00ECm ki\u1EBFm", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(65, 105, 225)));
		pn_dsnv.setBounds(10, 220, 1100, 480);
		add(pn_dsnv);
		pn_dsnv.setLayout(null);

		model_ds = new DefaultTableModel(new Object[][] {

		}, new String[] { "Mã nhân viên", "Họ tên", "Ngày sinh", "Giới tính", "Số điện thoại", "Địa chỉ", "Chức vụ",
				"Ca làm" }

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
		scr_Ds.setBounds(10, 20, 1080, 450);
		scr_Ds.getViewport().setBackground(Color.white);
		pn_dsnv.add(scr_Ds);

		JLabel lblTmKimNhn = new JLabel("TÌM KIẾM NHÂN VIÊN");
		lblTmKimNhn.setHorizontalAlignment(SwingConstants.CENTER);
		lblTmKimNhn.setForeground(new Color(100, 149, 237));
		lblTmKimNhn.setFont(new Font("Arial", Font.BOLD, 20));
		lblTmKimNhn.setBounds(0, 0, 1120, 50);
		add(lblTmKimNhn);
		
		updateTable();

		tbl_Ds.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int selected = tbl_Ds.getSelectedRow();

			}
		});
		
		btnTimNV.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				txtTimKiem.setText("Nhập mã, tên, số điện thoại của  nhân viên...");
				txtTimKiem.setForeground(Color.GRAY);
			}
		});
		btnXoaTrang.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				txtTimKiem.setText("Nhập mã, tên, số điện thoại của  nhân viên...");
				txtTimKiem.setForeground(Color.GRAY);
				cmb_caLam.setSelectedIndex(0);
				cmb_chucVu.setSelectedIndex(0);
				cmb_gioiTinh.setSelectedIndex(0);
			}
		});
	}
	private void xoaTable() {
        DefaultTableModel dtm = (DefaultTableModel) tbl_Ds.getModel();
        dtm.getDataVector().removeAllElements();
    }
	private void updateTable() {
		xoaTable();
		dsNV = nhanVien_DAO.getDsNhanVien();
		for (NhanVien nv : dsNV) {
			Object data[] = {nv.getMaNV(), nv.getTenNV(), dtf.format(nv.getNgaySinh()), nv.isGioiTinh()?"Nam":"Nữ", nv.getSdt(), nv.getDiaChi(), nv.getChucVu(), nv.getCaLamViec()};
			model_ds.addRow(data);
		}
	}
}
