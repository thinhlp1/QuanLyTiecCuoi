/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happywedding.view.manage;

import com.happywedding.dao.ChiTietDatMonDAO;
import com.happywedding.dao.ChiTietDichVuDiKemDAO;
import com.happywedding.dao.DichVuDiKemDAO;
import com.happywedding.dao.HoaDonDAO;
import com.happywedding.helper.AppStatus;
import com.happywedding.helper.DateHelper;
import com.happywedding.helper.DialogHelper;
import com.happywedding.helper.ShareHelper;
import com.happywedding.model.ChiPhiPhatSinhModel;
import com.happywedding.model.ChiTietDatMon;
import com.happywedding.model.ChiTietDichVuDiKem;
import com.happywedding.model.DichVuDiKemModel;
import com.happywedding.model.HoaDon;
import com.ui.swing.Combobox;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractCellEditor;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author ADMIN
 */
public class ChiPhiPhatSinh extends javax.swing.JFrame {

    private String maHD;
    private boolean isCreate;

    private DefaultTableModel tblThucDonModel;
    private DefaultTableModel tblChiTietDichVuModel;

    private List<ChiPhiPhatSinhModel> listChiPhiPhatSinh = new ArrayList<>();
    private List<ChiTietDatMon> listChiTietDatMon = new ArrayList<>();
    private List<ChiTietDichVuDiKem> listChiTietDichVuDiKem = new ArrayList<>();

    private HoaDonDAO hoaDonDAO = new HoaDonDAO();
    private ChiTietDatMonDAO datMonDAO = new ChiTietDatMonDAO();
    private ChiTietDichVuDiKemDAO diKemDAO = new ChiTietDichVuDiKemDAO();

    private HoaDon hoaDon;

    static class DichVu {

        static String TTCONG = "TTCONG";
        static String TTSANKHAU = "TTSANKHAU";
        static String TTBANTIEC = "TTBANTIEC";
        static String NGHETHUAT = "NGHETHUAT";
        static String DATMON = "DATMON";
        static String DIKEM = "DIKEM";
    }

    /**
     * Creates new form ChiPhiPhatSinh
     */
    public ChiPhiPhatSinh(String maHD, boolean isCreate) {
        this.maHD = maHD;
        this.isCreate = isCreate;
        initComponents();

        hoaDon = hoaDonDAO.selectByID(maHD);
//        if (hoaDon != null && hoaDon.getTrangTha() == 1) {
//            btnXuatHoaDon.setVisible(false);
//        }
        init();
    }

    public void init() {
        tblChiTietDichVuModel = (DefaultTableModel) tblChiTietDichVu.getModel();
        tblThucDonModel = (DefaultTableModel) tblThucDon.getModel();
        tblChiTietDichVu.fixTable(jScrollPane2);
        tblThucDon.fixTable(jScrollPane3);
        tblThucDon.addKeyListener(new MyTableCellEditor(), 2);
        tblChiTietDichVu.addKeyListener(new MyTableCellEditor(), 2);
        tblChiTietDichVuModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                String editingCell = "";

                int col = tblChiTietDichVu.getEditingColumn();
                int row = tblChiTietDichVu.getEditingRow();

                if (col > -1 && row > -1) {
                    if (col == 2) {
                        editingCell = (String) tblChiTietDichVu.getValueAt(row, col);

                        if (editingCell.equals(ShareHelper.toMoney(ShareHelper.toMoney(editingCell)))) {
                            tinhTien();
                            return;
                        }

                        if (editingCell.equals("")) {
                            tblChiTietDichVu.setValueAt("0", row, col);

                        } else if (ShareHelper.toMoney(editingCell) < 1000 && !editingCell.equals("0")) {
                            tblChiTietDichVu.setValueAt("1000", row, col);
                            tinhTien();

                        } else {
                            //tblChiTietDichVu.setValueAt(ShareHelper.toMoney(ShareHelper.toMoney(editingCell)), row, col);

                        }

                    }

                }
            }
        });
        tblThucDonModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                String editingCell = "";

                int col = tblThucDon.getEditingColumn();
                int row = tblThucDon.getEditingRow();

                if (col > -1 && row > -1) {
                    if (col == 2) {
                        editingCell = (String) tblThucDon.getValueAt(row, col);

                        if (editingCell.equals(ShareHelper.toMoney(ShareHelper.toMoney(editingCell)))) {
                            tinhTien();
                            return;
                        }
                        if (editingCell.equals("")) {
                            tblThucDon.setValueAt("0", row, col);
                        } else if (ShareHelper.toMoney(editingCell) < 1000 && !editingCell.equals("0")) {
                            tblThucDon.setValueAt("1000", row, col);
                            tinhTien();
                        } else {
                            //tblThucDon.setValueAt(ShareHelper.toMoney(ShareHelper.toMoney(editingCell)), row, col);
                        }
                    }
                }
            }
        });

        loadChiPhiDatMon();
        loadChiPhiDiKem();
        fillChiPhiDichVu();
        fillTableDichVuDiKem(listChiTietDichVuDiKem);
        fillTableThucDon(listChiTietDatMon);
        tinhTien();
        setCheckNumber();

