package gui;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import custom.lineChart.chart.CurveLineChart;
import custom.lineChart.chart.ModelChart;
import custom.lineChart.panel.PanelShadow;
import dao.HoaDon_DAO;
import entities.HoaDon;
import entities.ModelData;
import javax.swing.border.EmptyBorder;

import connectDB.ConnectDB;

public class BieuDoDoanhThu extends JPanel{
	
	private CurveLineChart chart;
    private PanelShadow panelShadow1;
    
    private ArrayList<Double> listH9;
    private ArrayList<Double> listH10;
    private ArrayList<Double> listH11;
    private ArrayList<Double> listH12;
    private ArrayList<Double> listH14;
    private ArrayList<Double> listH15;
    private ArrayList<Double> listH16;
    private ArrayList<Double> listH17;
    private ArrayList<Double> listH18;
    private ArrayList<Double> listH19;
    private ArrayList<Double> listH20;
    private ArrayList<Double> listH21;
    
    
	
	public BieuDoDoanhThu() {
		//setBounds(0, 0, 1080, 310);
		setSize(1080, 730);
		setVisible(true);
        initComponents();
        
        listH9 = new ArrayList<>();
        listH10 = new ArrayList<>();
        listH11 = new ArrayList<>();
        listH12 = new ArrayList<>();
        listH14 = new ArrayList<>();
        listH15 = new ArrayList<>();
        listH16 = new ArrayList<>();
        listH17 = new ArrayList<>();
        listH18 = new ArrayList<>();
        listH19 = new ArrayList<>();
        listH20 = new ArrayList<>();
        listH21 = new ArrayList<>();
        chart.setTitle("Doanh thu theo ngày");
        
        //chart.addLegend("Cost", Color.decode("#e65c00"), Color.decode("#F9D423"));
        //chart.addLegend("Profit", Color.decode("#0099F7"), Color.decode("#F11712"));
        //test();
        chart.clear();
        //setData();
        thongKeTheoNgay(2023, 11, 02, "#e65c00", "#F9D423");
        thongKeTheoNgay(2023, 11, 01,"#7b4397","#dc2430");
        thongKeTheoNgay(2023, 10, 31,"#0099F7","#F11712");
        
        chart.addData(new ModelChart("09:00", listH9));
        chart.addData(new ModelChart("10:00", listH10));
        chart.addData(new ModelChart("11:00", listH11));
        chart.addData(new ModelChart("12:00", listH12));
        chart.addData(new ModelChart("14:00", listH14));
        chart.addData(new ModelChart("15:00", listH15));
        chart.addData(new ModelChart("16:00", listH16));
        chart.addData(new ModelChart("17:00", listH17));
        chart.addData(new ModelChart("18:00", listH18));
        chart.addData(new ModelChart("19:00", listH19));
        chart.addData(new ModelChart("20:00", listH20));
        chart.addData(new ModelChart("21:00", listH21));
        chart.start();
    }
	
    private void setData() {
        try {
            List<ModelData> lists = new ArrayList<>();
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "select x = format( ngayLap,'HH'), y = sum(tongTienHD) \r\n"
            		+ "from HoaDon\r\n"
            		+ "where CONVERT(nvarchar(20),ngayLap,120) like '2023-11-01%'\r\n"
            		+ "group by format( ngayLap,'HH')\r\n"
            		+ "order by x desc";
            PreparedStatement p = con.prepareStatement(sql);
            ResultSet r = p.executeQuery();
            while (r.next()) {
                String x = r.getNString("x");
                double y = r.getDouble("y");
                lists.add(new ModelData(x, y));
            }
            r.close();
            p.close();
            //  Add Data to chart
            for (int i = lists.size() - 1; i >= 0; i--) {
                ModelData d = lists.get(i);
                //chart.addData(new ModelChart(d.getX(), new double[]{d.getY()}));
            }
            //  Start to show data with animation
            chart.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void thongKeTheoNgay(int nam, int thang, int ngay, String color1, String color2) {
    	String d = ngay<10?"0"+ngay:ngay+"";
    	//"#7b4397"), Color.decode("#dc2430"
    	chart.addLegend(d+"/"+thang+"/"+nam, Color.decode(color1), Color.decode(color2));
    	double h9=0,h10=0,h11=0,h12=0,h14=0,h15=0,h16=0,h17=0,h18=0,h19=0,h20=0,h21=0;
        HoaDon_DAO hoaDon_DAO = new HoaDon_DAO();
        List<HoaDon> dsHD = hoaDon_DAO.getDsHoaDon();
        for (HoaDon hoaDon : dsHD) {
			if (hoaDon.getNgayLap().getYear()==nam && hoaDon.getNgayLap().getMonthValue()==thang && hoaDon.getNgayLap().getDayOfMonth()==ngay) {
				if (hoaDon.getNgayLap().getHour()==9) {
					h9+=hoaDon.getTongTienHD();	
				}
				else if (hoaDon.getNgayLap().getHour()==10) {
					h10+=hoaDon.getTongTienHD();
				}
				else if (hoaDon.getNgayLap().getHour()==11) {
					h11+=hoaDon.getTongTienHD();
				}
				else if (hoaDon.getNgayLap().getHour()==12) {
					h12+=hoaDon.getTongTienHD();
				}
				else if (hoaDon.getNgayLap().getHour()==14) {
					h14+=hoaDon.getTongTienHD();
				}
				else if (hoaDon.getNgayLap().getHour()==15) {
					h15+=hoaDon.getTongTienHD();
				}
			}
		}
        listH9.add(h9);
        listH10.add(h10);
        listH11.add(h11);
        listH12.add(h12);
        listH14.add(h14);
        listH15.add(h15);
        listH16.add(h16);
        listH17.add(h17);
        listH18.add(h18);
        listH19.add(h19);
        listH20.add(h20);
        listH21.add(h21);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelShadow1 = new PanelShadow();
        panelShadow1.setShadowColor(new Color(65, 105, 225));
        panelShadow1.setShadowSize(2);
        chart = new CurveLineChart();

        panelShadow1.setBackground(new java.awt.Color(255, 255, 255));
        panelShadow1.setBorder(new EmptyBorder(10, 10, 10, 10));
        //panelShadow1.setColorGradient(new java.awt.Color(17, 38, 47));
        panelShadow1.setColorGradient(new Color(255,255,255));

        chart.setForeground(Color.BLACK);
        chart.setFillColor(true);

        javax.swing.GroupLayout panelShadow1Layout = new javax.swing.GroupLayout(panelShadow1);
        panelShadow1.setLayout(panelShadow1Layout);
        panelShadow1Layout.setHorizontalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, 1080, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelShadow1Layout.setVerticalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelShadow1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelShadow1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        
    }

    
}
