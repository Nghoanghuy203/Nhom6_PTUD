package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.UtilDateModel;

import connectDB.ConnectDB;
import custom.CustomScrollBarUI;
import custom.GeneratorID;
import custom.RoundedCornerBorder;
import dao.KhachHang_DAO;
import entities.KhachHang;
import javax.swing.DefaultComboBoxModel;

public class ManHinhCapNhatKhachHang extends JPanel implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtSearchKH;
	private JButton btnSearchKH;
	private JScrollPane scr_Ds;
	private JTable tbl_Ds;
	private static DefaultTableModel model_ds;
	private JTextField txt_hoTenKH;
	private JTextField txt_soDienThoai;
	private JTextField txt_diaChi;
	private JComboBox combo_gioiTinh;
	private JButton btnThemKH;
	private JButton btnXoaTrangKH;
	private JButton btnSuaKH;
	private UtilDateModel model_date;
	private JDatePanelImpl datePanel;
	private JTextField txt_maKH;
	private ArrayList<KhachHang> dsKh = new ArrayList<KhachHang>();
	private KhachHang_DAO kh_DAO = new KhachHang_DAO(); 
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

		

		txt_hoTenKH = new JTextField();
		txt_hoTenKH.setBackground(new Color(255, 250, 240));
		txt_hoTenKH.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txt_hoTenKH.setHorizontalAlignment(SwingConstants.LEFT);
		txt_hoTenKH.setBounds(270, 15, 160, 20);
		pn_kqTimKiem.add(txt_hoTenKH);
		txt_hoTenKH.setColumns(10);

		model_date = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		datePanel = new JDatePanelImpl(model_date,p);
		model_date.setDate(2000, 1, 1);

		String[] item_gt = {"Nam","Nữ"};
		combo_gioiTinh = new JComboBox(item_gt);
		combo_gioiTinh.setModel(new DefaultComboBoxModel(new String[] {"Nam", "Nữ"}));
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
		
		btnThemKH = new JButton("Thêm");
		btnThemKH.setBounds(665, 25, 110, 30);
		pn_kqTimKiem.add(btnThemKH);
		btnThemKH.setFont(new Font("Arial", Font.BOLD, 14));
		btnThemKH.setBackground(new Color(0, 128, 0));
		
		btnSuaKH = new JButton("Sửa");
		btnSuaKH.setBounds(792, 25, 110, 30);
		pn_kqTimKiem.add(btnSuaKH);
		btnSuaKH.setFont(new Font("Arial", Font.BOLD, 14));
		btnSuaKH.setBackground(new Color(244, 164, 96));
		
		btnXoaTrangKH = new JButton("Xóa trắng");
		btnXoaTrangKH.setFont(new Font("Arial", Font.BOLD, 14));
		btnXoaTrangKH.setBackground(new Color(255, 0, 0));
		btnXoaTrangKH.setBounds(912, 25, 110, 30);
		pn_kqTimKiem.add(btnXoaTrangKH);

		

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

		}, new String[] { "Mã khách hàng", "Họ tên", "Số điện thoại", "Giới tính", "Địa chỉ"}) {
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

		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		docDuLieu();
		
		btnThemKH.addActionListener(this);
		btnSuaKH.addActionListener(this);
		btnXoaTrangKH.addActionListener(this);
		btnSuaKH.addActionListener(this);
		btnSearchKH.addActionListener(this);
		tbl_Ds.addMouseListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThemKH)) {
			// TODO Auto-generated method stub
			String tenKh = txt_hoTenKH.getText();
			String sdt = txt_soDienThoai.getText();
			boolean gt = combo_gioiTinh.getSelectedItem().toString().equalsIgnoreCase("Nam")?true:false;
			String diaChi = txt_diaChi.getText();
			String maKh = GeneratorID.generateIDKhachHang();
			KhachHang hk = new KhachHang(maKh,tenKh,sdt,gt,diaChi);
			if(kh_DAO.themKH(hk)) {
				Object[] rowData = {maKh,tenKh,sdt,gt?"Nam":"Nữ",diaChi};
				model_ds.addRow(rowData);
				dsKh.add(hk);
				docDuLieu();
				xoaRong();
				ManHinhTimKiemKhachHang.resetData();
			}
			else {
				JOptionPane.showMessageDialog(this, "Lỗi thêm!");
			}
		}
		else if (o.equals (btnSuaKH)) { 
			int row = tbl_Ds.getSelectedRow(); 
			if (row >= 0){
				String tenKh = txt_hoTenKH.getText();
				String sdt = txt_soDienThoai.getText();
				boolean gt = combo_gioiTinh.getSelectedItem().toString().equalsIgnoreCase("Nam")?true:false;
				String diaChi = txt_diaChi.getText();
				String maKh = txt_maKH.getText();
				KhachHang hk = new KhachHang(maKh,tenKh,sdt,gt,diaChi);
				if(kh_DAO.capNhatKhachHang(hk)) {
					tbl_Ds.setValueAt(txt_maKH.getText(), row, 0);
					tbl_Ds.setValueAt(txt_hoTenKH.getText(), row, 1);
					tbl_Ds.setValueAt(txt_soDienThoai.getText(), row, 2);
					tbl_Ds.setValueAt(combo_gioiTinh.getSelectedItem().equals("Nam")?1:0, row, 3);
					tbl_Ds.setValueAt(txt_diaChi.getText(), row, 4);
				}
				
			}
			
			
		}
		else if(o.equals (btnSearchKH)){
			String sdt = txtSearchKH.getText();

			DefaultTableModel model = (DefaultTableModel) tbl_Ds.getModel();
			TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
			tbl_Ds.setRowSorter(sorter);
			List<RowFilter<Object, Object>> filters = new ArrayList<>();
			filters.add(RowFilter.regexFilter(sdt, 2));
			RowFilter<Object, Object> af = RowFilter.andFilter(filters);
			sorter.setRowFilter(af);
		}
		else if (o.equals (btnXoaTrangKH)) {
			xoaRong();
		}
	}
	public void docDuLieu() {
		// TODO Auto-generated method stub
		KhachHang_DAO ds = new KhachHang_DAO(); 
		List<KhachHang> list = ds.getDsKhachHang(); 
		for (KhachHang kh : list) {
			String [] rowData = {kh.getMaKH()
					,kh.getTenKH()
					,kh.getSdtKH()
					,kh.isGioiTinh()?"Nam":"Nữ"
						,kh.getDiaChi()};
			model_ds.addRow(rowData);
		}
		tbl_Ds.setModel (model_ds);
	}
	private void xoaRong() {
		txt_diaChi.setText("");
		txt_hoTenKH.setText("");
		txt_maKH.setText(""); 
		txt_soDienThoai.setText(""); 
		txtSearchKH.setText(""); 
		txt_maKH.requestFocus();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = tbl_Ds.getSelectedRow();
		txt_maKH.setText(tbl_Ds.getValueAt(row, 0).toString());
		txt_hoTenKH.setText(tbl_Ds.getValueAt(row, 1).toString());
		txt_soDienThoai.setText(tbl_Ds.getValueAt(row, 2).toString()); 
		combo_gioiTinh.setSelectedItem(tbl_Ds.getValueAt(row, 3).toString());
		txt_diaChi.setText(tbl_Ds.getValueAt(row, 4).toString()); 
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
	public static void updateData(List<KhachHang> ds) {
		//xoaTrangTable(tbl_Ds);
		for (KhachHang kh : ds) {
			String [] rowData = {kh.getMaKH()
					,kh.getTenKH()
					,kh.getSdtKH()
					,kh.isGioiTinh()?"Nam":"Nữ"
					,kh.getDiaChi()};
			model_ds.addRow(rowData);
		}
	}
	
	public static void resetData() {
		KhachHang_DAO kh_DAO = new KhachHang_DAO();
		List<KhachHang> ds = kh_DAO.getDsKhachHang();
		ManHinhCapNhatKhachHang.updateData(ds);
	}
}
