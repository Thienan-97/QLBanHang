
import DATA.MySQLConnect;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.view.JasperViewer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Shadow
 */
public class TestJasper {
    public static void main(String[] args) throws JRException,
           ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
       UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
       String reportSrcFile = "./src/report1.jrxml";
       
       // Compile file nguồn trước.
       JasperReport jasperReport =    JasperCompileManager.compileReport(reportSrcFile);
       MySQLConnect mySQL = new MySQLConnect();
       
       Connection conn = mySQL.getConnection();
 
       // Tham số truyền vào báo cáo.
       Map<String, Object> parameters = new HashMap<String, Object>();
 
       JasperPrint print = JasperFillManager.fillReport(jasperReport,
               parameters, conn);
 
       JasperViewer.viewReport(print);
       
       /***************** Xuất ra file pdf ******************************/
       // Đảm bảo thư mục đầu ra tồn tại.
       File outDir = new File(".\\QLBanHang\\report");
       outDir.mkdirs();
 
       // PDF Exportor.
       JRPdfExporter exporter = new JRPdfExporter();
 
       ExporterInput exporterInput = new SimpleExporterInput(print);
       // ExporterInput
       exporter.setExporterInput(exporterInput);
 
       // ExporterOutput
       OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(
               ".\\QLBanHang\\report\\FirstJasperReport.pdf");
       // Output
       exporter.setExporterOutput(exporterOutput);
 
       //
       SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
       exporter.setConfiguration(configuration);
       exporter.exportReport();
 
       System.out.print("Done!");
   }
}
