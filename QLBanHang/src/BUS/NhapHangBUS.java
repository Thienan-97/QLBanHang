/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DATA.NhapHangDAO;
import DTO.NhapHangDTO;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Shadow
 */
public class NhapHangBUS {
     private ArrayList<NhapHangDTO> dsNH;
    public NhapHangBUS()
    {
        
    }
    public NhapHangBUS(int i)
    {
        list();
    }
    public void list()
    {
        NhapHangDAO nhDAO = new NhapHangDAO();
        dsNH= new ArrayList<>();
        dsNH= nhDAO.list();
    }
    public void add(NhapHangDTO nh)
    {
        int id = 0;
        if(!dsNH.isEmpty())
        {
            id = Integer.parseInt(dsNH.get(dsNH.size()-1).getIdNH());
        }
        nh.setIdNH(String.valueOf(id+1));
        dsNH.add(nh);
        NhapHangDAO nhDAO = new NhapHangDAO();
        nhDAO.add(nh);
    }

    public void delete(String idNH)
    {
        for(NhapHangDTO nh : dsNH)
        {
            if(nh.getIdNH().equals(idNH))
            {
                dsNH.remove(nh);
                NhapHangDAO nhDAO = new NhapHangDAO();
                nhDAO.delete(idNH);
                return;
            }
        }
    }
    public int set(NhapHangDTO s)
    {
        for(int i = 0 ; i < dsNH.size() ; i++)
        {
            if(dsNH.get(i).getIdNH().equals(s.getIdNH()))
            {
                dsNH.set(i, s);
                NhapHangDAO nhDAO = new NhapHangDAO();
                nhDAO.set(s);
                return i;
            }
        }
        return -1;
    }
    public boolean checkTime(Calendar from,Calendar to,Calendar time)
    {
//        System.err.print(from.getTime()+" ");
//        System.err.print(to.getTime()+" ");
//        System.err.println(time.getTime());
        if(time.after(from) && time.before(to))
        {
            return true;
        }
        return false;
    }
    public ArrayList<NhapHangDTO> ListTime(Calendar from,Calendar to)
    {
        ArrayList<NhapHangDTO> list = new ArrayList<>();
        for(NhapHangDTO nh : dsNH)
        {
            Timestamp date = Timestamp.valueOf(nh.getNgayNhap());
            Calendar time = Calendar.getInstance();
            time.setTimeInMillis(date.getTime());
            if(checkTime(from, to, time))
            {
                list.add(nh);
            }
        }
        return list;
    }
    public ArrayList<NhapHangDTO> getList() {
        return dsNH;
    }
}
