/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.happywedding.view.manage;

import com.happywedding.dao.HoaDonDAO;
import com.happywedding.helper.AppStatus;
import com.happywedding.helper.DateHelper;
import com.happywedding.helper.DialogHelper;
import com.happywedding.helper.ShareHelper;
import com.happywedding.model.HoaDon;
import com.happywedding.model.HopDong;
import com.happywedding.model.Sanh;
import com.happywedding.model.TrangThaiHopDong;
import com.ui.swing.datechooser.DateChooser;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ACER
 */
public class QuanLyHoaDon extends javax.swing.JPanel {

    private DateChooser dtChooser1 = new DateChooser();
    private DateChooser dtChooser2 = new DateChooser();
    private DefaultTableModel tblModel;
    private List<HoaDon> listHoaDon = new ArrayList<>();
    private List<HoaDon> listFilted = new ArrayList<>();
    private HoaDonDAO hoaDonDAO = new HoaDonDAO();

    private boolean isLoad = false;

    public QuanLyHoaDon() {
        initComponents();
        init();
    }

    public void init() {
        tblHoaDon.fixTable(jScrollPane1);
        tblModel = (DefaultTableModel) tblHoaDon.getModel();
        tblHoaDon.setAutoscrolls(true);
        dtChooser1.setTextRefernce(txtNgayBatDau);
        dtChooser2.setTextRefernce(txtNgayKetThuc);

        isLoad = true;

        txtNgayBatDau.setText("");
        txtNgayKetThuc.setText("");
        listHoaDon = hoaDonDAO.selectHoaDon();
        for (HoaDon hd : listHoaDon) {
            listFilted.add(hd);
        }
        fillToTable(listHoaDon);
        initSort();

    }

    public void fillToTable(List<HoaDon> listHoaDon) {
        tblModel.setRowCount(0);
        try {

            for (HoaDon hd : listHoaDon) {
                Object[] row = {
                    hd.getMaHD(),
                    hd.getMaHoaDon(),
                    DateHelper.toString(hd.getNgayLap(), "dd/MM/yyyy"),
                    hd.getTenNV(),
                    DateHelper.toString(hd.getNgayLapLan2(), "dd/MM/yyyy"),
                    hd.getTenNLLan2(),
                    ShareHelper.toMoney(hd.getTienCoc()),
                    ShareHelper.toMoney(hd.getTongTien()),
                    hd.getTrangTha() == 0 ? "Đã trả cọc" : "Đã trả hết"
                };
                tblModel.addRow(row);
            }
        } catch (Exception e) {
            DialogHelper.alertError(this, "Lỗi truy vấn dữ liệu!");
        }

    }

