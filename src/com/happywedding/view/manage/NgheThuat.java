/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happywedding.view.manage;

import com.happywedding.dao.ChiTietDichVuDAO;
import com.happywedding.dao.CoSoVatChatDAO;
import com.happywedding.dao.GoiDichVuDAO;
import com.happywedding.helper.AppStatus;
import com.happywedding.helper.DialogHelper;
import com.happywedding.helper.ShareHelper;
import com.happywedding.model.ChiTietDichVu;
import com.happywedding.model.CoSoVatChat;
import com.happywedding.model.GoiDichVu;
import com.happywedding.model.HopDongDichVu;
import com.ui.swing.Combobox;
import com.ui.swing.SlideshowImage;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 *
 * @author ADMIN
 */
public class NgheThuat extends javax.swing.JDialog {

    private String maHD;
    private ChiTietDichVuDAO chiTietDVDAO = new ChiTietDichVuDAO();
    private CoSoVatChatDAO csvcDAO = new CoSoVatChatDAO();
    private GoiDichVuDAO goiDichVuDAO = new GoiDichVuDAO();

    private boolean isCreate;

    private List<CoSoVatChat> listAmNhac = new ArrayList<>();
    private List<CoSoVatChat> listVuDao = new ArrayList<>();
    private List<GoiDichVu> listGoiDichVu = new ArrayList<>();

    HopDongDichVu ttCong;

    private final String maDV = "NGHETHUAT";
    private boolean isLoad = false;

    static class VatTrangTri {

        static final String VUDAO = "VUDAO";
        static final String LIENKHUC = "LIENKHUC";

    }

    /**
     * Creates new form NgheThuat
     */
    public NgheThuat(java.awt.Frame parent, boolean modal, String maHD) {
        super(parent, modal);
        this.maHD = maHD;
        this.isCreate = modal;
        initComponents();
        init();
    }

    public void init() {
        //ITODO load combobox lên tất cả các vật trang trí và gói dịch vụ

        loadGoiDichVu();
        loadCoSoVatChat();
        setCheckNumber();

        ttCong = chiTietDVDAO.selectDichVu(maHD, maDV);

        isView(isCreate);
         isLoad = true;
        if (ttCong != null) {
            if (ttCong.getMaGoi() != null) {
                for (GoiDichVu goi : listGoiDichVu) {
                    if (goi.getMaGoi().equals(ttCong.getMaGoi())) {
                        cbbGoiDV.setSelectedItem(goi);
                        isTuyChinhGoiDichVu(false);
                        lblViewSlideShow.setVisible(true);
                        fillForm();
                    }

//                isTuyChinhGoiDichVu(true);
                }

            } else {
                cbbGoiDV.setSelectedIndex(-1);
                lblViewSlideShow.setVisible(false);
                fillForm();
            }

        }
       

    }

    // các hàm lấy dữ liệu từ form 
    public List<ChiTietDichVu> getChiTietDichVu() {

        List<ChiTietDichVu> list = new ArrayList<>();

        ChiTietDichVu ctAmNhac = new ChiTietDichVu();
        ChiTietDichVu ctVuDao = new ChiTietDichVu();

        ctAmNhac.setMaHD(maHD);
        ctAmNhac.setMaDV(maDV);
        ctAmNhac.setMaCSVC(((CoSoVatChat) cbbAmNhac.getSelectedItem()).getMaCSVC());
        ctAmNhac.setChiPhi(0);
        ctAmNhac.setChiPhiPhatSinh(ShareHelper.toMoney(txtCPPSAmNhac.getText()));
        ctAmNhac.setGhiChu(txtGCAmNhac.getText());

        ctVuDao.setMaHD(maHD);
        ctVuDao.setMaDV(maDV);
        ctVuDao.setMaCSVC(((CoSoVatChat) cbbVuDao.getSelectedItem()).getMaCSVC());
        ctVuDao.setChiPhi(0);
        ctVuDao.setChiPhiPhatSinh(ShareHelper.toMoney(txtCPPSVuDao.getText()));
        ctVuDao.setGhiChu(txtGCVuDao.getText());

        list.add(ctAmNhac);
        list.add(ctVuDao);

        return list;

    }

