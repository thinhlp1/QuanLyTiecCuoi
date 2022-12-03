
package com.happywedding.app;

import com.happywedding.dao.NhanVienDAO;
import com.happywedding.helper.AppStatus;

import com.happywedding.view.manage.*;

import com.happywedding.view.popup.AboutForm;
import com.happywedding.view.statical.ThongKe;

import com.ui.swing.component.EventMenuSelected;

import com.ui.swing.component.Header;

import com.ui.swing.component.Menu;

import java.awt.Component;
import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;

public class HappyWeddingApp extends javax.swing.JFrame {

    private MigLayout layout;
    private Menu menu;
    private Header header;

    private QuanLyHopDong quanlyHopDong;
    private QuanLyHoaDon quanLyHoaDon;
    private QuanLyNhanVien quanLyNhanVien;
    private QuanLyKho quanLyKho;
    private QuanLySanh quanLySanh;
    private QuanLyTaiKhoan quanLyTaiKhoan;
    private ThongKe thongKe;

    private int menuIndex;
    private int subMenuIndex;

    static class Role {

        static String QUANLY = "QLCC";
        static String TIEPTAN = "TIEPTAN";
        static String KHO = "KHO";
        static String ADMIN = "ADMIN";
    }

    static class MAIN_MENU_QL {

        static int QLHOPDONG = 0;
        static int QLHOADON = 1;
        static int QLNHANVIEN = 2;
        static int QLKHO = 3;
        static int QLSANH = 4;
        static int THONGKE = 5;

        static int GIOITHIEU = 7;
        static int HUONGDAN = 8;
        static int DOIMATKHAU = 10;
        static int DANGXUAT = 11;
        static int EXIT = 12;

    }

    static class MAIN_MENU_TIEPTAN {

        static int QLHOPDONG = 0;
        static int QLHOADON = 1;

        static int GIOITHIEU = 3;
        static int HUONGDAN = 4;
        static int DOIMATKHAU = 6;
        static int DANGXUAT = 7;
        static int EXIT = 8;

    }

    static class MAIN_MENU_QLKHO {

        static int QLKHO = 0;

        static int GIOITHIEU = 2;
        static int HUONGDAN = 3;
        static int DOIMATKHAU = 5;
        static int DANGXUAT = 6;
        static int EXIT = 7;

    }

    static class MAIN_MENU_ADMIN {

        static int QLTAIKHOAN = 0;
        static int QLSANH = 1;

        static int EXIT = 3;
    }

    public HappyWeddingApp() {

//        if (!AppStatus.isFirstStart()) {
//            new DangNhap(this, true).setVisible(true);
//            new Loading(this, true).setVisible(true);
//            AppStatus.loadApp();
//        }
        AppStatus.USER = new NhanVienDAO().findById("NV001");
        AppStatus.loadApp();
        initComponents();
        init();

    }

    //load	d
    public void phanQuyen() {
        String role = AppStatus.ROLE;
        if (role.equals(Role.QUANLY)) {
            menu.addEventMenuSelected(new MenuQLListener());
        } else if (role.equals(Role.TIEPTAN)) {
            menu.addEventMenuSelected(new MenuTiepTanListener());
        } else if (role.equals(Role.KHO)) {
            menu.addEventMenuSelected(new MenuQLKhoListener());
        } else if (role.equals(Role.ADMIN)) {
            menu.addEventMenuSelected(new MenuAdminListener());
        }
    }

    private void init() {
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        layout = new MigLayout("fill", "0[]0[100%, fill]0", "0[fill, top]0");
        mainPane.setLayout(layout);
        menu = new Menu();

        phanQuyen();

        header = new Header();

        mainPane.add(menu, "w 270!, spany 2");    // Span Y 2cell
        mainPane.add(header, "h 50!, wrap");
        mainPane.add(new AboutForm());
        showForm(new AboutForm());

        AppStatus.mainApp = this;
        AppStatus.MENU = menu;

        // menu.initMoving(this);
    }

    class MenuQLListener implements EventMenuSelected {

