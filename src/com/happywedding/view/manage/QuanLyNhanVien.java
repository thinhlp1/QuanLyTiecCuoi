/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.happywedding.view.manage;

import com.happywedding.dao.NhanVienDAO;
import com.happywedding.dao.PhongBanDAO;
import com.happywedding.dao.VaiTroDAO;
import com.happywedding.helper.AppStatus;
import com.happywedding.model.NhanVien;
import com.happywedding.model.PhongBan;
import com.happywedding.model.VaiTro;

import com.ui.swing.datechooser.DateChooser;
import com.happywedding.helper.DateHelper;
import com.happywedding.helper.DialogHelper;
import com.happywedding.helper.ShareHelper;
import java.awt.CardLayout;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.table.DefaultTableModel;

import com.ui.swing.datechooser.DateChooser;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

/**
 *
 * @author ACER
 */
public class QuanLyNhanVien extends javax.swing.JPanel {

    private int currentIndex = 0;
    private NhanVienDAO dao = new NhanVienDAO();
    private DefaultTableModel tblModel;
    private List<NhanVien> listAllEmployee;
    private List<PhongBan> listAllPhongBan;
    private List<VaiTro> listAllVaiTro;

    private VaiTroDAO daoVT = new VaiTroDAO();
    private PhongBanDAO daoPB = new PhongBanDAO();
    private DateChooser dtChooser1 = new DateChooser();

    /**
     * Creates new form QuanLyNhanVien
     */
    public QuanLyNhanVien() {
        initComponents();
        init();

        isView(true);
    }

    public void init() {

        dtChooser1.setTextRefernce(txtNgaySinh);
        tblNhanVien.fixTable(jScrollPane1);
        tblNhanVien.setAutoscrolls(true);

        tblModel = (DefaultTableModel) tblNhanVien.getModel();
        loadEmployee();
        fillTable(listAllEmployee);

        loadPhongBan();
        loadVaiTro();
        initSort();
    }

    public void showCalendar1() {
        dtChooser1.showPopup();
    }

    void loadEmployee() {
        listAllEmployee = dao.select();
    }

    void filtEmployee(String keyword) {
        List<NhanVien> list = new ArrayList<>();
        keyword = keyword.toUpperCase();
        for (int i = 0; i < listAllEmployee.size(); i++) {

            if (listAllEmployee.get(i).toString().toUpperCase().contains(keyword)) {

                list.add(listAllEmployee.get(i));
            }
        }
        fillTable(list);

    }

    NhanVien getModel() {

        List<String> listErorr = validation();

        if (listErorr.size() != 0) {
            DialogHelper.alertError(this, listErorr.toString());
            return null;
        }

        NhanVien model = new NhanVien();
        model.setMaNV(txtMaNhanVien.getText());
        model.setHoTen(txtHoTen.getText());
        model.setNgaySinh(DateHelper.toDate(txtNgaySinh.getText()));
        model.setGioiTinh(rdoNam.isSelected());
        model.setSoDienThoai(txtSoDienThoai.getText());
        model.setEmail(txtEmail.getText());
        model.setCMND_CCCD(txtCCCD.getText());
        model.setMaPB(((PhongBan) cbbPhongBan.getSelectedItem()).getMaPB());
        model.setMaVT(((VaiTro) cbbVaiTro.getSelectedItem()).getMaVT());
        model.setHinhAnh(lblHinhAnh.getToolTipText());
        return model;
    }

