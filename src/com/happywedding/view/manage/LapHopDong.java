/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happywedding.view.manage;

//import com.happywedding.Report.GetClass;
import com.happywedding.Report.GetClass;
import com.happywedding.dao.ChiTietDatMonDAO;
import com.happywedding.dao.ChiTietDichVuDAO;
import com.happywedding.dao.ChiTietDichVuDiKemDAO;
import com.happywedding.dao.HoaDonDAO;
import com.happywedding.dao.HopDongDAO;
import com.happywedding.dao.KhachHangDAO;
import com.happywedding.dao.SanhDAO;
import com.happywedding.dao.TrangThaiHopDongDAO;
import com.happywedding.helper.AppStatus;
import com.happywedding.helper.DateHelper;
import com.happywedding.helper.DialogHelper;
import com.happywedding.helper.EnglishNumberToWords;
import com.happywedding.helper.JDBCHelper;
import com.happywedding.helper.ShareHelper;
import com.happywedding.model.ChiTietDatMon;
import com.happywedding.model.ChiTietDichVu;
import com.happywedding.model.DichVuDatMon;
import com.happywedding.model.HoaDon;
import com.happywedding.model.HopDong;
import com.happywedding.model.HopDongDichVu;
import com.happywedding.model.HopDongPDF;
import com.happywedding.model.KhachHang;
import com.happywedding.model.Sanh;
import com.happywedding.model.TrangThaiHopDong;
import com.ui.swing.Combobox.EnabledJComboBoxRenderer;
import com.ui.swing.datechooser.DateChooser;
import com.ui.swing.timepicker.EventTimePicker;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import java.io.InputStream;
import java.net.URL;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author ADMIN
 */
public class LapHopDong extends javax.swing.JPanel {

    static class Role {

        static String QUANLY = "QLCC";
        static String TIEPTAN = "TIEPTAN";
    }

    static class StatusHopDong {

        static String DANGLAP = "DANGLAP";
        static String CHODUYET = "CHODUYET";
        static String CHOKYKET = "CHOKYKET";
        static String THUCHIEN = "THUCHIEN";
        static String DATHUCHIEN = "DATHUCHIEN";
        static String XOA = "XOA";
    }

    private HopDong hopDong;
    private KhachHang khachHang;
    private String maHD;

    private HopDongDAO hopDongDAO = new HopDongDAO();
    private SanhDAO sanhDAO = new SanhDAO();
    private KhachHangDAO khachHangDAO = new KhachHangDAO();
    private HoaDonDAO hoaDonDAO = new HoaDonDAO();
    private ChiTietDatMonDAO datMonDAO = new ChiTietDatMonDAO();
    private ChiTietDichVuDAO dichVuDAO = new ChiTietDichVuDAO();
    private ChiTietDichVuDiKemDAO dichVuDiKemDAO = new ChiTietDichVuDiKemDAO();

    private Calendar calendar = Calendar.getInstance();
    private DateChooser dateChooser = new DateChooser();
    private DateChooser dateChooser2 = new DateChooser();
    private String statusHopDong = StatusHopDong.DANGLAP;
    private boolean isCreate; // đang được tạo hay không
    private boolean isEdit; // có được chỉnh sữa hay
    private boolean isHoaDon;
    private boolean isValid = true;
    private List<Boolean> checkedDichVu = new ArrayList<Boolean>(Collections.nCopies(6, false));
    //private List<Boolean> checkIsValid = new ArrayList<>();

    private List<Sanh> listSanh = new ArrayList<>();
    private List<TrangThaiHopDong> listTrangThai = new ArrayList<>();

    /**
     * Creates new form LapHopDong
     *
     * @param isCreate là trạng thái đang tạo hợp đồng hay đang xem lại hợp đồng
     * @param maHD
     */
    public LapHopDong(boolean isCreate, String maHD, boolean isHoaDon) {
        initComponents();
        this.isCreate = isCreate;
        this.maHD = maHD;
        this.isHoaDon = isHoaDon;
        init();

        phanQuyen();
        if (isCreate) {
            isEdit();
            txtMaHD.setText(this.maHD);
        }
        AppStatus.lapHopDong = this;

    }

    public LapHopDong(String MaHD) {
        initComponents();

    }

