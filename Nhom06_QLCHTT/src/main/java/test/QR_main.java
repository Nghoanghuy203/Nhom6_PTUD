package test;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.oned.MultiFormatOneDReader;

public class QR_main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Webcam webcam= Webcam.getDefault();
		webcam.setViewSize(new Dimension(320,240));
		WebcamPanel webcamPanel = new WebcamPanel(webcam);
		webcamPanel.setMirrored(false);
		JFrame frame = new JFrame();
		frame.add(webcamPanel);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		do {
			try {
				BufferedImage image = webcam.getImage();
				LuminanceSource source = new BufferedImageLuminanceSource(image);
				BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
				Result result = new MultiFormatReader().decode(bitmap);
				if (result.getText()!=null) {
					JOptionPane.showMessageDialog(null, result.getText(),"", JOptionPane.INFORMATION_MESSAGE);
					frame.setVisible(false);
					frame.dispose();
					webcam.close();
					break;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		} while (true);
	}

}
