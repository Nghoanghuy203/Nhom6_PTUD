package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

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
import javax.swing.border.MatteBorder;

public class ManHinhThongKeDoanhThu extends JPanel {

	private JScrollPane scr_Ds;
	private JTable tbl_Ds;
	private DefaultTableModel model_ds;

	private DecimalFormat df;

	/**
	 * Create the panel.
	 */
	public ManHinhThongKeDoanhThu() {

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

		

		JPanel pn_timKiem = new JPanel();
		pn_timKiem.setBackground(new Color(255, 255, 255));
		pn_timKiem.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true), "Ti\u00EAu ch\u00ED th\u1ED1ng k\u00EA", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(65, 105, 225)));
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

		JLabel lblGioiTinh = new JLabel("Năm:");
		lblGioiTinh.setHorizontalAlignment(SwingConstants.LEFT);
		lblGioiTinh.setFont(new Font("Arial", Font.PLAIN, 11));
		lblGioiTinh.setBounds(520, 40, 40, 20);
		pn_kqTimKiem.add(lblGioiTinh);
		
		JComboBox cmb_gioiTinh = new JComboBox();
		cmb_gioiTinh.setBackground(new Color(245, 222, 179));
		cmb_gioiTinh.setBounds(560, 38, 70, 25);
		pn_kqTimKiem.add(cmb_gioiTinh);
		
		JLabel lblChucVu = new JLabel("Ngày:");
		lblChucVu.setHorizontalAlignment(SwingConstants.LEFT);
		lblChucVu.setFont(new Font("Arial", Font.PLAIN, 11));
		lblChucVu.setBounds(180, 40, 40, 20);
		pn_kqTimKiem.add(lblChucVu);
		
		JComboBox cmb_chucVu = new JComboBox();
		cmb_chucVu.setBackground(new Color(245, 222, 179));
		cmb_chucVu.setBounds(220, 38, 70, 25);
		pn_kqTimKiem.add(cmb_chucVu);
		
				JButton btnTimNV = new JButton("Thống kê");
				btnTimNV.setIcon(null);
				btnTimNV.setBounds(680, 35, 100, 30);
				pn_kqTimKiem.add(btnTimNV);
				btnTimNV.setFont(new Font("Arial", Font.BOLD, 14));
				btnTimNV.setBackground(new Color(148, 0, 211));
				
				JComboBox cmb_caLam = new JComboBox();
				cmb_caLam.setBounds(390, 38, 70, 25);
				pn_kqTimKiem.add(cmb_caLam);
				cmb_caLam.setBackground(new Color(245, 222, 179));
				
				JLabel lblCaLam = new JLabel("Tháng:");
				lblCaLam.setBounds(350, 40, 40, 20);
				pn_kqTimKiem.add(lblCaLam);

		JPanel pn_dsnv = new JPanel();
		pn_dsnv.setBackground(new Color(255, 255, 255));
		pn_dsnv.setBounds(10, 220, 550, 480);
		add(pn_dsnv);
		pn_dsnv.setLayout(null);

		model_ds = new DefaultTableModel(new Object[][] {
				
		}, new String[] { "Mã đơn", "Nhân viên", "Khách hàng", "Ngày lặp", "Thành tiền" }

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
		scr_Ds.setBounds(10, 50, 530, 420);
		scr_Ds.getViewport().setBackground(Color.white);
		pn_dsnv.add(scr_Ds);
		
		JLabel lblNewLabel = new JLabel("Danh sách hóa đơn");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel.setForeground(new Color(65, 105, 225));
		lblNewLabel.setBounds(0, 0, 550, 50);
		pn_dsnv.add(lblNewLabel);
		
		JLabel lblTmKimNhn = new JLabel("THỐNG KÊ DOANH THU");
		lblTmKimNhn.setHorizontalAlignment(SwingConstants.CENTER);
		lblTmKimNhn.setForeground(new Color(100, 149, 237));
		lblTmKimNhn.setFont(new Font("Arial", Font.BOLD, 20));
		lblTmKimNhn.setBounds(0, 0, 1120, 50);
		add(lblTmKimNhn);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new MatteBorder(0, 2, 0, 0, (Color) new Color(245, 222, 179)));
		panel.setBounds(560, 220, 550, 480);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblBiuDoanh = new JLabel("Biểu đồ doanh thu");
		lblBiuDoanh.setBounds(0, 0, 550, 50);
		lblBiuDoanh.setHorizontalAlignment(SwingConstants.CENTER);
		lblBiuDoanh.setForeground(new Color(65, 105, 225));
		lblBiuDoanh.setFont(new Font("Arial", Font.BOLD, 16));
		panel.add(lblBiuDoanh);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(ManHinhThongKeDoanhThu.class.getResource("/images/doanhthu.png")));
		lblNewLabel_1.setBounds(10, 50, 530, 420);
		panel.add(lblNewLabel_1);

		

		tbl_Ds.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int selected = tbl_Ds.getSelectedRow();
				
			}
		});
	}
}
