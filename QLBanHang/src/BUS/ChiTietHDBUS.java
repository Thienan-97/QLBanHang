/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DATA.ChiTietHDDAO;
import DTO.ChiTietHDDTO;
import java.util.ArrayList;

/**
 *
 * @author Minh Minion
 */
public class ChiTietHDBUS {
    private ArrayList<ChiTietHDDTO> dsChiTietHD ;
    public ChiTietHDBUS()
    {
        
    }
    public ChiTietHDBUS(int i)
    {
        list();
    }
    public void list()
    {
        ChiTietHDDAO loaiDAO = new ChiTietHDDAO();
        dsChiTietHD = new ArrayList<>();
        dsChiTietHD = loaiDAO.list();
    }
    public void add(ChiTietHDDTO loai)
    {
        dsChiTietHD.add(loai);
        ChiTietHDDAO loaiDAO = new ChiTietHDDAO();
        loaiDAO.add(loai);
    }

    public void delete(String idChiTietHD)
    {
        for(ChiTietHDDTO ct : dsChiTietHD )
        {
            if(ct.getMaHD().equals(idChiTietHD))
            {
                dsChiTietHD.remove(ct);
                ChiTietHDDAO loaiDAO = new ChiTietHDDAO();
                loaiDAO.delete(idChiTietHD);
                return;
            }
        }
    }
    
    public void set(ChiTietHDDTO s)
    {
        for(int i = 0 ; i < dsChiTietHD.size() ; i++)
        {
            if(dsChiTietHD.get(i).getMaHD().equals(s.getMaHD()))
            {
                dsChiTietHD.set(i, s);
//                ChiTietHDDAO loaiDAO = new ChiTietHDDAO();
//                loaiDAO.setChiTietHD(s);
                return;
            }
        }
    }
    public ChiTietHDDTO search(String maHD)
    {
        for(ChiTietHDDTO ct : dsChiTietHD)
        {
            if( ct.getMaHD().equals(maHD))
            {
                return ct;
            }
        }
        return null;
    }
    public ArrayList<String> getHD(String maSP)
    {
        ArrayList<String> s = new ArrayList<>();
        if(maSP.isEmpty()) return null;
        for(ChiTietHDDTO ct : dsChiTietHD)
        {
            if(ct.getMaSP().equals(maSP))
            {
                s.add(ct.getMaHD());
            }
        }
        return s;
    }
    public ArrayList<ChiTietHDDTO> getListHD(String maHD)
    {
        ArrayList<ChiTietHDDTO> ds = new ArrayList<>();
        for(ChiTietHDDTO ct : dsChiTietHD)
        {
            if( ct.getMaHD().equals(maHD))
            {
                ds.add(ct);
            }
        }
        return ds; 
    }
    public ArrayList<ChiTietHDDTO> getList() {
        return dsChiTietHD;
    }
}
