/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happywedding.view.manage;

import com.happywedding.dao.ChiTietDichVuDAO;
import com.happywedding.helper.AppStatus;
import com.happywedding.model.ChiTietDichVu;
import com.happywedding.model.CoSoVatChat;
import com.happywedding.model.GoiDichVu;
import com.happywedding.model.HopDongDichVu;
import com.ui.swing.Combobox;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JTextField;

/**
 *
 * @author ADMIN
 */
public class TrangTriBanTiec extends javax.swing.JDialog {

    private String maHD;
    private ChiTietDichVuDAO chiTietDVDAO = new ChiTietDichVuDAO();
    private boolean isCreate;

    private List<CoSoVatChat> listTraiBan = new ArrayList<>();
    private List<CoSoVatChat> listAoGhe = new ArrayList<>();
    private List<CoSoVatChat> listHoa = new ArrayList<>();
    private List<GoiDichVu> listGoiDichVu = new ArrayList<>();

    HopDongDichVu ttBanTiec;
    


    static class VatTrangTri {

        static final String TRAIBAN = "TRAIBAN";
        static final String AOGHE = "AOGHE";
        static final String HOACHUDAO = "HOACHUDAO";
    }

    private final String maDV = "TTBANTIEC";
    private int soLuongBan;
    /**
     * Creates new form TrangTriCong
     */
    public TrangTriBanTiec(java.awt.Frame parent, boolean modal, String maHD, int soLuongBan) {
        super(parent, modal);

        this.maHD = maHD;
        this.soLuongBan = soLuongBan;
        this.isCreate = modal;
        initComponents();
        init();
    }

    public void init() {
        //ITODO load combobox lên tất cả các vật trang trí và gói dịch vụ
        ttBanTiec = chiTietDVDAO.selectDichVu(maHD, maDV);
        if (ttBanTiec != null) {
           if (ttBanTiec.getMaGoi() != null){
               for (GoiDichVu goi : listGoiDichVu) {
                if (goi.getMaGoi().equals(ttBanTiec.getMaDV())) {
                    cbbAoGhe.setSelectedItem(goi);
                }
            }
           }
           fillForm();
        }

        isView(isCreate);

    }

    public void insertHopDongDichVu() {

    }

    public void updateHopDongDichVu() {

    }

    public void insertChiTietDichVu() {

    }

    public void updateChiTietDichVu() {

    }
    
    public void tinhTien(){
        long tongCPPS = Integer.parseInt(txtCPPSAoGhe.getText()) + Integer.parseInt(txtCPPSHoaTT.getText()) + Integer.parseInt(txtCPPSTham.getText()) ;
        long chiPhi = Integer.parseInt(txtCPAoGhe.getText()) + Integer.parseInt(txtCPHoaTT.getText()) + Integer.parseInt(txtCPTham.getText()) ;
        txtTongCPPS.setText(tongCPPS + "");
        txtChiPhi.setText(chiPhi + "");
    }
    
