package GUI;

import BUS.LoaiGGBUS;
import DTO.LoaiGGDTO;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author thienan
 */
public class LoaiGGGUI extends JFrame{
    private LoaiGGBUS lggBUS = new LoaiGGBUS();
    private String MALOAIGG;
    private JTextField txtMaLoaiGG,txtPhanTramGG, txtTongTien;
    private DefaultTableModel model;
    private JTable tbl;
    private int DWIDTH = 840;
    public LoaiGGGUI()
    {
        init();
    }
    public LoaiGGGUI(String MALOAIGG)
    {
        this.MALOAIGG = MALOAIGG;
        init();
    }
    public void init()
    {
        setTitle("Chi tiết giảm giá");
        setSize(DWIDTH,450);
        getContentPane().setBackground(new Color(55, 63, 81));
        setLayout(null);
        setLocation(250, 150);
                
        Font ftitle = new Font("Segoe UI",Font.BOLD,25);
        Font font0 = new Font("Segoe UI",Font.PLAIN,13);
        Font font1 = new Font("Segoe UI",Font.BOLD,13);
        
        //HEADER
        JLabel title = new JLabel("CHI TIẾT GIẢM GIÁ : "+MALOAIGG,JLabel.CENTER);
        title.setFont(ftitle);
        title.setForeground(Color.WHITE);
        title.setBounds(0, 0, DWIDTH, 60);
        add(title);
/***************** PHẦN HIỂN THỊ THÔNG TIN ***************************/
        JPanel itemView = new JPanel(null);
        itemView.setBounds(new Rectangle(0, 60,this.getSize().width, this.getSize().height - 150));
        itemView.setBackground(Color.WHITE);
        
        JLabel lbMaLoaiGG = new JLabel("Mã loại giảm giá ");
        lbMaLoaiGG.setFont(font0);
        lbMaLoaiGG.setBounds(20,20,100,30);
        txtMaLoaiGG = new JTextField();
        txtMaLoaiGG.setBounds(new Rectangle(120,20,210,30));
        itemView.add(lbMaLoaiGG);
        itemView.add(txtMaLoaiGG);
        
        JLabel lbPhanTramGG = new JLabel("Phần trăm giảm giá ");
        lbPhanTramGG.setFont(font0);
        lbPhanTramGG.setBounds(20,60,100,30);
        txtPhanTramGG = new JTextField();
        txtPhanTramGG.setBounds(new Rectangle(120,60,210,30));
        itemView.add(lbPhanTramGG);
        itemView.add(txtPhanTramGG);
                
        JLabel lbTongTien = new JLabel("Tổng tiền ");
        lbTongTien.setFont(font0);
        lbTongTien.setBounds(20,100,100,30);
        txtTongTien = new JTextField();
        txtTongTien.setBounds(new Rectangle(120,100,210,30));
        itemView.add(lbTongTien);
        itemView.add(txtTongTien);
        
/**************** TẠO CÁC BTN XÓA, SỬA, VIEW, IN BILL ********************/

        JLabel btnEdit = new JLabel(new ImageIcon("./src/image/btnEdit_150px.png"));
        btnEdit.setBounds(new Rectangle(20,180,150,50));
        btnEdit.setCursor(new Cursor(Cursor.HAND_CURSOR));
              
        JLabel btnDelete = new JLabel(new ImageIcon("./src/image/btnDelete_150px.png"));
        btnDelete.setBounds(new Rectangle(180,180,150,50));
        btnDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));    
        
        itemView.add(btnEdit);
        itemView.add(btnDelete);
/*************************************************************************/

/**************** TẠO TABLE ************************************************************/

    /************** TẠO MODEL VÀ HEADER *********************************/
        Vector header = new Vector();
        header.add("Mă loại giảm giá");
        header.add("Phần trăm giảm giá");
        model = new DefaultTableModel(header,3);
        tbl = new JTable(model);
        list(); //Đọc từ database lên table 
        
    /*******************************************************************/
        

    /******** CUSTOM TABLE ****************/
    
        // Chỉnh width các cột 
        tbl.getColumnModel().getColumn(0).setPreferredWidth(40);
        tbl.getColumnModel().getColumn(1).setPreferredWidth(120);


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
        scroll.setBounds(new Rectangle(350, 20, this.getSize().width - 450 , this.getSize().height - 180));
        scroll.setBackground(null);
        
        itemView.add(scroll);
        
        add(itemView);
    /**************************************/
    tbl.addMouseListener(new MouseAdapter() {
             public void mouseClicked(MouseEvent e)
             {
                int i = tbl.getSelectedRow();
                txtMaLoaiGG.setText(tbl.getModel().getValueAt(i, 0).toString());
                txtPhanTramGG.setText(tbl.getModel().getValueAt(i, 1).toString());
                
             }
        });
/*****************************************************************************************/
/*********************************************************************/
        
        setVisible(true);
    }
    public void outModel(DefaultTableModel model , ArrayList<LoaiGGDTO> ct) // Xuất ra Table từ ArrayList
    {
        Vector data;
        model.setRowCount(0);
        for(LoaiGGDTO c:ct)
        {
            data = new Vector();
            data.add(c.getMALOAIGG());
            data.add(c.getPHANTRAMGG());
            model.addRow(data);
        }
        tbl.setModel(model);
    }
    public void list() // Chép ArrayList lên table
    {
        if(lggBUS.getList()== null)lggBUS.list();
        ArrayList<LoaiGGDTO> ct = lggBUS.getListLoaiGG(MALOAIGG);
        model.setRowCount(0);
        outModel(model,ct);
    }
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(loginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(loginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(loginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(loginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               LoaiGGGUI dp = new LoaiGGGUI();
                dp.setVisible(true);
                dp.setLocationRelativeTo(null);
            }
        });
    }
}

