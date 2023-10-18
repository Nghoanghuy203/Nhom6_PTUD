package gui;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import custom.CustomScrollBarUI;
import custom.RoundedCornerBorder;
import custom.ScaledImg;
import custom.cell.TableActionCellEditor;
import custom.cell.TableActionCellRender;
import custom.cell.TableActionEvent;

import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;

import javax.swing.border.MatteBorder;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class ManHinhBanHang extends JPanel {
	private JPanel pn_timKiem;
	private JPanel timKiem;
	private JTextField txtSearch;
	private JTextField txt_timkiemKH;
	private JButton btnSearch;
	
	private JLabel lbl_kqMa;
	private JLabel lbl_kqTen;
	private JLabel lbl_kqGiaban;
	private JLabel lbl_kqKichco;
	private JLabel lbl_kqMausac;
	private JSpinner spn_soluong;
	
	private JButton btnThem;
	private JButton btn_timKH;
	private JButton btn_themKH;
	
	private JLabel lbl_kqTenKH;
	private JLabel lbl_kqTongtien;
	@SuppressWarnings("rawtypes")
	private JComboBox cbo_khuyenmai;
	private JLabel lbl_ptkm;
	
	private JLabel lbl_kqtongthanhtoan;
	private JLabel lbl_kqtienkhachtra;
	private JLabel lbl_kqtienthua;
	
	private JButton btn_dathang;
	private JButton btn_thanhtoan;

	private JTable table;
	private JButton btnSua;
	private JLabel lblChatLieu;
	private JLabel lbl_kqChatLieu;
	private JLabel lblNCC;
	private JLabel lbl_kqNCC;
	/**
	 * Create the panel.
	 */

	
	
	@SuppressWarnings({ "serial", "rawtypes" })
	public ManHinhBanHang() {
		setBackground(new Color(255, 255, 255));
		setAutoscrolls(true);
		setSize(1120, 710);
		setLayout(null);
		
		pn_timKiem = new JPanel();
		pn_timKiem.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true), "T\u00ECm ki\u1EBFm s\u1EA3n ph\u1EA9m", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(65, 105, 225)));
		pn_timKiem.setBackground(new Color(255, 255, 255));
		pn_timKiem.setBounds(10, 0, 1100, 200);
		add(pn_timKiem);
		pn_timKiem.setLayout(null);
		
		timKiem = new JPanel();
		timKiem.setBackground(new Color(255, 255, 255));
		timKiem.setBounds(10, 20, 300, 30);
		timKiem.setBorder(new RoundedCornerBorder());
		pn_timKiem.add(timKiem);
		timKiem.setLayout(null);
		
		txtSearch = new JTextField();
		txtSearch.setText("Nhập mã sản phẩm ...");
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
		pn_kqTimKiem.setBackground(new Color(255, 250, 240));
		pn_kqTimKiem.setBounds(10, 80, 1080, 100);
		pn_timKiem.add(pn_kqTimKiem);
		pn_kqTimKiem.setLayout(null);
		
		JLabel lblMa = new JLabel("Mã sản phẩm:");
		lblMa.setFont(new Font("Arial", Font.PLAIN, 11));
		lblMa.setHorizontalAlignment(SwingConstants.LEFT);
		lblMa.setBounds(10, 20, 70, 20);
		pn_kqTimKiem.add(lblMa);
		
		lbl_kqMa = new JLabel("SP001");
		lbl_kqMa.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(128, 128, 128)));
		lbl_kqMa.setBounds(80, 20, 80, 20);
		pn_kqTimKiem.add(lbl_kqMa);
		
		JLabel lblTen = new JLabel("Tên sản phẩm:");
		lblTen.setHorizontalAlignment(SwingConstants.LEFT);
		lblTen.setFont(new Font("Arial", Font.PLAIN, 11));
		lblTen.setBounds(180, 15, 74, 20);
		pn_kqTimKiem.add(lblTen);
		
		lbl_kqTen = new JLabel("Áo sơ thun basic");
		lbl_kqTen.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(128, 128, 128)));
		lbl_kqTen.setBounds(255, 15, 196, 20);
		pn_kqTimKiem.add(lbl_kqTen);
		
		btnThem = new JButton("Thêm");
		btnThem.setBackground(new Color(0, 128, 0));
		btnThem.setFont(new Font("Arial", Font.BOLD, 14));
		btnThem.setIcon(new ImageIcon(ManHinhBanHang.class.getResource("/images/them.png")));
		btnThem.setBounds(960, 60, 110, 30);
		pn_kqTimKiem.add(btnThem);
		
		JLabel lblSoluong = new JLabel("Số lượng:");
		lblSoluong.setHorizontalAlignment(SwingConstants.LEFT);
		lblSoluong.setFont(new Font("Arial", Font.PLAIN, 11));
		lblSoluong.setBounds(400, 60, 50, 20);
		pn_kqTimKiem.add(lblSoluong);
		
		spn_soluong = new JSpinner(new SpinnerNumberModel(1,1,10,1));
		spn_soluong.getModel().setValue(1);
		spn_soluong.setBounds(450, 55, 50, 30);
		pn_kqTimKiem.add(spn_soluong);
		
		JLabel lblGiaban = new JLabel("Giá bán:");
		lblGiaban.setHorizontalAlignment(SwingConstants.LEFT);
		lblGiaban.setFont(new Font("Arial", Font.PLAIN, 11));
		lblGiaban.setBounds(250, 60, 40, 20);
		pn_kqTimKiem.add(lblGiaban);
		
		lbl_kqGiaban = new JLabel("500.000 VND");
		lbl_kqGiaban.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(128, 128, 128)));
		lbl_kqGiaban.setBounds(300, 60, 90, 20);
		pn_kqTimKiem.add(lbl_kqGiaban);
		
		JLabel lblKichco = new JLabel("Kích cỡ:");
		lblKichco.setHorizontalAlignment(SwingConstants.LEFT);
		lblKichco.setFont(new Font("Arial", Font.PLAIN, 11));
		lblKichco.setBounds(732, 15, 40, 20);
		pn_kqTimKiem.add(lblKichco);
		
		lbl_kqKichco = new JLabel("XL");
		lbl_kqKichco.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(128, 128, 128)));
		lbl_kqKichco.setBounds(776, 15, 50, 20);
		pn_kqTimKiem.add(lbl_kqKichco);
		
		JLabel lblMausac = new JLabel("Màu sắc:");
		lblMausac.setHorizontalAlignment(SwingConstants.LEFT);
		lblMausac.setFont(new Font("Arial", Font.PLAIN, 11));
		lblMausac.setBounds(610, 15, 50, 20);
		pn_kqTimKiem.add(lblMausac);
		
		lbl_kqMausac = new JLabel("Trắng");
		lbl_kqMausac.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(128, 128, 128)));
		lbl_kqMausac.setBounds(662, 15, 60, 20);
		pn_kqTimKiem.add(lbl_kqMausac);
		
		btnSua = new JButton("Sửa");
		btnSua.setIcon(new ImageIcon(ManHinhBanHang.class.getResource("/images/sua.png")));
		btnSua.setFont(new Font("Arial", Font.BOLD, 14));
		btnSua.setBackground(new Color(255, 140, 0));
		btnSua.setBounds(840, 60, 110, 30);
		pn_kqTimKiem.add(btnSua);
		btnSua.setEnabled(false);
		
		
		lblChatLieu = new JLabel("Chất liệu:");
		lblChatLieu.setHorizontalAlignment(SwingConstants.LEFT);
		lblChatLieu.setFont(new Font("Arial", Font.PLAIN, 11));
		lblChatLieu.setBounds(470, 15, 50, 20);
		pn_kqTimKiem.add(lblChatLieu);
		
		lbl_kqChatLieu = new JLabel("Vải thun");
		lbl_kqChatLieu.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(128, 128, 128)));
		lbl_kqChatLieu.setBounds(520, 15, 80, 20);
		pn_kqTimKiem.add(lbl_kqChatLieu);
		
		lblNCC = new JLabel("Nhà cung cấp:");
		lblNCC.setHorizontalAlignment(SwingConstants.LEFT);
		lblNCC.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNCC.setBounds(10, 60, 80, 20);
		pn_kqTimKiem.add(lblNCC);
		
		lbl_kqNCC = new JLabel("IUHJHSJ");
		lbl_kqNCC.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(128, 128, 128)));
		lbl_kqNCC.setBounds(90, 60, 150, 20);
		pn_kqTimKiem.add(lbl_kqNCC);
		
		JPanel pn_thongTinHD = new JPanel();
		pn_thongTinHD.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true), "Th\u00F4ng tin h\u00F3a \u0111\u01A1n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(65, 105, 225)));
		pn_thongTinHD.setBackground(new Color(255, 255, 255));
		pn_thongTinHD.setBounds(780, 200, 330, 500);
		add(pn_thongTinHD);
		pn_thongTinHD.setLayout(null);
		
		JPanel pn_KH = new JPanel();
		pn_KH.setBackground(new Color(255, 255, 255));
		pn_KH.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		pn_KH.setBounds(15, 20, 300, 30);
		pn_thongTinHD.add(pn_KH);
		pn_KH.setLayout(null);
		
		txt_timkiemKH = new JTextField();
		txt_timkiemKH.setBorder(null);
		txt_timkiemKH.setToolTipText("");
		txt_timkiemKH.setForeground(new Color(192, 192, 192));
		txt_timkiemKH.setText("Nhập số điện thoại khách hàng ...");
		txt_timkiemKH.setBounds(5, 5, 190, 20);
		pn_KH.add(txt_timkiemKH);
		txt_timkiemKH.setColumns(10);
		
		btn_timKH = new JButton("");
		btn_timKH.setHorizontalAlignment(SwingConstants.CENTER);
		btn_timKH.setIcon(new ImageIcon(ManHinhBanHang.class.getResource("/images/search.png")));
		btn_timKH.setBounds(210, 5, 40, 20);
		pn_KH.add(btn_timKH);
		
		btn_themKH = new JButton("");
		btn_themKH.setIcon(new ImageIcon(ManHinhBanHang.class.getResource("/images/icon_themKH.png")));
		btn_themKH.setHorizontalAlignment(SwingConstants.CENTER);
		btn_themKH.setBounds(260, 5, 40, 20);
		pn_KH.add(btn_themKH);
		
		JLabel lbl_tenKH = new JLabel("Khách hàng:");
		lbl_tenKH.setBounds(15, 60, 90, 20);
		pn_thongTinHD.add(lbl_tenKH);
		
		lbl_kqTenKH = new JLabel("Khách lẻ");
		lbl_kqTenKH.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_kqTenKH.setBounds(125, 60, 190, 20);
		pn_thongTinHD.add(lbl_kqTenKH);
		
		JLabel lbl_Tongtien = new JLabel("Tổng tiền:");
		lbl_Tongtien.setBounds(15, 110, 90, 20);
		pn_thongTinHD.add(lbl_Tongtien);
		
		lbl_kqTongtien = new JLabel("0 VND");
		lbl_kqTongtien.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_kqTongtien.setBounds(125, 110, 190, 20);
		pn_thongTinHD.add(lbl_kqTongtien);
		
		JLabel lbl_khuyenmai = new JLabel("Mã khuyến mãi:");
		lbl_khuyenmai.setBounds(15, 160, 90, 20);
		pn_thongTinHD.add(lbl_khuyenmai);
		
		cbo_khuyenmai = new JComboBox();
		cbo_khuyenmai.setBorder(null);
		cbo_khuyenmai.setBackground(new Color(255, 255, 255));
		cbo_khuyenmai.setBounds(125, 160, 190, 20);
		pn_thongTinHD.add(cbo_khuyenmai);
		
		lbl_ptkm = new JLabel("Khuyến mãi 0%");
		lbl_ptkm.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lbl_ptkm.setForeground(new Color(255, 0, 0));
		lbl_ptkm.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_ptkm.setBounds(125, 180, 190, 20);
		pn_thongTinHD.add(lbl_ptkm);
		
		JLabel lbl_tongthanhtoan = new JLabel("Tổng thanh toán:");
		lbl_tongthanhtoan.setBounds(15, 210, 100, 20);
		pn_thongTinHD.add(lbl_tongthanhtoan);
		
		lbl_kqtongthanhtoan = new JLabel("0 VND");
		lbl_kqtongthanhtoan.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_kqtongthanhtoan.setBounds(125, 210, 190, 20);
		pn_thongTinHD.add(lbl_kqtongthanhtoan);
		
		JLabel lbl_tienkhachtra = new JLabel("Tiền khách trả:");
		lbl_tienkhachtra.setBounds(15, 260, 90, 20);
		pn_thongTinHD.add(lbl_tienkhachtra);
		
		lbl_kqtienkhachtra = new JLabel("0 VND");
		lbl_kqtienkhachtra.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_kqtienkhachtra.setBounds(125, 260, 190, 20);
		pn_thongTinHD.add(lbl_kqtienkhachtra);
		
		JLabel lbl_thienthua = new JLabel("Tiền thừa trả khách:");
		lbl_thienthua.setBounds(15, 310, 120, 20);
		pn_thongTinHD.add(lbl_thienthua);
		
		lbl_kqtienthua = new JLabel("0 VND");
		lbl_kqtienthua.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_kqtienthua.setBounds(125, 310, 190, 20);
		pn_thongTinHD.add(lbl_kqtienthua);
		
		btn_dathang = new JButton("Đặt hàng");
		btn_dathang.setBackground(new Color(255, 127, 80));
		btn_dathang.setBounds(15, 360, 130, 30);
		pn_thongTinHD.add(btn_dathang);
		
		btn_thanhtoan = new JButton("Thanh toán");
		btn_thanhtoan.setBackground(new Color(255, 127, 80));
		btn_thanhtoan.setBounds(185, 360, 130, 30);
		pn_thongTinHD.add(btn_thanhtoan);
		
		
		//
		JPanel pn_DSGioHang = new JPanel();
		pn_DSGioHang.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true), "Gi\u1ECF h\u00E0ng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(65, 105, 225)));
		pn_DSGioHang.setBackground(new Color(255, 255, 255));
		pn_DSGioHang.setBounds(10, 200, 760, 500);
		add(pn_DSGioHang);
		
		JScrollPane jsp_giohang = new JScrollPane();
		jsp_giohang.getVerticalScrollBar().setUI(new CustomScrollBarUI());
		jsp_giohang.getVerticalScrollBar().setPreferredSize(new Dimension(8,300));
		table = new JTable();
		table.setBackground(Color.white);
		/*
		table.getTableHeader().setOpaque(true);
		table.getTableHeader().setBackground(Color.white);
		table.getTableHeader().setFont(new Font("Segeo UI", Font.BOLD, 12));
		*/
		
		ImageIcon im = new ImageIcon(ManHinhBanHang.class.getResource("/images/ao.jpg"));
		
		table.setModel(new DefaultTableModel(
	            new Object [][] {
	            	
	            	{ im, "Áo sơ mi tay dài", 1, "500.000", "500.000",null },
					{ im, "Quần tây đen", 2, "350.000", "700.000"},
					{ "3", "Áo thun basic", 1, "200.000", "200.000"},
					{ im, "Áo sơ mi tay dài", 1, "500.000", "500.000",null },
					{ "2", "Quần tây đen", 2, "350.000", "700.000"},
					{ "3", "Áo thun basic", 1, "200.000", "200.000"},
					{ im, "Áo sơ mi tay dài", 1, "500.000", "500.000",null },
					{ "2", "Quần tây đen", 2, "350.000", "700.000"},
					{ "3", "Áo thun basic", 1, "200.000", "200.000"}
	            	
	            },
	            new String [] {
	                "Hình ảnh", "Tên", "Số lượng", "Giá bán","Thành tiền",""
	            }
	        ) {
	            boolean[] canEdit = new boolean [] {
	                false, false, false, false, false, true
	            };

	            public boolean isCellEditable(int rowIndex, int columnIndex) {
	                return canEdit [columnIndex];
	            }
	        });
		table.setRowHeight(80);
		table.setSelectionBackground(new java.awt.Color(65, 105, 225));
		
		table.getColumnModel().getColumn(0).setMinWidth(50);
		table.getColumnModel().getColumn(1).setMinWidth(200);
		table.getColumnModel().getColumn(2).setMinWidth(70);
		table.getColumnModel().getColumn(3).setMinWidth(70);
		table.getColumnModel().getColumn(4).setMinWidth(70);
		table.getColumnModel().getColumn(5).setMaxWidth(90);
		
		DefaultTableCellRenderer head_render = new DefaultTableCellRenderer(); 
	    head_render.setBackground(new Color(135, 205, 230));
	    table.getTableHeader().setDefaultRenderer(head_render);
		
		jsp_giohang.setViewportView(table);
		jsp_giohang.getViewport().setBackground(new Color(255,255,255));
		
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(pn_DSGioHang);
        pn_DSGioHang.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jsp_giohang, javax.swing.GroupLayout.DEFAULT_SIZE, 818, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jsp_giohang, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
                .addContainerGap())
        );
        
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
            	btnSua.setEnabled(true);
            	btnThem.setEnabled(false);
                System.out.println("Edit row : " + row);
            }

            @Override
            public void onDelete(int row) {
                if (table.isEditing()) {
                    table.getCellEditor().stopCellEditing();
                }
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.removeRow(row);
            }

            @Override
            public void onView(int row) {
                System.out.println("View row : " + row);
            }
        };
        table.getColumnModel().getColumn(5).setCellRenderer(new TableActionCellRender());
        table.getColumnModel().getColumn(5).setCellEditor(new TableActionCellEditor(event));
        table.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
                setHorizontalAlignment(SwingConstants.CENTER);          
                
                Component com = super.getTableCellRendererComponent(table, o, bln, bln1, i, i1);
                
                if (o == null) {
                	Icon image = new ImageIcon(ManHinhBanHang.class.getResource("/images/null.jpg"));
            		Image ima = ((ImageIcon) image).getImage();
            		Image newimg = ima.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
            		image = new ImageIcon(newimg);
            		JLabel label = new JLabel(image);
                	label.setHorizontalAlignment(JLabel.CENTER);
            		label.setOpaque(true);
                	label.setBackground(com.getBackground());
                	return label;
                } 
                else {
                	if (o instanceof Icon) {
                		Icon image = (ImageIcon)o;
                		Image ima = ((ImageIcon) image).getImage(); 
                		Image newimg = ima.getScaledInstance(70, 70,Image.SCALE_SMOOTH); 
                		image = new ImageIcon(newimg); 
                		JLabel label = new JLabel(image);
                		label.setHorizontalAlignment(JLabel.CENTER);
                		label.setOpaque(true);
                		label.setBackground(com.getBackground());
                		return label;
                	} 
                	else {
                		Icon image = new ImageIcon(ManHinhBanHang.class.getResource("/images/null.jpg"));
                		Image ima = ((ImageIcon) image).getImage();
                		Image newimg = ima.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
                		image = new ImageIcon(newimg);
                		JLabel label = new JLabel(image);
                    	label.setHorizontalAlignment(JLabel.CENTER);
                		label.setOpaque(true);
                    	label.setBackground(com.getBackground());
                    	return label;
                	}
                }
                
            }
        });
        
        btnSua.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				btnSua.setEnabled(false);
				btnThem.setEnabled(true);
			}
		});
	}
	
}