    public void fillForm(){
            ChiTietDichVu ctAoGhe = chiTietDVDAO.selectChiTietDichVuCustom(maHD, maDV, VatTrangTri.AOGHE);
            ChiTietDichVu ctTraiBan = chiTietDVDAO.selectChiTietDichVuCustom(maHD, maDV, VatTrangTri.TRAIBAN);
            ChiTietDichVu ctHoaChuDao = chiTietDVDAO.selectChiTietDichVuCustom(maHD, maDV, VatTrangTri.HOACHUDAO);

            for (CoSoVatChat csvc : listAoGhe) {
                if (csvc.getMaCSVC().equals(ctAoGhe.getMaCSVC())) {
                    cbbAoGhe.setSelectedItem(csvc);
                }
            }
            
            for (CoSoVatChat csvc : listTraiBan) {
                if (csvc.getMaCSVC().equals(ctTraiBan.getMaCSVC())) {
                    cbbTham.setSelectedItem(csvc);
                }
            }
            
            for (CoSoVatChat csvc : listHoa) {
                if (csvc.getMaCSVC().equals(ctHoaChuDao.getMaCSVC())) {
                    cbbHoaTT.setSelectedItem(csvc);
                }
            }
            
            txtCPAoGhe.setText(ctAoGhe.getChiPhi() + "");
            txtCPTham.setText(ctTraiBan.getChiPhi() + "");
            txtCPHoaTT.setText(ctHoaChuDao.getChiPhi() + "");
         
            
            txtCPPSAoGhe.setText(ctAoGhe.getChiPhiPhatSinh()+ "");
            txtCPPSTham.setText(ctTraiBan.getChiPhiPhatSinh() + "");
            txtCPPSHoaTT.setText(ctHoaChuDao.getChiPhiPhatSinh() + "");
            
            
            txtGCAoGhe.setText(ctAoGhe.getGhiChu()+ "");
            txtGCTham.setText(ctTraiBan.getGhiChu() + "");
            txtGCHoaTT.setText(ctHoaChuDao.getGhiChu() + "");
            
            txtTongCPPS.setText(ttBanTiec.getChiPhiPhatSinh() + "");
            txtChiPhi.setText(ttBanTiec.getChiPhi() + "");
            txtTongChiPhi.setText(ttBanTiec.getChiPhiPhatSinh() + ttBanTiec.getChiPhi() + "");
          
            
    }
    
    
    public void isView(boolean isCreate) {
        for (Component cp : pnlTTBanTiec.getComponents()) {
            if (cp instanceof JTextField) {
                cp.setEnabled(isCreate);
            } else if (cp instanceof Combobox) {
                cp.setEnabled(isCreate);
            }

        }
        btnSave.setVisible(isCreate);
        btnReset.setVisible(isCreate);
        btnEdit.setVisible(isCreate);
        taGhiChu.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTTBanTiec = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lblMaNH18 = new javax.swing.JLabel();
        cbbGoiDV = new com.ui.swing.Combobox();
        btnEdit = new com.ui.swing.HoverButton();
        jLabel5 = new javax.swing.JLabel();
        lblMaNH19 = new javax.swing.JLabel();
        cbbAoGhe = new com.ui.swing.Combobox();
        lblMaNH22 = new javax.swing.JLabel();
        cbbHoaTT = new com.ui.swing.Combobox();
        lblMaNH24 = new javax.swing.JLabel();
        lblMaNH25 = new javax.swing.JLabel();
        cbbTham = new com.ui.swing.Combobox();
        txtGCTham = new javax.swing.JTextField();
        txtGCAoGhe = new javax.swing.JTextField();
        txtGCHoaTT = new javax.swing.JTextField();
        txtChiPhi = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCPPSTham = new javax.swing.JTextField();
        txtCPPSAoGhe = new javax.swing.JTextField();
        txtCPPSHoaTT = new javax.swing.JTextField();
        lblMaNH8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taGhiChu = new javax.swing.JTextArea();
        txtTongCPPS = new javax.swing.JTextField();
        txtTongChiPhi = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnSave = new com.ui.swing.HoverButton();
        btnReset = new com.ui.swing.HoverButton();
        jLabel10 = new javax.swing.JLabel();
        txtCPTham = new javax.swing.JTextField();
        txtCPAoGhe = new javax.swing.JTextField();
        txtCPHoaTT = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlTTBanTiec.setBackground(new java.awt.Color(255, 255, 255));
        pnlTTBanTiec.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Tổng chi phí phải trả");
        pnlTTBanTiec.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 480, -1, -1));

        lblMaNH18.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMaNH18.setText("Gói");
        pnlTTBanTiec.add(lblMaNH18, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 50, 30));

        cbbGoiDV.setToolTipText("");
        cbbGoiDV.setLabeText("");
        cbbGoiDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbGoiDVActionPerformed(evt);
            }
        });
        pnlTTBanTiec.add(cbbGoiDV, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, 320, 35));

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
        pnlTTBanTiec.add(btnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 100, -1, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Trang trí bàn tiệc");
        pnlTTBanTiec.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        lblMaNH19.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMaNH19.setText("Áo ghế");
        pnlTTBanTiec.add(lblMaNH19, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, 90, 30));

        cbbAoGhe.setToolTipText("");
        cbbAoGhe.setLabeText("");
        cbbAoGhe.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbAoGheItemStateChanged(evt);
            }
        });
        cbbAoGhe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbAoGheActionPerformed(evt);
            }
        });
        pnlTTBanTiec.add(cbbAoGhe, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 290, 330, 35));

        lblMaNH22.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMaNH22.setText("Hoa trang trí");
        pnlTTBanTiec.add(lblMaNH22, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 360, 90, 30));

        cbbHoaTT.setToolTipText("");
        cbbHoaTT.setLabeText("");
        cbbHoaTT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbHoaTTItemStateChanged(evt);
            }
        });
        cbbHoaTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbHoaTTActionPerformed(evt);
            }
        });
        pnlTTBanTiec.add(cbbHoaTT, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 360, 330, 35));

        lblMaNH24.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMaNH24.setText("Chi phí");
        pnlTTBanTiec.add(lblMaNH24, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 530, 90, 30));

        lblMaNH25.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMaNH25.setText("Thảm trãi bàn");
        pnlTTBanTiec.add(lblMaNH25, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 100, 30));

        cbbTham.setToolTipText("");
        cbbTham.setLabeText("");
        cbbTham.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbThamItemStateChanged(evt);
            }
        });
        cbbTham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbThamActionPerformed(evt);
            }
        });
        pnlTTBanTiec.add(cbbTham, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 220, 330, 35));

        txtGCTham.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtGCTham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGCThamActionPerformed(evt);
            }
        });
        pnlTTBanTiec.add(txtGCTham, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 220, 360, 35));

        txtGCAoGhe.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        pnlTTBanTiec.add(txtGCAoGhe, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 280, 360, 35));

        txtGCHoaTT.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtGCHoaTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGCHoaTTActionPerformed(evt);
            }
        });
        pnlTTBanTiec.add(txtGCHoaTT, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 350, 360, 35));

        txtChiPhi.setEditable(false);
        txtChiPhi.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtChiPhi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtChiPhiActionPerformed(evt);
            }
        });
        pnlTTBanTiec.add(txtChiPhi, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 530, 330, 35));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Vật trang trí");
        pnlTTBanTiec.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Chi phí phát sinh");
        pnlTTBanTiec.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 160, -1, -1));

        txtCPPSTham.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtCPPSTham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCPPSThamActionPerformed(evt);
            }
        });
        txtCPPSTham.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCPPSThamKeyReleased(evt);
            }
        });
        pnlTTBanTiec.add(txtCPPSTham, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 220, 210, 35));

        txtCPPSAoGhe.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtCPPSAoGhe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCPPSAoGheKeyReleased(evt);
            }
        });
        pnlTTBanTiec.add(txtCPPSAoGhe, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 280, 210, 35));

        txtCPPSHoaTT.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtCPPSHoaTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCPPSHoaTTActionPerformed(evt);
            }
        });
        txtCPPSHoaTT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCPPSHoaTTKeyReleased(evt);
            }
        });
        pnlTTBanTiec.add(txtCPPSHoaTT, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 350, 210, 35));

        lblMaNH8.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMaNH8.setText("Ghi chú");
        pnlTTBanTiec.add(lblMaNH8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 440, 60, -1));

        taGhiChu.setColumns(20);
        taGhiChu.setRows(5);
        jScrollPane1.setViewportView(taGhiChu);

        pnlTTBanTiec.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 430, 330, 80));

        txtTongCPPS.setEditable(false);
        txtTongCPPS.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTongCPPS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongCPPSActionPerformed(evt);
            }
        });
        pnlTTBanTiec.add(txtTongCPPS, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 530, 360, 35));

        txtTongChiPhi.setEditable(false);
        txtTongChiPhi.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTongChiPhi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongChiPhiActionPerformed(evt);
            }
        });
        pnlTTBanTiec.add(txtTongChiPhi, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 530, 360, 35));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Ghi chú");
        pnlTTBanTiec.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 170, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("Tổng chi phát phát sinh");
        pnlTTBanTiec.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 490, -1, -1));

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
        pnlTTBanTiec.add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 610, -1, 30));

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
        pnlTTBanTiec.add(btnReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(1280, 610, -1, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText("Chi phí");
        pnlTTBanTiec.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 160, -1, -1));

        txtCPTham.setEditable(false);
        txtCPTham.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtCPTham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCPThamActionPerformed(evt);
            }
        });
        pnlTTBanTiec.add(txtCPTham, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 220, 210, 35));

        txtCPAoGhe.setEditable(false);
        txtCPAoGhe.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        pnlTTBanTiec.add(txtCPAoGhe, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 280, 210, 35));

        txtCPHoaTT.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtCPHoaTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCPHoaTTActionPerformed(evt);
            }
        });
        pnlTTBanTiec.add(txtCPHoaTT, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 350, 210, 35));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTTBanTiec, javax.swing.GroupLayout.PREFERRED_SIZE, 1446, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTTBanTiec, javax.swing.GroupLayout.PREFERRED_SIZE, 671, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setSize(new java.awt.Dimension(1464, 718));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cbbGoiDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbGoiDVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbGoiDVActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
       
    }//GEN-LAST:event_btnEditActionPerformed

    private void cbbAoGheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbAoGheActionPerformed
     
    }//GEN-LAST:event_cbbAoGheActionPerformed

    private void cbbHoaTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbHoaTTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbHoaTTActionPerformed

    private void cbbThamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbThamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbThamActionPerformed

    private void txtGCThamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGCThamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGCThamActionPerformed

    private void txtGCHoaTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGCHoaTTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGCHoaTTActionPerformed

    private void txtChiPhiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtChiPhiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtChiPhiActionPerformed

    private void txtCPPSThamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCPPSThamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPPSThamActionPerformed

    private void txtCPPSHoaTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCPPSHoaTTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPPSHoaTTActionPerformed

    private void txtTongCPPSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTongCPPSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTongCPPSActionPerformed

    private void txtTongChiPhiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTongChiPhiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTongChiPhiActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