    public boolean insertHopDongDichVu() {
        HopDongDichVu hddv = new HopDongDichVu();
        hddv.setMaHD(maHD);
        hddv.setMaDV(maDV);
        if (cbbGoiDV.getSelectedIndex() != -1) {
            hddv.setMaGoi(((GoiDichVu) cbbGoiDV.getSelectedItem()).getMaGoi());
        }
        hddv.setChiPhi(ShareHelper.toMoney(txtChiPhi.getText()));
        hddv.setGhiChu(taGhiChu.getText());
        return chiTietDVDAO.insertDichVu(hddv);

    }

    public void updateHopDongDichVu() {
        HopDongDichVu hddv = new HopDongDichVu();
        hddv.setMaHD(maHD);
        hddv.setMaDV(maDV);
        if (cbbGoiDV.getSelectedIndex() != -1) {
            hddv.setMaGoi(((GoiDichVu) cbbGoiDV.getSelectedItem()).getMaGoi());
        }
        hddv.setChiPhi(ShareHelper.toMoney(txtChiPhi.getText()));
        hddv.setGhiChu(taGhiChu.getText());

        chiTietDVDAO.updateDichVu(hddv);
    }

    public void insertChiTietDichVu() {
        List<ChiTietDichVu> list = getChiTietDichVu();
        try {
            for (ChiTietDichVu dv : list) {
                chiTietDVDAO.insertChiTietDichVy(dv);
            }

        } catch (Exception e) {
            DialogHelper.alertError(this, "Lưu không thành công");
        }
    }

    public void updateChiTietDichVu() {
        chiTietDVDAO.deleteAllChiTietDichVu(maHD, maDV);
        insertChiTietDichVu();
    }

    // các hàm lấy dữ liệu từ cơ sở dữ liệu
    public void loadGoiDichVu() {
        listGoiDichVu = new GoiDichVuDAO().selectGoiDichVu(maDV);
        DefaultComboBoxModel cbbModel = (DefaultComboBoxModel) cbbGoiDV.getModel();
        cbbModel.removeAllElements();
        for (GoiDichVu s : listGoiDichVu) {
            cbbModel.addElement(s);
        }
        cbbGoiDV.setSelectedIndex(-1);
    }

    public void loadCoSoVatChat() {

        listAmNhac = csvcDAO.selectByMaDMC(NgheThuat.VatTrangTri.LIENKHUC);
        listVuDao = csvcDAO.selectByMaDMC(NgheThuat.VatTrangTri.VUDAO);

        DefaultComboBoxModel cbbAmNhacModel = (DefaultComboBoxModel) cbbAmNhac.getModel();
        cbbAmNhacModel.removeAllElements();

        for (CoSoVatChat csvc : listAmNhac) {
            cbbAmNhacModel.addElement(csvc);
        }
        cbbAmNhac.setSelectedIndex(-1);

        DefaultComboBoxModel cbbVuDaoModel = (DefaultComboBoxModel) cbbVuDao.getModel();
        cbbVuDaoModel.removeAllElements();

        for (CoSoVatChat csvc : listVuDao) {
            cbbVuDaoModel.addElement(csvc);
        }
        cbbVuDao.setSelectedIndex(-1);

    }

    // các hàm xử lý form
    public boolean tinhTien() {
        try {
            long tongCPPS = ShareHelper.toMoney(txtCPPSAmNhac.getText()) + ShareHelper.toMoney(txtCPPSVuDao.getText());
            long chiPhi = ShareHelper.toMoney(txtCPAmNhac.getText()) + ShareHelper.toMoney(txtCPVuDao.getText());
            txtTongCPPS.setText(ShareHelper.toMoney(tongCPPS));
            txtChiPhi.setText(ShareHelper.toMoney(chiPhi));
            txtTongChiPhi.setText(ShareHelper.toMoney(tongCPPS + chiPhi));

        } catch (Exception e) {
            DialogHelper.alertError(this, "Vui lòng nhập đúng định dạng số");
            return false;
        }
        return true;

    }

