/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.KhachHangBUS;
import BUS.KhuyenMaiBUS;
import DTO.ChiTietHDDTO;
import DTO.KhachHangDTO;
import DTO.KhuyenMaiDTO;
import DTO.LoaiGGDTO;
import GUI.LoaiGGGUI;
import DATA.LoaiGGDAO;
import GUI.model.Page404;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import static java.awt.image.ImageObserver.WIDTH;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.BorderFactory;
import static javax.swing.BorderFactory.createLineBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
/**
 *
 * @author thienan
 */
public class KhuyenMaiGUI extends JPanel{
        
    private KhuyenMaiBUS kmBUS = new KhuyenMaiBUS();
    
    private JTable tbl;
    private JTextField txtMaLoaiGG,txtMaSp,txtDotGG,txtBatDau,txtKetThuc, txtPhanTramGG;
    private JTextField sortMaLoaiGG,sortMaSP, sortDotGG,sortBatDau,sortKetThuc;
    private DefaultTableModel model;
    private int DEFALUT_WIDTH;
    private boolean EditOrAdd = true;//Cờ cho button Cofirm True:ADD || False:Edit
    
    private ArrayList<LoaiGGDTO> ct = new ArrayList<>();
   
   
    private JButton btnkm, btnDeleteHD, btnEdit, btnRemove, btnConfirm;
    private Page404 page;

    
    public KhuyenMaiGUI (int width)
    {
        DEFALUT_WIDTH = width;
        init();
    }
    public void init()
    {
        setLayout(null);
        setBackground(null);
        setBounds(new Rectangle(50, 0, this.DEFALUT_WIDTH - 220, 1000));
        
        Font font0 = new Font("Segoe UI",Font.PLAIN,13);
        Font font1 = new Font("Segoe UI",Font.BOLD,13);
/****************************** PHẦN HIỂN THỊ THÔNG TIN ******************************************/

        JPanel itemView = new JPanel(null);
        itemView.setBounds(new Rectangle(30, 40, this.DEFALUT_WIDTH - 220 , 180));
        itemView.setBackground(null);
        
        /******** Tao Cac Label & TextField ************************/
        JLabel lbMaLoaiGG = new JLabel("Mă loại giảm giá");
        txtMaLoaiGG = new JTextField("");
        lbMaLoaiGG.setBounds(new Rectangle(50,0,200,30));
        lbMaLoaiGG.setFont(font0);
        txtMaLoaiGG.setBounds(new Rectangle(150,0,220,30));
        
        JLabel lbMaSp = new JLabel("Mã sản phẩm");
        txtMaSp = new JTextField("");
        lbMaSp.setBounds(new Rectangle(400,0,100,30)); 
        lbMaSp.setFont(font0);
        txtMaSp.setBounds(new Rectangle(500,0,220,30));
        
        JLabel lbDotGG = new JLabel("Đợt giảm giá");
        txtDotGG = new JTextField("");
        lbDotGG.setBounds(new Rectangle(50,40,200,30));
        lbDotGG.setFont(font0);
        txtDotGG.setBounds(new Rectangle(150,40,220,30));
     
        JLabel lbBatDau = new JLabel("Ngày bắt đầu");
        txtBatDau = new JTextField("");
        lbBatDau.setBounds(new Rectangle(400,40,200,30));
        lbBatDau.setFont(font0);
        txtBatDau.setBounds(new Rectangle(500,40,220,30));
        
        JLabel lbKetThuc = new JLabel("Ngày kết thúc");
        txtKetThuc = new JTextField("");
        lbKetThuc.setBounds(new Rectangle(400,80,200,30));
        lbKetThuc.setFont(font0);
        txtKetThuc.setBounds(new Rectangle(500,80,220,30));
        
        btnkm = new JButton("CHI TIẾT GIẢM GIÁ");
        btnkm.setBackground(Color.GREEN);
        btnkm.setBounds(new Rectangle(500,120,220,30));
        
        // THÊM VÀO PHẦN HIỂN THỊ
        itemView.add(lbMaLoaiGG);
        itemView.add(txtMaLoaiGG);
        itemView.add(lbMaSp);
        itemView.add(txtMaSp);
        itemView.add(lbDotGG);
        itemView.add(txtDotGG);
        itemView.add(lbBatDau);
        itemView.add(txtBatDau);
        itemView.add(lbKetThuc);
        itemView.add(txtKetThuc);
        itemView.add(btnkm);
        add(itemView);
        
        
        
        /**************** TẠO CÁC BTN THÊM ,XÓA, SỬA ********************/
        JLabel btnAdd = new JLabel(new ImageIcon("./src/image/btnAdd.png"));
        btnAdd.setBounds(new Rectangle(750,0,200,50));
        btnAdd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        
        JLabel btnEdit = new JLabel(new ImageIcon("./src/image/btnEdit.png"));
        btnEdit.setBounds(new Rectangle(750,55,200,50));
        btnEdit.setCursor(new Cursor(Cursor.HAND_CURSOR));
       
        
        JLabel btnDelete = new JLabel(new ImageIcon("./src/image/btnDelete.png"));
        btnDelete.setBounds(new Rectangle(750,110,200,50));
        btnDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        itemView.add(btnAdd);
        itemView.add(btnEdit);
        itemView.add(btnDelete);
        
        
        
        JLabel btnConfirm= new JLabel(new ImageIcon("./src/image/btnConfirm.png"));
        btnConfirm.setVisible(false);
        btnConfirm.setBounds(new Rectangle(750,0,200,50));
        btnConfirm.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JLabel btnBack = new JLabel(new ImageIcon("./src/image/btnBack.png"));
        btnBack.setVisible(false);
        btnBack.setBounds(new Rectangle(750,55,200,50));
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        itemView.add(btnConfirm);
        itemView.add(btnBack);
        
        
        // MouseClick btnADD
        btnAdd.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e)
            {
                EditOrAdd = true;
                
                cleanView();
                
                btnAdd.setVisible(false);
                btnEdit.setVisible(false);
                btnDelete.setVisible(false);
                
                btnConfirm.setVisible(true);
                btnBack.setVisible(true);
//                btnFile.setVisible(true);
                
                tbl.clearSelection();
                tbl.setEnabled(false);
            }
        });
        
        // MouseClick btnDelete
        btnDelete.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e)
            {   
                int i = JOptionPane.showConfirmDialog(null, "Xác nhận xóa","Alert",JOptionPane.YES_NO_OPTION);
                if(i == 0)
                {
                    kmBUS.delete(txtMaLoaiGG.getText());
                    cleanView();
                    tbl.clearSelection();
                    outModel(model, kmBUS.getList());
                }
            }
        });
        
        // MouseClick btnEdit
        btnEdit.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e)
            {
                
                if(txtMaLoaiGG.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên cần sửa !!!");
                    return;
                }
                
                EditOrAdd = false;
                
                
                txtMaLoaiGG.setEditable(false);
                
                btnAdd.setVisible(false);
                btnEdit.setVisible(false);
                btnDelete.setVisible(false);
                
                btnConfirm.setVisible(true);
                btnBack.setVisible(true);
//                btnFile.setVisible(true);
                
//                tbl.clearSelection();
                tbl.setEnabled(false);
            }
        });
        
        // Button chi tiết khuyến mãi btnkm
        btnkm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                LoaiGGGUI chitiet = new LoaiGGGUI(txtMaLoaiGG.getText());
            }
        });
        
        //MouseClick btnBack
        btnBack.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e)
            {
                cleanView();
                
                btnAdd.setVisible(true);
                btnEdit.setVisible(true);
                btnDelete.setVisible(true);
                
                btnConfirm.setVisible(false);
                btnBack.setVisible(false);
//                btnFile.setVisible(false);
                
                tbl.setEnabled(true);
            }
        });
               
        //MouseClick btnConfirm
        btnConfirm.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e)
            {
                int i;
                if(EditOrAdd) //Thêm Sản Phẩm
                {
                    i = JOptionPane.showConfirmDialog(null, "Xác nhận thêm chương trình khuyễn mãi","",JOptionPane.YES_NO_OPTION);
                    if(i == 0)
                    {
                        //Lấy dữ liệu từ TextField
                        String MALOAIGG = txtMaLoaiGG.getText();
                        String MASP = txtMaSp.getText();
                        String DOTGG = txtDotGG.getText();
                        String BATDAU = txtBatDau.getText();
                        String KETTHUC = txtKetThuc.getText();
                       
                        
                        if(kmBUS.check(MALOAIGG))
                        {
                            JOptionPane.showMessageDialog(null, "Mã loại giảm giá đă tồn tại !!!");
                            return;
                        }
                        //Upload sản phẩm lên DAO và BUS
                        KhuyenMaiDTO ncc = new KhuyenMaiDTO(MALOAIGG, MASP, DOTGG, BATDAU, KETTHUC);
                        kmBUS.add(ncc);

                        outModel(model, kmBUS.getList());// Load lại table
                        
                        cleanView();
                    }
                }
                else    // Edit Sản phẩm
                {
                    i = JOptionPane.showConfirmDialog(null, "Xác nhận sửa chương trình khuyến mãi","",JOptionPane.YES_NO_OPTION);
                    if(i == 0)
                    {
                        //Lấy dữ liệu từ TextField
                        String MALOAIGG = txtMaLoaiGG.getText();
                        String MASP = txtMaSp.getText();
                        String DOTGG = txtDotGG.getText();
                        String BATDAU = txtBatDau.getText();
                        String KETTHUC = txtKetThuc.getText();

                        //Upload sản phẩm lên DAO và BUS
                        KhuyenMaiDTO km = new KhuyenMaiDTO(MALOAIGG, MASP, DOTGG, BATDAU, KETTHUC);
                        kmBUS.set(km);
                        
                        outModel(model, kmBUS.getList());// Load lại table
                        
                        
                        JOptionPane.showMessageDialog(null, "Sửa thành công","Thành công",JOptionPane.INFORMATION_MESSAGE);
                        
                    }
                }
            }
            
        });