        @Override
        public void selected(int index) {
            if (index == MAIN_MENU_QL.QLHOPDONG) {
                if (quanlyHopDong == null) {
                    quanlyHopDong = new QuanLyHopDong();

                }
                showForm(quanlyHopDong);
            } else if (index == MAIN_MENU_QL.QLHOADON) {
                if (quanLyHoaDon == null) {
                    quanLyHoaDon = new QuanLyHoaDon();
                }
                showForm(quanLyHoaDon);

            } else if (index == MAIN_MENU_QL.QLNHANVIEN) {

                if (quanLyNhanVien == null) {
                    quanLyNhanVien = new QuanLyNhanVien();

                }
                showForm(quanLyNhanVien);
            } else if (index == MAIN_MENU_QL.QLKHO) {
                if (quanLyKho == null) {
                    quanLyKho = new QuanLyKho();

                }
                showForm(quanLyKho);

            } else if (index == MAIN_MENU_QL.QLSANH) {
                if (quanLySanh == null) {
                    quanLySanh = new QuanLySanh();

                }
                showForm(quanLySanh);

            } else if (index == MAIN_MENU_QL.THONGKE) {
                if (thongKe == null) {
                    thongKe = new ThongKe();
                }
                showForm(thongKe);
            } else if (index == MAIN_MENU_QL.GIOITHIEU) {

            } else if (index == MAIN_MENU_QL.HUONGDAN) {

            } else if (index == MAIN_MENU_QL.DOIMATKHAU) {

            } else if (index == MAIN_MENU_QL.DANGXUAT) {

            } else if (index == MAIN_MENU_QL.EXIT) {
                System.exit(1);

            }
        }
    }

    class MenuTiepTanListener implements EventMenuSelected {

        @Override
        public void selected(int index) {
            if (index == MAIN_MENU_QL.QLHOPDONG) {
                if (quanlyHopDong == null) {
                    quanlyHopDong = new QuanLyHopDong();

                }
                showForm(quanlyHopDong);
            } else if (index == MAIN_MENU_QL.QLHOADON) {
                if (quanLyHoaDon == null) {
                    quanLyHoaDon = new QuanLyHoaDon();
                }
                showForm(quanLyHoaDon);

            } else if (index == MAIN_MENU_QL.GIOITHIEU) {

            } else if (index == MAIN_MENU_QL.HUONGDAN) {

            } else if (index == MAIN_MENU_QL.DOIMATKHAU) {

            } else if (index == MAIN_MENU_QL.DANGXUAT) {

            } else if (index == MAIN_MENU_QL.EXIT) {
                System.exit(1);

            }
        }
    }

    class MenuQLKhoListener implements EventMenuSelected {

        @Override
        public void selected(int index) {
            if (index == MAIN_MENU_QLKHO.QLKHO) {
                if (quanLyKho == null) {
                    quanLyKho = new QuanLyKho();
                }
                showForm(quanLyKho);
            } else if (index == MAIN_MENU_QLKHO.GIOITHIEU) {

            } else if (index == MAIN_MENU_QLKHO.HUONGDAN) {

            } else if (index == MAIN_MENU_QLKHO.DOIMATKHAU) {

            } else if (index == MAIN_MENU_QLKHO.DANGXUAT) {

            } else if (index == MAIN_MENU_QLKHO.EXIT) {

            }
        }
    }

    class MenuAdminListener implements EventMenuSelected {

        @Override
        public void selected(int index) {
            if (index == MAIN_MENU_ADMIN.QLTAIKHOAN) {
                if (quanLyTaiKhoan == null) {
                    quanLyTaiKhoan = new QuanLyTaiKhoan();

                }
                showForm(quanLyTaiKhoan);

            } else if (index == MAIN_MENU_ADMIN.QLSANH) {
                if (quanLySanh == null) {
                    quanLySanh = new QuanLySanh();
                }
                showForm(quanLySanh);
            } else if (index == MAIN_MENU_ADMIN.EXIT) {

            }
        }
    }

    public void showForm(Component form) {

        mainPane.remove(mainPane.getComponentCount() - 1);
        mainPane.add(form, "w 100%, h 100%");
        repaint();
        revalidate();
    }
    
    public void showQuanLyHopDong(){
        quanlyHopDong.reload();
        showForm(quanlyHopDong);
        
    }
    
    
     public void showQuanLyHoaDon(){
        showForm(quanLyHoaDon);
    }

    public void logoff() {
        this.dispose();
        //new Login(this, true).setVisible(true);

        //new HappyWeddingApp().setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPane = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("HỆ THỐNG QUẢN LÝ ĐÀO TẠO");

        mainPane.setBackground(new java.awt.Color(245, 245, 245));
        mainPane.setMaximumSize(new java.awt.Dimension(1920, 1080));
        mainPane.setOpaque(true);
        mainPane.setPreferredSize(new java.awt.Dimension(1920, 950));
        mainPane.setRequestFocusEnabled(false);

        javax.swing.GroupLayout mainPaneLayout = new javax.swing.GroupLayout(mainPane);
        mainPane.setLayout(mainPaneLayout);
        mainPaneLayout.setHorizontalGroup(
            mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1920, Short.MAX_VALUE)
        );
        mainPaneLayout.setVerticalGroup(
            mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 950, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(HappyWeddingApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HappyWeddingApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HappyWeddingApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HappyWeddingApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                new HappyWeddingApp().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane mainPane;
    // End of variables declaration//GEN-END:variables
}
