/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DATA.KhachHangDAO;
import DTO.KhachHangDTO;
import DTO.NhanVienDTO;
import java.util.ArrayList;

/**
 *
 * @author Shadow
 */
public class KhachHangBUS {
    private ArrayList<KhachHangDTO> dskh ;
    public KhachHangBUS(int i1)
    {
        list();
    }
    
    public KhachHangBUS()
    {
        
    }
    public KhachHangDTO get(String MaKH)
    {
        for(KhachHangDTO kh : dskh )
        {
            if(kh.getMaKH().equals(MaKH))
            {
                return kh;
            }
        }
        return null;
    }
    public void list()
    {
        KhachHangDAO khDAO = new KhachHangDAO();
        dskh = new ArrayList<>();
        dskh = khDAO.list();
    }
    public void add(KhachHangDTO kh)
    {
        dskh.add(kh);
        KhachHangDAO khDAO = new KhachHangDAO();
        khDAO.add(kh);
    }

    public void delete(String MaKH)
    {
        for(KhachHangDTO kh : dskh )
        {
            if(kh.getMaKH().equals(MaKH))
            {
                dskh.remove(kh);
                KhachHangDAO khDAO = new KhachHangDAO();
                khDAO.delete(MaKH);
                return;
            }
        }
    }
    public void set(KhachHangDTO s)
    {
        for(int i = 0 ; i < dskh.size() ; i++)
        {
            if(dskh.get(i).getMaKH().equals(s.getMaKH()))
            {
                dskh.set(i, s);
                KhachHangDAO khDAO = new KhachHangDAO();
                khDAO.set(s);
                return;
            }
        }
    }
    public boolean check(String makh)
    {
        for(KhachHangDTO kh : dskh)
        {
            if(kh.getMaKH().equals(makh))
            {
                return true;
            }
        }
        return false;
    }
    public ArrayList<KhachHangDTO> search(String makh,String ho,String ten)
    {
        ArrayList<KhachHangDTO> search = new ArrayList<>();
        makh = makh.isEmpty()?makh = "": makh;
        ho = ho.isEmpty()?ho = "": ho;
        ten = ten.isEmpty()?ten = "": ten;
        for(KhachHangDTO kh : dskh)
        {
            if( kh.getMaKH().contains(makh) && 
                kh.getHoKH().contains(ho) &&
                kh.getTenKH().contains(ten))
            {
                search.add(kh);
            }
        }
        return search;
    }
    public ArrayList<KhachHangDTO> getList() {
        return dskh;
    }

}
