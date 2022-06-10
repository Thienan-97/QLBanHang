/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DATA.LoaiDAO;
import DTO.LoaiDTO;
import java.util.ArrayList;

/**
 *
 * @author Shadow
 */
public class LoaiBUS {
    private ArrayList<LoaiDTO> dsLoai ;
    public LoaiBUS()
    {
        
    }
    public void listLoai()
    {
        LoaiDAO loaiDAO = new LoaiDAO();
        dsLoai = new ArrayList<>();
        dsLoai = loaiDAO.list();
    }
    public void addLoai(LoaiDTO loai)
    {
        dsLoai.add(loai);
        LoaiDAO loaiDAO = new LoaiDAO();
        loaiDAO.add(loai);
    }

    public void deleteLoai(String idLoai)
    {
        for(LoaiDTO loai : dsLoai )
        {
            if(loai.getMaLoai().equals(idLoai))
            {
                dsLoai.remove(loai);
                LoaiDAO loaiDAO = new LoaiDAO();
                loaiDAO.delete(idLoai);
                return;
            }
        }
    }
    public void setLoai(LoaiDTO s)
    {
        for(int i = 0 ; i < dsLoai.size() ; i++)
        {
            if(dsLoai.get(i).getMaLoai().equals(s.getMaLoai()))
            {
                dsLoai.set(i, s);
                LoaiDAO loaiDAO = new LoaiDAO();
                loaiDAO.set(s);
                return;
            }
        }
    }
    public LoaiDTO searchMaLoai(String maloai)
    {
        for(LoaiDTO loai : dsLoai)
        {
            if( loai.getMaLoai().equals(maloai))
            {
                return loai;
            }
        }
        return null;
    }
    public ArrayList<LoaiDTO> searchLoai(String maloai,String tenloai)
    {
        ArrayList<LoaiDTO> search = new ArrayList<>();
        maloai = maloai.isEmpty()?maloai = "": maloai;
        tenloai = tenloai.isEmpty()?tenloai = "": tenloai;
        for(LoaiDTO loai : dsLoai)
        {
            if( loai.getMaLoai().contains(maloai) && 
                loai.getTenLoai().contains(tenloai))
            {
                search.add(loai);
            }
        }
        return search;
    }
    public ArrayList<LoaiDTO> getList() {
        return dsLoai;
    }
}
