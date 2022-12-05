/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.happywedding.view.manage;

import com.happywedding.dao.NhanVienDAO;
import javax.swing.BorderFactory;
import javax.swing.JFrame;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

import java.util.concurrent.ThreadLocalRandom;

import com.happywedding.dao.TaiKhoanDAO;
import com.happywedding.helper.AppStatus;
import com.happywedding.model.NhanVien;
//import static com.happywedding.view.manage.DangNhap.soTaiKhoan;
/**
 *
 * @author ACER
 */
public class QuenMatKhau extends javax.swing.JDialog implements Runnable{
    
    private final String UPDATE = "UPDATE dbo.TaiKhoan SET MatKhau = ? WHERE MaTaiKhoan = ?";
    
    public QuenMatKhau(java.awt.Frame parent, boolean modal) {
        
        super(parent, modal);
        initComponents();
        txtEmail.setBorder(BorderFactory.createCompoundBorder(txtEmail.getBorder(), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
        txtMaXacNhan.setBorder(BorderFactory.createCompoundBorder(txtMaXacNhan.getBorder(), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
        txtMatKhauMoi.setBorder(BorderFactory.createCompoundBorder(txtMatKhauMoi.getBorder(), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
        
        //btnXacNhan.setVisible(false);
        
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

        lblHidePassword = new javax.swing.JLabel();
        lblShowPassword = new javax.swing.JLabel();
        btnGuiMaXacNhan = new javax.swing.JButton();
        pictureBox2 = new com.ui.swing.PictureBox();
        pictureBox1 = new com.ui.swing.PictureBox();
        txtMatKhauMoi = new javax.swing.JPasswordField();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtMaXacNhan = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnXacNhan = new com.ui.swing.component.Tab();
        lblXacNhan = new javax.swing.JLabel();
        btnTroVeDangNhap = new com.ui.swing.component.Tab();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Quên mật khẩu");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblHidePassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/happywedding/assets/HidePassword.png"))); // NOI18N
        lblHidePassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHidePasswordMouseClicked(evt);
            }
        });
        getContentPane().add(lblHidePassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 440, 50, 50));

        lblShowPassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/happywedding/assets/ShowPassword.png"))); // NOI18N
        lblShowPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblShowPasswordMouseClicked(evt);
            }
        });
        getContentPane().add(lblShowPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 440, 50, 50));

        btnGuiMaXacNhan.setText("Gửi mã xác nhận");
        btnGuiMaXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuiMaXacNhanActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuiMaXacNhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(433, 180, 130, 30));

        pictureBox2.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/happywedding/assets/QuenMatKhau.png"))); // NOI18N
        getContentPane().add(pictureBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, -20, 420, 160));

        pictureBox1.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/happywedding/assets/HappyWedding.png"))); // NOI18N
        getContentPane().add(pictureBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 110, 640, 410));

        txtMatKhauMoi.setBackground(new java.awt.Color(255, 247, 244));
        txtMatKhauMoi.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtMatKhauMoi.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        getContentPane().add(txtMatKhauMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 450, 400, 50));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel8.setText("Mật khẩu mới");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 410, -1, -1));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel3.setText("Email");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, -1, -1));

        txtMaXacNhan.setBackground(new java.awt.Color(255, 247, 244));
        txtMaXacNhan.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtMaXacNhan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        getContentPane().add(txtMaXacNhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 310, 400, 50));

        txtEmail.setBackground(new java.awt.Color(255, 247, 244));
        txtEmail.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtEmail.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        getContentPane().add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, 400, 50));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel4.setText("Mã xác nhận");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 270, -1, -1));

        btnXacNhan.setEndColor("#FFB9B9");
        btnXacNhan.setStartColor("#E9D5CA");
        btnXacNhan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXacNhanMouseClicked(evt);
            }
        });

        lblXacNhan.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lblXacNhan.setForeground(new java.awt.Color(102, 102, 102));
        lblXacNhan.setText("Xác nhận");

        javax.swing.GroupLayout btnXacNhanLayout = new javax.swing.GroupLayout(btnXacNhan);
        btnXacNhan.setLayout(btnXacNhanLayout);
        btnXacNhanLayout.setHorizontalGroup(
            btnXacNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnXacNhanLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(lblXacNhan)
                .addContainerGap(54, Short.MAX_VALUE))
        );
        btnXacNhanLayout.setVerticalGroup(
            btnXacNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblXacNhan, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        getContentPane().add(btnXacNhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 580, 200, 50));

        btnTroVeDangNhap.setEndColor("#FFB9B9");
        btnTroVeDangNhap.setStartColor("#E9D5CA");
        btnTroVeDangNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTroVeDangNhapMouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Trở về đăng nhập");

        javax.swing.GroupLayout btnTroVeDangNhapLayout = new javax.swing.GroupLayout(btnTroVeDangNhap);
        btnTroVeDangNhap.setLayout(btnTroVeDangNhapLayout);
        btnTroVeDangNhapLayout.setHorizontalGroup(
            btnTroVeDangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnTroVeDangNhapLayout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(19, 19, 19))
        );
        btnTroVeDangNhapLayout.setVerticalGroup(
            btnTroVeDangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        getContentPane().add(btnTroVeDangNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 580, 220, 50));

        jLabel1.setBackground(new java.awt.Color(255, 247, 244));
        jLabel1.setOpaque(true);
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1270, 660));

        setSize(new java.awt.Dimension(1289, 698));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnTroVeDangNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTroVeDangNhapMouseClicked
        this.dispose();
        new DangNhap(new JFrame(), true).setVisible(true);
    }//GEN-LAST:event_btnTroVeDangNhapMouseClicked

    Thread t1 = new Thread(this);
    
    private void btnGuiMaXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuiMaXacNhanActionPerformed
            
        if(!txtEmail.getText().equals("baomqpc03196@fpt.edu.vn")){
            JOptionPane.showMessageDialog(this, "Sai email");      
            return;
        }
        
        txtEmail.setEditable(false);
        t1.start();
        
    }//GEN-LAST:event_btnGuiMaXacNhanActionPerformed

    private void btnXacNhanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXacNhanMouseClicked
        
        TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
        String tenDangNhap = DangNhap.tenTaiKhoan;
        
        if(String.valueOf(txtMatKhauMoi.getPassword()).trim().equals("")){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mật khẩu");
            return;
        }
        
        taiKhoanDAO.update(UPDATE, String.valueOf(txtMatKhauMoi.getPassword()));
        
        JOptionPane.showMessageDialog(this, "Đổi mật khẩu thành công");
        this.dispose();
        new DangNhap(new JFrame(), true).setVisible(true);
        
        
    }//GEN-LAST:event_btnXacNhanMouseClicked

    public void showPassword(boolean a){
        
        if(a){
            txtMatKhauMoi.setEchoChar((char)0);
        }else{
            txtMatKhauMoi.setEchoChar('*');
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
   
    String taiKhoan = "baomqpc03196@fpt.edu.vn";
    String matKhau = "matkhaulagidayta";
    String guiDen = "baomqpc03196@fpt.edu.vn";
    String tieuDe = "Mã xác minh tài khoản email";
    
    int randomNumber = 0;
    
    
    public void goiMail(){
            
        int ranNum = ThreadLocalRandom.current().nextInt(100000, 1000000);
        randomNumber = ranNum;
        
        try{
            //Các thông số kết nối tới mail Server
            Properties prop = new Properties(); 
            prop.put("mail.smtp.host", "smtp.gmail.com");
            prop.put("mail.smtp.port", "587");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.starttls.enable", "true");
            
            //Tạo đối tượng Session đưa vào các thông tin xác thực tài khoản email
            Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(taiKhoan, matKhau);
                    }
                });
            //lấy các thông tin người dùng nhập vào trên Form
            String from = taiKhoan;
            String to = guiDen;
            String subject = tieuDe;
            String body = "Mã xác minh tài khoản email của bạn là: " + String.valueOf(ranNum);
            //Tạo đối tượng MimeMessage
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients
            (
                Message.RecipientType.TO,
                InternetAddress.parse(to)
            );
            message.setSubject(subject);
            message.setText(body);
            Transport.send(message); //gọi phương thức send để gửi message đi
            JOptionPane.showMessageDialog(this, "Đã gởi mail thành công");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Override
    public void run(){
        goiMail();
        btnGuiMaXacNhan.setEnabled(false);
        int giaTri = 61;
        btnXacNhan.setEnabled(true);
        while(true){
            try {
                // … mã lấy thời gian hệ thống hiển thị lên nút
                Thread.sleep(1000);  
                giaTri--;
                btnGuiMaXacNhan.setText("Gửi lại sau " + giaTri + "s");
                //JOptionPane.showMessageDialog(this, randomNumber);
                if(txtMaXacNhan.getText().equals(String.valueOf(randomNumber))){
                    btnXacNhan.setVisible(true);
                }
                if(giaTri == 0){
                    btnXacNhan.setEnabled(false);
                    btnGuiMaXacNhan.setText("Gửi mã xác nhận");
                    btnGuiMaXacNhan.setEnabled(true);
                    t1.stop();
                }
            } catch (InterruptedException e) {
                break;
            }
        }
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
            java.util.logging.Logger.getLogger(QuenMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuenMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuenMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuenMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                QuenMatKhau dialog = new QuenMatKhau(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnGuiMaXacNhan;
    private com.ui.swing.component.Tab btnTroVeDangNhap;
    private com.ui.swing.component.Tab btnXacNhan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel lblHidePassword;
    private javax.swing.JLabel lblShowPassword;
    private javax.swing.JLabel lblXacNhan;
    private com.ui.swing.PictureBox pictureBox1;
    private com.ui.swing.PictureBox pictureBox2;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMaXacNhan;
    private javax.swing.JPasswordField txtMatKhauMoi;
    // End of variables declaration//GEN-END:variables
}
