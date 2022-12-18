/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ui.swing.component;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import javax.accessibility.AccessibleContext;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.event.EventListenerList;
import javax.swing.plaf.ComponentUI;

/**
 *
 * @author ADMIN
 */
public class DateComponent extends javax.swing.JPanel {

    private int mainDate;
    private String sanh1;
    private String maHD1;
    private String thoiGian1;
    private String sanh2;
    private String maHD2;
    private String thoiGian2;

    public String getHD1Color() {
        return HD1Color;
    }

    public void setHD1Color(String HD1Color) {
        this.HD1Color = HD1Color;
    }

    public String getHD2Color() {
        return HD2Color;
    }

    public void setHD2Color(String HD2Color) {
        this.HD2Color = HD2Color;
    }

    

    public JLabel getLblTG1() {
        return lblTG1;
    }

    public void setLblTG1(JLabel lblTG1) {
        this.lblTG1 = lblTG1;
    }

    private String HD1Color; // đang đặt tiệc
  
    
     private String HD2Color; // đang đặt tiệc
  

    private boolean isToDay;

    private boolean isSanh1;
    private boolean isSanh2;

    private MouseAdapter sanh1Click;
    private MouseAdapter sanh2Click;

    public int getMainDate() {
        return mainDate;
    }

    public void setMainDate(int mainDate) {
        this.mainDate = mainDate;
    }

    public String getSanh1() {
        return sanh1;
    }

    public void setSanh1(String sanh1) {
        this.sanh1 = sanh1;
    }

    public String getMaHD1() {
        return maHD1;
    }

    public void setMaHD1(String maHD1) {
        this.maHD1 = maHD1;
    }

    public String getThoiGian1() {
        return thoiGian1;
    }

    public void setThoiGian1(String thoiGian1) {
        this.thoiGian1 = thoiGian1;
    }

    public String getSanh2() {
        return sanh2;
    }

    public void setSanh2(String sanh2) {
        this.sanh2 = sanh2;
    }

    public String getMaHD2() {
        return maHD2;
    }

    public void setMaHD2(String maHD2) {
        this.maHD2 = maHD2;
    }

    public String getThoiGian2() {
        return thoiGian2;
    }

    public void setThoiGian2(String thoiGian2) {
        this.thoiGian2 = thoiGian2;
    }

    public boolean isIsToDay() {
        return isToDay;
    }

    public void setIsToDay(boolean isToDay) {
        this.isToDay = isToDay;
    }

    public boolean isIsSanh1() {
        return isSanh1;
    }

    public void setIsSanh1(boolean isSanh1) {
        this.isSanh1 = isSanh1;
    }

    public boolean isIsSanh2() {
        return isSanh2;
    }

    public void setIsSanh2(boolean isSanh2) {
        this.isSanh2 = isSanh2;
    }

    public MouseAdapter getSanh1Click() {
        return sanh1Click;
    }

    public void setSanh1Click(MouseAdapter sanh1Click) {
        this.sanh1Click = sanh1Click;
    }

    public MouseAdapter getSanh2Click() {
        return sanh2Click;
    }

    public void setSanh2Click(MouseAdapter sanh2Click) {
        this.sanh2Click = sanh2Click;
    }

    public JPanel getInfoSanh1() {
        return infoSanh1;
    }

    public void setInfoSanh1(JPanel infoSanh1) {
        this.infoSanh1 = infoSanh1;
    }

    public JPanel getInfoSanh2() {
        return infoSanh2;
    }

    public void setInfoSanh2(JPanel infoSanh2) {
        this.infoSanh2 = infoSanh2;
    }

   

    public JSeparator getjSeparator1() {
        return jSeparator1;
    }

    public void setjSeparator1(JSeparator jSeparator1) {
        this.jSeparator1 = jSeparator1;
    }

    public JLabel getLblHD1() {
        return lblHD1;
    }

    public void setLblHD1(JLabel lblHD1) {
        this.lblHD1 = lblHD1;
    }

    public JLabel getLblHD2() {
        return lblHD2;
    }

    public void setLblHD2(JLabel lblHD2) {
        this.lblHD2 = lblHD2;
    }

    public JLabel getLblMainDate() {
        return lblMainDate;
    }

    public void setLblMainDate(JLabel lblMainDate) {
        this.lblMainDate = lblMainDate;
    }

    public JLabel getLblSanh1() {
        return lblSanh1;
    }

    public void setLblSanh1(JLabel lblSanh1) {
        this.lblSanh1 = lblSanh1;
    }