    public void fillChiTietDichVu(ChiTietDichVu ct, List<CoSoVatChat> list,
            Combobox cbb, JTextField txtChiPhi, JTextField txtPhatSinh, JTextField txtGhiChu, int soLuongBan, int heSoNhan, boolean isCustom) {

        for (CoSoVatChat csvc : list) {
            if (csvc.getMaCSVC().equals(ct.getMaCSVC())) {
                cbb.setSelectedItem(csvc);
                cbb.setToolTipText(ShareHelper.toMoney(csvc.getGiaThue()));
            }
        }

        txtPhatSinh.setText(ShareHelper.toMoney(ct.getChiPhiPhatSinh()));
        if (isCustom) {
            txtChiPhi.setText(ShareHelper.toMoney(ct.getChiPhi()));
            txtChiPhi.setToolTipText(ShareHelper.toMoney(ct.getChiPhi()));
        } else {
            txtChiPhi.setText(ShareHelper.toMoney(ct.getChiPhi() * soLuongBan * heSoNhan));
            txtChiPhi.setToolTipText(ShareHelper.toMoney(ct.getChiPhi()));
        }
        txtGhiChu.setText(ct.getGhiChu());

    }

    public void fillChiTietGoiDichVu(ChiTietDichVu ct, List<CoSoVatChat> list,
            Combobox cbb, JTextField txtChiPhi, JTextField txtGhiChu, int soLuongBan, int heSoNhan, boolean isCustom) {

        for (CoSoVatChat csvc : list) {
            if (csvc.getMaCSVC().equals(ct.getMaCSVC())) {
                cbb.setSelectedItem(csvc);
                cbb.setToolTipText(ShareHelper.toMoney(csvc.getGiaThue()));
            }
        }
        if (isCustom) {
            txtChiPhi.setText(ShareHelper.toMoney(ct.getChiPhi()));
            txtChiPhi.setToolTipText(ShareHelper.toMoney(ct.getChiPhi()));
        } else {
            txtChiPhi.setText(ShareHelper.toMoney(ct.getChiPhi() * soLuongBan * heSoNhan));
            txtChiPhi.setToolTipText(ShareHelper.toMoney(ct.getChiPhi()));
        }
        txtGhiChu.setText(ct.getGhiChu());

    }

    public void changeCombbox(Combobox cbb, List<CoSoVatChat> list, JTextField txtChiPhi, JTextField txtPhatSinh, JTextField txtGhiChu, int soLuongBan, int heSoNhan, boolean isCustom) {
        if (isLoad && cbb.getSelectedIndex() != -1) {
            long giaThue = list.get(cbb.getSelectedIndex()).getGiaThue();

            cbb.setToolTipText(ShareHelper.toMoney(giaThue));
            if (isCustom) {
                txtChiPhi.setText("0");
                txtChiPhi.setToolTipText(ShareHelper.toMoney(giaThue));
            } else {
                txtChiPhi.setText(ShareHelper.toMoney(giaThue * soLuongBan * heSoNhan));
                txtChiPhi.setToolTipText(giaThue + "");
            }
//            txtGhiChu.setText("");
//            txtPhatSinh.setText("0");
            tinhTien();
        }
    }

