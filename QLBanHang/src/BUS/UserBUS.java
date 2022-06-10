/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DATA.UserDAO;
import DTO.UserDTO;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Shadow
 */
public class UserBUS {
    private ArrayList<UserDTO> dsUS;
    public UserBUS()
    {
        
    }
    public void list()
    {
        UserDAO usDAO = new UserDAO();
        dsUS= new ArrayList<>();
        dsUS= usDAO.list();
    }
    public void add(UserDTO hd)
    {
        dsUS.add(hd);
        UserDAO usDAO = new UserDAO();
        usDAO.add(hd);
    }

    public void add(UserDTO hd,int i)
    {
//        dsUS.add(hd);
        UserDAO usDAO = new UserDAO();
        usDAO.add(hd);
    }
    
    public void delete(String userID)
    {
        for(UserDTO hd : dsUS)
        {
            if(hd.getUserID().equals(userID))
            {
                dsUS.remove(hd);
                UserDAO usDAO = new UserDAO();
                usDAO.delete(userID);
                return;
            }
        }
    }
    public void set(UserDTO s)
    {
        for(int i = 0 ; i < dsUS.size() ; i++)
        {
            if(dsUS.get(i).getUserID().equals(s.getUserID()))
            {
                System.out.println("ABC");
                dsUS.set(i, s);
                UserDAO usDAO = new UserDAO();
                usDAO.set(s);
                return;
            }
        }
    }
    public UserDTO check(String userName,char[] pass)
    {
        
        for(UserDTO us : dsUS)
        {   
            char[] correctPass = us.getPass().toCharArray();
            if( us.getUserName().equals(userName) && Arrays.equals(pass, correctPass) && us.getEnable().equals("1"))
            {
                return us;
            }
        }
        return null;
    }
    public ArrayList<UserDTO> getList() {
        return dsUS;
    }

    public void delete(String text, int i) {
        list();
        delete(text);
    }
}
