/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happywedding.view.manage;
import com.happywedding.helper.AppStatus;
import com.ui.swing.Table;
import com.ui.swing.datechooser.DateChooser;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class QuanLyHopDong extends javax.swing.JPanel {
    
    private DefaultTableModel tblModel ;
    private int currentIndex = 0;
    private DateChooser dtChooser1 = new DateChooser();
    private DateChooser dtChooser2 = new DateChooser();
    
    /**
     * Creates new form QuanLyHopDong
     */
    public QuanLyHopDong() {
        initComponents();
        init();
    }
    
    public void init(){
        tblHopDong.fixTable(jScrollPane2);
        tblModel = (DefaultTableModel) tblHopDong.getModel();
        tblHopDong.setAutoscrolls(true);
        dtChooser1.setTextRefernce(txtNgayBatDau);
        dtChooser2.setTextRefernce(txtNgayKetThuc);
        
        txtNgayBatDau.setText("");
        txtNgayKetThuc.setText("");
    }
    
    public void fillToTable(){
        
    }
    
    public void load(){
        System.out.println("ldsfadddfS");
    }
    
    
    
    public void showCalendar1() {
        dtChooser1.showPopup();
    }
     public void showCalendar2() {
        dtChooser2.showPopup();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGrpStatus = new javax.swing.ButtonGroup();
        pnlQuanLyHopDong = new javax.swing.JPanel();
        txtSearch = new javax.swing.JTextField();
        lblSearch = new javax.swing.JLabel();
        lblSort = new javax.swing.JLabel();
        cbbSort = new com.ui.swing.Combobox();
        lblSort1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHopDong = new com.ui.swing.Table();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNgayKetThuc = new javax.swing.JTextField();
        txtNgayBatDau = new javax.swing.JTextField();
        cbbSanh = new com.ui.swing.Combobox();
        cbbSortBy = new com.ui.swing.Combobox();
        btnChiTiet = new com.ui.swing.HoverButton();
        btnLapHopDong = new com.ui.swing.HoverButton();
        jLabel4 = new javax.swing.JLabel();
        rdBtnDaThucHien = new javax.swing.JRadioButton();
        rdBtnDangCho = new javax.swing.JRadioButton();
        rdBtnDangThucHien = new javax.swing.JRadioButton();

        setPreferredSize(new java.awt.Dimension(1620, 990));

        pnlQuanLyHopDong.setBackground(new java.awt.Color(255, 255, 255));
        pnlQuanLyHopDong.setPreferredSize(new java.awt.Dimension(1600, 950));
        pnlQuanLyHopDong.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtSearch.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtSearch.setToolTipText("Tìm theo mã, tên khách hàng,...");
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
        pnlQuanLyHopDong.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 390, 35));

        lblSearch.setBackground(new java.awt.Color(204, 204, 255));
        lblSearch.setForeground(new java.awt.Color(102, 0, 255));
        lblSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/happywedding/assets/Search.png"))); // NOI18N
        lblSearch.setAutoscrolls(true);
        lblSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSearchMouseClicked(evt);
            }
        });
        pnlQuanLyHopDong.add(lblSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, 33));

        lblSort.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/happywedding/assets/sort.png"))); // NOI18N
        lblSort.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSortMouseClicked(evt);
            }
        });
        pnlQuanLyHopDong.add(lblSort, new org.netbeans.lib.awtextra.AbsoluteConstraints(1420, 100, 32, 35));

        cbbSort.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tăng dần", "Giảm dần" }));
        cbbSort.setLabeText("");
        cbbSort.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbSortItemStateChanged(evt);
            }
        });
        cbbSort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbSortActionPerformed(evt);
            }
        });
        pnlQuanLyHopDong.add(cbbSort, new org.netbeans.lib.awtextra.AbsoluteConstraints(1460, 80, 120, 54));

        lblSort1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/happywedding/assets/sort.png"))); // NOI18N
        lblSort1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSort1MouseClicked(evt);
            }
        });
        pnlQuanLyHopDong.add(lblSort1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 100, 32, 35));

        tblHopDong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MaHD", "Người lập", "Số bàn", "Sảnh", "Ngày lập ", "Khách hàng", "Ngày tổ chức", "Bắt đầu ", "Kết thúc", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblHopDong);

        pnlQuanLyHopDong.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 1590, 730));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/happywedding/assets/filt.png"))); // NOI18N
        pnlQuanLyHopDong.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Lọc");
        pnlQuanLyHopDong.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel3.setText("Ngày tổ chức");
        pnlQuanLyHopDong.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, -1, 20));

        txtNgayKetThuc.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtNgayKetThuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNgayKetThucActionPerformed(evt);
            }
        });
        pnlQuanLyHopDong.add(txtNgayKetThuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 40, 200, 35));

        txtNgayBatDau.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtNgayBatDau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNgayBatDauActionPerformed(evt);
            }
        });
        pnlQuanLyHopDong.add(txtNgayBatDau, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 40, 200, 35));

        cbbSanh.setToolTipText("");
        cbbSanh.setLabeText("Sảnh");
        cbbSanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbSanhActionPerformed(evt);
            }
        });
        pnlQuanLyHopDong.add(cbbSanh, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 10, 270, 54));

        cbbSortBy.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Họ và Tên" }));
        cbbSortBy.setSelectedIndex(-1);
        cbbSortBy.setLabeText("Sort by");
        pnlQuanLyHopDong.add(cbbSortBy, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 80, 270, 54));

        btnChiTiet.setBackground(new java.awt.Color(77, 76, 125));
        btnChiTiet.setForeground(new java.awt.Color(255, 255, 255));
        btnChiTiet.setText("Chi tiết");
        btnChiTiet.setBorderColor(new java.awt.Color(77, 76, 125));
        btnChiTiet.setColor(new java.awt.Color(77, 76, 125));
        btnChiTiet.setColorClick(new java.awt.Color(77, 0, 196));
        btnChiTiet.setColorOver(new java.awt.Color(77, 0, 196));
        btnChiTiet.setLabelColor(java.awt.Color.white);
        btnChiTiet.setLableColorClick(java.awt.Color.white);
        btnChiTiet.setRadius(15);
        btnChiTiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChiTietActionPerformed(evt);
            }
        });
        pnlQuanLyHopDong.add(btnChiTiet, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 920, 80, 30));

        btnLapHopDong.setBackground(new java.awt.Color(77, 76, 125));
        btnLapHopDong.setForeground(new java.awt.Color(255, 255, 255));
        btnLapHopDong.setText("Lập hợp đồng");
        btnLapHopDong.setBorderColor(new java.awt.Color(77, 76, 125));
        btnLapHopDong.setColor(new java.awt.Color(77, 76, 125));
        btnLapHopDong.setColorClick(new java.awt.Color(77, 0, 196));
        btnLapHopDong.setColorOver(new java.awt.Color(77, 0, 196));
        btnLapHopDong.setLabelColor(java.awt.Color.white);
        btnLapHopDong.setLableColorClick(java.awt.Color.white);
        btnLapHopDong.setRadius(15);
        btnLapHopDong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLapHopDongActionPerformed(evt);
            }
        });
        pnlQuanLyHopDong.add(btnLapHopDong, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 920, 110, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel4.setText("Trạng thái");
        pnlQuanLyHopDong.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 930, -1, -1));

        btnGrpStatus.add(rdBtnDaThucHien);
        rdBtnDaThucHien.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        rdBtnDaThucHien.setText("Đã thực hiện");
        rdBtnDaThucHien.setContentAreaFilled(false);
        rdBtnDaThucHien.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        rdBtnDaThucHien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdBtnDaThucHienActionPerformed(evt);
            }
        });
        pnlQuanLyHopDong.add(rdBtnDaThucHien, new org.netbeans.lib.awtextra.AbsoluteConstraints(1320, 930, -1, -1));

        btnGrpStatus.add(rdBtnDangCho);
        rdBtnDangCho.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        rdBtnDangCho.setSelected(true);
        rdBtnDangCho.setText("Đang chờ duyệt");
        rdBtnDangCho.setContentAreaFilled(false);
        rdBtnDangCho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdBtnDangChoActionPerformed(evt);
            }
        });
        pnlQuanLyHopDong.add(rdBtnDangCho, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 930, -1, -1));

        btnGrpStatus.add(rdBtnDangThucHien);
        rdBtnDangThucHien.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        rdBtnDangThucHien.setText("Đang thực hiện ");
        rdBtnDangThucHien.setContentAreaFilled(false);
        rdBtnDangThucHien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdBtnDangThucHienActionPerformed(evt);
            }
        });
        pnlQuanLyHopDong.add(rdBtnDangThucHien, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 930, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlQuanLyHopDong, javax.swing.GroupLayout.DEFAULT_SIZE, 1620, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlQuanLyHopDong, javax.swing.GroupLayout.PREFERRED_SIZE, 950, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
      
    }//GEN-LAST:event_txtSearchKeyReleased

    private void lblSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSearchMouseClicked
       
    }//GEN-LAST:event_lblSearchMouseClicked

    private void lblSortMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSortMouseClicked

    }//GEN-LAST:event_lblSortMouseClicked

    private void cbbSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbSortActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbSortActionPerformed

    private void lblSort1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSort1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblSort1MouseClicked

    private void cbbSanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbSanhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbSanhActionPerformed

    private void btnLapHopDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLapHopDongActionPerformed
        AppStatus.mainApp.showForm(new LapHopDong(true));
    }//GEN-LAST:event_btnLapHopDongActionPerformed

    private void txtNgayBatDauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgayBatDauActionPerformed
        showCalendar1();
        
    }//GEN-LAST:event_txtNgayBatDauActionPerformed

    private void txtNgayKetThucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgayKetThucActionPerformed
        showCalendar2();
    }//GEN-LAST:event_txtNgayKetThucActionPerformed

    private void btnChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChiTietActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnChiTietActionPerformed

    private void rdBtnDangChoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdBtnDangChoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdBtnDangChoActionPerformed

    private void rdBtnDangThucHienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdBtnDangThucHienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdBtnDangThucHienActionPerformed

    private void rdBtnDaThucHienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdBtnDaThucHienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdBtnDaThucHienActionPerformed

    private void cbbSortItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbSortItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbSortItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ui.swing.HoverButton btnChiTiet;
    private javax.swing.ButtonGroup btnGrpStatus;
    private com.ui.swing.HoverButton btnLapHopDong;
    private com.ui.swing.Combobox cbbSanh;
    private com.ui.swing.Combobox cbbSort;
    private com.ui.swing.Combobox cbbSortBy;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JLabel lblSort;
    private javax.swing.JLabel lblSort1;
    private javax.swing.JPanel pnlQuanLyHopDong;
    private javax.swing.JRadioButton rdBtnDaThucHien;
    private javax.swing.JRadioButton rdBtnDangCho;
    private javax.swing.JRadioButton rdBtnDangThucHien;
    private com.ui.swing.Table tblHopDong;
    private javax.swing.JTextField txtNgayBatDau;
    private javax.swing.JTextField txtNgayKetThuc;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
