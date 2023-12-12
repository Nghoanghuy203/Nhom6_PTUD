package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import custom.ScaledImg;
import dao.NhanVien_DAO;
import entities.NhanVien;

import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dimension;

public class DangNhap extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtMa;
	private JPasswordField pFDangNhap;
	private JLabel logo;
	private JButton btnDangNhap;
	private NhanVien_DAO nhanVien_DAO;
	public static NhanVien actNV;

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
            java.util.logging.Logger.getLogger(ManHinhDangNhap1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManHinhDangNhap1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManHinhDangNhap1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManHinhDangNhap1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DangNhap frame = new DangNhap();
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
	public DangNhap() {
		// Lấy đối tượng Toolkit
        Toolkit toolkit = Toolkit.getDefaultToolkit();

        // Lấy kích thước màn hình
        Dimension ss = toolkit.getScreenSize();

		int w = ss.width, h=ss.height;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(ss.width,ss.height);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setBackground(new Color(0, 0, 0, 30));
		
		contentPane = new JPanel();
		contentPane.setOpaque(false);
		contentPane.setBackground(new Color(0,0,0,30));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pnLogin = new JPanel();
		pnLogin.setBackground(new Color(255, 255, 255));
		pnLogin.setBounds(154,142,w-300,h-300);
		contentPane.add(pnLogin);
		pnLogin.setLayout(null);
		
		JLabel btnTroVe = new JLabel("Thoát");
		btnTroVe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Nhấn OK để thoát khỏi chương trình.", "Thoat", JOptionPane.WARNING_MESSAGE, JOptionPane.OK_CANCEL_OPTION) == 0) {
					//webcam.close();
					DangNhap.this.dispose();
					System.exit(0);
				}
			}
		});
		btnTroVe.setIcon(new ImageIcon(ManHinhDangNhap1.class.getResource("/images/close.png")));
		btnTroVe.setBounds(1171, 10, 55, 20);
		pnLogin.add(btnTroVe);
		
		JLabel lblMa = new JLabel("Mã nhân viên:");
		lblMa.setFont(new Font("Arial", Font.BOLD, 12));
		lblMa.setBounds(220, 120, 90, 30);
		pnLogin.add(lblMa);
		
		JLabel lblMatKhau = new JLabel("Mật Khẩu:");
		lblMatKhau.setFont(new Font("Arial", Font.BOLD, 12));
		lblMatKhau.setBounds(220, 180, 90, 30);
		pnLogin.add(lblMatKhau);
		
		pFDangNhap = new JPasswordField();
		pFDangNhap.setBounds(220, 200, 300, 30);
		pFDangNhap.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		pFDangNhap.setBackground(new Color(255,255,255,0));
		pFDangNhap.setOpaque(false);
		pnLogin.add(pFDangNhap);
		
		txtMa = new JTextField();
		txtMa.setOpaque(false);
		txtMa.setBounds(220, 140, 300, 30);
		txtMa.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtMa.setBackground(new Color(255,255,255,0));
		pnLogin.add(txtMa);
		txtMa.setColumns(10);
		
		JLabel btnShowPass = new JLabel("");
		btnShowPass.setIcon(new ImageIcon(ManHinhDangNhap.class.getResource("/images/show.png")));
		btnShowPass.setBounds(520, 200, 40, 40);
		pnLogin.add(btnShowPass);
		
		char dot = pFDangNhap.getEchoChar();
		
		JLabel btnHidePass = new JLabel("");
		btnHidePass.setIcon(new ImageIcon(ManHinhDangNhap.class.getResource("/images/eye-off.png")));
		btnHidePass.setBounds(520, 200, 40, 40);
		btnHidePass.setVisible(false);
		pnLogin.add(btnHidePass);
		
		logo = new JLabel();
		logo.setBounds(240, 20, 120, 80);
		File f = new File(ManHinhDangNhap.class.getResource("/images/logoAM.jpg").getFile());
		BufferedImage img = null;
		try {
			img = ImageIO.read(f);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		logo.setIcon(new ImageIcon(ScaledImg.scaledImage(img, logo.getWidth(), logo.getHeight())));
		pnLogin.add(logo);
		
		btnShowPass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pFDangNhap.setEchoChar((char)0);
				btnHidePass.setVisible(true);
				btnShowPass.setVisible(false);
			}
		});
		
		btnHidePass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pFDangNhap.setEchoChar(dot);
				btnShowPass.setVisible(true);
				btnHidePass.setVisible(false);
			}
		});
		
		btnDangNhap = new JButton("Đăng nhập");
		btnDangNhap.setBackground(new Color(0, 0, 255, 160));
		btnDangNhap.setForeground(new Color(245, 255, 250));
		btnDangNhap.setBounds(230, 270, 140, 40);
		pnLogin.add(btnDangNhap);
	}
}