    public void filtedHoaDon() {
        List<HoaDon> list = new ArrayList<>();
        List<HoaDon> list2 = new ArrayList<>();
        listFilted.clear();
        String search = txtSearch.getText().trim();

        for (HoaDon hd : listHoaDon) {
            if (hd.getInfoSearch().toUpperCase().contains(search.toUpperCase())) {
                list.add(hd);
            }
        }
        for (HoaDon hd : list) {
            if (cbbTrangThai.getSelectedIndex() == 0) {
                list2.add(hd);

                continue;
            }
            if (cbbTrangThai.getSelectedIndex() == 1) {
                if (hd.getTrangTha() == 0) {
                    list2.add(hd);
                }
            } else if (cbbTrangThai.getSelectedIndex() == 2) {
                if (hd.getTrangTha() == 1) {
                    list2.add(hd);
                }
            }
        }

        for (HoaDon hd : list2) {
            if (cbbNgay.getSelectedIndex() == 0) {
                if (txtNgayBatDau.getText().length() != 0 && txtNgayKetThuc.getText().length() == 0) {

                    if (DateHelper.toString(hd.getNgayLap(), "dd/MM/yyyy").equals(txtNgayBatDau.getText())
                            || hd.getNgayLap().after(DateHelper.toDate(txtNgayBatDau.getText(), "dd/MM/yyyy"))) {
                        listFilted.add(hd);
                    }

                } else if (txtNgayBatDau.getText().length() == 0 && txtNgayKetThuc.getText().length() != 0) {
                    if (DateHelper.toString(hd.getNgayLap(), "dd/MM/yyyy").equals(txtNgayKetThuc.getText())
                            || hd.getNgayLap().before(DateHelper.toDate(txtNgayKetThuc.getText(), "dd/MM/yyyy"))) {
                        listFilted.add(hd);
                    }
                } else if (txtNgayBatDau.getText().length() != 0 && txtNgayKetThuc.getText().length() != 0) {
                    if (DateHelper.toString(hd.getNgayLap(), "dd/MM/yyyy").equals(txtNgayKetThuc.getText())
                            || hd.getNgayLap().before(DateHelper.toDate(txtNgayKetThuc.getText(), "dd/MM/yyyy")
                            )) {
                        if (DateHelper.toString(hd.getNgayLap(), "dd/MM/yyyy").equals(txtNgayBatDau.getText())
                                || hd.getNgayLap().after(DateHelper.toDate(txtNgayBatDau.getText(), "dd/MM/yyyy"))) {
                            listFilted.add(hd);
                        }
                    }
                } else {
                    listFilted.add(hd);
                }
            } else if (cbbNgay.getSelectedIndex() == 1) {
                if (txtNgayBatDau.getText().length() != 0 && txtNgayKetThuc.getText().length() == 0) {

                    if (DateHelper.toString(hd.getNgayLapLan2(), "dd/MM/yyyy").equals(txtNgayBatDau.getText())
                            || hd.getNgayLapLan2().after(DateHelper.toDate(txtNgayBatDau.getText(), "dd/MM/yyyy"))) {
                        listFilted.add(hd);
                    }

                } else if (txtNgayBatDau.getText().length() == 0 && txtNgayKetThuc.getText().length() != 0) {
                    if (DateHelper.toString(hd.getNgayLapLan2(), "dd/MM/yyyy").equals(txtNgayKetThuc.getText())
                            || hd.getNgayLapLan2().before(DateHelper.toDate(txtNgayKetThuc.getText(), "dd/MM/yyyy"))) {
                        listFilted.add(hd);
                    }
                } else if (txtNgayBatDau.getText().length() != 0 && txtNgayKetThuc.getText().length() != 0) {
                    if (DateHelper.toString(hd.getNgayLapLan2(), "dd/MM/yyyy").equals(txtNgayKetThuc.getText())
                            || hd.getNgayLapLan2().before(DateHelper.toDate(txtNgayKetThuc.getText(), "dd/MM/yyyy")
                            )) {
                        if (DateHelper.toString(hd.getNgayLapLan2(), "dd/MM/yyyy").equals(txtNgayBatDau.getText())
                                || hd.getNgayLapLan2().after(DateHelper.toDate(txtNgayBatDau.getText(), "dd/MM/yyyy"))) {
                            listFilted.add(hd);
                        }
                    }
                } else {
                    listFilted.add(hd);
                }
            }

        }
        fillToTable(listFilted);
        int oldIndex = cbbSortBy.getSelectedIndex();
        cbbSortBy.setSelectedIndex(-1);
        cbbSortBy.setSelectedIndex(oldIndex);
    }

    public void showCalendar1() {
        dtChooser1.showPopup();
    }

    public void showCalendar2() {
        dtChooser2.showPopup();
    }

