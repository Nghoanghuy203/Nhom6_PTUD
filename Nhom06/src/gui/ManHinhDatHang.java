package gui;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;

import javax.swing.border.TitledBorder;
import javax.swing.plaf.TableHeaderUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import custom.RoundedCornerBorder;

import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;

public class ManHinhDatHang extends JPanel {

	private JTextField txtSearch;
	private JButton btnSearch;
	
	private JScrollPane jsp_Ds;
	private JTable table_Ds;
	private DefaultTableModel model_ds;

	/**
	 * Create the panel.
	 */
	public ManHinhDatHang() {
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		setSize(1120, 710);
		
		JPanel pn_timKiem = new JPanel();
		pn_timKiem.setBackground(new Color(255, 255, 255));
		pn_timKiem.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true), "T\u00ECm ki\u1EBFm \u0111\u01A1n \u0111\u1EB7t h\u00E0ng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(65, 105, 225)));
		pn_timKiem.setBounds(10, 0, 1100, 200);
		add(pn_timKiem);
		pn_timKiem.setLayout(null);
		
		JPanel timKiem = new JPanel();
		timKiem.setBackground(new Color(255, 255, 255));
		timKiem.setBounds(10, 20, 300, 30);
		timKiem.setBorder(new RoundedCornerBorder());
		pn_timKiem.add(timKiem);
		timKiem.setLayout(null);
		
		txtSearch = new JTextField();
		txtSearch.setText("Nhập số điện thoại khách hàng...");
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
		//btnSearch.setBorder(new RoundedCornerBorder());
		btnSearch.setBackground(new Color(65, 105, 225));
		btnSearch.setIcon(new ImageIcon(ManHinhBanHang.class.getResource("/images/search.png")));
		btnSearch.setBounds(220, 3, 70, 24);
		timKiem.add(btnSearch);
		
		JLabel lblKqTimKiem = new JLabel("Kết quả:");
		lblKqTimKiem.setBounds(10, 60, 100, 14);
		pn_timKiem.add(lblKqTimKiem);
		
		JPanel pn_kqTimKiem = new JPanel();
		pn_kqTimKiem.setLayout(null);
		pn_kqTimKiem.setBackground(new Color(255, 250, 240));
		pn_kqTimKiem.setBounds(10, 79, 1080, 100);
		pn_timKiem.add(pn_kqTimKiem);
		
		JLabel lblMn = new JLabel("Mã đơn:");
		lblMn.setHorizontalAlignment(SwingConstants.LEFT);
		lblMn.setFont(new Font("Arial", Font.PLAIN, 11));
		lblMn.setBounds(10, 15, 50, 20);
		pn_kqTimKiem.add(lblMn);
		
