/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happywedding.view.manage;

import com.happywedding.dao.ChiTietDatMonDAO;
import com.happywedding.dao.MonAnDAO;
import com.happywedding.dao.ThucDonDAO;
import com.happywedding.helper.AppStatus;
import com.happywedding.helper.DialogHelper;
import com.happywedding.helper.ShareHelper;
import com.happywedding.model.ChiTietDatMon;
import com.happywedding.model.CoSoVatChat;
import com.happywedding.model.DichVuDatMon;
import com.happywedding.model.GoiDichVu;
import com.happywedding.model.MonAn;
import com.happywedding.model.PhanLoaiMonAn;
import com.happywedding.model.ThucDon;
import com.ui.swing.Combobox;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.plaf.basic.BasicSpinnerUI;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author ADMIN
 */
public class DatMonTest extends javax.swing.JDialog {

    private String maHD;
    private boolean isCreate;
    private ThucDonDAO thucDonDAO = new ThucDonDAO();
    private ChiTietDatMonDAO chiTietDatMonDAO = new ChiTietDatMonDAO();
    private List<PhanLoaiMonAn> listPLMA = new ArrayList<>();
    private List<ThucDon> listThucDon = new ArrayList<>();
    private List<MonAn> listMonAn = new ArrayList<>();
    private List<ChiTietDatMon> listChiTietDatMon = new ArrayList<>();
    private List<ChiTietDatMon> listMonAnBanPhu = new ArrayList<>();

    private List<MonAn> listMonAnBk = new ArrayList<>();
    private List<MonAn> listFilted = new ArrayList<>();

    private boolean isLoad = false;

    private DichVuDatMon dichVuDatMon;

    private DefaultTableModel tblThucDonModel;
    private DefaultTableModel tblMonAnModel;
    private int soLuongBan;

    /**
     * Creates new form DatMon
     */
    public DatMonTest(java.awt.Frame parent, boolean modal, String maHD, int soLuongBan) {
        super(parent, modal);
        this.maHD = maHD;
        this.isCreate = modal;
        this.soLuongBan = soLuongBan;
        initComponents();
        init();
    }

