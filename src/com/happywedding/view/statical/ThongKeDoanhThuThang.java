/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happywedding.view.statical;

import com.happywedding.dao.ThongKeDAO;
import com.happywedding.helper.AppStatus;
import com.happywedding.helper.DialogHelper;
import com.happywedding.helper.ShareHelper;
import com.ui.swing.chart.ModelChart;
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
public class ThongKeDoanhThuThang extends javax.swing.JPanel {

    private DefaultTableModel tblModel;
    private List<Object[]> listThongKe = new ArrayList<>();
    private List<Object[]> listThongKeThang = new ArrayList<>();
    private List<Object[]> listFilted = new ArrayList<>();
    private ThongKeDAO thongKeDAO = new ThongKeDAO();
    private boolean isLoad = false;
    private List<Integer> listThang = new ArrayList<>();

    /**
     * Creates new form ThongKeDoanhThuNam
     */
    public ThongKeDoanhThuThang() {
        initComponents();
        init(-1);
    }

    public ThongKeDoanhThuThang(int year) {
        initComponents();
        init(year);
    }

    public void init(int year) {
        tblModel = (DefaultTableModel) tblDoanhThu.getModel();
        tblDoanhThu.fixTable(jScrollPane1);
        loadThongKe();

        fillCombboxNam();
        if (year == -1) {
            loadThongKeThang((int) listThongKe.get(listThongKe.size() - 1)[0]);
        } else {
            loadThongKeThang(year);
            for (int i = 0; i < cbbNamBatDau.getComponentCount(); i++) {
                if (((Integer) cbbNamBatDau.getItemAt(i)) == year) {
                    cbbNamBatDau.setSelectedIndex(i);
                    break;
                }
            }
        }

        fillTable(listThongKeThang);
        // fillChart(listThongKe);
        isLoad = true;
        initSort();

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

    public void loadThongKe() {
        listThongKe = thongKeDAO.thongKeDoanhThuNam();
    }

    public void loadThongKeThang(int year) {
        listThongKeThang = thongKeDAO.thongKeDoanhThuThang(year);

        for (int i = 0; i < listThongKeThang.size(); i++) {
            listFilted.add(listThongKeThang.get(i));
        }
    }
    int a = 0;

    public void fillTable(List<Object[]> list) {
        tblModel.setRowCount(0);
        /// System.out.println(a);
        for (int i = 0; i < list.size(); i++) {
            Object[] model = {
                list.get(i)[0],
                list.get(i)[1],
                ShareHelper.toMoney((long) list.get(i)[2]),
                ShareHelper.toMoney((long) list.get(i)[3]),
                ShareHelper.toMoney((long) list.get(i)[4])};
            tblModel.addRow(model);
        }

        fillChart(list);

    }

    public void fillChart(List<Object[]> list) {

        if (!isLoad) {
            chartDoanhThu.addLegend("Doanh thu", Color.red);
            chartDoanhThu.addLegend("Doanh thu cao nh???t", Color.yellow);
            chartDoanhThu.addLegend("Doanh thu th???p nh???t", Color.BLUE);
        }
        // new double[]{list.get(4)[[4]]};
        chartDoanhThu.removeAllData();
        for (int i = 1; i <= 12; i++) {
            boolean isExit = false;
            for (int j = 0; j < list.size(); j++) {
                if (i == ((int) listThongKeThang.get(j)[0])) {
                    chartDoanhThu.addData(new ModelChart(String.valueOf(list.get(j)[0]), new long[]{(long) list.get(j)[4], (long) list.get(j)[3], (long) list.get(j)[2]}));
                    isExit = true;
                    break;
                }
            }
            if (!isExit) {
                chartDoanhThu.addData(new ModelChart(String.valueOf(i), new long[]{(long) 0, 0, 0}));

            }

        }
    }

    public void fillCombboxNam() {
        List<Integer> year = new ArrayList<>();

        DefaultComboBoxModel cbbBatDauModel = (DefaultComboBoxModel) cbbNamBatDau.getModel();

        cbbBatDauModel.removeAllElements();

        try {
            int firstYear = (int) listThongKe.get(0)[0];
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            for (int i = firstYear; i <= currentYear; i++) {
                cbbBatDauModel.addElement(i);
            }

            cbbBatDauModel.setSelectedItem(currentYear);

        } catch (Exception ex) {
            //DialogHelper.alertError(this, "C?? l???i x???y ra. Vui l??ng li??n h??? h??? tr??? vi??n");
        }

    }

    public void filtedThongKe() {
        List<Object[]> list = new ArrayList<>();

        listFilted.clear();

        int namBatDau = (int) cbbNamBatDau.getSelectedItem();

        listThongKeThang = thongKeDAO.thongKeDoanhThuThang(namBatDau);

        for (int i = 0; i < listThongKeThang.size(); i++) {
            listFilted.add(listThongKeThang.get(i));
        }

        fillTable(listFilted);
        fillChart(listFilted);

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
        Object c = listSorted.get(0)[1];
        Object d = listSorted.get(1)[1];
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblSort = new javax.swing.JLabel();
        cbbSort = new com.ui.swing.Combobox();
        lblSort1 = new javax.swing.JLabel();
        cbbSortBy = new com.ui.swing.Combobox();
        cbbNamBatDau = new com.ui.swing.Combobox();
        chartDoanhThu = new com.ui.swing.chart.Chart();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDoanhThu = new com.ui.swing.Table();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, -1, -1));

