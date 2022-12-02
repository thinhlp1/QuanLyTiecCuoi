/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happywedding.view.manage;

import com.happywedding.dao.ChiTietDichVuDiKemDAO;
import com.happywedding.dao.DichVuDiKemDAO;
import com.happywedding.helper.AppStatus;
import com.happywedding.helper.DialogHelper;
import com.happywedding.helper.ShareHelper;
import com.happywedding.model.ChiTietDatMon;
import com.happywedding.model.ChiTietDichVuDiKem;
import com.happywedding.model.DichVuDiKemModel;
import com.happywedding.model.HopDongDichVuDiKem;
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
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author ADMIN
 */
public class DichVuDiKem extends javax.swing.JDialog {

    private String maHD;
    private boolean isCreate;
    private boolean isLoad = false;

    private DefaultTableModel tblChiTietDichVuModel;
    private DefaultTableModel tblDichVuModel;
    private List<ChiTietDichVuDiKem> listChiTietDichVu = new ArrayList<>();
    private List<DichVuDiKemModel> listDichVuDiKem = new ArrayList<>();
    private List<DichVuDiKemModel> listFilted = new ArrayList<>();

    private ChiTietDichVuDiKemDAO ctdvDAO = new ChiTietDichVuDiKemDAO();
    private DichVuDiKemDAO dvdkDAO = new DichVuDiKemDAO();

    private HopDongDichVuDiKem hopDongDichVuDiKem;

    private String maPhao = "PHAOKIMTUYEN"; // ngoại lệ là do xác định giá sau đãi tiệc

    /**
     * Creates new form DichVuDiKem
     */
    public DichVuDiKem(java.awt.Frame parent, boolean modal, String maHD) {
        super(parent, modal);
        this.maHD = maHD;
        this.isCreate = modal;
        initComponents();
        init();
    }

