/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happywedding.dao;

import com.happywedding.view.manage.DichVuDiKem;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ChiTietDichVuDiKemDAO {
       /*
    thêm vào bảng ChiTietDichVuDiKem
    */
    public boolean insertDichVuDiKem(String maHD, String MaDV, String ghiChu, String ChiPhiPhatSinh){
         throw new UnsupportedOperationException("Not supported yet."); 
    }
    public boolean updateDichVuDiKem(String maHD, String MaDV, String ghiChu, String ChiPhiPhatSinh){
         throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    public boolean removeAllDichVuDiKem( String maHD, String MaDV ){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /*
    lấy các dịch vụ đi kèm có trong hợp đồng
    */
    public List<DichVuDiKem> selectDichVuDiKemInHD(String maHD){
        throw new UnsupportedOperationException("Not supported yet.");
    }
     /*
    lấy các dịch vụ đi kèm ko có trong hợp đồng
    */
    public List<DichVuDiKem> selectDichVuDiKemNotinHopDong(String maHD){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
}
