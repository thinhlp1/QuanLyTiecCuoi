/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.happywedding.view.manage;

import com.happywedding.dao.PhanLoaiSanhDAO;
import com.happywedding.dao.SanhDAO;

import com.ui.swing.datechooser.DateChooser;
import com.happywedding.helper.DateHelper;
import com.happywedding.helper.DialogHelper;
import com.happywedding.helper.ShareHelper;
import com.happywedding.model.PhanLoaiSanh;
import com.happywedding.model.Sanh;
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
import javax.swing.ImageIcon;

/**
 *
 * @author ACER
 */
public class QuanLySanh extends javax.swing.JPanel {

    /**
     * Creates new form QuanLySanh
     */
    private int currentIndex = 0;
    private SanhDAO dao = new SanhDAO();
    private DefaultTableModel tblModel;
    private List<Sanh> listAllSanh;
    private List<PhanLoaiSanh> listAllPhanLoaiSanh;

    private PhanLoaiSanhDAO daoSanhPL = new PhanLoaiSanhDAO();
    private DateChooser dtChooser1 = new DateChooser();

    public QuanLySanh() {
        initComponents();
        init();
    }

    public void init() {
        tblSanh.fixTable(jScrollPane1);
        tblSanh.setAutoscrolls(true);

        tblModel = (DefaultTableModel) tblSanh.getModel();
        loadSanh();
        fillTable(listAllSanh);

        loadPhanLoaiSanh();

        //  initSort();
    }

    void loadSanh() {
        listAllSanh = dao.select();
    }

    void filtEmployee(String keyword) {
        List<Sanh> list = new ArrayList<>();
        keyword = keyword.toUpperCase();
        for (int i = 0; i < listAllSanh.size(); i++) {

            if (listAllSanh.get(i).toString().toUpperCase().contains(keyword)) {

                list.add(listAllSanh.get(i));
            }
        }
        fillTable(list);

    }

    Sanh getModel() {

        List<String> listErorr = validation();

        if (listErorr.size() != 0) {
            DialogHelper.alertError(this, listErorr.toString());
            return null;
        }

        Sanh model = new Sanh();
        model.setMaSanh(txtMaSanh.getText());
        model.setTenSanh(txtTenSanh.getText());
        model.setMaPL(((PhanLoaiSanh) cbbLoaiSanh.getSelectedItem()).getMaPL());
        
        model.setSucChua(Integer.valueOf(txtSoLuongNguoi.getText()));
        model.setGiaThueSanh(Integer.valueOf(txtGiaThue.getText()));
        model.setGiaBan(Integer.valueOf(txtGia1Ban.getText()));

        return model;
    }

    void setModel(Sanh model) {
        txtMaSanh.setText(model.getMaSanh());
        txtTenSanh.setText(model.getTenSanh());
        for (int i = 0; i < listAllPhanLoaiSanh.size(); i++) {
            if (model.getMaPL().equals(listAllPhanLoaiSanh.get(i).getMaPL())) {
                cbbLoaiSanh.setSelectedIndex(i);
            }
        }
        txtSoLuongNguoi.setText(String.valueOf(model.getSucChua()));
        txtGiaThue.setText(String.valueOf(model.getGiaThueSanh()));
        txtGia1Ban.setText(String.valueOf(model.getGiaBan()));
    }

    public List<String> validation() {

        List<String> listError = new ArrayList<>();

        if (txtMaSanh.getText().equals("")) {
            listError.add("Mã Sảnh không được bỏ trống");
            return listError;
        } else if (txtTenSanh.getText().equals("")) {
            listError.add("Tên không được để trống");
            return listError;
        }

        //check xong hết
        return listError;
    }

    // các hàm lấy dữ liệu từ cơ sở dữ liệu
    public void loadPhanLoaiSanh() {
        listAllPhanLoaiSanh = daoSanhPL.select();
        DefaultComboBoxModel cbbModel = (DefaultComboBoxModel) cbbLoaiSanh.getModel();
        cbbModel.removeAllElements();
        for (PhanLoaiSanh s : listAllPhanLoaiSanh) {
            cbbModel.addElement(s);
        }
        cbbLoaiSanh.setSelectedIndex(-1);
    }