    public void init() {

        // khởi tạo ngày và giờ
        timePickerBatDau.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ketThuc = ShareHelper.to24Hour(timePickerKetThuc.getSelectedTime());
                String batDau = ShareHelper.to24Hour(timePickerBatDau.getSelectedTime());

                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                Date d1;
                Date d2;

                try {

                    if (txtKetThuc.getText().length() == 0) {
                        d1 = sdf.parse(batDau);

                        timePickerKetThuc.setSelectedTime(new Date(d1.getTime() + 2 * 3600000));
                        txtKetThuc.setText((sdf.format(new Date(d1.getTime() + 2 * 3600000))));
                        txtBatDau.setText(batDau);
                        return;
                    }

                    d1 = sdf.parse(batDau);
                    d2 = sdf.parse(ketThuc);

                    if (d2.getTime() - d1.getTime() < 2 * 3600000 && batDau != null && ketThuc != null) {
                        txtBatDau.setText(batDau);

                        d2.setTime(d1.getTime() + 2 * 3600000);
                        timePickerKetThuc.setSelectedTime(d2);
                        txtKetThuc.setText(sdf.format(d2));

                    } else {
                        txtBatDau.setText(batDau);

                        d2.setTime(d1.getTime() + 2 * 3600000);
                        timePickerKetThuc.setSelectedTime(d2);
                        txtKetThuc.setText(ketThuc);

                    }
                } catch (ParseException ex) {
                    Logger.getLogger(LapHopDong.class.getName()).log(Level.SEVERE, null, ex);
                }
                //   checkSanh();
//                String myDayTime = txtNgayToChuc.getText() + " " + ShareHelper.to24Hour(timePickerBatDau.getSelectedTime());
//                //System.out.println(txtNgayToChuc.getText() +" " + ShareHelper.to24Hour(timePickerBatDau.getSelectedTime()) );
//                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
//                try {
//                    //date get lên từ cơ sở dữ liệu
//                    ResultSet rs = JDBCHelper.executeQuery("SELECT * FROM Test");
//                    rs.next();
//                    Timestamp time = rs.getTimestamp(1);
//
//                    String date = time.toString();
//                    Date date2 = DateHelper.toDate(date, "yyyy-MM-dd HH:mm:");
//                    String date3 = format.format(date2);
//                    System.out.println(date2);
//                    //JDBCHelper.executeUpdate("INSERT  Test (ThoiGian) VALUES ( ? )", datetime);
//                    // SwingConstants.SOUTH
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                    Logger.getLogger(LapHopDong.class.getName()).log(Level.SEVERE, null, ex);
//                }
            }

        });
        timePickerKetThuc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ketThuc = ShareHelper.to24Hour(timePickerKetThuc.getSelectedTime());
                String batDau = ShareHelper.to24Hour(timePickerBatDau.getSelectedTime());

                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                Date d1;
                Date d2;

                try {

                    if (txtBatDau.getText().length() == 0) {
                        d2 = sdf.parse(ketThuc);

                        timePickerBatDau.setSelectedTime(new Date(d2.getTime() - 2 * 3600000));
                        txtBatDau.setText((sdf.format(new Date(d2.getTime() - 2 * 3600000))));
                        txtKetThuc.setText(ketThuc);
                        return;
                    }

                    d1 = sdf.parse(batDau);
                    d2 = sdf.parse(ketThuc);

                    if (d2.getTime() - d1.getTime() < 2 * 3600000 && batDau != null && ketThuc != null) {
                        txtKetThuc.setText(ketThuc);

                        d1.setTime(d2.getTime() - 2 * 3600000);
                        timePickerBatDau.setSelectedTime(d1);
                        txtBatDau.setText(sdf.format(d1));

                    } else {
                        txtKetThuc.setText(ketThuc);

                        d1.setTime(d2.getTime() - 2 * 3600000);
                        timePickerBatDau.setSelectedTime(d1);
                        txtBatDau.setText(batDau);

                    }
                } catch (ParseException ex) {
                    Logger.getLogger(LapHopDong.class.getName()).log(Level.SEVERE, null, ex);
                }
                // checkSanh();
            }

        });

        dateChooser.setTextRefernce(txtNgayToChuc);

        //load các dữ liệu cần thiết
        loadSanh();
        loadTrangThai();

        // để xét xem nên ẩn cái nào , hiện cái nào
        if (isCreate == true) {

            txtNgayToChuc.setText("");
            initCreate();
        } else {
            initView();
        }
        checkedDichVu();

        //ITODO   Load list sanh và add vào combobox
    }

    public void initCreate() {
        statusHopDong = StatusHopDong.DANGLAP;

        txtNguoiLap.setText(AppStatus.USER.getHoTen());
        txtNgayLap.setText(DateHelper.toString(DateHelper.now(), "dd/MM/yyyy"));

        timePickerBatDau.setSelectedTime(DateHelper.toDate("8:00", "HH:mm"));
        timePickerKetThuc.setSelectedTime(DateHelper.toDate("12:00", "HH:mm"));

        reloadStatus();
        reloadHopDong();
        disableFeature();

    }

    public void initView() {
        hopDong = hopDongDAO.findById(maHD);
        khachHang = khachHangDAO.findById(maHD);

        statusHopDong = hopDong.getTrangThai();
        btnSaveInfo.setVisible(false);
        fillFormHopDong(khachHang, hopDong);

        reloadStatus();
        reloadHopDong();

    }

    public void loadCbbTrangThai(List<TrangThaiHopDong> list) {

    }

    // các hàm để thêm sửa xóa
    public boolean insertKhachHang() {
        KhachHang khachHang = getModelKhachHang();
        if (khachHang != null) {
            try {
                boolean rs = khachHangDAO.insert(khachHang);
                this.khachHang = khachHangDAO.findById(maHD);
                return true;
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
        return false;

    }

    public boolean insertHopDong() {
        HopDong hopDong = getModelHopDong();
        if (hopDong != null) {
            try {
                boolean rs = hopDongDAO.insert(hopDong);
                hopDong = hopDongDAO.findById(maHD);
                insertKhachHang();
                return true;
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
        return false;
    }

    public boolean updateHopDong() {
        updateKhacHang();

        HopDong hopDong = getModelHopDong();
        if (hopDong != null) {
            try {
                boolean rs = hopDongDAO.update(hopDong);
                this.hopDong = hopDongDAO.findById(maHD);
                return true;
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
        return false;
    }

    public boolean updateKhacHang() {
        KhachHang khacHang = getModelKhachHang();
        if (khacHang != null) {
            try {
                boolean rs = khachHangDAO.update(khacHang);
                this.khachHang = khachHangDAO.findById(maHD);
                return true;
            } catch (Exception e) {
            }
        }
        return false;
    }

    /**
     * khi làm thay đổi trạng thái hợp đồng chỉ cần gọi hàm này
     *
     * @param trangThai là mã trạng thái của hơp đồng hiện tại, sẽ đc chuyển đến
     * trạng thái kế tiếp
     */
    public void updateTrangThaiHopDong(String trangThai) {

        if (trangThai.equals(StatusHopDong.CHODUYET)) {
            statusHopDong = StatusHopDong.CHOKYKET;
            // hopDongDAO.updateTrangThai( maHD,  statusHopDong);    //DAO TODO   // hàm update trạng thái hợp đồng
        } else if (trangThai.equals(StatusHopDong.CHOKYKET)) {
            statusHopDong = StatusHopDong.THUCHIEN;
            //hopDongDAO.updateTrangThai(String maHD, String TrangThai);    //DAO TODO   // hàm update trạng thái hợp đồng
        } else if (trangThai.equals(StatusHopDong.THUCHIEN)) {
            statusHopDong = StatusHopDong.DATHUCHIEN;
            // hopDongDAO.updateTrangThai( maHD,  statusHopDong);    //DAO TODO   // hàm update trạng thái hợp đồng
        } else if (trangThai.equals(StatusHopDong.DATHUCHIEN)) {
            statusHopDong = StatusHopDong.XOA;
            // hopDongDAO.updateTrangThai( maHD,  statusHopDong);    //DAO TODO   // hàm update trạng thái hợp đồng
        } else if (trangThai.equals(StatusHopDong.DANGLAP)) {
            statusHopDong = StatusHopDong.CHODUYET;
        }
    }
    // các hàm lấy thông tin từ database

    /*
    Lấy thông tin khách hàng 
     */
    public KhachHang getKhachHang(String maKH) {

        KhachHang khachHang = khachHangDAO.findById(maKH);

        return khachHang;
    }

    /*
    Lấy danh sách các sảnh chưa đặt trong ngày tổ chức
     */
    // các hàm lấy thông tin từ database

    /*
    Lấy danh sách các sảnh chưa đặt trong ngày tổ chức
     */
    public void loadSanh() {

        listSanh = sanhDAO.select();
        DefaultComboBoxModel cbbModel = (DefaultComboBoxModel) cbbSanh.getModel();
//        DefaultListSelectionModel model = new DefaultListSelectionModel();
//
//        model.addSelectionInterval(0, 3);
//
//        //model.addSelectionInterval(6, 6);
//
//        EnabledJComboBoxRenderer enableRenderer = new EnabledJComboBoxRenderer(model);
//
//        cbbSanh.setRenderer(enableRenderer);
        cbbModel.removeAllElements();
        for (Sanh s : listSanh) {
            cbbModel.addElement(s);
        }
        cbbSanh.setSelectedIndex(-1);

    }

    public void loadTrangThai() {
        listTrangThai = new TrangThaiHopDongDAO().select();
    }

    /*
    Chỉ cần quan tâm thông tin các khoản chi phí. 
    Khi dịch vụ đc cập nhật thì thông tin chi phí trên form LapHopDong cũng phải reload
     */
    public void reloadHopDong() {
        //  System.out.println("reload");
        hopDong = hopDongDAO.findById(maHD);
        khachHang = khachHangDAO.findById(maHD);

        if (hopDong != null) {
            List<Long> chiPhi = hopDongDAO.tinhToan(maHD);
            txtChiPhi.setText(ShareHelper.toMoney(chiPhi.get(0)));
            txtChiPhiPhatSinh.setText(ShareHelper.toMoney(chiPhi.get(1)));
            long tt = ((chiPhi.get(0) + chiPhi.get(1)));
            long tienThue = (long) (((chiPhi.get(0) + chiPhi.get(1))) * ((Integer.parseInt(txtThue.getText())) / 100.0));
            long tongTien = tt + tienThue;
            long tienCoc = (long) (tongTien * 0.5);
            txtTongTien.setText(ShareHelper.toMoney(tongTien));
            txtTienCoc.setText(ShareHelper.toMoney(tienCoc));
            lblThanhChu.setText("( " + EnglishNumberToWords.convert(tongTien) + " )");
            txtThueThanhTien.setText((ShareHelper.toMoney(tienThue)));
            hopDongDAO.updateChiPhi(tienCoc, tongTien, maHD);
            checkedDichVu();
        }
    }

    public void reloadHopDongVoiSanh(String maSanh, int soLuongBan) {
        //  System.out.println("reload");
        hopDong = hopDongDAO.findById(maHD);

        List<Long> chiPhi = hopDongDAO.tinhToan(maHD, maSanh, soLuongBan);
        txtChiPhi.setText(ShareHelper.toMoney(chiPhi.get(0)));
        txtChiPhiPhatSinh.setText(ShareHelper.toMoney(chiPhi.get(1)));
        long tt = ((chiPhi.get(0) + chiPhi.get(1)));
        long tienThue = (long) (((chiPhi.get(0) + chiPhi.get(1))) * ((Integer.parseInt(txtThue.getText())) / 100.0));
        long tongTien = tt + tienThue;
        long tienCoc = (long) (tongTien * 0.5);
        txtTongTien.setText(ShareHelper.toMoney(tongTien));
        txtTienCoc.setText(ShareHelper.toMoney(tienCoc));
        lblThanhChu.setText("( " + EnglishNumberToWords.convert(tongTien) + " )");
        txtThueThanhTien.setText((ShareHelper.toMoney(tienThue)));
        hopDongDAO.updateChiPhi(tienCoc, tongTien, maHD);

    }

    public void reloadStatus() {

        for (TrangThaiHopDong tt : listTrangThai) {
            if (statusHopDong.equals(tt.getMaTT())) {
                txtTrangThai.setText(tt.getTenTT());
                break;
            }
        }
    }

    public void checkedDichVu(String maDV, boolean isSave) {
        int thuTuDichVu;
        switch (maDV) {
            case "TTBANTIEC":
                thuTuDichVu = 0;
                break;
            case "TTCONG":
                thuTuDichVu = 1;
                break;
            case "TTSANKHAU":
                thuTuDichVu = 2;
                break;
            case "DATMON":
                thuTuDichVu = 3;
                break;
            case "NGHETHUAT":
                thuTuDichVu = 4;
                break;
            case "DICHVUDIKEM":
                thuTuDichVu = 5;
                break;
            default:
                thuTuDichVu = 0;

        }

        checkedDichVu.remove(thuTuDichVu);
        checkedDichVu.add(thuTuDichVu, isSave);

        //checkedDichVu();
    }

    public void checkedDichVu() {
        boolean isValid = true;
        if (dichVuDAO.selectDichVu(maHD, "TTBANTIEC") != null) {
            btnTTBan.setBorderColor(new Color(226, 76, 56));
            checkedDichVu("TTBANTIEC", true);
        }
        if (dichVuDAO.selectDichVu(maHD, "TTCONG") != null) {
            btnTrangTriCong.setBorderColor(new Color(226, 76, 56));
            checkedDichVu("TTCONG", true);
        }
        if (dichVuDAO.selectDichVu(maHD, "TTSANKHAU") != null) {
            btnTTSanKhau.setBorderColor(new Color(226, 76, 56));
            checkedDichVu("TTSANKHAU", true);
        }
        if (dichVuDAO.selectDichVu(maHD, "NGHETHUAT") != null) {
            btnNgheThuat.setBorderColor(new Color(226, 76, 56));
            checkedDichVu("NGHETHUAT", true);
        }
        if (datMonDAO.selectThucDonChinh(maHD) != null) {
            btnDatMon.setBorderColor(new Color(226, 76, 56));
            checkedDichVu("DATMON", true);
        }
        if (dichVuDiKemDAO.selectHopDongDichVuDiKem(maHD) != null) {
            btnDVDK.setBorderColor(new Color(226, 76, 56));
            checkedDichVu("DICHVUDIKEM", true);
        }
    }

    // các hàm lấy dữ liệu từ form
    /*
    Lấy thông tin khách hàng từ form
     */
    public KhachHang getModelKhachHang() {
        // valid thông tin

        // lấy thông tin từ form;
        KhachHang kh = new KhachHang();

        kh.setMaHD(maHD);
        kh.setHoTen(txtHoTenKhachHang.getText());
        kh.setSoDienThoai(txtSDT.getText());
        kh.setCCCD(txtCMND.getText());
        kh.setHoTenCoDau(txtHoTenCoDau.getText());
        kh.setHoTenChuRe(txtHoTenChuRe.getText());
        kh.setDiaChi(taDiaChi.getText());

        return kh;
    }

    /*
    Lấy thông tin hợp đồng từ form
     */
    public HopDong getModelHopDong() {
        // valid thông tin
        String maHD = txtMaHD.getText();
        Date ngayToChuc = DateHelper.toDate(txtNgayToChuc.getText(), "dd/MM/yyyy");
        String thoiGianBatDau = txtBatDau.getText();
        String thoiGianKetThuc = txtKetThuc.getText();
        String maSanh = ((Sanh) cbbSanh.getSelectedItem()).getMaSanh();

        // lấy thông tin từ form;
        HopDong hopDong = new HopDong();

        hopDong.setMaHD(maHD);
        hopDong.setMaNL(AppStatus.USER.getMaNV());
        hopDong.setNgayLap(DateHelper.toDate(txtNgayLap.getText(), "dd/MM/yyyy"));

        if (!statusHopDong.equals(StatusHopDong.DANGLAP) && !statusHopDong.equals(StatusHopDong.CHODUYET) && AppStatus.ROLE.equals("QLCC")) {
            hopDong.setMaNL(AppStatus.USER.getMaNV());
            hopDong.setNgayLap(DateHelper.toDate(txtNgayDuyet.getText(), "dd/MM/yyyy"));
        }

        hopDong.setTrangThai(statusHopDong);

        hopDong.setSanh(maSanh);
        hopDong.setNgayToChuc(ngayToChuc);
        hopDong.setThoiGianBatDau(thoiGianBatDau);
        hopDong.setThoiGianKetThuc(thoiGianKetThuc);

        hopDong.setSoLuongBan(Integer.parseInt(txtSLBan.getText()));

        hopDong.setThue(Integer.parseInt(txtThue.getText()));
        hopDong.setTienCoc(ShareHelper.toMoney(txtTienCoc.getText()));
        hopDong.setTongTien(ShareHelper.toMoney(txtTongTien.getText()));

        return hopDong;
    }

    // các hàm hiển thị thông tin lên form
    /*
    Hiển thị tất cả thông tin lên form
     */
    public void fillFormHopDong(KhachHang khachHang, HopDong hopDong) {
        // hiển thị thông tin lên form
        txtHoTenKhachHang.setText(khachHang.getHoTen());
        txtCMND.setText(khachHang.getCCCD());
        txtSDT.setText(khachHang.getSoDienThoai());
        taDiaChi.setText(khachHang.getDiaChi());

        txtHoTenChuRe.setText(khachHang.getHoTenChuRe());
        txtHoTenCoDau.setText(khachHang.getHoTenCoDau());

        txtMaHD.setText(hopDong.getMaHD());
        txtNguoiLap.setText(hopDong.getTenNguoiLap());
        txtNgayLap.setText(DateHelper.toString(hopDong.getNgayLap(), "dd/MM/yyyy"));

        txtTrangThai.setText(hopDong.getTenTrangThai());

        txtNgayToChuc.setText(DateHelper.toString(hopDong.getNgayToChuc(), "dd/MM/yyyy"));
        txtBatDau.setText(hopDong.getThoiGianBatDau().substring(0, 5));
        txtKetThuc.setText(hopDong.getThoiGianKetThuc().substring(0, 5));
        timePickerBatDau.setSelectedTime(DateHelper.toDate(hopDong.getThoiGianBatDau(), "HH:mm"));
        timePickerKetThuc.setSelectedTime(DateHelper.toDate(hopDong.getThoiGianKetThuc(), "HH:mm"));
        txtSLBan.setText(hopDong.getSoLuongBan() + "");

        for (Sanh s : listSanh) {
            if (s.getTenSanh().equals(hopDong.getSanh())) {
                cbbSanh.setSelectedItem(s);
            }
        }

        txtTienCoc.setText(ShareHelper.toMoney(hopDong.getTienCoc()));

        List<Long> chiPhi = hopDongDAO.tinhToan(maHD);
        long tienThue = (long) (((chiPhi.get(0) + chiPhi.get(1))) * ((Integer.parseInt(txtThue.getText())) / 100.0));
        txtChiPhi.setText(ShareHelper.toMoney(chiPhi.get(0)));
        txtChiPhiPhatSinh.setText(ShareHelper.toMoney(chiPhi.get(1)));
        txtThueThanhTien.setText((ShareHelper.toMoney(tienThue)));
        txtTongTien.setText(ShareHelper.toMoney(hopDong.getTongTien()));
        lblThanhChu.setText("( " + EnglishNumberToWords.convert(hopDong.getTongTien()) + " )");

        if (hopDong.getNgayDuyet() != null) {
            txtNgayDuyet.setText(DateHelper.toString(hopDong.getNgayDuyet(), "dd/MM/yyyy"));
            txtNguoiDuyet.setText(hopDong.getTenNguoiDuyet());
        } else {
            if (!statusHopDong.equals(StatusHopDong.DANGLAP)) {
                txtNgayDuyet.setText(DateHelper.toString(DateHelper.now(), "dd/MM/yyyy"));
                txtNguoiDuyet.setText(AppStatus.USER.getMaNV());
            } else {
                if (AppStatus.ROLE.equals("QLCC")) {
                    txtNgayDuyet.setText(DateHelper.toString(DateHelper.now(), "dd/MM/yyyy"));
                    txtNguoiDuyet.setText(AppStatus.USER.getHoTen());
                }
            }
        }
        reloadHopDong();

    }

    // các hàm điều chỉnh trạng thái
    /*
    Khi không hợp đồng chưa được lưu lần 1 sẽ ko đc chọn dịch vụ.
     */
    public void disableFeature() {
        pnlDichVu.setVisible(false);
        lbldv.setVisible(false);
        pnlBtn.setVisible(false);
    }

    /*
    * lấy thông tin trên hợp đồng lưu trước lần 1 và cho chọn dịch vụ
     */
    public void showFeature() {
        pnlDichVu.setVisible(true);
        lbldv.setVisible(true);
        pnlBtn.setVisible(true);

    }

    public void saveHopDong() {
        if (AppStatus.ROLE.equals(Role.TIEPTAN)) {
            btnDanhDauXoa.setVisible(false);
            txtMaHD.setEditable(false);
        } else if (AppStatus.ROLE.equals(Role.QUANLY)) {
            btnDanhDauXoa.setVisible(true);
            txtMaHD.setEditable(false);
        }
    }

    public void isView() {
        isEdit = false;
        txtHoTenKhachHang.setEditable(false);
        txtHoTenChuRe.setEditable(false);
        txtHoTenCoDau.setEditable(false);
        txtSDT.setEditable(false);
        txtCMND.setEditable(false);
        taDiaChi.setEditable(false);
        txtMaHD.setEditable(false);

        txtNgayToChuc.setEditable(false);
        txtBatDau.setEditable(false);

        txtKetThuc.setEditable(false);
        txtSLBan.setEditable(false);
        cbbSanh.setEnabled(false);
        txtHoTenKhachHang.setEditable(false);
    }

    public void isEdit() {
        isEdit = true;
        // System.out.println(isEdit);
        txtHoTenKhachHang.setEditable(true);
        txtHoTenChuRe.setEditable(true);
        txtHoTenCoDau.setEditable(true);
        txtSDT.setEditable(true);
        txtCMND.setEditable(true);
        taDiaChi.setEditable(true);
        txtMaHD.setEditable(false);

        txtNgayToChuc.setEditable(false);
        txtBatDau.setEditable(false);
        txtKetThuc.setEditable(false);
        txtSLBan.setEditable(true);
        cbbSanh.setEnabled(true);

    }

    /*
    TH1: Người dùng là quản lý"
    - Khi đang lập hợp đồng, được lưu, duyệt, xóa
    - Khi đang chờ duyệt sẽ đc lưu, ký kết,chỉnh sửa, và xóa hợp đồng
    - Khi đã ký kết sẽ ko đc thay đổi, chỉ có thể đánh dấu xóa và phân công, xuất hóa đơn.
       khi có hóa đơn thì ko thể xuất lại, trừ khi xóa hóa đơn thì sẽ được xuất lại.
    - Khi đã thực hiện thì chỉ có thể xem 
    TH2:
     - Khi đang lập hợp đồng, được lưu, xóa
    - Khi đã ký kết sẽ ko đc thay đổi, chỉ đc xuất hóa đơn.
       khi có hóa đơn thì ko thể xuất lại, trừ khi xóa hóa đơn thì sẽ được xuất lại.
    - Khi đã thực hiện thì chỉ có thể xem 
    
     */
    public void phanQuyen() {
        if (AppStatus.ROLE.equals(Role.QUANLY)) {
            if (statusHopDong.equals(StatusHopDong.DANGLAP)) {
                isEdit();
                btnSave.setVisible(true);
                btnDuyet.setVisible(false);
                btnKyKet.setVisible(false);
                btnInHopDong.setVisible(false);
                btnDanhDauXoa.setVisible(true);
                btnPhanCong.setVisible(false);
                btnChiPhiPhatSinh.setVisible(false);
                btnXuatHoaDonTam.setVisible(false);
                btnComfimHoanThanh.setVisible(false);
                btnHuyHopDong.setVisible(false);
                btnHuyDuyet.setVisible(false);
                btnXuatHoaDon.setVisible(false);
            } else if (statusHopDong.equals(StatusHopDong.CHODUYET)) {
                isEdit();
                btnSave.setVisible(true);
                btnDuyet.setVisible(true);
                btnKyKet.setVisible(false);
                btnInHopDong.setVisible(false);
                btnDanhDauXoa.setVisible(true);
                btnPhanCong.setVisible(false);
                btnChiPhiPhatSinh.setVisible(false);
                btnXuatHoaDonTam.setVisible(false);
                btnComfimHoanThanh.setVisible(false);
                btnHuyHopDong.setVisible(false);
                btnHuyDuyet.setVisible(false);
                btnXuatHoaDon.setVisible(false);

                if (hopDong.getNgayToChuc().getTime() - DateHelper.now().getTime() < 0) {
                    DialogHelper.alert(this, "Ngày tổ chức không còn hợp lệ do chưa được duyệt.\n Vui lòng điều chỉnh lại ngày tổ chức");
                    btnDuyet.setVisible(false);
                    txtNgayToChuc.setText("");
                }

            } else if (statusHopDong.equals(StatusHopDong.CHOKYKET)) {
                isView();
                btnSave.setVisible(false);
                btnKyKet.setVisible(true);
                btnInHopDong.setVisible(true);
                btnDuyet.setVisible(false);
                btnDanhDauXoa.setVisible(false);
                btnPhanCong.setVisible(false);
                btnChiPhiPhatSinh.setVisible(false);
                btnXuatHoaDonTam.setVisible(false);
                btnComfimHoanThanh.setVisible(false);
                btnHuyHopDong.setVisible(false);
                btnHuyDuyet.setVisible(true);
                btnXuatHoaDon.setVisible(false);

                if (hopDong.getNgayToChuc().getTime() - DateHelper.now().getTime() < 0) {
                    DialogHelper.alert(this, "Ngày tổ chức không còn hợp lệ do chưa được duyệt.\n Vui lòng điều chỉnh lại ngày tổ chức");
                    btnKyKet.setVisible(false);
                    txtNgayToChuc.setText("");
                }

            } else if (statusHopDong.equals(StatusHopDong.THUCHIEN)) {
                isView();
                btnSave.setVisible(false);
                btnKyKet.setVisible(false);
                btnInHopDong.setVisible(true);
                btnDuyet.setVisible(false);
                btnDanhDauXoa.setVisible(false);
                btnPhanCong.setVisible(true);
                btnChiPhiPhatSinh.setVisible(false);
                // btnComfimHoanThanh.setVisible(true);
                btnXuatHoaDonTam.setVisible(true);

                try {
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                    String ngayToChuc = DateHelper.toString(hopDong.getNgayToChuc(), "dd/MM/yyyy");
                    String thoiGianKetThuc = hopDong.getThoiGianKetThuc();

                    String dateHopDong = ngayToChuc + " " + thoiGianKetThuc;
                    Date date = format.parse(dateHopDong);

                    Date date2 = DateHelper.now();
                    if (date.equals(date2) || date2.after(date)) {
                        btnComfimHoanThanh.setVisible(true);

                    } else {
                        btnComfimHoanThanh.setVisible(false);
                    }
                } catch (Exception e) {

                }

                btnHuyHopDong.setVisible(true);
                btnHuyDuyet.setVisible(false);
                btnXuatHoaDon.setVisible(false);
            } else if (statusHopDong.equals(StatusHopDong.DATHUCHIEN)) {
                isView();
                btnSave.setVisible(false);
                btnKyKet.setVisible(false);
                btnDuyet.setVisible(false);
                btnDanhDauXoa.setVisible(false);
                btnPhanCong.setVisible(true);
                btnChiPhiPhatSinh.setVisible(true);
                btnXuatHoaDonTam.setVisible(false);
                btnComfimHoanThanh.setVisible(false);
                btnHuyHopDong.setVisible(false);
                btnHuyDuyet.setVisible(false);
                btnInHopDong.setVisible(false);

                boolean isEdit = false;
                if (hoaDonDAO.selectByID(maHD) != null) {
                    if (hoaDonDAO.selectByID(maHD).getTrangTha() == 0 && !hopDong.getTrangThai().equals(StatusHopDong.XOA)) {

                        isEdit = true;
                        btnXuatHoaDon.setVisible(false);
                    } else {
                        btnXuatHoaDon.setVisible(true);
                    }
                }
                AppStatus.CHIPHIPHATSINH = new ChiPhiPhatSinh(maHD, isEdit);

            } else if (statusHopDong.equals(StatusHopDong.XOA)) {
                isView();
                btnSave.setVisible(false);
                btnKyKet.setVisible(false);
                btnDuyet.setVisible(false);
                btnDanhDauXoa.setVisible(false);
                btnPhanCong.setVisible(true);
                btnChiPhiPhatSinh.setVisible(true);
                btnXuatHoaDonTam.setVisible(false);
                btnComfimHoanThanh.setVisible(false);
                btnHuyHopDong.setVisible(false);
                btnHuyDuyet.setVisible(false);
                btnInHopDong.setVisible(false);
                btnXuatHoaDon.setVisible(false);
            }

        } else if (AppStatus.ROLE.equals(Role.TIEPTAN)) {

            if (statusHopDong.equals(StatusHopDong.DANGLAP)) {
                isEdit();
                btnSave.setVisible(true);
                btnDuyet.setVisible(false);
                btnKyKet.setVisible(false);
                btnInHopDong.setVisible(false);
                btnDanhDauXoa.setVisible(true);
                btnPhanCong.setVisible(false);
                btnChiPhiPhatSinh.setVisible(false);
                btnXuatHoaDonTam.setVisible(false);
                btnComfimHoanThanh.setVisible(false);
                btnHuyHopDong.setVisible(false);
                btnHuyDuyet.setVisible(false);
                btnXuatHoaDon.setVisible(false);
            } else if (statusHopDong.equals(StatusHopDong.CHODUYET)) {
                isView();
                btnSave.setVisible(false);
                btnDuyet.setVisible(false);
                btnKyKet.setVisible(false);
                btnDanhDauXoa.setVisible(false);
                btnPhanCong.setVisible(false);
                btnChiPhiPhatSinh.setVisible(false);
                btnXuatHoaDonTam.setVisible(false);
                btnComfimHoanThanh.setVisible(false);
                btnHuyHopDong.setVisible(false);
                btnHuyDuyet.setVisible(false);
                btnInHopDong.setVisible(false);
                btnXuatHoaDon.setVisible(false);
                
                
            } else if (statusHopDong.equals(StatusHopDong.CHOKYKET)) {
                isView();
                btnSave.setVisible(false);
                btnKyKet.setVisible(false);
                btnDuyet.setVisible(false);
                btnDanhDauXoa.setVisible(false);
                btnPhanCong.setVisible(false);
                btnChiPhiPhatSinh.setVisible(false);
                btnXuatHoaDonTam.setVisible(false);
                btnComfimHoanThanh.setVisible(false);
                btnHuyHopDong.setVisible(false);
                btnHuyDuyet.setVisible(false);
                btnInHopDong.setVisible(true);
                btnXuatHoaDon.setVisible(false);
            } else if (statusHopDong.equals(StatusHopDong.THUCHIEN)) {
                isView();
                btnSave.setVisible(false);
                btnKyKet.setVisible(false);
                btnDuyet.setVisible(false);
                btnDanhDauXoa.setVisible(false);
                btnPhanCong.setVisible(false);
                btnChiPhiPhatSinh.setVisible(false);
                btnXuatHoaDonTam.setVisible(true);
                btnChiPhiPhatSinh.setVisible(false);
                btnComfimHoanThanh.setVisible(false);
                btnHuyHopDong.setVisible(false);
                btnHuyDuyet.setVisible(false);
                btnInHopDong.setVisible(true);
                btnXuatHoaDon.setVisible(false);

            } else if (statusHopDong.equals(StatusHopDong.DATHUCHIEN)) {
                isView();
                btnSave.setVisible(false);
                btnKyKet.setVisible(false);
                btnDuyet.setVisible(false);
                btnDanhDauXoa.setVisible(false);
                btnPhanCong.setVisible(false);
                btnChiPhiPhatSinh.setVisible(true);
                btnXuatHoaDonTam.setVisible(false);
                btnComfimHoanThanh.setVisible(false);
                btnHuyHopDong.setVisible(false);
                btnHuyDuyet.setVisible(false);
                btnInHopDong.setVisible(false);

                boolean isEdit = false;
                if (hoaDonDAO.selectByID(maHD) != null) {
                    if (hoaDonDAO.selectByID(maHD).getTrangTha() == 0 && !hopDong.getTrangThai().equals(StatusHopDong.XOA)) {

                        isEdit = true;

                    }
                    if (hoaDonDAO.selectByID(maHD).getTrangTha() == 0) {
                        btnXuatHoaDon.setVisible(false);
                    } else {
                        btnXuatHoaDon.setVisible(true);
                    }
                }
                AppStatus.CHIPHIPHATSINH = new ChiPhiPhatSinh(maHD, isEdit);

            } else if (statusHopDong.equals(StatusHopDong.XOA)) {
                isView();
                btnSave.setVisible(false);
                btnKyKet.setVisible(false);
                btnDuyet.setVisible(false);
                btnDanhDauXoa.setVisible(false);
                btnPhanCong.setVisible(true);
                btnChiPhiPhatSinh.setVisible(true);
                btnXuatHoaDonTam.setVisible(false);
                btnComfimHoanThanh.setVisible(false);
                btnHuyHopDong.setVisible(false);
                btnHuyDuyet.setVisible(false);
                btnInHopDong.setVisible(false);
                btnXuatHoaDon.setVisible(false);
            }
        }

    }

    public boolean validHopDong() {
        boolean isValid = true;

        if (!checkName(txtHoTenKhachHang, txtSDT, null)) {
            return false;
        } else if (!checkName(txtHoTenCoDau, txtSDT, null)) {
            return false;
        } else if (!checkName(txtHoTenChuRe, txtSDT, null)) {
            return false;
        } else if (!checkSDT(null)) {
            return false;
        } else if (!checkCCCD(null)) {
            return false;
        }

        if (txtHoTenKhachHang.getText().trim().length() == 0
                || txtSDT.getText().trim().length() == 0
                || txtCMND.getText().trim().length() == 0
                || txtHoTenChuRe.getText().trim().length() == 0
                || txtHoTenCoDau.getText().trim().length() == 0
                || taDiaChi.getText().trim().length() == 0) {
            isValid = false;
            DialogHelper.alertError(this, "Nhập đầy đủ thông tin khách hàng");
            return false;
        }

        if (txtSLBan.getText().length() == 0) {
            DialogHelper.alertError(this, "Nhập số bàn");
            return false;
        }

        if (txtNgayToChuc.getText().length() != 0) {
            if (DateHelper.toDate(txtNgayToChuc.getText(), "dd/MM/yyyy").getTime() - DateHelper.toDate(DateHelper.toString(DateHelper.now(), "dd/MM/yyyy"), "dd/MM/yyyy").getTime() < 24 * 3600000) {
                DialogHelper.alertError(this, "Ngày tổ chức sau ít nhất một ngày đặt tiệc");
                txtNgayToChuc.setText("");
                return false;
            }
        }

        if (txtNgayToChuc.getText().length() == 0 || txtBatDau.getText().length() == 0 || txtKetThuc.getText().length() == 0) {
            DialogHelper.alertError(this, "Nhập đầy đủ thời gian");
            return false;
        }

        if (cbbSanh.getSelectedIndex() == -1) {
            DialogHelper.alertError(this, "Chọn Sảnh");
            return false;
        } else {
            long sucChua = (((Sanh) cbbSanh.getSelectedItem()).getSucChua());
            if (sucChua - (Integer.parseInt(txtSLBan.getText())) * 10 < 0) {
                DialogHelper.alert(this, "Sảnh này sức chứa tối đa là " + sucChua + " người");
                return false;
            }
        }

        HopDong hopDong = hopDongDAO.checkSanh(((Sanh) cbbSanh.getSelectedItem()).getMaSanh(), DateHelper.toDate(txtNgayToChuc.getText(), "dd/MM/yyyy"),
                txtBatDau.getText(), txtKetThuc.getText(), this.maHD);
        if (hopDong != null) {
//            try {
//                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
////                taThongBaoSanh.setText("Sảnh đã được đặt tại hợp đồng: " + hopDong.getMaHD()
////                        + "\nNgày tổ chức: " + DateHelper.toString(hopDong.getNgayToChuc(), "dd/MM/yyyy")
////                        + "\nGiờ bắt đầu: " + sdf.format(sdf.parse(hopDong.getThoiGianBatDau()))
////                        + "\nGiờ kết thúc: " + sdf.format(sdf.parse(hopDong.getThoiGianKetThuc()))
////                );
//                DialogHelper.alertError(this, "Không thể đặt sảnh này");
//                return false;
//            } catch (ParseException ex) {
//                Logger.getLogger(LapHopDong.class.getName()).log(Level.SEVERE, null, ex);
//            }
        } else {
            //taThongBaoSanh.setText("Sảnh chưa có lịch đặt tiệc");
            return true;
        }
        return false;

    }

    public List<ChiTietDichVu> getListTTBanTiec() {
        List<ChiTietDichVu> list = new ArrayList<>();
        ChiTietDichVuDAO ctdvDAO = new ChiTietDichVuDAO();
        ChiTietDichVu ctAoGhe = ctdvDAO.selectChiTietDichVuNoCustom(maHD, "TTBANTIEC", TrangTriBanTiec.VatTrangTri.AOGHE);
        ChiTietDichVu ctTraiBan = ctdvDAO.selectChiTietDichVuNoCustom(maHD, "TTBANTIEC", TrangTriBanTiec.VatTrangTri.TRAIBAN);
        ChiTietDichVu ctHoaChuDao = ctdvDAO.selectChiTietDichVuCustom(maHD, "TTBANTIEC", TrangTriBanTiec.VatTrangTri.HOACHUDAO);

        list.add(ctAoGhe);
        list.add(ctTraiBan);
        list.add(ctHoaChuDao);
        return list;

    }

    public List<ChiTietDichVu> getListTTCong() {
        List<ChiTietDichVu> list = new ArrayList<>();
        ChiTietDichVuDAO ctdvDAO = new ChiTietDichVuDAO();
        ChiTietDichVu ctCong = ctdvDAO.selectChiTietDichVuNoCustom(maHD, "TTCONG", TrangTriCong.VatTrangTri.CONG);
        ChiTietDichVu ctTham = ctdvDAO.selectChiTietDichVuNoCustom(maHD, "TTCONG", TrangTriCong.VatTrangTri.THAM);
        ChiTietDichVu ctHoaChuDao = ctdvDAO.selectChiTietDichVuCustom(maHD, "TTCONG", TrangTriCong.VatTrangTri.HOACHUDAO);
        ChiTietDichVu ctHoaPhu = ctdvDAO.selectChiTietDichVuCustom(maHD, "TTCONG", TrangTriCong.VatTrangTri.HOAPHUDAO);
        ChiTietDichVu ctBangTen = ctdvDAO.selectChiTietDichVuNoCustom(maHD, "TTCONG", TrangTriCong.VatTrangTri.BANGTEN);

        list.add(ctCong);
        list.add(ctTham);
        list.add(ctHoaChuDao);
        list.add(ctHoaPhu);
        list.add(ctBangTen);

        return list;

    }

    public List<ChiTietDichVu> getListTTSanKhau() {
        List<ChiTietDichVu> list = new ArrayList<>();
        ChiTietDichVuDAO ctdvDAO = new ChiTietDichVuDAO();

        ChiTietDichVu ctTham = ctdvDAO.selectChiTietDichVuNoCustom(maHD, "TTCONG", TrangTriCong.VatTrangTri.THAM);
        ChiTietDichVu ctHoaChuDao = ctdvDAO.selectChiTietDichVuCustom(maHD, "TTCONG", TrangTriCong.VatTrangTri.HOACHUDAO);
        ChiTietDichVu ctHoaPhu = ctdvDAO.selectChiTietDichVuCustom(maHD, "TTCONG", TrangTriCong.VatTrangTri.HOAPHUDAO);
        ChiTietDichVu ctBangTen = ctdvDAO.selectChiTietDichVuNoCustom(maHD, "TTCONG", TrangTriCong.VatTrangTri.BANGTEN);

        list.add(ctTham);
        list.add(ctHoaChuDao);
        list.add(ctHoaPhu);
        list.add(ctBangTen);

        return list;

    }

    public List<ChiTietDichVu> getListNgheThuat() {
        List<ChiTietDichVu> list = new ArrayList<>();
        ChiTietDichVuDAO ctdvDAO = new ChiTietDichVuDAO();

        ChiTietDichVu ctAmNhac = ctdvDAO.selectChiTietDichVuNoCustom(maHD, "NGHETHUAT", NgheThuat.VatTrangTri.LIENKHUC);
        ChiTietDichVu ctVuDao = ctdvDAO.selectChiTietDichVuNoCustom(maHD, "NGHETHUAT", NgheThuat.VatTrangTri.VUDAO);

        list.add(ctAmNhac);
        list.add(ctVuDao);

        return list;

    }

    public boolean checkName(JTextField txtHoTen, JComponent nextFocus, KeyEvent evt) {

        String name = txtHoTen.getText().trim().replaceAll("\\s+", " ");
        if (evt == null) {
            txtHoTen.setText(name);
            return true;
        }
        char testChar = evt.getKeyChar();
        if (name.length() > 50 && testChar != '\n') {
            evt.consume();
            return false;
        }
        if (((Character.isAlphabetic(testChar))) || Character.isWhitespace(testChar)) {
            if (testChar == '\n') {
                if (name.length() == 0) {
                    nextFocus.requestFocus();
                    return true;
                }
                txtHoTen.setText(name);
                nextFocus.requestFocus();
            }
        } else {
            evt.consume();
        }
        return false;
    }

    public boolean checkCCCD(KeyEvent evt) {

        String cccd = txtCMND.getText();

        if (evt == null) {
            if (cccd.length() != 9 && cccd.length() != 12 && cccd.length() != 0) {
                DialogHelper.alertError(this, "Số Căn cước/Chứng minh không đúng định dạng");
                txtCMND.requestFocus();
                return false;
            } else {
                return true;
            }
        }

        char testChar = evt.getKeyChar();
        if (txtCMND.getText().length() >= 12 && testChar != '\n') {
            evt.consume();
            return true;
        }

        if (!((Character.isDigit(testChar)))) {
            if (testChar == '\n') {

                if (cccd.length() != 9 && cccd.length() != 12 && cccd.length() != 0) {
                    DialogHelper.alertError(this, "Số Căn cước/Chứng minh không đúng định dạng");
                    txtCMND.requestFocus();
                } else {
                    txtHoTenCoDau.requestFocus();
                }
            } else {
                evt.consume();
            }
        }
        return false;
    }

    public boolean checkSDT(KeyEvent evt) {

        String sdt = txtSDT.getText();
        String numberPhone = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";

        if (evt == null) {
            if (!sdt.matches(numberPhone) && sdt.length() != 0) {
                DialogHelper.alertError(this, "Số điện thoại không đúng định dạng");
                txtSDT.requestFocus();
                return false;
            } else {

                return true;
            }
        }
        char testChar = evt.getKeyChar();
        if (txtSDT.getText().length() >= 10 && testChar != '\n') {
            evt.consume();
            return true;
        }
        if (!((Character.isDigit(testChar)))) {
            if (testChar == '\n') {

                if (!sdt.matches(numberPhone) && sdt.length() != 0) {
                    DialogHelper.alertError(this, "Số điện thoại không đúng định dạng");
                    txtSDT.requestFocus();
                    return false;
                } else {
                    txtCMND.requestFocus();
                    return true;
                }

            } else {
                evt.consume();
            }
        }
        return false;
    }

    public boolean checkSanh() {

        if (txtNgayToChuc.getText().length() == 0 || cbbSanh.getSelectedIndex() == -1 || txtBatDau.getText().length() == 0) {
            return false;
        }
        HopDong hopDong = hopDongDAO.checkSanh(((Sanh) cbbSanh.getSelectedItem()).getMaSanh(), DateHelper.toDate(txtNgayToChuc.getText(), "dd/MM/yyyy"),
                txtBatDau.getText(), txtKetThuc.getText(), this.maHD);

        if (hopDong != null) {
//            try {
//                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
////                taThongBaoSanh.setText("Sảnh đã được đặt tại hợp đồng: " + hopDong.getMaHD()
////                        + "\nNgày tổ chức: " + DateHelper.toString(hopDong.getNgayToChuc(), "dd/MM/yyyy")
////                        + "\nGiờ bắt đầu: " + sdf.format(sdf.parse(hopDong.getThoiGianBatDau()))
////                        + "\nGiờ kết thúc: " + sdf.format(sdf.parse(hopDong.getThoiGianKetThuc()))
////                );
//                return false;
//            } catch (ParseException ex) {
//                Logger.getLogger(LapHopDong.class.getName()).log(Level.SEVERE, null, ex);
//            }
        } else {
            //  taThongBaoSanh.setText("Sảnh chưa có lịch đặt tiệc");
            return true;
        }
        return false;
    }

    public void loadSanhPossible() {

        Date date;
        String timeStart, timeEnd;

        if (txtNgayToChuc.getText().length() == 0 || txtBatDau.getText().length() == 0) {
            return;
        }

        date = DateHelper.toDate(txtNgayToChuc.getText(), "dd/MM/yyyy");
        timeStart = txtBatDau.getText();
        timeEnd = txtKetThuc.getText();

        listSanh = sanhDAO.selectSanhPossible(date, timeStart, timeEnd);
        DefaultComboBoxModel cbbModel = (DefaultComboBoxModel) cbbSanh.getModel();

        cbbModel.removeAllElements();
        for (Sanh s : listSanh) {
            s.getTenSanh();
            s.getSucChua();
            s.getGiaBan();
            cbbModel.addElement(s);
        }
        cbbSanh.setSelectedIndex(-1);

    }

    private static net.sf.jasperreports.engine.JasperReport loadJasperReport(String reportName) {
        try {
            System.out.println(reportName);
            File f = new File(reportName + ".jasper");
            if (!f.exists()) {
                JasperCompileManager.compileReportToFile(reportName + ".jrxml", reportName + ".jasper");
                f = new File(reportName + ".jasper");
            }
            net.sf.jasperreports.engine.JasperReport jr = (net.sf.jasperreports.engine.JasperReport) JRLoader.loadObject(f);
            return jr;
        } catch (Exception e) {
            System.out.println(e);
//            e.printStackTrace();
        }
        return null;
    }

    void In() {
        hopDong = hopDongDAO.findById(maHD);
        try {
            Map<String, Object> parameters = new HashMap<String, Object>();
            Connection con = JDBCHelper.getConnection();

            String maHD = this.maHD;
            String maTD = datMonDAO.selectThucDonChinh(maHD);
            String maTDPhu = datMonDAO.selectThucDonPhu(maHD);
            long giaThucDonPhu = 0;
            if (!maTDPhu.equals(maTD)) {
//                maTDPhu = "";
                giaThucDonPhu = datMonDAO.selectDichVuDatMon(maHD, datMonDAO.selectThucDonPhu(maHD)).getChiPhi()
                        + datMonDAO.selectDichVuDatMon(maHD, datMonDAO.selectThucDonPhu(maHD)).getChiPhiPhatSinh();
            } else {
                maTDPhu = "";
            }
            Sanh sanh = new SanhDAO().findByName((hopDong.getSanh()));

            long ttdv = dichVuDAO.selectDichVu(maHD, "TTCONG").getChiPhi()
                    + dichVuDAO.selectDichVu(maHD, "TTCONG").getChiPhiPhatSinh()
                    + dichVuDAO.selectDichVu(maHD, "TTBANTIEC").getChiPhi()
                    + dichVuDAO.selectDichVu(maHD, "TTBANTIEC").getChiPhiPhatSinh()
                    + dichVuDAO.selectDichVu(maHD, "TTSANKHAU").getChiPhi()
                    + dichVuDAO.selectDichVu(maHD, "TTSANKHAU").getChiPhiPhatSinh()
                    + dichVuDAO.selectDichVu(maHD, "NGHETHUAT").getChiPhi()
                    + dichVuDAO.selectDichVu(maHD, "NGHETHUAT").getChiPhiPhatSinh()
                    + datMonDAO.selectDichVuDatMon(maHD, maTD).getChiPhi()
                    + datMonDAO.selectDichVuDatMon(maHD, maTD).getChiPhiPhatSinh()
                    + dichVuDiKemDAO.selectHopDongDichVuDiKem(maHD).getChiPhi() + giaThucDonPhu
                    + dichVuDiKemDAO.selectHopDongDichVuDiKem(maHD).getChiPhiPhatSinh();
            //  + (sanh.getGiaBan() * hopDong.getSoLuongBan()) + sanh.getGiaThueSanh();
            long giaSanh = (sanh.getGiaBan() * hopDong.getSoLuongBan()) + sanh.getGiaThueSanh();
            ttdv += giaSanh;
            String tongTienDichVu = ShareHelper.toMoney(ttdv);
            String thue = ShareHelper.toMoney((long) ((ShareHelper.toMoney(txtChiPhi.getText()) + ShareHelper.toMoney(txtChiPhiPhatSinh.getText())) * (10 / 100.0)));
            String tongTien = ShareHelper.toMoney(hopDong.getTongTien());
            String tienCoc = ShareHelper.toMoney(hopDong.getTienCoc());
            String tienConLai = ShareHelper.toMoney(hopDong.getTongTien() - hopDong.getTienCoc());
            String thanhChu = EnglishNumberToWords.convert(hopDong.getTongTien());
            String thanhChu2 = EnglishNumberToWords.convert(ShareHelper.toMoney(tienConLai));

            String thanhchu3 = thanhChu.substring(0, 1);
            String thanhChu4 = thanhChu.substring(1, thanhChu.length());
            thanhChu = thanhchu3.toUpperCase() + thanhChu4;
            String thanhchu5 = thanhChu.substring(0, 1);
            String thanhChu6 = thanhChu.substring(1, thanhChu.length());
            thanhChu2 = thanhchu5.toUpperCase() + thanhChu6;


            URL localPackage = this.getClass().getResource("");
            URL urlLoader = LapHopDong.class.getProtectionDomain().getCodeSource().getLocation();
            //String localDir = localPackage.getPath();
            String loaderDir = urlLoader.getPath().toString();
            //System.out.printf("loaderDir = %s\n localDir = %s\n", loaderDir, localDir);
            String dirPath = loaderDir.substring(0, loaderDir.length() - 15);

            String realPath = dirPath + "/Report/";
            realPath = realPath.replaceFirst("\\/", "");
            realPath = realPath.replaceAll("%20", " ");
            realPath = realPath.replaceAll("/", "\\\\");
            realPath = realPath.replaceAll("H\\\\", "");
            
            
            
            InputStream file = new GetClass().getClass().getResourceAsStream("/com/happywedding/Report/HoaDon.jrxml");
            JasperDesign desgin = JRXmlLoader.load(file);

            net.sf.jasperreports.engine.JasperReport rpt = JasperCompileManager.compileReport(desgin);

            parameters.put("MaHD", maHD);
            parameters.put("MaTD_Chinh", maTD);
            parameters.put("MaTD_Phu", maTDPhu);
            parameters.put("ThanhTien", thanhChu);
            parameters.put("ThanhTien2", thanhChu2);
            parameters.put("Thue", thue + " VND");
            parameters.put("TongTien", tongTien + " VND");
            parameters.put("TienCoc", tienCoc + " VND");
            parameters.put("TienConLai", tienConLai + " VND");
            parameters.put("tongTienDichVu", tongTienDichVu + " VND");
            parameters.put("SUBREPORT_DIR", realPath);
            System.out.println(parameters);
            JasperPrint p = JasperFillManager.fillReport(rpt, parameters, con);
            JasperViewer.viewReport(p, false);

        } catch (SQLException ex) {
            Logger.getLogger(JasperReport.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(JasperReport.class.getName()).log(Level.SEVERE, null, ex);
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

        timePickerBatDau = new com.ui.swing.timepicker.TimePicker();
        timePickerKetThuc = new com.ui.swing.timepicker.TimePicker();
        jPanel1 = new javax.swing.JPanel();
        btnBack = new com.ui.swing.InkwellButton();
        jPanel2 = new javax.swing.JPanel();
        lblMaNH = new javax.swing.JLabel();
        txtHoTenCoDau = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        txtCMND = new javax.swing.JTextField();
        txtHoTenChuRe = new javax.swing.JTextField();
        txtHoTenKhachHang = new javax.swing.JTextField();
        lblMaNH6 = new javax.swing.JLabel();
        lblMaNH7 = new javax.swing.JLabel();
        lblMaNH8 = new javax.swing.JLabel();
        lblMaNH9 = new javax.swing.JLabel();
        lblMaNH10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taDiaChi = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        lblMaNH11 = new javax.swing.JLabel();
        lblMaNH12 = new javax.swing.JLabel();
        txtNgayDuyet = new javax.swing.JTextField();
        txtNguoiDuyet = new javax.swing.JTextField();
        txtTenCongTy = new javax.swing.JTextField();
        lblMaNH1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lblMaNH13 = new javax.swing.JLabel();
        txtMaHD = new javax.swing.JTextField();
        lblMaNH2 = new javax.swing.JLabel();
        txtNguoiLap = new javax.swing.JTextField();
        lblMaNH15 = new javax.swing.JLabel();
        txtNgayLap = new javax.swing.JTextField();
        lblMaNH16 = new javax.swing.JLabel();
        txtTrangThai = new javax.swing.JTextField();
        lblMaNH14 = new javax.swing.JLabel();
        txtNgayToChuc = new javax.swing.JTextField();
        lblMaNH3 = new javax.swing.JLabel();
        txtBatDau = new javax.swing.JTextField();
        lblMaNH17 = new javax.swing.JLabel();
        txtSLBan = new javax.swing.JTextField();
        lblMaNH18 = new javax.swing.JLabel();
        lblMaNH4 = new javax.swing.JLabel();
        txtKetThuc = new javax.swing.JTextField();
        lblMaNH19 = new javax.swing.JLabel();
        txtThue = new javax.swing.JTextField();
        lblMaNH5 = new javax.swing.JLabel();
        txtTienCoc = new javax.swing.JTextField();
        lblMaNH20 = new javax.swing.JLabel();
        txtChiPhi = new javax.swing.JTextField();
        lblMaNH22 = new javax.swing.JLabel();
        txtChiPhiPhatSinh = new javax.swing.JTextField();
        lblMaNH23 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        lblThanhChu = new javax.swing.JLabel();
        lblMaNH24 = new javax.swing.JLabel();
        cbbSanh = new com.ui.swing.Combobox();
        txtThueThanhTien = new javax.swing.JTextField();
        lbldv = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        pnlDichVu = new javax.swing.JPanel();
        btnDVDK = new com.ui.swing.HoverButton();
        btnTTBan = new com.ui.swing.HoverButton();
        btnTrangTriCong = new com.ui.swing.HoverButton();
        btnTTSanKhau = new com.ui.swing.HoverButton();
        btnDatMon = new com.ui.swing.HoverButton();
        btnNgheThuat = new com.ui.swing.HoverButton();
        jLabel4 = new javax.swing.JLabel();
        btnSaveInfo = new com.ui.swing.HoverButton();
        pnlBtn = new javax.swing.JPanel();
        btnSave = new com.ui.swing.HoverButton();
        btnKyKet = new com.ui.swing.HoverButton();
        btnDuyet = new com.ui.swing.HoverButton();
        btnDanhDauXoa = new com.ui.swing.HoverButton();
        btnComfimHoanThanh = new com.ui.swing.HoverButton();
        btnHuyHopDong = new com.ui.swing.HoverButton();
        btnHuyDuyet = new com.ui.swing.HoverButton();
        pnlInHoaDOn = new javax.swing.JPanel();
        btnInHopDong = new com.ui.swing.HoverButton();
        btnXuatHoaDonTam = new com.ui.swing.HoverButton();
        btnPhanCong = new com.ui.swing.HoverButton();
        btnChiPhiPhatSinh = new com.ui.swing.HoverButton();
        btnXuatHoaDon = new com.ui.swing.HoverButton();

        timePickerBatDau.setForeground(new java.awt.Color(255, 102, 102));

        timePickerKetThuc.setForeground(new java.awt.Color(255, 102, 102));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1650, 980));
        jPanel1.setRequestFocusEnabled(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/happywedding/assets/back.png"))); // NOI18N
        btnBack.setFocusPainted(false);
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBackMouseClicked(evt);
            }
        });
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        jPanel1.add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 13, -1, -1));

        jPanel2.setBackground(new java.awt.Color(248, 248, 248));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel2.setForeground(new java.awt.Color(241, 238, 238));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblMaNH.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMaNH.setText("Số điện thoại ");
        jPanel2.add(lblMaNH, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 69, 100, 30));

        txtHoTenCoDau.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtHoTenCoDau.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtHoTenCoDauFocusLost(evt);
            }
        });
        txtHoTenCoDau.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtHoTenCoDauKeyTyped(evt);
            }
        });
        jPanel2.add(txtHoTenCoDau, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 20, 340, 35));

        txtSDT.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtSDT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSDTFocusLost(evt);
            }
        });
        txtSDT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSDTKeyTyped(evt);
            }
        });
        jPanel2.add(txtSDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 360, 35));

        txtCMND.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtCMND.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCMNDFocusLost(evt);
            }
        });
        txtCMND.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCMNDActionPerformed(evt);
            }
        });
        txtCMND.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCMNDKeyTyped(evt);
            }
        });
        jPanel2.add(txtCMND, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, 360, 35));

        txtHoTenChuRe.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtHoTenChuRe.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtHoTenChuReFocusLost(evt);
            }
        });
        txtHoTenChuRe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHoTenChuReActionPerformed(evt);
            }
        });
        txtHoTenChuRe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtHoTenChuReKeyTyped(evt);
            }
        });
        jPanel2.add(txtHoTenChuRe, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 70, 340, 35));

        txtHoTenKhachHang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtHoTenKhachHang.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtHoTenKhachHangFocusLost(evt);
            }
        });
        txtHoTenKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHoTenKhachHangActionPerformed(evt);
            }
        });
        txtHoTenKhachHang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtHoTenKhachHangKeyTyped(evt);
            }
        });
        jPanel2.add(txtHoTenKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 360, 35));

        lblMaNH6.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMaNH6.setText("Họ tên");
        jPanel2.add(lblMaNH6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 19, 70, 30));

        lblMaNH7.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMaNH7.setText("CMND/CCCD");
        jPanel2.add(lblMaNH7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 129, 90, 30));

        lblMaNH8.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMaNH8.setText("Địa chỉ");
        jPanel2.add(lblMaNH8, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 140, 100, -1));

        lblMaNH9.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMaNH9.setText("Họ tên chú rể");
        jPanel2.add(lblMaNH9, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 69, 110, 30));

        lblMaNH10.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMaNH10.setText("Họ tên cô dâu");
        jPanel2.add(lblMaNH10, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 20, 100, 30));

        taDiaChi.setColumns(20);
        taDiaChi.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        taDiaChi.setRows(3);
        taDiaChi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                taDiaChiKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(taDiaChi);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 130, 340, 80));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 1010, 240));

        jPanel3.setBackground(new java.awt.Color(248, 248, 248));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel3.setForeground(new java.awt.Color(241, 238, 238));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblMaNH11.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMaNH11.setText("Công ty");
        jPanel3.add(lblMaNH11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 29, 70, 20));

        lblMaNH12.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMaNH12.setText("Người duyệt");
        jPanel3.add(lblMaNH12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 90, 30));

        txtNgayDuyet.setEditable(false);
        txtNgayDuyet.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jPanel3.add(txtNgayDuyet, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 360, 35));

        txtNguoiDuyet.setEditable(false);
        txtNguoiDuyet.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtNguoiDuyet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNguoiDuyetActionPerformed(evt);
            }
        });
        jPanel3.add(txtNguoiDuyet, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, 360, 35));

        txtTenCongTy.setEditable(false);
        txtTenCongTy.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTenCongTy.setText("Trung tâm tiệc cưới Happy Wedding");
        txtTenCongTy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenCongTyActionPerformed(evt);
            }
        });
        jPanel3.add(txtTenCongTy, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 360, 35));

        lblMaNH1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMaNH1.setText("Ngày duyệt");
        jPanel3.add(lblMaNH1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 100, 30));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 120, 550, 240));

        jPanel4.setBackground(new java.awt.Color(248, 248, 248));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel4.setForeground(new java.awt.Color(241, 238, 238));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblMaNH13.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMaNH13.setText("Mã hợp đồng");
        jPanel4.add(lblMaNH13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 100, 30));

        txtMaHD.setEditable(false);
        txtMaHD.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtMaHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaHDActionPerformed(evt);
            }
        });
        jPanel4.add(txtMaHD, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 360, 35));

        lblMaNH2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMaNH2.setText("Người lập");
        jPanel4.add(lblMaNH2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 100, 30));

        txtNguoiLap.setEditable(false);
        txtNguoiLap.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jPanel4.add(txtNguoiLap, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 360, 35));

        lblMaNH15.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMaNH15.setText("Ngày lập");
        jPanel4.add(lblMaNH15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 90, 30));

        txtNgayLap.setEditable(false);
        txtNgayLap.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtNgayLap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNgayLapActionPerformed(evt);
            }
        });
        jPanel4.add(txtNgayLap, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, 360, 35));

        lblMaNH16.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMaNH16.setText("Trạng thái");
        jPanel4.add(lblMaNH16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 90, 30));

        txtTrangThai.setEditable(false);
        txtTrangThai.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTrangThaiActionPerformed(evt);
            }
        });
        jPanel4.add(txtTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 180, 360, 35));

        lblMaNH14.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMaNH14.setText("Ngày tổ chức");
        jPanel4.add(lblMaNH14, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 20, 100, 30));

        txtNgayToChuc.setEditable(false);
        txtNgayToChuc.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtNgayToChuc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNgayToChucFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNgayToChucFocusLost(evt);
            }
        });
        txtNgayToChuc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNgayToChucMouseClicked(evt);
            }
        });
        txtNgayToChuc.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txtNgayToChucInputMethodTextChanged(evt);
            }
        });
        txtNgayToChuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNgayToChucActionPerformed(evt);
            }
        });
        jPanel4.add(txtNgayToChuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 20, 360, 35));

        lblMaNH3.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMaNH3.setText("Kết thúc");
        jPanel4.add(lblMaNH3, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 70, 70, 30));

        txtBatDau.setEditable(false);
        txtBatDau.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtBatDau.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBatDauFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBatDauFocusLost(evt);
            }
        });
        txtBatDau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtBatDauMouseClicked(evt);
            }
        });
        jPanel4.add(txtBatDau, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 70, 120, 35));

        lblMaNH17.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMaNH17.setText("Số lượng bàn");
        jPanel4.add(lblMaNH17, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 120, 90, 30));

        txtSLBan.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtSLBan.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSLBanFocusLost(evt);
            }
        });
        txtSLBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSLBanActionPerformed(evt);
            }
        });
        txtSLBan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSLBanKeyTyped(evt);
            }
        });
        jPanel4.add(txtSLBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 120, 360, 35));

        lblMaNH18.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMaNH18.setText("Sảnh");
        jPanel4.add(lblMaNH18, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 190, 90, 30));

        lblMaNH4.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMaNH4.setText("Bắt đầu");
        jPanel4.add(lblMaNH4, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 70, 60, 30));

        txtKetThuc.setEditable(false);
        txtKetThuc.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtKetThuc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtKetThucFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtKetThucFocusLost(evt);
            }
        });
        txtKetThuc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtKetThucMouseClicked(evt);
            }
        });
        jPanel4.add(txtKetThuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 70, 140, 35));

        lblMaNH19.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMaNH19.setText("Thuế %");
        jPanel4.add(lblMaNH19, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 20, 100, 30));

        txtThue.setEditable(false);
        txtThue.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtThue.setText("10");
        txtThue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtThueActionPerformed(evt);
            }
        });
        jPanel4.add(txtThue, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 20, 40, 35));

        lblMaNH5.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMaNH5.setText("Tiền cọc");
        jPanel4.add(lblMaNH5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 70, 100, 30));

        txtTienCoc.setEditable(false);
        txtTienCoc.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTienCoc.setText("0");
        jPanel4.add(txtTienCoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 70, 360, 35));

        lblMaNH20.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMaNH20.setText("Chi phí ");
        lblMaNH20.addHierarchyBoundsListener(new java.awt.event.HierarchyBoundsListener() {
            public void ancestorMoved(java.awt.event.HierarchyEvent evt) {
                lblMaNH20AncestorMoved(evt);
            }
            public void ancestorResized(java.awt.event.HierarchyEvent evt) {
            }
        });
        jPanel4.add(lblMaNH20, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 120, 90, 30));

        txtChiPhi.setEditable(false);
        txtChiPhi.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtChiPhi.setText("0");
        txtChiPhi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtChiPhiActionPerformed(evt);
            }
        });
        jPanel4.add(txtChiPhi, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 120, 360, 35));

        lblMaNH22.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMaNH22.setText("Thành chữ");
        jPanel4.add(lblMaNH22, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 290, 110, 30));

        txtChiPhiPhatSinh.setEditable(false);
        txtChiPhiPhatSinh.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtChiPhiPhatSinh.setText("0");
        txtChiPhiPhatSinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtChiPhiPhatSinhActionPerformed(evt);
            }
        });
        jPanel4.add(txtChiPhiPhatSinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 180, 360, 35));

        lblMaNH23.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lblMaNH23.setText("Tổng tiền");
        jPanel4.add(lblMaNH23, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 240, 90, 30));

        txtTongTien.setEditable(false);
        txtTongTien.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTongTien.setText("0");
        txtTongTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongTienActionPerformed(evt);
            }
        });
        jPanel4.add(txtTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 240, 360, 35));

        lblThanhChu.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblThanhChu.setText("()");
        jPanel4.add(lblThanhChu, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 289, 390, 30));

        lblMaNH24.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblMaNH24.setText("Chi phí phát sinh");
        jPanel4.add(lblMaNH24, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 180, 110, 30));

        cbbSanh.setToolTipText("");
        cbbSanh.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cbbSanh.setLabeText("");
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
        jPanel4.add(cbbSanh, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 170, 360, 40));

        txtThueThanhTien.setEditable(false);
        txtThueThanhTien.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtThueThanhTien.setText("0");
        txtThueThanhTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtThueThanhTienActionPerformed(evt);
            }
        });
        jPanel4.add(txtThueThanhTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 20, 310, 35));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, 1590, 340));

        lbldv.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbldv.setText("Dịch vụ");
        jPanel1.add(lbldv, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 750, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Khách hàng");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Đại diện");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 80, -1, -1));

        pnlDichVu.setBackground(new java.awt.Color(248, 248, 248));
        pnlDichVu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(241, 238, 238)));
        pnlDichVu.setForeground(new java.awt.Color(241, 238, 238));
        pnlDichVu.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        pnlDichVu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnDVDK.setBackground(new java.awt.Color(251, 157, 144));
        btnDVDK.setForeground(new java.awt.Color(255, 255, 255));
        btnDVDK.setText("Dịch vụ đi kèm");
        btnDVDK.setBorderColor(new java.awt.Color(251, 157, 144));
        btnDVDK.setColor(new java.awt.Color(251, 157, 144));
        btnDVDK.setColorClick(new java.awt.Color(251, 157, 144));
        btnDVDK.setColorOver(new java.awt.Color(251, 157, 144));
        btnDVDK.setFocusPainted(false);
        btnDVDK.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnDVDK.setRadius(15);
        btnDVDK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDVDKActionPerformed(evt);
            }
        });
        pnlDichVu.add(btnDVDK, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 10, 130, 40));

        btnTTBan.setBackground(new java.awt.Color(251, 157, 144));
        btnTTBan.setForeground(new java.awt.Color(255, 255, 255));
        btnTTBan.setText("Trang trí bàn");
        btnTTBan.setBorderColor(new java.awt.Color(251, 157, 144));
        btnTTBan.setColor(new java.awt.Color(251, 157, 144));
        btnTTBan.setColorClick(new java.awt.Color(251, 157, 144));
        btnTTBan.setColorOver(new java.awt.Color(251, 157, 144));
        btnTTBan.setFocusPainted(false);
        btnTTBan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnTTBan.setRadius(15);
        btnTTBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTTBanActionPerformed(evt);
            }
        });
        pnlDichVu.add(btnTTBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 120, 40));

        btnTrangTriCong.setBackground(new java.awt.Color(251, 157, 144));
        btnTrangTriCong.setForeground(new java.awt.Color(255, 255, 255));
        btnTrangTriCong.setText("Trang trí cổng");
        btnTrangTriCong.setBorderColor(new java.awt.Color(251, 157, 144));
        btnTrangTriCong.setColor(new java.awt.Color(251, 157, 144));
        btnTrangTriCong.setColorClick(new java.awt.Color(251, 157, 144));
        btnTrangTriCong.setColorOver(new java.awt.Color(251, 157, 144));
        btnTrangTriCong.setFocusPainted(false);
        btnTrangTriCong.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnTrangTriCong.setRadius(15);
        btnTrangTriCong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrangTriCongActionPerformed(evt);
            }
        });
        pnlDichVu.add(btnTrangTriCong, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 120, 40));

        btnTTSanKhau.setBackground(new java.awt.Color(251, 157, 144));
        btnTTSanKhau.setForeground(new java.awt.Color(255, 255, 255));
        btnTTSanKhau.setText("Trang trí sân khấu");
        btnTTSanKhau.setBorderColor(new java.awt.Color(251, 157, 144));
        btnTTSanKhau.setColor(new java.awt.Color(251, 157, 144));
        btnTTSanKhau.setColorClick(new java.awt.Color(251, 157, 144));
        btnTTSanKhau.setColorOver(new java.awt.Color(251, 157, 144));
        btnTTSanKhau.setFocusPainted(false);
        btnTTSanKhau.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnTTSanKhau.setRadius(15);
        btnTTSanKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTTSanKhauActionPerformed(evt);
            }
        });
        pnlDichVu.add(btnTTSanKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 10, -1, 40));

        btnDatMon.setBackground(new java.awt.Color(251, 157, 144));
        btnDatMon.setForeground(new java.awt.Color(255, 255, 255));
        btnDatMon.setText("Đặt món");
        btnDatMon.setBorderColor(new java.awt.Color(251, 157, 144));
        btnDatMon.setColor(new java.awt.Color(251, 157, 144));
        btnDatMon.setColorClick(new java.awt.Color(251, 157, 144));
        btnDatMon.setColorOver(new java.awt.Color(251, 157, 144));
        btnDatMon.setFocusPainted(false);
        btnDatMon.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnDatMon.setRadius(15);
        btnDatMon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatMonActionPerformed(evt);
            }
        });
        pnlDichVu.add(btnDatMon, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, 100, 40));

        btnNgheThuat.setBackground(new java.awt.Color(251, 157, 144));
        btnNgheThuat.setForeground(new java.awt.Color(255, 255, 255));
        btnNgheThuat.setText("Nghệ thuật");
        btnNgheThuat.setBorderColor(new java.awt.Color(251, 157, 144));
        btnNgheThuat.setColor(new java.awt.Color(251, 157, 144));
        btnNgheThuat.setColorClick(new java.awt.Color(251, 157, 144));
        btnNgheThuat.setColorOver(new java.awt.Color(251, 157, 144));
        btnNgheThuat.setFocusPainted(false);
        btnNgheThuat.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnNgheThuat.setRadius(15);
        btnNgheThuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNgheThuatActionPerformed(evt);
            }
        });
        pnlDichVu.add(btnNgheThuat, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, 110, 40));

        jPanel1.add(pnlDichVu, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 790, 1590, 60));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Thông tin hợp đồng");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, -1, -1));

        btnSaveInfo.setBackground(new java.awt.Color(24, 153, 29));
        btnSaveInfo.setForeground(new java.awt.Color(255, 255, 255));
        btnSaveInfo.setText("Lưu thông tin");
        btnSaveInfo.setBorderColor(new java.awt.Color(24, 153, 29));
        btnSaveInfo.setColor(new java.awt.Color(24, 153, 29));
        btnSaveInfo.setColorClick(new java.awt.Color(0, 204, 0));
        btnSaveInfo.setColorOver(new java.awt.Color(0, 204, 0));
        btnSaveInfo.setFocusPainted(false);
        btnSaveInfo.setLabelColor(java.awt.Color.white);
        btnSaveInfo.setLableColorClick(java.awt.Color.white);
        btnSaveInfo.setRadius(15);
        btnSaveInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveInfoActionPerformed(evt);
            }
        });
        jPanel1.add(btnSaveInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 750, -1, 30));

        pnlBtn.setBackground(new java.awt.Color(255, 255, 255));
        pnlBtn.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        pnlBtn.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 15, 5));

        btnSave.setBackground(new java.awt.Color(24, 153, 29));
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setText("Lưu");
        btnSave.setBorderColor(new java.awt.Color(24, 153, 29));
        btnSave.setColor(new java.awt.Color(24, 153, 29));
        btnSave.setColorClick(new java.awt.Color(0, 204, 0));
        btnSave.setColorOver(new java.awt.Color(0, 204, 0));
        btnSave.setFocusPainted(false);
        btnSave.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSave.setLabelColor(java.awt.Color.white);
        btnSave.setLableColorClick(java.awt.Color.white);
        btnSave.setRadius(15);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        pnlBtn.add(btnSave);

        btnKyKet.setBackground(new java.awt.Color(24, 37, 153));
        btnKyKet.setForeground(new java.awt.Color(255, 255, 255));
        btnKyKet.setText("Đã ký kết");
        btnKyKet.setBorderColor(new java.awt.Color(24, 37, 153));
        btnKyKet.setColor(new java.awt.Color(24, 37, 153));
        btnKyKet.setColorClick(new java.awt.Color(51, 51, 255));
        btnKyKet.setColorOver(new java.awt.Color(51, 51, 255));
        btnKyKet.setFocusPainted(false);
        btnKyKet.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnKyKet.setLabelColor(java.awt.Color.white);
        btnKyKet.setLableColorClick(java.awt.Color.white);
        btnKyKet.setRadius(15);
        btnKyKet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKyKetActionPerformed(evt);
            }
        });
        pnlBtn.add(btnKyKet);

        btnDuyet.setBackground(new java.awt.Color(24, 37, 153));
        btnDuyet.setForeground(new java.awt.Color(255, 255, 255));
        btnDuyet.setText("Duyệt");
        btnDuyet.setBorderColor(new java.awt.Color(24, 37, 153));
        btnDuyet.setColor(new java.awt.Color(24, 37, 153));
        btnDuyet.setColorClick(new java.awt.Color(51, 51, 255));
        btnDuyet.setColorOver(new java.awt.Color(51, 51, 255));
        btnDuyet.setFocusPainted(false);
        btnDuyet.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnDuyet.setLabelColor(java.awt.Color.white);
        btnDuyet.setLableColorClick(java.awt.Color.white);
        btnDuyet.setRadius(15);
        btnDuyet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDuyetActionPerformed(evt);
            }
        });
        pnlBtn.add(btnDuyet);

        btnDanhDauXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnDanhDauXoa.setText("Xóa");
        btnDanhDauXoa.setBorderColor(new java.awt.Color(153, 24, 24));
        btnDanhDauXoa.setColor(new java.awt.Color(153, 24, 24));
        btnDanhDauXoa.setColorClick(new java.awt.Color(255, 51, 51));
        btnDanhDauXoa.setColorOver(new java.awt.Color(255, 51, 51));
        btnDanhDauXoa.setFocusPainted(false);
        btnDanhDauXoa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnDanhDauXoa.setLabelColor(java.awt.Color.white);
        btnDanhDauXoa.setLableColorClick(java.awt.Color.white);
        btnDanhDauXoa.setRadius(15);
        btnDanhDauXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDanhDauXoaActionPerformed(evt);
            }
        });
        pnlBtn.add(btnDanhDauXoa);

        btnComfimHoanThanh.setBackground(new java.awt.Color(24, 37, 153));
        btnComfimHoanThanh.setForeground(new java.awt.Color(255, 255, 255));
        btnComfimHoanThanh.setText("Xác nhận hoàn thành");
        btnComfimHoanThanh.setBorderColor(new java.awt.Color(24, 37, 153));
        btnComfimHoanThanh.setColor(new java.awt.Color(24, 37, 153));
        btnComfimHoanThanh.setColorClick(new java.awt.Color(51, 51, 255));
        btnComfimHoanThanh.setColorOver(new java.awt.Color(51, 51, 255));
        btnComfimHoanThanh.setFocusPainted(false);
        btnComfimHoanThanh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnComfimHoanThanh.setLabelColor(java.awt.Color.white);
        btnComfimHoanThanh.setLableColorClick(java.awt.Color.white);
        btnComfimHoanThanh.setRadius(15);
        btnComfimHoanThanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComfimHoanThanhActionPerformed(evt);
            }
        });
        pnlBtn.add(btnComfimHoanThanh);

        btnHuyHopDong.setForeground(new java.awt.Color(255, 255, 255));
        btnHuyHopDong.setText("Hủy");
        btnHuyHopDong.setBorderColor(new java.awt.Color(153, 24, 24));
        btnHuyHopDong.setColor(new java.awt.Color(153, 24, 24));
        btnHuyHopDong.setColorClick(new java.awt.Color(255, 51, 51));
        btnHuyHopDong.setColorOver(new java.awt.Color(255, 51, 51));
        btnHuyHopDong.setFocusPainted(false);
        btnHuyHopDong.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnHuyHopDong.setLabelColor(java.awt.Color.white);
        btnHuyHopDong.setLableColorClick(java.awt.Color.white);
        btnHuyHopDong.setRadius(15);
        btnHuyHopDong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyHopDongActionPerformed(evt);
            }
        });
        pnlBtn.add(btnHuyHopDong);

        btnHuyDuyet.setBackground(new java.awt.Color(24, 37, 153));
        btnHuyDuyet.setForeground(new java.awt.Color(255, 255, 255));
        btnHuyDuyet.setText("Chỉnh sửa hợp đồng");
        btnHuyDuyet.setBorderColor(new java.awt.Color(24, 37, 153));
        btnHuyDuyet.setColor(new java.awt.Color(24, 37, 153));
        btnHuyDuyet.setColorClick(new java.awt.Color(51, 51, 255));
        btnHuyDuyet.setColorOver(new java.awt.Color(51, 51, 255));
        btnHuyDuyet.setFocusPainted(false);
        btnHuyDuyet.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnHuyDuyet.setLabelColor(java.awt.Color.white);
        btnHuyDuyet.setLableColorClick(java.awt.Color.white);
        btnHuyDuyet.setRadius(15);
        btnHuyDuyet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyDuyetActionPerformed(evt);
            }
        });
        pnlBtn.add(btnHuyDuyet);

        jPanel1.add(pnlBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 890, 790, 50));

        pnlInHoaDOn.setBackground(new java.awt.Color(255, 255, 255));
        pnlInHoaDOn.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        pnlInHoaDOn.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 15, 5));

        btnInHopDong.setBackground(new java.awt.Color(77, 76, 125));
        btnInHopDong.setForeground(new java.awt.Color(255, 255, 255));
        btnInHopDong.setText("In hợp đồng");
        btnInHopDong.setBorderColor(new java.awt.Color(77, 76, 125));
        btnInHopDong.setColor(new java.awt.Color(77, 76, 125));
        btnInHopDong.setColorClick(new java.awt.Color(77, 0, 196));
        btnInHopDong.setColorOver(new java.awt.Color(77, 0, 196));
        btnInHopDong.setFocusPainted(false);
        btnInHopDong.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnInHopDong.setLabelColor(java.awt.Color.white);
        btnInHopDong.setLableColorClick(java.awt.Color.white);
        btnInHopDong.setRadius(15);
        btnInHopDong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInHopDongActionPerformed(evt);
            }
        });
        pnlInHoaDOn.add(btnInHopDong);

        btnXuatHoaDonTam.setBackground(new java.awt.Color(77, 76, 125));
        btnXuatHoaDonTam.setForeground(new java.awt.Color(255, 255, 255));
        btnXuatHoaDonTam.setText("Xuất hóa đơn tạm");
        btnXuatHoaDonTam.setBorderColor(new java.awt.Color(77, 76, 125));
        btnXuatHoaDonTam.setColor(new java.awt.Color(77, 76, 125));
        btnXuatHoaDonTam.setColorClick(new java.awt.Color(77, 0, 196));
        btnXuatHoaDonTam.setColorOver(new java.awt.Color(77, 0, 196));
        btnXuatHoaDonTam.setFocusPainted(false);
        btnXuatHoaDonTam.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnXuatHoaDonTam.setLabelColor(java.awt.Color.white);
        btnXuatHoaDonTam.setLableColorClick(java.awt.Color.white);
        btnXuatHoaDonTam.setRadius(15);
        btnXuatHoaDonTam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatHoaDonTamActionPerformed(evt);
            }
        });
        pnlInHoaDOn.add(btnXuatHoaDonTam);

        btnPhanCong.setBackground(new java.awt.Color(77, 76, 125));
        btnPhanCong.setForeground(new java.awt.Color(255, 255, 255));
        btnPhanCong.setText("Phân công");
        btnPhanCong.setBorderColor(new java.awt.Color(77, 76, 125));
        btnPhanCong.setColor(new java.awt.Color(77, 76, 125));
        btnPhanCong.setColorClick(new java.awt.Color(77, 0, 196));
        btnPhanCong.setColorOver(new java.awt.Color(77, 0, 196));
        btnPhanCong.setFocusPainted(false);
        btnPhanCong.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnPhanCong.setLabelColor(java.awt.Color.white);
        btnPhanCong.setLableColorClick(java.awt.Color.white);
        btnPhanCong.setRadius(15);
        btnPhanCong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhanCongActionPerformed(evt);
            }
        });
        pnlInHoaDOn.add(btnPhanCong);

        btnChiPhiPhatSinh.setBackground(new java.awt.Color(77, 76, 125));
        btnChiPhiPhatSinh.setForeground(new java.awt.Color(255, 255, 255));
        btnChiPhiPhatSinh.setText("Chi phí phát sinh");
        btnChiPhiPhatSinh.setBorderColor(new java.awt.Color(77, 76, 125));
        btnChiPhiPhatSinh.setColor(new java.awt.Color(77, 76, 125));
        btnChiPhiPhatSinh.setColorClick(new java.awt.Color(77, 0, 196));
        btnChiPhiPhatSinh.setColorOver(new java.awt.Color(77, 0, 196));
        btnChiPhiPhatSinh.setFocusPainted(false);
        btnChiPhiPhatSinh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnChiPhiPhatSinh.setLabelColor(java.awt.Color.white);
        btnChiPhiPhatSinh.setLableColorClick(java.awt.Color.white);
        btnChiPhiPhatSinh.setRadius(15);
        btnChiPhiPhatSinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChiPhiPhatSinhActionPerformed(evt);
            }
        });
        pnlInHoaDOn.add(btnChiPhiPhatSinh);

        btnXuatHoaDon.setBackground(new java.awt.Color(77, 76, 125));
        btnXuatHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        btnXuatHoaDon.setText("Xuất hóa đơn");
        btnXuatHoaDon.setBorderColor(new java.awt.Color(77, 76, 125));
        btnXuatHoaDon.setColor(new java.awt.Color(77, 76, 125));
        btnXuatHoaDon.setColorClick(new java.awt.Color(77, 0, 196));
        btnXuatHoaDon.setColorOver(new java.awt.Color(77, 0, 196));
        btnXuatHoaDon.setFocusPainted(false);
        btnXuatHoaDon.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnXuatHoaDon.setLabelColor(java.awt.Color.white);
        btnXuatHoaDon.setLableColorClick(java.awt.Color.white);
        btnXuatHoaDon.setRadius(15);
        btnXuatHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatHoaDonActionPerformed(evt);
            }
        });
        pnlInHoaDOn.add(btnXuatHoaDon);

        jPanel1.add(pnlInHoaDOn, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 880, 690, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1650, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 980, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseClicked

    }//GEN-LAST:event_btnBackMouseClicked

    private void txtHoTenKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHoTenKhachHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoTenKhachHangActionPerformed

    private void txtCMNDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCMNDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCMNDActionPerformed

    private void txtHoTenChuReActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHoTenChuReActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoTenChuReActionPerformed

    private void txtTenCongTyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenCongTyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenCongTyActionPerformed

    private void txtNguoiDuyetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNguoiDuyetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNguoiDuyetActionPerformed

    private void txtMaHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaHDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaHDActionPerformed

    private void txtNgayLapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgayLapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgayLapActionPerformed

    private void txtTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTrangThaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTrangThaiActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        if (!isHoaDon) {
            AppStatus.mainApp.showQuanLyHopDong();
        } else {
            AppStatus.mainApp.showQuanLyHoaDon();
        }
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnDVDKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDVDKActionPerformed

        new DichVuDiKem(new JFrame(), isEdit, maHD).setVisible(true);
    }//GEN-LAST:event_btnDVDKActionPerformed

    private void btnTTBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTTBanActionPerformed

        TrangTriBanTiec tr = new TrangTriBanTiec(new JFrame(), isEdit, maHD, (int) hopDong.getSoLuongBan());
        tr.setVisible(true);

    }//GEN-LAST:event_btnTTBanActionPerformed

    private void btnTrangTriCongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrangTriCongActionPerformed

        TrangTriCong tr = new TrangTriCong(new JFrame(), isEdit, maHD);
        tr.setVisible(true);
    }//GEN-LAST:event_btnTrangTriCongActionPerformed


    private void btnTTSanKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTTSanKhauActionPerformed
        TrangTriSanKhau tr = new TrangTriSanKhau(new JFrame(), isEdit, maHD);
        tr.setVisible(true);
    }//GEN-LAST:event_btnTTSanKhauActionPerformed

    private void btnDatMonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatMonActionPerformed
        new DatMon(new JFrame(), isEdit, maHD, (int) hopDong.getSoLuongBan()).setVisible(true);
    }//GEN-LAST:event_btnDatMonActionPerformed

    private void btnNgheThuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNgheThuatActionPerformed

        NgheThuat tr = new NgheThuat(new JFrame(), isEdit, maHD);
        tr.setVisible(true);
    }//GEN-LAST:event_btnNgheThuatActionPerformed

    private void txtNgayToChucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgayToChucActionPerformed

    }//GEN-LAST:event_txtNgayToChucActionPerformed

    private void txtSLBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSLBanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSLBanActionPerformed

    private void txtThueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtThueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtThueActionPerformed

    private void txtChiPhiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtChiPhiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtChiPhiActionPerformed

    private void txtChiPhiPhatSinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtChiPhiPhatSinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtChiPhiPhatSinhActionPerformed

    private void txtTongTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTongTienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTongTienActionPerformed

    private void lblMaNH20AncestorMoved(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_lblMaNH20AncestorMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_lblMaNH20AncestorMoved

    private void btnSaveInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveInfoActionPerformed
        if (!validHopDong()) {
            return;
        }
        if (insertHopDong()) {
            showFeature();
            btnSaveInfo.setVisible(false);
            phanQuyen();
            isEdit();
            hopDong = hopDongDAO.findById(maHD);
        } else {
            DialogHelper.alertError(this, "Lưu không thành công");
        }


    }//GEN-LAST:event_btnSaveInfoActionPerformed

    private void txtSLBanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSLBanKeyTyped
        char testChar = evt.getKeyChar();
        if (!((Character.isDigit(testChar)))) {
            evt.consume();
        }
    }//GEN-LAST:event_txtSLBanKeyTyped

    private void txtBatDauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBatDauMouseClicked
        if (isEdit) {
            timePickerBatDau.showPopup(this, (getWidth() - timePickerBatDau.getPreferredSize().width) / 2, (getHeight() - timePickerBatDau.getPreferredSize().height) / 2);
        }
    }//GEN-LAST:event_txtBatDauMouseClicked

    private void txtNgayToChucMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNgayToChucMouseClicked
        if (isEdit) {
            dateChooser.showPopup();
        }
    }//GEN-LAST:event_txtNgayToChucMouseClicked

    private void txtKetThucMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtKetThucMouseClicked
        if (isEdit) {
            timePickerKetThuc.showPopup(this, (getWidth() - timePickerBatDau.getPreferredSize().width) / 2, (getHeight() - timePickerBatDau.getPreferredSize().height) / 2);
        }
    }//GEN-LAST:event_txtKetThucMouseClicked

    private void cbbSanhItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbSanhItemStateChanged
        if (cbbSanh.getSelectedIndex() != -1 && cbbSanh.getItemCount() == listSanh.size() && evt.getStateChange() == 1) {
            long sucChua = (((Sanh) cbbSanh.getSelectedItem()).getSucChua());
            Sanh sanh = ((Sanh) cbbSanh.getSelectedItem());
            try {
                if (sucChua - (Integer.parseInt(txtSLBan.getText())) * 10 < 0) {
                    DialogHelper.alert(this, "Sảnh này sức chứa tối đa là " + sucChua + " người");
                    txtSLBan.setText(sucChua / 10 + "");

                } else {
                    //  System.out.println("thay doi");

                    if (hopDongDAO.findById(txtMaHD.getText()) == null) {
                        long chiPhi = 0;
                        long tienCoc = 0;
                        long tongTien = 0;
                        long tienThue = 0;
                        chiPhi = ShareHelper.toMoney(sanh.getGiaThueSanh() + (Integer.parseInt(txtSLBan.getText())) * sanh.getGiaBan() + "");

                        tienThue = (long) (chiPhi * ((Integer.parseInt(txtThue.getText())) / 100.0));
                        tongTien = chiPhi + tienThue;
                        tienCoc = (long) (tongTien * (0.5));
                        txtChiPhi.setText(ShareHelper.toMoney(chiPhi));
                        txtTongTien.setText(ShareHelper.toMoney(tongTien));
                        txtTienCoc.setText(ShareHelper.toMoney(tienCoc));
                        lblThanhChu.setText("( " + EnglishNumberToWords.convert(tongTien) + " )");
                        txtThueThanhTien.setText((ShareHelper.toMoney(tienThue)));
                    } else {
                        reloadHopDongVoiSanh(((Sanh) cbbSanh.getSelectedItem()).getMaSanh(), (Integer.parseInt(txtSLBan.getText())));

                    }
                    // checkSanh();
                    // lblKiemTra.requestFocus();
                }
            } catch (Exception e) {
            }
        }


    }//GEN-LAST:event_cbbSanhItemStateChanged

    private void cbbSanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbSanhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbSanhActionPerformed

    private void txtSDTKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSDTKeyTyped
        checkSDT(evt);
    }//GEN-LAST:event_txtSDTKeyTyped

    private void txtCMNDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCMNDKeyTyped
        checkCCCD(evt);