/***************************************************************/
/************************************************************************************************************/       

/************** TẠO MODEL VÀ HEADER *********************/
        Vector header = new Vector();
        header.add("Mă Loại GG");
        header.add("Mã SP");
        header.add("Đợt GG");
        header.add("Bắt đầu");
        header.add("Kết thúc");
        model = new DefaultTableModel(header,5);
        tbl = new JTable(model);
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(model);
        tbl.setRowSorter(rowSorter);
        list(); //Đọc từ database lên table 
        
/*********************************************************/
        
/**************** TẠO TABLE ************************************************************/

        // Chỉnh width các cột 
        tbl.getColumnModel().getColumn(0).setPreferredWidth(40);
        tbl.getColumnModel().getColumn(1).setPreferredWidth(40);
        tbl.getColumnModel().getColumn(2).setPreferredWidth(50);
        tbl.getColumnModel().getColumn(3).setPreferredWidth(100);
        tbl.getColumnModel().getColumn(4).setPreferredWidth(60);
        


        // Custom table
        tbl.setFocusable(false);
        tbl.setIntercellSpacing(new Dimension(0,0));     
        tbl.getTableHeader().setFont(font1);
        tbl.setRowHeight(30);
        tbl.setShowVerticalLines(false);              
        tbl.getTableHeader().setOpaque(false);
        tbl.setFillsViewportHeight(true);
        tbl.getTableHeader().setBackground(new Color(232,57,99));
        tbl.getTableHeader().setForeground(Color.WHITE);
        tbl.setSelectionBackground(new Color(52,152,219));          
        
        // Add table vào ScrollPane
        JScrollPane scroll = new JScrollPane(tbl);
        scroll.setBounds(new Rectangle(30, 220, this.DEFALUT_WIDTH - 400 , 450));
        scroll.setBackground(null);
        
        add(scroll);
