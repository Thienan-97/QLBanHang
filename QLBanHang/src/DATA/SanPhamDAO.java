/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA;

import DTO.SanPhamDTO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 *
 * @author Shadow
 */
public class SanPhamDAO {
    private  MySQLConnect mySQL = new MySQLConnect();
    public SanPhamDAO() {
       
    }
    public ArrayList<SanPhamDTO> list()
    {
        ArrayList<SanPhamDTO> sp = new ArrayList<>();
        try {
            String sql = "SELECT * FROM sanpham WHERE enable = 1";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next())
            {
                String maSP = rs.getString("MASP");
                String tenSP = rs.getString("TENSP");
                int sl = rs.getInt("SOLUONG");
                int gia = rs.getInt("GIA");
                String DVT = rs.getString("DONVITINH");
                String maLoai = rs.getString("MALOAI");
                String maNsx = rs.getString("MANSX");
                String IMG = rs.getString("IMG");
                SanPhamDTO s = new SanPhamDTO(maSP, tenSP, DVT, maLoai, maNsx, IMG, sl, gia);
                sp.add(s);
            }
            rs.close();
            mySQL.disConnect();

        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sp;
    }
    public void add(SanPhamDTO sp)
    {
        String sql = "INSERT INTO sanpham VALUES (";
        sql += "'"+sp.getMaSP()+"',";
        sql += "N'"+sp.getTenSP()+"',";
        sql += "'"+sp.getSl()+"',";
        sql += "'"+sp.getGia()+"',";
        sql += "N'"+sp.getDvt()+"',";
        sql += "'"+sp.getMaLoai()+"',";
        sql += "'"+sp.getMaNsx()+"',";
        sql += "'"+sp.getImg()+"',";
        sql += "'1')";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }

    public void delete(String idSP)
    {
        String sql = "UPDATE sanpham SET enable = 0 WHERE MaSP='"+idSP+"'";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
        
    }
    
    public void set(SanPhamDTO sp)
    {
        String sql = "UPDATE sanpham SET ";
        sql += "TENSP='"+sp.getTenSP()+"', ";
        sql += "SOLUONG='"+sp.getSl()+"', ";
        sql += "GIA='"+sp.getGia()+"', ";
        sql += "DONVITINH='"+sp.getDvt()+"', ";
        sql += "MALOAI='"+sp.getMaLoai()+"', ";
        sql += "MANSX='"+sp.getMaNsx()+"', ";
        sql += "IMG='"+sp.getImg()+"' ";
        sql += "WHERE MASP='"+sp.getMaSP()+"'";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }
    
    public void ExportExcelDatabase(){
        try{
            String sql = "SELECT * FROM sanpham WHERE 1";
            ResultSet rs = mySQL.executeQuery(sql);
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Sanphamdb");
            
                    
            XSSFFont font = workbook.createFont();
            font.setFontHeightInPoints((short) 12);
            font.setBold(true);
        
            XSSFCellStyle style = workbook.createCellStyle();
            style.setFont(font);
            
            XSSFRow row = sheet.createRow(0);
            XSSFCell cell;
            
            cell = row.createCell(0);
            cell.setCellValue("MASP");
            cell.setCellStyle(style);
            cell = row.createCell(1);
            cell.setCellValue("TENSP");
            cell.setCellStyle(style);
            cell = row.createCell(2);
            cell.setCellValue("SOLUONG");
            cell.setCellStyle(style);
            cell = row.createCell(3);
            cell.setCellValue("GIA");
            cell.setCellStyle(style);
            cell = row.createCell(4);
            cell.setCellValue("DONVITINH");
            cell.setCellStyle(style);
            cell = row.createCell(5);
            cell.setCellValue("MALOAI");
            cell.setCellStyle(style);
            cell = row.createCell(6);
            cell.setCellValue("MANSX");
            cell.setCellStyle(style);
            cell = row.createCell(7);
            cell.setCellValue("IMG");
            cell.setCellStyle(style);
            int i = 1;
        
        while(rs.next()){
            row = sheet.createRow(i);
            cell = row.createCell(0);
            cell.setCellValue(rs.getString("MASP"));
            cell = row.createCell(1);
            cell.setCellValue(rs.getString("TENSP"));
            cell = row.createCell(2);
            cell.setCellValue(rs.getInt("SOLUONG"));
            cell = row.createCell(3);
            cell.setCellValue(rs.getInt("GIA"));
            cell = row.createCell(4);
            cell.setCellValue(rs.getString("DONVITINH"));
            cell = row.createCell(5);
            cell.setCellValue(rs.getString("MALOAI"));
            cell = row.createCell(6);
            cell.setCellValue(rs.getString("MANSX"));
            cell = row.createCell(7);
            cell.setCellValue(rs.getString("IMG"));
            
            i++;
        }
        
        for(int colNum = 0;colNum < row.getLastCellNum();colNum++) {
            sheet.autoSizeColumn((short) (colNum));
        }
        
        FileOutputStream out = new FileOutputStream(new File("./report/sanphamdb.xlsx"));
        workbook.write(out);
        out.close();
        System.out.println("Xuat file thanh cong");
        
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void ImportExcelDatabase(File file){
        try{
            FileInputStream in = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(in);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Row row;
            for(int i = 1; i <= sheet.getLastRowNum(); i++){
                row = sheet.getRow(i);
                String maSP = row.getCell(0).getStringCellValue();
                String tenSP = row.getCell(1).getStringCellValue();
                int sl = (int) row.getCell(2).getNumericCellValue();
                int gia = (int) row.getCell(3).getNumericCellValue();
                String DVT = row.getCell(4).getStringCellValue();
                String maLoai = row.getCell(5).getStringCellValue();
                String maNsx = row.getCell(6).getStringCellValue();
                String IMG = row.getCell(7).getStringCellValue();
                
                String sql_check = "SELECT * FROM sanpham WHERE MaSP='"+maSP+"'";
                ResultSet rs = mySQL.executeQuery(sql_check);
                if(!rs.next()){
                    String sql = "INSERT INTO sanpham VALUES (";
                    sql += "'"+maSP+"',";
                    sql += "N'"+tenSP+"',";
                    sql += "'"+sl+"',";
                    sql += "'"+gia+"',";
                    sql += "N'"+DVT+"',";
                    sql += "'"+maLoai+"',";
                    sql += "'"+maNsx+"',";
                    sql += "'"+IMG+"')";
                    System.out.println(sql);
                    mySQL.executeUpdate(sql);
                }
                else{
                    String sql = "UPDATE sanpham SET ";
                    sql += "TENSP='"+tenSP+"', ";
                    sql += "SOLUONG='"+sl+"', ";
                    sql += "GIA='"+gia+"', ";
                    sql += "DONVITINH='"+DVT+"', ";
                    sql += "MALOAI='"+maLoai+"', ";
                    sql += "MANSX='"+maNsx+"', ";
                    sql += "IMG='"+IMG+"' ";
                    sql += "WHERE MASP='"+maSP+"'";
                    System.out.println(sql);    
                    mySQL.executeUpdate(sql);
                }
            }
            in.close();
          
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