        jLabel4.setText("N??m");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 500, -1, -1));

        lblSort.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/happywedding/assets/sort.png"))); // NOI18N
        lblSort.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSortMouseClicked(evt);
            }
        });
        jPanel1.add(lblSort, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 490, 32, 35));

        cbbSort.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "T??ng d???n", "Gi???m d???n" }));
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
        jPanel1.add(cbbSort, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 470, 120, 54));

        lblSort1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/happywedding/assets/sort.png"))); // NOI18N
        lblSort1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSort1MouseClicked(evt);
            }
        });
        jPanel1.add(lblSort1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 490, 32, 35));

        cbbSortBy.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "N??m", "S??? L?????ng H???p ?????ng", "Doanh Thu Th???p Nh???t", "Doanh Thu Cao Nh???t", "T???ng Doanh Thu" }));
        cbbSortBy.setSelectedIndex(-1);
        cbbSortBy.setLabeText("Sort by");
        cbbSortBy.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbSortByItemStateChanged(evt);
            }
        });
        jPanel1.add(cbbSortBy, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 470, 270, 54));

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
        jPanel1.add(cbbNamBatDau, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 470, 100, 54));
        jPanel1.add(chartDoanhThu, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 1540, 420));

        jLabel1.setText("VN??");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, -1));

        jLabel2.setText("Th??ng");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1580, 430, -1, -1));

        tblDoanhThu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Th??ng", "S??? l?????ng h???p ?????ng", "Danh thu th???p nh???t", "Danh thu cao nh???t", "T???ng danh thu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDoanhThu.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        tblDoanhThu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDoanhThuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDoanhThu);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 540, 1600, 330));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1716, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

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
        if (isLoad == true) {
            filtedThongKe();
        }
    }//GEN-LAST:event_cbbSortByItemStateChanged

    private void cbbNamBatDauItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbNamBatDauItemStateChanged
        if (isLoad == true) {
            filtedThongKe();
        }
    }//GEN-LAST:event_cbbNamBatDauItemStateChanged

    private void cbbNamBatDauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbNamBatDauActionPerformed

    }//GEN-LAST:event_cbbNamBatDauActionPerformed

    private void tblDoanhThuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDoanhThuMouseClicked
        int currentIndex = tblDoanhThu.getSelectedRow();
        if (evt.getClickCount() == 2) {
            int nam = (int) cbbNamBatDau.getSelectedItem();
            if (currentIndex > -1) {
                AppStatus.FORMTHONGKE.updateThongKeNgay((int) listFilted.get(currentIndex)[0], nam);
            }
        }

    }//GEN-LAST:event_tblDoanhThuMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ui.swing.Combobox cbbNamBatDau;
    private com.ui.swing.Combobox cbbSort;
    private com.ui.swing.Combobox cbbSortBy;
    private com.ui.swing.chart.Chart chartDoanhThu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblSort;
    private javax.swing.JLabel lblSort1;
    private com.ui.swing.Table tblDoanhThu;
    // End of variables declaration//GEN-END:variables
}
