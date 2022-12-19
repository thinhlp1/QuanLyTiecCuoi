/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happywedding.view.manage;

import com.happywedding.dao.HopDongDAO;
import com.happywedding.dao.SanhDAO;
import com.happywedding.dao.TrangThaiHopDongDAO;
import com.happywedding.helper.AppStatus;
import com.happywedding.helper.DateHelper;
import com.happywedding.helper.DialogHelper;
import com.happywedding.model.HopDong;
import com.happywedding.model.KhachHang;
import com.happywedding.model.Sanh;
import com.happywedding.model.TrangThaiHopDong;
import com.ui.swing.Table;
import com.ui.swing.datechooser.DateChooser;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class QuanLyHopDong extends javax.swing.JPanel {

    private DefaultTableModel tblModel;
    private int currentIndex = 0;
    private DateChooser dtChooser1 = new DateChooser();
    private DateChooser dtChooser2 = new DateChooser();

    private List<Sanh> listSanh = new ArrayList<>();
    private List<HopDong> listHopDong = new ArrayList<>();
    private List<HopDong> listFilted = new ArrayList<>();
    private HopDongDAO hopDongDAO = new HopDongDAO();
    private SanhDAO sanhDAO = new SanhDAO();

    private List<TrangThaiHopDong> listTrangThai = new ArrayList<>();

    private boolean isLoad = false;

    static class StatusHopDong {

        static String DANGLAP = "DANGLAP";
        static String CHODUYET = "CHODUYET";
        static String CHOKYKET = "CHOKYKET";
        static String THUCHIEN = "THUCHIEN";
        static String DATHUCHIEN = "DATHUCHIEN";
        static String XOA = "XOA";
    }

    /**
     * Creates new form QuanLyHopDong
     */
    public QuanLyHopDong() {
        initComponents();
        init();

    }

    public void init() {
        tblHopDong.fixTable(jScrollPane2);
        tblModel = (DefaultTableModel) tblHopDong.getModel();
        tblHopDong.setAutoscrolls(true);
        dtChooser1.setTextRefernce(txtNgayBatDau);
        dtChooser2.setTextRefernce(txtNgayKetThuc);

        loadSanh();
        loadTrangThai();

        isLoad = true;

        txtNgayBatDau.setText("");
        txtNgayKetThuc.setText("");
        listHopDong = hopDongDAO.select();
        for (HopDong hd : listHopDong) {
            listFilted.add(hd);
        }
        fillToTable(listHopDong);
        initSort();
        cbbSortBy.setSelectedIndex(3);
       cbbSortBy.setSelectedIndex(-1);
        AppStatus.QLHOPDONG = this;

    }

    public void fillToTable(List<HopDong> listHopDong) {
        tblModel.setRowCount(0);
        tblHopDong.resetRowChoDuyetColor();
        tblHopDong.resetRowSapHetHanColor();
        tblHopDong.resetRowChoKyKetColor();
        try {
            int i = 0;
            for (HopDong hd : listHopDong) {
                Object[] row = {
                    hd.getMaHD(),
                    hd.getTenNguoiLap(),
                    hd.getSoLuongBan(),
                    hd.getSanh(),
                    DateHelper.toString(hd.getNgayLap(), "dd/MM/yyyy"),
                    hd.getTenKhachHang(),
                    DateHelper.toString(hd.getNgayToChuc(), "dd/MM/yyyy"),
                    hd.getThoiGianBatDau().substring(0, 5),
                    hd.getThoiGianKetThuc().substring(0, 5),
                    hd.getTenTrangThai()
                };
                tblModel.addRow(row);
                if (hd.getTrangThai().equals(StatusHopDong.CHODUYET)) {
                    tblHopDong.addRowChoDuyetColor(i);
                    if ((hd.getNgayToChuc().getTime() - new Date().getTime() < 3 * 24 * 3600000) && (hd.getNgayToChuc().getTime() - new Date().getTime()) > 0) {
                        tblHopDong.addRowSapHetHanColor(i);
                    }
                } else if (hd.getTrangThai().equals(StatusHopDong.CHOKYKET)) {
                    tblHopDong.addRowChoKyKettColor(i);
                    if ((hd.getNgayToChuc().getTime() - new Date().getTime() < 3 * 24 * 3600000) && (hd.getNgayToChuc().getTime() - new Date().getTime()) > 0) {
                        tblHopDong.addRowSapHetHanColor(i);
                    }
                }
                i++;
            }
        } catch (Exception e) {
            DialogHelper.alertError(this, "Có lỗi xảy ra khi load dữ liệu.!");
        }

    }

    public void loadSanh() {
        listSanh = sanhDAO.select();
        DefaultComboBoxModel cbbModel = (DefaultComboBoxModel) cbbSanh.getModel();
        cbbModel.removeAllElements();
        cbbModel.addElement("Tất cả");
        for (Sanh ss : listSanh) {
            cbbModel.addElement(ss);
        }
        cbbSanh.setSelectedIndex(0);
    }

    public void loadTrangThai() {
        listTrangThai = new TrangThaiHopDongDAO().select();
        DefaultComboBoxModel cbbModel = (DefaultComboBoxModel) cbbTrangThai.getModel();
        cbbModel.removeAllElements();
        cbbModel.addElement("Tất cả");
        for (TrangThaiHopDong ss : listTrangThai) {
            cbbModel.addElement(ss);
        }
     
        cbbSanh.setSelectedIndex(0);
    }

    public void reload() {
        listHopDong = hopDongDAO.select();
        filtedHopDong();
    }

    public void filtedHopDong() {
        List<HopDong> list = new ArrayList<>();
        List<HopDong> list2 = new ArrayList<>();
        List<HopDong> list3 = new ArrayList<>();
        listFilted.clear();
        String search = txtSearch.getText().trim().replaceAll("\\s+", " ");
        for (HopDong hd : listHopDong) {
            if (hd.getInfoSearch().toUpperCase().contains(search.toUpperCase())) {
                list.add(hd);
            }
        }

        for (HopDong hd : list) {
            if (cbbTrangThai.getSelectedIndex() == 0) {
                list2.add(hd);

                continue;
            }
            if (hd.getTrangThai().equals(((TrangThaiHopDong) cbbTrangThai.getSelectedItem()).getMaTT())) {
                list2.add(hd);
            }
        }

        for (HopDong hd : list2) {
            if (cbbSanh.getSelectedIndex() == 0) {
                list3.add(hd);
                continue;
            }

            if (hd.getSanh().equals(((Sanh) cbbSanh.getSelectedItem()).getTenSanh())) {
                list3.add(hd);
            }
        }

        for (HopDong hd : list3) {
            if (cbbNgay.getSelectedIndex() == 0) {
                if (txtNgayBatDau.getText().length() != 0 && txtNgayKetThuc.getText().length() == 0) {

                    if (DateHelper.toString(hd.getNgayToChuc(), "dd/MM/yyyy").equals(txtNgayBatDau.getText())
                            || hd.getNgayToChuc().after(DateHelper.toDate(txtNgayBatDau.getText(), "dd/MM/yyyy"))) {
                        listFilted.add(hd);
                    }

                } else if (txtNgayBatDau.getText().length() == 0 && txtNgayKetThuc.getText().length() != 0) {
                    if (DateHelper.toString(hd.getNgayToChuc(), "dd/MM/yyyy").equals(txtNgayKetThuc.getText())
                            || hd.getNgayToChuc().before(DateHelper.toDate(txtNgayKetThuc.getText(), "dd/MM/yyyy"))) {
                        listFilted.add(hd);
                    }
                } else if (txtNgayBatDau.getText().length() != 0 && txtNgayKetThuc.getText().length() != 0) {
                    if (DateHelper.toString(hd.getNgayToChuc(), "dd/MM/yyyy").equals(txtNgayKetThuc.getText())
                            || hd.getNgayToChuc().before(DateHelper.toDate(txtNgayKetThuc.getText(), "dd/MM/yyyy")
                            )) {
                        if (DateHelper.toString(hd.getNgayToChuc(), "dd/MM/yyyy").equals(txtNgayBatDau.getText())
                                || hd.getNgayToChuc().after(DateHelper.toDate(txtNgayBatDau.getText(), "dd/MM/yyyy"))) {
                            listFilted.add(hd);
                        }
                    }
                } else {
                    listFilted.add(hd);
                }
            } else if (cbbNgay.getSelectedIndex() == 1) {
                if (txtNgayBatDau.getText().length() != 0 && txtNgayKetThuc.getText().length() == 0) {

                    if (DateHelper.toString(hd.getNgayLap(), "dd/MM/yyyy").equals(txtNgayBatDau.getText())
                            || hd.getNgayLap().after(DateHelper.toDate(txtNgayBatDau.getText(), "dd/MM/yyyy"))) {
                        listFilted.add(hd);
                    }

                } else if (txtNgayBatDau.getText().length() == 0 && txtNgayKetThuc.getText().length() != 0) {
                    if (DateHelper.toString(hd.getNgayLap(), "dd/MM/yyyy").equals(txtNgayKetThuc.getText())
                            || hd.getNgayLap().before(DateHelper.toDate(txtNgayKetThuc.getText(), "dd/MM/yyyy"))) {
                        listFilted.add(hd);
                    }
                } else if (txtNgayBatDau.getText().length() != 0 && txtNgayKetThuc.getText().length() != 0) {
                    if (DateHelper.toString(hd.getNgayLap(), "dd/MM/yyyy").equals(txtNgayKetThuc.getText())
                            || hd.getNgayLap().before(DateHelper.toDate(txtNgayKetThuc.getText(), "dd/MM/yyyy")
                            )) {
                        if (DateHelper.toString(hd.getNgayLap(), "dd/MM/yyyy").equals(txtNgayBatDau.getText())
                                || hd.getNgayLap().after(DateHelper.toDate(txtNgayBatDau.getText(), "dd/MM/yyyy"))) {
                            listFilted.add(hd);
                        }
                    }
                } else {
                    listFilted.add(hd);
                }
            }

        }
        fillToTable(listFilted);
        int oldIndex = cbbSortBy.getSelectedIndex();
        if (oldIndex == 3){
            oldIndex = -1;
        }
        cbbSortBy.setSelectedIndex(-1);
        cbbSortBy.setSelectedIndex(oldIndex);
    }

    public void showCalendar1() {
        dtChooser1.showPopup();
    }

    public void showCalendar2() {
        dtChooser2.showPopup();
    }

    public void initSort() {

        cbbSortBy.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (cbbSortBy.getSelectedIndex() == 0) {
                    if (cbbSort.getSelectedIndex() == 0) {
                        // ten tang dan
                        fillToTable(sortByNameKhachHang(false));
                    } else if (cbbSort.getSelectedIndex() == 1) {
                        // ten giam dan
                        fillToTable(sortByNameKhachHang(true));
                    }
                } else if (cbbSortBy.getSelectedIndex() == 1) {
                    if (cbbSort.getSelectedIndex() == 0) {
                        // ten tang dan
                        fillToTable(sortByNgayToChuc(false));
                    } else if (cbbSort.getSelectedIndex() == 1) {
                        // ten giam dan
                        fillToTable(sortByNgayToChuc(true));
                    }
                } else if (cbbSortBy.getSelectedIndex() == 2) {
                    if (cbbSort.getSelectedIndex() == 0) {
                        // ten tang dan
                        fillToTable(sortByNgayLap(false));
                    } else if (cbbSort.getSelectedIndex() == 1) {
                        // ten giam dan
                        fillToTable(sortByNgayLap(true));
                    } else {
                        cbbSortBy.setSelectedIndex(0);
                    }
                }else if (cbbSortBy.getSelectedIndex() == 3) {
                    sortTheoCapDo();
                }
            }
        });

        cbbSort.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (cbbSortBy.getSelectedIndex() == 0) {
                    if (cbbSort.getSelectedIndex() == 0) {
                        // ten tang dan
                        fillToTable(sortByNameKhachHang(false));
                    } else if (cbbSort.getSelectedIndex() == 1) {
                        // ten giam dan
                        fillToTable(sortByNameKhachHang(true));
                    }
                } else if (cbbSortBy.getSelectedIndex() == 1) {
                    if (cbbSort.getSelectedIndex() == 0) {
                        // ten tang dan
                        fillToTable(sortByNgayToChuc(false));
                    } else if (cbbSort.getSelectedIndex() == 1) {
                        // ten giam dan
                        fillToTable(sortByNgayToChuc(true));
                    }
                } else if (cbbSortBy.getSelectedIndex() == 2) {
                    if (cbbSort.getSelectedIndex() == 0) {
                        // ten tang dan
                        fillToTable(sortByNgayLap(false));
                    } else if (cbbSort.getSelectedIndex() == 1) {
                        // ten giam dan
                        fillToTable(sortByNgayLap(true));
                    } else {
                        cbbSortBy.setSelectedIndex(0);
                    }
                }else if (cbbSortBy.getSelectedIndex() == 3) {
                    sortTheoCapDo();
                }
            }
        });
    }

    public void sortTheoCapDo() {
        List<HopDong> listDangChoDuyet = new ArrayList<>();
        List<HopDong> listDangChoKyKet = new ArrayList<>();
        List<HopDong> listDangSapHetHan = new ArrayList<>();
        List<HopDong> listConLai = new ArrayList<>();
        listFilted.clear();
        for (HopDong hd : listHopDong) {
            if (hd.getTrangThai().equals(StatusHopDong.CHODUYET)) {

                if ((hd.getNgayToChuc().getTime() - new Date().getTime() < 3 * 24 * 3600000) && (hd.getNgayToChuc().getTime() - new Date().getTime()) > 0) {
                    listDangSapHetHan.add(hd);
                    continue;
                }
                listDangChoDuyet.add(hd);
            } else if (hd.getTrangThai().equals(StatusHopDong.CHOKYKET)) {
               
                if ((hd.getNgayToChuc().getTime() - new Date().getTime() < 3 * 24 * 3600000) && (hd.getNgayToChuc().getTime() - new Date().getTime()) > 0) {
                    listDangSapHetHan.add(hd);
                    continue;
                }
                 listDangChoKyKet.add(hd);
            } else {
                listConLai.add(hd);
            }
        }

        for (HopDong hd : listDangSapHetHan) {
            listFilted.add(hd);
        }

        for (HopDong hd : listDangChoKyKet) {
            listFilted.add(hd);
        }
        for (HopDong hd : listDangChoDuyet) {
            listFilted.add(hd);
        }

        for (HopDong hd : listConLai) {
            listFilted.add(hd);
        }

        fillToTable(listFilted);

    }

    public List<HopDong> sortByNameKhachHang(boolean isRevese) {
        List<HopDong> listSorted = listFilted;
        Collections.sort(listSorted, new Comparator<HopDong>() {
            public int compare(HopDong hopDong1, HopDong hopDong2) {
                int result = 0;

                String[] partNameEmployee1 = hopDong1.getTenKhachHang().split("\\s");
                String[] partNameEmployee2 = hopDong2.getTenKhachHang().split("\\s");
                if (partNameEmployee1.length == 1) {
                    System.out.println("");
                }

                int nameLenght1 = partNameEmployee1.length;
                int nameLenght2 = partNameEmployee2.length;

                if (nameLenght1 == 1 && nameLenght2 == 1) {
                    return hopDong1.getTenKhachHang().compareToIgnoreCase(hopDong2.getTenKhachHang());
                }

                if (nameLenght1 == 1 && nameLenght2 != 1) {
                    return hopDong1.getTenKhachHang().compareToIgnoreCase(partNameEmployee2[nameLenght2 - 1]);
                } else if (nameLenght1 != -1 && nameLenght2 == 1) {
                    return partNameEmployee1[nameLenght1 - 1].compareToIgnoreCase(hopDong2.getTenKhachHang());
                }

                int length = ((nameLenght1 > nameLenght2) ? nameLenght2 : nameLenght1);
                for (int i = 1; i < length; i++) {
                    result = (partNameEmployee1[nameLenght1 - i]).compareToIgnoreCase(partNameEmployee2[nameLenght2 - i]);
                    if (result != 0) {
                        return result;
                    }
                }
                if (nameLenght1 > nameLenght2) {
                    return 1;
                } else if (nameLenght1 < nameLenght2) {
                    return -1;
                }

                return 0;
            }
        });

        if (isRevese) {
            Collections.reverse(listSorted);
        }

        return listSorted;
    }

    public List<HopDong> sortByNgayToChuc(boolean isRevese) {
        List<HopDong> listSorted = listFilted;
        Collections.sort(listSorted, new Comparator<HopDong>() {
            public int compare(HopDong hopDong1, HopDong hopDong2) {
                return (hopDong1.getNgayToChuc().compareTo(hopDong2.getNgayToChuc()));

            }
        });

        if (isRevese) {
            Collections.reverse(listSorted);
        }

        return listSorted;
    }

    public void thongBaoHopDong() {
        List<HopDong> listHopDongChoDuyet = new ArrayList<>();
        String hopDong = "";
        for (HopDong hd : listHopDong) {
            if (hd.getTrangThai().equals("CHODUYET")) {
                listHopDongChoDuyet.add(hd);
                hopDong = hopDong + ", " + hd.getMaHD();

            }
        }

        Date today = DateHelper.now();
        for (HopDong hd : listHopDongChoDuyet) {
            if (hd.getNgayLap().getTime() - today.getTime() < 3 * 24 * 3600000) {
                boolean rs = DialogHelper.confirm(this, "Hợp đồng " + hd.getMaHD() + " chưa được duyệt."
                        + " \n Xem chi tiết hợp đồng");
                if (rs) {
                    LapHopDong lapHopDong = new LapHopDong(false, hd.getMaHD(), false);
                    AppStatus.mainApp.showForm(lapHopDong);
                    return;
                } else {
                    return;
                }
            }
        }

        boolean rs = DialogHelper.confirm(this, "Có " + listHopDongChoDuyet.size() + " hợp đồng đang chờ duyệt:\n" + hopDong + "\n" + "Xem danh sách");
        if (rs) {
            for (int i = 0; i < listTrangThai.size(); i++) {
                if (listTrangThai.get(i).getMaTT().equals("CHODUYET")) {
                    cbbTrangThai.setSelectedIndex(i + 1);
                    return;
                }
            }
        } else {

        }
    }

    public List<HopDong> sortByNgayLap(boolean isRevese) {
        List<HopDong> listSorted = listFilted;
        Collections.sort(listSorted, new Comparator<HopDong>() {
            public int compare(HopDong hopDong1, HopDong hopDong2) {
                return (hopDong1.getNgayLap().compareTo(hopDong2.getNgayLap()));

            }
        });

        if (isRevese) {
            Collections.reverse(listSorted);
        }

        return listSorted;
    }