    public void init() {
        tblChiTietDichVu.fixTable(jScrollPane2);
        tblDichVuDiKem.fixTable(jScrollPane3);
        tblChiTietDichVuModel = (DefaultTableModel) tblChiTietDichVu.getModel();
        tblDichVuModel = (DefaultTableModel) tblDichVuDiKem.getModel();

        TableColumnModel columnModel = tblChiTietDichVu.getColumnModel();
        columnModel.getColumn(4).setPreferredWidth(80);
        columnModel.getColumn(4).setMaxWidth(80);

        ((AbstractTableModel) tblChiTietDichVu.getModel()).fireTableDataChanged();
        tblChiTietDichVu.addKeyListener(new MyTableCellEditor(), 3);
        tblChiTietDichVu.addKeyListener(new MyTableCellEditor(), 4);
        // tblThucDonModel.fireTableDataChanged();
        tblChiTietDichVuModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                String editingCell = "";

                int col = tblChiTietDichVu.getEditingColumn();
                int row = tblChiTietDichVu.getEditingRow();

                if (col > -1 && row > -1) {
                    if (col == 3) {
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
                            tblChiTietDichVu.setValueAt(ShareHelper.toMoney(ShareHelper.toMoney(editingCell)), row, col);

                        }

                    }

                }
            }

        });
        loadDichVu();
        loadChiTietDichVu();
        isLoad = true;

        hopDongDichVuDiKem = ctdvDAO.selectHopDongDichVuDiKem(maHD);

        fillFomrDichVuDiKem();
        fillTableChiTietDichVu(listChiTietDichVu);
        fillTableDichVuDiKem(listDichVuDiKem);
        filtedDichVuDiKem();

        isView(isCreate);
    }

    public void isView(boolean isCreate) {

        btnSave.setVisible(isCreate);
        btnReset.setVisible(isCreate);
        taGhiChu.setEnabled(isCreate);
        txtSearch.setEditable(isCreate);
        tblChiTietDichVu.setEnabled(isCreate);
        tblDichVuDiKem.setEnabled(isCreate);
    }

    public void loadChiTietDichVu() {
        listChiTietDichVu = ctdvDAO.selectChiTietDichVuDiKem(maHD);
    }

    public void loadDichVu() {
        listDichVuDiKem = dvdkDAO.select();
    }

    public List<ChiTietDichVuDiKem> getChiTietDichVu() {
        List<ChiTietDichVuDiKem> list = new ArrayList<>();

        int size = tblChiTietDichVu.getRowCount();
        for (int i = 0; i < size; i++) {
            ChiTietDichVuDiKem ct = listChiTietDichVu.get(i);
            ct.setMaHD(maHD);
            ct.setGhiChu((String) tblChiTietDichVu.getValueAt(i, 2));
            ct.setChiPhiPhatSinh(ShareHelper.toMoney((String) tblChiTietDichVu.getValueAt(i, 3)));
            if (ct.getMaDV().equals(maPhao)) {
                ct.setSoLuong(-1);
            } else {
                ct.setSoLuong(Integer.parseInt(tblChiTietDichVu.getValueAt(i, 4).toString()));
            }
            list.add(ct);
        }

        return list;

    }

    public HopDongDichVuDiKem getDichVuDatMon() {
        HopDongDichVuDiKem dvdk = new HopDongDichVuDiKem();
        dvdk.setMaHD(maHD);
        dvdk.setChiPhi(ShareHelper.toMoney(txtChiPhi.getText()));
        dvdk.setGhiChu(taGhiChu.getText());
        return dvdk;
    }

    public boolean insertDichVuDiKem() {
        HopDongDichVuDiKem dv = getDichVuDatMon();
        try {
            ctdvDAO.insertHopDongDichVuDiKem(dv);
            return true;
        } catch (Exception e) {
            DialogHelper.alertError(this, "Lưu không thành công");
            return false;
        }
    }

    public boolean insertChiTietDichVuDiKem() {
        List<ChiTietDichVuDiKem> list = getChiTietDichVu();

        try {
            for (ChiTietDichVuDiKem ct : list) {
                ctdvDAO.insertChiTietDichVuDiKem(ct);
            }
            return true;
        } catch (Exception e) {
            DialogHelper.alertError(this, "Lưu không thành công");
            return false;
        }
    }

    public boolean updateDichVuDiKem() {
        HopDongDichVuDiKem dv = getDichVuDatMon();
        try {
            ctdvDAO.updateHopDongDichVuDiKem(dv);
            return true;
        } catch (Exception e) {
            DialogHelper.alertError(this, "Lưu không thành công");
            return false;
        }
    }

    public boolean updateChiTietDichVuDiKem() {
        try {
            ctdvDAO.removeAllChiTietDichVuDiKem(maHD);
            insertChiTietDichVuDiKem();

            return true;
        } catch (Exception e) {
            DialogHelper.alertError(this, "Lưu không thành công");
            return false;
        }
    }

    public void fillFomrDichVuDiKem() {
        if (hopDongDichVuDiKem != null) {
            taGhiChu.setText(hopDongDichVuDiKem.getGhiChu());
            txtChiPhi.setText(ShareHelper.toMoney(hopDongDichVuDiKem.getChiPhi()));
            txtTongCPPS.setText(ShareHelper.toMoney(hopDongDichVuDiKem.getChiPhiPhatSinh()));
            txtTongChiPhi.setText(ShareHelper.toMoney(hopDongDichVuDiKem.getChiPhi() + hopDongDichVuDiKem.getChiPhiPhatSinh()));
        }

    }

    public void fillTableChiTietDichVu(List<ChiTietDichVuDiKem> list) {

        tblChiTietDichVuModel.setRowCount(0);

        for (ChiTietDichVuDiKem ct : list) {
            Object[] row = {
                ct.getTenDV(),
                ShareHelper.toMoney(ct.getChiPhi()),
                ct.getGhiChu(),
                ShareHelper.toMoney(ct.getChiPhiPhatSinh()),
                (ct.getSoLuong() == -1) ? "CXD" : ct.getSoLuong()
            };
            tblChiTietDichVuModel.addRow(row);
        }
    }

    public void fillTableDichVuDiKem(List<DichVuDiKemModel> list) {
        tblDichVuModel.setRowCount(0);
        for (DichVuDiKemModel dv : list) {
            Object[] row = {
                dv.getTenDV(),
                ShareHelper.toMoney(dv.getGia())};
            tblDichVuModel.addRow(row);
        }
    }

    public void filtedDichVuDiKem() {
        listFilted.clear();

        for (DichVuDiKemModel dvdk : listDichVuDiKem) {
            boolean isExit = false;
            for (ChiTietDichVuDiKem ct : listChiTietDichVu) {

                if (ct.getMaDV().equals(dvdk.getMaDV())) {
                    isExit = true;
                }
            }
            if (!isExit) {
                if (dvdk.getInfo().contains(txtSearch.getText().trim())) {
                    listFilted.add(dvdk);
                }
            }
        }

        fillTableDichVuDiKem(listFilted);
    }

    public void tinhTien() {
        long chiPhi = 0;
        long chiPhiPhatSinh = 0;
        long tongChiPhi = 0;

        listChiTietDichVu = getChiTietDichVu();

        for (ChiTietDichVuDiKem ct : listChiTietDichVu) {
            if (ct.getSoLuong() == -1) {
                continue;
            }
            chiPhi += ct.getChiPhi();
            chiPhiPhatSinh += ct.getChiPhiPhatSinh();
        }

        tongChiPhi = (chiPhi + chiPhiPhatSinh);

        txtChiPhi.setText(ShareHelper.toMoney(chiPhi));
        // txtChiPhi.setToolTipText(chiPhi + " x " + soLuongBan + "bàn");
        txtTongCPPS.setText(ShareHelper.toMoney(chiPhiPhatSinh));
        //txtTongCPPS.setToolTipText(chiPhiPhatSinh + " x " + soLuongBan + "bàn");
        txtTongChiPhi.setText(ShareHelper.toMoney(tongChiPhi));

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
                    lblMaNH8.requestFocus();
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
        jScrollPane2 = new javax.swing.JScrollPane();
        tblChiTietDichVu = new com.ui.swing.Table();
        lblMaNH8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taGhiChu = new javax.swing.JTextArea();
        btnSave = new com.ui.swing.HoverButton();
        btnReset = new com.ui.swing.HoverButton();
        jLabel5 = new javax.swing.JLabel();
        lblSearch = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDichVuDiKem = new com.ui.swing.Table();
        jLabel9 = new javax.swing.JLabel();
        txtChiPhi = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTongChiPhi = new javax.swing.JTextField();
        txtTongCPPS = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btnBack = new com.ui.swing.InkwellButton();
        btnNext = new com.ui.swing.InkwellButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblChiTietDichVu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "Dịch vụ", "Chi phí", "Ghi chú", "Chi phí phát sinh", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblChiTietDichVu.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        tblChiTietDichVu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChiTietDichVuMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblChiTietDichVu);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 690, 540));

        lblMaNH8.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMaNH8.setText("Ghi chú");
        jPanel1.add(lblMaNH8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 610, 100, 30));

        taGhiChu.setColumns(20);
        taGhiChu.setRows(5);
        jScrollPane1.setViewportView(taGhiChu);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 640, 390, 100));

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
        jPanel1.add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 740, -1, 30));

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
        jPanel1.add(btnReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 740, -1, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Dịch vụ đi kèm");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, 30));

        lblSearch.setBackground(new java.awt.Color(204, 204, 255));
        lblSearch.setForeground(new java.awt.Color(102, 0, 255));
        lblSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/happywedding/assets/Search.png"))); // NOI18N
        lblSearch.setAutoscrolls(true);
        lblSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSearchMouseClicked(evt);
            }
        });
        jPanel1.add(lblSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 20, -1, 33));

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
        });
        jPanel1.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 10, 280, 35));

        tblDichVuDiKem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "Dịch vụ", "Chi phí"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDichVuDiKem.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        tblDichVuDiKem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDichVuDiKemMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblDichVuDiKem);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 70, 340, 520));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("Tổng chi phí");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 630, 170, -1));

        txtChiPhi.setEditable(false);
        txtChiPhi.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtChiPhi.setText("0");
        txtChiPhi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtChiPhiActionPerformed(evt);
            }
        });
        jPanel1.add(txtChiPhi, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 670, 190, 35));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Tổng chi phí phải trả");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 630, 200, -1));

        txtTongChiPhi.setEditable(false);
        txtTongChiPhi.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTongChiPhi.setText("0");
        jPanel1.add(txtTongChiPhi, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 670, 240, 35));

        txtTongCPPS.setEditable(false);
        txtTongCPPS.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTongCPPS.setText("0");
        jPanel1.add(txtTongCPPS, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 670, 220, 35));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText("Tổng chi phí phát sinh");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 630, 210, -1));

        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/happywedding/assets/back.png"))); // NOI18N
        btnBack.setFocusPainted(false);
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        jPanel1.add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 310, 50, 50));

        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/happywedding/assets/next.png"))); // NOI18N
        btnNext.setFocusPainted(false);
        jPanel1.add(btnNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 310, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1230, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 788, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setSize(new java.awt.Dimension(1248, 835));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSearchMouseClicked

    }//GEN-LAST:event_lblSearchMouseClicked

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        filtedDichVuDiKem();
    }//GEN-LAST:event_txtSearchKeyReleased

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        
        if (tblChiTietDichVu.getRowCount() == 0){
            boolean rs = DialogHelper.confirm(this, "Xác nhận không chọn thêm dịch vụ");
            if (!rs){
                return;
            }
        }
        
        if (ctdvDAO.selectHopDongDichVuDiKem(maHD) == null) {
            insertDichVuDiKem();
            insertChiTietDichVuDiKem();
        } else {
            updateDichVuDiKem();
            updateChiTietDichVuDiKem();
        }
        AppStatus.lapHopDong.checkedDichVu("DICHVUDIKEM ", true);
        AppStatus.lapHopDong.reloadHopDong();
        this.dispose();

        AppStatus.lapHopDong.reloadHopDong();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void txtChiPhiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtChiPhiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtChiPhiActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        tblChiTietDichVuModel.setRowCount(0);
        fillTableDichVuDiKem(listDichVuDiKem);
        txtSearch.setText("");
        tinhTien();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBackActionPerformed

    private void tblDichVuDiKemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDichVuDiKemMouseClicked
        int index = tblDichVuDiKem.getSelectedRow();
        if (index >= 0) {
            if (evt.getClickCount() == 2) {
                DichVuDiKemModel dvdk = listFilted.get(index);
                ChiTietDichVuDiKem ct = new ChiTietDichVuDiKem();
                ct.setMaHD(maHD);
                ct.setChiPhi(dvdk.getGia());
                ct.setGhiChu("");
                ct.setMaDV(dvdk.getMaDV());
                ct.setTenDV(dvdk.getTenDV());
                if (dvdk.getMaDV().equals(maPhao)) {
                    ct.setSoLuong(-1);
                } else {
                    ct.setSoLuong(1);
                }
                listChiTietDichVu.add(ct);
                fillTableChiTietDichVu(listChiTietDichVu);
                filtedDichVuDiKem();
                tinhTien();
            }
        }
    }//GEN-LAST:event_tblDichVuDiKemMouseClicked

    private void tblChiTietDichVuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChiTietDichVuMouseClicked
        int index = tblChiTietDichVu.getSelectedRow();
        if (index >= 0) {

            if (evt.getClickCount() == 2) {
                listChiTietDichVu.remove(index);
                fillTableChiTietDichVu(listChiTietDichVu);
                filtedDichVuDiKem();
                tinhTien();
            }
        }
    }//GEN-LAST:event_tblChiTietDichVuMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ui.swing.InkwellButton btnBack;
    private com.ui.swing.InkwellButton btnNext;
    private com.ui.swing.HoverButton btnReset;
    private com.ui.swing.HoverButton btnSave;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblMaNH8;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JTextArea taGhiChu;
    private com.ui.swing.Table tblChiTietDichVu;
    private com.ui.swing.Table tblDichVuDiKem;
    private javax.swing.JTextField txtChiPhi;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTongCPPS;
    private javax.swing.JTextField txtTongChiPhi;
    // End of variables declaration//GEN-END:variables
}
