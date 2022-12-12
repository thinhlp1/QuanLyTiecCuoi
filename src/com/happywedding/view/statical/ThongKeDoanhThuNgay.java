/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happywedding.view.statical;

import com.happywedding.dao.ThongKeDAO;
import com.happywedding.helper.AppStatus;
import com.happywedding.helper.DateHelper;
import com.happywedding.helper.DialogHelper;
import com.happywedding.helper.ShareHelper;
import com.ui.swing.chart.Chart;
import com.ui.swing.chart.ModelChart;
import com.ui.swing.datechooser.DateChooser;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.plaf.basic.BasicSpinnerUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class ThongKeDoanhThuNgay extends javax.swing.JPanel {

    private DefaultTableModel tblModel;

    private List<Object[]> listThongKeNgay = new ArrayList<>();
    private List<Object[]> listFilted = new ArrayList<>();
    private ThongKeDAO thongKeDAO = new ThongKeDAO();
    private boolean isLoad = false;
    private DateChooser dtChooser1 = new DateChooser();
    private DateChooser dtChooser2 = new DateChooser();
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);

    /**
     * Creates new form ThongKeDoanhThuNam
     */
    public ThongKeDoanhThuNgay(int month, int year) {
        initComponents();
        init(month, year);
    }

    public ThongKeDoanhThuNgay() {
        initComponents();
        init(-1, -1);
    }

    public void init(int month, int year) {
        tblModel = (DefaultTableModel) tblDoanhThu.getModel();
        tblDoanhThu.fixTable(jScrollPane1);
//        dtChooser1.setTextRefernce(txtNgayBatDau);
//        dtChooser2.setTextRefernce(txtNgayKetThuc);
//        txtNgayBatDau.setText("");
//        txtNgayKetThuc.setText("");

        fillCombboxNam();

        loadThongKeNgay(365, currentYear);

        fillTable(listThongKeNgay);
        // fillChart(listThongKe);

        initSort();

        if (year != -1) {
            for (int i = 0; i < cbbNam.getComponentCount(); i++) {
                if ((Integer.parseInt("" + cbbNam.getItemAt(i))) == year) {
                    cbbNam.setSelectedIndex(i);
                    break;
                }
            }

            for (int i = 0; i < 12; i++) {
                if ((Integer.parseInt("" + cbbMonth.getItemAt(i))) == month) {
                    cbbMonth.setSelectedIndex(i);
                    break;
                }
            }
            isLoad = true;
            filtedThongKe();

        }
        isLoad = true;

        lblSL.setVisible(false);
        txtSL.setVisible(false);
        cbbFiltBy.setVisible(false);

    }

    public void initSort() {
        cbbSortBy.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (cbbSortBy.getSelectedIndex() == 0) {
                    if (cbbSort.getSelectedIndex() == 0) {
                        // ten tang dan
                        fillTable(sortByNam(false));
                    } else if (cbbSort.getSelectedIndex() == 1) {
                        // ten giam dan
                        fillTable(sortByNam(true));
                    }
                } else if (cbbSortBy.getSelectedIndex() == 1) {
                    if (cbbSort.getSelectedIndex() == 0) {

                        // ten tang dan
                        fillTable(sortBySLHD(false));
                    } else if (cbbSort.getSelectedIndex() == 1) {

                        // ten giam dan
                        // a++;
                        fillTable(sortBySLHD(true));
                    }
                } else if (cbbSortBy.getSelectedIndex() == 2) {
                    if (cbbSort.getSelectedIndex() == 0) {
                        // ten tang dan
                        fillTable(sortByDoanhThuNhoNhat(false));
                    } else if (cbbSort.getSelectedIndex() == 1) {
                        // ten giam dan
                        fillTable(sortByDoanhThuNhoNhat(true));
                    } else {
                        cbbSortBy.setSelectedIndex(0);
                    }
                } else if (cbbSortBy.getSelectedIndex() == 3) {
                    if (cbbSort.getSelectedIndex() == 0) {
                        // ten tang dan
                        fillTable(sortByDoanhThuLonNhat(false));
                    } else if (cbbSort.getSelectedIndex() == 1) {
                        // ten giam dan
                        fillTable(sortByDoanhThuLonNhat(true));
                    } else {
                        cbbSortBy.setSelectedIndex(0);
                    }
                } else if (cbbSortBy.getSelectedIndex() == 4) {
                    if (cbbSort.getSelectedIndex() == 0) {
                        // ten tang dan
                        fillTable(sortByTongDoanhThu(false));
                    } else if (cbbSort.getSelectedIndex() == 1) {
                        // ten giam dan
                        fillTable(sortByTongDoanhThu(true));
                    } else {
                        cbbSortBy.setSelectedIndex(0);
                    }
                }
            }
        });

        cbbSort.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (cbbSortBy.getSelectedIndex() == 0) {
                    if (cbbSort.getSelectedIndex() == 0) {
                        // ten tang dan
                        fillTable(sortByNam(false));
                    } else if (cbbSort.getSelectedIndex() == 1) {
                        // ten giam dan
                        fillTable(sortByNam(true));
                    }
                } else if (cbbSortBy.getSelectedIndex() == 1) {
                    if (cbbSort.getSelectedIndex() == 0) {
                        // ten tang dan
                        fillTable(sortBySLHD(false));
                    } else if (cbbSort.getSelectedIndex() == 1) {
                        // System.out.println("");
                        //a++;
                        // ten giam dan
                        fillTable(sortBySLHD(true));
                    }
                } else if (cbbSortBy.getSelectedIndex() == 2) {
                    if (cbbSort.getSelectedIndex() == 0) {
                        // ten tang dan
                        fillTable(sortByDoanhThuNhoNhat(false));
                    } else if (cbbSort.getSelectedIndex() == 1) {
                        // ten giam dan
                        fillTable(sortByDoanhThuNhoNhat(true));
                    } else {
                        cbbSortBy.setSelectedIndex(0);
                    }
                } else if (cbbSortBy.getSelectedIndex() == 3) {
                    if (cbbSort.getSelectedIndex() == 0) {
                        // ten tang dan
                        fillTable(sortByDoanhThuLonNhat(false));
                    } else if (cbbSort.getSelectedIndex() == 1) {
                        // ten giam dan
                        fillTable(sortByDoanhThuLonNhat(true));
                    } else {
                        cbbSortBy.setSelectedIndex(0);
                    }
                } else if (cbbSortBy.getSelectedIndex() == 4) {
                    if (cbbSort.getSelectedIndex() == 0) {
                        // ten tang dan
                        fillTable(sortByTongDoanhThu(false));
                    } else if (cbbSort.getSelectedIndex() == 1) {
                        // ten giam dan
                        fillTable(sortByTongDoanhThu(true));
                    } else {
                        cbbSortBy.setSelectedIndex(0);
                    }
                }
            }
        });
    }

    public void loadThongKeNgay(int top, int nam) {
        listThongKeNgay = thongKeDAO.thongKeDoanhTheoNgay(top, nam);
        listFilted.clear();
        for (int i = 0; i < listThongKeNgay.size(); i++) {
            listFilted.add(listThongKeNgay.get(i));
        }
    }

    public void loadThongKeNgaySL(int sl, int nam) {
        listThongKeNgay = thongKeDAO.thongKeDoanhTheoNgaySL(sl, nam);
        listFilted.clear();
        for (int i = 0; i < listThongKeNgay.size(); i++) {
            listFilted.add(listThongKeNgay.get(i));
        }
    }

    public void fillTable(List<Object[]> list) {
        tblModel.setRowCount(0);
        /// System.out.println(a);
        for (int i = 0; i < list.size(); i++) {
            Object[] model = {
                DateHelper.toString(((Date) list.get(i)[0]), "dd/MM/yyyy"),
                list.get(i)[1],
                ShareHelper.toMoney((long) list.get(i)[2]),
                ShareHelper.toMoney((long) list.get(i)[3]),
                ShareHelper.toMoney((long) list.get(i)[4])};
            tblModel.addRow(model);
        }

        fillChart(list);

    }

    public void fillChart(List<Object[]> list) {
        if (list.size() == 0) {
            chartDoanhThu.removeAllData();
            System.out.println("rết");
            chartDoanhThu.addData(new ModelChart("", new long[]{0,0,0}));
            return;
        }
        if (!isLoad) {
            chartDoanhThu.addLegend("Doanh thu", Color.red);
            chartDoanhThu.addLegend("Doanh thu cao nhất", Color.yellow);
            chartDoanhThu.addLegend("Doanh thu thấp nhất", Color.BLUE);

        }
        // new double[]{list.get(4)[[4]]};
        chartDoanhThu.removeAllData();

        for (int i = 0; i < list.size(); i++) {
            chartDoanhThu.addData(new ModelChart(DateHelper.toString(((Date) list.get(i)[0]), "dd/MM"), new long[]{(long) list.get(i)[4], (long) list.get(i)[3], (long) list.get(i)[2]}));
        }

    }

    public void fillCombboxNam() {
        List<Integer> year = new ArrayList<>();

        DefaultComboBoxModel cbbNamModel = (DefaultComboBoxModel) cbbNam.getModel();

        cbbNamModel.removeAllElements();

        try {
            int firstYear = thongKeDAO.getFirtYear();
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            for (int i = firstYear; i <= currentYear; i++) {
                cbbNamModel.addElement(i);
            }

            cbbNamModel.setSelectedItem(currentYear);

        } catch (Exception ex) {
            //DialogHelper.alertError(this, "Có lỗi xảy ra. Vui lòng liên hệ hỗ trợ viên");
        }

    }

    public void filtedThongKe() {
        List<Object[]> list = new ArrayList<>();
        List<Object[]> list2 = new ArrayList<>();

        if (cbbNam.getSelectedIndex() != -1) {

            int nam = Integer.parseInt(cbbNam.getSelectedItem().toString());

            try {
                if (cbbFiltBy1.getSelectedIndex() == 0) {

                    lblMonth.setVisible(true);
                    cbbMonth.setVisible(true);
                    lblSL.setVisible(false);
                    txtSL.setVisible(false);
                    lblSoNgay.setVisible(true);
                    cbbSoNgay.setVisible(true);
                    cbbFiltBy.setVisible(false);

                    loadThongKeNgay(365, nam);
                    int thang = Integer.parseInt(cbbMonth.getSelectedItem().toString());

                    Calendar calendar = Calendar.getInstance();

                    int dayStart = 0;
                    int dayEnd = 31;
                    if (cbbSoNgay.getSelectedIndex() == 0) {
                        dayStart = 1;
                        dayEnd = 15;

                    } else if (cbbSoNgay.getSelectedIndex() == 1) {
                        dayStart = 15;
                        Date today = new Date();
                        calendar.setTime(today);

                        calendar.add(Calendar.MONTH, 1);
                        calendar.set(Calendar.DAY_OF_MONTH, 1);
                        calendar.add(Calendar.DATE, -1);

                        dayEnd = calendar.get(Calendar.DAY_OF_MONTH);
                    }

                    listFilted.clear();

                    for (int i = 0; i < listThongKeNgay.size(); i++) {
                        calendar.setTime((Date) listThongKeNgay.get(i)[0]);
                        if (calendar.get(Calendar.YEAR) == nam && calendar.get(Calendar.MONTH) + 1 == thang
                                && calendar.get(Calendar.DATE) >= dayStart && calendar.get(Calendar.DATE) <= dayEnd) {
                            list.add(listThongKeNgay.get(i));
                        }
                    }

                } else if (cbbFiltBy1.getSelectedIndex() == 1) {

                    lblMonth.setVisible(false);
                    cbbMonth.setVisible(false);
                    lblSoNgay.setVisible(false);
                    cbbSoNgay.setVisible(false);
                    lblSL.setVisible(true);
                    txtSL.setVisible(true);
                    cbbFiltBy.setVisible(true);

                    int sl = 1;
                    sl = Integer.parseInt(txtSL.getText());

                    if (cbbFiltBy.getSelectedIndex() == 0) {
                        loadThongKeNgay(sl, nam);
                    } else if (cbbFiltBy.getSelectedIndex() == 1) {
                        loadThongKeNgaySL(sl, nam);
                    }

                    listFilted.clear();
                    Calendar calendar = Calendar.getInstance();
                    for (int i = 0; i < listThongKeNgay.size(); i++) {

                        list.add(listThongKeNgay.get(i));

                    }

                }
            } catch (Exception e) {
            }

        } else {
            for (int i = 0; i < listThongKeNgay.size(); i++) {
                list.add(listThongKeNgay.get(i));
            }
        }

//        if (txtNgayBatDau.getText().length() != 0 && txtNgayKetThuc.getText().length() == 0) {
//            for (int i = 0; i < list.size(); i++) {
//                if (((Date) list.get(i)[0]).after(DateHelper.toDate(txtNgayBatDau.getText(), "dd//MM/yyyy"))
//                        || ((Date) list.get(i)[0]).equals(DateHelper.toDate(txtNgayBatDau.getText(), "dd//MM/yyyy"))) {
//                    list2.add(list.get(i));
//                }
//            }
//        } else if (txtNgayBatDau.getText().length() == 0 && txtNgayKetThuc.getText().length() != 0) {
//            for (int i = 0; i < list.size(); i++) {
//                if (((Date) list.get(i)[0]).before(DateHelper.toDate(txtNgayKetThuc.getText(), "dd//MM/yyyy"))
//                        || ((Date) list.get(i)[0]).equals(DateHelper.toDate(txtNgayKetThuc.getText(), "dd//MM/yyyy"))) {
//                    list2.add(list.get(i));
//                }
//            }
//        } else if (txtNgayBatDau.getText().length() != 0 && txtNgayKetThuc.getText().length() != 0) {
//            for (int i = 0; i < list.size(); i++) {
//                if (((Date) list.get(i)[0]).after(DateHelper.toDate(txtNgayBatDau.getText(), "dd//MM/yyyy"))
//                        || ((Date) list.get(i)[0]).before(DateHelper.toDate(txtNgayKetThuc.getText(), "dd//MM/yyyy"))
//                        || ((Date) list.get(i)[0]).equals(DateHelper.toDate(txtNgayKetThuc.getText(), "dd//MM/yyyy"))
//                        || ((Date) list.get(i)[0]).equals(DateHelper.toDate(txtNgayBatDau.getText(), "dd//MM/yyyy"))) {
//                    list2.add(list.get(i));
//                }
//            }
//        } else {
//            for (int i = 0; i < list.size(); i++) {
//                list2.add(list.get(i));
//            }
//        }
        for (int i = 0; i < list.size(); i++) {
            listFilted.add(list.get(i));
        }

        fillTable(listFilted);
        fillChart(listFilted);

    }

    public List<Object[]> sortByDoanhThuLonNhat(boolean isRevese) {
        List<Object[]> listSorted = listFilted;

        Collections.sort(listSorted, new Comparator<Object[]>() {
            public int compare(Object[] data1, Object[] data2) {
                if (Long.parseLong(data1[3].toString()) > Long.parseLong(data2[3].toString())) {
                    return 1;
                } else if (Long.parseLong(data1[3].toString()) < Long.parseLong(data2[3].toString())) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        if (isRevese) {
            Collections.reverse(listSorted);
        }
        return listSorted;
    }

    public List<Object[]> sortByDoanhThuNhoNhat(boolean isRevese) {
        List<Object[]> listSorted = listFilted;
        Collections.sort(listSorted, new Comparator<Object[]>() {
            public int compare(Object[] data1, Object[] data2) {
                if (Long.parseLong(data1[2].toString()) > Long.parseLong(data2[2].toString())) {
                    return 1;
                } else if (Long.parseLong(data1[2].toString()) < Long.parseLong(data2[2].toString())) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        if (isRevese) {
            Collections.reverse(listSorted);
        }
        return listSorted;
    }

    public List<Object[]> sortByNam(boolean isRevese) {
        List<Object[]> listSorted = listFilted;
        Object c = listSorted.get(0)[1];
        Object d = listSorted.get(1)[1];
        Collections.sort(listSorted, new Comparator<Object[]>() {
            public int compare(Object[] data1, Object[] data2) {
                if (Long.parseLong(data1[0].toString()) > Long.parseLong(data2[0].toString())) {
                    return 1;
                } else if (Long.parseLong(data1[0].toString()) < Long.parseLong(data2[0].toString())) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        Object a = listSorted.get(0)[1];
        Object b = listSorted.get(1)[1];
        if (isRevese) {
            Collections.reverse(listSorted);
        }
        Object g = listSorted.get(0)[1];
        Object h = listSorted.get(1)[1];
        return listSorted;
    }

    public List<Object[]> sortBySLHD(boolean isRevese) {
        List<Object[]> listSorted = listFilted;
        Object c = listSorted.get(0)[1];
        Object d = listSorted.get(1)[1];
        Collections.sort(listSorted, new Comparator<Object[]>() {
            public int compare(Object[] data1, Object[] data2) {
                if (Long.parseLong(data1[1].toString()) > Long.parseLong(data2[1].toString())) {
                    return 1;
                } else if (Long.parseLong(data1[1].toString()) < Long.parseLong(data2[1].toString())) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        Object e = listSorted.get(0)[1];
        Object f = listSorted.get(1)[1];
        if (isRevese) {
            Collections.reverse(listSorted);
        }
        Object h = listSorted.get(0)[1];
        Object j = listSorted.get(1)[1];
        return listSorted;
    }

    public List<Object[]> sortByTongDoanhThu(boolean isRevese) {
        List<Object[]> listSorted = listFilted;
        Collections.sort(listSorted, new Comparator<Object[]>() {
            public int compare(Object[] data1, Object[] data2) {
                if (Long.parseLong(data1[4].toString()) > Long.parseLong(data2[4].toString())) {
                    return 1;
                } else if (Long.parseLong(data1[4].toString()) < Long.parseLong(data2[4].toString())) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        if (isRevese) {
            Collections.reverse(listSorted);
        }
        return listSorted;
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
        jLabel3 = new javax.swing.JLabel();
        lblSort = new javax.swing.JLabel();
        cbbSort = new com.ui.swing.Combobox();
        lblSort1 = new javax.swing.JLabel();
        cbbSortBy = new com.ui.swing.Combobox();
        chartDoanhThu = new com.ui.swing.chart.Chart();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbbFiltBy1 = new com.ui.swing.Combobox();
        pnlFilt = new javax.swing.JPanel();
        lblMonth = new javax.swing.JLabel();
        cbbMonth = new com.ui.swing.Combobox();
        lblSoNgay = new javax.swing.JLabel();
        cbbSoNgay = new com.ui.swing.Combobox();
        cbbFiltBy = new com.ui.swing.Combobox();
        lblSL = new javax.swing.JLabel();
        txtSL = new javax.swing.JTextField();
        cbbNam = new com.ui.swing.Combobox();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDoanhThu = new com.ui.swing.Table();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, -1, -1));

        lblSort.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/happywedding/assets/sort.png"))); // NOI18N
        lblSort.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSortMouseClicked(evt);
            }
        });
        jPanel1.add(lblSort, new org.netbeans.lib.awtextra.AbsoluteConstraints(1450, 480, 32, 35));

        cbbSort.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tăng dần", "Giảm dần" }));
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
        jPanel1.add(cbbSort, new org.netbeans.lib.awtextra.AbsoluteConstraints(1490, 460, 100, 54));

        lblSort1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/happywedding/assets/sort.png"))); // NOI18N
        lblSort1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSort1MouseClicked(evt);
            }
        });
        jPanel1.add(lblSort1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 480, 32, 35));

        cbbSortBy.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Năm", "Số Lượng Họp Đồng", "Doanh Thu Thấp Nhất", "Doanh Thu Cao Nhất", "Tổng Doanh Thu" }));
        cbbSortBy.setSelectedIndex(-1);
        cbbSortBy.setLabeText("Sort by");
        cbbSortBy.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbSortByItemStateChanged(evt);
            }
        });
        jPanel1.add(cbbSortBy, new org.netbeans.lib.awtextra.AbsoluteConstraints(1270, 460, 170, 54));
        jPanel1.add(chartDoanhThu, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 1540, 420));

        jLabel1.setText("VNĐ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, -1));

        jLabel2.setText("Ngày");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1580, 430, -1, -1));

        cbbFiltBy1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ngày trong tháng", "Ngày doanh số lớn", "Ngày lễ lớn" }));
        cbbFiltBy1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cbbFiltBy1.setLabeText("");
        cbbFiltBy1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbFiltBy1ItemStateChanged(evt);
            }
        });
        cbbFiltBy1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbFiltBy1ActionPerformed(evt);
            }
        });
        jPanel1.add(cbbFiltBy1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 460, 190, 54));

        pnlFilt.setBackground(new java.awt.Color(255, 255, 255));
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 15, 5);
        flowLayout1.setAlignOnBaseline(true);
        pnlFilt.setLayout(flowLayout1);

        lblMonth.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMonth.setText("Tháng");
        lblMonth.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        lblMonth.setPreferredSize(new java.awt.Dimension(42, 40));
        pnlFilt.add(lblMonth);

        cbbMonth.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "11", "12" }));
        cbbMonth.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cbbMonth.setLabeText("");
        cbbMonth.setMinimumSize(new java.awt.Dimension(40, 49));
        cbbMonth.setPreferredSize(new java.awt.Dimension(60, 41));
        cbbMonth.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbMonthItemStateChanged(evt);
            }
        });
        cbbMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbMonthActionPerformed(evt);
            }
        });
        pnlFilt.add(cbbMonth);

        lblSoNgay.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblSoNgay.setText("Số ngày");
        lblSoNgay.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        lblSoNgay.setPreferredSize(new java.awt.Dimension(60, 40));
        lblSoNgay.setRequestFocusEnabled(false);
        pnlFilt.add(lblSoNgay);

        cbbSoNgay.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Đầu tháng - 15", "15 - Cuối tháng" }));
        cbbSoNgay.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cbbSoNgay.setLabeText("");
        cbbSoNgay.setMinimumSize(new java.awt.Dimension(155, 49));
        cbbSoNgay.setPreferredSize(new java.awt.Dimension(155, 41));
        cbbSoNgay.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbSoNgayItemStateChanged(evt);
            }
        });
        cbbSoNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbSoNgayActionPerformed(evt);
            }
        });
        pnlFilt.add(cbbSoNgay);

        cbbFiltBy.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Doanh Thu", "Số Lượng hợp đồng" }));
        cbbFiltBy.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cbbFiltBy.setLabeText("");
        cbbFiltBy.setPreferredSize(new java.awt.Dimension(200, 49));
        cbbFiltBy.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbFiltByItemStateChanged(evt);
            }
        });
        cbbFiltBy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbFiltByActionPerformed(evt);
            }
        });
        pnlFilt.add(cbbFiltBy);

        lblSL.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblSL.setText("SL");
        pnlFilt.add(lblSL);

        txtSL.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtSL.setText("10");
        txtSL.setPreferredSize(new java.awt.Dimension(50, 25));
        txtSL.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSLFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSLFocusLost(evt);
            }
        });
        txtSL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSLMouseClicked(evt);
            }
        });
        txtSL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSLActionPerformed(evt);
            }
        });
        txtSL.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSLKeyTyped(evt);
            }
        });
        pnlFilt.add(txtSL);

        jPanel1.add(pnlFilt, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 460, 720, 60));

        cbbNam.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cbbNam.setLabeText("");
        cbbNam.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbNamItemStateChanged(evt);
            }
        });
        cbbNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbNamActionPerformed(evt);
            }
        });
        jPanel1.add(cbbNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 460, 100, 54));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel6.setText("Năm");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 490, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel7.setText("Chọn theo");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 490, -1, -1));

        tblDoanhThu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Năm", "Số lượng hợp đồng", "Danh thu thấp nhất", "Danh thu cao nhất", "Tổng danh thu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDoanhThu.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        tblDoanhThu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDoanhThuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDoanhThu);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 540, 1600, 330));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1716, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblSortMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSortMouseClicked

    }//GEN-LAST:event_lblSortMouseClicked

    private void cbbSortItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbSortItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbSortItemStateChanged

    private void cbbSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbSortActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbSortActionPerformed

    private void lblSort1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSort1MouseClicked

    }//GEN-LAST:event_lblSort1MouseClicked

    private void cbbSortByItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbSortByItemStateChanged

    }//GEN-LAST:event_cbbSortByItemStateChanged

    private void cbbNamItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbNamItemStateChanged
        if (isLoad == true) {
            filtedThongKe();
        }
    }//GEN-LAST:event_cbbNamItemStateChanged

    private void cbbNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbNamActionPerformed

    }//GEN-LAST:event_cbbNamActionPerformed

    private void cbbFiltByItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbFiltByItemStateChanged
        if (isLoad) {
            filtedThongKe();
        }
    }//GEN-LAST:event_cbbFiltByItemStateChanged

    private void cbbFiltByActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbFiltByActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbFiltByActionPerformed

    private void txtSLFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSLFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSLFocusGained

    private void txtSLFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSLFocusLost
        try {
            if (Integer.parseInt(txtSL.getText()) > 20) {
                txtSL.setText("20");
            } else if (Integer.parseInt(txtSL.getText()) <= 0) {
                txtSL.setText("1");
            } else {
                filtedThongKe();
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtSLFocusLost

    private void txtSLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSLMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSLMouseClicked

    private void txtSLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSLActionPerformed

    private void txtSLKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSLKeyTyped
        char testChar = evt.getKeyChar();

        if (!((Character.isDigit(testChar)))) {
            if (testChar == '\n') {
                lblSort.requestFocus();
            }
            evt.consume();

        }


    }//GEN-LAST:event_txtSLKeyTyped

    private void cbbFiltBy1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbFiltBy1ItemStateChanged
        if (isLoad) {
            filtedThongKe();
        }
    }//GEN-LAST:event_cbbFiltBy1ItemStateChanged

    private void cbbFiltBy1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbFiltBy1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbFiltBy1ActionPerformed

    private void cbbMonthItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbMonthItemStateChanged
        if (isLoad) {
            filtedThongKe();
        }
    }//GEN-LAST:event_cbbMonthItemStateChanged

    private void cbbMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbMonthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbMonthActionPerformed

    private void cbbSoNgayItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbSoNgayItemStateChanged
        if (isLoad) {
            filtedThongKe();
        }
    }//GEN-LAST:event_cbbSoNgayItemStateChanged

    private void cbbSoNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbSoNgayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbSoNgayActionPerformed

    private void tblDoanhThuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDoanhThuMouseClicked
//        if (evt.getClickCount() == 2){
//            int index = tblDoanhThu.getSelectedRow();
//            AppStatus.FORMTHONGKE.updateThongKeThang((int) listFilted.get(index)[0]);
//        }
    }//GEN-LAST:event_tblDoanhThuMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ui.swing.Combobox cbbFiltBy;
    private com.ui.swing.Combobox cbbFiltBy1;
    private com.ui.swing.Combobox cbbMonth;
    private com.ui.swing.Combobox cbbNam;
    private com.ui.swing.Combobox cbbSoNgay;
    private com.ui.swing.Combobox cbbSort;
    private com.ui.swing.Combobox cbbSortBy;
    private com.ui.swing.chart.Chart chartDoanhThu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblMonth;
    private javax.swing.JLabel lblSL;
    private javax.swing.JLabel lblSoNgay;
    private javax.swing.JLabel lblSort;
    private javax.swing.JLabel lblSort1;
    private javax.swing.JPanel pnlFilt;
    private com.ui.swing.Table tblDoanhThu;
    private javax.swing.JTextField txtSL;
    // End of variables declaration//GEN-END:variables
}
