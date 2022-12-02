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
import com.happywedding.model.Sanh;
import com.ui.swing.Combobox;
import com.ui.swing.SlideshowImage;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.ComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 *
 * @author ADMIN
 */
public class TrangTriBanTiec extends javax.swing.JDialog {

    private String maHD;
    private ChiTietDichVuDAO chiTietDVDAO = new ChiTietDichVuDAO();
    private CoSoVatChatDAO csvcDAO = new CoSoVatChatDAO();
    private GoiDichVuDAO goiDichVuDAO = new GoiDichVuDAO();

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
    private boolean isLoad = false;

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

        loadGoiDichVu();
        loadCoSoVatChat();
        setCheckNumber();

        ttBanTiec = chiTietDVDAO.selectDichVu(maHD, maDV);

        isView(isCreate);
        isLoad = true;
        if (ttBanTiec != null) {
            if (ttBanTiec.getMaGoi() != null) {
                for (GoiDichVu goi : listGoiDichVu) {
                    if (goi.getMaGoi().equals(ttBanTiec.getMaGoi())) {
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

        ChiTietDichVu ctAoGoi = new ChiTietDichVu();
        ChiTietDichVu ctTham = new ChiTietDichVu();
        ChiTietDichVu ctHoaTT = new ChiTietDichVu();

        ctAoGoi.setMaHD(maHD);
        ctAoGoi.setMaDV(maDV);
        ctAoGoi.setMaCSVC(((CoSoVatChat) cbbAoGhe.getSelectedItem()).getMaCSVC());
        ctAoGoi.setChiPhi(0);
        ctAoGoi.setChiPhiPhatSinh(ShareHelper.toMoney(txtCPPSAoGhe.getText()));
        ctAoGoi.setGhiChu(txtGCAoGhe.getText());

        ctTham.setMaHD(maHD);
        ctTham.setMaDV(maDV);
        ctTham.setMaCSVC(((CoSoVatChat) cbbTham.getSelectedItem()).getMaCSVC());
        ctTham.setChiPhi(0);
        ctTham.setChiPhiPhatSinh(ShareHelper.toMoney(txtCPPSTham.getText()));
        ctTham.setGhiChu(txtGCTham.getText());

        ctHoaTT.setMaHD(maHD);
        ctHoaTT.setMaDV(maDV);
        ctHoaTT.setMaCSVC(((CoSoVatChat) cbbHoaTT.getSelectedItem()).getMaCSVC());
        ctHoaTT.setChiPhi(ShareHelper.toMoney(txtCPHoaTT.getText()));
        ctHoaTT.setChiPhiPhatSinh(ShareHelper.toMoney(txtCPPSHoaTT.getText()));
        ctHoaTT.setGhiChu(txtGCHoaTT.getText());

        list.add(ctAoGoi);
        list.add(ctTham);
        list.add(ctHoaTT);

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
        listAoGhe = csvcDAO.selectByMaDMC(VatTrangTri.AOGHE);
        listHoa = csvcDAO.selectByMaDMC(VatTrangTri.HOACHUDAO);
        listTraiBan = csvcDAO.selectByMaDMC(VatTrangTri.TRAIBAN);

        DefaultComboBoxModel cbbAoGheModel = (DefaultComboBoxModel) cbbAoGhe.getModel();
        cbbAoGheModel.removeAllElements();

        for (CoSoVatChat csvc : listAoGhe) {
            cbbAoGheModel.addElement(csvc);
        }
        cbbAoGhe.setSelectedIndex(-1);

        DefaultComboBoxModel cbbThamTraiModel = (DefaultComboBoxModel) cbbTham.getModel();
        cbbThamTraiModel.removeAllElements();

        for (CoSoVatChat csvc : listTraiBan) {
            cbbThamTraiModel.addElement(csvc);
        }
        cbbTham.setSelectedIndex(-1);

        DefaultComboBoxModel cbbHoaModel = (DefaultComboBoxModel) cbbHoaTT.getModel();
        cbbHoaModel.removeAllElements();

        for (CoSoVatChat csvc : listHoa) {
            cbbHoaModel.addElement(csvc);
        }
        cbbHoaTT.setSelectedIndex(-1);
    }

    // các hàm xử lý form
    public boolean tinhTien() {
        try {
            long tongCPPS = ShareHelper.toMoney(txtCPPSAoGhe.getText()) + ShareHelper.toMoney(txtCPPSHoaTT.getText()) + ShareHelper.toMoney(txtCPPSTham.getText());
            long chiPhi = ShareHelper.toMoney(txtCPAoGhe.getText()) + ShareHelper.toMoney(txtCPHoaTT.getText()) + ShareHelper.toMoney(txtCPTham.getText());

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
            txtChiPhi.setToolTipText(ShareHelper.toMoney(ct.getChiPhi()) + " x " + soLuongBan + " bàn");
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
            txtChiPhi.setToolTipText(ShareHelper.toMoney(ct.getChiPhi()) + " x " + soLuongBan + " bàn");
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
                txtChiPhi.setToolTipText(giaThue + " x " + soLuongBan + " bàn");
            }
//            txtGhiChu.setText("");
//            txtPhatSinh.setText("0");
            tinhTien();
        }
    }

    public void fillForm() {
        ChiTietDichVu ctAoGhe = chiTietDVDAO.selectChiTietDichVuNoCustom(maHD, maDV, VatTrangTri.AOGHE);
        ChiTietDichVu ctTraiBan = chiTietDVDAO.selectChiTietDichVuNoCustom(maHD, maDV, VatTrangTri.TRAIBAN);
        ChiTietDichVu ctHoaChuDao = chiTietDVDAO.selectChiTietDichVuCustom(maHD, maDV, VatTrangTri.HOACHUDAO);

        if (ctAoGhe != null) {

            fillChiTietDichVu(ctAoGhe, listAoGhe, cbbAoGhe, txtCPAoGhe, txtCPPSAoGhe, txtGCAoGhe, soLuongBan, 10, false);
        }

        if (ctTraiBan != null) {

            fillChiTietDichVu(ctTraiBan, listTraiBan, cbbTham, txtCPTham, txtCPPSTham, txtGCTham, soLuongBan, 1, false);
        }

        if (ctHoaChuDao != null) {

            fillChiTietDichVu(ctHoaChuDao, listHoa, cbbHoaTT, txtCPHoaTT, txtCPPSHoaTT, txtGCHoaTT, soLuongBan, 1, true);
        }

        txtTongCPPS.setText(ShareHelper.toMoney(ttBanTiec.getChiPhiPhatSinh()));
        txtChiPhi.setText(ShareHelper.toMoney(ttBanTiec.getChiPhi()));
        txtTongChiPhi.setText(ShareHelper.toMoney(ttBanTiec.getChiPhiPhatSinh() + ttBanTiec.getChiPhi()));
        taGhiChu.setText(ttBanTiec.getGhiChu());

    }

    public void fillFormByGoiDichVu(GoiDichVu goi) {

        ChiTietDichVu ctAoGhe = goiDichVuDAO.selectChiTietGoiDichVuNoCustom(goi.getMaGoi(), VatTrangTri.AOGHE);
        ChiTietDichVu ctTraiBan = goiDichVuDAO.selectChiTietGoiDichVuNoCustom(goi.getMaGoi(), VatTrangTri.TRAIBAN);
        ChiTietDichVu ctHoaChuDao = goiDichVuDAO.selectChiTietGoiDichVuCustom(goi.getMaGoi(), VatTrangTri.HOACHUDAO);

        if (ctAoGhe != null) {

            fillChiTietGoiDichVu(ctAoGhe, listAoGhe, cbbAoGhe, txtCPAoGhe, txtGCAoGhe, soLuongBan, 10, false);
        }

        if (ctTraiBan != null) {

            fillChiTietGoiDichVu(ctTraiBan, listTraiBan, cbbTham, txtCPTham, txtGCTham, soLuongBan, 1, false);
        }

        if (ctHoaChuDao != null) {

            fillChiTietGoiDichVu(ctHoaChuDao, listHoa, cbbHoaTT, txtCPHoaTT, txtGCHoaTT, soLuongBan, 1, true);
        }

    }

    public void isView(boolean isCreate) {
        for (Component cp : pnlTTBanTiec.getComponents()) {
            if (cp instanceof JTextField) {
                cp.setEnabled(isCreate);
            } else if (cp instanceof Combobox) {
                cp.setEnabled(false);
            }

        }
        cbbGoiDV.setEnabled(true);
        btnSave.setVisible(isCreate);
        btnReset.setVisible(isCreate);
        //btnEdit.setVisible(isCreate);
        taGhiChu.setEnabled(isCreate);
    }

    public void isTuyChinhGoiDichVu(boolean is) {
        if (isCreate) {
            cbbAoGhe.setEnabled(is);
            cbbHoaTT.setEnabled(is);
            cbbTham.setEnabled(is);
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
        txtCPHoaTT.addKeyListener(new CheckNumber());
        txtCPPSHoaTT.addKeyListener(new CheckNumber());
        txtCPPSAoGhe.addKeyListener(new CheckNumber());
        txtCPPSTham.addKeyListener(new CheckNumber());
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
        jLabel1 = new javax.swing.JLabel();
        lblViewSlideShow = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlTTBanTiec.setBackground(new java.awt.Color(255, 255, 255));
        pnlTTBanTiec.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Tổng chi phí phải trả");
        pnlTTBanTiec.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 420, -1, -1));

        lblMaNH18.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMaNH18.setText("Gói");
        pnlTTBanTiec.add(lblMaNH18, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 50, 30));

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
        pnlTTBanTiec.add(cbbGoiDV, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, 320, 40));

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
        pnlTTBanTiec.add(btnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 90, -1, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Trang trí bàn tiệc");
        pnlTTBanTiec.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        lblMaNH19.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMaNH19.setText("Áo ghế");
        pnlTTBanTiec.add(lblMaNH19, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, 90, 30));

