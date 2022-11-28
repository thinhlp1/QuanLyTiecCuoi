/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.happywedding.view.manage;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import com.happywedding.dao.TaiKhoanDAO;
import com.happywedding.model.TaiKhoan;

/**
 *
 * @author ACER
 */
public class DangNhap extends javax.swing.JDialog {

    /**
     * Creates new form DangNhap
     */
    public DangNhap(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        txtTenDangNhap.setBorder(BorderFactory.createCompoundBorder(txtTenDangNhap.getBorder(), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
        txtMatKhau.setBorder(BorderFactory.createCompoundBorder(txtMatKhau.getBorder(), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
       
        lblHidePassword.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblShowPassword = new javax.swing.JLabel();
        lblHidePassword = new javax.swing.JLabel();
        txtMatKhau = new javax.swing.JPasswordField();
        pictureBox2 = new com.ui.swing.PictureBox();
        pictureBox1 = new com.ui.swing.PictureBox();
        btnQuenMatKhau = new com.ui.swing.component.Tab();
        jLabel7 = new javax.swing.JLabel();
        btnDangNhap = new com.ui.swing.component.Tab();
        jLabel6 = new javax.swing.JLabel();
        txtTenDangNhap = new javax.swing.JTextField();
        lblMatKhau = new javax.swing.JLabel();
        lblTenDangNhap = new javax.swing.JLabel();
        lblMauNen = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Đăng nhập");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblShowPassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/happywedding/assets/ShowPassword.png"))); // NOI18N
        lblShowPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblShowPasswordMouseClicked(evt);
            }
        });
        getContentPane().add(lblShowPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 410, 50, 50));

        lblHidePassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/happywedding/assets/HidePassword.png"))); // NOI18N
        lblHidePassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHidePasswordMouseClicked(evt);
            }
        });
        getContentPane().add(lblHidePassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 410, 50, 50));

        txtMatKhau.setBackground(new java.awt.Color(255, 247, 244));
        txtMatKhau.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtMatKhau.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        getContentPane().add(txtMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 420, 400, 50));

        pictureBox2.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/happywedding/assets/dangNhap.png"))); // NOI18N
        getContentPane().add(pictureBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, 430, 140));

        pictureBox1.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/happywedding/assets/HappyWedding.png"))); // NOI18N
        getContentPane().add(pictureBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 100, 640, 410));

        btnQuenMatKhau.setEndColor("#FFB9B9");
        btnQuenMatKhau.setStartColor("#E9D5CA");
        btnQuenMatKhau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnQuenMatKhauMouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Quên mật khẩu");

        javax.swing.GroupLayout btnQuenMatKhauLayout = new javax.swing.GroupLayout(btnQuenMatKhau);
        btnQuenMatKhau.setLayout(btnQuenMatKhauLayout);
        btnQuenMatKhauLayout.setHorizontalGroup(
            btnQuenMatKhauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnQuenMatKhauLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel7)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        btnQuenMatKhauLayout.setVerticalGroup(
            btnQuenMatKhauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        getContentPane().add(btnQuenMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 560, 200, 50));

        btnDangNhap.setEndColor("#FFB9B9");
        btnDangNhap.setStartColor("#E9D5CA");
        btnDangNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDangNhapMouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Đăng nhập");

        javax.swing.GroupLayout btnDangNhapLayout = new javax.swing.GroupLayout(btnDangNhap);
        btnDangNhap.setLayout(btnDangNhapLayout);
        btnDangNhapLayout.setHorizontalGroup(
            btnDangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnDangNhapLayout.createSequentialGroup()
                .addContainerGap(51, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(45, 45, 45))
        );
        btnDangNhapLayout.setVerticalGroup(
            btnDangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        getContentPane().add(btnDangNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 560, 200, 50));

        txtTenDangNhap.setBackground(new java.awt.Color(255, 247, 244));
        txtTenDangNhap.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtTenDangNhap.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        getContentPane().add(txtTenDangNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 260, 400, 50));

        lblMatKhau.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lblMatKhau.setText("Mật khẩu");
        getContentPane().add(lblMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 370, -1, -1));

        lblTenDangNhap.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lblTenDangNhap.setText("Tên đăng nhập");
        getContentPane().add(lblTenDangNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 220, -1, -1));

        lblMauNen.setBackground(new java.awt.Color(255, 247, 244));
        lblMauNen.setOpaque(true);
        getContentPane().add(lblMauNen, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1310, 660));

        setSize(new java.awt.Dimension(1323, 694));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnQuenMatKhauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQuenMatKhauMouseClicked
        this.dispose();
        new QuenMatKhau(new JFrame(), true).setVisible(true);
    }//GEN-LAST:event_btnQuenMatKhauMouseClicked

    private void btnDangNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDangNhapMouseClicked
        login();
    }//GEN-LAST:event_btnDangNhapMouseClicked

    public void showPassword(boolean a){
        
        if(a){
            txtMatKhau.setEchoChar((char)0);
        }else{
            txtMatKhau.setEchoChar('*');
        }
        
        
        lblShowPassword.setVisible(!a);
        lblHidePassword.setVisible(a);
        
    }
    
    private void lblShowPasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblShowPasswordMouseClicked
        showPassword(true);
        
    }//GEN-LAST:event_lblShowPasswordMouseClicked
    
    private void lblHidePasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHidePasswordMouseClicked
        showPassword(false);
    }//GEN-LAST:event_lblHidePasswordMouseClicked
    
    public boolean hasUser(String username){
        
        TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
        TaiKhoan soTaiKhoan = taiKhoanDAO.findByUserName(username);
        
        return true;
    }
    
    
    public void login(){
        //
        
        
        
        this.dispose();
    }
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
            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DangNhap dialog = new DangNhap(new javax.swing.JFrame(), true);
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
    private com.ui.swing.component.Tab btnDangNhap;
    private com.ui.swing.component.Tab btnQuenMatKhau;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel lblHidePassword;
    private javax.swing.JLabel lblMatKhau;
    private javax.swing.JLabel lblMauNen;
    private javax.swing.JLabel lblShowPassword;
    private javax.swing.JLabel lblTenDangNhap;
    private com.ui.swing.PictureBox pictureBox1;
    private com.ui.swing.PictureBox pictureBox2;
    private javax.swing.JPasswordField txtMatKhau;
    private javax.swing.JTextField txtTenDangNhap;
    // End of variables declaration//GEN-END:variables
}
