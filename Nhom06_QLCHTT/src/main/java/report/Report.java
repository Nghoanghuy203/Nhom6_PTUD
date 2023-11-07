package report;

import java.util.Hashtable;

import connectDB.ConnectDB;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;


public class Report {

	public static void xuatHoaDonPDF (String ma, Object obj, String pathFile, Boolean xuatHoaDon) {
		String src = "src\\main\\java\\report\\hoaDon.jrxml" ;
		
		try {
			Hashtable<String, Object> map = new Hashtable<String, Object>();
			JasperReport report = JasperCompileManager.compileReport(src);
			map.put(ma, obj);
			JasperPrint print = JasperFillManager.fillReport(report, map, ConnectDB.con) ;
			JasperViewer.viewReport(print, false);
			
			if(xuatHoaDon) {
				JasperExportManager.exportReportToPdfFile(print, pathFile);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	public static void xuatThongKeDoanhThuPDF (String doanhThu, String pathFile) {
		String src = "src\\report\\thongKeDoanhThu.jrxml" ;
		
		try {
			Hashtable<String, Object> map = new Hashtable<String, Object>();
			JasperReport report = JasperCompileManager.compileReport(src);
			map.put("maNV", contains.getMaNV());
			map.put("tenNV", contains.getTenTK());
			map.put("ngayLapTK", Format.getCurrentDateAndTime());
			map.put("doanhThu", doanhThu);
			
			JasperPrint print = JasperFillManager.fillReport(report, map, ConnectDB.con) ;
			JasperViewer.viewReport(print, false);
			
			JasperExportManager.exportReportToPdfFile(print, pathFile);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void xuatThongKeKhoHangPDF (String pathFile) {
		String src = "src\\report\\thongKeKhoHang.jrxml" ;
		
		try {
			Hashtable<String, Object> map = new Hashtable<String, Object>();
			JasperReport report = JasperCompileManager.compileReport(src);
			map.put("ngayLapTK", Format.getCurrentDateAndTime());
			map.put("maNV", contains.getMaNV());
			map.put("tenNV", contains.getTenTK());
			
			JasperPrint print = JasperFillManager.fillReport(report, map, ConnectDB.con) ;
			JasperViewer.viewReport(print, false);
			
			JasperExportManager.exportReportToPdfFile(print, pathFile);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
}