//        if (hoaDonDAO.selectByID(maHD) != null) {
//            if (hoaDonDAO.selectByID(maHD).getTrangTha() == 0) {
//                isView(true);
//            }
//        } else {
//                isView(false);
//        }
        isView(isCreate);

    }

//    public void load() {
//        listChiPhiPhatSinh = hoaDonDAO.selectChiPhiPhatSinh(maHD);
//
//    }
    public void isView(boolean is) {
        for (Component cp : pnlChiTiet.getComponents()) {
            cp.setEnabled(is);

        }

        tblChiTietDichVu.setEnabled(is);
        tblThucDon.setEnabled(is);
        btnXuatHoaDon.setEnabled(true);
    }

    public void loadChiPhiDatMon() {
        listChiTietDatMon = datMonDAO.selectByMaPhanLoai(maHD, "NUOC");
    }

    public void loadChiPhiDiKem() {
        listChiTietDichVuDiKem = diKemDAO.selectChiTietDichVuDiKemByMaDV(maHD, "PHAOKIMTUYEN");
    }

    public void fillChiPhiDichVu() {
        ChiPhiPhatSinhModel model = hoaDonDAO.selectChiPhiPhatSinhByMaDV(maHD, DichVu.TTCONG);
        if (model != null) {
            txtTrangTriCong.setText(ShareHelper.toMoney(model.getChiPhi()));
            txtLyDoTrangTriCong.setText(model.getLyDo());
        }
        model = hoaDonDAO.selectChiPhiPhatSinhByMaDV(maHD, DichVu.TTSANKHAU);
        if (model != null) {
            txtTTSanKhau.setText(ShareHelper.toMoney(model.getChiPhi()));
            txtLyDoTTSanKhau.setText(model.getLyDo());
        }

        model = hoaDonDAO.selectChiPhiPhatSinhByMaDV(maHD, DichVu.TTBANTIEC);
        if (model != null) {
            txtTTBanTiec.setText(ShareHelper.toMoney(model.getChiPhi()));
            txtLyDoTTBanTiec.setText(model.getLyDo());
        }

        model = hoaDonDAO.selectChiPhiPhatSinhByMaDV(maHD, DichVu.NGHETHUAT);
        if (model != null) {
            txtNgheThuat.setText(ShareHelper.toMoney(model.getChiPhi()));
            txtLyDoNgheThuat.setText(model.getLyDo());
        }

        model = hoaDonDAO.selectChiPhiPhatSinhByMaDV(maHD, DichVu.DATMON);
        if (model != null) {
            txtDatMon.setText(ShareHelper.toMoney(model.getChiPhi()));
            txtLyDoDatMon.setText(model.getLyDo());
        }

        model = hoaDonDAO.selectChiPhiPhatSinhByMaDV(maHD, DichVu.DIKEM);
        if (model != null) {
            txtDiKem.setText(ShareHelper.toMoney(model.getChiPhi()));
            txtLyDoDiKem.setText(model.getLyDo());
        }

    }

    public void fillTableThucDon(List<ChiTietDatMon> list) {
        tblThucDonModel.setRowCount(0);

        for (ChiTietDatMon ct : list) {
            Object[] row = {
                ct.getTenMA(),
                ShareHelper.toMoney(ct.getGia()),
                ct.getSoLuong() == -1 ? "0" : ct.getSoLuong()
            };

            tblThucDonModel.addRow(row);

        }
    }

    public void fillTableDichVuDiKem(List<ChiTietDichVuDiKem> list) {
        tblChiTietDichVuModel.setRowCount(0);
        for (ChiTietDichVuDiKem ct : list) {
            Object[] row = {
                ct.getTenDV(),
                ShareHelper.toMoney(ct.getChiPhi()),
                (ct.getSoLuong() == -1) ? "0" : ct.getSoLuong()
            };
            tblChiTietDichVuModel.addRow(row);
        }
    }

    public void tinhTien() {
        long chiPhiDichVu = 0;
        long chiPhiDatMon = 0;
        long chiPhiDiKem = 0;
        long tongTien = 0;

        chiPhiDichVu = ShareHelper.toMoney(txtTrangTriCong.getText()) + ShareHelper.toMoney(txtTTSanKhau.getText()) + ShareHelper.toMoney(txtTTBanTiec.getText())
                + ShareHelper.toMoney(txtNgheThuat.getText()) + ShareHelper.toMoney(txtDatMon.getText()) + ShareHelper.toMoney(txtDiKem.getText());

        listChiTietDatMon = getChiPhiDatMon();
        for (ChiTietDatMon ct : listChiTietDatMon) {
            chiPhiDatMon += ct.getSoLuong() * ct.getGia();
        }

        listChiTietDichVuDiKem = getChiPhiDiKem();
        for (ChiTietDichVuDiKem ct : listChiTietDichVuDiKem) {
            chiPhiDiKem += ct.getSoLuong() * ct.getChiPhi();
        }

        tongTien = chiPhiDatMon + chiPhiDiKem + chiPhiDichVu;
        txtChiPhi.setText(ShareHelper.toMoney(tongTien));

    }

    public List<ChiPhiPhatSinhModel> getChiPhiPhatSinh() {
        List<ChiPhiPhatSinhModel> listChiPhiPhatSinh = new ArrayList<>();
        ChiPhiPhatSinhModel ctTTCong = new ChiPhiPhatSinhModel();
        ChiPhiPhatSinhModel ctTTSanKhau = new ChiPhiPhatSinhModel();
        ChiPhiPhatSinhModel ctNgheThua = new ChiPhiPhatSinhModel();
        ChiPhiPhatSinhModel ctTTBanTiec = new ChiPhiPhatSinhModel();
        ChiPhiPhatSinhModel ctDichVuDiKem = new ChiPhiPhatSinhModel();
        ChiPhiPhatSinhModel ctDichVuDatMon = new ChiPhiPhatSinhModel();

        if (ShareHelper.toMoney(txtTrangTriCong.getText()) > 0) {
            ctTTCong.setMaHD(maHD);
            ctTTCong.setMaDV(DichVu.TTCONG);
            ctTTCong.setChiPhi(ShareHelper.toMoney(txtTrangTriCong.getText()));
            ctTTCong.setLyDo(txtLyDoTrangTriCong.getText());
            listChiPhiPhatSinh.add(ctTTCong);
        }

        if (ShareHelper.toMoney(txtTTSanKhau.getText()) > 0) {
            ctTTSanKhau.setMaHD(maHD);
            ctTTSanKhau.setMaDV(DichVu.TTSANKHAU);
            ctTTSanKhau.setChiPhi(ShareHelper.toMoney(txtTTSanKhau.getText()));
            ctTTSanKhau.setLyDo(txtLyDoTTSanKhau.getText());
            listChiPhiPhatSinh.add(ctTTSanKhau);
        }

        if (ShareHelper.toMoney(txtNgheThuat.getText()) > 0) {
            ctNgheThua.setMaHD(maHD);
            ctNgheThua.setMaDV(DichVu.NGHETHUAT);
            ctNgheThua.setChiPhi(ShareHelper.toMoney(txtNgheThuat.getText()));
            ctNgheThua.setLyDo(txtLyDoNgheThuat.getText());
            listChiPhiPhatSinh.add(ctNgheThua);
        }

        if (ShareHelper.toMoney(txtTTBanTiec.getText()) > 0) {
            ctTTBanTiec.setMaHD(maHD);
            ctTTBanTiec.setMaDV(DichVu.TTBANTIEC);
            ctTTBanTiec.setChiPhi(ShareHelper.toMoney(txtTTBanTiec.getText()));
            ctTTBanTiec.setLyDo(txtLyDoTTBanTiec.getText());
            listChiPhiPhatSinh.add(ctTTBanTiec);
        }

        if (ShareHelper.toMoney(txtDiKem.getText()) > 0) {
            ctDichVuDiKem.setMaHD(maHD);
            ctDichVuDiKem.setMaDV(DichVu.DIKEM);
            ctDichVuDiKem.setChiPhi(ShareHelper.toMoney(txtDiKem.getText()));
            ctDichVuDiKem.setLyDo(txtLyDoDiKem.getText());
            listChiPhiPhatSinh.add(ctDichVuDiKem);
        }

        if (ShareHelper.toMoney(txtDatMon.getText()) > 0) {
            ctDichVuDatMon.setMaHD(maHD);
            ctDichVuDatMon.setMaDV(DichVu.DATMON);
            ctDichVuDatMon.setChiPhi(ShareHelper.toMoney(txtDatMon.getText()));
            ctDichVuDatMon.setLyDo(txtLyDoDatMon.getText());
            listChiPhiPhatSinh.add(ctDichVuDatMon);
        }

        return listChiPhiPhatSinh;

    }

    public List getChiPhiDatMon() {
        List<ChiTietDatMon> list = new ArrayList<>();
        int size = tblThucDon.getRowCount();
        for (int i = 0; i < size; i++) {

            ChiTietDatMon ct = listChiTietDatMon.get(i);
            ct.setSoLuong(Integer.parseInt(tblThucDon.getValueAt(i, 2).toString()));

            list.add(ct);
        }
        return list;
    }

    public List getChiPhiDiKem() {
        List<ChiTietDichVuDiKem> list = new ArrayList<>();
        int size = tblChiTietDichVu.getRowCount();
        for (int i = 0; i < size; i++) {

            ChiTietDichVuDiKem ct = listChiTietDichVuDiKem.get(i);
            ct.setSoLuong(Integer.parseInt(tblChiTietDichVu.getValueAt(i, 2).toString()));

            list.add(ct);
        }
        return list;
    }

    public boolean insertChiPhiDichVu() {
        listChiPhiPhatSinh = getChiPhiPhatSinh();
        try {
            for (ChiPhiPhatSinhModel ct : listChiPhiPhatSinh) {
                hoaDonDAO.insertChiPhiPhatSinh(ct);

            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            DialogHelper.alertError(this, "Xuất không thành công");
            return false;
        }

    }

    public boolean insertChiTietDatMon() {
        List<ChiTietDatMon> list = getChiPhiDatMon();
        try {
            for (ChiTietDatMon ct : list) {
                datMonDAO.updateChiTietDatMon(ct);

            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            DialogHelper.alertError(this, "Xuất không thành công");
            return false;
        }

    }

    public boolean insertChiTietDichVuDiKem() {
        List<ChiTietDichVuDiKem> list = getChiPhiDiKem();

        try {
            for (ChiTietDichVuDiKem ct : list) {
                diKemDAO.updateChiTietDiKem(ct);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            DialogHelper.alertError(this, "Xuất không thành công");
            return false;
        }
    }

    class CheckNumber extends KeyAdapter {

        public void keyTyped(java.awt.event.KeyEvent evt) {
            char testChar = evt.getKeyChar();
            if (!((Character.isDigit(testChar)))) {
                if (testChar == '\n') {
                    lblMaNH24.requestFocus();
                }
                if (testChar != '.') {
                    evt.consume();
                }
            }
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
        // tinhTien();
        return true;
    }

    public void setCheckNumber() {
        txtTTBanTiec.addKeyListener(new CheckNumber());
        txtDatMon.addKeyListener(new CheckNumber());
        txtDiKem.addKeyListener(new CheckNumber());
        txtTTSanKhau.addKeyListener(new CheckNumber());
        txtTTSanKhau.addKeyListener(new CheckNumber());
        txtNgheThuat.addKeyListener(new CheckNumber());
    }

    class MyTableCellEditor extends AbstractCellEditor implements TableCellEditor {

        JComponent component = new JTextField();

        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
                int rowIndex, int vColIndex) {

            ((JTextField) component).setText("" + value);
            component.addKeyListener(new CheckNumber());

            return component;
        }

        public Object getCellEditorValue() {
            return ((JTextField) component).getText();
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

        pnlChiTiet = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtTrangTriCong = new javax.swing.JTextField();
        txtLyDoTrangTriCong = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        lblMaNH26 = new javax.swing.JLabel();
        txtTTBanTiec = new javax.swing.JTextField();
        txtLyDoTTBanTiec = new javax.swing.JTextField();
        lblMaNH27 = new javax.swing.JLabel();
        txtTTSanKhau = new javax.swing.JTextField();
        txtLyDoTTSanKhau = new javax.swing.JTextField();
        lblMaNH28 = new javax.swing.JLabel();
        lblMaNH29 = new javax.swing.JLabel();
        txtDatMon = new javax.swing.JTextField();
        txtLyDoDatMon = new javax.swing.JTextField();
        lblMaNH30 = new javax.swing.JLabel();
        txtNgheThuat = new javax.swing.JTextField();
        txtLyDoNgheThuat = new javax.swing.JTextField();
        lblMaNH31 = new javax.swing.JLabel();
        txtDiKem = new javax.swing.JTextField();
        txtLyDoDiKem = new javax.swing.JTextField();
        btnXuatHoaDon = new com.ui.swing.HoverButton();
        lblMaNH24 = new javax.swing.JLabel();
        txtChiPhi = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblThucDon = new com.ui.swing.Table(new CheckNumber());
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblChiTietDichVu = new com.ui.swing.Table(new CheckNumber());

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlChiTiet.setBackground(new java.awt.Color(255, 255, 255));
        pnlChiTiet.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Số lượng nước uống");
        pnlChiTiet.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 450, 240, -1));

        txtTrangTriCong.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTrangTriCong.setText("0");
        txtTrangTriCong.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTrangTriCongFocusLost(evt);
            }
        });
        txtTrangTriCong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTrangTriCongActionPerformed(evt);
            }
        });
        pnlChiTiet.add(txtTrangTriCong, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, 270, 35));

        txtLyDoTrangTriCong.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtLyDoTrangTriCong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLyDoTrangTriCongActionPerformed(evt);
            }
        });
        pnlChiTiet.add(txtLyDoTrangTriCong, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 80, 360, 35));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Lý do");
        pnlChiTiet.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 40, -1, -1));

        lblMaNH26.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMaNH26.setText("Trang trí bàn tiệc");
        pnlChiTiet.add(lblMaNH26, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 120, 30));

        txtTTBanTiec.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTTBanTiec.setText("0");
        txtTTBanTiec.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTTBanTiecFocusLost(evt);
            }
        });
        txtTTBanTiec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTTBanTiecActionPerformed(evt);
            }
        });
        pnlChiTiet.add(txtTTBanTiec, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 140, 270, 35));

        txtLyDoTTBanTiec.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtLyDoTTBanTiec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLyDoTTBanTiecActionPerformed(evt);
            }
        });
        pnlChiTiet.add(txtLyDoTTBanTiec, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 140, 360, 35));

        lblMaNH27.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMaNH27.setText("Trang trí sân khấu");
        pnlChiTiet.add(lblMaNH27, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 120, 30));

        txtTTSanKhau.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTTSanKhau.setText("0");
        txtTTSanKhau.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTTSanKhauFocusLost(evt);
            }
        });
        txtTTSanKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTTSanKhauActionPerformed(evt);
            }
        });
        pnlChiTiet.add(txtTTSanKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 200, 270, 35));

        txtLyDoTTSanKhau.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtLyDoTTSanKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLyDoTTSanKhauActionPerformed(evt);
            }
        });
        pnlChiTiet.add(txtLyDoTTSanKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 200, 360, 35));

        lblMaNH28.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMaNH28.setText("Trang trí cổng");
        pnlChiTiet.add(lblMaNH28, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 100, 30));

        lblMaNH29.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMaNH29.setText("Đặt món");
        pnlChiTiet.add(lblMaNH29, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 100, 30));

        txtDatMon.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtDatMon.setText("0");
        txtDatMon.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDatMonFocusLost(evt);
            }
        });
        txtDatMon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDatMonActionPerformed(evt);
            }
        });
        pnlChiTiet.add(txtDatMon, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 260, 270, 35));

        txtLyDoDatMon.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtLyDoDatMon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLyDoDatMonActionPerformed(evt);
            }
        });
        pnlChiTiet.add(txtLyDoDatMon, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 260, 360, 35));

        lblMaNH30.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMaNH30.setText("Nghệ thuật");
        pnlChiTiet.add(lblMaNH30, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 100, 30));

        txtNgheThuat.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtNgheThuat.setText("0");
        txtNgheThuat.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNgheThuatFocusLost(evt);
            }
        });
        txtNgheThuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNgheThuatActionPerformed(evt);
            }
        });
        pnlChiTiet.add(txtNgheThuat, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 320, 270, 35));

        txtLyDoNgheThuat.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtLyDoNgheThuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLyDoNgheThuatActionPerformed(evt);
            }
        });
        pnlChiTiet.add(txtLyDoNgheThuat, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 320, 360, 35));

        lblMaNH31.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMaNH31.setText("Đi kèm");
        pnlChiTiet.add(lblMaNH31, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, 100, 30));

        txtDiKem.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtDiKem.setText("0");
        txtDiKem.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDiKemFocusLost(evt);
            }
        });
        txtDiKem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiKemActionPerformed(evt);
            }
        });
        pnlChiTiet.add(txtDiKem, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 380, 270, 35));

        txtLyDoDiKem.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtLyDoDiKem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLyDoDiKemActionPerformed(evt);
            }
        });
        pnlChiTiet.add(txtLyDoDiKem, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 380, 360, 35));

        btnXuatHoaDon.setBackground(new java.awt.Color(24, 153, 29));
        btnXuatHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        btnXuatHoaDon.setText("Xuất hóa đơn");
        btnXuatHoaDon.setBorderColor(new java.awt.Color(24, 153, 29));
        btnXuatHoaDon.setColor(new java.awt.Color(24, 153, 29));
        btnXuatHoaDon.setColorClick(new java.awt.Color(0, 204, 0));
        btnXuatHoaDon.setColorOver(new java.awt.Color(0, 204, 0));
        btnXuatHoaDon.setFocusPainted(false);
        btnXuatHoaDon.setLabelColor(java.awt.Color.white);
        btnXuatHoaDon.setLableColorClick(java.awt.Color.white);
        btnXuatHoaDon.setRadius(15);
        btnXuatHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatHoaDonActionPerformed(evt);
            }
        });
        pnlChiTiet.add(btnXuatHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 890, -1, 30));

        lblMaNH24.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblMaNH24.setText("Tổng chi phí");
        pnlChiTiet.add(lblMaNH24, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 890, 160, 30));

        txtChiPhi.setEditable(false);
        txtChiPhi.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtChiPhi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtChiPhiActionPerformed(evt);
            }
        });
        pnlChiTiet.add(txtChiPhi, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 890, 270, 35));

        tblThucDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Tên món", "Giá", "Số lượng", "Tổng tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblThucDon.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        tblThucDon.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tblThucDonFocusLost(evt);
            }
        });
        tblThucDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThucDonMouseClicked(evt);
            }
        });
        tblThucDon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tblThucDonKeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(tblThucDon);

        pnlChiTiet.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 490, 840, 160));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("Chi phí phát sinh");
        pnlChiTiet.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText("Dịch vụ sử dụng");
        pnlChiTiet.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 660, 180, -1));

        tblChiTietDichVu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Dịch vụ", "Giá", "Số lượng", "Tổng tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblChiTietDichVu.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        tblChiTietDichVu.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tblChiTietDichVuFocusLost(evt);
            }
        });
        tblChiTietDichVu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChiTietDichVuMouseClicked(evt);
            }
        });
        tblChiTietDichVu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tblChiTietDichVuKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(tblChiTietDichVu);

        pnlChiTiet.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 700, 840, 160));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlChiTiet, javax.swing.GroupLayout.DEFAULT_SIZE, 916, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlChiTiet, javax.swing.GroupLayout.DEFAULT_SIZE, 949, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtTrangTriCongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTrangTriCongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTrangTriCongActionPerformed

    private void txtLyDoTrangTriCongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLyDoTrangTriCongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLyDoTrangTriCongActionPerformed

    private void txtTTBanTiecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTTBanTiecActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTTBanTiecActionPerformed

    private void txtLyDoTTBanTiecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLyDoTTBanTiecActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLyDoTTBanTiecActionPerformed

    private void txtTTSanKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTTSanKhauActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTTSanKhauActionPerformed

    private void txtLyDoTTSanKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLyDoTTSanKhauActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLyDoTTSanKhauActionPerformed

    private void txtDatMonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDatMonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDatMonActionPerformed

    private void txtLyDoDatMonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLyDoDatMonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLyDoDatMonActionPerformed

    private void txtNgheThuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgheThuatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgheThuatActionPerformed

    private void txtLyDoNgheThuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLyDoNgheThuatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLyDoNgheThuatActionPerformed

    private void txtDiKemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiKemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiKemActionPerformed

    private void txtLyDoDiKemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLyDoDiKemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLyDoDiKemActionPerformed

    private void btnXuatHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatHoaDonActionPerformed
//        boolean rs = DialogHelper.confirm(this, "Xác nhận xuất hóa đơn ? Không thể xuất lại lần 2");
        if (hoaDonDAO.selectByID(maHD) != null) {
            if (hoaDonDAO.selectByID(maHD).getTrangTha() == 0) {
                if (insertChiPhiDichVu() && insertChiTietDatMon() && insertChiTietDichVuDiKem()) {
                    hoaDonDAO.updateHoaDon(maHD,DateHelper.now(),AppStatus.USER.getMaNV());
                    //btnXuatHoaDon.setVisible(false);
                    isView(false);
                    // DialogHelper.alert(this, "Xuất thành công");
                }
            } else {
                DialogHelper.alert(this, "Xuất lại hóa đơn");
            }
        }
    }//GEN-LAST:event_btnXuatHoaDonActionPerformed

    private void txtChiPhiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtChiPhiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtChiPhiActionPerformed

    private void tblThucDonFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tblThucDonFocusLost

        //      tblThucDon.getCellEditor().stopCellEditing();
    }//GEN-LAST:event_tblThucDonFocusLost

    private void tblThucDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThucDonMouseClicked
//        int index = tblThucDon.getSelectedRow();
//        if (index >= 0) {
//            if (evt.getClickCount() >= 2) {
//
//                ChiTietDatMon ct = listChiTietDatMon.get(index);
//                for (MonAn m : listMonAnBk) {
//                    if (ct.getMaMA().equals(m.getMaMA())) {
//                        listChiTietDatMon.remove(ct);
//
//                        break;
//                    }
//                }
//
//                fillTableThucDon(listChiTietDatMon);
//                filtedMonAn();
//
//                tinhTien();
//            } else if (SwingUtilities.isRightMouseButton(evt)) {
//                boolean rs = DialogHelper.confirm(this, "Chuyển qua bàn phụ");
//                if (rs) {
//                    listMonAnBanPhu.add(listChiTietDatMon.get(tblThucDon.getSelectedRow()));
//                    autoSetSoLuong();
//                    tinhTien();
//                }
//            }
//        }
    }//GEN-LAST:event_tblThucDonMouseClicked

    private void tblThucDonKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblThucDonKeyTyped

    }//GEN-LAST:event_tblThucDonKeyTyped

    private void tblChiTietDichVuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tblChiTietDichVuFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_tblChiTietDichVuFocusLost

    private void tblChiTietDichVuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChiTietDichVuMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblChiTietDichVuMouseClicked

    private void tblChiTietDichVuKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblChiTietDichVuKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tblChiTietDichVuKeyTyped

    private void txtTrangTriCongFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTrangTriCongFocusLost
        checkValid(txtTrangTriCong);
        tinhTien();
    }//GEN-LAST:event_txtTrangTriCongFocusLost

    private void txtTTBanTiecFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTTBanTiecFocusLost
        checkValid(txtTTBanTiec);
        tinhTien();
    }//GEN-LAST:event_txtTTBanTiecFocusLost

    private void txtTTSanKhauFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTTSanKhauFocusLost
        checkValid(txtTTSanKhau);
        tinhTien();
    }//GEN-LAST:event_txtTTSanKhauFocusLost

    private void txtDatMonFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDatMonFocusLost
        checkValid(txtDatMon);
        tinhTien();
    }//GEN-LAST:event_txtDatMonFocusLost

    private void txtNgheThuatFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNgheThuatFocusLost
        checkValid(txtNgheThuat);
        tinhTien();
    }//GEN-LAST:event_txtNgheThuatFocusLost

    private void txtDiKemFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDiKemFocusLost
        checkValid(txtDiKem);
        tinhTien();
    }//GEN-LAST:event_txtDiKemFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ui.swing.HoverButton btnXuatHoaDon;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblMaNH24;
    private javax.swing.JLabel lblMaNH26;
    private javax.swing.JLabel lblMaNH27;
    private javax.swing.JLabel lblMaNH28;
    private javax.swing.JLabel lblMaNH29;
    private javax.swing.JLabel lblMaNH30;
    private javax.swing.JLabel lblMaNH31;
    private javax.swing.JPanel pnlChiTiet;
    private com.ui.swing.Table tblChiTietDichVu;
    private com.ui.swing.Table tblThucDon;
    private javax.swing.JTextField txtChiPhi;
    private javax.swing.JTextField txtDatMon;
    private javax.swing.JTextField txtDiKem;
    private javax.swing.JTextField txtLyDoDatMon;
    private javax.swing.JTextField txtLyDoDiKem;
    private javax.swing.JTextField txtLyDoNgheThuat;
    private javax.swing.JTextField txtLyDoTTBanTiec;
    private javax.swing.JTextField txtLyDoTTSanKhau;
    private javax.swing.JTextField txtLyDoTrangTriCong;
    private javax.swing.JTextField txtNgheThuat;
    private javax.swing.JTextField txtTTBanTiec;
    private javax.swing.JTextField txtTTSanKhau;
    private javax.swing.JTextField txtTrangTriCong;
    // End of variables declaration//GEN-END:variables
}