    //Hiển thị lên textfied
    void edit() {
        try {
            String manv = (String) tblSanh.getValueAt(this.currentIndex, 0);
            Sanh model = dao.findById(manv);
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
        txtMaSanh.setText("");
        txtTenSanh.setText("");
        txtSoLuongNguoi.setText("");
        txtGia1Ban.setText("");
        txtGia1Ban.setText("");
       // cbbLoaiSanh.setVisible(false);

    }

    //Add
    void insert() {
        Sanh model = getModel();
        if (model == null) {
            return;
        }
        try {
            dao.insert(model);
            loadSanh();
            this.fillTable(listAllSanh);
            this.clear();
            DialogHelper.alert(this, "Thêm mới thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            DialogHelper.alertError(this, "Thêm mới thất bại!");
        }
    }

    //Update
    void update() {
        Sanh model = getModel();
        if (model == null) {
            return;
        }
        try {
            dao.update(model);
            loadSanh();
            this.fillTable(listAllSanh);
            DialogHelper.alert(this, "Cập nhật thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            DialogHelper.alertError(this, "Cập nhật thất bại!");
        }
    }

    //Delete
    void delete() {
        if (DialogHelper.confirm(this, "Bạn thực sự muốn xóa người học này?")) {
            String nv = txtMaSanh.getText();
            try {
                dao.delete(nv);
                loadSanh();
                this.fillTable(listAllSanh);
                this.clear();
                DialogHelper.alert(this, "Xóa thanh công!");
            } catch (HeadlessException e) {
                DialogHelper.alertError(this, "Xóa thất bại!");
            }
        }
    }

    //Di chuyển
    public void setTblSelected(int row) {
        tblSanh.setRowSelectionInterval(row, row);
        tblSanh.scrollRectToVisible(new Rectangle(tblSanh.getCellRect(row, 0, true)));

    }

    //Tìm kiếm
    void fillTable(List<Sanh> list) {

        tblModel.setRowCount(0);
        try {
            String keyword = txtTimKiem.getText();

            for (Sanh nv : list) {
                Object[] row = {
                    nv.getMaSanh(),
                    nv.getTenSanh(),
                    nv.getMaPL(),
                    nv.getSucChua(),
                    nv.getGiaThueSanh(),
                    nv.getGiaBan()
                };
                tblModel.addRow(row);
            }
        } catch (Exception e) {
            DialogHelper.alertError(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    public void initSort() {

        cbbSortBy.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                switch (cbbSortBy.getSelectedIndex()) {
                    case 0:
                        if (cbbSort.getSelectedIndex() == 0) {
                            // ten tang dan
                            fillTable(sortByGiaThue(false));
                        } else if (cbbSort.getSelectedIndex() == 1) {
                            // ten giam dan
                            fillTable(sortByGiaThue(true));
                        }
                        break;
                    case 1:
                        if (cbbSort.getSelectedIndex() == 0) {
                            // ten tang dan
                            fillTable(sortBySoLuong(false));
                        } else if (cbbSort.getSelectedIndex() == 1) {
                            // ten giam dan
                            fillTable(sortBySoLuong(true));
                        }
                        break;
                    case 2:
                        if (cbbSort.getSelectedIndex() == 0) {
                            // ten tang dan
                            fillTable(sortByMaCSVC(false));
                        } else if (cbbSort.getSelectedIndex() == 1) {
                            // ten giam dan
                            fillTable(sortByMaCSVC(true));
                        } else {
                            cbbSortBy.setSelectedIndex(0);
                        }
                        break;

                    default:
                        break;
                }
            }
        });

        cbbSort.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                switch (cbbSortBy.getSelectedIndex()) {
                    case 0:
                        if (cbbSort.getSelectedIndex() == 0) {
                            // ten tang dan
                            fillTable(sortByGiaThue(false));
                        } else if (cbbSort.getSelectedIndex() == 1) {
                            // ten giam dan
                            fillTable(sortByGiaThue(true));
                        }
                        break;
                    case 1:
                        if (cbbSort.getSelectedIndex() == 0) {
                            // ten tang dan
                            fillTable(sortBySoLuong(false));
                        } else if (cbbSort.getSelectedIndex() == 1) {
                            // ten giam dan
                            fillTable(sortBySoLuong(true));
                        }
                        break;
                    case 2:
                        if (cbbSort.getSelectedIndex() == 0) {
                            // ten tang dan
                            fillTable(sortByMaCSVC(false));
                        } else if (cbbSort.getSelectedIndex() == 1) {
                            // ten giam dan
                            fillTable(sortByMaCSVC(true));
                        } else {
                            cbbSortBy.setSelectedIndex(0);
                        }
                        break;

                    default:
                        break;
                }
            }
        });
    }

