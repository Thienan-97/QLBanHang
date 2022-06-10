/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA;

import DTO.LoaiDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Shadow
 */
public class LoaiDAO {
    private MySQLConnect mySQL = new MySQLConnect();
    public LoaiDAO()
    {
        
    }
    public ArrayList<LoaiDTO> list()
    {
        ArrayList<LoaiDTO> dsnv = new ArrayList<>();
        try {
           
            String sql = "SELECT * FROM loai WHERE 1";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next())
            {
                String maLoai = rs.getString("MALOAI");
                String tenLoai = rs.getString("TENLOAI");

                LoaiDTO nv = new LoaiDTO(maLoai, tenLoai);
                dsnv.add(nv);
            }
            rs.close();
            mySQL.disConnect();
            
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dsnv;
    }

    public void set(LoaiDTO nv) {
            MySQLConnect mySQL = new MySQLConnect();
            String sql = "UPDATE loai SET ";
            sql += "TENLOAI='"+nv.getTenLoai()+"', ";
            sql += " WHERE MANV='"+nv.getMaLoai()+"'";
            System.out.println(sql);
            
            mySQL.executeUpdate(sql);
    }

    public void add(LoaiDTO nv) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "INSERT INTO loai VALUES (";
               sql += "'"+nv.getMaLoai()+"',";
               sql += "'"+nv.getTenLoai()+"',";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }
    
    public void delete(String MaNV)
    {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "DELETE FROM loai WHERE MANV='"+MaNV+"'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
}
