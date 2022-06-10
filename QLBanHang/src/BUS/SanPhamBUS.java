/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DATA.SanPhamDAO;
import DTO.SanPhamDTO;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Minh Minion
 */
public class SanPhamBUS {
    private ArrayList<SanPhamDTO> dssp ;
    public SanPhamBUS()
    {
        
    }
    public void listSP()
    {
        SanPhamDAO spDAO = new SanPhamDAO();
        dssp = new ArrayList<>();
        dssp = spDAO.list();
    }
    public void addSP(SanPhamDTO sp)
    {
        dssp.add(sp);
        SanPhamDAO spDAO = new SanPhamDAO();
        spDAO.add(sp);
    }

    public void deleteSP(String idSP)
    {
        for(SanPhamDTO sp : dssp )
        {
            if(sp.getMaSP().equals(idSP))
            {
                dssp.remove(sp);
                SanPhamDAO spDAO = new SanPhamDAO();
                spDAO.delete(idSP);
                return;
            }
        }
    }
    public void setSP(SanPhamDTO s)
    {
        for(int i = 0 ; i < dssp.size() ; i++)
        {
            if(dssp.get(i).getMaSP().equals(s.getMaSP()))
            {
                dssp.set(i, s);
                SanPhamDAO spDAO = new SanPhamDAO();
                spDAO.set(s);
                return;
            }
        }
    }
    public boolean checkMasp(String masp)
    {
        for(SanPhamDTO sp : dssp)
        {
            if(sp.getMaSP().equals(masp))
            {
                return true;
            }
        }
        return false;
    }
    public SanPhamDTO getSP(String masp)
    {
        for(SanPhamDTO sp : dssp)
        {
            if(sp.getMaSP().equals(masp))
            {
                return sp;
            }
        }
        return null;
    }
    public boolean updateSL(String masp,int sl)
    {
        for(SanPhamDTO sp : dssp)
         {
             if(sp.getMaSP().equals(masp))
             {
                
                int old = sp.getSl();
                if(sl > old)
                {
                    JOptionPane.showMessageDialog(null, "Không đủ hàng");
                    return false;
                }
                old -= sl;
                sp.setSl(old);
                SanPhamDAO spDAO = new SanPhamDAO();
                spDAO.set(sp);
                System.out.println(sp.getSl());
                return true;
             }
         }
         return false;
    }
    public boolean checkSL(String masp , int sl)
    {
        for(SanPhamDTO sp : dssp)
         {
             if(sp.getMaSP().equals(masp))
             {
                if(sl > sp.getSl())
                {
                    JOptionPane.showMessageDialog(null, "Không đủ hàng");
                    return false;
                }
             }
         }
         return true;
    }
    public ArrayList<SanPhamDTO> searchSP(String masp,String maloai,String mansx,int max,int min)
    {
        ArrayList<SanPhamDTO> search = new ArrayList<>();
        masp = masp.isEmpty()?masp = "": masp;
        maloai = maloai.isEmpty()?maloai = "": maloai;
        mansx = mansx.isEmpty()?mansx = "": mansx;
        for(SanPhamDTO sp : dssp)
        {
            if( sp.getMaSP().contains(masp) && 
                sp.getMaLoai().contains(maloai) &&
                sp.getMaNsx().contains(mansx) &&
                sp.getGia() >= min && 
                sp.getGia() <= max)
            {
                search.add(sp);
            }
        }
        return search;
    }
    public ArrayList<SanPhamDTO> getList() {
        return dssp;
    }
    
    public void ExportExcelDatabase(){
        SanPhamDAO spDAO = new SanPhamDAO();
        spDAO.ExportExcelDatabase();
    }
    
    public void ImportExcelDatabase(File file){
        SanPhamDAO spDAO = new SanPhamDAO();
        spDAO.ImportExcelDatabase(file);
    }
    
}
