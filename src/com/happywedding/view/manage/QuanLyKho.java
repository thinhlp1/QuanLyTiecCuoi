/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.happywedding.view.manage;



import com.happywedding.dao.CoSoVatChatDAO;
import com.happywedding.dao.DanhMucConDAO;
import com.happywedding.dao.DanhMucDAO;
import com.ui.swing.datechooser.DateChooser;
import com.happywedding.helper.DateHelper;
import com.happywedding.helper.DialogHelper;
import com.happywedding.model.CoSoVatChat;
import com.happywedding.model.DanhMuc;
import com.happywedding.model.DanhMucCon;
import java.awt.CardLayout;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.table.DefaultTableModel;

import com.ui.swing.datechooser.DateChooser;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author ACER
 */
public class QuanLyKho extends javax.swing.JPanel {

    private int currentIndex = 0;
    private CoSoVatChatDAO dao = new CoSoVatChatDAO();
    private DefaultTableModel tblModel;
    private List<CoSoVatChat> listAllCSVC;
    private List<DanhMuc> listAllDanhMuc;
    private List<DanhMucCon> listAllDanhMucCon;

    private DanhMucDAO daoDM = new DanhMucDAO();
    private DanhMucConDAO daoDMC = new DanhMucConDAO();
    private DateChooser dtChooser1 = new DateChooser();

    
    /**
     * Creates new form QuanLyKho
     */
    public QuanLyKho() {
        initComponents();
        init();
    }
        
    public void init(){
        tblCoSoVatChat.fixTable(jScrollPane1);
        tblCoSoVatChat.setAutoscrolls(true);
        
            
        tblModel = (DefaultTableModel) tblCoSoVatChat.getModel();
        loadCSVC();
        loadDanhMuc();
        loadDanhMucCon();
        fillTable(listAllCSVC);

        initSort();
    }
    
    
    public void showCalendar1() {
        dtChooser1.showPopup();
    }

    void loadCSVC() {
        listAllCSVC = dao.select();
    }
    void loadDM() {
        listAllDanhMuc = daoDM.select();
    }
    void loadDMC() {
        listAllDanhMucCon = daoDMC.select();
    }
    
    

    void filtCSVC(String keyword) {
        List<CoSoVatChat> list = new ArrayList<>();
        keyword = keyword.toUpperCase();
        for (int i = 0; i < listAllCSVC.size(); i++) {

            if (listAllCSVC.get(i).toString().toUpperCase().contains(keyword)) {

                list.add(listAllCSVC.get(i));
            }
        }
        fillTable(list);

    }

    CoSoVatChat getModel() {

        List<String> listErorr = validation();

        if (listErorr.size() != 0) {
            DialogHelper.alertError(this, listErorr.toString());
            return null;
        }
       
        
        CoSoVatChat model = new CoSoVatChat();
         model.setMaDanhMuc(((DanhMuc) cbbDanhMuc.getSelectedItem() ).getMaDM());
        model.setMaDanhMucCon(((DanhMucCon) cbbDanhMucCon.getSelectedItem() ).getMaDMC());
        model.setMaCSVC(txtMaCSVC.getText());
        model.setTenCSVC(txtTenCSVC.getText());
        model.setSoLuong(Integer.valueOf(txtSoLuong.getText()));
        model.setGiaThue(Integer.valueOf(txtGiaThue.getText()));
        model.setGhiChu(txtGhiChu.getText());
        
        return model;
    }

    void setModel(CoSoVatChat model) {
        for (int i = 0; i < listAllDanhMuc.size(); i++) {
            if (model.getMaDanhMuc().equals(listAllDanhMuc.get(i).getMaDM())) {
                cbbDanhMuc.setSelectedIndex(i);
            }
        }
        for (int i = 0; i < listAllDanhMucCon.size(); i++) {
            if (model.getMaDanhMucCon().equals(listAllDanhMucCon.get(i).getMaDMC())) {
                cbbDanhMucCon.setSelectedIndex(i);
            }
        }
        txtMaCSVC.setText(model.getMaCSVC());
        txtTenCSVC.setText(model.getTenCSVC());
        txtSoLuong.setText(String.valueOf(model.getSoLuong()));
        txtGiaThue.setText(String.valueOf(model.getGiaThue()));
        txtGhiChu.setText(model.getGhiChu());
    }

    public List<String> validation() {

        List<String> listError = new ArrayList<>();

        if (txtMaCSVC.getText().equals("")) {
            listError.add("Mã CSVC không được bỏ trống");
            return listError;
        } else if (txtTenCSVC.getText().equals("")) {
            listError.add("Tên CSVC không được để trống");
            return listError;
        } else if (txtSoLuong.getText().equals("")) {
            listError.add("Số lượng không được để trống");
            return listError;
        }

        //check xong hết
        return listError;
    }

