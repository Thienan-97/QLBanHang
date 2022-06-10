/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;
import DATA.LoaiGGDAO;
import DTO.LoaiGGDTO;
import java.util.ArrayList;

public class LoaiGGBUS {
    private ArrayList<LoaiGGDTO> dsLoaiGG ;
    public LoaiGGBUS()
    {
        
    }
    public LoaiGGBUS(int i)
    {
        list();
    }
    public void list()
    {
        LoaiGGDAO loaiDAO = new LoaiGGDAO();
        dsLoaiGG = new ArrayList<>();
        dsLoaiGG = loaiDAO.list();
    }
    public void add(LoaiGGDTO loai)
    {
        dsLoaiGG.add(loai);
        LoaiGGDAO loaiDAO = new LoaiGGDAO();
        loaiDAO.add(loai);
    }

    public void delete(String MALOAIGG)
    {
        for(LoaiGGDTO ct : dsLoaiGG )
        {
            if(ct.getMALOAIGG().equals(MALOAIGG))
            {
                dsLoaiGG.remove(ct);
                LoaiGGDAO loaiDAO = new LoaiGGDAO();
                loaiDAO.delete(MALOAIGG);
                return;
            }
        }
    }
    
    public void set(LoaiGGDTO s)
    {
        for(int i = 0 ; i < dsLoaiGG.size() ; i++)
        {
            if(dsLoaiGG.get(i).getMALOAIGG().equals(s.getMALOAIGG()))
            {
                dsLoaiGG.set(i, s);
                return;
            }
        }
    }
    public LoaiGGDTO search(String MALOAIGG)
    {
        for(LoaiGGDTO ct : dsLoaiGG)
        {
            if( ct.getMALOAIGG().equals(MALOAIGG))
            {
                return ct;
            }
        }
        return null;
    }
    public ArrayList<String> getLoaiGG(String MALOAIGG)
    {
        ArrayList<String> s = new ArrayList<>();
        if(MALOAIGG.isEmpty()) return null;
        for(LoaiGGDTO ct : dsLoaiGG)
        {
            if(ct.getMALOAIGG().equals(MALOAIGG))
            {
                s.add(ct.getMALOAIGG());
            }
        }
        return s;
    }
    public ArrayList<LoaiGGDTO> getListLoaiGG(String MALOAIGG)
    {
        ArrayList<LoaiGGDTO> ds = new ArrayList<>();
        for(LoaiGGDTO ct : dsLoaiGG)
        {
            if( ct.getMALOAIGG().equals(MALOAIGG))
            {
                ds.add(ct);
            }
        }
        return ds; 
    }
    public ArrayList<LoaiGGDTO> getList() {
        return dsLoaiGG;
    }
}