    public void fillForm() {

        ChiTietDichVu ctAmNhac = chiTietDVDAO.selectChiTietDichVuNoCustom(maHD, maDV, NgheThuat.VatTrangTri.LIENKHUC);
        ChiTietDichVu ctVuDao = chiTietDVDAO.selectChiTietDichVuNoCustom(maHD, maDV, NgheThuat.VatTrangTri.VUDAO);

        if (ctAmNhac != null) {

            fillChiTietDichVu(ctAmNhac, listAmNhac, cbbAmNhac, txtCPAmNhac, txtCPPSAmNhac, txtGCAmNhac, 1, 1, false);
        }

        if (ctVuDao != null) {

            fillChiTietDichVu(ctVuDao, listVuDao, cbbVuDao, txtCPVuDao, txtCPPSVuDao, txtGCVuDao, 1, 1, false);
        }

        txtTongCPPS.setText(ShareHelper.toMoney(ttCong.getChiPhiPhatSinh()));
        txtChiPhi.setText(ShareHelper.toMoney(ttCong.getChiPhi()));
        txtTongChiPhi.setText(ShareHelper.toMoney(ttCong.getChiPhiPhatSinh() + ttCong.getChiPhi()));
        taGhiChu.setText(ttCong.getGhiChu());

    }

    public void fillFormByGoiDichVu(GoiDichVu goi) {

        ChiTietDichVu ctAmNhac = goiDichVuDAO.selectChiTietGoiDichVuNoCustom(goi.getMaGoi(), NgheThuat.VatTrangTri.LIENKHUC);
        ChiTietDichVu ctVuDao = goiDichVuDAO.selectChiTietGoiDichVuNoCustom(goi.getMaGoi(), NgheThuat.VatTrangTri.VUDAO);

        if (ctAmNhac != null) {

            fillChiTietDichVu(ctAmNhac, listAmNhac, cbbAmNhac, txtCPAmNhac, txtCPPSAmNhac, txtGCAmNhac, 1, 1, false);
        }

        if (ctVuDao != null) {

            fillChiTietDichVu(ctVuDao, listVuDao, cbbVuDao, txtCPVuDao, txtCPPSVuDao, txtGCVuDao, 1, 1, false);
        }
    }

    public void isView(boolean isCreate) {
        for (Component cp : pnlNgheThuat.getComponents()) {
            if (cp instanceof JTextField) {
                cp.setEnabled(isCreate);
            } else if (cp instanceof Combobox) {
                cp.setEnabled(false);
            }

        }
        cbbGoiDV.setEnabled(true);
        btnSave.setVisible(isCreate);
        btnReset.setVisible(isCreate);
       // btnEdit.setVisible(isCreate);
        taGhiChu.setEnabled(isCreate);
    }

    public void isTuyChinhGoiDichVu(boolean is) {
        if (isCreate) {

            cbbAmNhac.setEnabled(is);
            cbbVuDao.setEnabled(is);
        }

    }

    public boolean checkValid(JTextField txt) {
        if (txt.getText().equals("")) {
            txt.setText("0");
        } else if (ShareHelper.toMoney(txt.getText()) < 1000 && !txt.getText().equals("0")) {
            DialogHelper.alertError(this, "Chi phí thấp nhất là 1.000 VNĐ");
            txt.requestFocus();
            return false;
        }
        txt.setText(ShareHelper.toMoney(ShareHelper.toMoney(txt.getText())));
        tinhTien();
        return true;
    }

    class CheckNumber extends KeyAdapter {

        public void keyTyped(java.awt.event.KeyEvent evt) {
            char testChar = evt.getKeyChar();
            if (!((Character.isDigit(testChar)))) {
                if (testChar == '\n') {
                    lblMaNH18.requestFocus();
                }
                if (testChar != '.') {
                    evt.consume();
                }
            }
        }
    }

