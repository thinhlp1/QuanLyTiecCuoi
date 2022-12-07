package com.happywedding.dao;

import com.happywedding.helper.JDBCHelper;
import com.happywedding.model.NhanVien;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO extends AbstractDAO<NhanVien> {
 private final String INSERT_NV = "INSERT NhanVien (MaNV, HoTen, NgaySinh, GioiTinh, SoDienThoai,Email, CCCD_CMND, HinhAnh, MaPB, MaVT, TrangThai)VALUES(?,?,?,?,?,?,?,?,?,?,?)";
    private final String UPDATE_NV = "UPDATE NhanVien SET HoTen = ?,NgaySinh = ?,GioiTinh = ?,SoDienThoai = ?,CCCD_CMND = ?,Email = ?,HinhAnh = ?,MaPB = ?,MaVT = ?,TrangThai = ? WHERE MaNV = ?" ;
    private final String DELELTE_NV = "DELETE FROM NhanVien WHERE MaNV=?";
    private final String SELECT_ALL_NhanVien = "SELECT * FROM NhanVien";
    private final String SELECT_ALL = "SELECT  nv.MaNV,nv.HoTen,nv.NgaySinh,nv.GioiTinh,nv.SoDienThoai,nv.CCCD_CMND,nv.Email,nv.HinhAnh,nv.MaPB,pb.TenPB,nv.MaVT,vt.TenVT,TrangThai FROM  NhanVien nv INNER JOIN PhongBan pb ON nv.MaPB = pb.MaPB INNER JOIN VaiTro vt ON nv.MaVT = vt.MaVT " ;
    private final String SELECT_BY_ID = "SELECT * FROM NhanVien WHERE MaNV=?";
    private final String SELECT_BY_PHONGBAN = "SELECT  MaNV,HoTen,NgaySinh,GioiTinh,SoDienThoai,CCCD_CMND,Email,HinhAnh,nv.MaPB,pb.TenPB,nv.MaVT,vt.TenVT,TrangThai FROM  NhanVien nv INNER JOIN PhongBan pb ON nv.MaPB = pb.MaPB INNER JOIN VaiTro vt ON nv.MaVT = vt.MaVT WHERE  nv.MaPB = ?";
    private final String SELECT_BY_VaiTro = "SELECT  MaNV,HoTen,NgaySinh,GioiTinh,SoDienThoai,CCCD_CMND,Email,HinhAnh,nv.MaPB,pb.TenPB,nv.MaVT,vt.TenVT,TrangThai FROM  NhanVien nv INNER JOIN PhongBan pb ON nv.MaPB = pb.MaPB INNER JOIN VaiTro vt ON nv.MaVT = vt.MaVT WHERE  nv.MaVT = ?";
    private final String SELECT_BY_NGAYPC ="SELECT nv.MaNV,HoTen,NgaySinh,GioiTinh,SoDienThoai,CCCD_CMND,Email,HinhAnh,nv.MaPB,pb.TenPB,nv.MaVT,vt.TenVT,TrangThai FROM NhanVien nv INNER JOIN ChiTietPhanCong ct ON nv.MaNV = ct.MaNV INNER JOIN PhongBan pb ON nv.MaPB = pb.MaPB INNER JOIN VaiTro vt ON nv.MaVT = vt.MaVT WHERE NgayPC != ? ";
    private final String SELECT_Email= "select * from NhanVien where email = ?";
    private final String SELECT_SDT= "select * from NhanVien where SoDienThoai= ?";
    private final String SELECT_CMND_CCCD= "select * from NhanVien where CCCD_CMND = ?";
    
    
    
    @Override
    public boolean insert(NhanVien nhanVien) {
           int rs = JDBCHelper.executeUpdate(INSERT_NV,
                   nhanVien.getMaNV(),
                   nhanVien.getHoTen(),
                   nhanVien.getNgaySinh(),
                   nhanVien.isGioiTinh(),
                   nhanVien.getSoDienThoai(),
                   nhanVien.getEmail(),
                   nhanVien.getCMND_CCCD(),
                   nhanVien.getHinhAnh(),
                   nhanVien.getMaPB(),
                   nhanVien.getMaVT(),
                   nhanVien.getTrangThai());
        return rs > 0;
    
    }

    @Override
    public boolean update(NhanVien nhanVien) {
           int rs = JDBCHelper.executeUpdate(UPDATE_NV,
                   //SET HoTen = ?,NgaySinh = ?,GioiTinh = ?,SoDienThoai = ?,CCCD_CMND = ?,Email = ?,HinhAnh = ?,MaPB = ?,MaVT = ?,TrangThai = ? WHERE MaNV = ?
                   nhanVien.getHoTen(),
                   nhanVien.getNgaySinh(),
                   nhanVien.isGioiTinh(),
                   nhanVien.getSoDienThoai(),
                   nhanVien.getCMND_CCCD(),
                   nhanVien.getEmail(),                  
                   nhanVien.getHinhAnh(),
                   nhanVien.getMaPB(),
                   nhanVien.getMaVT(),
                   nhanVien.getTrangThai(),
                   nhanVien.getMaNV());
        return rs > 0;
    }

    @Override
    public void delete(String id) {
       // là update trạng thái
       int rs = JDBCHelper.executeUpdate(DELELTE_NV, id);
    }

    @Override
    public List<NhanVien> select() {
        return select(SELECT_ALL);
    }

    @Override
    public NhanVien findById(String id) {
       List<NhanVien> list = select(SELECT_BY_ID, id);
         return list.size() > 0 ? list.get(0) : null;
    }
    private List select(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.executeQuery(sql, args);
                while (rs.next()) {
                    NhanVien model = readFromResultSet(rs);
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }
    
     private NhanVien readFromResultSet(ResultSet rs) throws SQLException {
         //MaNV, HoTen, NgaySinh, GioiTinh, SoDienThoai,Email, CCCD_CMND, HinhAnh, MaPB, MaVT, TrangThai
        NhanVien model = new NhanVien();
        model.setMaNV(rs.getString("MaNV"));
        model.setHoTen(rs.getString("HoTen"));
        model.setNgaySinh(rs.getDate("NgaySinh"));
        model.setGioiTinh(rs.getBoolean("GioiTinh"));
        model.setSoDienThoai(rs.getString("SoDienThoai"));
        model.setEmail(rs.getString("Email"));
        model.setCMND_CCCD(rs.getString("CCCD_CMND"));
        model.setHinhAnh(rs.getString("HinhAnh"));
        model.setMaPB(rs.getString("MaPB"));
        model.setMaVT(rs.getString("MaVT"));
        model.setTrangThai(rs.getByte("TrangThai"));   
        return model;
    }
    
 
     /*
    Lấy danh sách nhân viên chua được phân công
    */
    public List<NhanVien> selectNhanVienChuaDuocPhanCong(String ngayPC){
                 return select(SELECT_BY_NGAYPC);
    }
    
    /*
    Kiểm tra xem email có tồn tại chưa
    */
    public boolean checkEmail(String email){
        List<NhanVien> list = select(SELECT_Email, email);
        return list.size() > 0;
    }
    
     /*
    Kiểm tra xem sdt có tồn tại chưa
    */
    public boolean checkSDT(String sdt){
         List<NhanVien> list = select(SELECT_SDT, sdt);
        return list.size() > 0;
    }
    
     /*
    Kiểm tra xem cccd có tồn tại chưa
    */
    public boolean checkCCCD(String cccd){
        List<NhanVien> list = select(SELECT_CMND_CCCD, cccd);
        return list.size() > 0;
    }
}
