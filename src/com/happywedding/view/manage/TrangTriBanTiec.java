/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happywedding.view.manage;

/**
 *
 * @author ADMIN
 */
public class TrangTriBanTiec extends javax.swing.JDialog {

    /**
     * Creates new form TrangTriCong
     */
    public TrangTriBanTiec(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lblMaNH18 = new javax.swing.JLabel();
        cbbGoiDV = new com.ui.swing.Combobox();
        btnEdit = new com.ui.swing.HoverButton();
        jLabel5 = new javax.swing.JLabel();
        lblMaNH19 = new javax.swing.JLabel();
        cbbAoGhe = new com.ui.swing.Combobox();
        lblMaNH22 = new javax.swing.JLabel();
        cbbVatTT = new com.ui.swing.Combobox();
        lblMaNH24 = new javax.swing.JLabel();
        lblMaNH25 = new javax.swing.JLabel();
        cbbThamTraiban = new com.ui.swing.Combobox();
        txtGCThamTraiBan = new javax.swing.JTextField();
        txtGCAoGhe = new javax.swing.JTextField();
        txtGCVatTT = new javax.swing.JTextField();
        txtChiPhi = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCPPSThamTraiBan = new javax.swing.JTextField();
        txtCPPSAoGhe = new javax.swing.JTextField();
        txtCPPSVatTT = new javax.swing.JTextField();
        lblMaNH8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taGhiChu = new javax.swing.JTextArea();
        txtTongCPPS = new javax.swing.JTextField();
        txtTongChiPhi = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnSave = new com.ui.swing.HoverButton();
        btnReset = new com.ui.swing.HoverButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Tổng chi phí phải trả");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 480, -1, -1));

        lblMaNH18.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMaNH18.setText("Gói");
        jPanel1.add(lblMaNH18, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 50, 30));

