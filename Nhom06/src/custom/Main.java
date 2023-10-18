package custom;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicScrollBarUI;

import entities.SanPham;

public class Main {
	JPanel mainPanel;
	int j = 10;
    public Main() {
        JFrame frame = new JFrame("SpringLayout");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton next = new JButton("Next");
        next.setBounds(0, 0, 50, 30);
        next.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		// TODO Auto-generated method stub
        		addSP("hello");
        		mainPanel.revalidate();
        	}
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.add(next);
        SpringLayout layout = new SpringLayout();
        mainPanel = new JPanel();
        mainPanel.setBounds(0,0,1120,260);
        mainPanel.setLayout(null);
        //int j = 10;
        ArrayList<String> s = new ArrayList<String>();
        
        s.add("hsadsd");
        s.add("Ngdsfds");
        /*
        s.add("234efs");
        s.add("Nguyen");
        s.add("hsadsd");
        s.add("Ngdsfds");
        s.add("234efs");
        s.add("Nguyen");
        s.add("hsadsd");
        s.add("Ngdsfds");
        s.add("234efs");
        s.add("234efs");
        s.add("Nguyen");
        s.add("hsadsd");
        s.add("Ngdsfds");
        s.add("234efs");
        */
        
        for (int i = 0; i < s.size(); i++) {
        	JPanel item = new JPanel();
        	item.setLayout(null);
        	item.setSize(1100, 30);;
            JLabel label = new JLabel(s.get(i));
            JTextField text = new JTextField();
            item.add(label);
            label.setBounds(0,0,50,20);
            item.add(text);
            text.setBounds(65,0,200,20);
            /*
            layout.putConstraint(SpringLayout.WEST, item, 50, SpringLayout.WEST,
                    mainPanel);
            layout.putConstraint(SpringLayout.NORTH, item, j, SpringLayout.NORTH,
                    mainPanel);
            layout.putConstraint(SpringLayout.NORTH, item, j, SpringLayout.NORTH,
                    mainPanel);
            layout.putConstraint(SpringLayout.WEST, item, 50, SpringLayout.EAST,
                    mainPanel);
            */
            item.setBounds(0,j,1100,30);
            j += 30;
            
            mainPanel.add(item);
            //mainPanel.revalidate();
            //mainPanel.add(text);
        }
    	
        JScrollPane jsc = new JScrollPane(mainPanel);
        //jsc.setPreferredSize(new Dimension(1100,300));
        mainPanel.setLayout(new javax.swing.BoxLayout(mainPanel, javax.swing.BoxLayout.Y_AXIS));
        //mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        //mainPanel.setPreferredSize(new Dimension(400,10));
		jsc.setBorder(null);
        jsc.setViewportView(mainPanel);
        frame.setLayout(null);
        frame.add(jsc);
        jsc.setBounds(0,0,1120,260);
        frame.add(buttonPanel);
        buttonPanel.setBounds(0, 260, 1120, 60);
        frame.setBounds(200, 200, 1120, 350);;
        frame.setVisible(true);
    }
    
    public void addSP(String ma) {
    	JPanel item = new JPanel();
    	item.setLayout(null);
        JLabel label = new JLabel(ma);
        //label.setSize(600, 30);
        JTextField text = new JTextField();
        //text.setSize(500, 30);
        item.add(label);
        label.setBounds(0,0,50,20);
        item.add(text);
        text.setBounds(65,0,200,20);
        item.setBounds(0,j,1100,30);
        j += 50;
        mainPanel.add(item);
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                 Main mn = new Main();
            }
        });
    }
}