//        char testChar = evt.getKeyChar();
//        if (txtCMND.getText().length() >= 12 && testChar != '\n') {
//            evt.consume();
//            return;
//        }
//
//        if (!((Character.isDigit(testChar)))) {
//            if (testChar == '\n') {
//                String cccd = txtCMND.getText();
//                if (cccd.length() != 9 && cccd.length() != 12 && cccd.length() != 0) {
//                    DialogHelper.alertError(this, "Số Căn cước/Chứng minh không đúng định dạng");
//                    txtCMND.requestFocus();
//                } else {
//                    txtHoTenCoDau.requestFocus();
//                }
//            } else {
//                evt.consume();
//            }
//        }
    }//GEN-LAST:event_txtCMNDKeyTyped

    private void txtSLBanFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSLBanFocusLost
        if (cbbSanh.getSelectedIndex() != -1 && cbbSanh.getItemCount() == listSanh.size()) {
            long sucChua = (((Sanh) cbbSanh.getSelectedItem()).getSucChua());
            Sanh sanh = ((Sanh) cbbSanh.getSelectedItem());
            try {
                if (sucChua - (Integer.parseInt(txtSLBan.getText())) * 10 < 0) {
                    DialogHelper.alert(this, "Sảnh này sức chứa tối đa là " + sucChua + " người");
                    txtSLBan.setText(sucChua / 10 + "");

                } else {
                    if (hopDongDAO.findById(txtMaHD.getText()) == null) {
                        long chiPhi = 0;
                        long tienCoc = 0;
                        long tongTien = 0;
                        long tienThue = 0;
                        chiPhi = ShareHelper.toMoney(sanh.getGiaThueSanh() + (Integer.parseInt(txtSLBan.getText())) * sanh.getGiaBan() + "");

                        tienThue = (long) (chiPhi * ((Integer.parseInt(txtThue.getText())) / 100.0));
                        tongTien = chiPhi + tienThue;
                        tienCoc = (long) (tongTien * (0.5));
                        txtChiPhi.setText(ShareHelper.toMoney(chiPhi));
                        txtTongTien.setText(ShareHelper.toMoney(tongTien));
                        txtTienCoc.setText(ShareHelper.toMoney(tienCoc));
                        txtThueThanhTien.setText((ShareHelper.toMoney(tienThue)));
                        lblThanhChu.setText("( " + EnglishNumberToWords.convert(tongTien) + " )");
                    } else {
                        reloadHopDongVoiSanh(((Sanh) cbbSanh.getSelectedItem()).getMaSanh(), (Integer.parseInt(txtSLBan.getText())));
                    }
                    //lblKiemTra.requestFocus();
                }
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_txtSLBanFocusLost

    private void txtBatDauFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBatDauFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBatDauFocusLost

    private void txtNgayToChucFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNgayToChucFocusLost

    }//GEN-LAST:event_txtNgayToChucFocusLost

    private void txtNgayToChucInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtNgayToChucInputMethodTextChanged
        //  System.out.println("change");
    }//GEN-LAST:event_txtNgayToChucInputMethodTextChanged

    private void txtSDTFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSDTFocusLost
        checkSDT(null);
    }//GEN-LAST:event_txtSDTFocusLost

    private void txtCMNDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCMNDFocusLost
        checkCCCD(null);
    }//GEN-LAST:event_txtCMNDFocusLost

    private void txtHoTenKhachHangKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHoTenKhachHangKeyTyped
        checkName(txtHoTenKhachHang, txtSDT, evt);
    }//GEN-LAST:event_txtHoTenKhachHangKeyTyped

    private void txtHoTenCoDauKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHoTenCoDauKeyTyped
        checkName(txtHoTenCoDau, txtHoTenChuRe, evt);
    }//GEN-LAST:event_txtHoTenCoDauKeyTyped

    private void txtHoTenChuReKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHoTenChuReKeyTyped
        checkName(txtHoTenChuRe, taDiaChi, evt);
    }//GEN-LAST:event_txtHoTenChuReKeyTyped

    private void taDiaChiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_taDiaChiKeyTyped
        char testChar = evt.getKeyChar();

        String name = taDiaChi.getText().trim().replaceAll("\\s+", " ");
        if (name.length() > 255 && testChar != '\n') {
            evt.consume();
            return;
        }

        if (testChar == '\n') {
            if (name.length() == 0) {
                return;
            }
            taDiaChi.setText(name);

        }

    }//GEN-LAST:event_taDiaChiKeyTyped

    private void txtHoTenKhachHangFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHoTenKhachHangFocusLost
        checkName(txtHoTenKhachHang, txtSDT, null);
    }//GEN-LAST:event_txtHoTenKhachHangFocusLost

    private void txtHoTenCoDauFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHoTenCoDauFocusLost
        checkName(txtHoTenCoDau, txtHoTenChuRe, null);
    }//GEN-LAST:event_txtHoTenCoDauFocusLost

    private void txtHoTenChuReFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHoTenChuReFocusLost
        checkName(txtHoTenChuRe, taDiaChi, null);
    }//GEN-LAST:event_txtHoTenChuReFocusLost

    private void txtNgayToChucFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNgayToChucFocusGained

        if (txtNgayToChuc.getText().length() != 0) {
            if (DateHelper.toDate(txtNgayToChuc.getText(), "dd/MM/yyyy").getTime() - DateHelper.toDate(DateHelper.toString(DateHelper.now(), "dd/MM/yyyy"), "dd/MM/yyyy").getTime() < 24 * 3600000) {
                DialogHelper.alertError(this, "Ngày tổ chức sau ít nhất một ngày đặt tiệc");
                txtNgayToChuc.setText("");
                txtNgayToChuc.requestFocus();
                dateChooser.showPopup();
            } else {
                loadSanhPossible();
            }
        }
    }//GEN-LAST:event_txtNgayToChucFocusGained

    private void txtThueThanhTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtThueThanhTienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtThueThanhTienActionPerformed

    private void btnInHopDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInHopDongActionPerformed
        hopDong = hopDongDAO.findById(maHD);
        try {
            Map<String, Object> parameters = new HashMap<String, Object>();
            Connection con = JDBCHelper.getConnection();

            String maHD = this.maHD;
            String maTD = datMonDAO.selectThucDonChinh(maHD);
            String maTDPhu = datMonDAO.selectThucDonPhu(maHD);
            long giaThucDonPhu = 0;
            if (!maTDPhu.equals(maTD)) {
//                maTDPhu = "";
                giaThucDonPhu = datMonDAO.selectDichVuDatMon(maHD, datMonDAO.selectThucDonPhu(maHD)).getChiPhi()
                        + datMonDAO.selectDichVuDatMon(maHD, datMonDAO.selectThucDonPhu(maHD)).getChiPhiPhatSinh();
            } else {
                maTDPhu = "";
            }
            Sanh sanh = new SanhDAO().findByName((hopDong.getSanh()));

            long ttdv = dichVuDAO.selectDichVu(maHD, "TTCONG").getChiPhi()
                    + dichVuDAO.selectDichVu(maHD, "TTCONG").getChiPhiPhatSinh()
                    + dichVuDAO.selectDichVu(maHD, "TTBANTIEC").getChiPhi()
                    + dichVuDAO.selectDichVu(maHD, "TTBANTIEC").getChiPhiPhatSinh()
                    + dichVuDAO.selectDichVu(maHD, "TTSANKHAU").getChiPhi()
                    + dichVuDAO.selectDichVu(maHD, "TTSANKHAU").getChiPhiPhatSinh()
                    + dichVuDAO.selectDichVu(maHD, "NGHETHUAT").getChiPhi()
                    + dichVuDAO.selectDichVu(maHD, "NGHETHUAT").getChiPhiPhatSinh()
                    + datMonDAO.selectDichVuDatMon(maHD, maTD).getChiPhi()
                    + datMonDAO.selectDichVuDatMon(maHD, maTD).getChiPhiPhatSinh()
                    + dichVuDiKemDAO.selectHopDongDichVuDiKem(maHD).getChiPhi() + giaThucDonPhu
                    + dichVuDiKemDAO.selectHopDongDichVuDiKem(maHD).getChiPhiPhatSinh();
            //  + (sanh.getGiaBan() * hopDong.getSoLuongBan()) + sanh.getGiaThueSanh();
            long giaSanh = (sanh.getGiaBan() * hopDong.getSoLuongBan()) + sanh.getGiaThueSanh();
            ttdv += giaSanh;
            String tongTienDichVu = ShareHelper.toMoney(ttdv);
            String thue = ShareHelper.toMoney((long) ((ShareHelper.toMoney(txtChiPhi.getText()) + ShareHelper.toMoney(txtChiPhiPhatSinh.getText())) * (10 / 100.0)));
            String tongTien = ShareHelper.toMoney(hopDong.getTongTien());
            String tienCoc = ShareHelper.toMoney(hopDong.getTienCoc());
            String tienConLai = ShareHelper.toMoney(hopDong.getTongTien() - hopDong.getTienCoc());
            String thanhChu = EnglishNumberToWords.convert(hopDong.getTongTien());
            String thanhChu2 = EnglishNumberToWords.convert(ShareHelper.toMoney(tienConLai));
//            
            URL localPackage = this.getClass().getResource("");
            URL urlLoader = LapHopDong.class.getProtectionDomain().getCodeSource().getLocation();
            //String localDir = localPackage.getPath();
            String loaderDir = urlLoader.getPath().toString();
            //System.out.printf("loaderDir = %s\n localDir = %s\n", loaderDir, localDir);
            String dirPath = loaderDir.substring(0, loaderDir.length() - 15);
            String realPath = dirPath + "/Report/";
            realPath = realPath.replaceFirst("\\/", "");
            realPath = realPath.replaceAll("%20", " ");
            realPath = realPath.replaceAll("/", "\\\\");
            realPath = realPath.replaceAll("H\\\\", "");

            InputStream file = new GetClass().getClass().getResourceAsStream("XuatHopDong.jrxml");
            JasperDesign desgin = JRXmlLoader.load(file);
            net.sf.jasperreports.engine.JasperReport rpt = JasperCompileManager.compileReport(desgin);

            parameters.put("MaHD", maHD);
            parameters.put("MaTD_Chinh", maTD);
            parameters.put("MaTD_Phu", maTDPhu);
            parameters.put("ThanhTien", thanhChu);
            parameters.put("ThanhTien2", thanhChu2);
            parameters.put("Thue", thue + " VND");
            parameters.put("TongTien", tongTien + " VND");
            parameters.put("TienCoc", tienCoc + " VND");
            parameters.put("TienConLai", tienConLai + " VND");
            parameters.put("tongTienDichVu", tongTienDichVu + " VND");
            parameters.put("SUBREPORT_DIR", realPath);
            System.out.println(parameters);
            JasperPrint p = JasperFillManager.fillReport(rpt, parameters, con);
            JasperViewer.viewReport(p, false);

        } catch (SQLException ex) {
            DialogHelper.alert(this,ex.getMessage());
            Logger.getLogger(JasperReport.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
                DialogHelper.alert(this,ex.getMessage());
            Logger.getLogger(JasperReport.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnInHopDongActionPerformed

    private void btnChiPhiPhatSinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChiPhiPhatSinhActionPerformed
        boolean isEdit = false;
        if (hoaDonDAO.selectByID(maHD) != null) {
            if (hoaDonDAO.selectByID(maHD).getTrangTha() == 0 && !hopDong.getTrangThai().equals(StatusHopDong.XOA)) {
                isEdit = true;
            }
        }
        new ChiPhiPhatSinh(maHD, isEdit).setVisible(true);
    }//GEN-LAST:event_btnChiPhiPhatSinhActionPerformed

    private void btnHuyDuyetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyDuyetActionPerformed
        statusHopDong = StatusHopDong.CHODUYET;

        if (hopDongDAO.updateTrangThai(maHD, statusHopDong)) {
            phanQuyen();
            reloadStatus();
            isEdit();

        } else {
            DialogHelper.alertError(this, "Lưu không thành công");
        }
    }//GEN-LAST:event_btnHuyDuyetActionPerformed

    private void btnHuyHopDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyHopDongActionPerformed
        boolean rs = DialogHelper.confirm(this, "Đánh dấu xóa? Không thể quay lại sau thao tác này ?");

        if (rs) {

            updateTrangThaiHopDong(StatusHopDong.DATHUCHIEN);
            if (hopDongDAO.updateTrangThai(maHD, statusHopDong)) {
                phanQuyen();
                reloadStatus();
            } else {
                DialogHelper.alertError(this, "Lưu không thành công");
            }

        }
    }//GEN-LAST:event_btnHuyHopDongActionPerformed

    private void btnComfimHoanThanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComfimHoanThanhActionPerformed
        boolean rs = DialogHelper.confirm(this, "Xác nhận hoàn thành tiệc? Không thể quay lại sau thao tác này ?");

        if (rs) {

            updateTrangThaiHopDong(StatusHopDong.THUCHIEN);
            if (hopDongDAO.updateTrangThai(maHD, statusHopDong)) {
                phanQuyen();
                reloadStatus();
            } else {
                DialogHelper.alertError(this, "Lưu không thành công");
            }

        }

        // hopDongDAO.updateTrangThai( maHD,  statusHopDong);
        //phanQuyen();
    }//GEN-LAST:event_btnComfimHoanThanhActionPerformed

    private void btnDanhDauXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDanhDauXoaActionPerformed
        boolean rs = DialogHelper.confirm(this, "Xóa hợp đồng này ?");
        if (rs) {
            try {
                hopDongDAO.delete(maHD);
                khachHangDAO.delete(maHD);
                btnBack.doClick();
            } catch (Exception e) {
                e.printStackTrace();
                DialogHelper.alertError(this, "Xóa không thành công");
            }

        }
    }//GEN-LAST:event_btnDanhDauXoaActionPerformed

    private void btnDuyetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDuyetActionPerformed

        btnSave.doClick();

        if (!isValid) {
            return;
        }

        updateTrangThaiHopDong(StatusHopDong.CHODUYET);
        updateHopDong();
        // update trạng thái hợp đồng sang chờ ký kết
        if (hopDongDAO.updateTrangThai(maHD, statusHopDong)) {

            phanQuyen();
            reloadStatus();

        } else {
            DialogHelper.alertError(this, "Lưu không thành công");
        }
    }//GEN-LAST:event_btnDuyetActionPerformed

    private void btnKyKetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKyKetActionPerformed
        updateTrangThaiHopDong(StatusHopDong.CHOKYKET);

        // update trạng thái hợp đồng sang chờ ký kết
        if (hopDongDAO.updateTrangThai(maHD, statusHopDong)) {
            phanQuyen();
            reloadStatus();

        } else {
            DialogHelper.alertError(this, "Lưu không thành công");
        }
    }//GEN-LAST:event_btnKyKetActionPerformed

    private void btnPhanCongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhanCongActionPerformed

        PhanCong pc = new PhanCong(new JFrame(), (statusHopDong.equals("DATHUCHIEN") || statusHopDong.equals("XOA") ? false : true), maHD);
        pc.setVisible(true);

    }//GEN-LAST:event_btnPhanCongActionPerformed

    private void btnXuatHoaDonTamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatHoaDonTamActionPerformed

        if (hoaDonDAO.selectByID(maHD) != null) {
            boolean rs = DialogHelper.confirm(this, "Hóa đơn đã được lập. Xuất lại");
            if (rs) {
                In();
                // Xuất hóa đơn
                return;
            }
            return;
        }

        HoaDon hd = new HoaDon();
        hd.setMaHD(maHD);
        hd.setNgayLap(DateHelper.now());
        hd.setMaNV(AppStatus.USER.getMaNV());
        hd.setTrangTha(0);

        hoaDonDAO.insertHoaDon(hd);

        phanQuyen();
        In();
        // In hóa đơn
    }//GEN-LAST:event_btnXuatHoaDonTamActionPerformed


    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed

        //lblMaNH.requestFocus();
        for (int i = 0; i < checkedDichVu.size(); i++) {
            if (checkedDichVu.get(i) == false) {
                DialogHelper.alertError(this, "Chọn đầy đủ dịch vụ");
                isValid = false;
                return;
            }
        }

        if (!validHopDong()) {
            isValid = false;
            return;
        }
        isValid = true;

        if (AppStatus.ROLE.equals("TIEPTAN")) {
            statusHopDong = StatusHopDong.CHODUYET;
            boolean rs = DialogHelper.confirm(this, "Sau khi lưu được chờ duyệt và không thể thay đổi.");
            if (rs) {
                // update thong tin hop dong thành trạng thái đang chờ duyệt
                if (updateHopDong()) {
                    phanQuyen();
                    saveHopDong();
                    reloadStatus();
                    reloadHopDong();
                } else {
                    DialogHelper.alertError(this, "Lưu không thành công");
                }
            }

        } else {
            statusHopDong = StatusHopDong.CHODUYET;
            if (updateHopDong()) {
                phanQuyen();
                saveHopDong();
                reloadStatus();
                reloadHopDong();
                fillFormHopDong(khachHang, hopDong);
            } else {
                DialogHelper.alertError(this, "Lưu không thành công");
            }
        }

    }//GEN-LAST:event_btnSaveActionPerformed

    private void txtKetThucFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtKetThucFocusLost

    }//GEN-LAST:event_txtKetThucFocusLost

    private void txtBatDauFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBatDauFocusGained
        loadSanhPossible();
    }//GEN-LAST:event_txtBatDauFocusGained

    private void txtKetThucFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtKetThucFocusGained
        loadSanhPossible();
    }//GEN-LAST:event_txtKetThucFocusGained

    private void btnXuatHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatHoaDonActionPerformed
        AppStatus.CHIPHIPHATSINH.xuatHoaDon();
    }//GEN-LAST:event_btnXuatHoaDonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ui.swing.InkwellButton btnBack;
    private com.ui.swing.HoverButton btnChiPhiPhatSinh;
    private com.ui.swing.HoverButton btnComfimHoanThanh;
    private com.ui.swing.HoverButton btnDVDK;
    private com.ui.swing.HoverButton btnDanhDauXoa;
    private com.ui.swing.HoverButton btnDatMon;
    private com.ui.swing.HoverButton btnDuyet;
    private com.ui.swing.HoverButton btnHuyDuyet;
    private com.ui.swing.HoverButton btnHuyHopDong;
    private com.ui.swing.HoverButton btnInHopDong;
    private com.ui.swing.HoverButton btnKyKet;
    private com.ui.swing.HoverButton btnNgheThuat;
    private com.ui.swing.HoverButton btnPhanCong;
    private com.ui.swing.HoverButton btnSave;
    private com.ui.swing.HoverButton btnSaveInfo;
    private com.ui.swing.HoverButton btnTTBan;
    private com.ui.swing.HoverButton btnTTSanKhau;
    private com.ui.swing.HoverButton btnTrangTriCong;
    private com.ui.swing.HoverButton btnXuatHoaDon;
    private com.ui.swing.HoverButton btnXuatHoaDonTam;
    private com.ui.swing.Combobox cbbSanh;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblMaNH;
    private javax.swing.JLabel lblMaNH1;
    private javax.swing.JLabel lblMaNH10;
    private javax.swing.JLabel lblMaNH11;
    private javax.swing.JLabel lblMaNH12;
    private javax.swing.JLabel lblMaNH13;
    private javax.swing.JLabel lblMaNH14;
    private javax.swing.JLabel lblMaNH15;
    private javax.swing.JLabel lblMaNH16;
    private javax.swing.JLabel lblMaNH17;
    private javax.swing.JLabel lblMaNH18;
    private javax.swing.JLabel lblMaNH19;
    private javax.swing.JLabel lblMaNH2;
    private javax.swing.JLabel lblMaNH20;
    private javax.swing.JLabel lblMaNH22;
    private javax.swing.JLabel lblMaNH23;
    private javax.swing.JLabel lblMaNH24;
    private javax.swing.JLabel lblMaNH3;
    private javax.swing.JLabel lblMaNH4;
    private javax.swing.JLabel lblMaNH5;
    private javax.swing.JLabel lblMaNH6;
    private javax.swing.JLabel lblMaNH7;
    private javax.swing.JLabel lblMaNH8;
    private javax.swing.JLabel lblMaNH9;
    private javax.swing.JLabel lblThanhChu;
    private javax.swing.JLabel lbldv;
    private javax.swing.JPanel pnlBtn;
    private javax.swing.JPanel pnlDichVu;
    private javax.swing.JPanel pnlInHoaDOn;
    private javax.swing.JTextArea taDiaChi;
    private com.ui.swing.timepicker.TimePicker timePickerBatDau;
    private com.ui.swing.timepicker.TimePicker timePickerKetThuc;
    private javax.swing.JTextField txtBatDau;
    private javax.swing.JTextField txtCMND;
    private javax.swing.JTextField txtChiPhi;
    private javax.swing.JTextField txtChiPhiPhatSinh;
    private javax.swing.JTextField txtHoTenChuRe;
    private javax.swing.JTextField txtHoTenCoDau;
    private javax.swing.JTextField txtHoTenKhachHang;
    private javax.swing.JTextField txtKetThuc;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtNgayDuyet;
    private javax.swing.JTextField txtNgayLap;
    private javax.swing.JTextField txtNgayToChuc;
    private javax.swing.JTextField txtNguoiDuyet;
    private javax.swing.JTextField txtNguoiLap;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSLBan;
    private javax.swing.JTextField txtTenCongTy;
    private javax.swing.JTextField txtThue;
    private javax.swing.JTextField txtThueThanhTien;
    private javax.swing.JTextField txtTienCoc;
    private javax.swing.JTextField txtTongTien;
    private javax.swing.JTextField txtTrangThai;
    // End of variables declaration//GEN-END:variables
}
