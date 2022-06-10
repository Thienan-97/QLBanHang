/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA;

import DTO.NhapHangDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Shadow
 */
public class NhapHangDAO {
    private MySQLConnect mySQL = new MySQLConnect();
    public NhapHangDAO(){}
    public ArrayList<NhapHangDTO> list()
    {
        ArrayList<NhapHangDTO> dsnh = new ArrayList<>();
        try {
           
            String sql = "SELECT * FROM phieunhaphang WHERE 1";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next())
            {
                String idNH = rs.getString("IDNHAP");
                String maNCC = rs.getString("MANCC");
                String maSP = rs.getString("MASP");
                String ngayNH = rs.getString("NGAYNHAP");
                int dongiaNhap = rs.getInt("DONGIANHAP");
                int soLuong = rs.getInt("SOLUONG");
                int tongtien = rs.getInt("TONGTIEN");

                NhapHangDTO ct = new NhapHangDTO(idNH,maNCC, maSP, ngayNH, dongiaNhap, soLuong, tongtien);
                dsnh.add(ct);
            }
            rs.close();
            mySQL.disConnect();
            
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dsnh;
    }
    public void add(NhapHangDTO nh) {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "INSERT INTO phieunhaphang VALUES (";
               sql += "'"+nh.getIdNH()+"',";
               sql += "'"+nh.getMaNCC()+"',";
               sql += "'"+nh.getMaSP()+"',";
               sql += "'"+nh.getNgayNhap()+"',";
               sql += "'"+nh.getDonGiaNhap()+"',";
               sql += "'"+nh.getSoLuong()+"',";
               sql += "'"+nh.getTongTien()+"')";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }
    public void set(NhapHangDTO nh)
    {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "UPDATE phieunhaphang SET ";
            sql += "NGAYNHAP='"+nh.getNgayNhap()+"', ";
            sql += "DONGIANHAP='"+nh.getDonGiaNhap()+"', ";
            sql += "TONGTIEN='"+nh.getSoLuong()+"', ";
            sql += "SOLUONG='"+nh.getTongTien()+"' ";
            sql += "WHERE IDNHAP ='"+nh.getIdNH()+"'";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }
    public void delete(String idNH)
    {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "DELETE FROM phieunhaphang WHERE IDNHAP ='"+idNH+"'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
}
