package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import connectDB.ConnectDB;
import custom.ScaledImg;
import dao.NhanVien_DAO;
import entities.NhanVien;
import custom.CustomScrollBarUI;
import custom.MenuItem;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class ManHinhChinh extends JFrame{

	private JPanel contentPane;
	private JPanel pn_listMenu;
	public static JPanel pn_body;
	public static NhanVien nvAct;
	public static FormNhapThongTinKhachHangMoi form;
	
	public static ManHinhLapHoaDon manHinhLapHoaDon;
	public static ManHinhDatHang mh_dathang;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ManHinhChinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManHinhChinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManHinhChinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManHinhChinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
		
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
		contentPane.setBackground(new Color(255, 255, 255));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		try {
			ConnectDB.getInstance().connect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		//manHinhLapHoaDon = new ManHinhLapHoaDon(true, "");
		form = new FormNhapThongTinKhachHangMoi();
		form.setVisible(false);
		
		JPanel pn_menu = new JPanel();
		pn_menu.setBackground(new Color(70, 130, 180));
		pn_menu.setBounds(0, 0, 240, 730);
		pn_menu.setBorder(new MatteBorder(0,0,0,2, new Color(0,0,0,100)));
		contentPane.add(pn_menu);
		pn_menu.setLayout(null);
		
		JLabel logo = new JLabel();
		logo.setBounds(60, 40, 120, 80);
		File f_logo = new File(ManHinhChinh.class.getResource("/images/logoAM.jpg").getFile());
		BufferedImage imgLogo = null;
		try {
			imgLogo = ImageIO.read(f_logo);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		logo.setIcon(new ImageIcon(ScaledImg.scaledImage(imgLogo, logo.getWidth(), logo.getHeight())));
		pn_menu.add(logo);
		
		JPanel pn_info = new JPanel();
		pn_info.setBackground(new Color(70, 130, 180));
		pn_info.setBounds(0, 650, 238, 80);
		pn_info.setBorder(new MatteBorder(2,0,0,0, new Color(0,0,0,100)));
		pn_menu.add(pn_info);
		pn_info.setLayout(null);
		
		JLabel btnLogout = new JLabel("");
		btnLogout.setBounds(210, 0, 30, 80);
		btnLogout.setIcon(new ImageIcon(ManHinhChinh.class.getResource("/images/logOut.png")));
		pn_info.add(btnLogout);
		
		JLabel lblChucvu = new JLabel("Quản lý");
		lblChucvu.setFont(new Font("Arial", Font.BOLD, 14));
		lblChucvu.setForeground(new Color(255, 255, 255));
		lblChucvu.setBounds(60, 10, 120, 30);
		pn_info.add(lblChucvu);
		if (isQL) lblChucvu.setText("Quản lý");
		else lblChucvu.setText("NV Bán hàng");
		
		NhanVien_DAO nhanVien_DAO = new NhanVien_DAO();
		nvAct = nhanVien_DAO.getNhanVien("a");
		JLabel lblHoTen = new JLabel("");
		lblHoTen.setText(nvAct.getTenNV());
		lblHoTen.setForeground(new Color(255, 255, 255));
		//lblHoTen.setText(ManHinhChinh.nvAct.getTenNV());
		lblHoTen.setFont(new Font("Arial", Font.PLAIN, 14));
		lblHoTen.setBounds(60, 40, 140, 30);
		pn_info.add(lblHoTen);
		
		JLabel lblIconNV = new JLabel("");
		lblIconNV.setIcon(new ImageIcon(ManHinhChinh.class.getResource("/images/userInfo.png")));
		lblIconNV.setBounds(0, 10, 60, 60);
		pn_info.add(lblIconNV);
		
		pn_listMenu = new JPanel();
		pn_listMenu.setBackground(new Color(70, 130, 180));
		pn_listMenu.setBounds(0, 160, 240, 450);
		
		JScrollPane jsp_menu = new JScrollPane(pn_listMenu);
		jsp_menu.setBounds(0,160,238,450);
		
		pn_listMenu.setLayout(new javax.swing.BoxLayout(pn_listMenu, javax.swing.BoxLayout.Y_AXIS));
		jsp_menu.setBorder(null);
        jsp_menu.setViewportView(pn_listMenu);
        jsp_menu.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        jsp_menu.getVerticalScrollBar().setPreferredSize(new Dimension(6,100));
        pn_menu.add(jsp_menu);
		
		pn_body = new JPanel();
		pn_body.setBackground(new Color(255, 255, 255));
		pn_body.setBounds(240, 0, 1120, 730);
		contentPane.add(pn_body);
		pn_body.setLayout(null);
		
		JLabel hinhNen = new JLabel("");
		hinhNen.setBounds(0, 0, 1120, 710);
		File f_hinhNen = new File(ManHinhDangNhap.class.getResource("/images/hinhNen.jpg").getFile());
		BufferedImage imgHinhNen = null;
		try {
			imgHinhNen = ImageIO.read(f_hinhNen);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		hinhNen.setIcon(new ImageIcon(ScaledImg.scaledImage(imgHinhNen, hinhNen.getWidth(), hinhNen.getHeight())));
		//pn_body.add(hinhNen);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 20, 1120, 710);
		pn_body.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("HỆ THỐNG QUẢN LÝ BÁN HÀNG CỬA HÀNG THỜI TRANG");
		lblNewLabel.setBounds(142, 300, 836, 36);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(65, 105, 225));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 30));
		panel.add(lblNewLabel);
		
		JLabel btnExit = new JLabel("");
		btnExit.setIcon(new ImageIcon(ManHinhChinh.class.getResource("/images/close.png")));
		btnExit.setBounds(1100, 0, 20, 20);
		pn_body.add(btnExit);
		
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(JOptionPane.showConfirmDialog(null, "Nhấn OK để thoát khỏi chương trình.", "Thoat", JOptionPane.WARNING_MESSAGE, JOptionPane.OK_CANCEL_OPTION) == 0) {
					ManHinhChinh.this.dispose();
					System.exit(0);
				}
			}
		});
		
		btnLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(JOptionPane.showConfirmDialog(null, "Bạn có muốn đăng xuất không?.", "Đăng xuất", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION) == 0) {
					ManHinhDangNhap frame = new ManHinhDangNhap();
		    		frame.setVisible(true);
		    		ManHinhChinh.this.dispose();
		    		ManHinhDangNhap.actNV=null;
				}
			}
		});
		
		menuVertical(new Color(70, 130, 180), new Color(55, 60, 255), isQL);
		
		
	}
	
	public static ActionListener swithPanel(JPanel panel) {
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
		//
		ManHinhLapHoaDon mh_lapHoaDon = new ManHinhLapHoaDon(false,"");
		mh_dathang = new ManHinhDatHang();
		ManHinhCapNhatNhanVien mh_capNhatNhanVien = new ManHinhCapNhatNhanVien();
		ManHinhTimKiemNhanVien mh_timKiemNhanVien = new ManHinhTimKiemNhanVien();
		ManHinhThongKeDoanhThu mh_thongKeDoanhThu = new ManHinhThongKeDoanhThu();
		ManHinhCapNhatKhachHang mh_capNhatKhachHang = new ManHinhCapNhatKhachHang();
		ManHinhTimKiemKhachHang mh_timKiemKhachHang = new ManHinhTimKiemKhachHang();
		ManHinhCapNhatSanPham mh_capNhatSanPham = new ManHinhCapNhatSanPham();
		ManHinhTimKiemSanPham mh_timKiemSanPham = new ManHinhTimKiemSanPham();
		ManHinhCapNhatNhaCungCap mh_capNhatNCC = new ManHinhCapNhatNhaCungCap();
		ManHinhTimKiemNhaCungCap mh_timKiemNCC = new ManHinhTimKiemNhaCungCap();
		ManHinhTimKiemHoaDon mh_timKiemHoaDon = new ManHinhTimKiemHoaDon();
		ManHinhCapNhatChuongTrinhKhuyenMai mh_capNhatCTKM = new ManHinhCapNhatChuongTrinhKhuyenMai();
		ManHinhTimKiemChuongTrinhKhuyenMai mh_timKiemCTKM = new ManHinhTimKiemChuongTrinhKhuyenMai();
		
		ImageIcon icon_submenu = new ImageIcon(ManHinhChinh.class.getResource("/images/point.png"));
		
		//submenu khách hàng
		MenuItem mn_capnhatKH = new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_capnhat.png")), "Cập nhật", swithPanel(mh_capNhatKhachHang), true, color1, color2);
		MenuItem mn_timkiemKH = new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_timkiem.png")), "Tìm kiếm", swithPanel(mh_timKiemKhachHang), true, color1, color2);
		
		//submenu sản phẩm
		MenuItem mn_capnhatSP = new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_capnhat.png")), "Cập nhật", swithPanel(mh_capNhatSanPham), true, color1, color2);
		MenuItem mn_timkiemSP = new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_timkiem.png")), "Tìm kiếm", swithPanel(mh_timKiemSanPham), true, color1, color2);
		//MenuItem mn_loaiSP = new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_loai.png")), "Loại sản phẩm", swithPanel(pn_body), true, color1, color2);
		//MenuItem mn_mausac = new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_mausac.png")), "Màu sắc", swithPanel(pn_body), true, color1, color2);
		//MenuItem mn_kichco = new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_kichco.png")), "Kích cỡ", swithPanel(pn_body), true, color1, color2);
		//MenuItem mn_chatlieu = new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_chatlieu.png")), "Chất liệu", swithPanel(pn_body), true, color1, color2);
		
		//submenu nhân viên
		MenuItem mn_laphoadon = new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_banhang.png")), "Lập hóa đơn", swithPanel(mh_lapHoaDon), true, color1, color2);
		MenuItem mn_capnhatNV = new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_capnhat.png")), "Cập nhật", swithPanel(mh_capNhatNhanVien), true, color1, color2);
		MenuItem mn_timkiemNV = new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_timkiem.png")), "Tìm kiếm", swithPanel(mh_timKiemNhanVien), true, color1, color2);
		
		//submenu hóa đơn
		MenuItem mn_hdbanhang = new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_timkiem.png")), "Tìm kiếm", swithPanel(mh_timKiemHoaDon), true, color1, color2);
		
		//submenu thống kê
		MenuItem mn_thongkeSPbanchay = new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_tkbanchay.png")), "Thống kê hàng bán chạy", swithPanel(pn_body), true, color1, color2);
		MenuItem mn_thongkehangtonkho = new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_tktonkho.png")), "Thống kê hàng tồn kho", swithPanel(pn_body), true, color1, color2);
		MenuItem mn_thongkedoanhthu = new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_tkdoanhthu.png")), "Thống kê doanh thu", swithPanel(mh_thongKeDoanhThu), true, color1, color2);
		
		//submenu chương trình khuyến mãi
		MenuItem mn_capNhatCTKM = new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_capnhat.png")), "Cập nhật CTKM", swithPanel(mh_capNhatCTKM), true, color1, color2);
		MenuItem mn_timkiemCTKM = new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_timkiem.png")), "Tìm kiếm CTKM", swithPanel(mh_timKiemCTKM), true, color1, color2);
		
		MenuItem mn_capNhatNCC = new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_capnhat.png")), "Cập nhật", swithPanel(mh_capNhatNCC), true, color1, color2);
		MenuItem mn_timkiemNCC = new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_timkiem.png")), "Tìm kiếm", swithPanel(mh_timKiemNCC), true, color1, color2);
		
		//submenu đơn đặt hàng
		MenuItem mn_timkiemDDH = new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_dathang.png")), "Đặt hàng", swithPanel(mh_dathang), true, color1, color2);
		
		//menu
		
		MenuItem mn_khachhang = new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_khachhang.png")), "Khách hàng", null, false, color1, color2, mn_capnhatKH, mn_timkiemKH);
		MenuItem mn_sanpham;
		MenuItem mn_nhacungcap = new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_ncc.png")), "Nhà cung cấp", null, false, color1, color2, mn_capNhatNCC, mn_timkiemNCC);
		MenuItem mn_nhanvien;
		MenuItem mn_hoadon = new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_hoadon.png")), "Hóa đơn", null, false, color1, color2, mn_hdbanhang);
		//MenuItem mn_thongke = new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_thongke.png")), "Thống kê", null, false, color1, color2, mn_thongkeSPbanchay, mn_thongkehangtonkho, mn_thongkedoanhthu);
		//MenuItem mn_dondathang = new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_dathang.png")), "Đơn đặt hàng", null, false, color1, color2,mn_timkiemDDH);
		MenuItem mn_CTKM = new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_ctkm.png")), "Chương trình khuyến mãi", null, false, color1, color2, mn_capNhatCTKM, mn_timkiemCTKM);
		
		if (isQL) {
			mn_nhanvien = new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_nhanvien.png")), "Nhân viên", null, false, color1, color2, mn_laphoadon, mn_timkiemDDH, mn_capnhatNV, mn_timkiemNV, mn_thongkeSPbanchay, mn_thongkehangtonkho, mn_thongkedoanhthu);
			mn_sanpham= new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_sanpham.png")), "Sản phẩm", null, false, color1, color2, mn_capnhatSP, mn_timkiemSP);
			addMenu(mn_nhanvien, mn_khachhang, mn_sanpham,mn_nhacungcap, mn_hoadon, mn_CTKM);
		}
		else {
			mn_nhanvien = new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_nhanvien.png")), "Nhân viên", null, false, color1, color2, mn_laphoadon, mn_timkiemDDH, mn_thongkeSPbanchay, mn_thongkehangtonkho, mn_thongkedoanhthu);
			mn_sanpham= new MenuItem(new ImageIcon(ManHinhChinh.class.getResource("/images/icon_sanpham.png")), "Sản phẩm", null, false, color1, color2, mn_timkiemSP);
			addMenu(mn_nhanvien, mn_khachhang, mn_sanpham, mn_hoadon);
		}
		
		
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


	