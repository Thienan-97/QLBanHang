/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;


import DATA.KhuyenMaiDAO;
import DTO.KhuyenMaiDTO;
import java.util.ArrayList;
/**
 *
 * @author thienan
 */

public class KhuyenMaiBUS {
    private ArrayList<KhuyenMaiDTO> dskm ;
    public KhuyenMaiBUS(int i1)
    {
        list();
    }
    
    public KhuyenMaiBUS()
    {
        
    }
    public KhuyenMaiDTO get(String MALOAIGG)
    {
        for(KhuyenMaiDTO km : dskm )
        {
            if(km.getMALOAIGG().equals(MALOAIGG))
            {
                return km;
            }
        }
        return null;
    }
    public void list()
    {
        KhuyenMaiDAO kmDAO = new KhuyenMaiDAO();
        dskm = new ArrayList<>();
        dskm = kmDAO.list();
    }
    public void add(KhuyenMaiDTO km)
    {
        dskm.add(km);
        KhuyenMaiDAO kmDAO = new KhuyenMaiDAO();
        kmDAO.add(km);
    }

    public void delete(String MALOAIGG)
    {
        for(KhuyenMaiDTO km : dskm )
        {
            if(km.getMALOAIGG().equals(MALOAIGG))
            {
                dskm.remove(km);
                KhuyenMaiDAO kmDAO = new KhuyenMaiDAO();
                kmDAO.delete(MALOAIGG);
                return;
            }
        }
    }
    public void set(KhuyenMaiDTO s)
    {
        for(int i = 0 ; i < dskm.size() ; i++)
        {
            if(dskm.get(i).getMALOAIGG().equals(s.getMALOAIGG()))
            {
                dskm.set(i, s);
                KhuyenMaiDAO kmDAO = new KhuyenMaiDAO();
                kmDAO.set(s);
                return;
            }
        }
    }
    public boolean check(String MALOAIGG)
    {
        for(KhuyenMaiDTO km : dskm)
        {
            if(km.getMALOAIGG().equals(MALOAIGG))
            {
                return true;
            }
        }
        return false;
    }
    public ArrayList<KhuyenMaiDTO> search(String MALOAIGG,String MASP,String DOTGG, String BATDAU, String KETTHUC)
    {
        ArrayList<KhuyenMaiDTO> search = new ArrayList<>();
        MALOAIGG = MALOAIGG.isEmpty()?MALOAIGG = "": MALOAIGG;
        MASP = MASP.isEmpty()?MASP = "": MASP;
        DOTGG = DOTGG.isEmpty()?DOTGG = "": DOTGG;
        BATDAU = BATDAU.isEmpty() ?BATDAU = "": BATDAU;
        KETTHUC = KETTHUC.isEmpty() ?KETTHUC = "": KETTHUC;
        for(KhuyenMaiDTO km : dskm)
        {
            if( km.getMALOAIGG().contains(MALOAIGG) && 
                km.getMASP().contains(MASP) &&
                km.getDOTGG().contains(DOTGG) &&
                km.getBATDAU().contains(BATDAU) &&
                km.getKETTHUC().contains(KETTHUC))
            {
                search.add(km);
            }
        }
        return search;
    }
    public ArrayList<KhuyenMaiDTO> getList() {
        return dskm;
    }

}