    public void setCheckNumber() {

        txtCPPSAmNhac.addKeyListener(new CheckNumber());
        txtCPPSVuDao.addKeyListener(new CheckNumber());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlNgheThuat = new javax.swing.JPanel();
        lblMaNH18 = new javax.swing.JLabel();
        cbbGoiDV = new com.ui.swing.Combobox();
        jLabel5 = new javax.swing.JLabel();
        lblMaNH19 = new javax.swing.JLabel();
        cbbVuDao = new com.ui.swing.Combobox();
        lblMaNH25 = new javax.swing.JLabel();
        cbbAmNhac = new com.ui.swing.Combobox();
        txtGCAmNhac = new javax.swing.JTextField();
        txtGCVuDao = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCPPSAmNhac = new javax.swing.JTextField();
        txtCPPSVuDao = new javax.swing.JTextField();
        lblMaNH8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taGhiChu = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtCPAmNhac = new javax.swing.JTextField();
        txtCPVuDao = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        lblMaNH24 = new javax.swing.JLabel();
        txtChiPhi = new javax.swing.JTextField();
        txtTongCPPS = new javax.swing.JTextField();
        txtTongChiPhi = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblViewSlideShow = new javax.swing.JLabel();
        btnEdit = new com.ui.swing.HoverButton();
        btnSave = new com.ui.swing.HoverButton();
        btnReset = new com.ui.swing.HoverButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlNgheThuat.setBackground(new java.awt.Color(255, 255, 255));
        pnlNgheThuat.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblMaNH18.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMaNH18.setText("Gói");
        pnlNgheThuat.add(lblMaNH18, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 50, 30));

