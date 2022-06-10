/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA;

import DTO.NhaCungCapDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author anhkhoa
 */
public class NhaCungCapDAO {
    private MySQLConnect mySQL = new MySQLConnect();
    public NhaCungCapDAO(){}
    
    public ArrayList<NhaCungCapDTO> list(){
        ArrayList<NhaCungCapDTO> dsncc = new ArrayList<>();
        try {
           
            String sql = "SELECT * FROM nhacungcap WHERE enable = 1";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next())
            {
                String maNCC = rs.getString("MANCC");
                String tenNCC = rs.getString("TENNCC");
                String diaChi = rs.getString("DIACHINCC");
                String dienThoai = rs.getString("DIENTHOAI");
                String soFax = rs.getString("SOFAX");

                NhaCungCapDTO ncc = new NhaCungCapDTO(maNCC, tenNCC, diaChi, dienThoai, soFax);
                dsncc.add(ncc);
            }
            rs.close();
            mySQL.disConnect();
            
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dsncc;
    }

    public void set(NhaCungCapDTO ncc) {
            MySQLConnect mySQL = new MySQLConnect();
            String sql = "UPDATE nhacungcap SET ";
            sql += "TENNCC='"+ncc.getTenNCC()+"', ";
            sql += "DIACHI='"+ncc.getDiaChi()+"', ";
            sql += "DIENTHOAI='"+ncc.getDienThoai()+"', ";
            sql += "SOFAX='"+ncc.getSoFax()+"', ";
            sql += " WHERE MANCC='"+ncc.getMaNCC()+"'";
            System.out.println(sql);
            
            mySQL.executeUpdate(sql);
    }

    public void add(NhaCungCapDTO ncc) {
        MySQLConnect mySQL = new MySQLConnect();
         String sql = "INSERT INTO nhacungcap VALUES (";
                sql += "'"+ncc.getMaNCC()+"',";
                sql += "'"+ncc.getTenNCC()+"',";
                sql += "'"+ncc.getDiaChi()+"',";
                sql += "'"+ncc.getDienThoai()+"',";
                sql += "'"+ncc.getSoFax()+"',";
                sql += "'1')";
         System.out.println(sql);
         mySQL.executeUpdate(sql);
    }
    
    public void delete(String maNCC)
    {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "UPDATE nhacungcap SET enable = 0 WHERE MANCC='"+maNCC+"'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }

    
}
