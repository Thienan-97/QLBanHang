/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA;

import DATA.MySQLConnect;
import DATA.NhanVienDAO;
import DTO.ChiTietHDDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Minh Minion
 */
public class ChiTietHDDAO {
    private MySQLConnect mySQL = new MySQLConnect();
    public ChiTietHDDAO()
    {
        
    }
    public ArrayList<ChiTietHDDTO> list()
    {
        ArrayList<ChiTietHDDTO> dsct = new ArrayList<>();
        try {
           
            String sql = "SELECT * FROM chitiethd WHERE 1";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next())
            {
                String maHD = rs.getString("MAHD");
                String maSP = rs.getString("MASP");
                String tenSP = rs.getString("TENSP");
                int sl = rs.getInt("SOLUONG");
                int dongia = rs.getInt("DONGIA");

                ChiTietHDDTO ct = new ChiTietHDDTO(maHD, maSP, tenSP, dongia, sl);
                dsct.add(ct);
            }
            rs.close();
            mySQL.disConnect();
            
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dsct;
    }

//    public void setChiTietHD(ChiTietHDDTO nv) {
//            MySQLConnect mySQL = new MySQLConnect();
//            String sql = "UPDATE chitiethhd SET ";
//            sql += "TENLOAI='"+nv.getTenChiTietHD()+"', ";
//            sql += " WHERE MANV='"+nv.getMaChiTietHD()+"'";
//            System.out.println(sql);
//            
//            mySQL.executeUpdate(sql);
//    }

    public void add(ChiTietHDDTO ct) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "INSERT INTO chitiethd VALUES (";
               sql += "'"+ct.getMaHD()+"',";
               sql += "'"+ct.getMaSP()+"',";
               sql += "'"+ct.getTenSP()+"',";
               sql += "'"+ct.getSl()+"',";
               sql += "'"+ct.getGia()+"')";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }
    
    public void delete(String MaHD)
    {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "DELETE FROM chitiethd WHERE MAHD='"+MaHD+"'";  
        mySQL.executeUpdate(sql);
        System.out.println(sql);
        
        
    }
}
