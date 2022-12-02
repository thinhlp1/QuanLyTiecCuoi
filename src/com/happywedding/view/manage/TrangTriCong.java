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
public class TrangTriCong extends javax.swing.JDialog {

    private String maHD;
    private ChiTietDichVuDAO chiTietDVDAO = new ChiTietDichVuDAO();
    private CoSoVatChatDAO csvcDAO = new CoSoVatChatDAO();
    private GoiDichVuDAO goiDichVuDAO = new GoiDichVuDAO();

    private boolean isCreate;

    private List<CoSoVatChat> listTham = new ArrayList<>();
    private List<CoSoVatChat> listBangTen = new ArrayList<>();
    private List<CoSoVatChat> listHoaChuDao = new ArrayList<>();
    private List<CoSoVatChat> listHoaPhu = new ArrayList<>();
    private List<CoSoVatChat> listCong = new ArrayList<>();
    private List<GoiDichVu> listGoiDichVu = new ArrayList<>();

    HopDongDichVu ttCong;

    private final String maDV = "TTCONG";
    private boolean isLoad = false;

    static class VatTrangTri {

        static final String CONG = "CONG";
        static final String HOACHUDAO = "HOACHUDAO";
        static final String HOAPHUDAO = "HOAPHUDAO";
        static final String THAM = "THAM";
        static final String BANGTEN = "BANGTEN";

    }

    /**
     * Creates new form TrangTriCong
     */
    public TrangTriCong(java.awt.Frame parent, boolean modal, String maHD) {
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

        ChiTietDichVu ctCong = new ChiTietDichVu();
        ChiTietDichVu ctTham = new ChiTietDichVu();
        ChiTietDichVu ctHoaChuDao = new ChiTietDichVu();
        ChiTietDichVu ctHoaPhu = new ChiTietDichVu();
        ChiTietDichVu ctBangTen = new ChiTietDichVu();

        ctCong.setMaHD(maHD);
        ctCong.setMaDV(maDV);
        ctCong.setMaCSVC(((CoSoVatChat) cbbCong.getSelectedItem()).getMaCSVC());
        ctCong.setChiPhi(0);
        ctCong.setChiPhiPhatSinh(ShareHelper.toMoney(txtCPPSCong.getText()));
        ctCong.setGhiChu(txtGCCong.getText());

        ctTham.setMaHD(maHD);
        ctTham.setMaDV(maDV);
        ctTham.setMaCSVC(((CoSoVatChat) cbbTham.getSelectedItem()).getMaCSVC());
        ctTham.setChiPhi(0);
        ctTham.setChiPhiPhatSinh(ShareHelper.toMoney(txtCPPSTham.getText()));
        ctTham.setGhiChu(txtGCTham.getText());

        ctHoaChuDao.setMaHD(maHD);
        ctHoaChuDao.setMaDV(maDV);
        ctHoaChuDao.setMaCSVC(((CoSoVatChat) cbbHoaChuDao.getSelectedItem()).getMaCSVC());
        ctHoaChuDao.setChiPhi(ShareHelper.toMoney(txtCPHoaChuDao.getText()));
        ctHoaChuDao.setChiPhiPhatSinh(ShareHelper.toMoney(txtCPPSHoaChuDao.getText()));
        ctHoaChuDao.setGhiChu(txtGCHoaChuDao.getText());

        ctHoaPhu.setMaHD(maHD);
        ctHoaPhu.setMaDV(maDV);
        ctHoaPhu.setMaCSVC(((CoSoVatChat) cbbHoaPhu.getSelectedItem()).getMaCSVC());
        ctHoaPhu.setChiPhi(ShareHelper.toMoney(txtCPHoaPhu.getText()));
        ctHoaPhu.setChiPhiPhatSinh(ShareHelper.toMoney(txtCPPSHoaPhu.getText()));
        ctHoaPhu.setGhiChu(txtGCHoaPhu.getText());

        ctBangTen.setMaHD(maHD);
        ctBangTen.setMaDV(maDV);
        ctBangTen.setMaCSVC(((CoSoVatChat) cbbBangTen.getSelectedItem()).getMaCSVC());
        ctBangTen.setChiPhi(0);
        ctBangTen.setChiPhiPhatSinh(ShareHelper.toMoney(txtCPPSBangTen.getText()));
        ctBangTen.setGhiChu(txtGCBangTen.getText());

        list.add(ctCong);
        list.add(ctTham);
        list.add(ctHoaChuDao);
        list.add(ctHoaPhu);
        list.add(ctBangTen);

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
        listCong = csvcDAO.selectByMaDMC(TrangTriCong.VatTrangTri.CONG);
        listHoaChuDao = csvcDAO.selectByMaDMC(TrangTriCong.VatTrangTri.HOACHUDAO);
        listHoaPhu = csvcDAO.selectByMaDMC(TrangTriCong.VatTrangTri.HOAPHUDAO);
        listTham = csvcDAO.selectByMaDMC(TrangTriCong.VatTrangTri.THAM);
        listBangTen = csvcDAO.selectByMaDMC(TrangTriCong.VatTrangTri.BANGTEN);

        DefaultComboBoxModel cbbAoGheModel = (DefaultComboBoxModel) cbbCong.getModel();
        cbbAoGheModel.removeAllElements();

        for (CoSoVatChat csvc : listCong) {
            cbbAoGheModel.addElement(csvc);
        }
        cbbCong.setSelectedIndex(-1);

        DefaultComboBoxModel cbbThamTraiModel = (DefaultComboBoxModel) cbbTham.getModel();
        cbbThamTraiModel.removeAllElements();

        for (CoSoVatChat csvc : listTham) {
            cbbThamTraiModel.addElement(csvc);
        }
        cbbTham.setSelectedIndex(-1);

        DefaultComboBoxModel cbbHoaChuDaoModel = (DefaultComboBoxModel) cbbHoaChuDao.getModel();
        cbbHoaChuDaoModel.removeAllElements();

        for (CoSoVatChat csvc : listHoaChuDao) {
            cbbHoaChuDaoModel.addElement(csvc);
        }
        cbbHoaChuDao.setSelectedIndex(-1);

        DefaultComboBoxModel cbbHoaChuModel = (DefaultComboBoxModel) cbbHoaPhu.getModel();
        cbbHoaChuModel.removeAllElements();

        for (CoSoVatChat csvc : listHoaPhu) {
            cbbHoaChuModel.addElement(csvc);
        }
        cbbHoaPhu.setSelectedIndex(-1);

        DefaultComboBoxModel cbbBangTenModel = (DefaultComboBoxModel) cbbBangTen.getModel();
        cbbBangTenModel.removeAllElements();

        for (CoSoVatChat csvc : listBangTen) {
            cbbBangTenModel.addElement(csvc);
        }
        cbbBangTen.setSelectedIndex(-1);

    }

