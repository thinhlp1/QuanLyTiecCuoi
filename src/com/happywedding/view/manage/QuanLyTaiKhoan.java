/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.happywedding.view.manage;

import com.happywedding.dao.SanhDAO;
import com.happywedding.helper.DateHelper;
import com.happywedding.helper.DialogHelper;
import com.happywedding.model.TaiKhoan;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.happywedding.dao.TaiKhoanDAO;
import com.happywedding.helper.AppStatus;
import com.happywedding.model.HopDong;
import javax.swing.JOptionPane;

import com.happywedding.dao.VaiTroTaiKhoanDAO;
import com.happywedding.model.VaiTroTaiKhoan;
import com.happywedding.dao.TaiKhoanDAO;
import com.happywedding.model.NhanVien;
import java.awt.Rectangle;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Collections;
import java.util.Comparator;

public class QuanLyTaiKhoan extends javax.swing.JPanel {

    private DefaultTableModel tblModel;
    private int currentIndex = 0;

    private List<TaiKhoan> listTaiKhoan = new ArrayList<>();
    private List<TaiKhoan> listFilted = new ArrayList<>();
    private List<VaiTroTaiKhoan> vaiTroTaiKhoan = new ArrayList<>();

    private TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();

    public QuanLyTaiKhoan() {
        initComponents();
        init();
    }

    public void init() {

        allowEdit(false);
        currentIndex = -1;

        tblTaiKhoan.fixTable(jScrollPane1);
        tblModel = (DefaultTableModel) tblTaiKhoan.getModel();
        tblTaiKhoan.setAutoscrolls(true);
        listTaiKhoan = taiKhoanDAO.select();

        for (TaiKhoan tk : listTaiKhoan) {
            listFilted.add(tk);
        }

        fillToTable(listTaiKhoan);
        loadVaiTro();
        initSort();
    }
    
    public List<TaiKhoan> sortByNameKhachHang(boolean isRevese) {
        List<TaiKhoan> listSorted = listFilted;
        Collections.sort(listSorted, new Comparator<TaiKhoan>() {
            public int compare(HopDong hopDong1, HopDong hopDong2) {
                int result = 0;
                
               
                
                String[] partNameEmployee1 = hopDong1.getTenKhachHang().split("\\s");
                String[] partNameEmployee2 = hopDong2.getTenKhachHang().split("\\s");
                 if (partNameEmployee1.length == 1){
                     System.out.println("");
                 }

                int nameLenght1 = partNameEmployee1.length;
                int nameLenght2 = partNameEmployee2.length;

                if (nameLenght1 == 1 && nameLenght2 == 1) {
                    return hopDong1.getTenKhachHang().compareToIgnoreCase(hopDong2.getTenKhachHang());
                }
                
                if (nameLenght1 == 1 && nameLenght2 != 1){
                   return hopDong1.getTenKhachHang().compareToIgnoreCase(partNameEmployee2[nameLenght2-1]);
                }else if (  nameLenght1 != -1 && nameLenght2 == 1  ) {
                     return partNameEmployee1[nameLenght1-1].compareToIgnoreCase(hopDong2.getTenKhachHang());
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

            @Override
            public int compare(TaiKhoan o1, TaiKhoan o2) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        });

        if (isRevese) {
            Collections.reverse(listSorted);
        }

        return listSorted;
    }

    public void initSort() {

        cbbSort.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (cbbSort.getSelectedIndex() == 0) {
                    if (cbbSort.getSelectedIndex() == 0) {
                        // ten tang dan
                        fillToTable(sortByNameKhachHang(false));
                    } else if (cbbSort.getSelectedIndex() == 1) {
                        // ten giam dan
                        fillToTable(sortByNameKhachHang(true));
                    }
                }
            }
        });