    public void init() {
        tblMonAn.fixTable(jScrollPane2);
        tblThucDon.fixTable(jScrollPane3);
        tblThucDonModel = (DefaultTableModel) tblThucDon.getModel();
        tblMonAnModel = (DefaultTableModel) tblMonAn.getModel();
        TableColumnModel columnModel = tblThucDon.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(40);
        columnModel.getColumn(0).setMaxWidth(40);
        columnModel.getColumn(1).setPreferredWidth(180);
        columnModel.getColumn(1).setMaxWidth(180);
        columnModel.getColumn(2).setPreferredWidth(110);
        columnModel.getColumn(2).setMaxWidth(110);
        columnModel.getColumn(5).setPreferredWidth(80);
        columnModel.getColumn(5).setMaxWidth(80);

        TableColumnModel columnModel2 = tblMonAn.getColumnModel();

        columnModel2.getColumn(1).setPreferredWidth(110);
        columnModel2.getColumn(1).setMaxWidth(110);

        ((AbstractTableModel) tblThucDon.getModel()).fireTableDataChanged();
        tblThucDon.addKeyListener(new MyTableCellEditor(), 4);
        tblThucDon.addKeyListener(new MyTableCellEditor(), 5);
        // tblThucDonModel.fireTableDataChanged();
        tblThucDonModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                String editingCell = "";

                int col = tblThucDon.getEditingColumn();
                int row = tblThucDon.getEditingRow();

                if (col > -1 && row > -1) {
                    if (col == 4) {
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
//                            DialogHelper.alertError(null, "Chi phí thấp nhất là 1.000 VNĐ");
//                              tblThucDon.setCellSelectionEnabled(true);
////                            tblThucDon.changeSelection(row, col, false, true);
////                            tblThucDon.requestFocus();
////                            tblThucDon.editCellAt(row, col,null);
//                                tblThucDon.setRowSelectionInterval(row, row);
//                                tblThucDon.setColumnSelectionInterval(col, col);
//                                tblThucDon.requestFocus();
//                              tblThucDon.setCellSelectionEnabled(false);

                        } else {
                            tblThucDon.setValueAt(ShareHelper.toMoney(ShareHelper.toMoney(editingCell)), row, col);

                        }

                    }

                }
            }

        });

        spnSoBanChinh.setModel(new SpinnerNumberModel(soLuongBan, soLuongBan - 3, soLuongBan, 1));

        spnSoBanChinh.setUI(new BasicSpinnerUI());

        loadThucDon();
        loadPhanLoaiMonAn();
        loadChiTietDatMon();
        loadMonAn();

        dichVuDatMon = chiTietDatMonDAO.selectDichVuDatMon(maHD);
        if (dichVuDatMon != null) {
            if (dichVuDatMon.getMaTD() != null) {
                for (ThucDon thucDon : listThucDon) {
                    if (thucDon.getMaTD().equals(dichVuDatMon.getMaTD())) {
                        cbbThucDon.setSelectedItem(thucDon);
                    }
                }
            } else {
                cbbThucDon.setSelectedItem(-1);
            }
        }

        isLoad = true;
        fillDatMonForm();
        fillTableThucDon(listChiTietDatMon);
        filtedMonAn();

        isView(isCreate);
    }

    public void isView(boolean isCreate) {

        btnSave.setVisible(isCreate);
        btnReset.setVisible(isCreate);
        cbbThucDon.setEnabled(isCreate);
        cbbLoaiMon.setEnabled(isCreate);
        txtSearch.setEditable(isCreate);
        tblMonAn.setEnabled(isCreate);
        tblThucDon.setEnabled(isCreate);
        taGhiChu.setEnabled(isCreate);
    }

    // các hàm lấy dữ liệu
    public void loadThucDon() {
        listThucDon = thucDonDAO.select();
        DefaultComboBoxModel cbbModel = (DefaultComboBoxModel) cbbThucDon.getModel();
        cbbModel.removeAllElements();
        for (ThucDon td : listThucDon) {
            cbbModel.addElement(td);
        }
        cbbThucDon.setSelectedIndex(-1);
    }

    public void loadPhanLoaiMonAn() {
        listPLMA = new com.happywedding.dao.PhanLoaiMonAn().select();
        DefaultComboBoxModel cbbModel = (DefaultComboBoxModel) cbbLoaiMon.getModel();
        cbbModel.removeAllElements();
        cbbModel.addElement("Tất cả");
        for (PhanLoaiMonAn pl : listPLMA) {
            cbbModel.addElement(pl);
        }
        cbbLoaiMon.setSelectedIndex(-1);
    }

    public void loadChiTietDatMon() {
        listChiTietDatMon = chiTietDatMonDAO.selectChiTietDatMon(maHD);
        int soBanPhu = soLuongBan;

        for (ChiTietDatMon ct : listChiTietDatMon) {
            if (ct.getSoLuong() < soBanPhu && ct.getSoLuong() != -1) {

                soBanPhu = ct.getSoLuong();
            }
        }
        for (ChiTietDatMon ct : listChiTietDatMon) {
            if (ct.getSoLuong() == soBanPhu && ct.getSoLuong() != -1) {
                listMonAnBanPhu.add(ct);
//                soBanPhu = ct.getSoLuong();
            }
        }
        if (soBanPhu != soLuongBan) {
            try {
                spnSoBanChinh.setValue(soLuongBan - soBanPhu);
            } catch (Exception e) {
                //   e.printStackTrace();
            }
            lblBanPhu.setText(soBanPhu + "");
        } else {
            lblBanPhu.setText("0");
        }
        autoSetSoLuong();
    }

    public void loadMonAn() {
        listMonAn = new MonAnDAO().select();

    }

    // các hàm để thực hiện DAO
    public boolean insertDichVuDatMon() {
        DichVuDatMon dv = getDichVuDatMon();
        try {
            chiTietDatMonDAO.insertDichVuDatMon(dv);
            return true;
        } catch (Exception e) {
            DialogHelper.alertError(this, "Lưu không thành công");
            return false;
        }
    }

    public void insertChiTietDatMon() {
        List<ChiTietDatMon> list = getChiTietDatMon();
        for (ChiTietDatMon ct : list) {
            try {
                chiTietDatMonDAO.insertChiTietDatMon(ct);
            } catch (Exception e) {
                // e.printStackTrace();

                DialogHelper.alertError(this, "Lưu không thành công");
                return;
            }
        }

    }

    public void updateDichVuDatMon() {
        DichVuDatMon dv = getDichVuDatMon();
        try {
            chiTietDatMonDAO.updateDichVuDatMon(dv);
        } catch (Exception e) {
            DialogHelper.alertError(this, "Lưu không thành công");
        }
    }

    public void updateChiTietDichVu() {
        try {
            chiTietDatMonDAO.removeAllChiTietDatMon(maHD);
            insertChiTietDatMon();
        } catch (Exception e) {
            DialogHelper.alertError(this, "Lưu không thành công");
        }
    }

    // các hàm lấy dữ liệu từ form
    public List<ChiTietDatMon> getChiTietDatMon() {
        List<ChiTietDatMon> list = new ArrayList<>();
        int size = tblThucDon.getRowCount();
        for (int i = 0; i < size; i++) {

            ChiTietDatMon ct = listChiTietDatMon.get(i);
            ct.setMaHD(maHD);
            ct.setGhiChu((String) tblThucDon.getValueAt(i, 3));
            ct.setChiPhiPhatSinh(ShareHelper.toMoney((String) tblThucDon.getValueAt(i, 4)));
            if (!tblThucDon.getValueAt(i, 5).toString().equals("CXD")) {
                ct.setSoLuong(Integer.parseInt(tblThucDon.getValueAt(i, 5).toString()));
                // System.out.println(tblThucDon.getValueAt(i, 1).toString() + " : " + tblThucDon.getValueAt(i, 5).toString());
            } else {
                ct.setSoLuong(-1);
            }

            list.add(ct);
        }
        return list;
    }

    // các hàm lấy dữ liệu từ form 
    public DichVuDatMon getDichVuDatMon() {
        DichVuDatMon dvdm = new DichVuDatMon();
        dvdm.setMaHD(maHD);
        dvdm.setMaTD(((ThucDon) cbbThucDon.getSelectedItem()).getMaTD());
        dvdm.setChiPhi(ShareHelper.toMoney(txtChiPhi.getText()));
        dvdm.setGhiChu(taGhiChu.getText());
        return dvdm;
    }

    // các hàm để hiển thị lên form
    public void fillDatMonForm() {
        if (dichVuDatMon != null) {
            taGhiChu.setText(dichVuDatMon.getGhiChu());
            txtChiPhi.setText(ShareHelper.toMoney(dichVuDatMon.getChiPhi()));
            txtTongCPPS.setText(ShareHelper.toMoney(dichVuDatMon.getChiPhiPhatSinh() * soLuongBan));
            txtTongChiPhi.setText(ShareHelper.toMoney(dichVuDatMon.getChiPhi() + dichVuDatMon.getChiPhiPhatSinh()));
        }

    }

    public void fillTableThucDon(List<ChiTietDatMon> list) {
        // tblThucDonModel = (DefaultTableModel) tblThucDon.getModel();
        tblThucDonModel.setRowCount(0);
        tblThucDon.resetRowColor();
        for (ChiTietDatMon ct : list) {
            Object[] row = {ct.getThuTu(),
                ct.getTenMA(),
                ShareHelper.toMoney(ct.getGia()),
                ct.getGhiChu(),
                ShareHelper.toMoney(ct.getChiPhiPhatSinh()),
                ct.getSoLuong()
            };
            //System.out.println("row");
            tblThucDonModel.addRow(row);
            if (ct.getSoLuong() == Integer.parseInt(lblBanPhu.getText())) {
                tblThucDon.addRowColor(tblThucDon.getRowCount() - 1);
            }
        }
        autoSetSoLuong();
    }
    
    
        public void fillTableThucDonPhu(List<ChiTietDatMon> list) {
        DefaultTableModel tblThucDonPhuModel = (DefaultTableModel) tblThucDonPhu.getModel();
        tblThucDonModel.setRowCount(0);
        tblThucDonPhu.resetRowColor();
        for (ChiTietDatMon ct : list) {
            Object[] row = {ct.getThuTu(),
                ct.getTenMA(),
                ShareHelper.toMoney(ct.getGia()),
                ct.getGhiChu(),
                ShareHelper.toMoney(ct.getChiPhiPhatSinh()),
                ct.getSoLuong()
            };
            //System.out.println("row");
            tblThucDonModel.addRow(row);
            if (ct.getSoLuong() == Integer.parseInt(lblBanPhu.getText())) {
                tblThucDonPhu.addRowColor(tblThucDonPhu.getRowCount() - 1);
            }
        }
        autoSetSoLuong();
    }

    public void fillTableMonAn(List<MonAn> list) {
        tblMonAnModel = (DefaultTableModel) tblMonAn.getModel();
        tblMonAnModel.setRowCount(0);
        for (MonAn ma : list) {
            Object[] row = {
                ma.getTenMA(),
                ShareHelper.toMoney(ma.getGiaTien()),};

            tblMonAnModel.addRow(row);
        }
    }

    public void filtedMonAn() {
        List<MonAn> list = new ArrayList<>();
        PhanLoaiMonAn pl = null;
        if (cbbLoaiMon.getSelectedIndex() != -1 && cbbLoaiMon.getSelectedIndex() != 0) {
            pl = (PhanLoaiMonAn) cbbLoaiMon.getSelectedItem();
        }
        String txtSearch = this.txtSearch.getText();

        listMonAnBk.clear();
        listFilted.clear();
        if (pl == null) {
            for (MonAn ma : listMonAn) {
                boolean isExit = false;
                for (ChiTietDatMon ct : listChiTietDatMon) {
                    if (ct.getMaMA().equals(ma.getMaMA())) {
                        listMonAnBk.add(ma);
                        isExit = true;
                        break;
                    }
                }
                if (!isExit) {
                    if (ma.getSearchInfo().contains(txtSearch)) {
                        listFilted.add(ma);
                        list.add(ma);
                    }
                };

            }
        } else {
            for (MonAn ma : listMonAn) {
                boolean isExit = false;
                for (ChiTietDatMon ct : listChiTietDatMon) {
                    if (ct.getMaMA().equals(ma.getMaMA())) {
                        listMonAnBk.add(ma);
                        isExit = true;
                        break;
                    }
                }
                if (!isExit) {
                    if (ma.getMaPL().equals(pl.getMaPL()) && ma.getSearchInfo().contains(txtSearch)) {
                        listFilted.add(ma);
                        list.add(ma);
                    }
                }
            }

        }
        fillTableMonAn(list);
    }

    public void tinhTien() {
        long chiPhi = 0;
        long chiPhiPhatSinh = 0;
        long tongChiPhi = 0;

        listChiTietDatMon = getChiTietDatMon();

        for (ChiTietDatMon ct : listChiTietDatMon) {
            chiPhi += ct.getGia() * ct.getSoLuong();
            chiPhiPhatSinh += ct.getChiPhiPhatSinh() * ct.getSoLuong();
        }

        tongChiPhi = (chiPhi + chiPhiPhatSinh);

        txtChiPhi.setText(ShareHelper.toMoney(chiPhi));
        // txtChiPhi.setToolTipText(chiPhi + " x " + soLuongBan + "bàn");
        txtTongCPPS.setText(ShareHelper.toMoney(chiPhiPhatSinh));
        //txtTongCPPS.setToolTipText(chiPhiPhatSinh + " x " + soLuongBan + "bàn");
        txtTongChiPhi.setText(ShareHelper.toMoney(tongChiPhi));

    }

    public void autoSetSoLuong() {
        int soLuongCoBan = soLuongBan;
        int soBanChinh = Integer.parseInt(spnSoBanChinh.getValue().toString());
        int soBanPhu = Integer.parseInt(lblBanPhu.getText());
        int size = tblThucDon.getRowCount();
        for (int i = 0; i < size; i++) {
            boolean isNuoc = false;
            for (MonAn ma : listMonAn) {
                if (tblThucDon.getValueAt(i, 1).equals(ma.getTenMA())) {
                    boolean isChinh = true;
                    for (ChiTietDatMon ct : listMonAnBanPhu) {
                        if (ct.getMaMA().equals(ma.getMaMA())) {

                            if (ma.getMaPL().equals("NUOC")) {
                                tblThucDon.setValueAt("CXD", i, 5);
                                isNuoc = true;
                                break;
                            }
                            if (!isNuoc) {
                                tblThucDon.setValueAt(soBanPhu, i, 5);
                            }
                            isChinh = false;
                            break;
                        }
                    }
                    if (isChinh) {
                        if (ma.getMaPL().equals("NUOC")) {
                            tblThucDon.setValueAt("CXD", i, 5);
                            //tblThucDon.setValueAt(soBanChinh * 10, i, 5);
                            isNuoc = true;
                            break;
                        }
                        if (!isNuoc) {
                            tblThucDon.setValueAt(soBanChinh, i, 5);
                        }
                    }

                }

            }
        }

    }

    public void autoSetThuTu() {
        int size = tblThucDon.getRowCount();
        for (int i = 0; i < size; i++) {
            tblThucDon.setValueAt(i, i, 0);
        }
    }

    public void changeSoLuongBan() {

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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblMonAn = new com.ui.swing.Table();
        btnBack = new com.ui.swing.InkwellButton();
        jLabel9 = new javax.swing.JLabel();
        txtChiPhi = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTongChiPhi = new javax.swing.JTextField();
        btnSave = new com.ui.swing.HoverButton();
        btnReset = new com.ui.swing.HoverButton();
        jLabel5 = new javax.swing.JLabel();
        lblMaNH18 = new javax.swing.JLabel();
        cbbThucDon = new com.ui.swing.Combobox();
        cbbLoaiMon = new com.ui.swing.Combobox();
        lblSearch = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        btnNext = new com.ui.swing.InkwellButton();
        txtTongCPPS = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblThucDon = new com.ui.swing.Table(new CheckNumber());
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taGhiChu = new javax.swing.JTextArea();
        lblMaNH8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        spnSoBanChinh = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        lblBanPhu = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        btnSave1 = new com.ui.swing.HoverButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblThucDonPhu = new com.ui.swing.Table(new CheckNumber());

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblMonAn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên món", "Giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMonAn.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        tblMonAn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMonAnMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblMonAn);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 140, 390, 490));

        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/happywedding/assets/back.png"))); // NOI18N
        btnBack.setFocusPainted(false);
        jPanel1.add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 340, 50, 50));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("Tổng chi phí");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 690, -1, -1));

        txtChiPhi.setEditable(false);
        txtChiPhi.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jPanel1.add(txtChiPhi, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 730, 190, 35));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Tổng chi phí phải trả");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 700, -1, -1));

        txtTongChiPhi.setEditable(false);
        txtTongChiPhi.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jPanel1.add(txtTongChiPhi, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 730, 280, 35));

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
        jPanel1.add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 790, -1, 30));

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
        jPanel1.add(btnReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 790, -1, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Thực đơn");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, 30));

        lblMaNH18.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMaNH18.setText("Thực đơn");
        jPanel1.add(lblMaNH18, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 80, 30));

        cbbThucDon.setToolTipText("");
        cbbThucDon.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cbbThucDon.setLabeText("");
        cbbThucDon.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbThucDonItemStateChanged(evt);
            }
        });
        cbbThucDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbThucDonActionPerformed(evt);
            }
        });
        jPanel1.add(cbbThucDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 320, 40));

        cbbLoaiMon.setLabeText("Loại món");
        cbbLoaiMon.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbLoaiMonItemStateChanged(evt);
            }
        });
        cbbLoaiMon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLoaiMonActionPerformed(evt);
            }
        });
        jPanel1.add(cbbLoaiMon, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 60, 160, 54));

        lblSearch.setBackground(new java.awt.Color(204, 204, 255));
        lblSearch.setForeground(new java.awt.Color(102, 0, 255));
        lblSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/happywedding/assets/Search.png"))); // NOI18N
        lblSearch.setAutoscrolls(true);
        lblSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSearchMouseClicked(evt);
            }
        });
        jPanel1.add(lblSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 80, -1, 33));

        txtSearch.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtSearch.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSearchKeyTyped(evt);
            }
        });
        jPanel1.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 80, 190, 35));

        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/happywedding/assets/next.png"))); // NOI18N
        btnNext.setFocusPainted(false);
        jPanel1.add(btnNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 340, -1, -1));

        txtTongCPPS.setEditable(false);
        txtTongCPPS.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jPanel1.add(txtTongCPPS, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 730, 200, 35));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText("Tổng chi phí phát sinh");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 690, -1, -1));

        tblThucDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Tên món", "Giá", "Ghi chú", "Phát sinh", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, false
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

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 140, 340, 480));
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 90, -1, -1));

        taGhiChu.setColumns(20);
        taGhiChu.setRows(4);
        jScrollPane1.setViewportView(taGhiChu);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 700, 390, 90));

        lblMaNH8.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMaNH8.setText("Ghi chú");
        jPanel1.add(lblMaNH8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 650, 100, 40));

        jLabel2.setText("Số bàn chính");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 80, 80, 30));

        spnSoBanChinh.setUI(new BasicSpinnerUI());
        spnSoBanChinh.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        spnSoBanChinh.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        spnSoBanChinh.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnSoBanChinhStateChanged(evt);
            }
        });
        jPanel1.add(spnSoBanChinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 84, 60, 30));

        jLabel3.setText("Bàn phụ");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 80, 50, 30));

        lblBanPhu.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblBanPhu.setText("0");
        jPanel1.add(lblBanPhu, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 80, 40, 30));
        jPanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 340, -1, -1));

        btnSave1.setBackground(new java.awt.Color(24, 153, 29));
        btnSave1.setForeground(new java.awt.Color(255, 255, 255));
        btnSave1.setText("Lưu");
        btnSave1.setBorderColor(new java.awt.Color(24, 153, 29));
        btnSave1.setColor(new java.awt.Color(24, 153, 29));
        btnSave1.setColorClick(new java.awt.Color(0, 204, 0));
        btnSave1.setColorOver(new java.awt.Color(0, 204, 0));
        btnSave1.setFocusPainted(false);
        btnSave1.setLabelColor(java.awt.Color.white);
        btnSave1.setLableColorClick(java.awt.Color.white);
        btnSave1.setRadius(15);
        btnSave1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnSave1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 790, -1, 30));

        tblThucDonPhu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Tên món", "Giá", "Ghi chú", "Phát sinh", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblThucDonPhu.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        tblThucDonPhu.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tblThucDonPhuFocusLost(evt);
            }
        });
        tblThucDonPhu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThucDonPhuMouseClicked(evt);
            }
        });
        tblThucDonPhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tblThucDonPhuKeyTyped(evt);
            }
        });
        jScrollPane4.setViewportView(tblThucDonPhu);

        jPanel1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 340, 480));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 850));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cbbThucDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbThucDonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbThucDonActionPerformed

    private void cbbLoaiMonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbLoaiMonItemStateChanged
//        if (isLoad && cbbLoaiMon.getSelectedIndex() != -1) {
//            PhanLoaiMonAn pl = (PhanLoaiMonAn) cbbLoaiMon.getSelectedItem();
//            List<MonAn> list = new ArrayList<>();
//            String txtSearch = this.txtSearch.getText();
//            for (MonAn ma : listFilted) {
//                if (ma.getMaPL().equals(pl.getMaPL()) && ma.getSearchInfo().contains(txtSearch)) {
//                    boolean isExit = false;
//                    for (MonAn m : listMonAnBk) {
//                        if (ma.getMaMA().equals(m.getMaMA())) {
//                            isExit = true;
//                            break;
//                        }
//                    }
//                    if (!isExit) {
//                        list.add(ma);
//                    }
//                }
//            }
//            listFilted = list;
//            filtedMonAn();
//            fillTableMonAn(list);
//        }
        filtedMonAn();
    }//GEN-LAST:event_cbbLoaiMonItemStateChanged

    private void cbbLoaiMonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLoaiMonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbLoaiMonActionPerformed

    private void lblSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSearchMouseClicked

    }//GEN-LAST:event_lblSearchMouseClicked

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        filtedMonAn();
    }//GEN-LAST:event_txtSearchKeyReleased

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if (cbbThucDon.getSelectedIndex() == -1) {
            DialogHelper.alertError(this, "Vui lòng chọn thực đơn");
            return;
        }

        if (listMonAnBanPhu.isEmpty() && !lblBanPhu.getText().equals("0")) {
            DialogHelper.alertError(this, "Không có bàn phụ. Hãy nâng bàn chính");
        }

        if (listChiTietDatMon.size() < 6) {
            boolean rs = DialogHelper.confirm(this, "Thực đơn nên có ít nhất 6 món. Xác nhận lưu ?");
            if (!rs) {
                return;
            }
        }

        if (listChiTietDatMon.size() > 12) {
            boolean rs = DialogHelper.confirm(this, "Thực đơn chỉ nên có 12 món. Xác nhận lưu ?");
            if (!rs) {
                return;
            }
        }

        if (chiTietDatMonDAO.selectDichVuDatMon(maHD) == null) {
            insertDichVuDatMon();
            insertChiTietDatMon();
        } else {
            updateDichVuDatMon();
            updateChiTietDichVu();
        }

        AppStatus.lapHopDong.reloadHopDong();
        this.dispose();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void cbbThucDonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbThucDonItemStateChanged
        if (isLoad && cbbThucDon.getSelectedIndex() != -1) {
            listChiTietDatMon = chiTietDatMonDAO.selectByMaThucDon(((ThucDon) cbbThucDon.getSelectedItem()).getMaTD());
            fillTableThucDon(listChiTietDatMon);

            tinhTien();
            filtedMonAn();
        }
    }//GEN-LAST:event_cbbThucDonItemStateChanged

    private void tblThucDonKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblThucDonKeyTyped

    }//GEN-LAST:event_tblThucDonKeyTyped

    private void tblThucDonFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tblThucDonFocusLost

        //      tblThucDon.getCellEditor().stopCellEditing();
    }//GEN-LAST:event_tblThucDonFocusLost

    private void tblMonAnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMonAnMouseClicked
        int index = tblMonAn.getSelectedRow();
        if (index >= 0) {
            if (evt.getClickCount() >= 2) {
                MonAn ma = listFilted.get(index);

                ChiTietDatMon ct = new ChiTietDatMon();
                ct.setMaHD(maHD);
                ct.setMaMA(ma.getMaMA());
                ct.setTenMA(ma.getTenMA());
                ct.setGia(ma.getGiaTien());
                ct.setSoLuong(soLuongBan);

                int thuTu = listChiTietDatMon.get(listChiTietDatMon.size() - 1).getThuTu() + 1;

                ct.setThuTu(thuTu);

                listChiTietDatMon.add(ct);
                autoSetSoLuong();
                ct.getSoLuong();

                fillTableThucDon(listChiTietDatMon);

                autoSetThuTu();
                filtedMonAn();

                tinhTien();
            }
        }

    }//GEN-LAST:event_tblMonAnMouseClicked

    private void tblThucDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThucDonMouseClicked
        int index = tblThucDon.getSelectedRow();
        if (index >= 0) {
            if (evt.getClickCount() >= 2) {

                ChiTietDatMon ct = listChiTietDatMon.get(index);
                for (MonAn m : listMonAnBk) {
                    if (ct.getMaMA().equals(m.getMaMA())) {
                        listChiTietDatMon.remove(ct);
                        break;
                    }
                }

                fillTableThucDon(listChiTietDatMon);

                filtedMonAn();
                autoSetThuTu();
                tinhTien();
            } else if (SwingUtilities.isRightMouseButton(evt)) {
                int soBanPhu = Integer.parseInt(lblBanPhu.getText());
                if (soBanPhu == 0) {
                    DialogHelper.alertError(this, "Không có bàn phụ");
                    return;
                }
                ChiTietDatMon ct = listChiTietDatMon.get(tblThucDon.getSelectedRow());

                if (ct.getSoLuong() == -1) {
                    boolean isPhu = false;
                    for (ChiTietDatMon c : listMonAnBanPhu){
                        if (c.getMaMA().equals(ct.getMaMA())){
                            isPhu = true;
                        }
                    }
                    if (isPhu) {
                        boolean rs = DialogHelper.confirm(this, "Chuyển qua bàn chính ?");
                        if (rs) {
                            listMonAnBanPhu.remove(ct);
                            listChiTietDatMon.add(ct);
                            tblThucDon.removeRowColor(tblThucDon.getSelectedRow());
                            autoSetSoLuong();

                            tinhTien();
                        }
                    } else {
                        boolean rs = DialogHelper.confirm(this, "Chuyển qua bàn phụ ?");
                        if (rs) {
                            listMonAnBanPhu.add(ct);
                            tblThucDon.addRowColor(tblThucDon.getSelectedRow());
                            autoSetSoLuong();

                            tinhTien();
                        }
                    }
                    return;
                }

                if (ct.getSoLuong() == soBanPhu) {
                    boolean rs = DialogHelper.confirm(this, "Chuyển qua bàn chính ?");
                    if (rs) {
                        listMonAnBanPhu.remove(ct);
                        listChiTietDatMon.add(ct);
                        tblThucDon.removeRowColor(tblThucDon.getSelectedRow());
                        autoSetSoLuong();
                        fillTableThucDonPhu(listMonAnBanPhu);
                        tinhTien();
                    }
                } else {
                    boolean rs = DialogHelper.confirm(this, "Chuyển qua bàn phụ ?");
                    if (rs) {
                        listMonAnBanPhu.add(ct);
                        tblThucDon.addRowColor(tblThucDon.getSelectedRow());
                        autoSetSoLuong();
fillTableThucDonPhu(listMonAnBanPhu);
                        tinhTien();
                    }
                }

            }
        }
    }//GEN-LAST:event_tblThucDonMouseClicked

    private void spnSoBanChinhStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnSoBanChinhStateChanged
        if (isLoad) {
            int soBanChinh = Integer.parseInt(spnSoBanChinh.getValue().toString());
            int soBanPhu = soLuongBan - soBanChinh;
            if (soBanPhu != Integer.parseInt(lblBanPhu.getText())) {
                lblBanPhu.setText(soBanPhu + "");
                autoSetSoLuong();
                tinhTien();
            } else {
                spnSoBanChinh.setValue(soBanChinh + 1);
            }
        }
    }//GEN-LAST:event_spnSoBanChinhStateChanged

    private void txtSearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchKeyTyped

    private void btnSave1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave1ActionPerformed
        int soBanPhu = Integer.parseInt(lblBanPhu.getText());
        if (soBanPhu == 0) {
            DialogHelper.alertError(this, "Không có bàn phụ");
            return;
        }
        ChiTietDatMon ct = listChiTietDatMon.get(tblThucDon.getSelectedRow());
        if (ct.getSoLuong() == soBanPhu) {
            boolean rs = DialogHelper.confirm(this, "Chuyển qua bàn chính ?");
            if (rs) {
                listMonAnBanPhu.remove(ct);
                listChiTietDatMon.add(ct);
                tblThucDon.removeRowColor(tblThucDon.getSelectedRow());
                autoSetSoLuong();

                tinhTien();
            }
        } else {
            boolean rs = DialogHelper.confirm(this, "Chuyển qua bàn phụ ?");
            if (rs) {
                listMonAnBanPhu.add(ct);
                tblThucDon.addRowColor(tblThucDon.getSelectedRow());
                autoSetSoLuong();

                tinhTien();
            }
        }
    }//GEN-LAST:event_btnSave1ActionPerformed

    private void tblThucDonPhuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tblThucDonPhuFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_tblThucDonPhuFocusLost

    private void tblThucDonPhuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThucDonPhuMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblThucDonPhuMouseClicked

    private void tblThucDonPhuKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblThucDonPhuKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tblThucDonPhuKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ui.swing.InkwellButton btnBack;
    private com.ui.swing.InkwellButton btnNext;
    private com.ui.swing.HoverButton btnReset;
    private com.ui.swing.HoverButton btnSave;
    private com.ui.swing.HoverButton btnSave1;
    private com.ui.swing.Combobox cbbLoaiMon;
    private com.ui.swing.Combobox cbbThucDon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblBanPhu;
    private javax.swing.JLabel lblMaNH18;
    private javax.swing.JLabel lblMaNH8;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JSpinner spnSoBanChinh;
    private javax.swing.JTextArea taGhiChu;
    private com.ui.swing.Table tblMonAn;
    private com.ui.swing.Table tblThucDon;
    private com.ui.swing.Table tblThucDonPhu;
    private javax.swing.JTextField txtChiPhi;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTongCPPS;
    private javax.swing.JTextField txtTongChiPhi;
    // End of variables declaration//GEN-END:variables
}