        cbbGoiDV.setToolTipText("");
        cbbGoiDV.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cbbGoiDV.setLabeText("");
        cbbGoiDV.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbGoiDVItemStateChanged(evt);
            }
        });
        cbbGoiDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbGoiDVActionPerformed(evt);
            }
        });
        pnlNgheThuat.add(cbbGoiDV, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, 320, 45));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Nghệ thuật");
        pnlNgheThuat.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        lblMaNH19.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMaNH19.setText("Vũ đạo");
        pnlNgheThuat.add(lblMaNH19, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, 90, 30));

        cbbVuDao.setToolTipText("");
        cbbVuDao.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cbbVuDao.setLabeText("");
        cbbVuDao.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbVuDaoItemStateChanged(evt);
            }
        });
        cbbVuDao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbVuDaoActionPerformed(evt);
            }
        });
        pnlNgheThuat.add(cbbVuDao, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 270, 330, 45));

        lblMaNH25.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMaNH25.setText("Âm nhạc");
        pnlNgheThuat.add(lblMaNH25, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 100, 30));

        cbbAmNhac.setToolTipText("");
        cbbAmNhac.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cbbAmNhac.setLabeText("");
        cbbAmNhac.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbAmNhacItemStateChanged(evt);
            }
        });
        cbbAmNhac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbAmNhacActionPerformed(evt);
            }
        });
        pnlNgheThuat.add(cbbAmNhac, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 200, 330, 45));

        txtGCAmNhac.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtGCAmNhac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGCAmNhacActionPerformed(evt);
            }
        });
        pnlNgheThuat.add(txtGCAmNhac, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 210, 360, 35));

        txtGCVuDao.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        pnlNgheThuat.add(txtGCVuDao, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 270, 360, 35));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Tiết mục");
        pnlNgheThuat.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Chi phí phát sinh");
        pnlNgheThuat.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 160, -1, -1));

        txtCPPSAmNhac.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtCPPSAmNhac.setText("0");
        txtCPPSAmNhac.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        txtCPPSAmNhac.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCPPSAmNhacFocusLost(evt);
            }
        });
        txtCPPSAmNhac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCPPSAmNhacActionPerformed(evt);
            }
        });
        pnlNgheThuat.add(txtCPPSAmNhac, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 210, 170, 35));

        txtCPPSVuDao.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtCPPSVuDao.setText("0");
        txtCPPSVuDao.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        txtCPPSVuDao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCPPSVuDaoFocusLost(evt);
            }
        });
        pnlNgheThuat.add(txtCPPSVuDao, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 270, 170, 35));

        lblMaNH8.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMaNH8.setText("Ghi chú");
        pnlNgheThuat.add(lblMaNH8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 380, 60, -1));

        taGhiChu.setColumns(20);
        taGhiChu.setRows(5);
        jScrollPane1.setViewportView(taGhiChu);

        pnlNgheThuat.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 370, 330, 80));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Ghi chú");
        pnlNgheThuat.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 160, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText("Chi phí");
        pnlNgheThuat.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 160, -1, -1));

        txtCPAmNhac.setEditable(false);
        txtCPAmNhac.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtCPAmNhac.setText("0");
        txtCPAmNhac.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        txtCPAmNhac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCPAmNhacActionPerformed(evt);
            }
        });
        pnlNgheThuat.add(txtCPAmNhac, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 210, 170, 35));

        txtCPVuDao.setEditable(false);
        txtCPVuDao.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtCPVuDao.setText("0");
        txtCPVuDao.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        pnlNgheThuat.add(txtCPVuDao, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 270, 170, 35));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Tổng chi phí phải trả");
        pnlNgheThuat.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 360, -1, -1));

        lblMaNH24.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblMaNH24.setText("Tổng chi phí");
        pnlNgheThuat.add(lblMaNH24, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 360, 160, 30));

        txtChiPhi.setEditable(false);
        txtChiPhi.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtChiPhi.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        txtChiPhi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtChiPhiActionPerformed(evt);
            }
        });
        pnlNgheThuat.add(txtChiPhi, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 400, 190, 35));

        txtTongCPPS.setEditable(false);
        txtTongCPPS.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTongCPPS.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        txtTongCPPS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongCPPSActionPerformed(evt);
            }
        });
        pnlNgheThuat.add(txtTongCPPS, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 400, 200, 35));

        txtTongChiPhi.setEditable(false);
        txtTongChiPhi.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTongChiPhi.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        txtTongChiPhi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongChiPhiActionPerformed(evt);
            }
        });
        pnlNgheThuat.add(txtTongChiPhi, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 400, 270, 35));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("Tổng chi phát phát sinh");
        pnlNgheThuat.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 360, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel1.setText("VNĐ");
        pnlNgheThuat.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1380, 400, -1, 30));

        lblViewSlideShow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/happywedding/assets/eye.png"))); // NOI18N
        lblViewSlideShow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblViewSlideShowMouseClicked(evt);
            }
        });
        pnlNgheThuat.add(lblViewSlideShow, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 40, 40, 20));

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
        pnlNgheThuat.add(btnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 80, -1, 30));

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
        pnlNgheThuat.add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 480, -1, 30));

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
        pnlNgheThuat.add(btnReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 480, -1, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlNgheThuat, javax.swing.GroupLayout.PREFERRED_SIZE, 1446, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlNgheThuat, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cbbGoiDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbGoiDVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbGoiDVActionPerformed

    private void cbbVuDaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbVuDaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbVuDaoActionPerformed

    private void cbbAmNhacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbAmNhacActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbAmNhacActionPerformed

    private void txtGCAmNhacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGCAmNhacActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGCAmNhacActionPerformed

    private void txtCPPSAmNhacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCPPSAmNhacActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPPSAmNhacActionPerformed

    private void txtCPAmNhacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCPAmNhacActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPAmNhacActionPerformed

    private void txtChiPhiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtChiPhiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtChiPhiActionPerformed

    private void txtTongCPPSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTongCPPSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTongCPPSActionPerformed

    private void txtTongChiPhiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTongChiPhiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTongChiPhiActionPerformed

    private void lblViewSlideShowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblViewSlideShowMouseClicked
        new SlideshowImage(new JFrame(), true, (GoiDichVu) cbbGoiDV.getSelectedItem(), maDV).setVisible(true);
    }//GEN-LAST:event_lblViewSlideShowMouseClicked

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        isTuyChinhGoiDichVu(true);
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed

        if (cbbGoiDV.getSelectedIndex() == -1) {
            DialogHelper.alertError(this, "Vui lòng chọn gói");
            return;
        }

        if (cbbAmNhac.getSelectedIndex() == -1 || cbbVuDao.getSelectedIndex() == -1) {
            DialogHelper.alertError(this, "Vui lòng chọn đầy đủ thông tin");
            return;
        }

        if (!checkValid(txtCPPSAmNhac) || !checkValid(txtCPPSVuDao)) {
            return;
        }