        cbbSort.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (cbbSort.getSelectedIndex() == 0) {
                    if (cbbSort.getSelectedIndex() == 0) {
                        // ten tang dan
                        fillToTable(sortByNameKhachHang(false));
                    } else if (cbbSort.getSelectedIndex() == 1) {
                        // ten giam dan
                        fillToTable(sortByNameKhachHang(true));
                    }
                } 
            }
        });
    }
    
    public void loadTaiKhoan(int currentIndex) {

        txtMaNhanVien.setText(String.valueOf(tblTaiKhoan.getValueAt(this.currentIndex, 1)));
        txtTenDangNhap.setText(String.valueOf(tblTaiKhoan.getValueAt(this.currentIndex, 2)));
        txtMatKhau.setText(String.valueOf(tblTaiKhoan.getValueAt(this.currentIndex, 3)));
        if (String.valueOf(tblTaiKhoan.getValueAt(this.currentIndex, 4)).equals("ADMIN")) {
            cbbVaiTro.setSelectedItem("Admin");
        } else if (String.valueOf(tblTaiKhoan.getValueAt(this.currentIndex, 4)).equals("KHO")) {
            cbbVaiTro.setSelectedItem("Quản lý kho");
        } else if (String.valueOf(tblTaiKhoan.getValueAt(this.currentIndex, 4)).equals("QLCC")) {
            cbbVaiTro.setSelectedItem("Quản lý cấp cao");
        } else if (String.valueOf(tblTaiKhoan.getValueAt(this.currentIndex, 4)).equals("TIEPTAN")) {
            cbbVaiTro.setSelectedItem("Tiếp tân");
        }

    }

    public void loadVaiTro() {

        VaiTroTaiKhoanDAO vaiTroTaiKhoanDAO = new VaiTroTaiKhoanDAO();

        vaiTroTaiKhoan = vaiTroTaiKhoanDAO.select();
        for (int i = 0; i < vaiTroTaiKhoan.size(); i++) {
            String tenVaiTro = vaiTroTaiKhoan.get(i).getTenVT();
            cbbVaiTro.addItem(tenVaiTro);
        }

    }

    public void fillToTable(List<TaiKhoan> listHopDong) {
        tblModel.setRowCount(0);
        try {

            for (TaiKhoan tk : listTaiKhoan) {
                Object[] row = {
                    tk.getMaTaiKhoan(),
                    tk.getMaNhanVien(),
                    tk.getTenDangNhap(),
                    tk.getMatKhau(),
                    tk.getVaiTro()
                };
                tblModel.addRow(row);
            }
        } catch (Exception e) {
            DialogHelper.alertError(this, "Có lỗi xảy ra khi load dữ liệu.!");
        }

    }

    public static void main(String[] args) {

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pnlEmplpyeeManager = new javax.swing.JPanel();
        txtTimKiem = new javax.swing.JTextField();
        lblSearch = new javax.swing.JLabel();
        txtTenDangNhap = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txtMatKhau = new javax.swing.JPasswordField();
        jLabel29 = new javax.swing.JLabel();
        cbbVaiTro = new com.ui.swing.Combobox();
        cbbSapXepTangGiam = new com.ui.swing.Combobox();
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
        tblTaiKhoan = new com.ui.swing.Table();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbbSort = new com.ui.swing.Combobox();
        txtMaNhanVien = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(1650, 950));

        jPanel1.setPreferredSize(new java.awt.Dimension(1680, 1000));

        pnlEmplpyeeManager.setBackground(new java.awt.Color(255, 255, 255));
        pnlEmplpyeeManager.setMinimumSize(new java.awt.Dimension(1600, 838));
        pnlEmplpyeeManager.setPreferredSize(new java.awt.Dimension(1650, 950));
        pnlEmplpyeeManager.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
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

        txtTenDangNhap.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtTenDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenDangNhapActionPerformed(evt);
            }
        });
        pnlEmplpyeeManager.add(txtTenDangNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 340, 35));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel25.setText("Mã nhân viên");
        pnlEmplpyeeManager.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 92, -1));

        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel27.setText("Mật khẩu");
        pnlEmplpyeeManager.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 130, -1));

        txtMatKhau.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtMatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMatKhauActionPerformed(evt);
            }
        });
        pnlEmplpyeeManager.add(txtMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 340, 35));

        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel29.setText("Vai trò");
        pnlEmplpyeeManager.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, 130, -1));

        cbbVaiTro.setLabeText("");
        cbbVaiTro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbVaiTroActionPerformed(evt);
            }
        });
        pnlEmplpyeeManager.add(cbbVaiTro, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 480, 340, 50));

        cbbSapXepTangGiam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tăng dần", "Giảm dần" }));
        cbbSapXepTangGiam.setLabeText("");
        cbbSapXepTangGiam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbSapXepTangGiamActionPerformed(evt);
            }
        });
        pnlEmplpyeeManager.add(cbbSapXepTangGiam, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 0, 120, 54));

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel31.setText("Tên đăng nhập");
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
        pnlEmplpyeeManager.add(btnMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 620, 80, -1));

        btnFirst.setBackground(new java.awt.Color(51, 0, 255));
        btnFirst.setForeground(new java.awt.Color(255, 255, 255));
        btnFirst.setText(">|");
        btnFirst.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnFirst.setPreferredSize(new java.awt.Dimension(45, 27));
        pnlEmplpyeeManager.add(btnFirst, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 680, 70, 30));

        btnThem.setBackground(new java.awt.Color(0, 153, 0));
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setText("Thêm");
        btnThem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        pnlEmplpyeeManager.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 620, 80, -1));

        btnSua.setBackground(new java.awt.Color(0, 153, 0));
        btnSua.setForeground(new java.awt.Color(255, 255, 255));
        btnSua.setText("Sửa");
        btnSua.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        pnlEmplpyeeManager.add(btnSua, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 620, 80, -1));

        btnXoa.setBackground(new java.awt.Color(153, 24, 24));
        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setText("Xóa");
        btnXoa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        pnlEmplpyeeManager.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 620, 80, -1));

        btnLast.setBackground(new java.awt.Color(51, 0, 255));
        btnLast.setForeground(new java.awt.Color(255, 255, 255));
        btnLast.setText("|<");
        btnLast.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnLast.setPreferredSize(new java.awt.Dimension(45, 27));
        pnlEmplpyeeManager.add(btnLast, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 680, 70, 30));

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
        pnlEmplpyeeManager.add(btnPre, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 680, 80, 30));

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
        pnlEmplpyeeManager.add(btnNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 680, 80, 30));

        tblTaiKhoan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã tài khoản", "Mã nhân viên", "Tên tài khoản", "Mật khẩu", "Vai trò"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTaiKhoanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblTaiKhoan);

        pnlEmplpyeeManager.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 100, 1250, 820));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/happywedding/assets/Search.png"))); // NOI18N
        pnlEmplpyeeManager.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, 40, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/happywedding/assets/filt.png"))); // NOI18N
        pnlEmplpyeeManager.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 10, 40, 40));

        cbbSort.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Họ và Tên" }));
        cbbSort.setSelectedIndex(-1);
        cbbSort.setLabeText("Sort by");
        cbbSort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbSortActionPerformed(evt);
            }
        });
        pnlEmplpyeeManager.add(cbbSort, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 0, 270, 54));

        txtMaNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtMaNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaNhanVienActionPerformed(evt);
            }
        });
        pnlEmplpyeeManager.add(txtMaNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 340, 35));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlEmplpyeeManager, javax.swing.GroupLayout.DEFAULT_SIZE, 1680, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlEmplpyeeManager, javax.swing.GroupLayout.DEFAULT_SIZE, 1008, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1008, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblEmployeeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEmployeeMouseClicked

    }//GEN-LAST:event_tblEmployeeMouseClicked

    private void tblEmployeeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblEmployeeKeyReleased

    }//GEN-LAST:event_tblEmployeeKeyReleased

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void lblSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSearchMouseClicked
    }//GEN-LAST:event_lblSearchMouseClicked

    private void txtTenDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenDangNhapActionPerformed

    }//GEN-LAST:event_txtTenDangNhapActionPerformed

    private void txtMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMatKhauActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMatKhauActionPerformed

    private void cbbVaiTroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbVaiTroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbVaiTroActionPerformed

    private void cbbSapXepTangGiamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbSapXepTangGiamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbSapXepTangGiamActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        txtMaNhanVien.setText("");
        txtTenDangNhap.setText("");
        txtMatKhau.setText("");
        allowEdit(true);
    }//GEN-LAST:event_btnMoiActionPerformed

    public boolean check() {

        if (txtMaNhanVien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Mời nhập mã nhân viên");
            return false;
        }
        if (txtTenDangNhap.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Mời nhập tên đăng nhập");
            return false;
        }
        if (String.valueOf(txtMatKhau.getPassword()).trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Mời nhập mật khẩu");
            return false;
        }
        if (tblTaiKhoan.getValueAt(currentIndex, 4).equals("ADMIN")) {
            JOptionPane.showMessageDialog(this, "Không được đụng tới admin");
            return false;
        }

        return true;
    }

    public void them() {

        if (check() == false) {
            return;
        }

        String maNhanVien = txtMaNhanVien.getText();
        String matKhau = String.valueOf(txtMatKhau.getPassword());

        String vaiTro;

        if (String.valueOf(cbbVaiTro.getSelectedItem()).equals("Admin")) {
            vaiTro = "ADMIN";
        } else if (String.valueOf(cbbVaiTro.getSelectedItem()).equals("Quản lý kho")) {
            vaiTro = "KHO";
        } else if (String.valueOf(cbbVaiTro.getSelectedItem()).equals("Quản lý cấp cao")) {
            vaiTro = "QLCC";
        } else if (String.valueOf(cbbVaiTro.getSelectedItem()).equals("Tiếp tân")) {
            vaiTro = "TIEPTAN";
        } else {
            JOptionPane.showMessageDialog(this, "Sai vai trò");
            return;
        }

        TaiKhoan tk = new TaiKhoan();
        tk.setMaNhanVien(maNhanVien);
        tk.setTenDangNhap(txtTenDangNhap.getText());
        tk.setMatKhau(matKhau);
        tk.setVaiTro(vaiTro);

        taiKhoanDAO.insert(tk);
        init();

        JOptionPane.showMessageDialog(this, "Thêm thành công");

    }

    public void sua() {

        if (check() == false) {
            return;
        }

        String maNhanVien = txtMaNhanVien.getText();
        String matKhau = String.valueOf(txtMatKhau.getPassword());

        String vaiTro;

        if (String.valueOf(cbbVaiTro.getSelectedItem()).equals("Admin")) {
            vaiTro = "ADMIN";
        } else if (String.valueOf(cbbVaiTro.getSelectedItem()).equals("Quản lý kho")) {
            vaiTro = "KHO";
        } else if (String.valueOf(cbbVaiTro.getSelectedItem()).equals("Quản lý cấp cao")) {
            vaiTro = "QLCC";
        } else if (String.valueOf(cbbVaiTro.getSelectedItem()).equals("Tiếp tân")) {
            vaiTro = "TIEPTAN";
        } else {
            JOptionPane.showMessageDialog(this, "Sai vai trò");
            return;
        }

        TaiKhoan tk = new TaiKhoan();
        tk.setTenDangNhap(txtTenDangNhap.getText());
        tk.setMatKhau(matKhau);
        tk.setVaiTro(vaiTro);
        tk.setMaTaiKhoan((int) tblTaiKhoan.getValueAt(currentIndex, 0));

        taiKhoanDAO.sua(tk);
        init();

        JOptionPane.showMessageDialog(this, "Sửa thành công");

    }

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        them();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        sua();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        if (!check()) {
            return;
        }
        taiKhoanDAO.delete(String.valueOf(tblTaiKhoan.getValueAt(currentIndex, 0)));
        init();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void cbbSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbSortActionPerformed
    
        int oldIndex = cbbSort.getSelectedIndex();
        cbbSort.setSelectedIndex(0);
        cbbSort.setSelectedIndex(oldIndex);
        
    }//GEN-LAST:event_cbbSortActionPerformed

    private void tblTaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTaiKhoanMouseClicked
        currentIndex = tblTaiKhoan.rowAtPoint(evt.getPoint());
        if (currentIndex >= 0) {
            if (evt.getClickCount() == 2) {

                allowEdit(true);
            } else {
                //String maTaiKhoan = String.valueOf(tblTaiKhoan.getValueAt(this.currentIndex, 0));
                //System.out.println(maTaiKhoan);
                allowEdit(false);
                loadTaiKhoan(currentIndex);
            }
        }
    }//GEN-LAST:event_tblTaiKhoanMouseClicked

    private void txtMaNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaNhanVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaNhanVienActionPerformed

    //Di chuyển
    public void setTblSelected(int row) {
        tblTaiKhoan.setRowSelectionInterval(row, row);
        tblTaiKhoan.scrollRectToVisible(new Rectangle(tblTaiKhoan.getCellRect(row, 0, true)));
        loadTaiKhoan(currentIndex);
    }

    //Hiển thị lên textfied
    void edit() {

        int maTaiKhoan = (int) tblTaiKhoan.getValueAt(this.currentIndex, 0);
        loadTaiKhoan(currentIndex);

    }

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        
        if(currentIndex >= listTaiKhoan.size()-1){
            currentIndex = 0;
        }else{
            currentIndex++;
        }
        setTblSelected(currentIndex);
        // isUpdate(false);
        edit();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnPreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreActionPerformed
        
        if(currentIndex <= 0){
            currentIndex = listTaiKhoan.size()-1;
        }else{
            currentIndex--;
        }
        setTblSelected(currentIndex);
        // isUpdate(false);
        edit();
    }//GEN-LAST:event_btnPreActionPerformed

    public void allowEdit(boolean a) {
        txtMaNhanVien.setEditable(a);
        txtTenDangNhap.setEditable(a);
        txtMatKhau.setEditable(a);
        cbbVaiTro.setEnabled(a);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ui.swing.InkwellButton btnFirst;
    private com.ui.swing.InkwellButton btnLast;
    private com.ui.swing.InkwellButton btnMoi;
    private com.ui.swing.InkwellButton btnNext;
    private com.ui.swing.InkwellButton btnPre;
    private com.ui.swing.InkwellButton btnSua;
    private com.ui.swing.InkwellButton btnThem;
    private com.ui.swing.InkwellButton btnXoa;
    private com.ui.swing.Combobox cbbSapXepTangGiam;
    private com.ui.swing.Combobox cbbSort;
    private com.ui.swing.Combobox cbbVaiTro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JPanel pnlEmplpyeeManager;
    private com.ui.swing.Table tblTaiKhoan;
    private javax.swing.JTextField txtMaNhanVien;
    private javax.swing.JPasswordField txtMatKhau;
    private javax.swing.JTextField txtTenDangNhap;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