    void setModel(NhanVien model) {
        txtMaNhanVien.setText(model.getMaNV());
        txtHoTen.setText(model.getHoTen());
        txtNgaySinh.setText(model.getNgaySinh() != null ? DateHelper.toString(model.getNgaySinh()) : "");
        rdoNam.setSelected(model.isGioiTinh());
        rdoNu.setSelected(!model.isGioiTinh());

        txtSoDienThoai.setText(model.getSoDienThoai());
        txtEmail.setText(model.getEmail());
        txtCCCD.setText(model.getCMND_CCCD());
        for (int i = 0; i < listAllPhongBan.size(); i++) {
            if (model.getMaPB().equals(listAllPhongBan.get(i).getMaPB())) {
                cbbPhongBan.setSelectedIndex(i);
            }
        }
        for (int i = 0; i < listAllVaiTro.size(); i++) {
            if (model.getMaVT().equals(listAllVaiTro.get(i).getMaVT())) {
                cbbVaiTro.setSelectedIndex(i);
            }
        }
        lblHinhAnh.setToolTipText(model.getHinhAnh());
        if (model.getHinhAnh() != null) {
            ImageIcon icon = ShareHelper.readLogo(model.getHinhAnh());
            lblHinhAnh.setIcon(icon);
        }

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

        String cccd = txtCCCD.getText();

        if (evt == null) {
            if (cccd.length() != 9 && cccd.length() != 12 && cccd.length() != 0) {
                DialogHelper.alertError(this, "Số Căn cước/Chứng minh không đúng định dạng");
                txtCCCD.requestFocus();
                return false;
            } else {
                return true;
            }
        }

        char testChar = evt.getKeyChar();
        if (txtCCCD.getText().length() >= 12 && testChar != '\n') {
            evt.consume();
            return true;
        }

        if (!((Character.isDigit(testChar)))) {
            if (testChar == '\n') {

                if (cccd.length() != 9 && cccd.length() != 12 && cccd.length() != 0) {
                    DialogHelper.alertError(this, "Số Căn cước/Chứng minh không đúng định dạng");
                    txtCCCD.requestFocus();
                } else {
                    txtHoTen.requestFocus();
                }
            } else {
                evt.consume();
            }
        }
        return false;
    }

    public boolean checkSDT(KeyEvent evt) {

        String sdt = txtSoDienThoai.getText();
        String numberPhone = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";

        if (evt == null) {
            if (!sdt.matches(numberPhone) && sdt.length() != 0) {
                DialogHelper.alertError(this, "Số điện thoại không đúng định dạng");
                txtSoDienThoai.requestFocus();
                return false;
            } else {

                return true;
            }
        }
        char testChar = evt.getKeyChar();
        if (txtSoDienThoai.getText().length() >= 10 && testChar != '\n') {
            evt.consume();
            return true;
        }
        if (!((Character.isDigit(testChar)))) {
            if (testChar == '\n') {

                if (!sdt.matches(numberPhone) && sdt.length() != 0) {
                    DialogHelper.alertError(this, "Số điện thoại không đúng định dạng");
                    txtSoDienThoai.requestFocus();
                    return false;
                } else {
                    txtCCCD.requestFocus();
                    return true;
                }

            } else {
                evt.consume();
            }
        }
        return false;
    }

    public List<String> validation() {
        String name = txtHoTen.getText().trim().replaceAll("\\s+", " ");
        List<String> listError = new ArrayList<>();

        Pattern pattern;
        String EMAIL_REGEX = "^[a-zA-Z][\\w-]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})$";
        String NumberPhone = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";
        pattern = Pattern.compile(EMAIL_REGEX);

        // check null
        if (txtMaNhanVien.getText().equals("")) {
            listError.add("Mã người học không được để trống");
            return listError;
        } else if (txtHoTen.getText().equals("")) {
            listError.add("Họ tên không được trống");
            return listError;
        } else if (txtNgaySinh.getText().equals("")) {
            listError.add("Ngày sinh không được bỏ trống");
            return listError;
        } else if (txtEmail.getText().equals("")) {
            listError.add("Email không được bỏ trống");
            return listError;
        } else if (txtSoDienThoai.getText().equals("")) {
            listError.add("Số số điện thoại không được bỏ trống");
        } else if (cbbPhongBan.getSelectedIndex() == -1) {
            listError.add("Chưa chọn phòng ban");
            return listError;
        } else if (cbbVaiTro.getSelectedIndex() == -1) {
            listError.add("Chưa chọn Vai Trò");
            return listError;
        }
        if (txtCCCD.getText().length() != 9 && txtCCCD.getText().length() != 12 && txtCCCD.getText().length() != 0) {
            DialogHelper.alertError(this, "Số Căn cước/Chứng minh không đúng định dạng");
            txtCCCD.requestFocus();
            return listError;
        }

        // check email
        if (!txtEmail.getText().matches(EMAIL_REGEX)) {
            listError.add("Email không đúng định dạng");
            return listError;
        }
        // check sdt
        if (!txtSoDienThoai.getText().matches(NumberPhone)) {
            listError.add("Số điện thoại không đúng định dạng");
            return listError;
        }

        //check xong hết
        return listError;

    }