//     public List<HopDong> sortByMaHD(boolean isRevese) {
//        List<HopDong> listSorted = listFilted;
//        Collections.sort(listSorted, new Comparator<HopDong>() {
//            public int compare(HopDong hopDong1, HopDong hopDong2) {
//                List<HopDong> listSortNgayLap = sortByNgayLap(true);
//
//            }
//        });
//
//        if (isRevese) {
//            Collections.reverse(listSorted);
//        }
//
//        return listSorted;
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGrpStatus = new javax.swing.ButtonGroup();
        pnlQuanLyHopDong = new javax.swing.JPanel();
        txtSearch = new javax.swing.JTextField();
        lblSearch = new javax.swing.JLabel();
        lblSort = new javax.swing.JLabel();
        cbbSort = new com.ui.swing.Combobox();
        lblSort1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHopDong = new com.ui.swing.Table("HopDongChoDuyet");
        jLabel1 = new javax.swing.JLabel();
        lblHuyLoc = new javax.swing.JLabel();
        txtNgayBatDau = new javax.swing.JTextField();
        cbbSanh = new com.ui.swing.Combobox();
        cbbSortBy = new com.ui.swing.Combobox();
        btnChiTiet = new com.ui.swing.HoverButton();
        btnLapHopDong = new com.ui.swing.HoverButton();
        jLabel4 = new javax.swing.JLabel();
        cbbTrangThai = new com.ui.swing.Combobox();
        txtNgayKetThuc = new javax.swing.JTextField();
        cbbNgay = new com.ui.swing.Combobox();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1620, 990));

        pnlQuanLyHopDong.setBackground(new java.awt.Color(255, 255, 255));
        pnlQuanLyHopDong.setPreferredSize(new java.awt.Dimension(1600, 950));
        pnlQuanLyHopDong.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtSearch.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtSearch.setToolTipText("Tìm theo mã, tên khách hàng,...");
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
        pnlQuanLyHopDong.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 390, 35));

        lblSearch.setBackground(new java.awt.Color(204, 204, 255));
        lblSearch.setForeground(new java.awt.Color(102, 0, 255));
        lblSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/happywedding/assets/Search.png"))); // NOI18N
        lblSearch.setAutoscrolls(true);
        lblSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                lblSearchFocusGained(evt);
            }
        });
        lblSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSearchMouseClicked(evt);
            }
        });
        pnlQuanLyHopDong.add(lblSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, 33));

        lblSort.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/happywedding/assets/sort.png"))); // NOI18N
        lblSort.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSortMouseClicked(evt);
            }
        });
        pnlQuanLyHopDong.add(lblSort, new org.netbeans.lib.awtextra.AbsoluteConstraints(1440, 110, 32, 35));

        cbbSort.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tăng dần", "Giảm dần" }));
        cbbSort.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cbbSort.setLabeText("");
        cbbSort.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbSortItemStateChanged(evt);
            }
        });
        cbbSort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbSortActionPerformed(evt);
            }
        });
        pnlQuanLyHopDong.add(cbbSort, new org.netbeans.lib.awtextra.AbsoluteConstraints(1480, 90, 120, 54));

        lblSort1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/happywedding/assets/sort.png"))); // NOI18N
        lblSort1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSort1MouseClicked(evt);
            }
        });
        pnlQuanLyHopDong.add(lblSort1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 110, 32, 35));

        tblHopDong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MaHD", "Người lập", "Số bàn", "Sảnh", "Ngày lập ", "Khách hàng", "Ngày tổ chức", "Giờ bắt đầu ", "Giờ kết thúc", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHopDong.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        tblHopDong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHopDongMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblHopDong);

        pnlQuanLyHopDong.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 1590, 710));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/happywedding/assets/filt.png"))); // NOI18N
        pnlQuanLyHopDong.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, -1));

        lblHuyLoc.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblHuyLoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/happywedding/assets/notfiltd.png"))); // NOI18N
        lblHuyLoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHuyLocMouseClicked(evt);
            }
        });
        pnlQuanLyHopDong.add(lblHuyLoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(1430, 30, -1, 50));

        txtNgayBatDau.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtNgayBatDau.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNgayBatDauFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNgayBatDauFocusLost(evt);
            }
        });
        txtNgayBatDau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNgayBatDauMouseClicked(evt);
            }
        });
        txtNgayBatDau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNgayBatDauActionPerformed(evt);
            }
        });
        pnlQuanLyHopDong.add(txtNgayBatDau, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 40, 200, 35));

        cbbSanh.setToolTipText("");
        cbbSanh.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cbbSanh.setLabeText("Sảnh");
        cbbSanh.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbSanhItemStateChanged(evt);
            }
        });
        cbbSanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbSanhActionPerformed(evt);
            }
        });
        pnlQuanLyHopDong.add(cbbSanh, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 20, 140, 54));

        cbbSortBy.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Họ Tên  Khách Hàng", "Ngày Tổ Chức", "Ngày Lập", "Cấp độ hợp đồng" }));
        cbbSortBy.setSelectedIndex(-1);
        cbbSortBy.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cbbSortBy.setLabeText("Sort by");
        cbbSortBy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbSortByActionPerformed(evt);
            }
        });
        pnlQuanLyHopDong.add(cbbSortBy, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 90, 270, 54));

        btnChiTiet.setBackground(new java.awt.Color(77, 76, 125));
        btnChiTiet.setForeground(new java.awt.Color(255, 255, 255));
        btnChiTiet.setText("Chi tiết");
        btnChiTiet.setBorderColor(new java.awt.Color(77, 76, 125));
        btnChiTiet.setColor(new java.awt.Color(77, 76, 125));
        btnChiTiet.setColorClick(new java.awt.Color(77, 0, 196));
        btnChiTiet.setColorOver(new java.awt.Color(77, 0, 196));
        btnChiTiet.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnChiTiet.setLabelColor(java.awt.Color.white);
        btnChiTiet.setLableColorClick(java.awt.Color.white);
        btnChiTiet.setRadius(15);
        btnChiTiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChiTietActionPerformed(evt);
            }
        });
        pnlQuanLyHopDong.add(btnChiTiet, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 900, 80, 30));

        btnLapHopDong.setBackground(new java.awt.Color(77, 76, 125));
        btnLapHopDong.setForeground(new java.awt.Color(255, 255, 255));
        btnLapHopDong.setText("Lập hợp đồng");
        btnLapHopDong.setBorderColor(new java.awt.Color(77, 76, 125));
        btnLapHopDong.setColor(new java.awt.Color(77, 76, 125));
        btnLapHopDong.setColorClick(new java.awt.Color(77, 0, 196));
        btnLapHopDong.setColorOver(new java.awt.Color(77, 0, 196));
        btnLapHopDong.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnLapHopDong.setLabelColor(java.awt.Color.white);
        btnLapHopDong.setLableColorClick(java.awt.Color.white);
        btnLapHopDong.setRadius(15);
        btnLapHopDong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLapHopDongActionPerformed(evt);
            }
        });
        pnlQuanLyHopDong.add(btnLapHopDong, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 900, 130, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel4.setText("Trạng thái");
        pnlQuanLyHopDong.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 40, -1, 30));

        cbbTrangThai.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tất cả", "Đang chờ duyệt", "Đang chờ ký kết", "Đang chờ thực hiện", "Đã hủy" }));
        cbbTrangThai.setToolTipText("");
        cbbTrangThai.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cbbTrangThai.setLabeText("");
        cbbTrangThai.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbTrangThaiItemStateChanged(evt);
            }
        });
        cbbTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTrangThaiActionPerformed(evt);
            }
        });
        pnlQuanLyHopDong.add(cbbTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 20, 280, 50));

        txtNgayKetThuc.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtNgayKetThuc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNgayKetThucFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNgayKetThucFocusLost(evt);
            }
        });
        txtNgayKetThuc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNgayKetThucMouseClicked(evt);
            }
        });
        txtNgayKetThuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNgayKetThucActionPerformed(evt);
            }
        });
        pnlQuanLyHopDong.add(txtNgayKetThuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 40, 200, 35));

        cbbNgay.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ngày tổ chức", "Ngày lập" }));
        cbbNgay.setToolTipText("");
        cbbNgay.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cbbNgay.setLabeText("");
        cbbNgay.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbNgayItemStateChanged(evt);
            }
        });
        cbbNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbNgayActionPerformed(evt);
            }
        });
        pnlQuanLyHopDong.add(cbbNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 34, 190, 40));

        jPanel1.setBackground(new java.awt.Color(255, 102, 102));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        pnlQuanLyHopDong.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 900, 30, 30));

        jLabel2.setText("Chưa được xử lý");
        pnlQuanLyHopDong.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 900, -1, 30));

        jPanel2.setBackground(new java.awt.Color(153, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        pnlQuanLyHopDong.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 900, -1, -1));

        jLabel3.setText("Đang chờ ký kết");
        pnlQuanLyHopDong.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 900, -1, 30));

        jPanel3.setBackground(new java.awt.Color(153, 255, 153));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        pnlQuanLyHopDong.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 900, -1, -1));

        jLabel5.setText("Đang chờ duyệt");
        pnlQuanLyHopDong.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 900, -1, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlQuanLyHopDong, javax.swing.GroupLayout.DEFAULT_SIZE, 1620, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlQuanLyHopDong, javax.swing.GroupLayout.DEFAULT_SIZE, 990, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        if (isLoad) {
            filtedHopDong();
        }
    }//GEN-LAST:event_txtSearchKeyReleased

    private void lblSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSearchMouseClicked

    }//GEN-LAST:event_lblSearchMouseClicked

    private void lblSortMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSortMouseClicked

    }//GEN-LAST:event_lblSortMouseClicked

    private void cbbSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbSortActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbSortActionPerformed

    private void lblSort1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSort1MouseClicked
        int oldIndex = cbbSortBy.getSelectedIndex();
        cbbSortBy.setSelectedIndex(0);
        cbbSortBy.setSelectedIndex(oldIndex);
    }//GEN-LAST:event_lblSort1MouseClicked

    private void cbbSanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbSanhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbSanhActionPerformed

    private void btnLapHopDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLapHopDongActionPerformed
        String maHD = "";
        String thuTu = "";
        String today = DateHelper.toString(DateHelper.now(), "dd/MM/yyyy");
        List<String> list = new ArrayList<>();
        for (HopDong hd : listHopDong) {
            if (today.equals(DateHelper.toString(hd.getNgayLap(), "dd/MM/yyyy"))) {
                list.add(hd.getMaHD());
            }
        }

        if (!list.isEmpty()) {
            List<Integer> listThuTu = new ArrayList<>();

            for (String ma : list) {
                int tt = Integer.parseInt(ma.substring(10));
                listThuTu.add(tt);
            }
            Collections.sort(listThuTu);
            thuTu = String.format("%03d", listThuTu.get(listThuTu.size() - 1) + 1);;
        } else {
            thuTu = "001";
        }

        String[] partDate = today.split("/");

        maHD = "HD" + partDate[2] + partDate[1] + partDate[0] + thuTu;

        AppStatus.mainApp.showForm(new LapHopDong(true, maHD, false));
    }//GEN-LAST:event_btnLapHopDongActionPerformed

    private void txtNgayBatDauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgayBatDauActionPerformed

    }//GEN-LAST:event_txtNgayBatDauActionPerformed

    private void btnChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChiTietActionPerformed
        currentIndex = tblHopDong.getSelectedRow();
        if (currentIndex >= 0) {
            String maHD = (String) tblHopDong.getValueAt(this.currentIndex, 0);
            LapHopDong lapHopDong = new LapHopDong(false, maHD, false);
            AppStatus.mainApp.showForm(lapHopDong);

        } else {
            DialogHelper.alertError(this, "Vui lòng chọn hợp đồng");
        }
    }//GEN-LAST:event_btnChiTietActionPerformed

    private void cbbSortItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbSortItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbSortItemStateChanged

    private void txtNgayBatDauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNgayBatDauMouseClicked
        showCalendar1();
    }//GEN-LAST:event_txtNgayBatDauMouseClicked

    private void tblHopDongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHopDongMouseClicked
        currentIndex = tblHopDong.rowAtPoint(evt.getPoint());
        if (currentIndex >= 0) {
            if (evt.getClickCount() == 2) {
                String maHD = (String) tblHopDong.getValueAt(this.currentIndex, 0);
                LapHopDong lapHopDong = new LapHopDong(false, maHD, false);
                AppStatus.mainApp.showForm(lapHopDong);
            }
        }
    }//GEN-LAST:event_tblHopDongMouseClicked

    private void cbbTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTrangThaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbTrangThaiActionPerformed

    private void cbbTrangThaiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbTrangThaiItemStateChanged
        if (isLoad) {
            filtedHopDong();
        }
    }//GEN-LAST:event_cbbTrangThaiItemStateChanged

    private void cbbSanhItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbSanhItemStateChanged
        if (isLoad) {
            filtedHopDong();
        }
    }//GEN-LAST:event_cbbSanhItemStateChanged

    private void txtNgayBatDauFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNgayBatDauFocusLost
        // System.out.println("thay doi");
    }//GEN-LAST:event_txtNgayBatDauFocusLost

    private void txtNgayBatDauFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNgayBatDauFocusGained
        filtedHopDong();
    }//GEN-LAST:event_txtNgayBatDauFocusGained

    private void lblHuyLocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHuyLocMouseClicked
        txtNgayBatDau.setText("");
        txtNgayKetThuc.setText("");
        txtSearch.setText("");
        cbbSanh.setSelectedIndex(0);
        cbbTrangThai.setSelectedIndex(0);
        cbbNgay.setSelectedIndex(0);

        filtedHopDong();

    }//GEN-LAST:event_lblHuyLocMouseClicked

    private void txtNgayKetThucFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNgayKetThucFocusGained
        filtedHopDong();
    }//GEN-LAST:event_txtNgayKetThucFocusGained

    private void txtNgayKetThucFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNgayKetThucFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgayKetThucFocusLost

    private void txtNgayKetThucMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNgayKetThucMouseClicked
        showCalendar2();
    }//GEN-LAST:event_txtNgayKetThucMouseClicked

    private void txtNgayKetThucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgayKetThucActionPerformed

    }//GEN-LAST:event_txtNgayKetThucActionPerformed

    private void cbbNgayItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbNgayItemStateChanged
        filtedHopDong();
    }//GEN-LAST:event_cbbNgayItemStateChanged

    private void cbbNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbNgayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbNgayActionPerformed

    private void cbbSortByActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbSortByActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbSortByActionPerformed

    private void lblSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lblSearchFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_lblSearchFocusGained



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ui.swing.HoverButton btnChiTiet;
    private javax.swing.ButtonGroup btnGrpStatus;
    private com.ui.swing.HoverButton btnLapHopDong;
    private com.ui.swing.Combobox cbbNgay;
    private com.ui.swing.Combobox cbbSanh;
    private com.ui.swing.Combobox cbbSort;
    private com.ui.swing.Combobox cbbSortBy;
    private com.ui.swing.Combobox cbbTrangThai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblHuyLoc;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JLabel lblSort;
    private javax.swing.JLabel lblSort1;
    private javax.swing.JPanel pnlQuanLyHopDong;
    private com.ui.swing.Table tblHopDong;
    private javax.swing.JTextField txtNgayBatDau;
    private javax.swing.JTextField txtNgayKetThuc;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
