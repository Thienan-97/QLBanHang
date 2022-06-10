/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA;

import DTO.NhanVienDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Shadow
 */
public class NhanVienDAO {
    private  MySQLConnect mySQL = new MySQLConnect();
    public NhanVienDAO() {
    }
    public ArrayList<NhanVienDTO> list()
    {
        ArrayList<NhanVienDTO> dsnv = new ArrayList<>();
        try {
           
            String sql = "SELECT * FROM nhanvien WHERE enable = 1";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next())
            {
                String maNV = rs.getString("MANV");
                String hoNV = rs.getString("HONV");
                String tenNV = rs.getString("TENNV");
                int namsinh = rs.getInt("NAMSINH");
                String phai = rs.getString("PHAI");
                int mucLuong = rs.getInt("MUCLUONG");
                String diaChi = rs.getString("DIACHI");
                String IMG = rs.getString("IMG");

                NhanVienDTO nv = new NhanVienDTO(maNV, hoNV, tenNV, namsinh, phai, mucLuong, diaChi, IMG);
                dsnv.add(nv);
            }
            rs.close();
            mySQL.disConnect();
            
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dsnv;
    }

    public void set(NhanVienDTO nv) {
            MySQLConnect mySQL = new MySQLConnect();
            String sql = "UPDATE nhanvien SET ";
            sql += "HONV='"+nv.getHoNV()+"', ";
            sql += "TENNV='"+nv.getTenNV()+"', ";
            sql += "NAMSINH='"+nv.getNamSinh()+"', ";
            sql += "PHAI='"+nv.getPhai()+"', ";
            sql += "MUCLUONG='"+nv.getMucLuong()+"', ";
            sql += "DIACHI='"+nv.getDiaChi()+"', ";
            sql += "IMG='"+nv.getImg()+"' ";
            sql += " WHERE MANV='"+nv.getMaNV()+"'";
            System.out.println(sql);
            
            mySQL.executeUpdate(sql);
    }

    public void add(NhanVienDTO nv) {
        MySQLConnect mySQL = new MySQLConnect();
         String sql = "INSERT INTO nhanvien VALUES (";
                sql += "'"+nv.getMaNV()+"',";
                sql += "'"+nv.getHoNV()+"',";
                sql += "'"+nv.getTenNV()+"',";
                sql += "'"+nv.getNamSinh()+"',";
                sql += "'"+nv.getPhai()+"',";
                sql += "'"+nv.getMucLuong()+"',";
                sql += "'"+nv.getDiaChi()+"',";
                sql += "'"+nv.getImg()+"',";
                sql += "'1')";
         System.out.println(sql);
         mySQL.executeUpdate(sql);
    }
    
    public void delete(String MaNV)
    {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "UPDATE nhanvien SET enable = 0 WHERE MANV='"+MaNV+"'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
}