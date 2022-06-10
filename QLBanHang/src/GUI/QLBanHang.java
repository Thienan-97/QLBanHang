/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import GUI.model.Page404;
import GUI.model.header;
import GUI.model.navItem;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

/**
 *
 * @author Shadow
 */
public class QLBanHang extends JFrame implements MouseListener{
    private String userID;
    private String userName;
    private String role;
    private boolean flag = true;
    private JPanel header,nav,main;
    private int DEFAULT_HEIGHT = 730,DEFALUT_WIDTH = 1300 ;
    private ArrayList<String> navItem = new ArrayList<>();  //Chứa thông tin có button cho menu gồm
    private ArrayList<navItem> navObj = new ArrayList<>();  //Chứa cái button trên thanh menu
    public QLBanHang(String userID, String userName, String role)
    {
        this.userID = userID;
        this.userName = userName;
        this.role = role;
        Toolkit screen = Toolkit.getDefaultToolkit();
        init();
    }
    public QLBanHang()
    {
        Toolkit screen = Toolkit.getDefaultToolkit();
        init();
    }
    public void init()
    {
        Font font = new Font("Segoe UI",Font.BOLD,14);
        setTitle("Quản Lý Bán Hàng ");
        ImageIcon logo = new ImageIcon("");
        setIconImage(logo.getImage());
        setLayout(new BorderLayout());
        setSize(DEFALUT_WIDTH,DEFAULT_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        //setShape(new RoundRectangle2D.Double(0, 0, DEFALUT_WIDTH, DEFAULT_HEIGHT, 30, 30)); //Bo khung Frame
        
/************ PHẦN HEADER *************************************/      
        header = new JPanel(null);
        header.setBackground(new Color(27, 27, 30));
        header.setPreferredSize(new Dimension(DEFALUT_WIDTH,40));
        
        header hmain = new header(DEFALUT_WIDTH,40);
        
        if(userName != null)
        {
            if(role.equals("Admin")) userName = "Admin";
            JLabel user = new JLabel("Chào, "+userName);
            user.setFont(font);
            user.setForeground(Color.WHITE);
            user.setBounds(new Rectangle(DEFALUT_WIDTH-300,-7,150,50));
            hmain.add(user);
        }
        header.add(hmain);
        
/****************************************************************/    


/************ PHẦN NAVIGATION ( MENU ) **************************/  
        nav = new JPanel(null);
        nav.setBackground( Color.DARK_GRAY);
        nav.setPreferredSize(new Dimension(220,DEFAULT_HEIGHT));
        
        JScrollPane scroll = new JScrollPane(nav);
        scroll.getVerticalScrollBar().setPreferredSize(new Dimension(1,100));
        scroll.setHorizontalScrollBarPolicy(scroll.HORIZONTAL_SCROLLBAR_NEVER);
     
        
        //Thêm item vào thanh menu (Tên item : icon : icon hover)
        navItem = new ArrayList<>();  //Chứa thông tin có button cho menu gồm ( Tên btn : icon : icon hover )
        navItem.add("Tạo hóa đơn:Shop_20px.png:Shop_20px_active.png");
        navItem.add("Quản lý Sản Phẩm:QLSP_20px.png:QLSP_20px_active.png");
        navItem.add("Quản lý nhân viên:NhanVien_20px.png:NhanVien_20px_active.png");
        navItem.add("Quản lý Khách Hàng:KhachHang_20px.png:KhachHang_20px_active.png");
        navItem.add("Nhập & Xuất:ThongKe_20px.png:ThongKe_20px_active.png");
       
        navItem.add("Nhà cung cấp:CongCu_20px.png:CongCu_20px_active.png");
        
        if( role == null || role.equals("Admin") )
        {
            navItem.add("Tài Khoản:CaiDat_20px.png:CaiDat_20px_active.png");
            navItem.add("Thống kê:ThongKe_20px.png:ThongKe_20px_active.png");   
                   
        }
        
        navItem.add("KhuyenMai:CongCu_20px.png:CongCu_20px_active.png");

        outNav();
        
/************ PHẦN MAIN ( HIỂN THỊ ) **************************/        
        main = new JPanel(null);
        main.setBackground(Color.WHITE);
        navObj.get(0).doActive();
        changeMainInfo(0);
/**************************************************************/   

        add(header,BorderLayout.NORTH);
        add(scroll,BorderLayout.WEST);
        add(main,BorderLayout.CENTER);
      
        setVisible(true);
    }
    public static void main(String[] args) {
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(Exception e)
        {
            
        }
        QLBanHang ql = new QLBanHang();
        
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        for(int i  = 0 ; i<navObj.size();i++)
        {
            navItem item = navObj.get(i); // lấy vị trí item trong menu
            if(e.getSource()== item)
            {
                item.doActive(); // Active NavItem đc chọn 
                changeMainInfo(i); // Hiển thị ra phần main
            }
            else
            {
                item.noActive();
            }
        }
    }

    public void changeMainInfo(int i) //Đổi Phần hiển thị khi bấm btn trên menu
    {
        if(flag && i>4 && i<10) // Thay đổi nếu Thông kế đang dropdown
        {
            i = i + 2;
        }
        switch(i)
        {
            case 0: //  BÁN HÀNG 
                main.removeAll();
                main.add(new BanHangGUI(DEFALUT_WIDTH,userID));
                main.repaint();
                main.revalidate();
            break;
            case 1: // QUẢN LÝ SẢN PHẨM
                main.removeAll();
                main.add(new SanPhamGUI(DEFALUT_WIDTH));
                main.repaint();
                main.revalidate();
            break;

            case 2: // QUẢN LÝ NHÂN VIÊN
                main.removeAll();
                main.add(new NhanVienGUI(DEFALUT_WIDTH));
                main.repaint();
                main.revalidate();
            break;
            case 3: // QUẢN LÝ KHÁCH HÀNG
                main.removeAll();
                main.add(new KhachHangGUI(DEFALUT_WIDTH));
                main.repaint();
                main.revalidate();
            break;
            case 4: //NHẬP VẦ XUẤT
                if(flag)
                {
                    // Thêm 2 btn vào dưới thống kê
                    navItem.add(5, "Bán Hàng:KhachHang_20px.png:KhachHang_20px_active.png");
                    navItem.add(6, "Nhập Hàng:KhachHang_20px.png:KhachHang_20px_active.png");
                    
                    flag = false; // Thông báo là đang Dropdown thống kê
                }
                else
                {
                    // Xóa 2 btn của thống kê
                    navItem.remove(5);
                    navItem.remove(5);
                    
                    flag = true;  // Thông báo là Dropdown thống kê đă ẩn
                }
                outNav(); //Load lại phần Navigation
            break;
            case 5: // BÁN HÀNG
                main.removeAll();
                main.add(new HoaDonGUI(DEFALUT_WIDTH));
                main.repaint();
                main.revalidate();
            break;
            case 6: // NHẬP HÀNG
                main.removeAll();
//                main.add(new Page404(DEFALUT_WIDTH, "THỐNG KÊ - NHẬP HÀNG"));
                main.add(new NhapHangGUI(DEFALUT_WIDTH));
                main.repaint();
                main.revalidate();
            break;
            case 7: //NHÀ CUNG CẤP
                main.removeAll();
                main.add(new NhaCungCapGUI(DEFALUT_WIDTH));
                main.repaint();
                main.revalidate();
            break;
            case 8: //USER
                main.removeAll();
                main.add(new UserGUI(DEFALUT_WIDTH));
                main.repaint();
                main.revalidate();
            break;
            case 9: // THỐNG KÊ
                main.removeAll();
                main.add(new ThongKeGUI(DEFALUT_WIDTH));
                main.repaint();
                main.revalidate();
            break;
            case 10: // THỐNG KÊ
                main.removeAll();
                main.add(new KhuyenMaiGUI(DEFALUT_WIDTH));
                main.repaint();
                main.revalidate();
            break;
            default:
            break;
        }
    }
      
    public void outNav()
    {
        //Gắn cái NavItem vào NavOBJ
        navObj.clear();
        for(int i = 0 ; i < navItem.size() ; i++)
        {
            String s = navItem.get(i).split(":")[0];
            String icon = navItem.get(i).split(":")[1];
            String iconActive = navItem.get(i).split(":")[2];
            navObj.add(new navItem(s, new Rectangle(0,200+50*i,220,50),icon,iconActive));
            navObj.get(i).addMouseListener(this);
        }
        if(!flag && navObj.size() > 10) //Đổi màu phần DropDown của thống kê
        {
            navObj.get(5).setColorNormal(new Color(86, 94, 127));
            navObj.get(6).setColorNormal(new Color(86, 94, 127));
        }
        
        //Xuất ra Naigation
        nav.removeAll();
        JLabel profile = new JLabel(new ImageIcon("./src/image/profile_150px.png"));
        profile.setBounds(0,0,220,200);
        nav.add(profile);
        for(navItem n : navObj)
        {
            nav.add(n); 
        }
        repaint();
        revalidate();
    }
    
    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {

    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {

    }
    
}
