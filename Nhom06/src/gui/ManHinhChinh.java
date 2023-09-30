package gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import custom.ScaledImg;
import custom.MenuItem;

import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ManHinhChinh extends JFrame {

	private JPanel contentPane;
	private JPanel pn_listMenu;
	private JPanel pn_body;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManHinhChinh frame = new ManHinhChinh(true);
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
	public ManHinhChinh(boolean isQL) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1360,730);
		setLocationRelativeTo(null);
		setUndecorated(true);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pn_menu = new JPanel();
		pn_menu.setBackground(new Color(100, 149, 237));
		pn_menu.setBounds(0, 0, 240, 730);
		pn_menu.setBorder(new MatteBorder(0,0,0,2, new Color(0,0,0,100)));
		contentPane.add(pn_menu);
		pn_menu.setLayout(null);
		
		JLabel logo = new JLabel();
		logo.setBounds(60, 40, 120, 80);
		File f = new File(ManHinhDangNhap.class.getResource("/images/logoAM.jpg").getFile());
		BufferedImage img = null;
		try {
			img = ImageIO.read(f);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		logo.setIcon(new ImageIcon(ScaledImg.scaledImage(img, logo.getWidth(), logo.getHeight())));
		pn_menu.add(logo);
		
		JPanel pn_info = new JPanel();
		pn_info.setBackground(new Color(100, 149, 237));
		pn_info.setBounds(0, 650, 238, 80);
		pn_info.setBorder(new MatteBorder(2,0,0,0, new Color(0,0,0,100)));
		pn_menu.add(pn_info);
		pn_info.setLayout(null);
		
		JLabel btnLogout = new JLabel("");
		btnLogout.setBounds(210, 0, 30, 30);
		btnLogout.setIcon(new ImageIcon(ManHinhChinh.class.getResource("/images/logOut.png")));
		pn_info.add(btnLogout);
		
		JLabel lblChucvu = new JLabel("Quản lý");
		lblChucvu.setFont(new Font("Arial", Font.BOLD, 14));
		lblChucvu.setForeground(new Color(255, 255, 255));
		lblChucvu.setBounds(60, 10, 120, 30);
		pn_info.add(lblChucvu);
		
		JLabel lblHoTen = new JLabel("Nguyễn Hoàng Huy");
		lblHoTen.setForeground(new Color(255, 255, 255));
		lblHoTen.setFont(new Font("Arial", Font.PLAIN, 14));
		lblHoTen.setBounds(60, 40, 140, 30);
		pn_info.add(lblHoTen);
		
		JLabel lblIconNV = new JLabel("");
		lblIconNV.setIcon(new ImageIcon(ManHinhChinh.class.getResource("/images/userInfo.png")));
		lblIconNV.setBounds(0, 10, 60, 60);
		pn_info.add(lblIconNV);
		
		pn_listMenu = new JPanel();
		pn_listMenu.setBackground(new Color(100, 149, 237));
		pn_listMenu.setBounds(0, 160, 240, 450);
		
		JScrollPane jsp_menu = new JScrollPane(pn_listMenu);
		jsp_menu.setBounds(0,160,238,450);
		
		pn_listMenu.setLayout(new javax.swing.BoxLayout(pn_listMenu, javax.swing.BoxLayout.Y_AXIS));
		jsp_menu.setBorder(null);
        jsp_menu.setViewportView(pn_listMenu);
        pn_menu.add(jsp_menu);
		
		pn_body = new JPanel();
		pn_body.setBackground(new Color(255, 255, 255));
		pn_body.setBounds(200, 0, 1160, 730);
		contentPane.add(pn_body);
		
		menuVertical(new Color(100, 149, 237), new Color(55, 60, 255), isQL);
	}
	
	public ActionListener swithPanel(JPanel panel) {
		ActionListener action = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pn_body.removeAll();
				pn_body.add(panel);
				pn_body.validate();
				pn_body.repaint();
			}
		};
		return action;
	}
	
	public void menuVertical(Color color1, Color color2, boolean isQL) {
		ImageIcon icon_submenu = new ImageIcon(ManHinhChinh.class.getResource("/images/point.png"));
		
		//submenu khách hàng
		MenuItem mn_capnhatKH = new MenuItem(icon_submenu, "Cập nhật", swithPanel(pn_body), true, color1, color2);
		MenuItem mn_timkiemKH = new MenuItem(icon_submenu, "Tìm kiếm", swithPanel(pn_body), true, color1, color2);
		
		//submenu sản phẩm
		MenuItem mn_capnhatSP = new MenuItem(icon_submenu, "Cập nhật", swithPanel(pn_body), true, color1, color2);
		MenuItem mn_timkiemSP = new MenuItem(icon_submenu, "Tìm kiếm", swithPanel(pn_body), true, color1, color2);
		MenuItem mn_loaiSP = new MenuItem(icon_submenu, "Loại sản phẩm", swithPanel(pn_body), true, color1, color2);
		MenuItem mn_mausac = new MenuItem(icon_submenu, "Màu sắc", swithPanel(pn_body), true, color1, color2);
		MenuItem mn_kichco = new MenuItem(icon_submenu, "Kích cỡ", swithPanel(pn_body), true, color1, color2);
		MenuItem mn_chatlieu = new MenuItem(icon_submenu, "Chất liệu", swithPanel(pn_body), true, color1, color2);
		MenuItem mn_nhacungcap = new MenuItem(icon_submenu, "Nhà cung cấp", swithPanel(pn_body), true, color1, color2);
		
		//submenu nhân viên
		MenuItem mn_capnhatNV = new MenuItem(icon_submenu, "Cập nhật", swithPanel(pn_body), true, color1, color2);
		MenuItem mn_timkiemNV = new MenuItem(icon_submenu, "Tìm kiếm", swithPanel(pn_body), true, color1, color2);
		
		//submenu lịch sử hoạt động
		MenuItem mn_nhaphang = new MenuItem(icon_submenu, "Nhập hàng", swithPanel(pn_body), true, color1, color2);
		MenuItem mn_hoadon = new MenuItem(icon_submenu, "Hóa đơn", swithPanel(pn_body), true, color1, color2);
		
		//submenu thống kê
		MenuItem mn_thongkeSPbanchay = new MenuItem(icon_submenu, "Sản phẩm bán chạy", swithPanel(pn_body), true, color1, color2);
		MenuItem mn_thongkehangtonkho = new MenuItem(icon_submenu, "Hàng tồn kho", swithPanel(pn_body), true, color1, color2);
		MenuItem mn_thongkedoanhthu = new MenuItem(icon_submenu, "Doanh thu", swithPanel(pn_body), true, color1, color2);
		
		//menu
		MenuItem mn_banhang = new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_banhang.png")), "Bán hàng", swithPanel(pn_body), false, color1, color2);
		MenuItem mn_khachhang = new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_khachhang.png")), "Khách hàng", null, false, color1, color2, mn_capnhatKH, mn_timkiemKH);
		MenuItem mn_sanpham = new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_sanpham.png")), "Sản phẩm", null, false, color1, color2, mn_capnhatSP, mn_timkiemSP, mn_mausac, mn_chatlieu, mn_loaiSP, mn_kichco, mn_nhacungcap);
		MenuItem mn_nhanvien = new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_nhanvien.png")), "Nhân viên", null, false, color1, color2, mn_capnhatNV, mn_timkiemNV);
		MenuItem mn_lichsuhoatdong = new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_hoadon.png")), "Lịch sử hoạt động", null, false, color1, color2, mn_hoadon, mn_nhaphang);
		MenuItem mn_thongke = new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_thongke.png")), "Thống kê", null, false, color1, color2, mn_thongkeSPbanchay, mn_thongkehangtonkho, mn_thongkedoanhthu);
		
		addMenu(mn_banhang, mn_khachhang, mn_sanpham, mn_nhanvien, mn_lichsuhoatdong, mn_thongke);
		
	}
	
	private void addMenu(MenuItem... menu) {
        for (int i = 0; i < menu.length; i++) {
            pn_listMenu.add(menu[i]);
            ArrayList<MenuItem> subMenu = menu[i].getSubMenu();
            for (MenuItem m : subMenu) {
                addMenu(m);
            }
        }
        pn_listMenu.revalidate();
    }
}