/*****************************************************************************************/

        
        tbl.addMouseListener(new MouseAdapter() {
             public void mouseClicked(MouseEvent e)
             {
                int i = tbl.getSelectedRow();
                txtMaLoaiGG.setText(tbl.getModel().getValueAt(i, 0).toString());
                txtMaSp.setText(tbl.getModel().getValueAt(i, 1).toString());
                txtDotGG.setText(tbl.getModel().getValueAt(i, 2).toString()); 
                txtBatDau.setText(tbl.getModel().getValueAt(i, 3).toString());
                txtKetThuc.setText( tbl.getModel().getValueAt(i, 4).toString());       
             }
        });
/*********************** SORT TABLE *****************************/
       JPanel searchBox = new JPanel(null);
        searchBox.setBackground(null);
        searchBox.setBounds(new Rectangle(50,120,400,30)); 
        searchBox.setBorder(createLineBorder(Color.BLACK)); //Chỉnh viền 
        //PHẦN CHỌN SEARCH
        JComboBox cmbChoice = new JComboBox();
        cmbChoice.setEditable(true);
        cmbChoice.setFont(new Font("Segoe UI",Font.PLAIN,14));
        cmbChoice.addItem("Mã Loại GG");
        cmbChoice.addItem("Mã SP");
        cmbChoice.addItem("Đợt GG");
        cmbChoice.addItem("Bắt đầu");
        cmbChoice.addItem("Kết thúc");
        cmbChoice.setBounds(new Rectangle(0,0,120,30));
        
        //Phần TextField
        JTextField txtSearch = new JTextField();
        txtSearch.setBounds(new Rectangle(125,0,300,30));
        txtSearch.setBorder(null);
        txtSearch.setOpaque(false);
        txtSearch.setFont(new Font("Segoe UI",Font.PLAIN,15));
        
        // Custem Icon search
        JLabel searchIcon = new JLabel(new ImageIcon("./src/image/search_25px.png"));
        searchIcon.setBounds(new Rectangle(360,-10,50,50));
        searchIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Add tất cả vào search box
        searchBox.add(cmbChoice);
        searchBox.add(txtSearch);
        searchBox.add(searchIcon);
        
        
        

        //bắt sự kiện Focus vào search box
        txtSearch.addFocusListener(new FocusAdapter(){
            @Override
            public void focusGained(FocusEvent e) 
            {
                searchIcon.setIcon(new ImageIcon("./src/image/search_25px_focus.png")); //Đổi màu icon
                searchBox.setBorder(createLineBorder(new Color(52,152,219))); // Đổi màu viền 
            }
            public void focusLost(FocusEvent e) //Trờ về như cũ
            {
                searchIcon.setIcon(new ImageIcon("./src/image/search_25px.png"));
                searchBox.setBorder(createLineBorder(Color.BLACK));
            }
        });
        txtSearch.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = txtSearch.getText();
                int choice = cmbChoice.getSelectedIndex();
                
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)"+ text +"", choice));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = txtSearch.getText();
                int choice = cmbChoice.getSelectedIndex();
                
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)"+ text +"", choice));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
        });
        itemView.add(searchBox);
/******************************************************************/
    }
    public void cleanView() //Xóa trắng các TextField
    {
        txtMaLoaiGG.setEditable(true);

        txtMaLoaiGG.setText("");
        txtMaSp.setText("");
        txtDotGG.setText("");
        txtBatDau.setText("");
        txtKetThuc.setText("");
        
    }
    public void outModel(DefaultTableModel model , ArrayList<KhuyenMaiDTO> nv) // Xuất ra Table từ ArrayList
    {
        Vector data;
        model.setRowCount(0);
        for(KhuyenMaiDTO n:nv)
        {
            data = new Vector();
            data.add(n.getMALOAIGG());
            data.add(n.getMASP());
            data.add(n.getDOTGG());
            data.add(n.getBATDAU());
            data.add(n.getKETTHUC());
            model.addRow(data);
        }
        tbl.setModel(model);
    }

    public void list() // Chép ArrayList lên table
    {
        if(kmBUS.getList()== null)kmBUS.list();
        ArrayList<KhuyenMaiDTO> nv = kmBUS.getList();
//        model.setRowCount(0);
        outModel(model,nv);
    }
    
    
}