    // các hàm lấy dữ liệu từ cơ sở dữ liệu
    public void loadDanhMuc() {
        listAllDanhMuc = daoDM.select();
        DefaultComboBoxModel cbbModel = (DefaultComboBoxModel) cbbDanhMuc.getModel();
        cbbModel.removeAllElements();
        for (DanhMuc s : listAllDanhMuc) {
            cbbModel.addElement(s);
        }
        cbbDanhMuc.setSelectedIndex(-1);
    }
    
    public void loadDanhMucCon() {
        listAllDanhMucCon = daoDMC.select();
        DefaultComboBoxModel cbbModel = (DefaultComboBoxModel) cbbDanhMucCon.getModel();
        cbbModel.removeAllElements();
        for (DanhMucCon s : listAllDanhMucCon) {
            cbbModel.addElement(s);
        }
        cbbDanhMucCon.setSelectedIndex(-1);
    }
    
    

    //Hiển thị lên textfied
    void edit() {
        try {
            String manv = (String) tblCoSoVatChat.getValueAt(this.currentIndex, 0);
            CoSoVatChat model = dao.findById(manv);
            if (model != null) {
                this.setModel(model);
                //isView(true);
            }
        } catch (Exception e) {
            DialogHelper.alertError(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    //Clear
    void clear() {
        
        cbbDanhMuc.setVisible(false);
        cbbDanhMucCon.setVisible(false);
        txtMaCSVC.setText("");
        txtTenCSVC.setText("");
        txtSoLuong.setText("");
        txtGiaThue.setText("");
        txtGhiChu.setText("");
    
        
        //isView(true);

    }

    //Add
    void insert() {
        CoSoVatChat model = getModel();
        if (model == null) {
            return;
        }
        try {
            dao.insert(model);
            loadCSVC();
            this.fillTable(listAllCSVC);
            this.clear();
            DialogHelper.alert(this, "Thêm mới thành công!");
        } catch (Exception e) {
             e.printStackTrace();
            DialogHelper.alertError(this, "Thêm mới thất bại!");
        }
        
        
    }

    //Update
    void update() {
        CoSoVatChat model = getModel();
        if (model == null) {
            return;
        }
        try {
            dao.update(model);
            loadCSVC();
            this.fillTable(listAllCSVC);
            DialogHelper.alert(this, "Cập nhật thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            DialogHelper.alertError(this, "Cập nhật thất bại!");
        }
    }

    //Delete
    void delete() {
        if (DialogHelper.confirm(this, "Bạn thực sự muốn xóa người học này?")) {
            String csvc = txtMaCSVC.getText();
            try {
                dao.delete(csvc);
                loadCSVC();
                this.fillTable(listAllCSVC);
                this.clear();
                DialogHelper.alert(this, "Xóa thanh công!");
            } catch (HeadlessException e) {
                e.printStackTrace();
                DialogHelper.alertError(this, "Xóa thất bại!");
            }
        }
    }

    //Di chuyển
    public void setTblSelected(int row) {
        tblCoSoVatChat.setRowSelectionInterval(row, row);
        tblCoSoVatChat.scrollRectToVisible(new Rectangle(tblCoSoVatChat.getCellRect(row, 0, true)));

    }

    //Tìm kiếm
    void fillTable(List<CoSoVatChat> list) {

        tblModel.setRowCount(0);
        try {
            String keyword = txtTimKiem.getText();

            for (CoSoVatChat csvc : list) {
                Object[] row = {
                    csvc.getMaCSVC(),
                    csvc.getTenCSVC(),
                    csvc.getSoLuong(),                   
                    csvc.getGiaThue(),
                    csvc.getGhiChu()
   
                };
                tblModel.addRow(row);
            }
        } catch (Exception e) {
            DialogHelper.alertError(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    //Tăng giảm
    public void initSort() {

        cbbSortBy.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (cbbSortBy.getSelectedIndex() == 0) {
                    if (cbbSort.getSelectedIndex() == 0) {
                        // ten tang dan
                        fillTable(sortByName(false));
                    } else if (cbbSort.getSelectedIndex() == 1) {
                        // ten giam dan
                        fillTable(sortByName(true));
                    }
                } else {
                    cbbSortBy.setSelectedIndex(0);
                }
            }
        });

        cbbSort.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (cbbSort.getSelectedIndex() == 0) {
                    if (cbbSort.getSelectedIndex() == 0) {
                        // ten tang dan
                        fillTable(sortByName(false));
                    } else if (cbbSort.getSelectedIndex() == 1) {
                        // ten giam dan
                        fillTable(sortByName(true));
                    }
                } else {
                    cbbSortBy.setSelectedIndex(0);
                }
            }
        });
    }

    public List<CoSoVatChat> sortByName(boolean isRevese) {
        List<CoSoVatChat> listSorted = listAllCSVC;
        Collections.sort(listSorted, new Comparator<CoSoVatChat>() {
            public int compare(CoSoVatChat employee1, CoSoVatChat employee2) {
                int result = 0;
                String[] partNameEmployee1 = employee1.getTenCSVC().split("\\s");
                String[] partNameEmployee2 = employee2.getTenCSVC().split("\\s");

                int nameLenght1 = partNameEmployee1.length;
                int nameLenght2 = partNameEmployee2.length;

                if (nameLenght1 == 1 && nameLenght2 == 1) {
                    return employee1.getTenCSVC().compareToIgnoreCase(employee2.getTenCSVC());
                }

                int length = ((nameLenght1 > nameLenght2) ? nameLenght2 : nameLenght1);
                for (int i = 1; i < length; i++) {
                    result = (partNameEmployee1[nameLenght1 - i]).compareToIgnoreCase(partNameEmployee2[nameLenght2 - i]);
                    if (result != 0) {
                        return result;
                    }
                }
                if (nameLenght1 > nameLenght2) {
                    return 1;
                } else if (nameLenght1 < nameLenght2) {
                    return -1;
                }

                return 0;
            }
        });

        if (isRevese) {
            Collections.reverse(listSorted);
        }

        return listSorted;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlEmplpyeeManager = new javax.swing.JPanel();
        lblSearch = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txtMaCSVC = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        cbbDanhMucCon = new com.ui.swing.Combobox();
        jLabel31 = new javax.swing.JLabel();
        btnMoi = new com.ui.swing.InkwellButton();
        btnFirst = new com.ui.swing.InkwellButton();
        btnThem = new com.ui.swing.InkwellButton();
        btnSua = new com.ui.swing.InkwellButton();
        btnXoa = new com.ui.swing.InkwellButton();
        btnLast = new com.ui.swing.InkwellButton();
        btnPre = new com.ui.swing.InkwellButton();
        btnNext = new com.ui.swing.InkwellButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCoSoVatChat = new com.ui.swing.Table();
        jLabel2 = new javax.swing.JLabel();
        cbbSapXepDanhMucCon = new com.ui.swing.Combobox();
        jLabel1 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        cbbSort = new com.ui.swing.Combobox();
        cbbSortBy = new com.ui.swing.Combobox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        cbbSapXepDanhMuc = new com.ui.swing.Combobox();
        cbbDanhMuc = new com.ui.swing.Combobox();
        lblHinhAnh = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        txtGiaThue = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        txtTenCSVC = new javax.swing.JTextField();

        pnlEmplpyeeManager.setBackground(new java.awt.Color(255, 255, 255));
        pnlEmplpyeeManager.setMinimumSize(new java.awt.Dimension(1600, 838));
        pnlEmplpyeeManager.setPreferredSize(new java.awt.Dimension(1650, 950));
        pnlEmplpyeeManager.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblSearch.setBackground(new java.awt.Color(204, 204, 255));
        lblSearch.setForeground(new java.awt.Color(102, 0, 255));
        lblSearch.setAutoscrolls(true);
        lblSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSearchMouseClicked(evt);
            }
        });
        pnlEmplpyeeManager.add(lblSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, -1, 35));

        jLabel25.setText("Danh mục");
        pnlEmplpyeeManager.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 92, -1));

        jLabel27.setText("Mã cơ sở vật chất");
        pnlEmplpyeeManager.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, 130, -1));

        jLabel28.setText("Tên cơ sở vật chất");
        pnlEmplpyeeManager.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 530, 130, -1));

        jLabel30.setText("Số lượng");
        pnlEmplpyeeManager.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 610, 130, -1));

        txtMaCSVC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaCSVCActionPerformed(evt);
            }
        });
        pnlEmplpyeeManager.add(txtMaCSVC, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 480, 340, 35));

        jLabel29.setText("Ghi chú");
        pnlEmplpyeeManager.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 750, 60, -1));

        cbbDanhMucCon.setLabeText("");
        cbbDanhMucCon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbDanhMucConActionPerformed(evt);
            }
        });
        pnlEmplpyeeManager.add(cbbDanhMucCon, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, 340, 35));

        jLabel31.setText("Danh mục con");
        pnlEmplpyeeManager.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 130, -1));

        btnMoi.setBackground(new java.awt.Color(81, 194, 225));
        btnMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnMoi.setText("Mới");
        btnMoi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });
        pnlEmplpyeeManager.add(btnMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 870, 80, -1));

        btnFirst.setBackground(new java.awt.Color(51, 0, 255));
        btnFirst.setForeground(new java.awt.Color(255, 255, 255));
        btnFirst.setText(">|");
        btnFirst.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnFirst.setPreferredSize(new java.awt.Dimension(45, 27));
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });
        pnlEmplpyeeManager.add(btnFirst, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 930, 70, 30));

        btnThem.setBackground(new java.awt.Color(0, 153, 0));
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setText("Thêm");
        btnThem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        pnlEmplpyeeManager.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 870, 80, -1));

        btnSua.setBackground(new java.awt.Color(0, 153, 0));
        btnSua.setForeground(new java.awt.Color(255, 255, 255));
        btnSua.setText("Lưu");
        btnSua.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        pnlEmplpyeeManager.add(btnSua, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 870, 80, -1));

        btnXoa.setBackground(new java.awt.Color(153, 24, 24));
        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setText("Xóa");
        btnXoa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        pnlEmplpyeeManager.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 870, 80, -1));

        btnLast.setBackground(new java.awt.Color(51, 0, 255));
        btnLast.setForeground(new java.awt.Color(255, 255, 255));
        btnLast.setText("|<");
        btnLast.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnLast.setPreferredSize(new java.awt.Dimension(45, 27));
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });
        pnlEmplpyeeManager.add(btnLast, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 930, 70, 30));

        btnPre.setBackground(new java.awt.Color(51, 0, 255));
        btnPre.setForeground(new java.awt.Color(255, 255, 255));
        btnPre.setText("<<");
        btnPre.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnPre.setPreferredSize(new java.awt.Dimension(45, 27));
        btnPre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreActionPerformed(evt);
            }
        });
        pnlEmplpyeeManager.add(btnPre, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 930, 80, 30));

        btnNext.setBackground(new java.awt.Color(51, 0, 255));
        btnNext.setForeground(new java.awt.Color(255, 255, 255));
        btnNext.setText(">>");
        btnNext.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnNext.setPreferredSize(new java.awt.Dimension(45, 27));
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        pnlEmplpyeeManager.add(btnNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 930, 80, 30));

        tblCoSoVatChat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã cơ sở vật chất", "Tên cơ sở vật chất", "Số lượng", "Giá thuê", "Ghi chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCoSoVatChat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCoSoVatChatMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCoSoVatChat);

        pnlEmplpyeeManager.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 190, 1210, 780));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/happywedding/assets/filt.png"))); // NOI18N
        jLabel2.setText("   Lọc");
        pnlEmplpyeeManager.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 40, 80, 40));

        cbbSapXepDanhMucCon.setLabeText("Danh mục con");
        cbbSapXepDanhMucCon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbSapXepDanhMucConActionPerformed(evt);
            }
        });
        pnlEmplpyeeManager.add(cbbSapXepDanhMucCon, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 30, 120, 54));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/happywedding/assets/sort.png"))); // NOI18N
        pnlEmplpyeeManager.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1340, 110, 40, 40));

        txtTimKiem.setToolTipText("");
        txtTimKiem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTimKiemMouseClicked(evt);
            }
        });
        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });
        pnlEmplpyeeManager.add(txtTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 110, 610, 35));

        cbbSort.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tăng dần", "Giảm dần" }));
        cbbSort.setLabeText("");
        cbbSort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbSortActionPerformed(evt);
            }
        });
        pnlEmplpyeeManager.add(cbbSort, new org.netbeans.lib.awtextra.AbsoluteConstraints(1400, 90, 120, 54));

        cbbSortBy.setLabeText("Sắp xếp");
        cbbSortBy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbSortByActionPerformed(evt);
            }
        });
        pnlEmplpyeeManager.add(cbbSortBy, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 90, 120, 54));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/happywedding/assets/Search.png"))); // NOI18N
        pnlEmplpyeeManager.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 110, 40, 40));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/happywedding/assets/sort.png"))); // NOI18N
        pnlEmplpyeeManager.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 110, 40, 40));

        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        jScrollPane2.setViewportView(txtGhiChu);

        pnlEmplpyeeManager.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 780, 340, 70));

        cbbSapXepDanhMuc.setLabeText("Danh mục");
        cbbSapXepDanhMuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbSapXepDanhMucActionPerformed(evt);
            }
        });
        pnlEmplpyeeManager.add(cbbSapXepDanhMuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 30, 120, 54));

        cbbDanhMuc.setLabeText("");
        cbbDanhMuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbDanhMucActionPerformed(evt);
            }
        });
        pnlEmplpyeeManager.add(cbbDanhMuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 340, 35));

        lblHinhAnh.setBackground(new java.awt.Color(255, 255, 255));
        lblHinhAnh.setForeground(new java.awt.Color(255, 255, 255));
        lblHinhAnh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblHinhAnh.setDoubleBuffered(true);
        lblHinhAnh.setOpaque(true);
        lblHinhAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinhAnhMouseClicked(evt);
            }
        });
        pnlEmplpyeeManager.add(lblHinhAnh, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 180, 190));

        jLabel32.setText("Giá thuê");
        pnlEmplpyeeManager.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 680, 130, -1));

        txtGiaThue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaThueActionPerformed(evt);
            }
        });
        pnlEmplpyeeManager.add(txtGiaThue, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 710, 340, 35));

        txtSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuongActionPerformed(evt);
            }
        });
        pnlEmplpyeeManager.add(txtSoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 640, 340, 35));

        txtTenCSVC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenCSVCActionPerformed(evt);
            }
        });
        pnlEmplpyeeManager.add(txtTenCSVC, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 560, 340, 35));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlEmplpyeeManager, javax.swing.GroupLayout.PREFERRED_SIZE, 1680, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlEmplpyeeManager, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSearchMouseClicked

    }//GEN-LAST:event_lblSearchMouseClicked

    private void txtMaCSVCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaCSVCActionPerformed

    }//GEN-LAST:event_txtMaCSVCActionPerformed

    private void cbbDanhMucConActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbDanhMucConActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbDanhMucConActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        insert();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
         // isUpdate(true);
        update();
        //  isView(true);
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
         delete();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void cbbSapXepDanhMucConActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbSapXepDanhMucConActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbSapXepDanhMucConActionPerformed

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        filtCSVC(txtTimKiem.getText());
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void cbbSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbSortActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbSortActionPerformed

    private void cbbSortByActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbSortByActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbSortByActionPerformed

    private void cbbSapXepDanhMucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbSapXepDanhMucActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbSapXepDanhMucActionPerformed

    private void cbbDanhMucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbDanhMucActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbDanhMucActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
        currentIndex = tblCoSoVatChat.getRowCount() - 1;
        setTblSelected(currentIndex);
        //isUpdate(false);
        edit();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void lblHinhAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhAnhMouseClicked

    }//GEN-LAST:event_lblHinhAnhMouseClicked

    private void txtGiaThueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaThueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaThueActionPerformed

    private void txtSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuongActionPerformed

    private void txtTenCSVCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenCSVCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenCSVCActionPerformed

    private void tblCoSoVatChatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCoSoVatChatMouseClicked
        // TODO add your handling code here:
        currentIndex = tblCoSoVatChat.rowAtPoint(evt.getPoint());

        if (this.currentIndex >= 0) {
            this.edit();
            if (evt.getClickCount() == 2) {
                // isUpdate(true);

            }
        }
    }//GEN-LAST:event_tblCoSoVatChatMouseClicked

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        currentIndex = 0;
        setTblSelected(currentIndex);
        //isUpdate(false);
        edit();
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnPreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreActionPerformed
        // TODO add your handling code here:
        currentIndex--;
        setTblSelected(currentIndex);
        //isUpdate(false);
        edit();
    }//GEN-LAST:event_btnPreActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        currentIndex++;
        setTblSelected(currentIndex);
        // isUpdate(false);
        edit();
    }//GEN-LAST:event_btnNextActionPerformed

    private void txtTimKiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimKiemMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ui.swing.InkwellButton btnFirst;
    private com.ui.swing.InkwellButton btnLast;
    private com.ui.swing.InkwellButton btnMoi;
    private com.ui.swing.InkwellButton btnNext;
    private com.ui.swing.InkwellButton btnPre;
    private com.ui.swing.InkwellButton btnSua;
    private com.ui.swing.InkwellButton btnThem;
    private com.ui.swing.InkwellButton btnXoa;
    private com.ui.swing.Combobox cbbDanhMuc;
    private com.ui.swing.Combobox cbbDanhMucCon;
    private com.ui.swing.Combobox cbbSapXepDanhMuc;
    private com.ui.swing.Combobox cbbSapXepDanhMucCon;
    private com.ui.swing.Combobox cbbSort;
    private com.ui.swing.Combobox cbbSortBy;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblHinhAnh;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JPanel pnlEmplpyeeManager;
    private com.ui.swing.Table tblCoSoVatChat;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtGiaThue;
    private javax.swing.JTextField txtMaCSVC;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenCSVC;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
