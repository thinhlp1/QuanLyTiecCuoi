package com.happywedding.dao;

import com.happywedding.helper.JDBCHelper;
import com.happywedding.model.NhanVien;
import java.util.List;

public class NhanVienDAO extends AbstractDAO<NhanVien> {

    private final String INSERT_NHANVIEN = "INSERT NhanVien (MaNV, HoTen, NgaySinh, GioiTinh, SoDienThoai, CCCD_CMND, HinhAnh, MaPB, MaVT, TrangThai) \n"
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    @Override
    public boolean insert(NhanVien nhanVien) {
        int rs =  JDBCHelper.executeUpdate(INSERT_NHANVIEN,nhanVien.getMaNV(),nhanVien.getHoTen(),nhanVien.getNgaySinh(),nhanVien.isGioiTinh(),nhanVien.getSoDienThoai(),nhanVien.getCMND_CCCD(),nhanVien.getHinhAnh(),nhanVien.getMaPB(),nhanVien.getTrangThai());
        return rs > 0;
    }

    @Override
    public boolean update(NhanVien nhanVien) {

    }

    @Override
    public void delete(String id) {
       // là update trạng thái
    }

    @Override
    public List<NhanVien> select() {

    }

    @Override
    public NhanVien findById(String id) {

    }
    
    

}
