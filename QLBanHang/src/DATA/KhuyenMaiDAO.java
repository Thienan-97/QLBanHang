/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA;

import DTO.KhachHangDTO;
import DTO.KhuyenMaiDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thienan
 */
public class KhuyenMaiDAO {
    private  MySQLConnect mySQL = new MySQLConnect();
    public KhuyenMaiDAO() {
    }
    public ArrayList<KhuyenMaiDTO> list()
    {
        ArrayList<KhuyenMaiDTO> dskm = new ArrayList<>();
        try {
           
            String sql = "SELECT * FROM thongtingiamgia WHERE 1";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next())
            {
                String MALOAIGG = rs.getString("MALOAIGG");
                String MASP = rs.getString("MASP");
                String DOTGG = rs.getString("DOTGG");
                String BATDAU = rs.getString("BATDAU");
                String KETTHUC = rs.getString("KETTHUC");

                KhuyenMaiDTO km = new KhuyenMaiDTO(MALOAIGG, MASP, DOTGG, BATDAU, KETTHUC);
                dskm.add(km);
            }
            rs.close();
            mySQL.disConnect();
            
        } catch (SQLException ex) {
            Logger.getLogger(KhuyenMaiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dskm;
    }
    
    public void set(KhuyenMaiDTO km) {
            MySQLConnect mySQL = new MySQLConnect();
            String sql = "UPDATE thongtingiamgia SET ";
            sql += "TENKH='"+km.getMASP()+"', ";
            sql += "DIACHI='"+km.getDOTGG()+"', ";
            sql += "SDT='"+km.getBATDAU()+"' ";
            sql += " WHERE MALOAIGG='"+km.getMALOAIGG()+"'";
            System.out.println(sql);
            
            mySQL.executeUpdate(sql);
    }

public void add(KhuyenMaiDTO km)
{
   
        MySQLConnect mySQL = new MySQLConnect();
            String sql = "INSERT INTO VALUES (";
            sql+="'"+km.getMALOAIGG()+"',";
            sql+="'"+km.getMASP()+"',";
            sql+="'"+km.getDOTGG()+"',";
            sql+="'"+km.getBATDAU()+"')";
            sql+="'"+km.getKETTHUC()+"')";
            mySQL.executeUpdate(sql);
            System.out.println(sql);
}
    public void delete(String MALOAIGG)
    {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "UPDATE thongtingiamgia SET enable = 0 WHERE MALOAIGG='"+MALOAIGG+"'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
}



