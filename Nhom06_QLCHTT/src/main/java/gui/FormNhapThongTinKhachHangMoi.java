package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import connectDB.ConnectDB;
import custom.GeneratorID;
import dao.KhachHang_DAO;
import entities.KhachHang;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.Component;
import javax.swing.ScrollPaneConstants;

public class FormNhapThongTinKhachHangMoi extends JDialog {
	static JTextField txt_kqHotenKHMoi;
	static JTextField txt_kqSdtKHMoi;
	static JTextArea txt_kqDiaChiKHMoi;
	static JComboBox<String> cmb_gioiTinhKHMoi;
	private KhachHang_DAO khachHang_DAO;
	public static JButton btnThemKHMoi;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FormNhapThongTinKhachHangMoi dialog = new FormNhapThongTinKhachHangMoi();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FormNhapThongTinKhachHangMoi() {
		setBounds(240, 0, 1120, 730);
		setUndecorated(true);
		getContentPane().setLayout(null);
		setBackground(new Color(0, 0, 0, 30));
		
		try {
			ConnectDB.getInstance().connect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		khachHang_DAO = new KhachHang_DAO();
		
		JPanel main = new JPanel();
		main.setBackground(new Color(255, 255, 255));
		main.setBounds(340,200,450,300);
		getContentPane().add(main);
		main.setLayout(null);
		
		JLabel btnExit = new JLabel("");
		btnExit.setIcon(new ImageIcon(ManHinhChinh.class.getResource("/images/close.png")));
		btnExit.setBounds(430, 0, 20, 20);
		main.add(btnExit);
		
		JLabel lblNewLabel = new JLabel("NHẬP THÔNG TIN KHÁCH HÀNG");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(65, 105, 225));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel.setBounds(20, 10, 410, 30);
		main.add(lblNewLabel);
		
		btnThemKHMoi = new JButton("Thêm");
		btnThemKHMoi.setBackground(new Color(0, 128, 0));
		btnThemKHMoi.setBounds(310, 250, 90, 30);
		main.add(btnThemKHMoi);
		
		
		JLabel lblNewLabel_1_1 = new JLabel("Họ tên:");
		lblNewLabel_1_1.setBounds(40, 60, 100, 30);
		main.add(lblNewLabel_1_1);
		
		txt_kqHotenKHMoi = new JTextField();
		txt_kqHotenKHMoi.setColumns(10);
		txt_kqHotenKHMoi.setBounds(140, 60, 260, 30);
		main.add(txt_kqHotenKHMoi);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Số điện thoại:");
		lblNewLabel_1_1_1.setBounds(40, 100, 100, 30);
		main.add(lblNewLabel_1_1_1);
		
		txt_kqSdtKHMoi = new JTextField();
		txt_kqSdtKHMoi.setColumns(10);
		txt_kqSdtKHMoi.setBounds(140, 100, 260, 30);
		main.add(txt_kqSdtKHMoi);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Địa chỉ:");
		lblNewLabel_1_1_1_1.setBounds(40, 140, 100, 30);
		main.add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Giới tính:");
		lblNewLabel_1_1_2.setBounds(40, 200, 100, 30);
		main.add(lblNewLabel_1_1_2);
		
		
		cmb_gioiTinhKHMoi = new JComboBox(new Object[]{"Nam","Nữ"});
		cmb_gioiTinhKHMoi.setBounds(140, 200, 90, 30);
		main.add(cmb_gioiTinhKHMoi);
		
		txt_kqDiaChiKHMoi = new JTextArea();
		
		JScrollPane scr_nhapDiaChiKH = new JScrollPane(txt_kqDiaChiKHMoi, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scr_nhapDiaChiKH.setBounds(140, 140, 260, 50);
		main.add(scr_nhapDiaChiKH);
		
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
			}
		});
	}
	public static boolean validDataKHNew() {
		String hoTen = txt_kqHotenKHMoi.getText().trim();
		String soDienThoai = txt_kqSdtKHMoi.getText().trim();
		String diaChi = txt_kqDiaChiKHMoi.getText();
		if(!(hoTen.length() > 0 && hoTen.matches("^("+GeneratorID.tiengVietLow().toUpperCase()+GeneratorID.tiengVietLow()+"*((\\s)))+"+GeneratorID.tiengVietLow().toUpperCase()+GeneratorID.tiengVietLow()+"*$"))) {
			JOptionPane.showMessageDialog(null, "Họ Tên không chứa ký tự số. VD: Nguyễn Văn A");
			txt_kqHotenKHMoi.requestFocus();
			txt_kqHotenKHMoi.selectAll();
			return false;
		}
		if (!(soDienThoai.length() == 9 && soDienThoai.matches("([0])([0-9]{8})"))) {
			JOptionPane.showMessageDialog(null, "Số Điện thoại bắt đầu bằng số 0 và độ dài số điện thoại bằng 9");
			txt_kqSdtKHMoi.requestFocus();
			txt_kqSdtKHMoi.selectAll();
			return false;
		}
		if (!(diaChi.length()>0)) {
			JOptionPane.showMessageDialog(null, "Nhập địa chỉ khách hàng");
			txt_kqDiaChiKHMoi.requestFocus();
			txt_kqDiaChiKHMoi.selectAll();
			return false;
		}
		
		return true;
	}
}
