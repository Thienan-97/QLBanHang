/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.NhanVienBUS;
import BUS.UserBUS;
import DTO.NhanVienDTO;
import DTO.UserDTO;
import GUI.model.header;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.UIManager;
import keeptoo.KGradientPanel;
import keeptoo.KButton;


/**
 *
 * @author Shadow
 */
public class Login extends JFrame implements KeyListener{
    private QLBanHang qlst;
    private UserBUS usBUS = new UserBUS();
    private NhanVienBUS nvBUS = new NhanVienBUS();
    private JTextField user;
    private JPasswordField pass;
    private int DEFAULT_HEIGHT = 600 ,DEFAULT_WIDTH = 400;
    private KButton btnLogin;
    public Login()
    {
        init();
    }
    public void init()
    {
       
        Font font = new Font("Segoe UI",Font.BOLD,14);
        setTitle("Quản Lý Bán ĐIỆN THOẠI ");
        ImageIcon logo = new ImageIcon("");
        setIconImage(logo.getImage());
        setLayout(new BorderLayout());
        int DEFALUT_WIDTH = 0;
        setSize(DEFALUT_WIDTH,DEFAULT_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        Font font1 = new Font("Segoe UI",Font.BOLD,15);
        
        KGradientPanel background = new KGradientPanel();
        background.setLayout(null);
        background.setkStartColor( Color.lightGray);
        background.setkEndColor( Color.BLUE);
        background.setkGradientFocus(1200);
        background.setBounds(0,0,DEFAULT_WIDTH,DEFAULT_HEIGHT);
        JLabel nv =new JLabel("WELCOME TO JMOBILE");
        nv.setForeground(Color.yellow);
        nv.setFont(new Font("Serif", Font.BOLD, 25));
        nv.setBounds(50,80,300,60);
        btnLogin = new KButton();
        btnLogin.setBorder(null);
        btnLogin.setOpaque(false);
        btnLogin.setText("Đăng nhập");
        btnLogin.setFont(font1);
        btnLogin.setkStartColor( Color.orange);
        btnLogin.setkEndColor(new Color(11,191,214));
        btnLogin.setkHoverForeGround(Color.WHITE);
        btnLogin.setkHoverStartColor(new Color(11,191,214));
        btnLogin.setkHoverEndColor(new Color(90,204,193));
        btnLogin.setkBorderRadius(20); 
        btnLogin.setkSelectedColor(null);
        btnLogin.setkAllowTab(false);
        btnLogin.setBounds(100, 400, 200, 40);
        
        JLabel lbUser = new JLabel(new ImageIcon("./src/image/user_30px.png"),JLabel.CENTER);
        lbUser.setBounds(80, 197, 30, 30);
        JLabel lbPass = new JLabel(new ImageIcon("./src/image/pwd_30px.png"),JLabel.CENTER);
        lbPass.setBounds(80, 277, 30, 30);
        
        user = new JTextField("Username");
        user.setFont(font);
        user.setForeground(Color.WHITE);
        user.setCaretColor(Color.WHITE);
        user.setBounds(120,200,180,30);
        user.setBorder(null);
        user.setOpaque(false);
        
        pass = new JPasswordField("Password");
        pass.setFont(font);
        pass.setForeground(Color.WHITE);
        pass.setCaretColor(Color.WHITE);
        pass.setBorder(null);
        pass.setBounds(120,280,150,30);
        pass.setOpaque(false);    
        
        JSeparator sp1 = new JSeparator();
        sp1.setBounds(80,230,220,10);
        
        JSeparator sp2 = new JSeparator();
        sp2.setBounds(80,310,220,10);
        
        
        
       
        
        background.add(lbUser);
        background.add(nv);
        background.add(user);
        background.add(sp1);
        background.add(lbPass);
        background.add(pass);
        background.add(sp2);
        background.add(btnLogin);
        add(background);
        
        btnLogin.requestFocus();
        
        setSize(DEFAULT_WIDTH ,DEFAULT_HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);
         

        
        user.addMouseListener((new MouseAdapter() {
            public void mousePressed(MouseEvent e)
            {
                user.setText("");
            }
        }));
        user.addKeyListener(this);
        
        pass.addMouseListener((new MouseAdapter() {
            public void mousePressed(MouseEvent e)
            {
                pass.setText("");
            }
        }));
        pass.addFocusListener(new FocusAdapter(){
            public void focusGained(FocusEvent e)
            {
                pass.setText("");
            }
        });
        pass.addKeyListener(this);
        
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(usBUS.getList()== null)usBUS.list();
                String username = user.getText();
                char[] passwd = pass.getPassword();
                UserDTO user = usBUS.check(username, passwd);
                if(user == null)
                {
                    JOptionPane.showMessageDialog(null, "Sai tên tài khoản hoặc mật khẩu");
                    return;
                }
                if(nvBUS.getList() == null)nvBUS.listNV();
                NhanVienDTO nv = new NhanVienDTO();
                nv = nvBUS.get(user.getUserID());
                qlst = new QLBanHang(nv.getMaNV(),nv.getHoNV().concat(" "+nv.getTenNV()),user.getRole());
                dispose();
            }
        });
    }
   
    public static void main(String[]args)
    {
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(Exception e)
        {
            
        }
        Login lg = new Login();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Object a = e.getSource();
        if(a.equals(user) || a.equals(pass))
        {
            if(e.getKeyCode() == KeyEvent.VK_ENTER)
            {
                btnLogin.doClick();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}