    public JLabel getLblSanh2() {
        return lblSanh2;
    }

    public void setLblSanh2(JLabel lblSanh2) {
        this.lblSanh2 = lblSanh2;
    }

    public JLabel getLblTG2() {
        return lblTG2;
    }

    public void setLblTG2(JLabel lblTG2) {
        this.lblTG2 = lblTG2;
    }

    public JLabel getLlbTG1() {
        return lblTG1;
    }

    public void setLlbTG1(JLabel llbTG1) {
        this.lblTG1 = llbTG1;
    }

    public ComponentUI getUi() {
        return ui;
    }

    public void setUi(ComponentUI ui) {
        this.ui = ui;
    }

    public EventListenerList getListenerList() {
        return listenerList;
    }

    public void setListenerList(EventListenerList listenerList) {
        this.listenerList = listenerList;
    }

    public AccessibleContext getAccessibleContext() {
        return accessibleContext;
    }

    public void setAccessibleContext(AccessibleContext accessibleContext) {
        this.accessibleContext = accessibleContext;
    }

    public void fillDateCompnent() {
        lblMainDate.setText(mainDate + "");

        if (isSanh1) {

            infoSanh1.setBackground(Color.decode(HD1Color));
            lblSanh1.setText(sanh1);
            lblHD1.setText(maHD1);
            lblTG1.setText(thoiGian1);
            
            infoSanh1.addMouseListener(sanh1Click);
        } else {
            infoSanh1.setVisible(false);
        }

        if (isSanh2) {

            infoSanh2.setBackground(Color.decode(HD2Color));
            lblSanh2.setText(sanh2);
            lblHD2.setText(maHD2);
            lblTG2.setText(thoiGian2);
            infoSanh2.addMouseListener(sanh2Click);
        } else {
            jSeparator1.setVisible(false);
            infoSanh2.setVisible(false);
        }
        
        if (isToDay){
        // this.setBorder(BorderFactory.createLineBorder(Color.yellow,2));
            paneHead.setBackground(Color.green);
        }
    }

    public DateComponent() {
        initComponents();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        infoSanh1 = new javax.swing.JPanel();
        lblHD1 = new javax.swing.JLabel();
        lblSanh1 = new javax.swing.JLabel();
        lblTG1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        infoSanh2 = new javax.swing.JPanel();
        lblHD2 = new javax.swing.JLabel();
        lblSanh2 = new javax.swing.JLabel();
        lblTG2 = new javax.swing.JLabel();
        paneHead = new javax.swing.JPanel();
        lblMainDate = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        setPreferredSize(new java.awt.Dimension(158, 201));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 80, -1));

        infoSanh1.setBackground(new java.awt.Color(204, 255, 204));
        infoSanh1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        infoSanh1.setPreferredSize(new java.awt.Dimension(148, 60));
        infoSanh1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblHD1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblHD1.setText("HD:");
        infoSanh1.add(lblHD1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        lblSanh1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblSanh1.setText("Sảnh 1");
        infoSanh1.add(lblSanh1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        lblTG1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblTG1.setText("TG:");
        infoSanh1.add(lblTG1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        infoSanh1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, -40, -1, 40));

        add(infoSanh1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 40, 156, 70));

        infoSanh2.setBackground(new java.awt.Color(255, 204, 204));
        infoSanh2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        infoSanh2.setPreferredSize(new java.awt.Dimension(148, 66));
        infoSanh2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblHD2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblHD2.setText("HD:");
        infoSanh2.add(lblHD2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        lblSanh2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblSanh2.setText("Sảnh 1");
        infoSanh2.add(lblSanh2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        lblTG2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblTG2.setText("TG:");
        infoSanh2.add(lblTG2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        add(infoSanh2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 130, 156, 70));

        paneHead.setBackground(new java.awt.Color(255, 153, 153));
        paneHead.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblMainDate.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        lblMainDate.setText("1");
        paneHead.add(lblMainDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 0, -1, 40));

        add(paneHead, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 0, 158, -1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel infoSanh1;
    private javax.swing.JPanel infoSanh2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblHD1;
    private javax.swing.JLabel lblHD2;
    private javax.swing.JLabel lblMainDate;
    private javax.swing.JLabel lblSanh1;
    private javax.swing.JLabel lblSanh2;
    private javax.swing.JLabel lblTG1;
    private javax.swing.JLabel lblTG2;
    private javax.swing.JPanel paneHead;
    // End of variables declaration//GEN-END:variables
}
