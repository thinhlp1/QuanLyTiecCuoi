package com.happywedding.dao;

import com.happywedding.helper.JDBCHelper;
import com.happywedding.model.NhanVien;
import java.util.List;

public class NhanVienDAO extends AbstractDAO<NhanVien> {


    @Override
    public boolean insert(NhanVien nhanVien) {
           throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
    }

    @Override
    public boolean update(NhanVien nhanVien) {
           throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String id) {
       // là update trạng thái
    }

    @Override
    public List<NhanVien> select() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public NhanVien findById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
 
     /*
    Lấy danh sách nhân viên chua được phân công
    */
    public List<NhanVien> selectNhanVienChuaDuocPhanCong(String ngayPC){
         throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /*
    Kiểm tra xem email có tồn tại chưa
    */
    public boolean checkEmail(String email){
         throw new UnsupportedOperationException("Not supported yet.");
    }
    
     /*
    Kiểm tra xem sdt có tồn tại chưa
    */
    public boolean checkSDT(String sdt){
         throw new UnsupportedOperationException("Not supported yet.");
    }
    
     /*
    Kiểm tra xem cccd có tồn tại chưa
    */
    public boolean checkCCCD(String cccd){
         throw new UnsupportedOperationException("Not supported yet.");
    }
    
    

}