    public List<Sanh> sortByMaCSVC(boolean isRevese) {
        List<Sanh> listSorted = listAllSanh;
        Collections.sort(listSorted, new Comparator<Sanh>() {
            public int compare(Sanh hoaDolon1, Sanh hoaDolon2) {
                return (hoaDolon1.getMaSanh().compareTo(hoaDolon2.getMaSanh()));

            }
        });

        if (isRevese) {
            Collections.reverse(listSorted);
        }

        return listSorted;
    }

    public List<Sanh> sortByGiaThue(boolean isRevese) {
        List<Sanh> listSorted = listAllSanh;
        Collections.sort(listSorted, new Comparator<Sanh>() {
            public int compare(Sanh hoaDon1, Sanh hoaDon2) {
                if (hoaDon1.getGiaThueSanh() > hoaDon2.getGiaThueSanh()) {
                    return 1;
                } else if (hoaDon1.getGiaThueSanh() < hoaDon2.getGiaThueSanh()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        if (isRevese) {
            Collections.reverse(listSorted);
        }
        return listSorted;
    }

    public List<Sanh> sortBySoLuong(boolean isRevese) {
        List<Sanh> listSorted = listAllSanh;
        Collections.sort(listSorted, new Comparator<Sanh>() {
            public int compare(Sanh hoaDon1, Sanh hoaDon2) {
                if (hoaDon1.getSucChua() > hoaDon2.getSucChua()) {
                    return 1;
                } else if (hoaDon1.getSucChua() < hoaDon2.getSucChua()) {
                    return -1;
                } else {
                    return 0;
                }
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
        txtTimKiem = new javax.swing.JTextField();
        lblSearch = new javax.swing.JLabel();
        txtGia1Ban = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        cbbLoaiSanh = new com.ui.swing.Combobox();
        cbbSort = new com.ui.swing.Combobox();
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
        tblSanh = new com.ui.swing.Table();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbbSortBy = new com.ui.swing.Combobox();
        jLabel30 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        txtMaSanh = new javax.swing.JTextField();
        txtTenSanh = new javax.swing.JTextField();
        txtSoLuongNguoi = new javax.swing.JTextField();
        txtGiaThue = new javax.swing.JTextField();

        pnlEmplpyeeManager.setBackground(new java.awt.Color(255, 255, 255));
        pnlEmplpyeeManager.setMinimumSize(new java.awt.Dimension(1600, 838));
        pnlEmplpyeeManager.setPreferredSize(new java.awt.Dimension(1650, 950));
        pnlEmplpyeeManager.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTimKiem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
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
        pnlEmplpyeeManager.add(txtTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 20, 388, 35));

        lblSearch.setBackground(new java.awt.Color(204, 204, 255));
        lblSearch.setForeground(new java.awt.Color(102, 0, 255));
        lblSearch.setAutoscrolls(true);
        lblSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSearchMouseClicked(evt);
            }
        });
        pnlEmplpyeeManager.add(lblSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, -1, 35));

        txtGia1Ban.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGia1BanActionPerformed(evt);
            }
        });
        pnlEmplpyeeManager.add(txtGia1Ban, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 600, 340, 35));

        jLabel25.setText("Mã sảnh");
        pnlEmplpyeeManager.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 92, -1));

        jLabel27.setText("Loại sảnh");
        pnlEmplpyeeManager.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 130, -1));

        jLabel28.setText("Số lượng người");
        pnlEmplpyeeManager.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 130, -1));

        cbbLoaiSanh.setLabeText("");
        cbbLoaiSanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLoaiSanhActionPerformed(evt);
            }
        });
        pnlEmplpyeeManager.add(cbbLoaiSanh, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 340, 40));

        cbbSort.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tăng dần", "Giảm dần" }));
        cbbSort.setLabeText("");
        cbbSort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbSortActionPerformed(evt);
            }
        });
        pnlEmplpyeeManager.add(cbbSort, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 0, 120, 54));

        jLabel31.setText("Tên sảnh");
        pnlEmplpyeeManager.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 130, -1));

        btnMoi.setBackground(new java.awt.Color(81, 194, 225));
        btnMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnMoi.setText("Mới");
        btnMoi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });
        pnlEmplpyeeManager.add(btnMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 730, 80, -1));

        btnFirst.setBackground(new java.awt.Color(51, 0, 255));
        btnFirst.setForeground(new java.awt.Color(255, 255, 255));
        btnFirst.setText(">|");
        btnFirst.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnFirst.setPreferredSize(new java.awt.Dimension(45, 27));
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });
        pnlEmplpyeeManager.add(btnFirst, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 790, 70, 30));

        btnThem.setBackground(new java.awt.Color(0, 153, 0));
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setText("Thêm");
        btnThem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        pnlEmplpyeeManager.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 730, 80, -1));

        btnSua.setBackground(new java.awt.Color(0, 153, 0));
        btnSua.setForeground(new java.awt.Color(255, 255, 255));
        btnSua.setText("Sửa");
        btnSua.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        pnlEmplpyeeManager.add(btnSua, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 730, 80, -1));

        btnXoa.setBackground(new java.awt.Color(153, 24, 24));
        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setText("Xóa");
        btnXoa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        pnlEmplpyeeManager.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 730, 80, -1));

        btnLast.setBackground(new java.awt.Color(51, 0, 255));
        btnLast.setForeground(new java.awt.Color(255, 255, 255));
        btnLast.setText("|<");
        btnLast.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnLast.setPreferredSize(new java.awt.Dimension(45, 27));
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });
        pnlEmplpyeeManager.add(btnLast, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 790, 70, 30));

        btnPre.setBackground(new java.awt.Color(51, 0, 255));
        btnPre.setForeground(new java.awt.Color(255, 255, 255));
        btnPre.setText("<<");
        btnPre.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnPre.setPreferredSize(new java.awt.Dimension(45, 27));
        btnPre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreActionPerformed(evt);
            }
        });
        pnlEmplpyeeManager.add(btnPre, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 790, 80, 30));

        btnNext.setBackground(new java.awt.Color(51, 0, 255));
        btnNext.setForeground(new java.awt.Color(255, 255, 255));
        btnNext.setText(">>");
        btnNext.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnNext.setPreferredSize(new java.awt.Dimension(45, 27));
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        pnlEmplpyeeManager.add(btnNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 790, 80, 30));

        tblSanh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sảnh", "Tên sảnh", "Loại sảnh", "Số lượng người", "Giá thuê", "Giá 1 bàn"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanhMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanh);

        pnlEmplpyeeManager.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 90, 1160, 820));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/happywedding/assets/Search.png"))); // NOI18N
        pnlEmplpyeeManager.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, 40, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/happywedding/assets/filt.png"))); // NOI18N
        pnlEmplpyeeManager.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 10, 40, 40));

        cbbSortBy.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Giá Thuê", "Sức Chứa", "Mã Sảnh", " ", " " }));
        cbbSortBy.setSelectedIndex(-1);
        cbbSortBy.setLabeText("Sort by");
        cbbSortBy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbSortByActionPerformed(evt);
            }
        });
        pnlEmplpyeeManager.add(cbbSortBy, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 0, 270, 54));

        jLabel30.setText("Giá thuê");
        pnlEmplpyeeManager.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 470, 130, -1));

        jLabel32.setText("Giá 1 bàn");
        pnlEmplpyeeManager.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 560, 130, -1));

        txtMaSanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaSanhActionPerformed(evt);
            }
        });
        pnlEmplpyeeManager.add(txtMaSanh, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 340, 35));

        txtTenSanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenSanhActionPerformed(evt);
            }
        });
        pnlEmplpyeeManager.add(txtTenSanh, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 340, 35));

        txtSoLuongNguoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuongNguoiActionPerformed(evt);
            }
        });
        pnlEmplpyeeManager.add(txtSoLuongNguoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 340, 35));

        txtGiaThue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaThueActionPerformed(evt);
            }
        });
        pnlEmplpyeeManager.add(txtGiaThue, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 510, 340, 35));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlEmplpyeeManager, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlEmplpyeeManager, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        filtEmployee(txtTimKiem.getText());
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void lblSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSearchMouseClicked

    }//GEN-LAST:event_lblSearchMouseClicked

    private void txtGia1BanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGia1BanActionPerformed

    }//GEN-LAST:event_txtGia1BanActionPerformed

    private void cbbLoaiSanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLoaiSanhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbLoaiSanhActionPerformed

    private void cbbSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbSortActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbSortActionPerformed

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
        update();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void cbbSortByActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbSortByActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbSortByActionPerformed

    private void txtMaSanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSanhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaSanhActionPerformed

    private void txtTenSanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenSanhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenSanhActionPerformed

    private void txtSoLuongNguoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuongNguoiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuongNguoiActionPerformed

    private void txtGiaThueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaThueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaThueActionPerformed

    private void tblSanhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanhMouseClicked
        // TODO add your handling code here:
        currentIndex = tblSanh.rowAtPoint(evt.getPoint());

        if (this.currentIndex >= 0) {
            this.edit();
            if (evt.getClickCount() == 2) {
                // isUpdate(true);

            }
        }
    }//GEN-LAST:event_tblSanhMouseClicked

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        currentIndex = 0;
        setTblSelected(currentIndex);
        //isUpdate(false);
        edit();
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnPreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreActionPerformed
        currentIndex--;
        setTblSelected(currentIndex);
        //isUpdate(false);
        edit();        // TODO add your handling code here:
    }//GEN-LAST:event_btnPreActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        currentIndex++;
        setTblSelected(currentIndex);
        // isUpdate(false);
        edit();        // TODO add your handling code here:
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
        currentIndex = tblSanh.getRowCount() - 1;
        setTblSelected(currentIndex);
        //isUpdate(false);
        edit();
    }//GEN-LAST:event_btnFirstActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ui.swing.InkwellButton btnFirst;
    private com.ui.swing.InkwellButton btnLast;
    private com.ui.swing.InkwellButton btnMoi;
    private com.ui.swing.InkwellButton btnNext;
    private com.ui.swing.InkwellButton btnPre;
    private com.ui.swing.InkwellButton btnSua;
    private com.ui.swing.InkwellButton btnThem;
    private com.ui.swing.InkwellButton btnXoa;
    private com.ui.swing.Combobox cbbLoaiSanh;
    private com.ui.swing.Combobox cbbSort;
    private com.ui.swing.Combobox cbbSortBy;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JPanel pnlEmplpyeeManager;
    private com.ui.swing.Table tblSanh;
    private javax.swing.JTextField txtGia1Ban;
    private javax.swing.JTextField txtGiaThue;
    private javax.swing.JTextField txtMaSanh;
    private javax.swing.JTextField txtSoLuongNguoi;
    private javax.swing.JTextField txtTenSanh;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
