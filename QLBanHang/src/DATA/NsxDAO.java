/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA;

import DTO.NsxDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Shadow
 */
public class NsxDAO {
    private MySQLConnect mySQL = new MySQLConnect();
    public NsxDAO()
    {
        
    }
    public ArrayList<NsxDTO> list()
    {
        ArrayList<NsxDTO> dsnv = new ArrayList<>();
        try {
           
            String sql = "SELECT * FROM nhasanxuat WHERE 1";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next())
            {
                String maNsx = rs.getString("MANSX");
                String tenNsx = rs.getString("TENNSX");

                NsxDTO nv = new NsxDTO(maNsx, tenNsx);
                dsnv.add(nv);
            }
            rs.close();
            mySQL.disConnect();
            
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dsnv;
    }

    public void set(NsxDTO nv) {
            MySQLConnect mySQL = new MySQLConnect();
            String sql = "UPDATE nhasanxuat SET ";
            sql += "TENNSX='"+nv.getTenNSX()+"', ";
            sql += " WHERE MANV='"+nv.getMaNSX()+"'";
            System.out.println(sql);
            
            mySQL.executeUpdate(sql);
    }

    public void add(NsxDTO nv) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "INSERT INTO nhasanxuat VALUES (";
               sql += "'"+nv.getMaNSX()+"',";
               sql += "'"+nv.getTenNSX()+"',";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }
    
    public void delete(String MaNV)
    {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "DELETE FROM nhasanxuat WHERE MANV='"+MaNV+"'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
}

