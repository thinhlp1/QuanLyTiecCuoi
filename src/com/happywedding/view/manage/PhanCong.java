/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happywedding.view.manage;

import com.happywedding.dao.PhanCongDAO;
import com.happywedding.helper.AppStatus;
import com.happywedding.helper.DateHelper;
import com.happywedding.helper.DialogHelper;
import com.happywedding.helper.ShareHelper;
import com.happywedding.model.ChiTietPhanCong;
import com.happywedding.model.NhanVien;
import com.happywedding.model.PhanCongModel;
import com.ui.swing.Combobox;
import com.ui.swing.datechooser.DateChooser;
import com.ui.swing.timepicker.TimePicker;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class PhanCong extends javax.swing.JDialog {

    private String maHD;
    private boolean isCreate;
    private DateChooser dtChooser = new DateChooser();
    private TimePicker timePickerBatDau = new TimePicker();
    private TimePicker timePickerKetThuc = new TimePicker();
    private List<ChiTietPhanCong> listChiTietPhanCong = new ArrayList<>();
    private DefaultTableModel tblModel;
    private PhanCongDAO phanCongDAO = new PhanCongDAO();
    private String maVT = "";
    private boolean isNguoiPC = false;
    private int maPC = -1;

    /**
     * Creates new form PhanCong
     */
    public PhanCong(java.awt.Frame parent, boolean modal, String maHD) {
        super(parent, modal);
        this.isCreate = modal;
        PhanCongModel pc = phanCongDAO.checkPhanCong(maHD, AppStatus.USER.getMaNV());
        if (pc != null) {

            maPC = pc.getMaPC();
            isNguoiPC = true;
        
        } else {
            
            this.isCreate = false;
        }

        this.maHD = maHD;
        initComponents();
        init();
        isInsert();
        AppStatus.PHANCONG = this;
    }

    public void init() {
        tblNhanVien.fixTable(jScrollPane2);
        dtChooser.setTextRefernce(txtNgayPhanCong);
        tblModel = (DefaultTableModel) tblNhanVien.getModel();
        txtNgayPhanCong.setText("");

        timePickerBatDau.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ketThuc = ShareHelper.to24Hour(timePickerKetThuc.getSelectedTime());
                String batDau = ShareHelper.to24Hour(timePickerBatDau.getSelectedTime());

                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                Date d1;
                Date d2;

                try {

                    if (txtKetThuc.getText().length() == 0) {
                        d1 = sdf.parse(batDau);

                        timePickerKetThuc.setSelectedTime(new Date(d1.getTime() + 2 * 3600000));
                        txtKetThuc.setText((sdf.format(new Date(d1.getTime() + 2 * 3600000))));
                        txtBatDau.setText(batDau);
                        return;
                    }

                    d1 = sdf.parse(batDau);
                    d2 = sdf.parse(ketThuc);

                    if (d2.getTime() - d1.getTime() < 2 * 3600000 && batDau != null && ketThuc != null) {
                        txtBatDau.setText(batDau);

                        d2.setTime(d1.getTime() + 2 * 3600000);
                        timePickerKetThuc.setSelectedTime(d2);
                        txtKetThuc.setText(sdf.format(d2));

                    } else {
                        txtBatDau.setText(batDau);

                        d2.setTime(d1.getTime() + 2 * 3600000);
                        timePickerKetThuc.setSelectedTime(d2);
                        txtKetThuc.setText(ketThuc);

                    }
                } catch (ParseException ex) {
                    Logger.getLogger(LapHopDong.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });
        timePickerKetThuc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ketThuc = ShareHelper.to24Hour(timePickerKetThuc.getSelectedTime());
                String batDau = ShareHelper.to24Hour(timePickerBatDau.getSelectedTime());

                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                Date d1;
                Date d2;

                try {

                    if (txtBatDau.getText().length() == 0) {
                        d2 = sdf.parse(ketThuc);

                        timePickerBatDau.setSelectedTime(new Date(d2.getTime() - 2 * 3600000));
                        txtBatDau.setText((sdf.format(new Date(d2.getTime() - 2 * 3600000))));
                        txtKetThuc.setText(ketThuc);
                        return;
                    }

                    d1 = sdf.parse(batDau);
                    d2 = sdf.parse(ketThuc);

                    if (d2.getTime() - d1.getTime() < 2 * 3600000 && batDau != null && ketThuc != null) {
                        txtKetThuc.setText(ketThuc);

                        d1.setTime(d2.getTime() - 2 * 3600000);
                        timePickerBatDau.setSelectedTime(d1);
                        txtBatDau.setText(sdf.format(d1));

                    } else {
                        txtKetThuc.setText(ketThuc);

                        d1.setTime(d2.getTime() - 2 * 3600000);
                        timePickerBatDau.setSelectedTime(d1);
                        txtBatDau.setText(batDau);

                    }
                } catch (ParseException ex) {
                    Logger.getLogger(LapHopDong.class.getName()).log(Level.SEVERE, null, ex);
                }
                // checkSanh();
            }

        });

        loadChiTietPhanCong();
        fillTable(listChiTietPhanCong);
        isView(isCreate);
    }

    public void loadChiTietPhanCong() {
        listChiTietPhanCong = phanCongDAO.selectAllChiTietPhanCong(maHD);

    }

    public void fillTable(List<ChiTietPhanCong> list) {
        tblModel.setRowCount(0);
        for (ChiTietPhanCong ct : list) {
            Object[] row = {
                ct.getMaNV(),
                ct.getTenNV(),
                ct.getTenVT(),
                DateHelper.toString(ct.getNgayPhanCong(), "dd/MM/yyyy"),
                ct.getThoiGianBatDau().substring(0, 5),
                ct.getThoiGianKetThuc().substring(0, 5)
            };
            tblModel.addRow(row);
        }
    }

    public void phanCongMoi(NhanVien nv) {
        isInsert();
        txtMaNV.setText(nv.getMaNV());
        txtHoTen.setText(nv.getHoTen());
        txtVaiTro.setText(nv.getTenVT());
        txtNgayPhanCong.setText("");
        txtBatDau.setText("");
        txtKetThuc.setText("");
        maVT = nv.getMaVT();
    }

    public void setModel(ChiTietPhanCong ct) {
        txtMaNV.setText(ct.getMaNV());
        txtHoTen.setText(ct.getTenNV());
        txtVaiTro.setText(ct.getTenVT());
        txtNgayPhanCong.setText(DateHelper.toString(ct.getNgayPhanCong(), "dd/MM/yyyy"));
        txtBatDau.setText(ct.getThoiGianBatDau().substring(0, 5));
        txtKetThuc.setText(ct.getThoiGianKetThuc().substring(0, 5));
        maVT = ct.getMaVT();

    }

    public void clear() {
        txtMaNV.setText("");
        txtHoTen.setText("");
        txtVaiTro.setText("");
        txtNgayPhanCong.setText("");
        txtBatDau.setText("");
        txtKetThuc.setText("");
    }

    public ChiTietPhanCong getModel() {

        if (!isValidHD()) {
            return null;
        }

        ChiTietPhanCong ct = new ChiTietPhanCong();
        ct.setMaPC(maPC);
        ct.setMaNV(txtMaNV.getText());
        ct.setMaVT(this.maVT);
        ct.setNgayPhanCong(DateHelper.toDate(txtNgayPhanCong.getText(), "dd/MM/yyyy"));
        ct.setMaVT(maHD);
        ct.setThoiGianBatDau(txtBatDau.getText());
        ct.setThoiGianKetThuc(txtKetThuc.getText());

        return ct;
    }

    public void insertPhanCong() {

        if (isNguoiPC) {
            return;
        }

        PhanCongModel pc = new PhanCongModel();
        pc.setMaHD(maHD);
        pc.setMaNguoiPC(AppStatus.USER.getMaNV());

        try {
            phanCongDAO.insertPhanCong(pc);
        } catch (Exception e) {
            DialogHelper.alertError(this, "Phân công không thành công");
        }
    }

    public void insertChiTiet() {
        ChiTietPhanCong ct = new ChiTietPhanCong();
        ct = getModel();
        if (ct == null) {
            return;
        }
        try {
            phanCongDAO.insertChiTietPhanCong(ct);
            clear();
            isInsert();
        } catch (Exception e) {
            DialogHelper.alertError(this, "Phân công không thành công");
        }
    }

    public void updateChiTiet() {
        ChiTietPhanCong ct = new ChiTietPhanCong();
        ct = getModel();
        if (ct == null) {
            return;
        }
        try {
            phanCongDAO.updateChiTietPhanCong(ct);
            clear();
            isInsert();
        } catch (Exception e) {
            e.printStackTrace();
            DialogHelper.alertError(this, "Cập nhật không thành công");
        }
    }

    public void deleteChiTiet() {

        try {
            phanCongDAO.deleteChiTietPhanCong(maPC + "", txtMaNV.getText());
        } catch (Exception e) {
            DialogHelper.alertError(this, "Xóa không thành công");
        }
    }

    public boolean isValidHD() {

        if (txtMaNV.getText().length() == 0) {
            DialogHelper.alertError(this, "Chọn nhân viên");
            return false;
        }

        if (txtNgayPhanCong.getText().length() == 0 || txtBatDau.getText().length() == 0 || txtKetThuc.getText().length() == 0) {
            DialogHelper.alertError(this, "Nhập đầy đủ thời gian");
            return false;
        }

        return true;
    }

    public void isInsert() {
        if (!isCreate) {
            return;
        }

        txtNgayPhanCong.setEditable(false);
        txtBatDau.setEditable(false);
        txtKetThuc.setEditable(false);

        btnThem.setVisible(true);
        btnSua.setVisible(false);
        btnXoa.setVisible(false);
        txtMaNV.setEnabled(true);
    }

    public void isEdit() {

        if (!isCreate) {
            return;
        }

        txtNgayPhanCong.setEditable(false);
        txtBatDau.setEditable(false);
        txtKetThuc.setEditable(false);

        btnThem.setVisible(false);
        btnSua.setVisible(true);
        btnXoa.setVisible(true);
        txtMaNV.setEnabled(true);
    }

    public void isView(boolean isCreate) {
        txtMaNV.setEnabled(isCreate);
//        txtBatDau.setEditable(isCreate);
//        txtNgayPhanCong.setEditable(isCreate);
//        txtKetThuc.setEditable(isCreate);
        btnThem.setVisible(isCreate);
        btnSua.setVisible(isCreate);
        btnXoa.setVisible(isCreate);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlUpdate3 = new javax.swing.JPanel();
        lblSearch = new javax.swing.JLabel();
        lblSort = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtVaiTro = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtNgayPhanCong = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtBatDau = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtKetThuc = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblNhanVien = new com.ui.swing.Table();
        jPanel1 = new javax.swing.JPanel();
        btnThem = new com.ui.swing.InkwellButton();
        btnSua = new com.ui.swing.InkwellButton();
        btnXoa = new com.ui.swing.InkwellButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        pnlUpdate3.setBackground(new java.awt.Color(255, 255, 255));
        pnlUpdate3.setMinimumSize(new java.awt.Dimension(1600, 838));
        pnlUpdate3.setPreferredSize(new java.awt.Dimension(1680, 1000));
        pnlUpdate3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblSearch.setBackground(new java.awt.Color(204, 204, 255));
        lblSearch.setForeground(new java.awt.Color(102, 0, 255));
        lblSearch.setAutoscrolls(true);
        lblSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSearchMouseClicked(evt);
            }
        });
        pnlUpdate3.add(lblSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(368, 20, -1, 33));

        lblSort.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSortMouseClicked(evt);
            }
        });
        pnlUpdate3.add(lblSort, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 30, 32, 35));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel17.setText("Mã nhân viên");
        pnlUpdate3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, -1, -1));

        txtMaNV.setEditable(false);
        txtMaNV.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtMaNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtMaNVMouseClicked(evt);
            }
        });
        txtMaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaNVActionPerformed(evt);
            }
        });
        pnlUpdate3.add(txtMaNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 340, 35));

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel18.setText("Họ và tên");
        pnlUpdate3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, -1, -1));

        txtHoTen.setEditable(false);
        txtHoTen.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        pnlUpdate3.add(txtHoTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, 340, 35));

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel19.setText("Vai trò");
        pnlUpdate3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, -1, -1));

        txtVaiTro.setEditable(false);
        txtVaiTro.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        pnlUpdate3.add(txtVaiTro, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, 340, 35));

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel20.setText("Ngày phân công");
        pnlUpdate3.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, -1, -1));

        txtNgayPhanCong.setEditable(false);
        txtNgayPhanCong.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtNgayPhanCong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNgayPhanCongMouseClicked(evt);
            }
        });
        txtNgayPhanCong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNgayPhanCongActionPerformed(evt);
            }
        });
        pnlUpdate3.add(txtNgayPhanCong, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 290, 340, 35));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel15.setText("Bắt đầu");
        pnlUpdate3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 340, -1, -1));

        txtBatDau.setEditable(false);
        txtBatDau.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtBatDau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtBatDauMouseClicked(evt);
            }
        });
        txtBatDau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBatDauActionPerformed(evt);
            }
        });
        pnlUpdate3.add(txtBatDau, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 370, 140, 35));

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel23.setText("Kết thúc");
        pnlUpdate3.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 340, -1, -1));

        txtKetThuc.setEditable(false);
        txtKetThuc.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtKetThuc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtKetThucMouseClicked(evt);
            }
        });
        txtKetThuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKetThucActionPerformed(evt);
            }
        });
        pnlUpdate3.add(txtKetThuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 370, 160, 35));

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nhân viên", "Họ tên nhân viên", "Vai trò", "Ngày phân công", "Bắt đầu", "Kết thúc"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNhanVien.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblNhanVien);

        pnlUpdate3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 30, 1080, 660));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 15, 5));

        btnThem.setBackground(new java.awt.Color(0, 153, 0));
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setText("Thêm");
        btnThem.setFocusPainted(false);
        btnThem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnThem.setPreferredSize(new java.awt.Dimension(60, 27));
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel1.add(btnThem);

        btnSua.setBackground(new java.awt.Color(0, 153, 0));
        btnSua.setForeground(new java.awt.Color(255, 255, 255));
        btnSua.setText("Sửa");
        btnSua.setFocusPainted(false);
        btnSua.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSua.setPreferredSize(new java.awt.Dimension(60, 27));
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        jPanel1.add(btnSua);

        btnXoa.setBackground(new java.awt.Color(153, 24, 24));
        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setText("Xóa");
        btnXoa.setFocusPainted(false);
        btnXoa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnXoa.setPreferredSize(new java.awt.Dimension(60, 27));
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        jPanel1.add(btnXoa);

        pnlUpdate3.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 440, 280, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlUpdate3, javax.swing.GroupLayout.DEFAULT_SIZE, 1600, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlUpdate3, javax.swing.GroupLayout.PREFERRED_SIZE, 718, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblSortMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSortMouseClicked

    }//GEN-LAST:event_lblSortMouseClicked

    private void lblSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSearchMouseClicked

    }//GEN-LAST:event_lblSearchMouseClicked

    private void txtMaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaNVActionPerformed
        new ChonNhanVien(new JFrame(), true,maHD).setVisible(true);
        isInsert();
    }//GEN-LAST:event_txtMaNVActionPerformed

    private void txtMaNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMaNVMouseClicked
       new ChonNhanVien(new JFrame(), true,maHD).setVisible(true);
    }//GEN-LAST:event_txtMaNVMouseClicked

    private void txtBatDauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBatDauActionPerformed

    }//GEN-LAST:event_txtBatDauActionPerformed

    private void txtKetThucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKetThucActionPerformed

    }//GEN-LAST:event_txtKetThucActionPerformed

    private void txtNgayPhanCongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgayPhanCongActionPerformed

    }//GEN-LAST:event_txtNgayPhanCongActionPerformed

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        int currentIndex = tblNhanVien.getSelectedRow();
        if (currentIndex > -1) {
            setModel(listChiTietPhanCong.get(currentIndex));
            isEdit();
        }
    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (maPC == -1) {
            insertPhanCong();
        }

        insertChiTiet();
        loadChiTietPhanCong();
        fillTable(listChiTietPhanCong);
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        updateChiTiet();
        loadChiTietPhanCong();
        fillTable(listChiTietPhanCong);
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        deleteChiTiet();
        clear();
        isInsert();
        loadChiTietPhanCong();
        fillTable(listChiTietPhanCong);
    }//GEN-LAST:event_btnXoaActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        AppStatus.PHANCONG = null;
    }//GEN-LAST:event_formWindowClosing

    private void txtNgayPhanCongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNgayPhanCongMouseClicked
        dtChooser.showPopup();
    }//GEN-LAST:event_txtNgayPhanCongMouseClicked

    private void txtBatDauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBatDauMouseClicked
        timePickerBatDau.showPopup(this, 200, 200);
    }//GEN-LAST:event_txtBatDauMouseClicked

    private void txtKetThucMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtKetThucMouseClicked
        timePickerKetThuc.showPopup(this, 300, 200);
    }//GEN-LAST:event_txtKetThucMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ui.swing.InkwellButton btnSua;
    private com.ui.swing.InkwellButton btnThem;
    private com.ui.swing.InkwellButton btnXoa;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JLabel lblSort;
    private javax.swing.JPanel pnlUpdate3;
    private com.ui.swing.Table tblNhanVien;
    private javax.swing.JTextField txtBatDau;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtKetThuc;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtNgayPhanCong;
    private javax.swing.JTextField txtVaiTro;
    // End of variables declaration//GEN-END:variables
}
