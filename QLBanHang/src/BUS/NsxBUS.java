/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DATA.NsxDAO;
import DTO.NsxDTO;
import java.util.ArrayList;

/**
 *
 * @author Shadow
 */
public class NsxBUS {

    private ArrayList<NsxDTO> dsNsx ;
    public NsxBUS()
    {
        
    }
    public void listNSX()
    {
        NsxDAO nsxDAO = new NsxDAO();
        dsNsx = new ArrayList<>();
        dsNsx = nsxDAO.list();
    }
    public void addNsx(NsxDTO nsx)
    {
        dsNsx.add(nsx);
        NsxDAO nsxDAO = new NsxDAO();
        nsxDAO.add(nsx);
    }

    public void deleteNsx(String idNsx)
    {
        for(NsxDTO nsx : dsNsx )
        {
            if(nsx.getMaNSX().equals(idNsx))
            {
                dsNsx.remove(nsx);
                NsxDAO nsxDAO = new NsxDAO();
                nsxDAO.delete(idNsx);
                return;
            }
        }
    }
    public void setNsx(NsxDTO s)
    {
        for(int i = 0 ; i < dsNsx.size() ; i++)
        {
            if(dsNsx.get(i).getMaNSX().equals(s.getMaNSX()))
            {
                dsNsx.set(i, s);
                NsxDAO nsxDAO = new NsxDAO();
                nsxDAO.set(s);
                return;
            }
        }
    }
    public NsxDTO searchMaNsx(String mansx)
    {
        for(NsxDTO nsx : dsNsx)
        {
            if( nsx.getMaNSX().equals(mansx))
            {
                return nsx;
            }
        }
        return null;
    }
    public ArrayList<NsxDTO> searchNsx(String mansx,String tennsx)
    {
        ArrayList<NsxDTO> search = new ArrayList<>();
        mansx = mansx.isEmpty()?mansx = "": mansx;
        tennsx = tennsx.isEmpty()?tennsx = "": tennsx;
        for(NsxDTO nsx : dsNsx)
        {
            if( nsx.getMaNSX().contains(mansx) && 
                nsx.getTenNSX().contains(tennsx))
            {
                search.add(nsx);
            }
        }
        return search;
    }
    public ArrayList<NsxDTO> getList() {
        return dsNsx;
    }
}
