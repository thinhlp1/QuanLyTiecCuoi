/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happywedding.view.statical;

import com.happywedding.dao.HopDongDAO;
import com.happywedding.dao.ThongKeDAO;
import com.happywedding.helper.DialogHelper;
import com.happywedding.helper.ShareHelper;
import com.happywedding.model.HopDong;
import com.ui.swing.chart.ModelChartPie;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class ThongKeDichVu extends javax.swing.JPanel {

    private DefaultTableModel tblModel;
    private List<Object[]> listThongKe = new ArrayList<>();
    private List<Object[]> listFilted = new ArrayList<>();
    private ThongKeDAO thongKeDAO = new ThongKeDAO();
    private HopDongDAO hopDongDAO = new HopDongDAO();
    private boolean isLoad = false;

    /**
     * Creates new form ThongKeDoanhThuNam
     */
    public ThongKeDichVu() {
        initComponents();
        tblModel = (DefaultTableModel) tblDoanhThu.getModel();
        tblDoanhThu.fixTable(jScrollPane1);
        fillCombboxNam();

        loadThongKe();

        isLoad = true;

        fillTable(listThongKe);
        fillChart(listThongKe.get(listThongKe.size() - 1));
        initSort();
    }

    public void thongKeDichVuTheoNam(int nam) {
        List<String> listAllHopDong = hopDongDAO.selectMaHDInNam(nam);
        long chiPhiDatMon = 0;
        long chiPhiDiKem = 0;
        long ttCong = 0;
        long ttSanKhau = 0;
        long ttBan = 0;
        long ngheThuat = 0;
        for (int i = 0; i < listAllHopDong.size(); i++) {
            List<Object[]> list = thongKeDAO.thongKeDichVuTheoNamHD(nam, listAllHopDong.get(i));
            chiPhiDatMon += Long.parseLong(list.get(0)[2] + "");
            chiPhiDiKem += Long.parseLong(list.get(0)[3] + "");
            ngheThuat += Long.parseLong(list.get(0)[4] + "");
            ttBan += Long.parseLong(list.get(0)[5] + "");
            ttCong += Long.parseLong(list.get(0)[6] + "");
            ttSanKhau += Long.parseLong(list.get(0)[7] + "");
        }

        listThongKe.add(new Object[]{nam, listAllHopDong.size(), chiPhiDatMon, chiPhiDiKem, ngheThuat, ttBan, ttCong, ttSanKhau});
        listFilted.add(new Object[]{nam, listAllHopDong.size(), chiPhiDatMon, chiPhiDiKem, ngheThuat, ttBan, ttCong, ttSanKhau});
    }

    public void loadThongKe() {
        listThongKe = new ArrayList<>();
        int firstYear = (int) thongKeDAO.getFirtYear();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        for (int i = firstYear; i <= currentYear; i++) {
            thongKeDichVuTheoNam(i);
        }
    }

    public void fillCombboxNam() {
        List<Integer> year = new ArrayList<>();

        DefaultComboBoxModel cbbBatDauModel = (DefaultComboBoxModel) cbbNamBatDau.getModel();
        DefaultComboBoxModel cbbKetThucModel = (DefaultComboBoxModel) cbbNamKetThuc.getModel();
        cbbBatDauModel.removeAllElements();
        cbbKetThucModel.removeAllElements();
        try {
            int firstYear = (int) thongKeDAO.getFirtYear();
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            for (int i = firstYear; i <= currentYear; i++) {
                cbbBatDauModel.addElement(i);
            }
            for (int i = firstYear; i <= currentYear; i++) {
                cbbKetThucModel.addElement(i);
            }
            cbbBatDauModel.setSelectedItem(firstYear);
            cbbKetThucModel.setSelectedItem(currentYear);
        } catch (Exception ex) {
            //DialogHelper.alertError(this, "Có lỗi xảy ra. Vui lòng liên hệ hỗ trợ viên");
        }

    }

    public void filtedThongKe() {
        List<Object[]> list = new ArrayList<>();

        listFilted.clear();

        int namBatDau = (int) cbbNamBatDau.getSelectedItem();
        int namKetThuc = (int) cbbNamKetThuc.getSelectedItem();

        if (namBatDau > namKetThuc) {
            DialogHelper.alert(this, "Thời gian không hợp lệ");
            return;
        }

        for (int i = 0; i < listThongKe.size(); i++) {
            if (((int) listThongKe.get(i)[0]) >= namBatDau && ((int) listThongKe.get(i)[0]) <= namKetThuc) {
                listFilted.add(listThongKe.get(i));
            }
        }

        fillTable(listFilted);
        fillChart(listFilted.get(listFilted.size() - 1));

    }

    public void fillTable(List<Object[]> list) {
        tblModel.setRowCount(0);
        /// System.out.println(a);
        for (int i = 0; i < list.size(); i++) {
            Object[] model = {
                list.get(i)[0],
                list.get(i)[1],
                ShareHelper.toMoney((long) list.get(i)[2]),
                ShareHelper.toMoney((long) list.get(i)[3]),
                ShareHelper.toMoney((long) list.get(i)[4]),
                ShareHelper.toMoney((long) list.get(i)[5]),
                ShareHelper.toMoney((long) list.get(i)[6]),
                ShareHelper.toMoney((long) list.get(i)[7])};

            tblModel.addRow(model);
        }

    }

    public void fillChart(Object[] list) {
        //chartDoanhThu.removeAllData();

        if (list[0].toString().equals("2021")) {
            System.out.println("");
        }

        if (isLoad) {
            List<ModelChartPie> list1 = new ArrayList<>();
            list1.add(new ModelChartPie("Đặt món", (long) list[2], new Color(4, 174, 243)));
            list1.add(new ModelChartPie("Đi kèm", (long) list[3], new Color(215, 39, 250)));
            list1.add(new ModelChartPie("Nghệ thuật", (long) list[4], new Color(238, 167, 35)));
            list1.add(new ModelChartPie("TT Bàn tiệc", (long) list[5], new Color(44, 88, 236)));
            list1.add(new ModelChartPie("TT Cổng", (long) list[6], new Color(21, 202, 87)));
            list1.add(new ModelChartPie("TT Sân khấu", (long) list[7], new Color(127, 63, 255)));

            chartDoanhThu.setModel(list1, list[0].toString());
        }

    }

    public void initSort() {
        cbbSortBy.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (cbbSortBy.getSelectedIndex() == 0) {
                    if (cbbSort.getSelectedIndex() == 0) {
                        // ten tang dan
                        fillTable(sortByNam(false));
                    } else if (cbbSort.getSelectedIndex() == 1) {
                        // ten giam dan
                        fillTable(sortByNam(true));
                    }
                } else if (cbbSortBy.getSelectedIndex() == 1) {
                    if (cbbSort.getSelectedIndex() == 0) {

                        // ten tang dan
                        fillTable(sortBySLHD(false));
                    } else if (cbbSort.getSelectedIndex() == 1) {

                        // ten giam dan
                        // a++;
                        fillTable(sortBySLHD(true));
                    }
                } else if (cbbSortBy.getSelectedIndex() == 2) {
                    if (cbbSort.getSelectedIndex() == 0) {
                        // ten tang dan
                        fillTable(sortByDoanhThuNhoNhat(false));
                    } else if (cbbSort.getSelectedIndex() == 1) {
                        // ten giam dan
                        fillTable(sortByDoanhThuNhoNhat(true));
                    } else {
                        cbbSortBy.setSelectedIndex(0);
                    }
                } else if (cbbSortBy.getSelectedIndex() == 3) {
                    if (cbbSort.getSelectedIndex() == 0) {
                        // ten tang dan
                        fillTable(sortByDoanhThuLonNhat(false));
                    } else if (cbbSort.getSelectedIndex() == 1) {
                        // ten giam dan
                        fillTable(sortByDoanhThuLonNhat(true));
                    } else {
                        cbbSortBy.setSelectedIndex(0);
                    }
                } else if (cbbSortBy.getSelectedIndex() == 4) {
                    if (cbbSort.getSelectedIndex() == 0) {
                        // ten tang dan
                        fillTable(sortByTongDoanhThu(false));
                    } else if (cbbSort.getSelectedIndex() == 1) {
                        // ten giam dan
                        fillTable(sortByTongDoanhThu(true));
                    } else {
                        cbbSortBy.setSelectedIndex(0);
                    }
                }
            }
        });

        cbbSort.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (cbbSortBy.getSelectedIndex() == 0) {
                    if (cbbSort.getSelectedIndex() == 0) {
                        // ten tang dan
                        fillTable(sortByNam(false));
                    } else if (cbbSort.getSelectedIndex() == 1) {
                        // ten giam dan
                        fillTable(sortByNam(true));
                    }
                } else if (cbbSortBy.getSelectedIndex() == 1) {
                    if (cbbSort.getSelectedIndex() == 0) {
                        // ten tang dan
                        fillTable(sortBySLHD(false));
                    } else if (cbbSort.getSelectedIndex() == 1) {
                        // System.out.println("");
                        //a++;
                        // ten giam dan
                        fillTable(sortBySLHD(true));
                    }
                } else if (cbbSortBy.getSelectedIndex() == 2) {
                    if (cbbSort.getSelectedIndex() == 0) {
                        // ten tang dan
                        fillTable(sortByDoanhThuNhoNhat(false));
                    } else if (cbbSort.getSelectedIndex() == 1) {
                        // ten giam dan
                        fillTable(sortByDoanhThuNhoNhat(true));
                    } else {
                        cbbSortBy.setSelectedIndex(0);
                    }
                } else if (cbbSortBy.getSelectedIndex() == 3) {
                    if (cbbSort.getSelectedIndex() == 0) {
                        // ten tang dan
                        fillTable(sortByDoanhThuLonNhat(false));
                    } else if (cbbSort.getSelectedIndex() == 1) {
                        // ten giam dan
                        fillTable(sortByDoanhThuLonNhat(true));
                    } else {
                        cbbSortBy.setSelectedIndex(0);
                    }
                } else if (cbbSortBy.getSelectedIndex() == 4) {
                    if (cbbSort.getSelectedIndex() == 0) {
                        // ten tang dan
                        fillTable(sortByTongDoanhThu(false));
                    } else if (cbbSort.getSelectedIndex() == 1) {
                        // ten giam dan
                        fillTable(sortByTongDoanhThu(true));
                    } else {
                        cbbSortBy.setSelectedIndex(0);
                    }
                }
            }
        });
    }

    public List<Object[]> sortByDoanhThuLonNhat(boolean isRevese) {
        List<Object[]> listSorted = listFilted;

        Collections.sort(listSorted, new Comparator<Object[]>() {
            public int compare(Object[] data1, Object[] data2) {
                if (Long.parseLong(data1[3].toString()) > Long.parseLong(data2[3].toString())) {
                    return 1;
                } else if (Long.parseLong(data1[3].toString()) < Long.parseLong(data2[3].toString())) {
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

    public List<Object[]> sortByDoanhThuNhoNhat(boolean isRevese) {
        List<Object[]> listSorted = listFilted;
        Collections.sort(listSorted, new Comparator<Object[]>() {
            public int compare(Object[] data1, Object[] data2) {
                if (Long.parseLong(data1[2].toString()) > Long.parseLong(data2[2].toString())) {
                    return 1;
                } else if (Long.parseLong(data1[2].toString()) < Long.parseLong(data2[2].toString())) {
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

    public List<Object[]> sortByNam(boolean isRevese) {
        List<Object[]> listSorted = listFilted;
        Object c = listSorted.get(0)[0];
        Object d = listSorted.get(1)[0];
        Collections.sort(listSorted, new Comparator<Object[]>() {
            public int compare(Object[] data1, Object[] data2) {
                if (Long.parseLong(data1[0].toString()) > Long.parseLong(data2[0].toString())) {
                    return 1;
                } else if (Long.parseLong(data1[0].toString()) < Long.parseLong(data2[0].toString())) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        Object a = listSorted.get(0)[1];
        Object b = listSorted.get(1)[1];
        if (isRevese) {
            Collections.reverse(listSorted);
        }
        Object g = listSorted.get(0)[1];
        Object h = listSorted.get(1)[1];
        return listSorted;
    }

    public List<Object[]> sortBySLHD(boolean isRevese) {
        List<Object[]> listSorted = listFilted;
        Object c = listSorted.get(0)[1];
        Object d = listSorted.get(1)[1];
        Collections.sort(listSorted, new Comparator<Object[]>() {
            public int compare(Object[] data1, Object[] data2) {
                if (Long.parseLong(data1[1].toString()) > Long.parseLong(data2[1].toString())) {
                    return 1;
                } else if (Long.parseLong(data1[1].toString()) < Long.parseLong(data2[1].toString())) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        Object e = listSorted.get(0)[1];
        Object f = listSorted.get(1)[1];
        if (isRevese) {
            Collections.reverse(listSorted);
        }
        Object h = listSorted.get(0)[1];
        Object j = listSorted.get(1)[1];
        return listSorted;
    }

    public List<Object[]> sortByTongDoanhThu(boolean isRevese) {
        List<Object[]> listSorted = listFilted;
        Collections.sort(listSorted, new Comparator<Object[]>() {
            public int compare(Object[] data1, Object[] data2) {
                if (Long.parseLong(data1[4].toString()) > Long.parseLong(data2[4].toString())) {
                    return 1;
                } else if (Long.parseLong(data1[4].toString()) < Long.parseLong(data2[4].toString())) {
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDoanhThu = new com.ui.swing.Table();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbbNamKetThuc = new com.ui.swing.Combobox();
        lblSort = new javax.swing.JLabel();
        cbbSort = new com.ui.swing.Combobox();
        lblSort1 = new javax.swing.JLabel();
        cbbSortBy = new com.ui.swing.Combobox();
        cbbNamBatDau = new com.ui.swing.Combobox();
        chartDoanhThu = new com.ui.swing.chart.ChartPie();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblDoanhThu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Năm", "Số lượng HD", "Đặt món", "Đi kèm", "Nghệ thuật", "Trang trí cổng", "Trang trí bàn tiệc", "Trang trí sân khấu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDoanhThu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDoanhThuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDoanhThu);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 560, 1600, 330));
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, -1, -1));

        jLabel4.setText("Năm");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 520, -1, -1));

        cbbNamKetThuc.setLabeText("");
        cbbNamKetThuc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbNamKetThucItemStateChanged(evt);
            }
        });
        cbbNamKetThuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbNamKetThucActionPerformed(evt);
            }
        });
        jPanel1.add(cbbNamKetThuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 490, 100, 54));

        lblSort.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/happywedding/assets/sort.png"))); // NOI18N
        lblSort.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSortMouseClicked(evt);
            }
        });
        jPanel1.add(lblSort, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 510, 32, 35));

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
        jPanel1.add(cbbSort, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 490, 120, 54));

        lblSort1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/happywedding/assets/sort.png"))); // NOI18N
        lblSort1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSort1MouseClicked(evt);
            }
        });
        jPanel1.add(lblSort1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 510, 32, 35));

        cbbSortBy.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Năm", "Số Lượng Họp Đồng", "Doanh Thu Thấp Nhất", "Doanh Thu Cao Nhất", "Tổng Doanh Thu" }));
        cbbSortBy.setSelectedIndex(-1);
        cbbSortBy.setLabeText("Sort by");
        cbbSortBy.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbSortByItemStateChanged(evt);
            }
        });
        jPanel1.add(cbbSortBy, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 490, 270, 54));

        cbbNamBatDau.setLabeText("");
        cbbNamBatDau.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbNamBatDauItemStateChanged(evt);
            }
        });
        cbbNamBatDau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbNamBatDauActionPerformed(evt);
            }
        });
        jPanel1.add(cbbNamBatDau, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 490, 100, 54));
        jPanel1.add(chartDoanhThu, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, 530, 320));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1658, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 920, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbbNamKetThucItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbNamKetThucItemStateChanged
        if (isLoad == true) {
            filtedThongKe();
        }
    }//GEN-LAST:event_cbbNamKetThucItemStateChanged

    private void cbbNamKetThucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbNamKetThucActionPerformed

    }//GEN-LAST:event_cbbNamKetThucActionPerformed

    private void lblSortMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSortMouseClicked

    }//GEN-LAST:event_lblSortMouseClicked

    private void cbbSortItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbSortItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbSortItemStateChanged

    private void cbbSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbSortActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbSortActionPerformed

    private void lblSort1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSort1MouseClicked

    }//GEN-LAST:event_lblSort1MouseClicked

    private void cbbSortByItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbSortByItemStateChanged

    }//GEN-LAST:event_cbbSortByItemStateChanged

    private void cbbNamBatDauItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbNamBatDauItemStateChanged
        if (isLoad == true) {
            filtedThongKe();
        }
    }//GEN-LAST:event_cbbNamBatDauItemStateChanged

    private void cbbNamBatDauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbNamBatDauActionPerformed

    }//GEN-LAST:event_cbbNamBatDauActionPerformed

    private void tblDoanhThuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDoanhThuMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblDoanhThuMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ui.swing.Combobox cbbNamBatDau;
    private com.ui.swing.Combobox cbbNamKetThuc;
    private com.ui.swing.Combobox cbbSort;
    private com.ui.swing.Combobox cbbSortBy;
    private com.ui.swing.chart.ChartPie chartDoanhThu;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblSort;
    private javax.swing.JLabel lblSort1;
    private com.ui.swing.Table tblDoanhThu;
    // End of variables declaration//GEN-END:variables
}
