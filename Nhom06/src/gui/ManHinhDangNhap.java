package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import custom.ScaledImg;

@SuppressWarnings("serial")
public class ManHinhDangNhap extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField txtemail;
	private JPasswordField pFDangNhap;
	private JButton btnDangNhap;
	private JLabel logo;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManHinhDangNhap frame = new ManHinhDangNhap();
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
	public ManHinhDangNhap() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1360,730);
		setLocationRelativeTo(null);
		setUndecorated(true);
		contentPane = new JPanel();
		getContentPane().add(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(0, 0, 0, 30));
		setBackground(new Color(0, 0, 0, 30));
		
		JPanel pnLogin = new JPanel();
		pnLogin.setBackground(Color.white);
		pnLogin.setBounds(400, 200, 560, 330);
		pnLogin.setLayout(null);
		contentPane.add(pnLogin);
		
		txtemail = new JTextField();
		txtemail.setToolTipText("");
		txtemail.setBounds(130, 140, 300, 30);
		txtemail.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		txtemail.setBackground(new Color(255,255,255,0));
		txtemail.setOpaque(false);
		pnLogin.add(txtemail);
		txtemail.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Mã nhân viên:");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel.setBounds(130, 120, 90, 30);
		pnLogin.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Mật Khẩu:");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_1.setBounds(130, 180, 90, 30);
		pnLogin.add(lblNewLabel_1);
		
		pFDangNhap = new JPasswordField();
		pFDangNhap.setBounds(130, 200, 300, 30);
		pFDangNhap.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		pFDangNhap.setBackground(new Color(255,255,255,0));
		pFDangNhap.setOpaque(false);
		pnLogin.add(pFDangNhap);
		
		JLabel btnTroVe = new JLabel("Thoát");
		btnTroVe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Nhấn OK để thoát khỏi chương trình.", "Thoat", JOptionPane.WARNING_MESSAGE, JOptionPane.OK_CANCEL_OPTION) == 0) {
					System.exit(0);
				}
			}
		});
		btnTroVe.setIcon(new ImageIcon(ManHinhDangNhap.class.getResource("/images/close.png")));
		btnTroVe.setBounds(495, 10, 55, 20);
		pnLogin.add(btnTroVe);
		
		JLabel btnShowPass = new JLabel("");
		btnShowPass.setIcon(new ImageIcon(ManHinhDangNhap.class.getResource("/images/show.png")));
		btnShowPass.setBounds(430, 200, 40, 40);
		pnLogin.add(btnShowPass);
		
		char dot = pFDangNhap.getEchoChar();
		
		JLabel btnHidePass = new JLabel("");
		btnHidePass.setIcon(new ImageIcon(ManHinhDangNhap.class.getResource("/images/eye-off.png")));
		btnHidePass.setBounds(430, 200, 40, 40);
		btnHidePass.setVisible(false);
		pnLogin.add(btnHidePass);
		
		logo = new JLabel();
		logo.setBounds(220, 20, 120, 80);
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
		btnDangNhap.addActionListener(this);
		btnDangNhap.setBackground(new Color(0, 0, 255, 160));
		btnDangNhap.setForeground(new Color(245, 255, 250));
		btnDangNhap.setBounds(200, 270, 160, 40);
		pnLogin.add(btnDangNhap);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