    // các hàm xử lý form
    public boolean tinhTien() {
        try {
            long tongCPPS = ShareHelper.toMoney(txtCPPSCong.getText()) + ShareHelper.toMoney(txtCPPSHoaChuDao.getText())
                    + ShareHelper.toMoney(txtCPPSHoaPhu.getText()) + ShareHelper.toMoney(txtCPPSTham.getText()) + ShareHelper.toMoney(txtCPPSBangTen.getText());
            long chiPhi = ShareHelper.toMoney(txtCPCong.getText()) + ShareHelper.toMoney(txtCPHoaChuDao.getText())
                    + ShareHelper.toMoney(txtCPHoaPhu.getText()) + ShareHelper.toMoney(txtCPTham.getText()) + ShareHelper.toMoney(txtCPBangTen.getText());

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
                System.out.println(csvc.getMaCSVC());
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

        ChiTietDichVu ctCong = chiTietDVDAO.selectChiTietDichVuNoCustom(maHD, maDV, TrangTriCong.VatTrangTri.CONG);
        ChiTietDichVu ctTham = chiTietDVDAO.selectChiTietDichVuNoCustom(maHD, maDV, TrangTriCong.VatTrangTri.THAM);
        ChiTietDichVu ctHoaChuDao = chiTietDVDAO.selectChiTietDichVuCustom(maHD, maDV, TrangTriCong.VatTrangTri.HOACHUDAO);
        ChiTietDichVu ctHoaPhu = chiTietDVDAO.selectChiTietDichVuCustom(maHD, maDV, TrangTriCong.VatTrangTri.HOAPHUDAO);
        ChiTietDichVu ctBangTen = chiTietDVDAO.selectChiTietDichVuNoCustom(maHD, maDV, TrangTriCong.VatTrangTri.BANGTEN);

        if (ctCong != null) {

            fillChiTietDichVu(ctCong, listCong, cbbCong, txtCPCong, txtCPPSCong, txtGCCong, 1, 1, false);
        }

        if (ctTham != null) {

            fillChiTietDichVu(ctTham, listTham, cbbTham, txtCPTham, txtCPPSTham, txtGCTham, 1, 1, false);
        }

        if (ctHoaChuDao != null) {

            fillChiTietDichVu(ctHoaChuDao, listHoaChuDao, cbbHoaChuDao, txtCPHoaChuDao, txtCPPSHoaChuDao, txtGCHoaChuDao, 1, 1, true);
        }
        if (ctHoaPhu != null) {

            fillChiTietDichVu(ctHoaPhu, listHoaPhu, cbbHoaPhu, txtCPHoaPhu, txtCPPSHoaPhu, txtGCHoaPhu, 1, 1, true);
        }
        if (ctBangTen != null) {

            fillChiTietDichVu(ctBangTen, listBangTen, cbbBangTen, txtCPBangTen, txtCPPSBangTen, txtGCBangTen, 1, 1, false);
        }

        txtTongCPPS.setText(ShareHelper.toMoney(ttCong.getChiPhiPhatSinh()));
        txtChiPhi.setText(ShareHelper.toMoney(ttCong.getChiPhi()));
        txtTongChiPhi.setText(ShareHelper.toMoney(ttCong.getChiPhiPhatSinh() + ttCong.getChiPhi()));
        taGhiChu.setText(ttCong.getGhiChu());

    }

    public void fillFormByGoiDichVu(GoiDichVu goi) {

        ChiTietDichVu ctCong = goiDichVuDAO.selectChiTietGoiDichVuNoCustom(goi.getMaGoi(), TrangTriCong.VatTrangTri.CONG);
        ChiTietDichVu ctTham = goiDichVuDAO.selectChiTietGoiDichVuNoCustom(goi.getMaGoi(), TrangTriCong.VatTrangTri.THAM);
        ChiTietDichVu ctHoaChuDao = goiDichVuDAO.selectChiTietGoiDichVuCustom(goi.getMaGoi(), TrangTriCong.VatTrangTri.HOACHUDAO);
        ChiTietDichVu ctHoaPhu = goiDichVuDAO.selectChiTietGoiDichVuCustom(goi.getMaGoi(), TrangTriCong.VatTrangTri.HOAPHUDAO);
        ChiTietDichVu ctBangTen = goiDichVuDAO.selectChiTietGoiDichVuNoCustom(goi.getMaGoi(), TrangTriCong.VatTrangTri.BANGTEN);

        if (ctCong != null) {

            fillChiTietGoiDichVu(ctCong, listCong, cbbCong, txtCPCong, txtGCCong, 1, 1, false);
        }

        if (ctTham != null) {

            fillChiTietGoiDichVu(ctTham, listTham, cbbTham, txtCPTham, txtGCTham, 1, 1, false);
        }

        if (ctHoaChuDao != null) {

            fillChiTietGoiDichVu(ctHoaChuDao, listHoaChuDao, cbbHoaChuDao, txtCPHoaChuDao, txtGCHoaChuDao, 1, 1, true);
        }
        if (ctHoaPhu != null) {

            fillChiTietGoiDichVu(ctHoaPhu, listHoaPhu, cbbHoaPhu, txtCPHoaPhu, txtGCHoaPhu, 1, 1, true);
        }
        if (ctBangTen != null) {

            fillChiTietGoiDichVu(ctBangTen, listBangTen, cbbBangTen, txtCPBangTen, txtGCBangTen, 1, 1, false);
        }

    }

    public void isView(boolean isCreate) {
        for (Component cp : pnlTTCong.getComponents()) {
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
            cbbCong.setEnabled(is);
            cbbHoaChuDao.setEnabled(is);
            cbbHoaPhu.setEnabled(is);
            cbbTham.setEnabled(is);
            cbbBangTen.setEnabled(is);
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
        txtCPHoaChuDao.addKeyListener(new CheckNumber());
        txtCPHoaPhu.addKeyListener(new CheckNumber());
        txtCPPSCong.addKeyListener(new CheckNumber());
        txtCPPSTham.addKeyListener(new CheckNumber());
        txtCPPSHoaChuDao.addKeyListener(new CheckNumber());
        txtCPPSHoaPhu.addKeyListener(new CheckNumber());
        txtCPPSBangTen.addKeyListener(new CheckNumber());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTTCong = new javax.swing.JPanel();
        lblMaNH18 = new javax.swing.JLabel();
        btnEdit = new com.ui.swing.HoverButton();
        jLabel5 = new javax.swing.JLabel();
        lblMaNH19 = new javax.swing.JLabel();
        cbbHoaChuDao = new com.ui.swing.Combobox();
        lblMaNH22 = new javax.swing.JLabel();
        cbbHoaPhu = new com.ui.swing.Combobox();
        lblMaNH23 = new javax.swing.JLabel();
        cbbTham = new com.ui.swing.Combobox();
        cbbBangTen = new com.ui.swing.Combobox();
        lblMaNH25 = new javax.swing.JLabel();
        cbbCong = new com.ui.swing.Combobox();
        txtGCCong = new javax.swing.JTextField();
        txtGCHoaChuDao = new javax.swing.JTextField();
        txtGCHoaPhu = new javax.swing.JTextField();
        txtGCTham = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCPPSCong = new javax.swing.JTextField();
        txtCPPSHoaChuDao = new javax.swing.JTextField();
        txtCPPSHoaPhu = new javax.swing.JTextField();
        txtCPPSTham = new javax.swing.JTextField();
        txtCPPSBangTen = new javax.swing.JTextField();
        lblMaNH8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taGhiChu = new javax.swing.JTextArea();
        txtGCBangTen = new javax.swing.JTextField();
        lblMaNH26 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnSave = new com.ui.swing.HoverButton();
        btnReset = new com.ui.swing.HoverButton();
        jLabel10 = new javax.swing.JLabel();
        txtCPCong = new javax.swing.JTextField();
        txtCPHoaChuDao = new javax.swing.JTextField();
        txtCPHoaPhu = new javax.swing.JTextField();
        txtCPTham = new javax.swing.JTextField();
        txtCPBangTen = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblMaNH24 = new javax.swing.JLabel();
        txtChiPhi = new javax.swing.JTextField();
        txtTongCPPS = new javax.swing.JTextField();
        txtTongChiPhi = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblViewSlideShow = new javax.swing.JLabel();
        cbbGoiDV = new com.ui.swing.Combobox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlTTCong.setBackground(new java.awt.Color(255, 255, 255));
        pnlTTCong.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblMaNH18.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMaNH18.setText("Gói");
        pnlTTCong.add(lblMaNH18, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 50, 30));

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
        pnlTTCong.add(btnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 80, -1, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Trang trí cổng");
        pnlTTCong.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        lblMaNH19.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMaNH19.setText("Hoa chủ đạo");
        pnlTTCong.add(lblMaNH19, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 300, 90, 30));

        cbbHoaChuDao.setToolTipText("");
        cbbHoaChuDao.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cbbHoaChuDao.setLabeText("");
        cbbHoaChuDao.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbHoaChuDaoItemStateChanged(evt);
            }
        });
        cbbHoaChuDao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbHoaChuDaoActionPerformed(evt);
            }
        });
        pnlTTCong.add(cbbHoaChuDao, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 280, 330, 45));

        lblMaNH22.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMaNH22.setText("Hoa phụ ");
        pnlTTCong.add(lblMaNH22, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 360, 90, 30));

        cbbHoaPhu.setToolTipText("");
        cbbHoaPhu.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cbbHoaPhu.setLabeText("");
        cbbHoaPhu.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbHoaPhuItemStateChanged(evt);
            }
        });
        cbbHoaPhu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbHoaPhuActionPerformed(evt);
            }
        });
        pnlTTCong.add(cbbHoaPhu, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 340, 330, 45));

        lblMaNH23.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMaNH23.setText("Thảm");
        pnlTTCong.add(lblMaNH23, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 430, 50, 30));

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
        pnlTTCong.add(cbbTham, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 410, 330, 45));

        cbbBangTen.setToolTipText("");
        cbbBangTen.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cbbBangTen.setLabeText("");
        cbbBangTen.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbBangTenItemStateChanged(evt);
            }
        });
        cbbBangTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbBangTenActionPerformed(evt);
            }
        });
        pnlTTCong.add(cbbBangTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 470, 330, 45));

        lblMaNH25.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMaNH25.setText("Cổng");
        pnlTTCong.add(lblMaNH25, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, 50, 30));

        cbbCong.setToolTipText("");
        cbbCong.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cbbCong.setLabeText("");
        cbbCong.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbCongItemStateChanged(evt);
            }
        });
        cbbCong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbCongActionPerformed(evt);
            }
        });
        pnlTTCong.add(cbbCong, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 210, 330, 45));

        txtGCCong.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtGCCong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGCCongActionPerformed(evt);
            }
        });
        pnlTTCong.add(txtGCCong, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 220, 360, 35));

        txtGCHoaChuDao.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        pnlTTCong.add(txtGCHoaChuDao, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 280, 360, 35));

        txtGCHoaPhu.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtGCHoaPhu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGCHoaPhuActionPerformed(evt);
            }
        });
        pnlTTCong.add(txtGCHoaPhu, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 350, 360, 35));

        txtGCTham.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtGCTham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGCThamActionPerformed(evt);
            }
        });
        pnlTTCong.add(txtGCTham, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 420, 360, 35));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Vật trang trí");
        pnlTTCong.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Chi phí phát sinh");
        pnlTTCong.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 170, -1, -1));

        txtCPPSCong.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtCPPSCong.setText("0");
        txtCPPSCong.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        txtCPPSCong.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCPPSCongFocusLost(evt);
            }
        });
        txtCPPSCong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCPPSCongActionPerformed(evt);
            }
        });
        pnlTTCong.add(txtCPPSCong, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 220, 190, 35));

        txtCPPSHoaChuDao.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtCPPSHoaChuDao.setText("0");
        txtCPPSHoaChuDao.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        txtCPPSHoaChuDao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCPPSHoaChuDaoFocusLost(evt);
            }
        });
        pnlTTCong.add(txtCPPSHoaChuDao, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 280, 190, 35));

        txtCPPSHoaPhu.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtCPPSHoaPhu.setText("0");
        txtCPPSHoaPhu.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        txtCPPSHoaPhu.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCPPSHoaPhuFocusLost(evt);
            }
        });
        txtCPPSHoaPhu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCPPSHoaPhuActionPerformed(evt);
            }
        });
        pnlTTCong.add(txtCPPSHoaPhu, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 350, 190, 35));

        txtCPPSTham.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtCPPSTham.setText("0");
        txtCPPSTham.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        txtCPPSTham.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCPPSThamFocusLost(evt);
            }
        });
        txtCPPSTham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCPPSThamActionPerformed(evt);
            }
        });
        pnlTTCong.add(txtCPPSTham, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 420, 190, 35));

        txtCPPSBangTen.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtCPPSBangTen.setText("0");
        txtCPPSBangTen.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        txtCPPSBangTen.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCPPSBangTenFocusLost(evt);
            }
        });
        txtCPPSBangTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCPPSBangTenActionPerformed(evt);
            }
        });
        pnlTTCong.add(txtCPPSBangTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 480, 190, 35));

        lblMaNH8.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMaNH8.setText("Ghi chú");
        pnlTTCong.add(lblMaNH8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 560, 60, -1));

        taGhiChu.setColumns(20);
        taGhiChu.setRows(5);
        jScrollPane1.setViewportView(taGhiChu);

        pnlTTCong.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 550, 330, 100));

        txtGCBangTen.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtGCBangTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGCBangTenActionPerformed(evt);
            }
        });
        pnlTTCong.add(txtGCBangTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 480, 360, 35));

        lblMaNH26.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMaNH26.setText("Bảng tên");
        pnlTTCong.add(lblMaNH26, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 490, 90, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Ghi chú");
        pnlTTCong.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, -30, -1, -1));

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
        pnlTTCong.add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 680, -1, 30));

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
        pnlTTCong.add(btnReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(1280, 680, -1, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText("Ghi chú");
        pnlTTCong.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 170, -1, -1));

        txtCPCong.setEditable(false);
        txtCPCong.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtCPCong.setText("0");
        txtCPCong.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        txtCPCong.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCPCongFocusLost(evt);
            }
        });
        txtCPCong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCPCongActionPerformed(evt);
            }
        });
        pnlTTCong.add(txtCPCong, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 220, 190, 35));

        txtCPHoaChuDao.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtCPHoaChuDao.setText("0");
        txtCPHoaChuDao.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        txtCPHoaChuDao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCPHoaChuDaoFocusLost(evt);
            }
        });
        pnlTTCong.add(txtCPHoaChuDao, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 280, 190, 35));

        txtCPHoaPhu.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtCPHoaPhu.setText("0");
        txtCPHoaPhu.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        txtCPHoaPhu.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCPHoaPhuFocusLost(evt);
            }
        });
        txtCPHoaPhu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCPHoaPhuActionPerformed(evt);
            }
        });
        pnlTTCong.add(txtCPHoaPhu, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 350, 190, 35));

        txtCPTham.setEditable(false);
        txtCPTham.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtCPTham.setText("0");
        txtCPTham.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        txtCPTham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCPThamActionPerformed(evt);
            }
        });
        pnlTTCong.add(txtCPTham, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 420, 190, 35));

        txtCPBangTen.setEditable(false);
        txtCPBangTen.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtCPBangTen.setText("0");
        txtCPBangTen.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        txtCPBangTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCPBangTenActionPerformed(evt);
            }
        });
        pnlTTCong.add(txtCPBangTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 480, 190, 35));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setText("Chi phí");
        pnlTTCong.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 170, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Tổng chi phí phải trả");
        pnlTTCong.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 560, -1, -1));

        lblMaNH24.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblMaNH24.setText("Tổng chi phí");
        pnlTTCong.add(lblMaNH24, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 560, 160, 30));

        txtChiPhi.setEditable(false);
        txtChiPhi.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtChiPhi.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        txtChiPhi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtChiPhiActionPerformed(evt);
            }
        });
        pnlTTCong.add(txtChiPhi, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 600, 190, 35));

        txtTongCPPS.setEditable(false);
        txtTongCPPS.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTongCPPS.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        txtTongCPPS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongCPPSActionPerformed(evt);
            }
        });
        pnlTTCong.add(txtTongCPPS, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 600, 210, 35));

        txtTongChiPhi.setEditable(false);
        txtTongChiPhi.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTongChiPhi.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        txtTongChiPhi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongChiPhiActionPerformed(evt);
            }
        });
        pnlTTCong.add(txtTongChiPhi, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 600, 270, 35));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("Tổng chi phát phát sinh");
        pnlTTCong.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 560, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel1.setText("VNĐ");
        pnlTTCong.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1400, 600, -1, 30));

        lblViewSlideShow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/happywedding/assets/eye.png"))); // NOI18N
        lblViewSlideShow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblViewSlideShowMouseClicked(evt);
            }
        });
        pnlTTCong.add(lblViewSlideShow, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 40, 40, 20));

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
        pnlTTCong.add(cbbGoiDV, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, 320, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTTCong, javax.swing.GroupLayout.PREFERRED_SIZE, 1446, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTTCong, javax.swing.GroupLayout.DEFAULT_SIZE, 735, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(1464, 782));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        isTuyChinhGoiDichVu(true);

    }//GEN-LAST:event_btnEditActionPerformed

    private void cbbHoaChuDaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbHoaChuDaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbHoaChuDaoActionPerformed

    private void cbbHoaPhuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbHoaPhuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbHoaPhuActionPerformed

    private void cbbThamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbThamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbThamActionPerformed

    private void cbbBangTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbBangTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbBangTenActionPerformed

    private void cbbCongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbCongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbCongActionPerformed

    private void txtGCCongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGCCongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGCCongActionPerformed

    private void txtGCHoaPhuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGCHoaPhuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGCHoaPhuActionPerformed

    private void txtGCThamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGCThamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGCThamActionPerformed

    private void txtCPPSCongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCPPSCongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPPSCongActionPerformed

    private void txtCPPSHoaPhuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCPPSHoaPhuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPPSHoaPhuActionPerformed

    private void txtCPPSThamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCPPSThamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPPSThamActionPerformed

    private void txtCPPSBangTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCPPSBangTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPPSBangTenActionPerformed

    private void txtGCBangTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGCBangTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGCBangTenActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed

        if (cbbGoiDV.getSelectedIndex() == -1) {
            DialogHelper.alertError(this, "Vui lòng chọn gói");
            return;
        }

        if (cbbCong.getSelectedIndex() == -1 || cbbHoaChuDao.getSelectedIndex() == -1 || cbbHoaPhu.getSelectedIndex() == -1
                || cbbTham.getSelectedIndex() == -1 || cbbBangTen.getSelectedIndex() == -1) {
            DialogHelper.alertError(this, "Vui lòng chọn đầy đủ thông tin");
            return;
        }

        if (!checkValid(txtCPPSCong) || !checkValid(txtCPPSHoaChuDao) || !checkValid(txtCPPSHoaPhu) || !checkValid(txtCPPSTham)
                || !checkValid(txtCPPSBangTen) || !checkValid(txtCPHoaChuDao) || !checkValid(txtCPHoaPhu)) {
            return;
        }

        if (txtCPHoaChuDao.getText().equals("0") || txtCPHoaPhu.getText().equals("0")) {
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
        cbbCong.setSelectedIndex(-1);
        cbbHoaChuDao.setSelectedIndex(-1);
        cbbHoaPhu.setSelectedIndex(-1);
        cbbBangTen.setSelectedIndex(-1);
        cbbTham.setSelectedIndex(-1);
        cbbGoiDV.setSelectedIndex(-1);

        txtCPCong.setText("0");
        txtCPHoaChuDao.setText("0");
        txtCPHoaPhu.setText("0");
        txtCPBangTen.setText("0");
        txtCPTham.setText("0");

        txtCPPSCong.setText("0");
        txtCPPSHoaChuDao.setText("0");
        txtCPPSHoaPhu.setText("0");
        txtCPPSBangTen.setText("0");
        txtCPPSTham.setText("0");

        txtGCCong.setText("");
        txtGCHoaChuDao.setText("");
        txtGCHoaPhu.setText("");
        txtGCBangTen.setText("");
        txtGCTham.setText("");

        tinhTien();
    }//GEN-LAST:event_btnResetActionPerformed

    private void txtCPCongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCPCongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPCongActionPerformed

    private void txtCPHoaPhuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCPHoaPhuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPHoaPhuActionPerformed

    private void txtCPThamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCPThamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPThamActionPerformed

    private void txtCPBangTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCPBangTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPBangTenActionPerformed

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

    private void cbbThamItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbThamItemStateChanged
        changeCombbox(cbbTham, listTham, txtCPTham, txtCPPSTham, txtGCTham, 1, 1, false);
    }//GEN-LAST:event_cbbThamItemStateChanged

    private void cbbBangTenItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbBangTenItemStateChanged
        changeCombbox(cbbBangTen, listBangTen, txtCPBangTen, txtCPPSBangTen, txtGCBangTen, 1, 1, false);
    }//GEN-LAST:event_cbbBangTenItemStateChanged

    private void cbbCongItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbCongItemStateChanged
        changeCombbox(cbbCong, listCong, txtCPCong, txtCPPSCong, txtGCCong, 1, 1, false);
    }//GEN-LAST:event_cbbCongItemStateChanged

    private void cbbHoaPhuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbHoaPhuItemStateChanged
        changeCombbox(cbbHoaPhu, listHoaPhu, txtCPHoaPhu, txtCPPSHoaPhu, txtGCHoaPhu, 1, 1, true);
    }//GEN-LAST:event_cbbHoaPhuItemStateChanged

    private void cbbHoaChuDaoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbHoaChuDaoItemStateChanged
        changeCombbox(cbbHoaChuDao, listHoaChuDao, txtCPHoaChuDao, txtCPPSHoaChuDao, txtGCHoaChuDao, 1, 1, true);
    }//GEN-LAST:event_cbbHoaChuDaoItemStateChanged

    private void txtCPCongFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCPCongFocusLost
        checkValid(txtCPCong);
    }//GEN-LAST:event_txtCPCongFocusLost

    private void txtCPHoaChuDaoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCPHoaChuDaoFocusLost
        checkValid(txtCPHoaChuDao);
    }//GEN-LAST:event_txtCPHoaChuDaoFocusLost

    private void txtCPHoaPhuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCPHoaPhuFocusLost
        checkValid(txtCPHoaPhu);
    }//GEN-LAST:event_txtCPHoaPhuFocusLost

    private void txtCPPSCongFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCPPSCongFocusLost
        checkValid(txtCPPSCong);
    }//GEN-LAST:event_txtCPPSCongFocusLost

    private void txtCPPSHoaChuDaoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCPPSHoaChuDaoFocusLost
        checkValid(txtCPPSHoaChuDao);
    }//GEN-LAST:event_txtCPPSHoaChuDaoFocusLost

    private void txtCPPSHoaPhuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCPPSHoaPhuFocusLost
        checkValid(txtCPPSHoaPhu);
    }//GEN-LAST:event_txtCPPSHoaPhuFocusLost

    private void txtCPPSThamFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCPPSThamFocusLost
        checkValid(txtCPPSTham);
    }//GEN-LAST:event_txtCPPSThamFocusLost

    private void txtCPPSBangTenFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCPPSBangTenFocusLost
        checkValid(txtCPPSBangTen);
    }//GEN-LAST:event_txtCPPSBangTenFocusLost

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

    private void cbbGoiDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbGoiDVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbGoiDVActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ui.swing.HoverButton btnEdit;
    private com.ui.swing.HoverButton btnReset;
    private com.ui.swing.HoverButton btnSave;
    private com.ui.swing.Combobox cbbBangTen;
    private com.ui.swing.Combobox cbbCong;
    private com.ui.swing.Combobox cbbGoiDV;
    private com.ui.swing.Combobox cbbHoaChuDao;
    private com.ui.swing.Combobox cbbHoaPhu;
    private com.ui.swing.Combobox cbbTham;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JLabel lblMaNH23;
    private javax.swing.JLabel lblMaNH24;
    private javax.swing.JLabel lblMaNH25;
    private javax.swing.JLabel lblMaNH26;
    private javax.swing.JLabel lblMaNH8;
    private javax.swing.JLabel lblViewSlideShow;
    private javax.swing.JPanel pnlTTCong;
    private javax.swing.JTextArea taGhiChu;
    private javax.swing.JTextField txtCPBangTen;
    private javax.swing.JTextField txtCPCong;
    private javax.swing.JTextField txtCPHoaChuDao;
    private javax.swing.JTextField txtCPHoaPhu;
    private javax.swing.JTextField txtCPPSBangTen;
    private javax.swing.JTextField txtCPPSCong;
    private javax.swing.JTextField txtCPPSHoaChuDao;
    private javax.swing.JTextField txtCPPSHoaPhu;
    private javax.swing.JTextField txtCPPSTham;
    private javax.swing.JTextField txtCPTham;
    private javax.swing.JTextField txtChiPhi;
    private javax.swing.JTextField txtGCBangTen;
    private javax.swing.JTextField txtGCCong;
    private javax.swing.JTextField txtGCHoaChuDao;
    private javax.swing.JTextField txtGCHoaPhu;
    private javax.swing.JTextField txtGCTham;
    private javax.swing.JTextField txtTongCPPS;
    private javax.swing.JTextField txtTongChiPhi;
    // End of variables declaration//GEN-END:variables
}