    public void initSort() {

        cbbSortBy.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                switch (cbbSortBy.getSelectedIndex()) {
                    case 0:
                        if (cbbSort.getSelectedIndex() == 0) {
                            // ten tang dan
                            fillToTable(sortByTienCoc(false));
                        } else if (cbbSort.getSelectedIndex() == 1) {
                            // ten giam dan
                            fillToTable(sortByTienCoc(true));
                        }
                        break;
                    case 1:
                        if (cbbSort.getSelectedIndex() == 0) {
                            // ten tang dan
                            fillToTable(sortByTongTien(false));
                        } else if (cbbSort.getSelectedIndex() == 1) {
                            // ten giam dan
                            fillToTable(sortByTongTien(true));
                        }
                        break;
                    case 2:
                        if (cbbSort.getSelectedIndex() == 0) {
                            // ten tang dan
                            fillToTable(sortByNgayLapLan1(false));
                        } else if (cbbSort.getSelectedIndex() == 1) {
                            // ten giam dan
                            fillToTable(sortByNgayLapLan1(true));
                        } else {
                            cbbSortBy.setSelectedIndex(0);
                        }
                        break;
                    case 3:
                        if (cbbSort.getSelectedIndex() == 0) {
                            // ten tang dan
                            fillToTable(sortByNgayLapLan2(false));
                        } else if (cbbSort.getSelectedIndex() == 1) {
                            // ten giam dan
                            fillToTable(sortByNgayLapLan2(true));
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
                            fillToTable(sortByTienCoc(false));
                        } else if (cbbSort.getSelectedIndex() == 1) {
                            // ten giam dan
                            fillToTable(sortByTienCoc(true));
                        }
                        break;
                    case 1:
                        if (cbbSort.getSelectedIndex() == 0) {
                            // ten tang dan
                            fillToTable(sortByTongTien(false));
                        } else if (cbbSort.getSelectedIndex() == 1) {
                            // ten giam dan
                            fillToTable(sortByTongTien(true));
                        }
                        break;
                    case 2:
                        if (cbbSort.getSelectedIndex() == 0) {
                            // ten tang dan
                            fillToTable(sortByNgayLapLan1(false));
                        } else if (cbbSort.getSelectedIndex() == 1) {
                            // ten giam dan
                            fillToTable(sortByNgayLapLan1(true));
                        } else {
                            cbbSortBy.setSelectedIndex(0);
                        }
                        break;
                    case 3:
                        if (cbbSort.getSelectedIndex() == 0) {
                            // ten tang dan
                            fillToTable(sortByNgayLapLan2(false));
                        } else if (cbbSort.getSelectedIndex() == 1) {
                            // ten giam dan
                            fillToTable(sortByNgayLapLan2(true));
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

    public List<HoaDon> sortByNgayLapLan1(boolean isRevese) {
        List<HoaDon> listSorted = listFilted;
        Collections.sort(listSorted, new Comparator<HoaDon>() {
            public int compare(HoaDon hoaDolon1, HoaDon hoaDolon2) {
                return (hoaDolon1.getNgayLap().compareTo(hoaDolon2.getNgayLap()));

            }
        });

        if (isRevese) {
            Collections.reverse(listSorted);
        }

        return listSorted;
    }

    public List<HoaDon> sortByNgayLapLan2(boolean isRevese) {
        List<HoaDon> listSorted = listFilted;
        Collections.sort(listSorted, new Comparator<HoaDon>() {
            public int compare(HoaDon hoaDolon1, HoaDon hoaDolon2) {
                return (hoaDolon1.getNgayLapLan2().compareTo(hoaDolon2.getNgayLapLan2()));

            }
        });

        if (isRevese) {
            Collections.reverse(listSorted);
        }

        return listSorted;
    }

    public List<HoaDon> sortByTienCoc(boolean isRevese) {
        List<HoaDon> listSorted = listFilted;
        Collections.sort(listSorted, new Comparator<HoaDon>() {
            public int compare(HoaDon hoaDon1, HoaDon hoaDon2) {
                if (hoaDon1.getTienCoc() > hoaDon2.getTienCoc()) {
                    return 1;
                } else if (hoaDon1.getTienCoc() < hoaDon2.getTienCoc()) {
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

    public List<HoaDon> sortByTongTien(boolean isRevese) {
        List<HoaDon> listSorted = listFilted;
        Collections.sort(listSorted, new Comparator<HoaDon>() {
            public int compare(HoaDon hoaDon1, HoaDon hoaDon2) {
                if (hoaDon1.getTongTien() > hoaDon2.getTongTien()) {
                    return 1;
                } else if (hoaDon1.getTongTien() < hoaDon2.getTongTien()) {
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
        lblSearch = new javax.swing.JLabel();
        btnChiTiet = new com.ui.swing.InkwellButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon = new com.ui.swing.Table();
        jLabel1 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        cbbSort = new com.ui.swing.Combobox();
        cbbSortBy = new com.ui.swing.Combobox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblHuyLoc = new javax.swing.JLabel();
        txtNgayBatDau = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cbbTrangThai = new com.ui.swing.Combobox();
        txtNgayKetThuc = new javax.swing.JTextField();
        cbbNgay = new com.ui.swing.Combobox();

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

        btnChiTiet.setBackground(new java.awt.Color(0, 153, 0));
        btnChiTiet.setForeground(new java.awt.Color(255, 255, 255));
        btnChiTiet.setText("Chi tiết");
        btnChiTiet.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnChiTiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChiTietActionPerformed(evt);
            }
        });
        pnlEmplpyeeManager.add(btnChiTiet, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 900, 80, -1));

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hợp đồng", "Mã hóa đơn", "Ngày lập", "Người lập", "Ngày lập lần 2", "Người lập lần 2", "Tiền cọc", "Tổng tiền", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDon.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jScrollPane1.setViewportView(tblHoaDon);

        pnlEmplpyeeManager.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 1610, 700));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/happywedding/assets/sort.png"))); // NOI18N
        pnlEmplpyeeManager.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1350, 110, 40, 40));

        txtSearch.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtSearch.setToolTipText("");
        txtSearch.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });
        pnlEmplpyeeManager.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, 610, 35));

        cbbSort.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tăng dần", "Giảm dần" }));
        cbbSort.setLabeText("");
        cbbSort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbSortActionPerformed(evt);
            }
        });
        pnlEmplpyeeManager.add(cbbSort, new org.netbeans.lib.awtextra.AbsoluteConstraints(1410, 90, 120, 54));

        cbbSortBy.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tiền Cọc", "Tổng Tiền", "Ngày Lập Lần 1", "Ngày Lập Lần 2" }));
        cbbSortBy.setSelectedIndex(-1);
        cbbSortBy.setLabeText("Sắp xếp");
        cbbSortBy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbSortByActionPerformed(evt);
            }
        });
        pnlEmplpyeeManager.add(cbbSortBy, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 90, 240, 54));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/happywedding/assets/Search.png"))); // NOI18N
        pnlEmplpyeeManager.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 40, 40));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/happywedding/assets/sort.png"))); // NOI18N
        pnlEmplpyeeManager.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 110, 40, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/happywedding/assets/filt.png"))); // NOI18N
        pnlEmplpyeeManager.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, -1, -1));

        lblHuyLoc.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblHuyLoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/happywedding/assets/notfiltd.png"))); // NOI18N
        lblHuyLoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHuyLocMouseClicked(evt);
            }
        });
        pnlEmplpyeeManager.add(lblHuyLoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(1360, 30, -1, 50));

        txtNgayBatDau.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtNgayBatDau.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNgayBatDauFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNgayBatDauFocusLost(evt);
            }
        });
        txtNgayBatDau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNgayBatDauMouseClicked(evt);
            }
        });
        txtNgayBatDau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNgayBatDauActionPerformed(evt);
            }
        });
        pnlEmplpyeeManager.add(txtNgayBatDau, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 40, 200, 35));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel5.setText("Trạng thái");
        pnlEmplpyeeManager.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 40, -1, 30));

        cbbTrangThai.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tất cả", "Trả lần 1", "Trả lần 2" }));
        cbbTrangThai.setToolTipText("");
        cbbTrangThai.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cbbTrangThai.setLabeText("");
        cbbTrangThai.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbTrangThaiItemStateChanged(evt);
            }
        });
        cbbTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTrangThaiActionPerformed(evt);
            }
        });
        pnlEmplpyeeManager.add(cbbTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 20, 220, 50));

        txtNgayKetThuc.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtNgayKetThuc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNgayKetThucFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNgayKetThucFocusLost(evt);
            }
        });
        txtNgayKetThuc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNgayKetThucMouseClicked(evt);
            }
        });
        txtNgayKetThuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNgayKetThucActionPerformed(evt);
            }
        });
        pnlEmplpyeeManager.add(txtNgayKetThuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 40, 200, 35));

        cbbNgay.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ngày trả lần \t1", "Ngày trả lần 2" }));
        cbbNgay.setToolTipText("");
        cbbNgay.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cbbNgay.setLabeText("");
        cbbNgay.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbNgayItemStateChanged(evt);
            }
        });
        cbbNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbNgayActionPerformed(evt);
            }
        });
        pnlEmplpyeeManager.add(cbbNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, 190, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlEmplpyeeManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlEmplpyeeManager, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSearchMouseClicked

    }//GEN-LAST:event_lblSearchMouseClicked

    private void btnChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChiTietActionPerformed
        int currentIndex = tblHoaDon.getSelectedRow();
        if (currentIndex >= 0) {
            String maHD = (String) tblHoaDon.getValueAt(currentIndex, 0);
            LapHopDong lapHopDong = new LapHopDong(false, maHD,true);
            AppStatus.mainApp.showForm(lapHopDong);
            AppStatus.MENU.setSelected(0);

        } else {
            DialogHelper.alertError(this, "Vui lòng chọn hợp đồng");
        }
    }//GEN-LAST:event_btnChiTietActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        if (isLoad) {
            filtedHoaDon();
        }
    }//GEN-LAST:event_txtSearchKeyReleased

    private void cbbSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbSortActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbSortActionPerformed

    private void cbbSortByActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbSortByActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbSortByActionPerformed

    private void lblHuyLocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHuyLocMouseClicked
        txtNgayBatDau.setText("");
        txtNgayKetThuc.setText("");
        txtSearch.setText("");
        cbbTrangThai.setSelectedIndex(0);
        cbbNgay.setSelectedIndex(0);

        filtedHoaDon();
    }//GEN-LAST:event_lblHuyLocMouseClicked

    private void txtNgayBatDauFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNgayBatDauFocusGained
        filtedHoaDon();
    }//GEN-LAST:event_txtNgayBatDauFocusGained

    private void txtNgayBatDauFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNgayBatDauFocusLost
        // System.out.println("thay doi");
    }//GEN-LAST:event_txtNgayBatDauFocusLost

    private void txtNgayBatDauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNgayBatDauMouseClicked
        showCalendar1();
    }//GEN-LAST:event_txtNgayBatDauMouseClicked

    private void txtNgayBatDauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgayBatDauActionPerformed

    }//GEN-LAST:event_txtNgayBatDauActionPerformed

    private void cbbTrangThaiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbTrangThaiItemStateChanged
        if (isLoad) {
            filtedHoaDon();
        }
    }//GEN-LAST:event_cbbTrangThaiItemStateChanged

    private void cbbTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTrangThaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbTrangThaiActionPerformed

    private void txtNgayKetThucFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNgayKetThucFocusGained
        filtedHoaDon();
    }//GEN-LAST:event_txtNgayKetThucFocusGained

    private void txtNgayKetThucFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNgayKetThucFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgayKetThucFocusLost

    private void txtNgayKetThucMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNgayKetThucMouseClicked
        showCalendar2();
    }//GEN-LAST:event_txtNgayKetThucMouseClicked

    private void txtNgayKetThucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgayKetThucActionPerformed

    }//GEN-LAST:event_txtNgayKetThucActionPerformed

    private void cbbNgayItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbNgayItemStateChanged
        filtedHoaDon();
    }//GEN-LAST:event_cbbNgayItemStateChanged

    private void cbbNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbNgayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbNgayActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ui.swing.InkwellButton btnChiTiet;
    private com.ui.swing.Combobox cbbNgay;
    private com.ui.swing.Combobox cbbSort;
    private com.ui.swing.Combobox cbbSortBy;
    private com.ui.swing.Combobox cbbTrangThai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblHuyLoc;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JPanel pnlEmplpyeeManager;
    private com.ui.swing.Table tblHoaDon;
    private javax.swing.JTextField txtNgayBatDau;
    private javax.swing.JTextField txtNgayKetThuc;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
