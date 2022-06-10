/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA;

/**
 *
 * @author thienan
 */
import DATA.MySQLConnect;
import DATA.LoaiGGDAO;
import DTO.LoaiGGDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoaiGGDAO {
    private MySQLConnect mySQL = new MySQLConnect();
    public LoaiGGDAO()
    {
        
    }
    public ArrayList<LoaiGGDTO> list()
    {
        ArrayList<LoaiGGDTO> dsct = new ArrayList<>();
        try {
           
            String sql = "SELECT * FROM loaigg order by MALOAIGG asc ";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next())
            {
                String MALOAIGG = rs.getString("MALOAIGG");
                int PHANTRAMGIAMGIA = rs.getInt("PHANTRAMGG");

               LoaiGGDTO ct = new LoaiGGDTO(MALOAIGG, PHANTRAMGIAMGIA);
                dsct.add(ct);
            }
            rs.close();
            mySQL.disConnect();
            
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dsct;
    }


    public void add(LoaiGGDTO ct) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "INSERT INTO chitiethd VALUES (";
               sql += "'"+ct.getMALOAIGG()+"',";
               sql += "'"+ct.getPHANTRAMGG()+"',";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }
    
    public void delete(String MALOAIGG)
    {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "DELETE FROM loaigg WHERE MALOAIGG='"+MALOAIGG+"'";  
        mySQL.executeUpdate(sql);
        System.out.println(sql);
        
        
    }
}