        cbbGoiDV.setToolTipText("");
        cbbGoiDV.setLabeText("");
        cbbGoiDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbGoiDVActionPerformed(evt);
            }
        });
        jPanel1.add(cbbGoiDV, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, 320, 35));

        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setText("Tùy chỉnh");
        btnEdit.setBorderColor(new java.awt.Color(153, 24, 24));
        btnEdit.setColor(new java.awt.Color(153, 24, 24));
        btnEdit.setColorClick(new java.awt.Color(255, 51, 51));
        btnEdit.setColorOver(new java.awt.Color(255, 51, 51));
        btnEdit.setFocusPainted(false);
        btnEdit.setLabelColor(java.awt.Color.white);
        btnEdit.setLableColorClick(java.awt.Color.white);
        btnEdit.setRadius(15);
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        jPanel1.add(btnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 100, -1, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Trang trí bàn tiệc");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        lblMaNH19.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMaNH19.setText("Áo ghế");
        jPanel1.add(lblMaNH19, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, 90, 30));

        cbbAoGhe.setToolTipText("");
        cbbAoGhe.setLabeText("");
        cbbAoGhe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbAoGheActionPerformed(evt);
            }
        });
        jPanel1.add(cbbAoGhe, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 290, 330, 35));

        lblMaNH22.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMaNH22.setText("Vật trang trí");
        jPanel1.add(lblMaNH22, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 360, 90, 30));

        cbbVatTT.setToolTipText("");
        cbbVatTT.setLabeText("");
        cbbVatTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbVatTTActionPerformed(evt);
            }
        });
        jPanel1.add(cbbVatTT, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 360, 330, 35));

        lblMaNH24.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMaNH24.setText("Chi phí");
        jPanel1.add(lblMaNH24, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 530, 90, 30));

        lblMaNH25.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMaNH25.setText("Thảm trãi bàn");
        jPanel1.add(lblMaNH25, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 100, 30));

        cbbThamTraiban.setToolTipText("");
        cbbThamTraiban.setLabeText("");
        cbbThamTraiban.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbThamTraibanActionPerformed(evt);
            }
        });
        jPanel1.add(cbbThamTraiban, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 220, 330, 35));

        txtGCThamTraiBan.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtGCThamTraiBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGCThamTraiBanActionPerformed(evt);
            }
        });
        jPanel1.add(txtGCThamTraiBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 230, 360, 35));

        txtGCAoGhe.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jPanel1.add(txtGCAoGhe, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 290, 360, 35));

        txtGCVatTT.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtGCVatTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGCVatTTActionPerformed(evt);
            }
        });
        jPanel1.add(txtGCVatTT, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 360, 360, 35));

        txtChiPhi.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtChiPhi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtChiPhiActionPerformed(evt);
            }
        });
        jPanel1.add(txtChiPhi, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 530, 330, 35));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Vật trang trí");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Chi phí phát sinh");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 170, -1, -1));

        txtCPPSThamTraiBan.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtCPPSThamTraiBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCPPSThamTraiBanActionPerformed(evt);
            }
        });
        jPanel1.add(txtCPPSThamTraiBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 230, 360, 35));

        txtCPPSAoGhe.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jPanel1.add(txtCPPSAoGhe, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 290, 360, 35));

        txtCPPSVatTT.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtCPPSVatTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCPPSVatTTActionPerformed(evt);
            }
        });
        jPanel1.add(txtCPPSVatTT, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 360, 360, 35));

        lblMaNH8.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMaNH8.setText("Ghi chú");
        jPanel1.add(lblMaNH8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 440, 60, -1));

        taGhiChu.setColumns(20);
        taGhiChu.setRows(5);
        jScrollPane1.setViewportView(taGhiChu);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 430, 330, 80));

        txtTongCPPS.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTongCPPS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongCPPSActionPerformed(evt);
            }
        });
        jPanel1.add(txtTongCPPS, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 530, 360, 35));

        txtTongChiPhi.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTongChiPhi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongChiPhiActionPerformed(evt);
            }
        });
        jPanel1.add(txtTongChiPhi, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 530, 360, 35));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Ghi chú");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 170, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("Tổng chi phát phát sinh");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 490, -1, -1));

        btnSave.setBackground(new java.awt.Color(24, 153, 29));
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setText("Lưu");
        btnSave.setBorderColor(new java.awt.Color(24, 153, 29));
        btnSave.setColor(new java.awt.Color(24, 153, 29));
        btnSave.setColorClick(new java.awt.Color(0, 204, 0));
        btnSave.setColorOver(new java.awt.Color(0, 204, 0));
        btnSave.setFocusPainted(false);
        btnSave.setLabelColor(java.awt.Color.white);
        btnSave.setLableColorClick(java.awt.Color.white);
        btnSave.setRadius(15);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jPanel1.add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 610, -1, 30));

        btnReset.setBackground(new java.awt.Color(24, 37, 153));
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setText("Làm mới");
        btnReset.setBorderColor(new java.awt.Color(24, 37, 153));
        btnReset.setColor(new java.awt.Color(24, 37, 153));
        btnReset.setColorClick(new java.awt.Color(51, 51, 255));
        btnReset.setColorOver(new java.awt.Color(51, 51, 255));
        btnReset.setFocusPainted(false);
        btnReset.setLabelColor(java.awt.Color.white);
        btnReset.setLableColorClick(java.awt.Color.white);
        btnReset.setRadius(15);
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        jPanel1.add(btnReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(1280, 610, -1, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1446, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 671, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setSize(new java.awt.Dimension(1464, 718));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cbbGoiDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbGoiDVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbGoiDVActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditActionPerformed

    private void cbbAoGheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbAoGheActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbAoGheActionPerformed

    private void cbbVatTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbVatTTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbVatTTActionPerformed

    private void cbbThamTraibanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbThamTraibanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbThamTraibanActionPerformed

    private void txtGCThamTraiBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGCThamTraiBanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGCThamTraiBanActionPerformed

    private void txtGCVatTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGCVatTTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGCVatTTActionPerformed

    private void txtChiPhiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtChiPhiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtChiPhiActionPerformed

    private void txtCPPSThamTraiBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCPPSThamTraiBanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPPSThamTraiBanActionPerformed

    private void txtCPPSVatTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCPPSVatTTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPPSVatTTActionPerformed

    private void txtTongCPPSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTongCPPSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTongCPPSActionPerformed

    private void txtTongChiPhiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTongChiPhiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTongChiPhiActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnResetActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TrangTriBanTiec.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TrangTriBanTiec.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TrangTriBanTiec.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TrangTriBanTiec.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TrangTriBanTiec dialog = new TrangTriBanTiec(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ui.swing.HoverButton btnEdit;
    private com.ui.swing.HoverButton btnReset;
    private com.ui.swing.HoverButton btnSave;
    private com.ui.swing.Combobox cbbAoGhe;
    private com.ui.swing.Combobox cbbGoiDV;
    private com.ui.swing.Combobox cbbThamTraiban;
    private com.ui.swing.Combobox cbbVatTT;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblMaNH18;
    private javax.swing.JLabel lblMaNH19;
    private javax.swing.JLabel lblMaNH22;
    private javax.swing.JLabel lblMaNH24;
    private javax.swing.JLabel lblMaNH25;
    private javax.swing.JLabel lblMaNH8;
    private javax.swing.JTextArea taGhiChu;
    private javax.swing.JTextField txtCPPSAoGhe;
    private javax.swing.JTextField txtCPPSThamTraiBan;
    private javax.swing.JTextField txtCPPSVatTT;
    private javax.swing.JTextField txtChiPhi;
    private javax.swing.JTextField txtGCAoGhe;
    private javax.swing.JTextField txtGCThamTraiBan;
    private javax.swing.JTextField txtGCVatTT;
    private javax.swing.JTextField txtTongCPPS;
    private javax.swing.JTextField txtTongChiPhi;
    // End of variables declaration//GEN-END:variables
}