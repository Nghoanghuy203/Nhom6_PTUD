package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import custom.CustomScrollBarUI;
import custom.barChart.ThongKeBanChay;
import custom.barChart.ThongKeTonKho;
import dao.ChiTietHoaDon_DAO;
import dao.LoaiSanPham_DAO;
import dao.SanPham_DAO;
import entities.LoaiSanPham;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.swing.ScrollPaneConstants;


public class ManHinhThongKeTonKho extends JPanel{
	private JScrollPane scr_Ds;
	public static JTable tbl_Ds;
	public static DefaultTableModel model_ds;


	private JComboBox<String> cmb_tieuChi;

	private SanPham_DAO sp_DAO;
	private LoaiSanPham_DAO loaiSanPham_DAO = new LoaiSanPham_DAO();
	public static List<ThongKeTonKho> dstk;
	private BieuDoTonKho chart;
	private JPanel pn_bieuDo;
	/**
	 * Create the panel.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ManHinhThongKeTonKho() {
		/**
		 * Create the panel.
		 */

		setBackground(new Color(255, 255, 255));
		setLayout(null);
		setSize(1120, 730);

		try {
			ConnectDB.getInstance().connect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}


		sp_DAO = new SanPham_DAO();

		JPanel pn_timKiem = new JPanel();
		pn_timKiem.setBackground(new Color(255, 255, 255));
		pn_timKiem.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 1, true),
				"Ti\u00EAu ch\u00ED th\u1ED1ng k\u00EA", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(65, 105, 225)));
		pn_timKiem.setBounds(10, 50, 1100, 80);
		add(pn_timKiem);
		pn_timKiem.setLayout(null);

		JLabel lbl_thongBaoKq = new JLabel();
		lbl_thongBaoKq.setBounds(60, 60, 100, 14);
		lbl_thongBaoKq.setForeground(Color.red);
		pn_timKiem.add(lbl_thongBaoKq);

		JPanel pn_kqTimKiem = new JPanel();
		pn_kqTimKiem.setLayout(null);
		pn_kqTimKiem.setBackground(new Color(255, 250, 240));
		pn_kqTimKiem.setBounds(10, 20, 1080, 50);
		pn_timKiem.add(pn_kqTimKiem);

		JLabel lblTieuChi = new JLabel("Tiêu chí:");
		lblTieuChi.setHorizontalAlignment(SwingConstants.LEFT);
		lblTieuChi.setFont(new Font("Arial", Font.PLAIN, 11));
		lblTieuChi.setBounds(10, 10, 50, 30);
		pn_kqTimKiem.add(lblTieuChi);



		String[] loai = capNhatCmbLoai();
		cmb_tieuChi = new JComboBox();
		cmb_tieuChi.setModel(new DefaultComboBoxModel(loai));
		cmb_tieuChi.setBackground(new Color(245, 222, 179));
		cmb_tieuChi.setBounds(60, 10, 150, 30);
		pn_kqTimKiem.add(cmb_tieuChi);
		
		JButton btnXuatBaoCao = new JButton("Xuất báo cáo");
		btnXuatBaoCao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnXuatBaoCao.setBounds(239, 14, 139, 23);
		pn_kqTimKiem.add(btnXuatBaoCao);

		JButton btnTimNV = new JButton("Thống kê");
		btnTimNV.setIcon(null);
		btnTimNV.setBounds(970, 9, 100, 30);
		//pn_kqTimKiem.add(btnTimNV);
		btnTimNV.setFont(new Font("Arial", Font.BOLD, 14));
		btnTimNV.setBackground(new Color(147, 112, 219));


		JPanel pn_dsnv = new JPanel();
		pn_dsnv.setBackground(new Color(255, 255, 255));
		pn_dsnv.setBounds(10, 519, 1100, 200);
		add(pn_dsnv);
		pn_dsnv.setLayout(null);

		model_ds = new DefaultTableModel(new Object[][] {

		}, new String[] { "Mã Sản Phẩm","Tên Sản Phẩm","Số Lượng" }

				) {
			boolean[] canEdit = new boolean[] { false, false, false };

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
		scr_Ds.setBounds(10, 30, 1080, 160);
		scr_Ds.getViewport().setBackground(Color.white);
		pn_dsnv.add(scr_Ds);

		JLabel lblDs_SP = new JLabel("Danh sách sản phẩm");
		lblDs_SP.setHorizontalAlignment(SwingConstants.CENTER);
		lblDs_SP.setFont(new Font("Arial", Font.BOLD, 16));
		lblDs_SP.setForeground(new Color(65, 105, 225));
		lblDs_SP.setBounds(0, 0, 1100, 30);
		pn_dsnv.add(lblDs_SP);

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

		JLabel lblTmKimNhn = new JLabel("THỐNG KÊ TỒN KHO");
		lblTmKimNhn.setHorizontalAlignment(SwingConstants.CENTER);
		lblTmKimNhn.setForeground(new Color(100, 149, 237));
		lblTmKimNhn.setFont(new Font("Arial", Font.BOLD, 20));
		lblTmKimNhn.setBounds(20, 0, 1080, 50);
		add(lblTmKimNhn);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(245, 222, 179)));
		panel.setBounds(10, 133, 1100, 386);
		add(panel);
		panel.setLayout(null);

		JLabel lblBieuDoTonKho = new JLabel("Biểu đồ số lượng tồn kho");
		lblBieuDoTonKho.setBounds(0, 0, 1100, 30);
		lblBieuDoTonKho.setHorizontalAlignment(SwingConstants.CENTER);
		lblBieuDoTonKho.setForeground(new Color(65, 105, 225));
		lblBieuDoTonKho.setFont(new Font("Arial", Font.BOLD, 16));
		panel.add(lblBieuDoTonKho);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(ManHinhThongKeDoanhThu.class.getResource("/images/doanhthu.png")));
		lblNewLabel_1.setBounds(10, 30, 1080, 310);
		//panel.add(lblNewLabel_1);

		pn_bieuDo = new JPanel();
		pn_bieuDo.setBounds(10, 30, 1090, 309);
		//panel.add(pn_bieuDo);
		pn_bieuDo.setLayout(null);

		dstk = sp_DAO.getDSTonKho();
		

		JScrollPane scroll_chart = new JScrollPane();
		scroll_chart.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scroll_chart.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll_chart.setBounds(10, 30, 1080, 345);
		panel.add(scroll_chart);
		//pn_bieuDo.add(scroll_chart);
		chart = new BieuDoTonKho();
		scroll_chart.setViewportView(chart);
		scroll_chart.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
		scroll_chart.getVerticalScrollBar().setUI(new CustomScrollBarUI());
		chart.ThongKeKho(dstk);
		
		updateTable(dstk);
		cmb_tieuChi.getModel().addListDataListener(new ListDataListener() {

			@Override
			public void intervalRemoved(ListDataEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void intervalAdded(ListDataEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void contentsChanged(ListDataEvent e) {
				// TODO Auto-generated method stub
				String tc = cmb_tieuChi.getSelectedItem().toString();
				tc = tc.equalsIgnoreCase("")?"Tất cả":tc;
				if(tc.equalsIgnoreCase("Tất cả")) {
					/*
					pn_bieuDo.removeAll();
					pn_bieuDo.remove(scroll_chart);
					pn_bieuDo.add(chart);
					panel.add(pn_bieuDo);
					*/
					chart = new BieuDoTonKho();
					scroll_chart.revalidate();
					scroll_chart.setViewportView(chart);
					
					//pn_bieuDo.add(scroll_chart);
					chart.ThongKeKho(dstk);
					updateTable(dstk);
				}
				else {
					/*
					pn_bieuDo.removeAll();
					pn_bieuDo.remove(scroll_chart);
					pn_bieuDo.add(chart);
					panel.add(pn_bieuDo);
					*/
					dstk = sp_DAO.getDSTonKhoTheoTieuChi(tc);
					//pn_bieuDo.add(scroll_chart);
					scroll_chart.revalidate();;
					chart = new BieuDoTonKho();
					scroll_chart.setViewportView(chart);
					
					chart.ThongKeKho(dstk);

					updateTable(dstk);

				}


			}
		});
		btnXuatBaoCao.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	String tc = cmb_tieuChi.getSelectedItem().toString();
				tc = tc.equalsIgnoreCase("")?"Tất cả":tc;
				xuatBaoCaoExcelTheoTieuChi(tc);
		    }
		});
	}


	private void xoaTrangTable(JTable t) {
		DefaultTableModel dm = (DefaultTableModel) t.getModel();
		dm.getDataVector().removeAllElements();
	}
	private void updateTable(List<ThongKeTonKho> ds) {
		xoaTrangTable(tbl_Ds);
		tbl_Ds.revalidate();

		for (ThongKeTonKho tk : ds) {
			Object data[] = {tk.getMaSP(),tk.getTenSP(),tk.getTongSoLuong()};
			model_ds.addRow(data);
		}
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


	private void xuatBaoCaoExcelTheoTieuChi(String tieuChi) {
	    try {
	        JFileChooser fileChooser = new JFileChooser();
	        fileChooser.setDialogTitle("Chọn vị trí lưu file Excel");
	        int userSelection = fileChooser.showSaveDialog(null);

	        if (userSelection == JFileChooser.APPROVE_OPTION) {
	            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
	            if (!filePath.endsWith(".xlsx")) {
	                filePath += ".xlsx";
	            }
	            
	            Workbook workbook = new XSSFWorkbook();
	            Sheet sheet = workbook.createSheet("Danh Sách Sản Phẩm");

	            Row headerRow = sheet.createRow(0);
	            String[] headers = {"Mã Sản Phẩm", "Tên Sản Phẩm", "Số Lượng"};
	            for (int i = 0; i < headers.length; i++) {
	                Cell cell = headerRow.createCell(i);
	                cell.setCellValue(headers[i]);
	            }

	            List<ThongKeTonKho> dsThongKe = new ArrayList<>();
	            if (tieuChi.equalsIgnoreCase("Tất cả")) {
	                dsThongKe = sp_DAO.getDSTonKho();
	            } else {
	                dsThongKe = sp_DAO.getDSTonKhoTheoTieuChi(tieuChi);
	            }

	            for (int i = 0; i < dsThongKe.size(); i++) {
	                Row dataRow = sheet.createRow(i + 1);
	                dataRow.createCell(0).setCellValue(dsThongKe.get(i).getMaSP());
	                dataRow.createCell(1).setCellValue(dsThongKe.get(i).getTenSP());
	                dataRow.createCell(2).setCellValue(dsThongKe.get(i).getTongSoLuong());
	            }

	            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
	                workbook.write(fileOut);
	            }

	            workbook.close();

	            JOptionPane.showMessageDialog(null, "Xuất báo cáo thành công!");
	        }
	    } catch (IOException ex) {
	        ex.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Lỗi khi xuất báo cáo: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
	    }
	}

}