//        if (txtCPPSAmNhac.getText().equals("0") || txtCPPSVuDao.getText().equals("0")) {
//            boolean rs = DialogHelper.confirm(this, "Bạn chưa xác định giá trị của hoa trang trí. Tiếp tục lưu");
//            if (!rs) {
//                return;
//            }
//        }
        if (!chiTietDVDAO.checkHopDongDichVu(maHD, maDV)) {

            if (insertHopDongDichVu()) {
                insertChiTietDichVu();
            } else {
                DialogHelper.alertError(this, "Lưu không thành công");
            }

        } else {

            updateHopDongDichVu();
            updateChiTietDichVu();
        }
        AppStatus.lapHopDong.checkedDichVu(maDV, true);
        AppStatus.lapHopDong.reloadHopDong();
        this.dispose();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed

        cbbAmNhac.setSelectedIndex(-1);
        cbbVuDao.setSelectedIndex(-1);

        cbbGoiDV.setSelectedIndex(-1);

        txtCPAmNhac.setText("0");
        txtCPVuDao.setText("0");

        txtCPPSAmNhac.setText("0");
        txtCPPSVuDao.setText("0");

        txtGCAmNhac.setText("");
        txtGCVuDao.setText("");

        tinhTien();
    }//GEN-LAST:event_btnResetActionPerformed

    private void txtCPPSAmNhacFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCPPSAmNhacFocusLost
        checkValid(txtCPPSAmNhac);
    }//GEN-LAST:event_txtCPPSAmNhacFocusLost

    private void txtCPPSVuDaoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCPPSVuDaoFocusLost
        checkValid(txtCPPSVuDao);
    }//GEN-LAST:event_txtCPPSVuDaoFocusLost

    private void cbbAmNhacItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbAmNhacItemStateChanged
        changeCombbox(cbbAmNhac, listAmNhac, txtCPAmNhac, txtCPPSAmNhac, txtGCAmNhac, 1, 1, false);
    }//GEN-LAST:event_cbbAmNhacItemStateChanged

    private void cbbVuDaoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbVuDaoItemStateChanged
        changeCombbox(cbbVuDao, listVuDao, txtCPVuDao, txtCPPSVuDao, txtGCVuDao, 1, 1, false);
    }//GEN-LAST:event_cbbVuDaoItemStateChanged

    private void cbbGoiDVItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbGoiDVItemStateChanged
        if (isLoad && cbbGoiDV.getSelectedIndex() != -1 && isCreate) {
            fillFormByGoiDichVu((GoiDichVu) cbbGoiDV.getSelectedItem());
            isTuyChinhGoiDichVu(false);
            lblViewSlideShow.setVisible(true);
            btnEdit.setVisible(true);
        } else {
            btnEdit.setVisible(false);
            lblViewSlideShow.setVisible(false);
        }
    }//GEN-LAST:event_cbbGoiDVItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ui.swing.HoverButton btnEdit;
    private com.ui.swing.HoverButton btnReset;
    private com.ui.swing.HoverButton btnSave;
    private com.ui.swing.Combobox cbbAmNhac;
    private com.ui.swing.Combobox cbbGoiDV;
    private com.ui.swing.Combobox cbbVuDao;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JLabel lblMaNH24;
    private javax.swing.JLabel lblMaNH25;
    private javax.swing.JLabel lblMaNH8;
    private javax.swing.JLabel lblViewSlideShow;
    private javax.swing.JPanel pnlNgheThuat;
    private javax.swing.JTextArea taGhiChu;
    private javax.swing.JTextField txtCPAmNhac;
    private javax.swing.JTextField txtCPPSAmNhac;
    private javax.swing.JTextField txtCPPSVuDao;
    private javax.swing.JTextField txtCPVuDao;
    private javax.swing.JTextField txtChiPhi;
    private javax.swing.JTextField txtGCAmNhac;
    private javax.swing.JTextField txtGCVuDao;
    private javax.swing.JTextField txtTongCPPS;
    private javax.swing.JTextField txtTongChiPhi;
    // End of variables declaration//GEN-END:variables
}