		JLabel lbl_kqMa = new JLabel("DH001");
		lbl_kqMa.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(128, 128, 128)));
		lbl_kqMa.setBounds(60, 15, 80, 20);
		pn_kqTimKiem.add(lbl_kqMa);
		
		JLabel lblTenNV = new JLabel("Nhân viên:");
		lblTenNV.setHorizontalAlignment(SwingConstants.LEFT);
		lblTenNV.setFont(new Font("Arial", Font.PLAIN, 11));
		lblTenNV.setBounds(150, 15, 60, 20);
		pn_kqTimKiem.add(lblTenNV);
		
		JLabel lbl_kqTen = new JLabel("Nguyễn Hoàng Huy");
		lbl_kqTen.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(128, 128, 128)));
		lbl_kqTen.setBounds(210, 15, 140, 20);
		pn_kqTimKiem.add(lbl_kqTen);
		
		JButton btnThem = new JButton("Tiếp tục thanh toán");
		btnThem.setFont(new Font("Arial", Font.BOLD, 14));
		btnThem.setBackground(new Color(0, 128, 0));
		btnThem.setBounds(900, 60, 170, 30);
		pn_kqTimKiem.add(btnThem);
		
		JLabel lblGiaban = new JLabel("Thành tiền:");
		lblGiaban.setHorizontalAlignment(SwingConstants.LEFT);
		lblGiaban.setFont(new Font("Arial", Font.PLAIN, 11));
		lblGiaban.setBounds(710, 15, 60, 20);
		pn_kqTimKiem.add(lblGiaban);
		
		JLabel lbl_kqGiaban = new JLabel("500.000 VND");
		lbl_kqGiaban.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(128, 128, 128)));
		lbl_kqGiaban.setBounds(770, 16, 90, 20);
		pn_kqTimKiem.add(lbl_kqGiaban);
		
		JLabel lblKichco = new JLabel("Ngày đặt:");
		lblKichco.setHorizontalAlignment(SwingConstants.LEFT);
		lblKichco.setFont(new Font("Arial", Font.PLAIN, 11));
		lblKichco.setBounds(580, 15, 50, 20);
		pn_kqTimKiem.add(lblKichco);
		
		JLabel lbl_kqKichco = new JLabel("15-10-2023");
		lbl_kqKichco.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(128, 128, 128)));
		lbl_kqKichco.setBounds(630, 15, 70, 20);
		pn_kqTimKiem.add(lbl_kqKichco);
		
		JLabel lblTnKhchHng = new JLabel("Khách hàng:");
		lblTnKhchHng.setHorizontalAlignment(SwingConstants.LEFT);
		lblTnKhchHng.setFont(new Font("Arial", Font.PLAIN, 11));
		lblTnKhchHng.setBounds(360, 15, 80, 20);
		pn_kqTimKiem.add(lblTnKhchHng);
		
		JLabel lbl_kqMausac = new JLabel("Phạm Thanh Hùng");
		lbl_kqMausac.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(128, 128, 128)));
		lbl_kqMausac.setBounds(430, 15, 140, 20);
		pn_kqTimKiem.add(lbl_kqMausac);
		
		JButton btnGiaoHng = new JButton("Giao hàng");
		btnGiaoHng.setBounds(900, 60, 170, 30);
		pn_kqTimKiem.add(btnGiaoHng);
		btnGiaoHng.setFont(new Font("Arial", Font.BOLD, 14));
		btnGiaoHng.setBackground(new Color(244, 164, 96));
		
		JLabel lblChaThanhTon = new JLabel("Chưa thanh toán");
		lblChaThanhTon.setForeground(new Color(255, 0, 0));
		lblChaThanhTon.setHorizontalAlignment(SwingConstants.RIGHT);
		lblChaThanhTon.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
		lblChaThanhTon.setBounds(970, 10, 100, 30);
		pn_kqTimKiem.add(lblChaThanhTon);
		
		JPanel pn_dsddh = new JPanel();
		pn_dsddh.setBackground(new Color(255, 255, 255));
		pn_dsddh.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true), "Danh s\u00E1ch \u0111\u01A1n \u0111\u1EB7t h\u00E0ng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(65, 105, 225)));
		pn_dsddh.setBounds(10, 200, 1100, 500);
		add(pn_dsddh);
		pn_dsddh.setLayout(null);
		
		
		model_ds = new DefaultTableModel(
			new Object[][] {
				{"DH001","Nguyễn Hoàng Huy","Phạm Thanh Hùng","15-10-2023","Chưa thanh toán","2.000.000 VND"},
				{"DH001","Nguyễn Hoàng Huy","Phạm Thanh Hùng","15-10-2023","Chưa thanh toán","2.000.000 VND"}
			},
			new String [] {
				"Mã đơn","Nhân viên","Khách hàng","Ngày đặt", "Tình trạng thanh toán","Thành tiền"	
			}
			
		) {
			boolean[] canEdit = new boolean [] {
				false,false,false,false,false,false	
			};
			
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return canEdit[column];
			}
		};
		
		table_Ds = new JTable(model_ds);
		table_Ds.setSelectionBackground(new Color(65, 105, 225));
		table_Ds.setRowHeight(40);
		table_Ds.setGridColor(new Color(0, 0, 0));
		
		DefaultTableCellRenderer head_render = new DefaultTableCellRenderer(); 
	    head_render.setBackground(new Color(135, 205, 230));
	    table_Ds.getTableHeader().setDefaultRenderer(head_render);
	    
		jsp_Ds = new JScrollPane();
		jsp_Ds.setViewportView(table_Ds);
		jsp_Ds.setBounds(10, 20, 1080, 470);
		jsp_Ds.getViewport().setBackground(Color.white);
		pn_dsddh.add(jsp_Ds);
		
		
	}
}