        cbbAoGhe.setToolTipText("");
        cbbAoGhe.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
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
        pnlTTBanTiec.add(cbbAoGhe, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 270, 330, 45));

        lblMaNH22.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMaNH22.setText("Hoa trang trí");
        pnlTTBanTiec.add(lblMaNH22, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 360, 90, 30));

        cbbHoaTT.setToolTipText("");
        cbbHoaTT.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
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
        pnlTTBanTiec.add(cbbHoaTT, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 340, 330, 45));

        lblMaNH24.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblMaNH24.setText("Tổng chi phí");
        pnlTTBanTiec.add(lblMaNH24, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 420, 160, 30));

        lblMaNH25.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMaNH25.setText("Thảm trãi bàn");
        pnlTTBanTiec.add(lblMaNH25, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 100, 30));

        cbbTham.setToolTipText("");
        cbbTham.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
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
        pnlTTBanTiec.add(cbbTham, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 200, 330, 45));

        txtGCTham.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtGCTham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGCThamActionPerformed(evt);
            }
        });
        pnlTTBanTiec.add(txtGCTham, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 210, 360, 35));

        txtGCAoGhe.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        pnlTTBanTiec.add(txtGCAoGhe, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 280, 360, 35));

        txtGCHoaTT.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtGCHoaTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGCHoaTTActionPerformed(evt);
            }
        });
        pnlTTBanTiec.add(txtGCHoaTT, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 350, 360, 35));

        txtChiPhi.setEditable(false);
        txtChiPhi.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtChiPhi.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        txtChiPhi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtChiPhiActionPerformed(evt);
            }
        });
        pnlTTBanTiec.add(txtChiPhi, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 460, 220, 35));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Vật trang trí");
        pnlTTBanTiec.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Chi phí phát sinh");
        pnlTTBanTiec.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 160, -1, -1));

        txtCPPSTham.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtCPPSTham.setText("0");
        txtCPPSTham.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        txtCPPSTham.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCPPSThamFocusLost(evt);
            }
        });
        txtCPPSTham.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCPPSThamKeyTyped(evt);
            }
        });
        pnlTTBanTiec.add(txtCPPSTham, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 210, 210, 35));

        txtCPPSAoGhe.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtCPPSAoGhe.setText("0");
        txtCPPSAoGhe.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        txtCPPSAoGhe.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCPPSAoGheFocusLost(evt);
            }
        });
        txtCPPSAoGhe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCPPSAoGheActionPerformed(evt);
            }
        });
        txtCPPSAoGhe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCPPSAoGheKeyTyped(evt);
            }
        });
        pnlTTBanTiec.add(txtCPPSAoGhe, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 280, 210, 35));

        txtCPPSHoaTT.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtCPPSHoaTT.setText("0");
        txtCPPSHoaTT.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        txtCPPSHoaTT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCPPSHoaTTFocusLost(evt);
            }
        });
        txtCPPSHoaTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCPPSHoaTTActionPerformed(evt);
            }
        });
        txtCPPSHoaTT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCPPSHoaTTKeyTyped(evt);
            }
        });
        pnlTTBanTiec.add(txtCPPSHoaTT, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 350, 210, 35));

        lblMaNH8.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMaNH8.setText("Ghi chú");
        pnlTTBanTiec.add(lblMaNH8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 440, 60, -1));

        taGhiChu.setColumns(20);
        taGhiChu.setRows(5);
        jScrollPane1.setViewportView(taGhiChu);

        pnlTTBanTiec.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 430, 330, 100));

        txtTongCPPS.setEditable(false);
        txtTongCPPS.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTongCPPS.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        txtTongCPPS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongCPPSActionPerformed(evt);
            }
        });
        pnlTTBanTiec.add(txtTongCPPS, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 460, 200, 35));

        txtTongChiPhi.setEditable(false);
        txtTongChiPhi.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTongChiPhi.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        txtTongChiPhi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongChiPhiActionPerformed(evt);
            }
        });
        pnlTTBanTiec.add(txtTongChiPhi, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 460, 270, 35));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Ghi chú");
        pnlTTBanTiec.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 160, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("Tổng chi phát phát sinh");
        pnlTTBanTiec.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 420, -1, -1));

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
        pnlTTBanTiec.add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 550, -1, 30));

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
        pnlTTBanTiec.add(btnReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(1280, 550, -1, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText("Chi phí");
        pnlTTBanTiec.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 160, -1, -1));

        txtCPTham.setEditable(false);
        txtCPTham.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtCPTham.setText("0");
        txtCPTham.setToolTipText("");
        txtCPTham.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        txtCPTham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCPThamActionPerformed(evt);
            }
        });
        pnlTTBanTiec.add(txtCPTham, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 210, 210, 35));

        txtCPAoGhe.setEditable(false);
        txtCPAoGhe.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtCPAoGhe.setText("0");
        txtCPAoGhe.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        txtCPAoGhe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCPAoGheActionPerformed(evt);
            }
        });
        pnlTTBanTiec.add(txtCPAoGhe, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 280, 210, 35));

        txtCPHoaTT.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtCPHoaTT.setText("0");
        txtCPHoaTT.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        txtCPHoaTT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCPHoaTTFocusLost(evt);
            }
        });
        txtCPHoaTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCPHoaTTActionPerformed(evt);
            }
        });
        txtCPHoaTT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCPHoaTTKeyTyped(evt);
            }
        });
        pnlTTBanTiec.add(txtCPHoaTT, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 350, 210, 35));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel1.setText("VNĐ");
        pnlTTBanTiec.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1400, 460, -1, 30));

        lblViewSlideShow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/happywedding/assets/eye.png"))); // NOI18N
        lblViewSlideShow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblViewSlideShowMouseClicked(evt);
            }
        });
        pnlTTBanTiec.add(lblViewSlideShow, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 50, 40, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTTBanTiec, javax.swing.GroupLayout.PREFERRED_SIZE, 1446, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlTTBanTiec, javax.swing.GroupLayout.PREFERRED_SIZE, 621, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(1464, 667));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cbbGoiDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbGoiDVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbGoiDVActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        isTuyChinhGoiDichVu(true);
        //cbbGoiDV.setSelectedIndex(-1);
    }//GEN-LAST:event_btnEditActionPerformed

    private void cbbAoGheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbAoGheActionPerformed

    }//GEN-LAST:event_cbbAoGheActionPerformed

    private void cbbHoaTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbHoaTTActionPerformed

    }//GEN-LAST:event_cbbHoaTTActionPerformed

    private void cbbThamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbThamActionPerformed

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

    private void txtTongCPPSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTongCPPSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTongCPPSActionPerformed

    private void txtTongChiPhiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTongChiPhiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTongChiPhiActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed

        if (cbbGoiDV.getSelectedIndex() == -1) {
            DialogHelper.alertError(this, "Vui lòng chọn gói");
            return;
        }

        if (cbbAoGhe.getSelectedIndex() == -1 || cbbHoaTT.getSelectedIndex() == -1 || cbbTham.getSelectedIndex() == -1) {
            DialogHelper.alertError(this, "Vui lòng chọn đầy đủ thông tin");
            return;
        }

        if (!checkValid(txtCPPSTham) || !checkValid(txtCPPSHoaTT) || !checkValid(txtCPPSHoaTT) || !checkValid(txtCPHoaTT)) {
            return;
        }

        if (txtCPHoaTT.getText().equals("0")) {
            boolean rs = DialogHelper.confirm(this, "Bạn chưa xác định giá trị của hoa trang trí. Tiếp tục lưu");
            if (!rs) {
                return;
            }
        }

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
        cbbAoGhe.setSelectedIndex(-1);
        cbbHoaTT.setSelectedIndex(-1);
        cbbTham.setSelectedIndex(-1);
        cbbGoiDV.setSelectedIndex(-1);

        txtCPAoGhe.setText("0");
        txtCPHoaTT.setText("0");
        txtCPTham.setText("0");

        txtCPPSAoGhe.setText("0");
        txtCPPSHoaTT.setText("0");
        txtCPPSTham.setText("0");

        txtGCAoGhe.setText("");
        txtGCHoaTT.setText("");
        txtGCTham.setText("");

        tinhTien();

    }//GEN-LAST:event_btnResetActionPerformed

    private void txtCPThamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCPThamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPThamActionPerformed

    private void txtCPHoaTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCPHoaTTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPHoaTTActionPerformed

    private void cbbThamItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbThamItemStateChanged

        changeCombbox(cbbTham, listTraiBan, txtCPTham, txtCPPSTham, txtGCTham, soLuongBan, 1, false);

    }//GEN-LAST:event_cbbThamItemStateChanged

    private void cbbAoGheItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbAoGheItemStateChanged

        changeCombbox(cbbAoGhe, listAoGhe, txtCPAoGhe, txtCPPSAoGhe, txtGCAoGhe, soLuongBan, 10, false);
    }//GEN-LAST:event_cbbAoGheItemStateChanged

    private void cbbHoaTTItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbHoaTTItemStateChanged

        changeCombbox(cbbHoaTT, listHoa, txtCPHoaTT, txtCPPSHoaTT, txtGCHoaTT, soLuongBan, 1, true);
    }//GEN-LAST:event_cbbHoaTTItemStateChanged

    private void txtCPPSThamFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCPPSThamFocusLost
        checkValid(txtCPPSTham);

    }//GEN-LAST:event_txtCPPSThamFocusLost

    private void txtCPPSAoGheFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCPPSAoGheFocusLost

        checkValid(txtCPPSAoGhe);
    }//GEN-LAST:event_txtCPPSAoGheFocusLost

    private void txtCPPSHoaTTFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCPPSHoaTTFocusLost
        checkValid(txtCPPSHoaTT);

    }//GEN-LAST:event_txtCPPSHoaTTFocusLost

    private void txtCPHoaTTFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCPHoaTTFocusLost
        checkValid(txtCPHoaTT);
    }//GEN-LAST:event_txtCPHoaTTFocusLost

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

    private void txtCPPSAoGheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCPPSAoGheActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPPSAoGheActionPerformed

    private void txtCPPSHoaTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCPPSHoaTTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPPSHoaTTActionPerformed

    private void txtCPAoGheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCPAoGheActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPAoGheActionPerformed

    private void txtCPPSThamKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCPPSThamKeyTyped

    }//GEN-LAST:event_txtCPPSThamKeyTyped

    private void txtCPPSAoGheKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCPPSAoGheKeyTyped

    }//GEN-LAST:event_txtCPPSAoGheKeyTyped

    private void txtCPPSHoaTTKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCPPSHoaTTKeyTyped

    }//GEN-LAST:event_txtCPPSHoaTTKeyTyped

    private void txtCPHoaTTKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCPHoaTTKeyTyped

    }//GEN-LAST:event_txtCPHoaTTKeyTyped

    private void lblViewSlideShowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblViewSlideShowMouseClicked
        new SlideshowImage(new JFrame(), true, (GoiDichVu) cbbGoiDV.getSelectedItem(), maDV).setVisible(true);
    }//GEN-LAST:event_lblViewSlideShowMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ui.swing.HoverButton btnEdit;
    private com.ui.swing.HoverButton btnReset;
    private com.ui.swing.HoverButton btnSave;
    private com.ui.swing.Combobox cbbAoGhe;
    private com.ui.swing.Combobox cbbGoiDV;
    private com.ui.swing.Combobox cbbHoaTT;
    private com.ui.swing.Combobox cbbTham;
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
    private javax.swing.JLabel lblMaNH22;
    private javax.swing.JLabel lblMaNH24;
    private javax.swing.JLabel lblMaNH25;
    private javax.swing.JLabel lblMaNH8;
    private javax.swing.JLabel lblViewSlideShow;
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