    // các hàm lấy dữ liệu từ cơ sở dữ liệu
    public void loadPhongBan() {
        listAllPhongBan = daoPB.select();
        DefaultComboBoxModel cbbModel = (DefaultComboBoxModel) cbbPhongBan.getModel();
        cbbModel.removeAllElements();
        for (PhongBan s : listAllPhongBan) {
            cbbModel.addElement(s);
        }
        cbbPhongBan.setSelectedIndex(-1);
    }

    public void loadVaiTro() {
        listAllVaiTro = daoVT.select();
        DefaultComboBoxModel cbbModel = (DefaultComboBoxModel) cbbVaiTro.getModel();
        cbbModel.removeAllElements();
        for (VaiTro s : listAllVaiTro) {
            cbbModel.addElement(s);
        }
        cbbVaiTro.setSelectedIndex(-1);
    }

    //Hiển thị lên textfied
    void edit() {
        try {
            String manv = (String) tblNhanVien.getValueAt(this.currentIndex, 0);
            NhanVien model = dao.findById(manv);
            if (model != null) {
                this.setModel(model);
                isView(true);
            }
        } catch (Exception e) {
            DialogHelper.alertError(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    //Clear
    void clear() {
        txtMaNhanVien.setText("");
        txtHoTen.setText("");
        txtNgaySinh.setText("");
        txtSoDienThoai.setText("");
        txtEmail.setText("");
//        rdoNam.setVisible(false);
//        rdoNu.setVisible(false);
        txtCCCD.setText("");
//        cbbPhongBan.setVisible(false);
//        cbbVaiTro.setVisible(false);

        isView(true);
    }

    //Add
    void insert() {
        NhanVien model = getModel();
        if (model == null) {
            return;
        }
        try {
            dao.insert(model);
            loadEmployee();
            this.fillTable(listAllEmployee);
            this.clear();
            DialogHelper.alert(this, "Thêm mới thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            DialogHelper.alertError(this, "Thêm mới thất bại!");
        }
    }

    //Update
    void update() {
        NhanVien model = getModel();
        if (model == null) {
            return;
        }
        try {
            dao.update(model);
            loadEmployee();
            this.fillTable(listAllEmployee);
            DialogHelper.alert(this, "Cập nhật thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            DialogHelper.alertError(this, "Cập nhật thất bại!");
        }
    }

    //Delete
    void delete() {
        if (DialogHelper.confirm(this, "Bạn thực sự muốn xóa người học này?")) {
            String nv = txtMaNhanVien.getText();
            try {
                dao.delete(nv);
                loadEmployee();
                this.fillTable(listAllEmployee);
                this.clear();
                DialogHelper.alert(this, "Xóa thanh công!");
            } catch (HeadlessException e) {
                DialogHelper.alertError(this, "Xóa thất bại!");
            }
        }
    }

    //Di chuyển
    public void setTblSelected(int row) {
        tblNhanVien.setRowSelectionInterval(row, row);
        tblNhanVien.scrollRectToVisible(new Rectangle(tblNhanVien.getCellRect(row, 0, true)));

    }

    //Tìm kiếm
    void fillTable(List<NhanVien> list) {

        tblModel.setRowCount(0);
        try {
            String keyword = txtTimKiem.getText();

            for (NhanVien nv : list) {
                Object[] row = {
                    nv.getMaNV(),
                    nv.getHoTen(),
                    nv.getNgaySinh(),
                    nv.isGioiTinh(),
                    nv.getSoDienThoai(),
                    nv.getEmail(),
                    nv.getCMND_CCCD(),
                    nv.getHinhAnh(),
                    nv.getMaPB(),
                    nv.getMaVT()
                };
                tblModel.addRow(row);
            }
        } catch (Exception e) {
            DialogHelper.alertError(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    public void initSort() {

        cbbSortBy.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                switch (cbbSortBy.getSelectedIndex()) {

                    case 0:
                        if (cbbSort.getSelectedIndex() == 0) {
                            // ten tang dan
                            fillTable(sortByNameKhachHang(false));
                        } else if (cbbSort.getSelectedIndex() == 1) {
                            // ten giam dan
                            fillTable(sortByNameKhachHang(true));
                        }
                        break;
                    case 1:
                        if (cbbSort.getSelectedIndex() == 0) {
                            // ten tang dan
                            fillTable(sortByMaNV(false));
                        } else if (cbbSort.getSelectedIndex() == 1) {
                            // ten giam dan
                            fillTable(sortByMaNV(true));
                        } else {
                            cbbSortBy.setSelectedIndex(0);
                        }
                        break;

                    default:
                        break;
                }
            }
        });

        cbbSort.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                switch (cbbSortBy.getSelectedIndex()) {

                    case 0:
                        if (cbbSort.getSelectedIndex() == 0) {
                            // ten tang dan
                            fillTable(sortByNameKhachHang(false));
                        } else if (cbbSort.getSelectedIndex() == 1) {
                            // ten giam dan
                            fillTable(sortByNameKhachHang(true));
                        }
                        break;
                    case 1:
                        if (cbbSort.getSelectedIndex() == 0) {
                            // ten tang dan
                            fillTable(sortByMaNV(false));
                        } else if (cbbSort.getSelectedIndex() == 1) {
                            // ten giam dan
                            fillTable(sortByMaNV(true));
                        } else {
                            cbbSortBy.setSelectedIndex(0);
                        }
                        break;

                    default:
                        break;
                }
            }
        });
    }

    public List<NhanVien> sortByMaNV(boolean isRevese) {
        List<NhanVien> listSorted = listAllEmployee;
        Collections.sort(listSorted, new Comparator<NhanVien>() {
            public int compare(NhanVien hoaDolon1, NhanVien hoaDolon2) {
                return (hoaDolon1.getMaNV().compareTo(hoaDolon2.getMaNV()));

            }
        });

        if (isRevese) {
            Collections.reverse(listSorted);
        }

        return listSorted;
    }

    public List<NhanVien> sortByNameKhachHang(boolean isRevese) {
        List<NhanVien> listSorted = listAllEmployee;
        Collections.sort(listSorted, new Comparator<NhanVien>() {
            public int compare(NhanVien hopDong1, NhanVien hopDong2) {
                int result = 0;

                String[] partNameEmployee1 = hopDong1.getHoTen().split("\\s");
                String[] partNameEmployee2 = hopDong2.getHoTen().split("\\s");
                if (partNameEmployee1.length == 1) {
                    System.out.println("");
                }

                int nameLenght1 = partNameEmployee1.length;
                int nameLenght2 = partNameEmployee2.length;

                if (nameLenght1 == 1 && nameLenght2 == 1) {
                    return hopDong1.getHoTen().compareToIgnoreCase(hopDong2.getHoTen());
                }

                if (nameLenght1 == 1 && nameLenght2 != 1) {
                    return hopDong1.getHoTen().compareToIgnoreCase(partNameEmployee2[nameLenght2 - 1]);
                } else if (nameLenght1 != -1 && nameLenght2 == 1) {
                    return partNameEmployee1[nameLenght1 - 1].compareToIgnoreCase(hopDong2.getHoTen());
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

//     public List<NhanVien> sortByNgaySinh(boolean isRevese) {
//        List<NhanVien> listSorted = listAllEmployee;
//        Collections.sort(listSorted, new Comparator<NhanVien>() {
//            public int compare(NhanVien hopDong1, NhanVien hopDong2) {
//                return (hopDong1.getNgaySinh().compareTo(hopDong2.getNgaySinh()));
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
    void selectImage() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

            File file = fileChooser.getSelectedFile();

            if (ShareHelper.saveLogo(file)) {
                // Hiển thị hình lên form
                lblHinhAnh.setIcon(ShareHelper.readLogo(file.getName()));
                lblHinhAnh.setToolTipText(file.getName());
            } else {
                DialogHelper.alertError(this, "Vui lòng chọn file hình ảnh");
            }
        }
    }

    void isView(boolean is) {
        txtMaNhanVien.setEditable(false);
        txtHoTen.setEditable(false);
        txtNgaySinh.setEnabled(false);
        txtSoDienThoai.setEditable(false);
        txtEmail.setEditable(false);
        txtCCCD.setEditable(false);

//  
//        btnThem.setEnabled(false);
//        //btnThem.setVisible(false);
//        btnSua.setEnabled(false);
//     //   btnSua.setVisible(false);
//        btnXoa.setVisible(false);
        boolean first = this.currentIndex > 0;
        boolean last = this.currentIndex < tblNhanVien.getRowCount() - 1;
        btnFirst.setEnabled(true && first);
        btnPre.setEnabled(true && first);
        btnLast.setEnabled(true && last);
        btnNext.setEnabled(true && last);
    }

    void isUpdate(boolean is) {
        txtMaNhanVien.setEditable(is);
        txtHoTen.setEditable(is);
        txtNgaySinh.setEnabled(is);
        txtSoDienThoai.setEditable(is);
        txtEmail.setEditable(is);
        txtCCCD.setEditable(is);

        btnSua.setVisible(is);
        if (AppStatus.USER.isGioiTinh()) {
            btnXoa.setVisible(true);
        }

    }

    void isInsert(boolean is) {
        txtMaNhanVien.setEditable(is);
        txtHoTen.setEditable(is);
        txtNgaySinh.setEnabled(is);
        txtSoDienThoai.setEditable(is);
        txtEmail.setEditable(is);
        txtCCCD.setEditable(is);

        btnThem.setVisible(true);
        btnSua.setVisible(false);
        btnXoa.setVisible(false);

        btnFirst.setEnabled(false);
        btnPre.setEnabled(false);
        btnLast.setEnabled(false);
        btnNext.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        pnlUpdate3 = new javax.swing.JPanel();
        txtTimKiem = new javax.swing.JTextField();
        lblSearch = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtMaNhanVien = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblHinhAnh = new javax.swing.JLabel();
        cbbSortBy = new com.ui.swing.Combobox();
        lblSort = new javax.swing.JLabel();
        cbbSort = new com.ui.swing.Combobox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhanVien = new com.ui.swing.Table();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtNgaySinh = new javax.swing.JTextField();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        txtSoDienThoai = new javax.swing.JTextField();
        txtCCCD = new javax.swing.JTextField();
        btnMoi = new com.ui.swing.InkwellButton();
        btnXoa = new com.ui.swing.InkwellButton();
        btnSua = new com.ui.swing.InkwellButton();
        btnThem = new com.ui.swing.InkwellButton();
        cbbVaiTro = new com.ui.swing.Combobox();
        cbbPhongBan = new com.ui.swing.Combobox();
        jLabel12 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        btnFirst = new com.ui.swing.InkwellButton();
        btnLast = new com.ui.swing.InkwellButton();
        btnPre = new com.ui.swing.InkwellButton();
        btnNext = new com.ui.swing.InkwellButton();
        jLabel2 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1650, 950));

        pnlUpdate3.setBackground(new java.awt.Color(255, 255, 255));
        pnlUpdate3.setMinimumSize(new java.awt.Dimension(1600, 838));
        pnlUpdate3.setPreferredSize(new java.awt.Dimension(1680, 1000));
        pnlUpdate3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTimKiem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });
        pnlUpdate3.add(txtTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, 388, 35));

        lblSearch.setBackground(new java.awt.Color(204, 204, 255));
        lblSearch.setForeground(new java.awt.Color(102, 0, 255));
        lblSearch.setAutoscrolls(true);
        lblSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSearchMouseClicked(evt);
            }
        });
        pnlUpdate3.add(lblSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(368, 20, -1, 33));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel4.setText("Mã nhân viên");
        pnlUpdate3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        txtMaNhanVien.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtMaNhanVienFocusLost(evt);
            }
        });
        pnlUpdate3.add(txtMaNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 340, 35));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel5.setText("Họ và tên");
        pnlUpdate3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, -1, -1));

        txtHoTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHoTenActionPerformed(evt);
            }
        });
        pnlUpdate3.add(txtHoTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 340, 35));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel6.setText("Ngày sinh");
        pnlUpdate3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel7.setText("Vai trò");
        pnlUpdate3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 750, -1, -1));

        lblHinhAnh.setBackground(new java.awt.Color(255, 255, 255));
        lblHinhAnh.setForeground(new java.awt.Color(255, 255, 255));
        lblHinhAnh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblHinhAnh.setDoubleBuffered(true);
        lblHinhAnh.setOpaque(true);
        lblHinhAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinhAnhMouseClicked(evt);
            }
        });
        pnlUpdate3.add(lblHinhAnh, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 130, 160));

        cbbSortBy.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tên Nhân Viên", "Mã Nhân Viên" }));
        cbbSortBy.setSelectedIndex(-1);
        cbbSortBy.setLabeText("Sort by");
        cbbSortBy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbSortByActionPerformed(evt);
            }
        });
        pnlUpdate3.add(cbbSortBy, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 0, 270, 54));

        lblSort.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSortMouseClicked(evt);
            }
        });
        pnlUpdate3.add(lblSort, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 30, 32, 35));

        cbbSort.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tăng dần", "Giảm dần" }));
        cbbSort.setLabeText("");
        cbbSort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbSortActionPerformed(evt);
            }
        });
        pnlUpdate3.add(cbbSort, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 0, 120, 54));

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nhân viên", "Họ tên nhân viên", "Ngày sinh", "Giới tính", "Số điện thoại", "Email", "CCCD/CMND", "Hình ảnh", "Phòng ban", "Vai trò"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNhanVien.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        tblNhanVien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblNhanVienKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblNhanVien);

        pnlUpdate3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 70, 1200, 870));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/happywedding/assets/Search.png"))); // NOI18N
        pnlUpdate3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, 40, 40));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/happywedding/assets/filt.png"))); // NOI18N
        pnlUpdate3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 20, 40, 40));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel8.setText("Giới tính");
        pnlUpdate3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel9.setText("Số điện thoại");
        pnlUpdate3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 470, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel10.setText("CCCD/CMND");
        pnlUpdate3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 610, -1, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel11.setText("Phòng ban");
        pnlUpdate3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 680, -1, -1));
        pnlUpdate3.add(txtNgaySinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 290, 35));

        buttonGroup1.add(rdoNam);
        rdoNam.setText("Nam");
        rdoNam.setContentAreaFilled(false);
        rdoNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoNamActionPerformed(evt);
            }
        });
        pnlUpdate3.add(rdoNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 430, -1, -1));

        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");
        rdoNu.setContentAreaFilled(false);
        pnlUpdate3.add(rdoNu, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 430, 50, -1));
        pnlUpdate3.add(txtSoDienThoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, 340, 35));
        pnlUpdate3.add(txtCCCD, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 640, 340, 35));

        btnMoi.setBackground(new java.awt.Color(24, 37, 153));
        btnMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnMoi.setText("Mới");
        btnMoi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });
        pnlUpdate3.add(btnMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 840, 80, -1));

        btnXoa.setBackground(new java.awt.Color(153, 24, 24));
        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setText("Xóa");
        btnXoa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        pnlUpdate3.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 840, 80, -1));

        btnSua.setBackground(new java.awt.Color(0, 153, 0));
        btnSua.setForeground(new java.awt.Color(255, 255, 255));
        btnSua.setText("Lưu");
        btnSua.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        pnlUpdate3.add(btnSua, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 840, 80, -1));

        btnThem.setBackground(new java.awt.Color(0, 153, 0));
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setText("Thêm");
        btnThem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        pnlUpdate3.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 840, 80, -1));

        cbbVaiTro.setLabeText("");
        cbbVaiTro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbVaiTroActionPerformed(evt);
            }
        });
        pnlUpdate3.add(cbbVaiTro, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 780, 340, -1));

        cbbPhongBan.setLabeText("");
        cbbPhongBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbPhongBanActionPerformed(evt);
            }
        });
        pnlUpdate3.add(cbbPhongBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 700, 340, 40));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel12.setText("Email");
        pnlUpdate3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 540, -1, -1));
        pnlUpdate3.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 570, 340, 35));

        btnFirst.setBackground(new java.awt.Color(51, 0, 255));
        btnFirst.setForeground(new java.awt.Color(255, 255, 255));
        btnFirst.setText(">|");
        btnFirst.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnFirst.setPreferredSize(new java.awt.Dimension(45, 27));
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });
        pnlUpdate3.add(btnFirst, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 890, 70, 30));

        btnLast.setBackground(new java.awt.Color(51, 0, 255));
        btnLast.setForeground(new java.awt.Color(255, 255, 255));
        btnLast.setText("|<");
        btnLast.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnLast.setPreferredSize(new java.awt.Dimension(45, 27));
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });
        pnlUpdate3.add(btnLast, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 890, 70, 30));

        btnPre.setBackground(new java.awt.Color(51, 0, 255));
        btnPre.setForeground(new java.awt.Color(255, 255, 255));
        btnPre.setText("<<");
        btnPre.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnPre.setPreferredSize(new java.awt.Dimension(45, 27));
        btnPre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreActionPerformed(evt);
            }
        });
        pnlUpdate3.add(btnPre, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 890, 80, 30));

        btnNext.setBackground(new java.awt.Color(51, 0, 255));
        btnNext.setForeground(new java.awt.Color(255, 255, 255));
        btnNext.setText(">>");
        btnNext.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnNext.setPreferredSize(new java.awt.Dimension(45, 27));
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        pnlUpdate3.add(btnNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 890, 80, 30));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/happywedding/assets/Calendar.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        pnlUpdate3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 350, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlUpdate3, javax.swing.GroupLayout.DEFAULT_SIZE, 1650, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlUpdate3, javax.swing.GroupLayout.DEFAULT_SIZE, 950, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        filtEmployee(txtTimKiem.getText());
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void lblSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSearchMouseClicked

    }//GEN-LAST:event_lblSearchMouseClicked

    private void txtHoTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHoTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoTenActionPerformed

    private void lblHinhAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhAnhMouseClicked
        if (!btnXoa.isVisible() && !btnThem.isVisible()) {
            return;
        }

        selectImage();
    }//GEN-LAST:event_lblHinhAnhMouseClicked

    private void cbbSortByActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbSortByActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbSortByActionPerformed

    private void lblSortMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSortMouseClicked

    }//GEN-LAST:event_lblSortMouseClicked

    private void cbbSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbSortActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbSortActionPerformed

    private void rdoNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoNamActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        // TODO add your handling code here:
        //  clear();
        clear();
        isInsert(true);
    }//GEN-LAST:event_btnMoiActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        isUpdate(true);
        update();
        isView(true);;
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        insert();
    }//GEN-LAST:event_btnThemActionPerformed

    private void cbbVaiTroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbVaiTroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbVaiTroActionPerformed

    private void cbbPhongBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbPhongBanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbPhongBanActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
        currentIndex = tblNhanVien.getRowCount() - 1;
        setTblSelected(currentIndex);
        //isUpdate(false);
        edit();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        showCalendar1();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        currentIndex = tblNhanVien.rowAtPoint(evt.getPoint());

        if (this.currentIndex >= 0) {
            this.edit();
            if (evt.getClickCount() == 2) {
                isUpdate(true);

            }
        }
    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        currentIndex = 0;
        setTblSelected(currentIndex);
        //isUpdate(false);
        edit();