//        if (!chiTietDVDAO.checkHopDongDichVu("HD003", "TTSANKHAU")) {
//            System.out.println("INSERT");
//            insertHopDongDichVu();
//            insertChiTietDichVu();
//        } else {
//            System.out.println("UPDATE");
//            updateHopDongDichVu();
//            updateChiTietDichVu();
//        }
        //  AppStatus.lapHopDong.reloadHopDong();
        this.dispose();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnResetActionPerformed

    private void txtCPThamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCPThamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPThamActionPerformed

    private void txtCPHoaTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCPHoaTTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPHoaTTActionPerformed

    private void cbbThamItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbThamItemStateChanged
        txtCPTham.setText(listTraiBan.get(cbbTham.getSelectedIndex()).getGiaThue() * soLuongBan  + "");
        tinhTien();
       
    }//GEN-LAST:event_cbbThamItemStateChanged

    private void cbbAoGheItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbAoGheItemStateChanged
          txtCPPSAoGhe.setText(listAoGhe.get(cbbAoGhe.getSelectedIndex()).getGiaThue() * soLuongBan * 10  + "");
        tinhTien();
    }//GEN-LAST:event_cbbAoGheItemStateChanged

    private void cbbHoaTTItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbHoaTTItemStateChanged
        txtCPHoaTT.setText("0");
        tinhTien();
    }//GEN-LAST:event_cbbHoaTTItemStateChanged

    private void txtCPPSThamKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCPPSThamKeyReleased
       tinhTien();
    }//GEN-LAST:event_txtCPPSThamKeyReleased

    private void txtCPPSAoGheKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCPPSAoGheKeyReleased
        tinhTien();
    }//GEN-LAST:event_txtCPPSAoGheKeyReleased

    private void txtCPPSHoaTTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCPPSHoaTTKeyReleased
        tinhTien();
    }//GEN-LAST:event_txtCPPSHoaTTKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ui.swing.HoverButton btnEdit;
    private com.ui.swing.HoverButton btnReset;
    private com.ui.swing.HoverButton btnSave;
    private com.ui.swing.Combobox cbbAoGhe;
    private com.ui.swing.Combobox cbbGoiDV;
    private com.ui.swing.Combobox cbbHoaTT;
    private com.ui.swing.Combobox cbbTham;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblMaNH18;
    private javax.swing.JLabel lblMaNH19;
    private javax.swing.JLabel lblMaNH22;
    private javax.swing.JLabel lblMaNH24;
    private javax.swing.JLabel lblMaNH25;
    private javax.swing.JLabel lblMaNH8;
    private javax.swing.JPanel pnlTTBanTiec;
    private javax.swing.JTextArea taGhiChu;
    private javax.swing.JTextField txtCPAoGhe;
    private javax.swing.JTextField txtCPHoaTT;
    private javax.swing.JTextField txtCPPSAoGhe;
    private javax.swing.JTextField txtCPPSHoaTT;
    private javax.swing.JTextField txtCPPSTham;
    private javax.swing.JTextField txtCPTham;
    private javax.swing.JTextField txtChiPhi;
    private javax.swing.JTextField txtGCAoGhe;
    private javax.swing.JTextField txtGCHoaTT;
    private javax.swing.JTextField txtGCTham;
    private javax.swing.JTextField txtTongCPPS;
    private javax.swing.JTextField txtTongChiPhi;
    // End of variables declaration//GEN-END:variables
}
