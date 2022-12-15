/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happywedding.view.manage;

import com.happywedding.dao.HopDongDAO;
import com.happywedding.dao.ThongKeDAO;
import com.happywedding.helper.AppStatus;
import com.happywedding.helper.DateHelper;
import com.happywedding.model.HopDong;
import com.ui.swing.component.DateComponent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author ADMIN
 */
public class LichDatTiec extends javax.swing.JPanel {

    private List<HopDong> listHopDong = new ArrayList<>();
    private HopDongDAO hopDongDAO = new HopDongDAO();
    private boolean isLoad = false;

    /**
     * Creates new form LichDatTiec
     */
    public LichDatTiec() {
        initComponents();

        fillCombboxNam();
        isLoad = true;
        fillCalendar(Integer.parseInt(cbbNam.getSelectedItem() + ""), Integer.parseInt(cbbMonth.getSelectedItem() + ""));

    }

    public void fillCombboxNam() {
        List<Integer> year = new ArrayList<>();

        DefaultComboBoxModel cbbNamModel = (DefaultComboBoxModel) cbbNam.getModel();

        cbbNamModel.removeAllElements();

        try {
            int firstYear = new ThongKeDAO().getFirtYear();
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            for (int i = firstYear; i <= currentYear; i++) {
                cbbNamModel.addElement(i);
            }

            cbbNamModel.setSelectedItem(currentYear);

        } catch (Exception ex) {
            //DialogHelper.alertError(this, "Có lỗi xảy ra. Vui lòng liên hệ hỗ trợ viên");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        cbbMonth.setSelectedIndex(calendar.get(Calendar.MONTH) - 1);

    }

    public void fillCalendar(int year, int month) {
        // listHopDong = hopDongDAO.select();

        Calendar calendar = Calendar.getInstance();

        int dayStart = 1;
        int dayEnd = 31;

        if (month == 11) {
            System.out.println("1");
        }

        Date dateOfMonth = DateHelper.toDate("1/" + month + "/" + year, "dd/MM/yyyy");

        calendar.setTime(dateOfMonth);

        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DATE, -1);

        dayEnd = calendar.get(Calendar.DAY_OF_MONTH);

        month = calendar.get(Calendar.MONTH) + 1;
        year = calendar.get(Calendar.YEAR);
        int l = calendarPane.getComponents().length;
        if (l > 0) {
            calendarPane.remove(l - 1);
        }
        calendarPane.removeAll();

        for (int i = dayStart; i <= dayEnd; i++) {
            DateComponent dateComponent = new DateComponent();
            String date = i + "/" + month + "/" + year;

            dateComponent.setMainDate(i);

            List<HopDong> list = hopDongDAO.selectHopDongByDate(DateHelper.toDate(date, "dd/MM/yyyy"));

            if (list.size() == 0) {
                dateComponent.setIsSanh1(false);
                dateComponent.setIsSanh2(false);
            }

            for (int j = 0; j < list.size() && j < 2; j++) {

                if (j == 0) {
                    HopDong hd = list.get(j);
                    dateComponent.setIsSanh1(true);
                    dateComponent.setMaHD1(hd.getMaHD());
                    dateComponent.setSanh1(hd.getSanh());
                    dateComponent.setThoiGian1(hd.getThoiGianBatDau().substring(0, 5) + " - " + hd.getThoiGianKetThuc().substring(0, 5));

                    if ((hd.getNgayToChuc().getTime() - new Date().getTime() < 3 * 24 * 3600000) && (hd.getNgayToChuc().getTime() - new Date().getTime()) > 0) {
                        dateComponent.setHD1Color("#99FF99");
                    } else if ((DateHelper.toString(hd.getNgayToChuc(), "dd/MM/yyyy").equals(DateHelper.toString(new Date(), "dd/MM/yyyy")))) {
                        dateComponent.setHD1Color("#FF6666");
                        dateComponent.setIsToDay(true);
                    } else if ((hd.getNgayToChuc().getTime() - new Date().getTime() > 3 * 24 * 3600000)) {
                        dateComponent.setHD1Color("#99FFFF");
                    } else {
                        dateComponent.setHD1Color("#FFFF00");
                    }
                    dateComponent.setSanh1Click(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            LapHopDong lapHopDong = new LapHopDong(false, hd.getMaHD(), false);
                            lapHopDong.backToXemLich = true;
                            AppStatus.mainApp.showForm(lapHopDong);
                        }

                    });
                } else if (j == 1) {
                    HopDong hd = list.get(j);
                    dateComponent.setIsSanh2(true);
                    dateComponent.setMaHD2(hd.getMaHD());
                    dateComponent.setSanh2(hd.getSanh());
                    dateComponent.setThoiGian2(hd.getThoiGianBatDau().substring(0, 5) + " - " + hd.getThoiGianKetThuc().substring(0, 5));

                    if ((hd.getNgayToChuc().getTime() - new Date().getTime() < 3 * 24 * 3600000) && (hd.getNgayToChuc().getTime() - new Date().getTime()) > 0) {
                        dateComponent.setHD2Color("#99FF99");
                    } else if ((DateHelper.toString(hd.getNgayToChuc(), "dd/MM/yyyy").equals(DateHelper.toString(new Date(), "dd/MM/yyyy")))) {
                        dateComponent.setHD2Color("#FF6666");
                        dateComponent.setIsToDay(true);
                    } else if ((hd.getNgayToChuc().getTime() - new Date().getTime() > 3 * 24 * 3600000)) {
                        dateComponent.setHD2Color("#99FFFF");
                    } else {
                        dateComponent.setHD2Color("#FFFF00");
                    }
                    dateComponent.setSanh2Click(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            LapHopDong lapHopDong = new LapHopDong(false, hd.getMaHD(), false);
                            lapHopDong.backToXemLich = true;
                            AppStatus.mainApp.showForm(lapHopDong);
                        }

                    });
                }
            }

            dateComponent.fillDateCompnent();

            calendarPane.add(dateComponent);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        calendarPane = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        cbbNam = new com.ui.swing.Combobox();
        lblMonth = new javax.swing.JLabel();
        cbbMonth = new com.ui.swing.Combobox();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        calendarPane.setBackground(new java.awt.Color(255, 255, 255));
        calendarPane.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        calendarPane.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        add(calendarPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 1680, 890));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel6.setText("Năm");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, -1, -1));

        cbbNam.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cbbNam.setLabeText("");
        cbbNam.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbNamItemStateChanged(evt);
            }
        });
        cbbNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbNamActionPerformed(evt);
            }
        });
        add(cbbNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 100, 54));

        lblMonth.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMonth.setText("Tháng");
        lblMonth.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        lblMonth.setPreferredSize(new java.awt.Dimension(42, 40));
        add(lblMonth, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, -1, -1));

        cbbMonth.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "11", "12" }));
        cbbMonth.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cbbMonth.setLabeText("");
        cbbMonth.setMinimumSize(new java.awt.Dimension(40, 49));
        cbbMonth.setPreferredSize(new java.awt.Dimension(60, 41));
        cbbMonth.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbMonthItemStateChanged(evt);
            }
        });
        cbbMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbMonthActionPerformed(evt);
            }
        });
        add(cbbMonth, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, 100, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void cbbNamItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbNamItemStateChanged
        if (isLoad == true) {
            fillCalendar(Integer.parseInt(cbbNam.getSelectedItem() + ""), Integer.parseInt(cbbMonth.getSelectedItem() + ""));
        }
    }//GEN-LAST:event_cbbNamItemStateChanged

    private void cbbNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbNamActionPerformed

    }//GEN-LAST:event_cbbNamActionPerformed

    private void cbbMonthItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbMonthItemStateChanged
        if (isLoad) {
            fillCalendar(Integer.parseInt(cbbNam.getSelectedItem() + ""), Integer.parseInt(cbbMonth.getSelectedItem() + ""));
        }
    }//GEN-LAST:event_cbbMonthItemStateChanged

    private void cbbMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbMonthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbMonthActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel calendarPane;
    private com.ui.swing.Combobox cbbMonth;
    private com.ui.swing.Combobox cbbNam;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel lblMonth;
    // End of variables declaration//GEN-END:variables
}