//    this.index = 0;
//        this.edit();
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnPreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreActionPerformed
        // TODO add your handling code here:
        currentIndex--;
        setTblSelected(currentIndex);
        //isUpdate(false);
        edit();
    }//GEN-LAST:event_btnPreActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        currentIndex++;
        setTblSelected(currentIndex);
        // isUpdate(false);
        edit();
    }//GEN-LAST:event_btnNextActionPerformed

    private void tblNhanVienKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblNhanVienKeyReleased
        currentIndex = tblNhanVien.getSelectedRow();
        if (this.currentIndex >= 0) {
            this.edit();

        }
    }//GEN-LAST:event_tblNhanVienKeyReleased

    private void txtMaNhanVienFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMaNhanVienFocusLost
        // TODO add your handling code here:
        checkName(txtHoTen, txtMaNhanVien, null);
    }//GEN-LAST:event_txtMaNhanVienFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ui.swing.InkwellButton btnFirst;
    private com.ui.swing.InkwellButton btnLast;
    private com.ui.swing.InkwellButton btnMoi;
    private com.ui.swing.InkwellButton btnNext;
    private com.ui.swing.InkwellButton btnPre;
    private com.ui.swing.InkwellButton btnSua;
    private com.ui.swing.InkwellButton btnThem;
    private com.ui.swing.InkwellButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.ui.swing.Combobox cbbPhongBan;
    private com.ui.swing.Combobox cbbSort;
    private com.ui.swing.Combobox cbbSortBy;
    private com.ui.swing.Combobox cbbVaiTro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblHinhAnh;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JLabel lblSort;
    private javax.swing.JPanel pnlUpdate3;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private com.ui.swing.Table tblNhanVien;
    private javax.swing.JTextField txtCCCD;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMaNhanVien;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtSoDienThoai;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
