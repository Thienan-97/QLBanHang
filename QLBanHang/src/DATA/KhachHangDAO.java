/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA;

import DTO.KhachHangDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Shadow
 */
public class KhachHangDAO {
    private  MySQLConnect mySQL = new MySQLConnect();
    public KhachHangDAO() {
    }
    public ArrayList<KhachHangDTO> list()
    {
        ArrayList<KhachHangDTO> dskh = new ArrayList<>();
        try {
           
            String sql = "SELECT * FROM khachhang WHERE enable = 1";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next())
            {
                String maNV = rs.getString("MAKH");
                String hoNV = rs.getString("HOKH");
                String tenNV = rs.getString("TENKH");
                String diaChi = rs.getString("DIACHI");
                int sdt = rs.getInt("SDT");

                KhachHangDTO kh = new KhachHangDTO(maNV, hoNV, tenNV, diaChi, sdt);
                dskh.add(kh);
            }
            rs.close();
            mySQL.disConnect();
            
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dskh;
    }

    public void set(KhachHangDTO kh) {
            MySQLConnect mySQL = new MySQLConnect();
            String sql = "UPDATE khachhang SET ";
            sql += "HOKH='"+kh.getHoKH()+"', ";
            sql += "TENKH='"+kh.getTenKH()+"', ";
            sql += "DIACHI='"+kh.getDiaChi()+"', ";
            sql += "SDT='"+kh.getSdt()+"' ";
            sql += " WHERE MAKH='"+kh.getMaKH()+"'";
            System.out.println(sql);
            
            mySQL.executeUpdate(sql);
    }

public void add(KhachHangDTO kh)
{
   
        MySQLConnect mySQL = new MySQLConnect();
            String sql = "INSERT INTO VALUES (";
            sql+="'"+kh.getMaKH()+"',";
            sql+="'"+kh.getHoKH()+"',";
            sql+="'"+kh.getTenKH()+"',";
            sql+="'"+kh.getDiaChi()+"')";
            mySQL.executeUpdate(sql);
            System.out.println(sql);
}
    public void delete(String MaKH)
    {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "UPDATE khachhang SET enable = 0 WHERE MAKH='"+MaKH+"'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
}
